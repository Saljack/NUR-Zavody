/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 *
 * @author Saljack
 */
public class DrawDirection extends View{
    public static final String TAG = "DrawDirectionRL";

    public DrawDirection(Context context) {
        super(context);
    }

    public DrawDirection(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawDirection(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    
    

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setStrokeWidth(10);
        p.setColor(Color.RED);
        Log.d(TAG, "DRAWUJU");
        canvas.drawText("HELLasdfajklsdfajkfjklasdjkfji qerjpg sidfjig osdfjpas ifO", 10, 10, p);
        canvas.drawText("HELasdfa hfjasjklrh fawuirhfashf uashdf hasioufhuiosadfuhLO", 100, 100, p);
        
        
    }
    
    
    
    
}
