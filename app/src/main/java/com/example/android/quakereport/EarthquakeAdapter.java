package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hossam on 3/17/18.
 */

public class EarthquakeAdapter extends ArrayAdapter
{
    private static final String LOCATION_SEPARATOR = " of ";

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

        TextView magnitudeView = listItem.findViewById(R.id.magnitude);
        DecimalFormat formatter = new DecimalFormat("0.0");
        magnitudeView.setText(formatter.format(earthquake.getMagnitude()));
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        magnitudeCircle.setColor(getMagnitudeColor(earthquake.getMagnitude()));

        String originalLocation = earthquake.getLocation();
        String primaryLocation, locationOffset;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView locationOffsetView = listItem.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        TextView primaryLocationView = listItem.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        Date dateObject = new Date(earthquake.getTimeInMilliseconds());

        TextView date = listItem.findViewById(R.id.date);
        date.setText(formatDate(dateObject));

        TextView time = listItem.findViewById(R.id.time);
        time.setText(formatTime(dateObject));

        return listItem;
    }

    private int getMagnitudeColor(double magnitude) {
        int color;
        switch ((int) Math.floor(magnitude)) {
            case 0:
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
            case 3:
                color = R.color.magnitude3;
                break;
            case 4:
                color = R.color.magnitude4;
                break;
            case 5:
                color = R.color.magnitude5;
                break;
            case 6:
                color = R.color.magnitude6;
                break;
            case 7:
                color = R.color.magnitude7;
                break;
            case 8:
                color = R.color.magnitude8;
                break;
            case 9:
                color = R.color.magnitude9;
                break;
            default:
                color = R.color.magnitude10plus;
        }
        System.out.println(color);
        return ContextCompat.getColor(getContext(), color);
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
