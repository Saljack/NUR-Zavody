/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author saljack
 */
public class RaceTool {

    static public String formatMillis(long l) {
        final long hr = TimeUnit.MILLISECONDS.toHours(l);
        final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        return String.format("%02d:%02d:%02d", hr, min, sec);
    }

    public static float round(float f, int places) {
        if (Float.isNaN(f)
                || Double.isInfinite(f)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(places, RoundingMode.HALF_DOWN);
        return bd.floatValue();
    }

    public static String getLengthWithMetric(float lenght) {

        if (lenght < 1000) {
            return ((int) lenght) + " m";
        }
        return round(lenght / 1000, 1) + " km";
    }
}
