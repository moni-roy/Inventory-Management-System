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
public class Product {
    int pid;
    int pqnity;
    double price;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPqnity(int pqnity) {
        this.pqnity = pqnity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getPqnity() {
        return pqnity;
    }

    public int getPid() {
        return pid;
    }

    public Product() {
    }

    public Product(int pid, int pqnity, double price) {
        this.pid = pid;
        this.pqnity = pqnity;
        this.price = price;
    }
    
}
