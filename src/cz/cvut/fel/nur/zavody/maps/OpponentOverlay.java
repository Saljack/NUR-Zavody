/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import cz.cvut.fel.nur.zavody.R;

/**
 *
 *  //testing coordinates Heldova 50°5'5.671"N, 14°30'52.475"E Oponent
 * 50°5'4.328"N, 14°30'50.703"E Finish 50°5'7.458"N, 14°30'47.161"E
 *
 * @author saljack
 */
public class OpponentOverlay extends OverlayItem {

    public static final int LATITUDE = (int) (14.513599 * 1E6);
    public static final int LONGITUDE = (int) (50.084688 * 1E6);
    private Context _ctx;
    private Drawable _marker;

    public OpponentOverlay(Context ctx) {
        super(new GeoPoint(LONGITUDE, LATITUDE), "Opponent", "Opponent position");
        _ctx = ctx;
        _marker = _ctx.getResources().getDrawable(R.drawable.opponent);
        _marker.setBounds(0 - _marker.getIntrinsicWidth() / 2, 0 - _marker.getIntrinsicHeight(),
                _marker.getIntrinsicWidth() / 2, 0);
    }

    @Override
    public Drawable getMarker(int arg0) {
        return _marker;
    }
}
