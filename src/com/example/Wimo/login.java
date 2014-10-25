package com.example.Wimo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by JoseMaria on 24/10/14.
 */

public class login extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void jumpToHome(View view){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

}
