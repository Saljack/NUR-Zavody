/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saljack
 */
public class RaceOverlays extends ItemizedOverlay<OverlayItem> {

    private Context _ctx;
    private FinishOverlay _finish;
    private List<OverlayItem> _items = new ArrayList<OverlayItem>(2);
    private long systemTime;

    public RaceOverlays(Drawable marker, Context ctx) {
        super(boundCenterBottom(marker));
        _ctx = ctx;
        _finish = new FinishOverlay(ctx);
        _items.add(_finish);
        _items.add(new OpponentOverlay(ctx));
        populate();
    }

    public void add(OverlayItem item) {
        _items.add(item);
        populate();
    }

    @Override
    protected OverlayItem createItem(int i) {
//        OverlayItem item = _items.get(i);
//        Drawable marker = item.getMarker(0);
//        boundCenterBottom(marker);
//        item.setMarker(marker);
        return _items.get(i);
    }

    @Override
    public int size() {
        return _items.size();
    }

    public FinishOverlay getFinishOverlay() {
        return _finish;
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
