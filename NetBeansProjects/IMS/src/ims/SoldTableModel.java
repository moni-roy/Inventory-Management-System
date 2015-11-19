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
public class SoldTableModel extends AbstractTableModel{
    
        private final int SERIAL=0;
	private final int  P_ID =1;
        private final int  P_QN =2;
        private final int  P_PR =3;
        private final int  S_D =4;
	
	public static final int OBJECT_COL = -1;
        
        private ArrayList<SoldItem> sold;
       String[]  columnName={"Serial No","Product ID","Quantity","Total Price","Date"};

    public SoldTableModel(ArrayList<SoldItem> sold) {
        this.sold = sold;
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
		SoldItem employee=sold.get(row);
		
		switch (col) {
		
		case  SERIAL:
			 return employee.getSl();
		case P_ID:
			 return employee.getId();
		case  P_QN:
			 return employee.getQn();
		case P_PR:
			 return employee.getPrice();
                case  S_D:
			 return employee.getDt();
		
		case OBJECT_COL:
			return employee;


		default:
			 return employee.getSl();
		}
		
		
		
	}

   @Override
	public int getColumnCount() {
		
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		
		return sold.size();
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
