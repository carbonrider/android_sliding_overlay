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
package com.carbonrider.android.slidermenu.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * @author  Yogesh C. Jadhav
 * @since 1.0
 */
public class RemoveAnimation extends Animation {
    private final int margin, currentMargin;
    private final LinearLayout layoutToRemove;

    public RemoveAnimation(int margin, LinearLayout layoutToRemove, int currentMargin) {
        this.margin = margin;
        this.layoutToRemove = layoutToRemove;
        this.currentMargin = currentMargin;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int marginToSet = currentMargin + (int) ((margin - currentMargin) * interpolatedTime);
        layoutToRemove.setX(marginToSet);
    }
}
