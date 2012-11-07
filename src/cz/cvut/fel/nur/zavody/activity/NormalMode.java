/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.activity;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import cz.cvut.fel.nur.zavody.R;

/**
 *
 * @author saljack
 */
public class NormalMode extends MapActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.normal_mode);
        MapView mapView = (MapView) findViewById(R.id.nm_map);
        mapView.setBuiltInZoomControls(true);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return true;
    }
}
