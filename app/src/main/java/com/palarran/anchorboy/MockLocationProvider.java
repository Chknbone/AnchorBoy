package com.palarran.anchorboy;

/**
 * Created by jason on 10/6/16.
 */

public class MockLocationProvider implements LocationProvider {

    public Location getLocation() {
        return new Location("47.8732", "-122.459");
    }
}
