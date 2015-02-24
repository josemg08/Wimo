package com.wheelsinmotion.jose.wimo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import com.wheelsinmotion.jose.wimo.R;

/**
 * Created by JoseMaria on 21/11/2014.
 */
public class ActivityTutorial extends Activity{
    private ViewFlipper viewFlipper;
    private float lastElement;
    private ImageButton lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutorial);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        lastPage = (ImageButton) findViewById(R.id.lastPage);
        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });

    }



    //Switch throw every possible swipe to make the transition
    public boolean onTouchEvent(MotionEvent touchEvent) {

        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                lastElement = touchEvent.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float currentElement = touchEvent.getX();

                if (lastElement < currentElement) {
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();
                }

                // if right to left swipe on screen
                if (lastElement > currentElement) {
                    if (viewFlipper.getDisplayedChild() == 3)
                        break;

                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show the next Screen
                    viewFlipper.showNext();
                }
                break;
            }
        }
        return false;
    }

    private void goToHome(){
        Intent intent = new Intent(this, Activitylogin.class);
        startActivity(intent);
    }

}
