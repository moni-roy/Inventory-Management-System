/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

/**
 *
 * @author krishna
 */
public class Manager {
    String MId;
    String MPASS;

    public void setMPASS(String MPASS) {
        this.MPASS = MPASS;
    }

    public void setMId(String MId) {
        this.MId = MId;
    }

    public String getMPASS() {
        return MPASS;
    }

    public Manager(String MId, String MPASS) {
        this.MId = MId;
        this.MPASS = MPASS;
    }

    public Manager() {
    }

    public String getMId() {
        return MId;
    }
}
