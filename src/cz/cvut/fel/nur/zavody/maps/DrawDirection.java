/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import cz.cvut.fel.nur.zavody.utils.FPoint;

/**
 *
 * @author Saljack
 */
public class DrawDirection extends View {

    public static final String TAG = "DrawDirectionRL";
    private float _directions = 0;
    private Paint _p = null;
    private FPoint[] _apexs;
    private float _startX;
    private static final int SIZE_OF_SIDE = 20;
    private static final int SIZE_OF_TRIANGLE = 17;

    public DrawDirection(Context context) {
        super(context);
    }

    public DrawDirection(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawDirection(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBearing(float bearing) {
        _directions = bearing;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "PAINT DIRECTION");
        if (_p == null) {
            _p = new Paint(Paint.ANTI_ALIAS_FLAG);
            _p.setStrokeWidth(2);
            _p.setColor(Color.RED);
            _p.setStyle(Paint.Style.FILL_AND_STROKE);
            _apexs = new FPoint[3];
            for (int i = 0; i < _apexs.length; i++) {
                _apexs[i] = new FPoint(0, 0);
            }
        }
        float b = (_directions + 360 + 45) % 360;

        float Xd = getWidth() / 90f;
        float Yd = getHeight() / 90f;
        float h = getHeight();
        float w = getWidth();


        if ((b >= 0 && b < 90)) {
            //TOP
            _startX = Xd * b;
            _apexs[0].set(_startX, 0);
            _apexs[1].set(_startX - (SIZE_OF_SIDE / 2), SIZE_OF_TRIANGLE);
            _apexs[2].set(_startX + (SIZE_OF_SIDE / 2), SIZE_OF_TRIANGLE);
        } else if (b >= 90 && b < 180) {
            //RIGHT
            _startX = (Yd * (b % 90));
            _apexs[0].set(w, _startX);
            _apexs[1].set(w - SIZE_OF_TRIANGLE, _startX - (SIZE_OF_SIDE / 2));
            _apexs[2].set(w - SIZE_OF_TRIANGLE, _startX + (SIZE_OF_SIDE / 2));
        } else if (b >= 270) {
            //LEFT
            _startX = h - (Yd * (b % 90));//kvuli otoceni

            _apexs[0].set(0, _startX);
            _apexs[1].set(SIZE_OF_TRIANGLE, _startX - (SIZE_OF_SIDE / 2));
            _apexs[2].set(SIZE_OF_TRIANGLE, _startX + (SIZE_OF_SIDE / 2));
        } else {
            //BOTTOM
            _startX = w - (Xd * (b % 90));
            _apexs[0].set(_startX, h);
            _apexs[1].set(_startX - (SIZE_OF_SIDE / 2), (h - SIZE_OF_TRIANGLE));
            _apexs[2].set(_startX + (SIZE_OF_SIDE / 2), (h - SIZE_OF_TRIANGLE));
        }

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(_apexs[0].X, _apexs[0].Y);
        path.lineTo(_apexs[1].X, _apexs[1].Y);
        path.lineTo(_apexs[2].X, _apexs[2].Y);
        path.lineTo(_apexs[0].X, _apexs[0].Y);
        path.close();
        canvas.drawPath(path, _p);

//        canvas.drawLine(_apexs[0].X, _apexs[0].Y, _apexs[1].X, _apexs[1].Y, _p);
//        canvas.drawLine(_apexs[2].X, _apexs[2].Y, _apexs[1].X, _apexs[1].Y, _p);
//        canvas.drawLine(_apexs[0].X, _apexs[0].Y, _apexs[2].X, _apexs[2].Y, _p);




//        canvas.drawText("BEARING: " + _directions, 10, 10, p);
//        canvas.drawText("HELasdfa hfjasjklrh fawuirhfashf uashdf hasioufhuiosadfuhLO", 100, 100, p);


    }
}
