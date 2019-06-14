package Presentacion.Vistas.Clientes;

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

import Negocio.Cliente.EmpresaTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;
import Presentacion.Vistas.VistaPrincipal.Settings_General;
import Presentacion.Vistas.VistaPrincipal.TableModel;


public class EmpresaGUI extends JPanel {
	

	private static final long serialVersionUID = 1384731375569897139L;

	private String idModule = "ClienteEmpresa";
	private JTable table;
	private int filas;
	
	/**
	 * Construtor
	 * 
	 * @param filas
	 */
	public EmpresaGUI(int filas){
		
		this.filas = filas;
		
		initGUI();
		
	}//EmpresaGUI
	
	
	/**
	 * Agrega todos los componentes Swing necesarios para visualziar el modulo Clientes.
	 * 
	 * Notifica eventos al controlador segun la accion elegida en las opciones del modulo.
	 */
	private void initGUI() {

		
		this.setLayout(new BorderLayout());
		
			JPanel informationPanel = new JPanel(new BorderLayout());
				informationPanel.setPreferredSize(new Dimension(700,300));
				informationPanel.setBorder(BorderFactory.createMatteBorder(10, 7, 1, 1, MainGUI.getBackgroundColor()));
	
			
				table = createTableEmpresa(this.filas);
	
				JScrollPane table_barra = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
			informationPanel.add(table_barra, BorderLayout.CENTER);
			
		//ALOJADA LA TABLA DE INFORMAION
		this.add(informationPanel, BorderLayout.CENTER);
		
		
		//PANEL DONDE VAN ALOJADOS LOS BOTONES DE ACCION.
		
		this.add( new Settings_General(
				
				this.idModule,
				
				//REMOVE
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {

						
						if (table.getSelectedRow() == -1)
							
							MainGUI.notSelectedRow();
						
						else {
				
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de eliminar esta empresa?", "Eliminar Empresa",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
							
					
							if(n == 0)
								
								Controlador.getInstancia().tratarPeticion(new Contexto("BorrarCliente", table.getValueAt(table.getSelectedRow(), 0)));
						}
					}
				},
				
				//ADD
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarAnadirClienteVista", idModule));
						
					}//actionPerformed
				},
				
				//UPDATE
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {

						
						if (table.getSelectedRow() == -1)
							
							MainGUI.notSelectedRow();
						
						else {						
							
							Controlador.getInstancia().tratarPeticion(new Contexto("cambiarActualizarClienteVista", table.getValueAt(table.getSelectedRow(), 0)));
						
						}//else
					}
				},
				
				//ACTIVATE
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {

						if (table.getSelectedRow() == -1)
							
							MainGUI.notSelectedRow();
						
						else {	
							
							
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de activar esta empresa?", "Eliminar Empresa",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
							
					
							if(n == 0)	
								Controlador.getInstancia().tratarPeticion(new Contexto("ActivarCliente", table.getValueAt(table.getSelectedRow(), 0)));
						
						}
						
					}
					
				},
				
				null, null), 
				
				BorderLayout.NORTH);
		

	}//initGUI
	

	//*****************************************************************************************************
	
	/**
	 * Agrega los datos a la tabla de Marcas.
	 * @param lista
	 */
	public void updateTable(ArrayList<EmpresaTrans>lista){
		
		for(int j = 0; j < lista.size(); j++){
			
			this.table.setValueAt(lista.get(j).getId(), j, 0);
			this.table.setValueAt(lista.get(j).getDni(), j, 1);
			this.table.setValueAt(lista.get(j).getNombre(), j, 2);
			this.table.setValueAt(lista.get(j).getSector(), j, 3);
			this.table.setValueAt(lista.get(j).getTelefono(), j, 4);
			this.table.setValueAt(lista.get(j).getEmail(), j, 5);
			this.table.setValueAt(lista.get(j).getActivo(), j, 6);
		
		}//for
		
		this.repaint();
		
		this.setVisible(true); 
		
	}//updateTable

	

	private JTable createTableEmpresa(int f) {

		String[] nombreColumnas={"ID", "DNI", "Nombre", "Sector", "Telefono", "e-Mail", "Activo"};
		
		
		TableModel tableModel = new TableModel(f, nombreColumnas);
		
		JTable aux = new JTable(tableModel);
		
	
			JTableHeader th; 
			
				th = aux.getTableHeader();
				th.setFont(new Font("Atial", Font.BOLD, 15));
				th.setForeground(MainGUI.getBackgroundColor());
		
			
			TableCellRenderer renderer = new RenderCelda(this.idModule);
			
			aux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		    aux.setDefaultRenderer(Object.class, renderer);
    
		    aux.setRowHeight(30);

			aux.getColumnModel().getColumn(0).setPreferredWidth(60);
			
			aux.getColumnModel().getColumn(1).setPreferredWidth(60);
			
			aux.getColumnModel().getColumn(2).setPreferredWidth(150);
			
			aux.getColumnModel().getColumn(3).setPreferredWidth(160);
			
			aux.getColumnModel().getColumn(4).setPreferredWidth(80);
			
			aux.getColumnModel().getColumn(5).setPreferredWidth(150);
			
			aux.getColumnModel().getColumn(6).setPreferredWidth(50);
			
		
		return aux;
		
		
	}//createTableEmpresa

}//EmpresaGUI
