package com.palarran.anchorboy;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

/**
 * App name: AnchorBoy
 * Created by CB on 10/21/2016.
 *
 * This is the Units(Feet, Yards, Meters, Fathoms) selection spinner.
 * Default setting is Feet.
 *
 */

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
