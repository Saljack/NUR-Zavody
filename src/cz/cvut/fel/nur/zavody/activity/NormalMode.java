/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.Zavody;
import cz.cvut.fel.nur.zavody.maps.DrawDirection;
import cz.cvut.fel.nur.zavody.maps.Mode;
import cz.cvut.fel.nur.zavody.maps.RaceOverlays;
import cz.cvut.fel.nur.zavody.maps.UserOverlay;
import cz.cvut.fel.nur.zavody.utils.RaceTool;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Aktivita pro normalni mod
 * @author saljack
 */
public class NormalMode extends MapActivity implements Mode {

    private MapController _controller;
    private UserOverlay _myLocationOverlay;
    private MapView _mapView;
    private LocationManager _locationManager;
    private LocationListener _locationListener;
    private TextView _speed;
    private TextView _time;
    private TextView _remain;
    private TextView _elapsed;
    private Timer _timer;
    private DrawDirection _drawDirection;
    public static final String TAG = "NormalMode";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);     
        setContentView(R.layout.normal_mode);
        //loading statistic
        View included = findViewById(R.id.included_statistic);
        _speed = (TextView) included.findViewById(R.id.statistic_speed);
        _time = (TextView) included.findViewById(R.id.statistic_time);
        _remain = (TextView) included.findViewById(R.id.statistic_remain);
        _elapsed = (TextView) included.findViewById(R.id.statistic_elapsed);
        _drawDirection = (DrawDirection) findViewById(R.id.draw_direction);

        _mapView = (MapView) findViewById(R.id.nm_map);
        _mapView.setBuiltInZoomControls(true);
        _controller = _mapView.getController();
        _myLocationOverlay = new UserOverlay(this, _mapView, this);
        _controller.setZoom(17);
        _mapView.getOverlays().add(_myLocationOverlay);
        RaceOverlays raceOverlays = new RaceOverlays(getResources().getDrawable(R.drawable.ic_launcher), this);

        _mapView.getOverlays().add(raceOverlays);
        _locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        _locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                float speed = location.getSpeed();
                _speed.setText((int) (speed * 3.6) + "km/h");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener); //throw error on emulator
        _locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, _locationListener);
        _timer = new Timer();

    }

    private void timeUpdate() {
        final long time = System.currentTimeMillis() - ((Zavody) getApplication()).getStartTime();

        runOnUiThread(new Runnable() {
            public void run() {
                _time.setText(RaceTool.formatMillis(time));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        _myLocationOverlay.enableMyLocation();
        _myLocationOverlay.enableCompass();

        _mapView.postInvalidate();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _myLocationOverlay.disableCompass();
        _myLocationOverlay.disableMyLocation();
        _locationManager.removeUpdates(_locationListener);
        _timer.cancel();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    public void touchEnd() {
        Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();
        _timer.cancel();
    }

    private void startTimer() {
        if (_timer != null) {
            _timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUpdate();
                }
            }, 0, 1000);
        }
    }

    public void remainsToFinish(float remains) {
        _remain.setText(RaceTool.getLengthWithMetric(remains));
    }

    public void elapsedTrack(float elapsed) {
        _elapsed.setText(RaceTool.getLengthWithMetric(elapsed));
    }

    /**
     * musi byt nastaveno jinak nebude zobrazovat sipku kde se nachazi cil
     * @param bearing 
     */
    public void setBearingToTarget(float bearing) {
        _drawDirection.setBearing(bearing);
        _drawDirection.invalidate();
    }
}
