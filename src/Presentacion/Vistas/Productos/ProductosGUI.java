package Presentacion.Vistas.Productos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Negocio.Producto.ProductoTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;
import Presentacion.Vistas.VistaPrincipal.Settings_General;
import Presentacion.Vistas.VistaPrincipal.TableModel;



public class ProductosGUI extends JPanel {
	

	private static final long serialVersionUID = -4642317986767786986L;

	private String idModule = "Producto";
	private JTable table;
	private int filas;
	
	/**
	 * Constructor
	 *
	 * @param lista --> lista de productos para rellenar la tabla.
	 */
	public ProductosGUI(ArrayList<ProductoTrans>lista){
		
		if (lista == null)
			
			this.filas = 0;
		
		else
			this.filas = lista.size();
		
		initGUI();
		
	}//ProductosGUI
	
	
	//*****************************************************************************************************
	
	
	/**
	 * Agrega todos los componentes Swing necesarios para visualziar el modulo Productos.
	 * 
	 * Notifica eventos al controlador segun la accion elegida en las opciones del modulo.
	 */
	private void initGUI() {

		
		//THIS --> PANEL CENTRO.
		
		this.setLayout(new BorderLayout());	
		
			
			JPanel informationPanel = new JPanel(new BorderLayout());
				informationPanel.setPreferredSize(new Dimension(700,300));
				informationPanel.setBorder(BorderFactory.createMatteBorder(10, 7, 1, 1, MainGUI.getBackgroundColor()));
			
				
				table = createTableProductos(this.filas);
		
				JScrollPane table_barra = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				
			informationPanel.add(table_barra, BorderLayout.CENTER);
			
			
		//PANEL CENTRO DONDE VA ALOJADO EL TABLERO
		
		this.add(informationPanel, BorderLayout.CENTER);
			
			
			
		//PANEL NORTE DONDE VAN ALOJADOS LOS BOTONES DE ACCION
		
		this.add(new Settings_General(
				
				//ID
				idModule,
				
				//REMOVE
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						if (table.getSelectedRow() == -1)
							MainGUI.notSelectedRow();
						
						else {
				
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de eliminar este producto?", "Eliminar Producto",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

							if(n == 0)
								Controlador.getInstancia().tratarPeticion(new Contexto("BorrarProducto", table.getValueAt(table.getSelectedRow(), 0)));
						
						}//else
					}//actionPerformed
				},
				
				//ADD
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarAnadirProductoVista", null));
					}
					
				},
				
				//UPDATE
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if (table.getSelectedRow() == -1)
							MainGUI.notSelectedRow();
							
						else {
							
							ArrayList<Object>datos = new ArrayList<Object>();
							
							for (int i = 0; i < table.getColumnCount(); i++){
								datos.add(table.getValueAt(table.getSelectedRow(), i));
							}
							
							Controlador.getInstancia().tratarPeticion(new Contexto("cambiarActualizarProductoVista", datos));
							
						}//else
					}//actionPerformed	
					
				},
				
				//ACTIVAR
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if (table.getSelectedRow() == -1)
							MainGUI.notSelectedRow();
						
						else {
							
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de activar este producto?", "Activar Producto",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
							
							if(n == 0)
								Controlador.getInstancia().tratarPeticion(new Contexto("ActivarProducto", table.getValueAt(table.getSelectedRow(), 0)));
						}
					}
				},
				
				null, null),
				
			BorderLayout.NORTH);
		
		
	}//initGUI
	
	
	//*****************************************************************************************************


	/**
	 * Contiene los metodos necesarios para crear la tabla correspondiente a Productos.
	 * 
	 * @param f --> numero de filas que contendra la tabla.
	 * @return tabla del modulo.
	 */
	private JTable createTableProductos(int f) {

		String[]nombreColumnas={"ID","Nombre", "Proveedor", "Stock", "Precio (€)", "Activo"};

		
		TableModel tableModel = new TableModel(f, nombreColumnas);
		
		JTable aux = new JTable(tableModel);
	
			JTableHeader th; 
				th = aux.getTableHeader();
				th.setFont(new Font("Atial", Font.BOLD, 15));
				th.setForeground(MainGUI.getBackgroundColor());
		
			TableCellRenderer renderer = new RenderCelda(idModule);
			aux.setDefaultRenderer(Object.class, renderer);
			aux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			aux.setRowHeight(30);
			    
			aux.getColumnModel().getColumn(0).setPreferredWidth(50);
			aux.getColumnModel().getColumn(1).setPreferredWidth(300);
			aux.getColumnModel().getColumn(2).setPreferredWidth(250);
			aux.getColumnModel().getColumn(3).setPreferredWidth(40);
			aux.getColumnModel().getColumn(4).setPreferredWidth(40);
			aux.getColumnModel().getColumn(5).setPreferredWidth(10);
			
		return aux;
		
	}//createTableProductos

	
	//*****************************************************************************************************

	
	/**
	 * Agrega los datos a la tabla de Productos.
	 * @param lista
	 */
	public void updateTable(ArrayList<ProductoTrans>lista){
		
		for(int j = 0; j < lista.size(); j++){
			
			this.table.setValueAt(lista.get(j).getId(), j, 0);
			this.table.setValueAt(lista.get(j).getNombre(),j,1);
			this.table.setValueAt(lista.get(j).getProveedor(), j, 2);
			this.table.setValueAt(lista.get(j).getStock(), j, 3);
			this.table.setValueAt(lista.get(j).getPrecio(), j, 4);
			this.table.setValueAt(lista.get(j).getActivo(), j, 5);
		
		}//for
		
		this.repaint();
		
		this.setVisible(true); 
		
	}//updateTable
	
	
	//*****************************************************************************************************


	
	
	//*****************************************************************************************************


}//ProductosGUI



