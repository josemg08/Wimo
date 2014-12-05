package com.wheelsinmotion.jose.wimo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.wheelsinmotion.jose.wimo.R;
import com.wheelsinmotion.jose.wimo.model.Badge;
import com.wheelsinmotion.jose.wimo.util.DBHelper;

/**
 * Created by JoseMaria on 24/10/14.
 */

public class ActivitySplash extends Activity {
    private static final String INSTANCES = "Instances";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DBHelper db = new DBHelper(this);
        SharedPreferences instances = getSharedPreferences(INSTANCES, MODE_WORLD_WRITEABLE);

        //this is to initialize instances in the data base for the first time
        if(instances.getBoolean("badges_initialized", false)){
            initBadges(db, instances);
        }
        db.closeDB();
    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, Activitylogin.class);
        startActivity(intent);
    }

    private void initBadges(DBHelper db, SharedPreferences instances){
        Badge badgeBronceTree = new Badge(System.currentTimeMillis(), "Badge Bronce Tree", 10, R.drawable.badge_tree_bronce);
        Badge badgeSilverTree = new Badge(System.currentTimeMillis(), "Badge Silver Tree", 100, R.drawable.badge_tree_silver);
        Badge badgeGoldTree = new Badge(System.currentTimeMillis(), "Badge Gold Tree", 1000, R.drawable.badge_tree_gold);

        db.createBadge(badgeBronceTree);
        db.createBadge(badgeSilverTree);
        db.createBadge(badgeGoldTree);

        SharedPreferences.Editor editor = instances.edit();
        editor.putBoolean("badges_initialized", true);
        editor.commit();
    }

}
