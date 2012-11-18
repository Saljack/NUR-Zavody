/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody;

import android.app.Application;
import android.location.Location;
import com.google.android.maps.GeoPoint;

/**
 *
 * @author saljack
 */
public class Zavody extends Application {

    /**
     * Finish position
     */
    public static final int LATITUDE = (int) (14.5205569 * 1E6);
    public static final int LONGITUDE = (int) (50.08352695 * 1E6);
    public static final int MIN_DISTANCE = 15;//meters
    private GeoPoint _finishGeoPoint;
    private long _startTime;
    private Location _last;
    private float _elapsed = 0;
//    private Location _finishLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        setFinish(new GeoPoint(LONGITUDE, LATITUDE));
    }

    public void setFinish(GeoPoint point) {
        _finishGeoPoint = point;
    }

    public GeoPoint getFinishGeoPoint() {
        return _finishGeoPoint;
    }

    public float getLengthToFinish(Location location) {
        float[] dist = new float[1];
        Location.distanceBetween(_finishGeoPoint.getLatitudeE6() / 1E6, _finishGeoPoint.getLongitudeE6() / 1E6, location.getLatitude(), location.getLongitude(), dist);
        return dist[0];
    }

    public void startRace() {
        _startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return _startTime;
    }

    /**
     * Add distance between last position used with this method and parameter
     * loc into sum of length track.
     *
     * @param loc new position
     * @return
     */
    public float addNewPosition(Location loc) {
        if (_last != null) {
            _elapsed += _last.distanceTo(loc);
        }
        _last = loc;
        return _elapsed;
    }

    public void resetAll() {
        _elapsed = 0;
        _last = null;
        _startTime = System.currentTimeMillis();
    }
}
