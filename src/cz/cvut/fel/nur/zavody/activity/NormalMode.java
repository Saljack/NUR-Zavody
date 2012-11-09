/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.activity;

import android.graphics.Canvas;
import android.location.Location;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.maps.RaceOverlays;
import cz.cvut.fel.nur.zavody.maps.UserOverlay;

/**
 *
 * @author saljack
 */
public class NormalMode extends MapActivity {

    private MapController _controller;
    private UserOverlay _myLocationOverlay;
    private MapView _mapView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.normal_mode);
        _mapView = (MapView) findViewById(R.id.nm_map);
        _mapView.setBuiltInZoomControls(true);
        _controller = _mapView.getController();
        _myLocationOverlay = new UserOverlay(this, _mapView);
        _controller.setZoom(17);
        _mapView.getOverlays().add(_myLocationOverlay);
        RaceOverlays raceOverlays = new RaceOverlays(getResources().getDrawable(R.drawable.ic_launcher), this);

        _mapView.getOverlays().add(raceOverlays);


//        _mapView.invalidate();

    }

    @Override
    protected void onResume() {
        super.onResume();
        _myLocationOverlay.enableMyLocation();
        _myLocationOverlay.enableCompass();
        _mapView.postInvalidate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _myLocationOverlay.disableCompass();
        _myLocationOverlay.disableMyLocation();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
