/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import java.sql.SQLException;

/**
 *
 * @author krishna
 */
public class SellerInfo {
 
    String SellerId;
    String Spassword;
    

    public SellerInfo(String SellerId, String Spassword) throws SQLException {
        this.SellerId = SellerId;
        this.Spassword = Spassword;
        
    }

    public void setSpassword(String Spassword) {
        this.Spassword = Spassword;
    }

    public void setSellerId(String SellerId) {
        this.SellerId = SellerId;
    }

    public String getSpassword() {
        return Spassword;
    }

    public String getSellerId() {
        return SellerId;
    }

    public SellerInfo() {
    }
}
