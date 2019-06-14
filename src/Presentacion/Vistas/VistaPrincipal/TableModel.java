package Presentacion.Vistas.VistaPrincipal;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
	

	private static final long serialVersionUID = 5252167965225902960L;

	private String[] columnNames;
	
	private Object[][] rowData;
	
	/**
	 * Constructor
	 * @param nRows --> numeor de filas que tendra la tabla
	 * @param columNames --> diferentes nombre de las columnas de las tablas.
	 * @param idModule --> id identificativo de cada modulo para que la tabla se ajuste a sus caracterisitcas.
	 */
	public TableModel(int nRows, String[] columNames) {
			
		this.columnNames=columNames;
		
		rowData = new Object[nRows][columnNames.length];

	}//TableModel
	
	
	//************************************************************************************************************************

	/**
	 * Devuelve el nombre de las columnas (cabecera)
	 */
	public String getColumnName(int col){
		
		return columnNames[col].toString();
		
	}//getColumnName
	
	
	//************************************************************************************************************************
	
	
	@Override
	/**
	 * Devuelve el numero de colunmas de la tabla
	 */
	public int getColumnCount() {
		
		return columnNames.length;
	
	}
	
	
	//************************************************************************************************************************


	@Override
	/**
	 * Devuelve el numero de fila de la tabla
	 */
	public int getRowCount() {
		
		return rowData.length;
	}
	
	
	//************************************************************************************************************************


	@Override
	/**
	 * Devuelve el contenido de la celda que el llega como parametro.
	 */
	public Object getValueAt(int row, int col) {
		
		
		return rowData[row][col];
	}
	
	
	//************************************************************************************************************************

	
	/**
	 * Si queremos que la celda sa editable devolvemos true.
	 */
	public boolean isCellEditable(int row, int col) {
		
		return false;
	}
	
	
	//************************************************************************************************************************

	/**
	 * Introduce un valor a la celda que le llega como parametro.
	 */
	public void setValueAt(Object value, int row, int col) {
		
		rowData[row][col] = value;
		fireTableCellUpdated(row, col);	
	}
	
	
	//************************************************************************************************************************

	/**
	 * Dibuja la tabla con su contenido.
	 */
	public void printTable() {
		
		for(int i=0; i<rowData.length; i++) {
			
			for(int j=0; j<columnNames.length; j++) 
				
				System.out.print(rowData[i][j]+" ");
			
			System.out.println();
			
		}//for i
		
	}//prinTable
	

	
}//TableModel


