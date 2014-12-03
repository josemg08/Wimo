package com.wheelsinmotion.jose.wimo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
