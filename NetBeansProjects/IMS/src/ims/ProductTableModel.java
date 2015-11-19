/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;


import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author krishna
 */


public class ProductTableModel extends AbstractTableModel{

    
    
    
	private final int PRODUCT_ID=0;
	private final int  PRODUCT_QNTY =1;
	private final int PRODUCT_PRICE=2;
	
	public static final int OBJECT_COL = -1;
	
	
	private ArrayList<Product> product;
       String[]  columnName={"Product ID","Quantity","Price"};

    public ProductTableModel(ArrayList<Product> product) {
        this.product = product;
    }

    ProductTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
   
   
	
	@Override
	public int getColumnCount() {
		
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		
		return product.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Product employee=product.get(row);
		
		switch (col) {
		
		case  PRODUCT_ID:
			 return employee.getPid();
		case PRODUCT_QNTY:
			 return employee.getPqnity();
		case PRODUCT_PRICE:
			 return employee.getPrice();
		
		case OBJECT_COL:
			return employee;


		default:
			 return employee.getPid();
		}
		
		
		
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
