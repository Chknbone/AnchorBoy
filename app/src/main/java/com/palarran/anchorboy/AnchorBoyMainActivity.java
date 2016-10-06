package com.palarran.anchorboy;

/**
 * App name: AnchorBoy
 * Created by CB on 7/7/2016.
 *
 * App talks to an Arduino board that has a GPS and BlueTooth Shield installed, which is mounted in
 * a water proof buoy. This buoy is attached via line to an anchor.
 *
 * When App starts, it checks that Bluetooth is enabled. If not, it prompts user to enable it.
 *
 * Once Bluetooth is enabled it will show a list of previously paired devices if any are present.
 * After Bluetooth is enabled and paired App will monitor and get continuous GPS position updates
 * from Arduino, set the initial position. From there App will monitor position and if position
 * changes by a predefined or user defined distance, an alarm will sound on Android phone.
 *
 * App will then display amount of feet/yards/meters buoy has moved and the direction boat is
 * drifting and possibly speed in future builds.
 *
 * Future builds will also have ability to use google maps, send warning text messages...
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AnchorBoyMainActivity extends Activity {

    private Location anchorSetLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor_boy_main);
        
        //TODO Working on putting something in the textview if bluetooth is not connected. Normally the GPS data would go there.
        Intent intent = new Intent(this, BluetoothGpsDataActivity.class);
        startActivity(intent);
    }

    public void onClickBluetooth (View view) { //Takes user to the Bluetooth connection/selection page
        Intent intent = new Intent(this, PairBluetoothActivity.class);
        startActivity(intent);
    }

    public void onClickSetPosition (View view) {
        Intent intent = new Intent(this, LocationProvider.class);
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

    /**
     * This method only returns whatever anchor position was returned from the onClickSetPosition method
     *
     * @return
     */
    public Location getAnchorLocation() {

        return anchorSetLocation;
    }
}