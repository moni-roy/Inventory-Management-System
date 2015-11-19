/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import java.sql.Date;

/**
 *
 * @author krishna
 */
public class SoldItem {
    int sl;
    int id;
    int qn;
    double price;

    Date dt;

    public int getQn() {
        return qn;
    }

    public int getSl() {
        return sl;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public Date getDt() {
        return dt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public SoldItem() {
    }

    public SoldItem( int sl,int id, int qn, double price, Date dt) {
         this.sl = sl;this.id = id;
         
        this.qn = qn;
        this.price = price;
       
        this.dt = dt;
    }
    
    
}
