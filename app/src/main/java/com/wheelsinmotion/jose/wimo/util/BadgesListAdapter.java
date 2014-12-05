package com.wheelsinmotion.jose.wimo.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wheelsinmotion.jose.wimo.R;
import com.wheelsinmotion.jose.wimo.model.Badge;

/**
 * Created by jose on 12/4/14.
 */
public class BadgesListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private Badge[] badges;

    public BadgesListAdapter(Activity context, String[] text, Badge[] badges) {
        super(context, R.layout.fragment_badges_listrow, text);
        this.context = context;
        this.badges = badges;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.fragment_badges_listrow, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.text);

        /*TableRow tableRow = (TableRow) rowView.findViewById(R.id.list_row);
        tableRow.setBackground(franchises[position].getBackground());*/

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtTitle.setText(badges[position].getName());

        imageView.setImageResource(badges[position].getDrawableValue());

        return rowView;
    }

}
