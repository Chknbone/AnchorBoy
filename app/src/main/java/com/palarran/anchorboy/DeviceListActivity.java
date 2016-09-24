package com.palarran.anchorboy;

import android.app.Activity;
import android.os.Bundle;

/**
 * App name: AnchorBoy
 * Created by CB on 9/1/2016.
 *
 * Stupid, but I cannot remember why I created this Activity
 */

//TODO figure out why I created this Activity

public class DeviceListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        setContentView(R.layout.device_name);
    }
}
