package com.palarran.anchorboy;

import android.widget.TextView;

/**
 * Provides current GPS location by connecting to a GPS bluetooth device.
 */

public class BluetoothLocationProvider implements LocationProvider {

    private TextView ABC;

    public void setup() {
        /*
         * throw up a device list
         * have the user pick a device
         * open the device
         * read from the input stream
         */
//        AnchorBoyMainActivity bluetoothGpsDataActivity = new AnchorBoyMainActivity();
//        String anchorIsSet = AnchorBoyMainActivity.gpsPositionString();
    }

    public Location getLocation() {
        //TODO - holy shit, how do we do this???
        throw new UnsupportedOperationException("not implemented yet");
    }
}
