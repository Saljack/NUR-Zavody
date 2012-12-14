/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.maps.MapTapOverlay;

/**
 * This activity can finish with result of coordinates of selected point. This
 * point is save in intent (POINT string) as int array where first is Latitude and second is
 * Longitude.
 *
 * @author saljack
 */
public class MapSelectCoordinates extends MapActivity {

    public static String POINT = "cz.cvut.fel.nur.zavody.activity.Point";
    private MapView _mapView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.map_select_coordinates);

        _mapView = (MapView) findViewById(R.id.msc_Map);
        _mapView.setBuiltInZoomControls(true);
        _mapView.getOverlays().add(new MapTapOverlay(this));
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    public void acceptPoint(GeoPoint arg0) {
        
        final GeoPoint point = arg0;
        DialogInterface.OnClickListener negative = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        DialogInterface.OnClickListener positive = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                endActivityWithPoint(point);
            }
        };
        Dialog _dlg = new AlertDialog.Builder(this).setTitle(R.string.msc_wantSelect)
                .setMessage(getString(R.string.msc_point) + "\n" + arg0.toString())
                .setNegativeButton(android.R.string.cancel, negative)
                .setPositiveButton(android.R.string.ok, positive).create();

        _dlg.show();
    }

    private void endActivityWithPoint(GeoPoint point) {
        Intent result = new Intent();
        result.putExtra(POINT, new int[]{point.getLatitudeE6(), point.getLongitudeE6()});
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
