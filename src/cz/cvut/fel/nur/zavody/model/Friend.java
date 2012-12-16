/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.model;

/**
 * Trida znazornujici pritele ze socialni site
 *
 * @author Saljack
 */
public class Friend implements Comparable<Friend> {

    public enum SocialNetwork {

        FACEBOOK, TWITTER
    }
    private String _name;
    private SocialNetwork _social;

    public Friend(String _name, SocialNetwork _social) {
        this._name = _name;
        this._social = _social;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public SocialNetwork getSocial() {
        return _social;
    }

    public void setSocial(SocialNetwork _social) {
        this._social = _social;
    }

    public int compareTo(Friend another) {
        return _name.compareTo(another._name);
    }
}
