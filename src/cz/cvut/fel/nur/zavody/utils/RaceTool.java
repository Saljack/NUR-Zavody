/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * Trida ktera obsahuje staticke metody vyuzivane v aplikaci n
 * napr. prevod cisla na na nejlepsi moznou jednotku a pridani znacky jednotky napr. km
 * @author saljack
 */
public class RaceTool {

    /**
     * Prevede cas v milisekundach na HH:MM:SS
     * @param l cas v milisekundach
     * @return HH:MM:SS
     */
    static public String formatMillis(long l) {
        final long hr = TimeUnit.MILLISECONDS.toHours(l);
        final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        return String.format("%02d:%02d:%02d", hr, min, sec);
    }

    /**
     * Zaokrouhleni cisla s presnosti na N pozic
     * @param f cislo ktere bude zaokrouhleno
     * @param places na kolik platnych pozic
     * @return zaokrouhlene cislo na places pozic
     */
    public static float round(float f, int places) {
        if (Float.isNaN(f)
                || Double.isInfinite(f)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(places, RoundingMode.HALF_DOWN);
        return bd.floatValue();
    }

    /**
     * Vytvori string ze vzdalenosti a prida jednotku m nebo km
     * @param lenght vzdalenost
     * @return string se vzdalenosti a jednotkou vzdalenosti
     */
    public static String getLengthWithMetric(float lenght) {

        if (lenght < 1000) {
            return ((int) lenght) + " m";
        }
        return round(lenght / 1000, 1) + " km";
    }
}
