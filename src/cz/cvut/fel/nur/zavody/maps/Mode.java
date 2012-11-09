/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.maps;

/**
 *
 * @author saljack
 */
public interface Mode {
    public void touchEnd();
    public void remainsToFinish(float remains);
    public void elapsedTrack(float elapsed);
}
