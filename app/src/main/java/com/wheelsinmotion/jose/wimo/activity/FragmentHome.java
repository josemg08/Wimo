package com.wheelsinmotion.jose.wimo.activity;

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

import com.wheelsinmotion.jose.wimo.R;

/**
 * Created by jose on 12/3/14.
 */
public class FragmentHome extends Fragment implements LocationListener {
    private Location lastLocation = null;
    private float actualDistanceMeters = 0, totalDistanceMeters = 0;
    private int kilometersForTree = 0, kilometersForWimoCash = 0;
    private int actualDistanceKilometers = 0, totalDistanceKilometers = 0;
    private int totalWimoCash = 0, totalTrees = 0;

    private static final String TOTAL_KILOMETERS = "TotalKilometers";

    private TextView kilometers_count;
    private TextView meters_count;
    private TextView total_kilometers_count;
    private TextView total_wimo_cash;
    private TextView total_trees;

    private LocationManager locationManager;

    private SharedPreferences settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        settings = getActivity().getSharedPreferences(TOTAL_KILOMETERS, 0);
        totalDistanceMeters = settings.getInt("total_meters", 0);
        totalDistanceKilometers = settings.getInt("total_km", 0);
        totalWimoCash = settings.getInt("total_wimo_cash", 0);
        totalTrees = settings.getInt("total_trees", 0);

        kilometers_count = (TextView) rootView.findViewById(R.id.kilometers);
        meters_count = (TextView) rootView.findViewById(R.id.meters);
        total_kilometers_count = (TextView) rootView.findViewById(R.id.total_kilometers);
        total_wimo_cash = (TextView) rootView.findViewById(R.id.wimo_cash_label);
        total_trees = (TextView) rootView.findViewById(R.id.trees_label);

        locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);

        extrapolateKilometers();

        writteKilometer(totalDistanceKilometers);
        writteMeter((int)totalDistanceMeters);
        writteTotalKilometers(totalDistanceKilometers);
        writteTrees(totalTrees);
        writteWimoCash(totalWimoCash);

        return rootView;
    }

    //TODO check this algorithm
    private void extrapolateKilometers(){
        if(totalDistanceMeters > 990){
            while(totalDistanceMeters >990){
                totalDistanceKilometers ++;
                totalDistanceMeters -= 990;

                kilometersForTree++;
                kilometersForWimoCash++;
                if(kilometersForTree > 2){
                    totalTrees++;
                    kilometersForTree = 0;
                    writteTrees(totalTrees);
                }
                if(kilometersForWimoCash > 3){
                    totalWimoCash++;
                    kilometersForWimoCash = 0;
                    writteWimoCash(totalWimoCash);
                }
            }
            kilometers_count.setText(""+totalDistanceKilometers+".");
            totalDistanceMeters = 0;
            writteMeter(0);
        }
        else{
            writteMeter((int)(totalDistanceMeters/10));
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
            writteKilometer(totalDistanceKilometers);
            writteTotalKilometers(totalDistanceKilometers);
        }

        //kilometers.setText(totalDistanceMeters);
    }

    @Override
    public void onStop(){
        super.onStop();

        settings = getActivity().getSharedPreferences(TOTAL_KILOMETERS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("total", (int) totalDistanceMeters);
        editor.putInt("total_km", totalDistanceKilometers);
        editor.putInt("total_wimo_cash", totalWimoCash);
        editor.putInt("total_trees", totalTrees);
        editor.commit();
    }

    private void writteKilometer(int kilometers){
        if(kilometers < 10)
            kilometers_count.setText("0"+kilometers+".");
        else
            kilometers_count.setText(""+kilometers+".");
    }

    private void writteMeter(int meters){
        if(meters < 10)
            meters_count.setText("0"+meters);
        else
            meters_count.setText(""+meters);
    }

    private void writteTotalKilometers(int kilometers){
        total_kilometers_count.setText("Km totales "+kilometers);
    }

    private void writteWimoCash(int wimoCash){
        //total_wimo_cash.setText(""+wimoCash);
    }

    private void writteTrees(int trees){
        //total_trees.setText(""+trees);
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