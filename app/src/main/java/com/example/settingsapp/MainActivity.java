package com.example.settingsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if(s.equals("listPref"))
            Log.d("dbAsif","the value of the listPref is "
                    +sharedPreferences.getString("listPref","default"));

        else if(s.equals("editTextPref"))
            Log.d("dbAsif","the value of EditTextPref is "
                    +sharedPreferences.getString("editTextPref","should give the value"));
    }

    public static class SettingsFragemnt extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            addPreferencesFromResource(R.xml.pref_settings);

            Preference preference = findPreference("editTextPref");
            preference.setOnPreferenceChangeListener(this);
        }

        @Override
        public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
            Toast.makeText(getContext(),"sab kuch mat likna only 15 letters allowed",Toast.LENGTH_SHORT)
                    .show();

            if(preference.getKey().equals("editTextPref")){
                String str = (String) newValue;
                if(str.length()<=14)
                    return true;

            }
            return false;
        }
    }
}