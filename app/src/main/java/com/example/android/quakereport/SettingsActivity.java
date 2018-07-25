package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.prefs.PreferenceChangeEvent;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class EarthquakePreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummaryToValue(minMagnitude);
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);

            SharedPreferences sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext());

            String preferenceValue = sharedPreferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceValue);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String value = o.toString();
            preference.setSummary(value);
            return true;
        }
    }
}
