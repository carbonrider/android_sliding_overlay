/*
* The MIT License (MIT)
* Copyright (c) 2016 Yogesh C. Jadhav
* Permission is hereby granted, free of charge, to any person obtaining a copy of this software
* and associated documentation files (the "Software"), to deal in the Software without restriction,
* including without limitation the rights to use, copy, modify, merge, publish, distribute,
* sublicense, and/or sell copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all copies or
* substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
* AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
* DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package com.carbonrider.android.slidermenu.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.carbonrider.android.slidermenu.animation.RemoveAnimation;

/**
 * @author Yogesh C. Jadhav
 * @since 1.0
 */
public class SlideLinearLayout extends LinearLayout {

    private float touchDownX, dragStartX;
    private boolean isLayoutDragging;
    private double dragDelta = 30;

    private static final int LEFTOVER_RIGHT_SLIDE_ANIME_DURATION = 500;


    public SlideLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SlideLinearLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownX = event.getX();
                dragStartX = touchDownX;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isLayoutDragging = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();

                float yDeltaTotal = x - dragStartX;
                if (Math.abs(yDeltaTotal) > dragDelta) {
                    isLayoutDragging = true;
                    dragStartX = x;
                    return true;
                }
                break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isLayoutDragging = false;
                float upX = event.getX();
                float deltaX = upX - dragStartX;
                if (deltaX > dragDelta) {
                    swipeRemove();
                } else {
                    swipe(0);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();

                float xDelta = x - dragStartX;
                if (!isLayoutDragging && Math.abs(xDelta) > dragDelta) {
                    isLayoutDragging = true;
                }

                if (isLayoutDragging) {
                    swipe((int) xDelta);
                }

                break;
        }

        return true;
    }


    private void swipe(int distance) {
        View animationView = this;
        if (distance >= 0)
            animationView.setX(distance);
    }

    private void swipeRemove() {
        RemoveAnimation removeAnimation = new RemoveAnimation(
                this.getWidth()
                , this
                , (int) this.getX());
        removeAnimation.setDuration(LEFTOVER_RIGHT_SLIDE_ANIME_DURATION);
        removeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SlideLinearLayout.this.setVisibility(View.GONE);
                SlideLinearLayout.this.setX(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.startAnimation(removeAnimation);
    }


}
