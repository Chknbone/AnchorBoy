package com.palarran.anchorboy;

import org.junit.Assert;
import org.junit.Test;

/**
 * App name: AnchorBoy
 * Created by CB on 10/6/2016.
 */

public class SetAnchorPositionTest {

    @Test
    public void positionTest() {
        AnchorBoyMainActivity abma  = new AnchorBoyMainActivity();
        abma.onClickSetPosition(null);
        Location anchorLocation = abma.getAnchorLocation();
        Assert.assertNotNull(anchorLocation);
        Assert.assertNotNull(anchorLocation.getLatitude());
        Assert.assertNotNull(anchorLocation.getLongitude());
    }
}
