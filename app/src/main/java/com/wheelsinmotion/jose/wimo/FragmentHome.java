package com.wheelsinmotion.jose.wimo;

import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jose on 12/3/14.
 */
public class FragmentHome extends Fragment implements LocationListener {
    Location lastLocation = null;
    float actualDistanceMeters = 0, totalDistanceMeters = 0;
    int actualDistanceKilometers = 0, totalDistanceKilometers = 0;

    public static final String TOTAL_KILOMETERS = "TotalKilometers";

    TextView kilometers_count;
    TextView meters_count;

    private LocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences settings = getActivity().getSharedPreferences(TOTAL_KILOMETERS, 0);
        totalDistanceMeters = settings.getInt("total_meters", 0);
        totalDistanceKilometers = settings.getInt("total_km", 0);

        kilometers_count = (TextView) rootView.findViewById(R.id.kilometers);
        meters_count = (TextView) rootView.findViewById(R.id.meters);

        locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);

        extrapolateKilometers();

        kilometers_count.setText(""+ totalDistanceKilometers+".");
        meters_count.setText(""+(int) totalDistanceMeters);

        return rootView;
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
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}