package com.carbonrider.android.slidermenu.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.carbonrider.android.slidermenu.R;
import com.carbonrider.android.slidermenu.animation.MoveAnimation;

/**
 * @author Yogesh C. Jadhav
 * @since 1.0
 */
public class ExampleActivity extends AppCompatActivity {

    private static final int LEFT_SLIDE_TRY_OPTIONS_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        listenForEvents();
    }

    private void listenForEvents() {
        Button btnPlay = (Button) findViewById(R.id.btn_try);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayOverlayContainer();
            }
        });
    }


    private void displayOverlayContainer() {
        RelativeLayout prefLayout = (RelativeLayout) findViewById(R.id.first_layout);
        LinearLayout tryOptionsLayout = (LinearLayout) findViewById(R.id.overlay_layout);

        tryOptionsLayout.setVisibility(View.VISIBLE);

        int marginToSet = prefLayout.getWidth();

        tryOptionsLayout.setX(marginToSet);

        Animation animation = new MoveAnimation(prefLayout.getWidth(), tryOptionsLayout);
        animation.setDuration(LEFT_SLIDE_TRY_OPTIONS_DURATION);
        tryOptionsLayout.startAnimation(animation);
    }


}
