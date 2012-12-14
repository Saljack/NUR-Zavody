/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.graphics.Canvas;
import android.view.MotionEvent;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import cz.cvut.fel.nur.zavody.activity.MapSelectCoordinates;

/**
 * Overlay pro vyber souradnic na tapnuti
 * @author saljack
 */
public class MapTapOverlay extends Overlay {

    public static final String TAG = "MapTapOverlay";
    private MapSelectCoordinates _mapsAc;
    private long systemTime=-1;

    public MapTapOverlay(MapSelectCoordinates _mapsAc) {
        this._mapsAc = _mapsAc;
    }

    @Override
    public void draw(Canvas arg0, MapView arg1, boolean arg2) {

        super.draw(arg0, arg1, arg2);
    }

    @Override
    public boolean onTap(GeoPoint arg0, MapView arg1) {
        //TODO co se souradnicema
        final GeoPoint point = arg0;
        if (_mapsAc != null) {
            _mapsAc.runOnUiThread(new Runnable() {
                public void run() {
                    _mapsAc.acceptPoint(point);
                }
            });

        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MapView mapView) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ((System.currentTimeMillis() - systemTime) < 200) {
                    mapView.getController().zoomIn();
                }
                systemTime = System.currentTimeMillis();
                
                break;
        }

        return false;
    }
}
