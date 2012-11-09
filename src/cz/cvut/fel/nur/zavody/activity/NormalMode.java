/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
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
    LocationManager locationManager;
    LocationListener locationListener;
    private TextView _speed;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.normal_mode);
        _speed = (TextView) findViewById(R.id.included_statistic).findViewById(R.id.statistic_speed);
        _mapView = (MapView) findViewById(R.id.nm_map);
        _mapView.setBuiltInZoomControls(true);
        _controller = _mapView.getController();
        _myLocationOverlay = new UserOverlay(this, _mapView);
        _controller.setZoom(17);
        _mapView.getOverlays().add(_myLocationOverlay);
        RaceOverlays raceOverlays = new RaceOverlays(getResources().getDrawable(R.drawable.ic_launcher), this);

        _mapView.getOverlays().add(raceOverlays);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                float speed = location.getSpeed();
                _speed.setText(speed+"m/s");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

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
