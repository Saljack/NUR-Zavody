/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.utils;

/**
 * Trida je ekvivaletni tride Point ale vyuziva float pro vetsi presnost
 * @author Saljack
 */
public class FPoint {

    public float X;
    public float Y;

    public FPoint(float X, float Y) {
        this.X = X;
        this.Y = Y;
    }

    /**
     * 
     * @param x souradnice X
     * @param y souradnice Y
     */
    public void set(float x, float y) {
        X = x;
        Y = y;
    }
}
