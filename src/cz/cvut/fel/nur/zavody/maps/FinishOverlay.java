/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import cz.cvut.fel.nur.zavody.R;

/**
 *
 * @author saljack
 */
public class FinishOverlay extends OverlayItem {

    public static final int LATITUDE = (int) (14.516599 * 1E6);
    public static final int LONGITUDE = (int) (50.084988 * 1E6);
    private Context _ctx;
    private Drawable _marker;

    public FinishOverlay(Context ctx) {
        super(new GeoPoint(LONGITUDE, LATITUDE), "Opponent", "Opponent position");
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
