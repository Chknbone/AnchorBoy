package com.palarran.anchorboy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * App name: AnchorBoy
 * Created by CB on 9/17/2016.
 *
 * This is the first page that loads when the App is started.
 *
 * User is given two choices/buttons;
 *
 * 1: Connect to the AnchorBoy MainPage via Bluetooth.
 * 2: Go to the App's configuration/utility page without connecting via bluetooth.
 */

public class StartPageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
    }

    public void onClickBluetooth (View view) { //Takes user to the Bluetooth connection/selection page
        Intent intent = new Intent(this, PairBluetoothActivity.class);
        startActivity(intent);
    }

    public void onClickConfig(View view) { //Takes user to the Main activity page w/out connecting to bluetooth
        Intent intent = new Intent(this, ConfigUtilityPageActivity.class);
        startActivity(intent);
    }
}
