/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import cz.cvut.fel.nur.zavody.Zavody;

/**
 *
 * @author saljack
 */
public class UserOverlay extends MyLocationOverlay {

    private MapView _view;
    private Context _ctx;
    private Mode _mode;

    public UserOverlay(Context ctx, MapView mapView, Mode mode) {
        super(ctx, mapView);
        _view = mapView;
        _ctx = ctx;
        _mode = mode;
    }

    @Override
    public synchronized void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        int lat = (int) (location.getLatitude() * 1E6);
        int lng = (int) (location.getLongitude() * 1E6);
        GeoPoint point = new GeoPoint(lat, lng);

        if (_view != null) {
            _view.getController().animateTo(point);
        }
        Zavody app = ((Zavody) _ctx.getApplicationContext());
        float length = app.addNewPosition(location);
        _mode.elapsedTrack(length);


        float toFinish = app.getLengthToFinish(location);
        if (toFinish < Zavody.MIN_DISTANCE) {
            _mode.touchEnd();
        } else {
            _mode.remainsToFinish(toFinish);
        }
    }

    @Override
    protected void drawMyLocation(Canvas canvas, MapView mapView, Location lastFix, GeoPoint myLocation, long when) {
        super.drawMyLocation(canvas, mapView, lastFix, myLocation, when);

    }
}
