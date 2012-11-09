/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;
import android.widget.Toast;
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

    public UserOverlay(Context ctx, MapView mapView) {
        super(ctx, mapView);
        _view = mapView;
        _ctx = ctx;
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

        GeoPoint finish = ((Zavody) _ctx.getApplicationContext()).getFinishGeoPoint();
        float[] dist = new float[1];
        Location.distanceBetween(finish.getLatitudeE6() / 1E6, finish.getLongitudeE6() / 1E6, location.getLatitude(), location.getLongitude(), dist);
        if (dist[0] < Zavody.MIN_DISTANCE) {
            Toast.makeText(_ctx, "You win!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void drawMyLocation(Canvas canvas, MapView mapView, Location lastFix, GeoPoint myLocation, long when) {
        super.drawMyLocation(canvas, mapView, lastFix, myLocation, when);

    }
}
