package Presentacion.Vistas.Ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Negocio.Venta.VentaTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;
import Presentacion.Vistas.VistaPrincipal.Settings_General;
import Presentacion.Vistas.VistaPrincipal.TableModel;


public class ListaFrame_Ventas extends JPanel {
	

	private static final long serialVersionUID = 7859309329398096006L;
	
	private String idModule = "Venta";
	private JTable table;
	private int filas;
	private JTextField ob;
	
	/**
	 * Constructor
	 * 
	 * @param filas
	 */
	public ListaFrame_Ventas(int filas){

		this.filas = filas;
		
		initGUI();
		
	}//MarcasGUI
	
	
	/**
	 * Anade todos los componentes Swing necesarios para poder visualizar el frame para ver la lista de ventas.
	 */
	private void initGUI() {
		
		//THIS --> PANEL CENTRO.
		
		this.setLayout(new BorderLayout());	
		
	
		//***********************************************************************************************************************

		
			JPanel informationPanel = new JPanel(new BorderLayout());
				informationPanel.setPreferredSize(new Dimension(700,300));
				informationPanel.setBorder(BorderFactory.createMatteBorder(10, 7, 1, 1, MainGUI.getBackgroundColor()));

				
				table = createTableVentas(this.filas);
		
				JScrollPane table_barra = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				
			informationPanel.add(table_barra, BorderLayout.CENTER);
			
			
		//ALOJADA LA TABLA DE INFORMAION
			
		this.add(informationPanel, BorderLayout.CENTER);
		
		
		//***********************************************************************************************************************

		
			//PANEL DONDE VAN ALOJADOS LOS BOTONES DE ACCION.
			
			JPanel setting;
			
			setting = new Settings_General(
					
					//ID
					idModule,
					
					
					//REMOVE
					null,

					//ADD
					null,

					//UPDATE
					null,

					//ACTIVATE
					null,
					
					//DEVOLVER
					new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							ArrayList<Integer> lista = new ArrayList<Integer>();
							
							try {
								
								
								if (table.getSelectedRow() == -1)
									
									MainGUI.notSelectedRow();
								
								else {
									
									int giveInt = mensajesDevolucion("Introduzca el id del producto a eliminar:");
									
									if (giveInt == 0)
										if(ob.getText().isEmpty())
											errMensaje("No ha introducido ningun numero.");
									
										else {
											
											lista.add(Integer.parseInt(ob.getText()));
											lista.add((Integer) table.getValueAt(table.getSelectedRow(), 0));
											giveInt = mensajesDevolucion("Introduzca la cantidad a repartir.");
											
											if (giveInt == 0)
												if(ob.getText().isEmpty())
													errMensaje("No ha introducido ningun numero.");
											
												else {
													
													lista.add(Integer.parseInt(ob.getText()));
													Controlador.getInstancia().tratarPeticion(new Contexto("DevolucionProducto", lista));
												}
										}
									
								}
							}//try
							catch (NumberFormatException e1) {
								
								errMensaje("Formato incorrecto.");
							}
							
							
						}
					}, null);
			
		
		//***********************************************************************************************************************

		
		this.add(setting, BorderLayout.NORTH);
		
	}//initGUI
	
	
	//*****************************************************************************************************
	
	
	public void updateTable(ArrayList<VentaTrans>lista){
		
		for(int j = 0;j < lista.size(); j++){
		
			this.table.setValueAt(lista.get(j).getId(), j, 0);
			this.table.setValueAt(lista.get(j).getFecha(), j, 1);
			this.table.setValueAt(lista.get(j).getIdCliente(), j, 2);
			
			String productos = "";
			
			for (int i = 0; i < lista.get(j).getLineasVenta().size(); i++){
				
				productos += lista.get(j).getLineasVenta().get(i).getProducto();
				productos += " [ " + lista.get(j).getLineasVenta().get(i).getCantidad() + " ]";
				
				
				if(i < lista.get(j).getLineasVenta().size()-1)
					productos+= " - ";
				
			}
			
			this.table.setValueAt(productos, j, 3);
			this.table.setValueAt(lista.get(j).getTotal(), j, 4);

		
		}//for
		
		this.repaint();
		
		this.setVisible(true); 
		
	}//updateTable
	
	
	//*****************************************************************************************************

	
	private JTable createTableVentas(int f){
		
		//PROVISIONAL
		
		String[]nombreColumnas={"ID","Fecha","ID_Cliente", "Productos", "Importe Total"};
		
		TableModel tableModel = new TableModel(f, nombreColumnas);
		
		
		JTable aux = new JTable(tableModel);
		
			JTableHeader th; 
				th = aux.getTableHeader();
				th.setFont(new Font("Atial", Font.BOLD, 15));
				th.setForeground(MainGUI.getBackgroundColor());
	
			
			TableCellRenderer renderer = new RenderCelda(idModule);
		
			aux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		    aux.setDefaultRenderer(Object.class, renderer);
	
		    aux.setRowHeight(30);
	    
		    aux.getColumnModel().getColumn(0).setPreferredWidth(50);
		
		    aux.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		    aux.getColumnModel().getColumn(2).setPreferredWidth(20);
		    
		    aux.getColumnModel().getColumn(3).setPreferredWidth(200);
		    
		    aux.getColumnModel().getColumn(4).setPreferredWidth(20);


		return aux;
			
	}//createTableVentas
	
	
	private int mensajesDevolucion(String texto) {
		
		JPanel giveFecha = new JPanel(new BorderLayout());
			JLabel sentence = new JLabel(texto);
			JPanel givePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
				this.ob = new JTextField();
					this.ob.setPreferredSize(new Dimension(160,30));
					Font fuente = new Font("Arial", Font.BOLD, 13);
					this.ob.setFont(fuente);
					this.ob.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
					
			givePanel.add(ob);
				
		giveFecha.add(sentence, BorderLayout.NORTH);
		giveFecha.add(givePanel, BorderLayout.SOUTH);
		
		return JOptionPane.showConfirmDialog(new JFrame(), giveFecha, "Consulta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	
	private void errMensaje(String texto) {
		
		JOptionPane.showOptionDialog(new JFrame(), texto, "Error",
				
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		
	}


}//ListaFrame_Ventas
