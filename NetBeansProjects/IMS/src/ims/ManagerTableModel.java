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
public class ManagerTableModel extends AbstractTableModel{
    
        private final int USER_ID=0;
	private final int  USER_PASS =1;
	
	public static final int OBJECT_COL = -1;
        
        private ArrayList<Manager> manager;
       String[]  columnName={"User Name","Password"};

    public ManagerTableModel(ArrayList<Manager> manager) {
        this.manager = manager;
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
		Manager employee=manager.get(row);
		
		switch (col) {
		
		case  USER_ID:
			 return employee.getMId();
		case USER_PASS:
			 return employee.getMPASS();
		
		case OBJECT_COL:
			return employee;


		default:
			 return employee.getMId();
		}
		
		
		
	}

   @Override
	public int getColumnCount() {
		
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		
		return manager.size();
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
