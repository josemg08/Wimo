package com.wheelsinmotion.jose.wimo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wheelsinmotion.jose.wimo.R;

/**
 * Created by JoseMaria on 24/10/14.
 */

public class Activitylogin extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void jumpToHome(View view){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

}
