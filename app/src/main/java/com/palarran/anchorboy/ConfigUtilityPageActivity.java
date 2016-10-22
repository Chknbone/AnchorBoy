package com.palarran.anchorboy;

/**
 * App name: AnchorBoy
 * Created by CB on 7/7/2016.
 *
 * Config Activity:
 *
 * Setting for Units (Feet/Meters)
 * Alarm types (Sound, vibrate, Text, email...)
 * Drag Radius setting
 * Button to go to Google Map page
 * Button to connect Bluetooth and go to Main Activity
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigUtilityPageActivity extends Activity {

    private Location anchorSetLocation;
    private TextView noBluetoothNoGpsFix;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_utility_page);

        noBluetoothNoGpsFix = (TextView) findViewById(R.id.config_page_header);
        noBluetoothNoGpsFix.setTextSize(35);
        Toast.makeText(getBaseContext(), "GPS is not connected", Toast.LENGTH_LONG).show();

        //TODO // FIXME: 10/21/2016 Accesses the unit_spinner Spinner. Spinners show up and contain options, but they mean/do nothing yet.
        Spinner spinnerUnit = (Spinner) findViewById(R.id.unit_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of unit choices appears
        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerUnit.setAdapter(adapterUnit);

        Spinner spinnerRadius = (Spinner) findViewById(R.id.drag_radius_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterRadius = ArrayAdapter.createFromResource(this, R.array.radius_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of unit choices appears
        adapterRadius.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerRadius.setAdapter(adapterRadius);
    }



    public void onClickBluetooth (View view) { //Takes user to the Bluetooth connection/selection page
        Intent intent = new Intent(this, PairBluetoothActivity.class);
        startActivity(intent);
    }

    private void openSettings(){
        //TODO make ActionBar setting do something
    }
    private void openSearch(){
        //TODO make ActionBar search do something
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}