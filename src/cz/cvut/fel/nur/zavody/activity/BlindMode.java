/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
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

    public static final String TAG = "NUR-Zavody: " + BlindMode.class.getName();
    private Zavody _app;
    private TextView _speed;
    private TextView _time;
    private TextView _remain;
    private TextView _elapsed;
    private LocationManager _locationManager;
    private Timer _timer;
    private LocationListener _locationListener;
    private SensorManager _sensorManager;
    private float[] _GData = new float[3];
    private float[] _MData = new float[3];
    private float[] _R = new float[16];
    private float[] _I = new float[16];
    private float[] _orientation = new float[3];
//    private SensorEventListener _sensorListener;
    private int _count = 0;
    private Location _location;
    private TextView _orientationView;
    private ImageView _arrow;
    private int _bound;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.blind_mode);

        _orientationView = (TextView) findViewById(R.id.bm_orientation);
        _arrow = (ImageView) findViewById(R.id.bm_arrow);
        _arrow.setImageResource(R.drawable.arrow);
        _arrow.setScaleType(ScaleType.CENTER);
        _bound = _arrow.getWidth();
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
                _app = ((Zavody) getApplication());
                float length = _app.addNewPosition(location);
                elapsedTrack(length);
                _location = location;
                float toFinish = _app.getLengthToFinish(location);
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
        Sensor magnetic = _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor acelerometer = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        _sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
        _sensorManager.registerListener(this, acelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        _locationManager.removeUpdates(_locationListener);
        _sensorManager.unregisterListener(this);
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

    /**
     * only load service manager
     */
    private void loadSensors() {
        _sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    public void onSensorChanged(SensorEvent event) {

        int type = event.sensor.getType();
        float[] data;
        if (type == Sensor.TYPE_ACCELEROMETER) {
            data = _GData;
        } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
            data = _MData;
        } else {
            return;
        }
        System.arraycopy(event.values, 0, data, 0, 3);
        SensorManager.getRotationMatrix(_R, _I, _GData, _MData);
        SensorManager.getOrientation(_R, _orientation);
        float incl = SensorManager.getInclination(_I);

        if (_count++ > 50) {
            if (_location != null) {
                float latitude = (float) _location.getLatitude();
                float longitude = (float) _location.getLongitude();
                float altitude = (float) _location.getAltitude();
                GeomagneticField fld = new GeomagneticField(latitude, longitude, altitude, System.currentTimeMillis());

                final float rad2deg = (float) (180.0f / Math.PI);
                float orientation = (360 + _orientation[0] * rad2deg) % 360;
                orientation += fld.getDeclination();
                setImagePosition(orientation);
                _orientationView.setText("" + (int) orientation);
            }
        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void setImagePosition(float orientation) {
        Matrix matrix = new Matrix();
        _arrow.setScaleType(ScaleType.MATRIX);   //required
        matrix.postRotate(orientation, _arrow.getDrawable().getBounds().width() / 2, _arrow.getDrawable().getBounds().height() / 2);
        _arrow.setImageMatrix(matrix);
    }

    public void setBearingToTarget(float bearing) {
        //STUB
    }
}
