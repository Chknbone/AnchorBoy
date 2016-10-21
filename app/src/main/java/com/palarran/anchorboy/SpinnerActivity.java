package com.palarran.anchorboy;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * App name:
 * Created by CB on 10/21/2016.
 */

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
