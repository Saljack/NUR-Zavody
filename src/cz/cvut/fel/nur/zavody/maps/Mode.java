/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

/**
 * Rozhrani ktere musi implementovat kazda aktivita, ktera chce byt mod do zavodu
 * (napr. normal mod nebo blind mode)
 * @author saljack
 */
public interface Mode {
    /**
     * Bylo dosazeno cile
     */
    public void touchEnd();
    
    /**
     * Kolik zbyva do cile
     * @param remains vzdalenost do cile v metrech
     */
    public void remainsToFinish(float remains);
    
    /**
     * Kolik jiz bylo ubehnuto
     * @param elapsed ubehnuto v metrech
     */
    public void elapsedTrack(float elapsed);
    
    /**
     * Nastaveni smeru k cili
     * @param bearing smer k cili 0 je sever -90 = 275
     */
    public void setBearingToTarget(float bearing);
}
