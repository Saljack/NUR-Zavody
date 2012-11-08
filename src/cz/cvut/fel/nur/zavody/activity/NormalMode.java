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

/**
 *
 * @author saljack
 */
public class NormalMode extends MapActivity {

    private MapController _controller;
    private MyLocationOverlay _myLocationOverlay;
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
        _myLocationOverlay = new MyLocationOverlay(this, _mapView)
        {
            @Override
            public synchronized void onLocationChanged(Location location) {
                super.onLocationChanged(location);
                int lat = (int) (location.getLatitude() * 1E6);
                int lng = (int) (location.getLongitude() * 1E6);
                GeoPoint point = new GeoPoint(lat, lng);
                _controller.animateTo(point);
            }
            
            

            @Override
            protected void drawMyLocation(Canvas arg0, MapView arg1, Location arg2, GeoPoint arg3, long arg4) {
                super.drawMyLocation(arg0, arg1, arg2, arg3, arg4);
                
            }
        };
        
        _controller.setZoom(17);
        _mapView.getOverlays().add(_myLocationOverlay);
        
        _mapView.invalidate();

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
