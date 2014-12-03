package com.wheelsinmotion.jose.wimo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by JoseMaria on 25/10/14.
 */
public class home extends Activity implements LocationListener {
    Location lastLocation = null;
    float actualDistanceMeters = 0, totalDistanceMeters = 0;
    int actualDistanceKilometers = 0, totalDistanceKilometers = 0;

    public static final String TOTAL_KILOMETERS = "TotalKilometers";

    TextView kilometers_count;
    TextView meters_count;

    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        SharedPreferences settings = getSharedPreferences(TOTAL_KILOMETERS, 0);
        totalDistanceMeters = settings.getInt("total_meters", 0);
        totalDistanceKilometers = settings.getInt("total_km", 0);

        kilometers_count = (TextView) findViewById(R.id.kilometers);
        meters_count = (TextView) findViewById(R.id.meters);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);

        extrapolateKilometers();

        kilometers_count.setText(""+ totalDistanceKilometers+".");
        meters_count.setText(""+(int) totalDistanceMeters);
    }

    //TODO check this algorithm
    private void extrapolateKilometers(){
        if(totalDistanceMeters > 990){
            while(totalDistanceMeters >990){
                totalDistanceKilometers ++;
                totalDistanceMeters -= 990;
            }
            kilometers_count.setText(""+totalDistanceKilometers+".");
            totalDistanceMeters = 0;
            meters_count.setText("0");
        }
        else{
            meters_count.setText(""+((int)(totalDistanceMeters/10)));
        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences settings = getSharedPreferences(TOTAL_KILOMETERS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("total_meters", (int) totalDistanceMeters);
        editor.putInt("total_km", totalDistanceKilometers);

        editor.commit();
    }

    /************* Called after each 3 sec **********/
    @Override
    public void onLocationChanged(Location location) {
        if(lastLocation == null){
            lastLocation = location;
            return;
        }
        else{
            actualDistanceMeters = location.distanceTo(lastLocation);
            totalDistanceMeters += actualDistanceMeters;
            lastLocation = location;
            extrapolateKilometers();
            kilometers_count.setText(""+totalDistanceKilometers+".");
        }

        //kilometers.setText(totalDistanceMeters);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle){
    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
