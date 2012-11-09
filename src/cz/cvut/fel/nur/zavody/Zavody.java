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
//    private Location _finishLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        setFinish(new GeoPoint(LONGITUDE, LATITUDE));
    }

    public void setFinish(GeoPoint point) {
        _finishGeoPoint = point;
//        _finishLocation = new Location("Finish");
//        _finishLocation.setLatitude(point.getLatitudeE6() / 1E6);
//        _finishLocation.setLongitude(point.getLongitudeE6() / 1E6);
    }

    public GeoPoint getFinishGeoPoint() {
        return _finishGeoPoint;
    }

//    public Location getFinishLocation() {
//        return _finishLocation;
//    }
}
