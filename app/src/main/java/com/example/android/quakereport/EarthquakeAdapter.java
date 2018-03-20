package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hossam on 3/17/18.
 */

public class EarthquakeAdapter extends ArrayAdapter
{
    public EarthquakeAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        Earthquake earthquake = (Earthquake) getItem(position);

        TextView magnitude = listItem.findViewById(R.id.magnitude);
        magnitude.setText(earthquake.getMagnitude());

        TextView location = listItem.findViewById(R.id.location);
        location.setText(earthquake.getLocation());

        Date dateObject = new Date(earthquake.getTimeInMilliseconds());

        TextView date = listItem.findViewById(R.id.date);
        date.setText(formatDate(dateObject));

        TextView time = listItem.findViewById(R.id.time);
        time.setText(formatTime(dateObject));

        return listItem;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(dateObject);
    }
}
