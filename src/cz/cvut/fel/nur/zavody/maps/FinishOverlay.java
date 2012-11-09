/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.Zavody;

/**
 *
 * @author saljack
 */
public class FinishOverlay extends OverlayItem {


    private Context _ctx;
    private Drawable _marker;

    public FinishOverlay(Context ctx) {
        super(((Zavody)ctx.getApplicationContext()).getFinishGeoPoint(), "Opponent", "Opponent position");
        _ctx = ctx;
        _marker = _ctx.getResources().getDrawable(R.drawable.finish);
        _marker.setBounds(0 - _marker.getIntrinsicWidth() / 2, 0 - _marker.getIntrinsicHeight(),
                _marker.getIntrinsicWidth() / 2, 0);
    }

    @Override
    public Drawable getMarker(int arg0) {
        return _marker;
    }
}
