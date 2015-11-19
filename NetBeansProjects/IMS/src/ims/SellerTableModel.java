/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author krishna
 */
public class SellerTableModel extends AbstractTableModel{
    
        private final int USER_ID=0;
	private final int  USER_PASS =1;
	
	public static final int OBJECT_COL = -1;
        
        private ArrayList<SellerInfo> seller;
       String[]  columnName={"User Name","Password"};

    public SellerTableModel(ArrayList<SellerInfo> seller) {
        this.seller = seller;
    }

//    @Override
//    public int getRowCount() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public int getColumnCount() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public Object getValueAt(int row, int col) {
		SellerInfo employee=seller.get(row);
		
		switch (col) {
		
		case  USER_ID:
			 return employee.getSellerId();
		case USER_PASS:
			 return employee.getSpassword();
		
		case OBJECT_COL:
			return employee;


		default:
			 return employee.getSellerId();
		}
		
		
		
	}

   @Override
	public int getColumnCount() {
		
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		
		return seller.size();
	}
        @Override
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public String getColumnName(int col) {
		
		return columnName[col];
	}
    
}
