/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    private EarthquakeAdapter adapter;
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Earthquake earthquake = (Earthquake) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquake.getUrl()));

                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        new EarthquakeAsyncTask().execute(USGS_REQUEST_URL);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {
        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return QueryUtils.fetchEarthquakesData(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            // Clear the adapter of previous earthquake data
            adapter.clear();

            if (earthquakes != null && !earthquakes.isEmpty()) {
                adapter.addAll(earthquakes);
            }
        }
    }
}
