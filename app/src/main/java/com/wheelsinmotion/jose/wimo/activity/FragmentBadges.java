package com.wheelsinmotion.jose.wimo.activity;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wheelsinmotion.jose.wimo.R;
import com.wheelsinmotion.jose.wimo.model.Badge;
import com.wheelsinmotion.jose.wimo.util.BadgesListAdapter;
import com.wheelsinmotion.jose.wimo.util.DBHelper;

/**
 * Created by jose on 12/3/14.
 */
public class FragmentBadges extends ListFragment {
    String[] pepe;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_badges_list, container, false);
        //list_items = getResources().getStringArray(R.array.list);
        getCurrentBadges();
        String[] list_items = getResources().getStringArray(R.array.list);
        setListAdapter(new BadgesListAdapter(getActivity(), pepe, getCurrentBadges()));

        return rootView;
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {*/

       /* View rootView = inflater.inflate(R.layout.fragment_badges_list, null);
        getCurrentBadges();
        BadgesListAdapter adapter = new BadgesListAdapter(this.getActivity(), pepe, getCurrentBadges());
        ListView badgeslist = (ListView) rootView.findViewById(R.id.badges_list);
        badgeslist.setAdapter(adapter);
        badgeslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO do some
            }
        });

        return rootView;
        return inflater.inflate(R.layout.fragment_badges_list, container, false);
    }*/

    private Badge[] getCurrentBadges(){
        DBHelper dbHelper = DBHelper.getHelper(getActivity());
        //.getReadableDatabase();
        Object[] aux =dbHelper.getBadges(1111).toArray();
        Badge[] badges = new Badge[aux.length];
        pepe = new String[aux.length];
        int counter = 0;
        for(Object o : aux){
            badges[counter] = (Badge)o;
            pepe[counter] = ((Badge) o).getName();
            counter++;
        }
        return badges;
    }

}