package com.wheelsinmotion.jose.wimo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by JoseMaria on 24/10/14.
 */

public class ActivitySplash extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void jumpToLogin(View view){
        Intent intent = new Intent(this, Activitylogin.class);
        startActivity(intent);
    }
}
