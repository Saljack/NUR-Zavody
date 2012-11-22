/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.activity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.Zavody;
import cz.cvut.fel.nur.zavody.maps.Mode;
import cz.cvut.fel.nur.zavody.utils.RaceTool;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author saljack
 */
public class BlindMode extends Activity implements Mode, SensorEventListener {

    private TextView _speed;
    private TextView _time;
    private TextView _remain;
    private TextView _elapsed;
    private LocationManager _locationManager;
    private Timer _timer;
    private LocationListener _locationListener;
    private SensorManager _sensorManager;
//    private SensorEventListener _sensorListener;
    

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.blind_mode);

        //loading statistic
        View included = findViewById(R.id.included_statistic);
        _speed = (TextView) included.findViewById(R.id.statistic_speed);
        _time = (TextView) included.findViewById(R.id.statistic_time);
        _remain = (TextView) included.findViewById(R.id.statistic_remain);
        _elapsed = (TextView) included.findViewById(R.id.statistic_elapsed);


        _locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        _locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                float speed = location.getSpeed();
                _speed.setText((int) (speed * 3.6) + "km/h");
                Zavody app = ((Zavody) getApplication());
                float length = app.addNewPosition(location);
                elapsedTrack(length);
                
                float toFinish = app.getLengthToFinish(location);
                if (toFinish < Zavody.MIN_DISTANCE) {
                    touchEnd();
                } else {
                    remainsToFinish(toFinish);
                }
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
        loadSensors();
        
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
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _locationManager.removeUpdates(_locationListener);
        _timer.cancel();
    }

    public void touchEnd() {
        Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();
        _timer.cancel();
    }

    public void remainsToFinish(float remains) {
        _remain.setText(RaceTool.getLengthWithMetric(remains));
    }

    public void elapsedTrack(float elapsed) {
        _elapsed.setText(RaceTool.getLengthWithMetric(elapsed));
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

    private void loadSensors() {
        _sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor magnetic = _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor acelerometer = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        _sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
        _sensorManager.registerListener(this, acelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        
        
    }

    public void onSensorChanged(SensorEvent event) {
        
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
