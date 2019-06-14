package Presentacion.Vistas.VistaPrincipal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderCelda extends DefaultTableCellRenderer {

	private String idM;
	private Font fuente;
	
	/**
	 * Constructor
	 * 
	 * @param idM --> id identificativo de cada modulo
	 */
	public RenderCelda(String idM){
		
		this.idM = idM;
		this.fuente = new Font ("Arial", Font.BOLD, 12);
		
	}//RenderCelda
	
	
	private static final long serialVersionUID = -7109269672454343511L;

	@Override
	/**
	 * Permite modificar el contenido y aparencia de la celda que se le pasa como parametro.
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    
		this.setFont(fuente);

		
			if (!this.idM.equals("VentaCompra")){
				 
				 if (row % 2 == 0){
					 
					 this.setBackground(new Color(210,210,210));
		             this.setForeground(Color.BLACK);
		             
				 }//if
				 
				 else if (row % 2 != 0){
					 
					 this.setBackground(Color.WHITE);
		             this.setForeground(Color.BLACK);
		            
				 }//else
				 
				 //---------------------------------------------------------------------------------
					 
				 if (!this.idM.equals("VentaProducto") && !this.idM.equals("VentaCompra") && !this.idM.equals("Venta")){
						 
					 if (column == table.getColumnCount()-1){
							 
						 if ((Boolean)table.getValueAt(row, column)!=null && (Boolean)table.getValueAt(row, column) == true){
								 
							 this.setBackground(new Color(117,255,117));
							 this.setForeground(new Color(117,255,117));
								 
						 }//if
							 
						 else {
								 
							 this.setBackground(new Color(255,117,117));
							 this.setForeground(new Color(255,117,117));
							 
						 }//else
				 
					 }//if
				 }//if
				
					 
				 //------------------------------------------------------------------------------------
		
				 if (isSelected){
						 
					 this.setBackground(table.getSelectionBackground());
					 
					 if(!this.idM.equals("VentaProducto") && !this.idM.equals("VentaCompra") && !this.idM.equals("Venta")){
				          
				         if (column == table.getColumnCount()-1){
					             
				        	 if ((Boolean)table.getValueAt(row, column) == true){
										 
								 this.setBackground(new Color(190,255,190));
								 this.setForeground(new Color(190,255,190));
								 
							 }//if
							 
							 else {
								 
								 this.setBackground(new Color(255,190,190));
								 this.setForeground(new Color(255,190,190));
								 
							 }//else
									 
			             }//if column
					 }//if equals
				 }//if selectd
				 
				 
			}//if !Ventas
			 

			else{
				 
				 this.setBackground(new Color(210,210,210));
				 
				 if (isSelected)
					 
					 this.setBackground(table.getSelectionBackground());
						 
			 }//else


		return this;
		 
		 
	}//getTableCellRendererComponent
	
	
}//RenderCelda



