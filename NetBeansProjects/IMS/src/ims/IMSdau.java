/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author krishna
 */
public class IMSdau {

    private static Connection myConn = null;
    private static byte[] byteArray = null;

    public IMSdau() throws SQLException {
        String userName = "root";
        String password = "mk";
        String url = "jdbc:mysql://localhost:3306/IMS";

        myConn = (Connection) DriverManager.getConnection(url, userName, password);

        System.out.println("Connection Successful with:" + url);

    }

    public static boolean CheckSeller(String userId, String password)
            throws SQLException {

        // PreparedStatement preparedStatement=null;
        ResultSet rs = null;
        Statement stat = null;
        SellerInfo seller = null;
        boolean found = false;
        try {

            // preparedStatement=myConn.prepareStatement("SELECT * FROM employees WHERE last_name LIKE ?");
            // preparedStatement.setString(1, lastName);
            // rs=preparedStatement.executeQuery();
            stat = myConn.createStatement();
            rs = stat
                    .executeQuery("SELECT * FROM seller WHERE SID ='"
                            + userId + "'AND PASS='" + password + "'");

            while (rs.next()) {
                found = true;

            }

        } finally {
            // close(rs, stat);
        }

        return found;
    }

    public static boolean checkManager(String userId, String password)
            throws SQLException {

        // PreparedStatement preparedStatement=null;
        ResultSet rs = null;
        Statement stat = null;
        SellerInfo seller = null;
        boolean found = false;
        try {

            // preparedStatement=myConn.prepareStatement("SELECT * FROM employees WHERE last_name LIKE ?");
            // preparedStatement.setString(1, lastName);
            // rs=preparedStatement.executeQuery();
            stat = myConn.createStatement();
            rs = stat
                    .executeQuery("SELECT * FROM manager WHERE MID ='"
                            + userId + "'AND MPASS='" + password + "'");

            while (rs.next()) {
                found = true;

            }

        } finally {
            // close(rs, stat);
        }

        return found;
    }

    //
    public static String getPinfo(int pid)
            throws SQLException {

        ResultSet rs = null;
        Statement stat = null;
        String found = new String("");

        double price = 0.0;
        int qnt = 0, ck = 0;
        try {

            // preparedStatement=myConn.prepareStatement("SELECT * FROM employees WHERE last_name LIKE ?");
            // preparedStatement.setString(1, lastName);
            // rs=preparedStatement.executeQuery();
            stat = myConn.createStatement();
            rs = stat
                    .executeQuery("SELECT * FROM product WHERE PID ='"
                            + pid + "'");

            while (rs.next()) {
//				found=true;
                ck = 1;
                price = rs.getDouble("PPR");
                qnt = rs.getInt("PQT");
//                                price=price
//                                found =price;
                found = "Product ID : " + pid + " Qauntity : " + qnt + "  Price : " + price;
            }
        } finally {
            // close(rs, stat);
        }
        if (ck == 0) {
            found = "Product Not Found !!!";
        }

        return found;

    }

    // Report Make
    public static double getPrice(int pid, int qnty)
            throws SQLException {

        // PreparedStatement preparedStatement=null;
        ResultSet rs = null;
        Statement stat = null;
        double found = 0.0;

        double price = 0.0;
        int qnt = 0;
        try {

            // preparedStatement=myConn.prepareStatement("SELECT * FROM employees WHERE last_name LIKE ?");
            // preparedStatement.setString(1, lastName);
            // rs=preparedStatement.executeQuery();
            stat = myConn.createStatement();
            rs = stat
                    .executeQuery("SELECT * FROM product WHERE PID ='"
                            + pid + "'");

            while (rs.next()) {
//				found=true;
                price = rs.getDouble("PPR");
                qnt = rs.getInt("PQT");
                price = price * qnty;
                found = price;
                if (qnty > qnt) {
                    return -1.0;
                }

                PreparedStatement state = null;

                try {

                    state = myConn
                            .prepareStatement("INSERT INTO `soldProduct`(`PID`, `PQN`, `PPR`, `SDate`)VALUES(?,?,?,?)");

                    state.setInt(1, pid);
                    state.setInt(2, qnty);
                    state.setDouble(3, price);

                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                    state.setDate(4, sqlDate);

                    // InputStream is=new FileInputStream(new File(path));
//                        stat.setBlob(10, is);
                    int i = state.executeUpdate();
//                    
                    if (i != 1) {
                        found = -1.0;
                    }
                } catch (Exception e) {

                }

                state = null;
                try {

                    double totalQnt = (qnt - qnty);

                    state = myConn.prepareStatement("UPDATE `product` SET `PQT`=? WHERE `PID` ='" + pid + "'");
//                        System.out.println(state);

                    state.setDouble(1, totalQnt);

                    int i = state.executeUpdate();
                    if (i != 1) {
                        found = -1.0;
                    }

                } catch (Exception e) {
                    System.out.println("Error in update");
                }

            }
        } finally {
            // close(rs, stat);
        }

        return found;
    }

    public static boolean updateProduct(int pid, int qnt, double price) throws SQLException {

        // PreparedStatement preparedStatement=null;
        ResultSet rs = null;
        Statement stat = null;
        boolean found = false;

        try {

            // preparedStatement=myConn.prepareStatement("SELECT * FROM employees WHERE last_name LIKE ?");
            // preparedStatement.setString(1, lastName);
            // rs=preparedStatement.executeQuery();
            stat = myConn.createStatement();
            rs = stat
                    .executeQuery("SELECT * FROM product WHERE PID ='"
                            + pid + "'");

            while (rs.next()) {
//				found=true;
                int qnty = rs.getInt("PQT");
                qnt = qnt + qnty;

                PreparedStatement state = null;
                try {

//			double totalQnt=(qnt-qnty);
                    state = myConn.prepareStatement("UPDATE `product` SET `PQT`=?,`PPR`=? WHERE `PID` ='" + pid + "'");
//                        System.out.println(state);

                    state.setInt(1, qnt);
                    state.setDouble(2, price);

//			state = myConn.prepareStatement("UPDATE `product` SET `PPR`=? WHERE `PID` ='"+pid+"'" );
////                        System.out.println(state);
//			 state.setDouble(1,price);
                    //and `PPR`=? 
                    int i = state.executeUpdate();
                    if (i == 1) {
                        found = true;
                    }

                } catch (Exception e) {
                    System.out.println("Error in update");
                }

            }
        } finally {
            // close(rs, stat);
        }

        return found;
    }

    ///product info   
    public static java.util.List<Product> getProductList()
            throws SQLException {

        java.util.List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        Statement stat = null;
        try {
            stat = myConn.createStatement();
            rs = stat.executeQuery("SELECT * FROM product");

            while (rs.next()) {
                System.out.println("OK");
                Product employee = convertToProduct(rs);
                list.add(employee);
            }
            return list;

        } finally {
            // close(rs, stat);
        }

    }

    public static Product convertToProduct(ResultSet rs) throws SQLException {

        int PID = rs.getInt("PID");
        int PQT = rs.getInt("PQT");
        double PPR = rs.getDouble("PPR");

        Product w = new Product(PID, PQT, PPR);

        return w;

    }

    public static boolean addProduct(int pid, int qnty, double price)
            throws SQLException {

        // PreparedStatement preparedStatement=null;
        boolean found = false;
        PreparedStatement state = null;

        try {

            state = myConn
                    .prepareStatement("INSERT INTO `product`(`PID`, `PQT`, `PPR`)VALUES(?,?,?)");

            state.setInt(1, pid);
            state.setInt(2, qnty);
            state.setDouble(3, price);

            // InputStream is=new FileInputStream(new File(path));
//                        stat.setBlob(10, is);
            int i = state.executeUpdate();
//                    
            if (i == 1) {
                found = true;
            }
        } catch (Exception e) {

        }

        return found;
    }

    public static void main(String[] args) throws FileNotFoundException,
            IOException, SQLException {
        IMSdau employeeDao = new IMSdau();

    }

    public static boolean deleteProduct(int pid) throws SQLException {

        // PreparedStatement preparedStatement=null;
        boolean found = false;
        PreparedStatement state = null;

        try {

            state = myConn
                    .prepareStatement("Delete from product where PID = '" + pid + "'");

            // InputStream is=new FileInputStream(new File(path));
//                        stat.setBlob(10, is);
            int i = state.executeUpdate();
//                    
            if (i == 1) {
                found = true;
            }
        } catch (Exception e) {

        }

        return found;

    }

    public static boolean addSeller(String id, String pass) throws SQLException {

        // PreparedStatement preparedStatement=null;
        boolean found = false;
        PreparedStatement state = null;

        try {

            state = myConn
                    .prepareStatement("INSERT INTO `seller`(`SID`, `PASS`)VALUES(?,?)");

            state.setString(1, id);
            state.setString(2, pass);

            // InputStream is=new FileInputStream(new File(path));
//                        stat.setBlob(10, is);
            int i = state.executeUpdate();
//                    
            if (i == 1) {
                found = true;
            }
        } catch (Exception e) {

        }

        return found;

    }

    boolean addManager(String id, String pass) throws SQLException {

        // PreparedStatement preparedStatement=null;
        boolean found = false;
        PreparedStatement state = null;

        try {

            state = myConn
                    .prepareStatement("INSERT INTO `manager`(`MID`, `MPASS`)VALUES(?,?)");

            state.setString(1, id);
            state.setString(2, pass);

            // InputStream is=new FileInputStream(new File(path));
//                        stat.setBlob(10, is);
            int i = state.executeUpdate();
//                    
            if (i == 1) {
                found = true;
            }
        } catch (Exception e) {

        }

        return found;
    }

    static public boolean deleteSeller(String id) throws SQLException {

        // PreparedStatement preparedStatement=null;
        boolean found = false;
        PreparedStatement state = null;

        try {

            state = myConn
                    .prepareStatement("Delete from seller where SID = '" + id + "'");

            // InputStream is=new FileInputStream(new File(path));
//                        stat.setBlob(10, is);
            int i = state.executeUpdate();
//                    
            if (i == 1) {
                found = true;
            }
        } catch (Exception e) {

        }

        return found;
    }

    public static boolean deleteManager(String id) throws SQLException {

        // PreparedStatement preparedStatement=null;
        boolean found = false;
        PreparedStatement state = null;

        try {

            state = myConn
                    .prepareStatement("Delete from manager where MID = '" + id + "'");

            // InputStream is=new FileInputStream(new File(path));
//                        stat.setBlob(10, is);
            int i = state.executeUpdate();
//                    
            if (i == 1) {
                found = true;
            }
        } catch (Exception e) {

        }

        return found;
    }
    
    
    
    
    public static java.util.List<SellerInfo> getsellerList()
            throws SQLException {

        java.util.List<SellerInfo> list = new ArrayList<>();
        ResultSet rs = null;
        Statement stat = null;
        try {
            stat = myConn.createStatement();
            rs = stat.executeQuery("SELECT * FROM seller");

            while (rs.next()) {
                System.out.println("OK");
                SellerInfo employee = convertToseller(rs);
                list.add(employee);
            }
            return list;

        } finally {
            // close(rs, stat);
        }

    }

    public static SellerInfo convertToseller(ResultSet rs) throws SQLException {

        String SID = rs.getString("SID");
        String PASS = rs.getString("PASS");

        SellerInfo w = new SellerInfo(SID,PASS);

        return w;

    }
    
    
    public static java.util.List<Manager> getManagerList()
            throws SQLException {

        java.util.List<Manager> list = new ArrayList<>();
        ResultSet rs = null;
        Statement stat = null;
        try {
            stat = myConn.createStatement();
            rs = stat.executeQuery("SELECT * FROM manager");

            while (rs.next()) {
                System.out.println("OK");
                Manager employee = convertTomanager(rs);
                list.add(employee);
            }
            return list;

        } finally {
            // close(rs, stat);
        }

    }

    public static Manager convertTomanager(ResultSet rs) throws SQLException {

        String MID = rs.getString("MID");
        String MPASS = rs.getString("MPASS");

        Manager w = new Manager(MID,MPASS);

        return w;

    }
    
    public static java.util.List<SoldItem> getSoldList()
            throws SQLException {

        java.util.List<SoldItem> list = new ArrayList<>();
        ResultSet rs = null;
        Statement stat = null;
        try {
            stat = myConn.createStatement();
            rs = stat.executeQuery("SELECT * FROM soldProduct");

            while (rs.next()) {
                System.out.println("OK");
                SoldItem employee = convertTosold(rs);
                list.add(employee);
            }
            return list;

        } finally {
            // close(rs, stat);
        }

    }

    public static SoldItem convertTosold(ResultSet rs) throws SQLException {

        int SL = rs.getInt("SL");
        int PID = rs.getInt("PID");
        int PQN = rs.getInt("PQN");
        double PPR = rs.getDouble("PPR");
        Date SDate = rs.getDate("SDate");

        SoldItem w = new SoldItem(SL,PID,PQN,PPR,SDate);

        return w;

    }

}
