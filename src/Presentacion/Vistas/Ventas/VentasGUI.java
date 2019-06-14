package Presentacion.Vistas.Ventas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Negocio.Producto.ProductoTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;



public class VentasGUI extends JPanel  {

	
	private static final long serialVersionUID = -4459721907761602007L;

	String idModule = "VentaProducto";
	
	private JTable table;
	private JPanel settings;
	private Area_Venta area;
	private DefaultTableModel tableModel;
	private String[]nombreColumnas={"ID","Nombre","Stock", "Precio (€)"};
	private int filas;
	
	/**
	 * Constructor
	 * 
	 * @param filas
	 */
	public VentasGUI(ArrayList<ProductoTrans> lista){
		
		if (lista == null)
			
			this.filas = 0;
		
		else
			this.filas = lista.size();
		
		initGUI();
		
		
	}//ventasGUI
	
	/**
	 * Anade todos los componentes Swing necesarios para poder visualzar la frame de ventas.
	 */
	private void initGUI() {
	
		this.setLayout(new BorderLayout());
		
		
		//***********************************************************************************************************************
		
		
			JPanel informationPanel = new JPanel(new BorderLayout());
			
				informationPanel.setPreferredSize(new Dimension(800,300));
				informationPanel.setBorder(BorderFactory.createMatteBorder(10, 7, 1, 1, MainGUI.getBackgroundColor()));
				
				this.table = createTableVentasProductos(this.filas);
				
				JScrollPane table_barra = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			informationPanel.add(table_barra, BorderLayout.CENTER);
			
			
			
			MouseListener mouseListener = new MouseListener(){
				
				@Override
				public void mouseClicked(MouseEvent evt) {
					
					  if (evt.getClickCount() == 2) {

						  Controlador.getInstancia().tratarPeticion(new Contexto("AnadirProductoCarrito", table.getValueAt(table.getSelectedRow(), 0)));
						  
					  }//if
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {					
				}
				
				
				
			};
			
			table.addMouseListener(mouseListener);
			
		//ALOJADA LA TABLA DE INFORMAION
			
		this.add(informationPanel, BorderLayout.CENTER);
		
		
		//***********************************************************************************************************************
		
		
			JPanel  nortePanel = new JPanel(new BorderLayout());
			
				nortePanel.setPreferredSize(new Dimension(80,300));
				nortePanel.setBackground(MainGUI.getBackgroundColor());
				
				//-----------------------------------------------------------------------------------------
				
				
			
				//ALOJADOS LOS BOTONES DE ACCION DE VENTAS.
				
				this.settings = new Settings_Ventas(
					
					//LISTAR
					new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {

							Controlador.getInstancia().tratarPeticion(new Contexto("cambiarListaVentaVista", null));
							
						}//actionPerformed

					} 
				);
		
				//-----------------------------------------------------------------------------------------
				
				//ALOJADO LA TABLA DE LA VENTA ACTUAL Y SUS CORRESPONDIENTES BOTONES.
				
				this.area = new Area_Venta(
						
						//CLOSE VENTA
						new ActionListener(){
							
							public void actionPerformed(ActionEvent e) {
								
								int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de cancelar la compra en curso?", "Cancelar Compra",
										
										JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
								
								if (n == 0){
									
									Controlador.getInstancia().tratarPeticion(new Contexto("iniciarVista", null));
								}
								
							}//actionPerformed
							
						},
						
						//REMOVE PRODUCTO
						new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
							
								int selected = area.getTable().getSelectedRow();
								
								if (area.getTable().getSelectedRow() == -1)
									MainGUI.notSelectedRow();
								
								else{
									
									int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de eliminar este producto de la venta?", "Eliminar Producto",
											
											JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
									
									if(n == 0)
										Controlador.getInstancia().tratarPeticion(new Contexto("QuitarProductoCarrito", selected));
								}//else
							}
						}
					);

				//-----------------------------------------------------------------------------------------

				
			nortePanel.add(settings, BorderLayout.EAST);
			nortePanel.add(area, BorderLayout.CENTER);
			
			
		//***********************************************************************************************************************
	
			
		this.add(nortePanel, BorderLayout.NORTH);
		
		
	}//initGUI


	private JTable createTableVentasProductos(int f) {
		
		this.createTableModel(f);
		
		JTable aux = new JTable(this.tableModel);
	
			JTableHeader th; 
				th = aux.getTableHeader();
				th.setFont(new Font("Atial", Font.BOLD, 15));
				th.setForeground(MainGUI.getBackgroundColor());
		
		
			TableCellRenderer renderer = new RenderCelda(this.idModule);
			
			aux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		    aux.setDefaultRenderer(Object.class, renderer);
    
			aux.setRowHeight(30);
			    
			aux.getColumnModel().getColumn(0).setPreferredWidth(100);
			
			aux.getColumnModel().getColumn(1).setPreferredWidth(450);
			
			aux.getColumnModel().getColumn(2).setPreferredWidth(10);
			
			aux.getColumnModel().getColumn(3).setPreferredWidth(40);
			
			
		return aux;
	}
	
	
	//***********************************************************************************************************************

	
	public void createTableModel(int f) {

		this.tableModel = new DefaultTableModel(this.nombreColumnas, this.filas){
				
				private static final long serialVersionUID = 1L;
	
				@Override
		            public boolean isCellEditable(int row, int col){
					
		                return false;
		            }
			};

	}//createTableModel

	
	//***********************************************************************************************************************


	public void updateTable(ArrayList<ProductoTrans> lista) {
		
		for(int j = 0;j < lista.size(); j++){
			
			this.table.setValueAt(lista.get(j).getId(), j, 0);
			this.table.setValueAt(lista.get(j).getNombre(),j,1);
			this.table.setValueAt(lista.get(j).getStock(), j, 2);
			this.table.setValueAt(lista.get(j).getPrecio(), j, 3);
			
		
		}//for
		
		this.repaint();
		
		this.setVisible(true); 
		
	}
	
	//***********************************************************************************************************************
	
	
	public void addProducto(ProductoTrans p){
		
		this.area.addProducto(p);
		
	}

	
	//***********************************************************************************************************************

	
	public void removeProducto(int i){
		
		this.area.removeProducto(i);
		
	}
	
	
	//***********************************************************************************************************************

	
	public void addRows (ArrayList<ProductoTrans> lista){

		
		if(this.tableModel.getRowCount() == 0)
			
			for (int i = 0 ; i < lista.size(); i++){
				
				this.tableModel.addRow(new Object[]{lista.get(i).getId(),
						
													lista.get(i).getNombre(),
													
													lista.get(i).getStock(),
				
													lista.get(i).getActivo()}
				);
			}//for
			
		else
		
		for (int i = this.tableModel.getRowCount()-1; i < lista.size()-1; i++){
			
			this.tableModel.addRow(new Object[]{lista.get(i).getId(),
					
												lista.get(i).getNombre(),
												
												lista.get(i).getStock(),
			
												lista.get(i).getActivo()}
			);
		}//for
		
	}//addRows
	
	
	//***********************************************************************************************************************

	
	public void removeRows(int size){
		
		for (int i = this.tableModel.getRowCount()-1; i >= size; i--){
			
			this.tableModel.removeRow(i);
		}
		
		
	}//removeRows
	
	
	//***********************************************************************************************************************

	
	public void updateTotalImport(Double imp){
	
		area.updateTotalImport(imp);
	
	}

	
}//VentasGUI



