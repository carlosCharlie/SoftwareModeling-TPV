package Presentacion.Vistas.Empleados;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Negocio.Empleado.TemporalBO;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;
import Presentacion.Vistas.VistaPrincipal.Settings_General;
import Presentacion.Vistas.VistaPrincipal.TableModel;

public class TemporalesGUI extends JPanel {
	
	
	private static final long serialVersionUID = 7577743609144090261L;
	
	private String idModule = "EmpleadoTemporal";
	private JTable table;
	private int filas;
	
	/**
	 * Constructor
	 * 
	 * @param lista
	 */
	public TemporalesGUI(List<TemporalBO>lista){
		
		if (lista == null)
			
			this.filas = 0;
		else
			this.filas = lista.size();
	
		
		initGUI();
		
	}//ClientesGUI
	
	
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
	
			
				table = createTableClientes(this.filas);
	
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
				
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de eliminar este empleado?", "Eliminar Empleado",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
							
					
							if(n == 0)
								
								Controlador.getInstancia().tratarPeticion(new Contexto("BorrarEmpleado", table.getValueAt(table.getSelectedRow(), 0)));
						}//else
					}
					
				},
				
				//ADD
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("CambiarAnadirEmpleadoVista", idModule));
						
					}//actionPerformed
				},
				
				//UPDATE
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {

						
						if (table.getSelectedRow() == -1)
							
							MainGUI.notSelectedRow();
						
						else {						
							
							Controlador.getInstancia().tratarPeticion(new Contexto("CambiarActualizarEmpleadoVista", table.getValueAt(table.getSelectedRow(), 0)));
						
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
							
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de activar este empleado?", "Activar Empleado",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
							
					
							if(n == 0)							
								Controlador.getInstancia().tratarPeticion(new Contexto("ActivarEmpleado", table.getValueAt(table.getSelectedRow(), 0)));
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
	public void updateTable(List<TemporalBO>lista){
		
			for(int j = 0;j < lista.size(); j++){
			
				this.table.setValueAt(lista.get(j).getId(), j, 0);
				this.table.setValueAt(lista.get(j).getNss(), j, 1);
				this.table.setValueAt(lista.get(j).getNombre(), j, 2);
				this.table.setValueAt(lista.get(j).getEmail(), j, 3);
				this.table.setValueAt(lista.get(j).getDireccion(), j, 4);
				this.table.setValueAt(lista.get(j).getDepartamento().getId(), j, 5);
				this.table.setValueAt(lista.get(j).getHoras(), j, 6);
				this.table.setValueAt(lista.get(j).getSueldoHoras(), j, 7);
				this.table.setValueAt(lista.get(j).getActivo(), j, 8);
				
				
			
			}//for
		
		this.repaint();
		
		this.setVisible(true); 
		
	}//updateTable

	

	private JTable createTableClientes(int f) {
		
		
		String[]nombreColumnas={"ID","NSS", "Nombre", "e-Mail", "Direccion", "Departamento", "Horas", "Sueldo/Horas", "Activo"};
		
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
		    aux.getColumnModel().getColumn(0).setPreferredWidth(10);
		    aux.getColumnModel().getColumn(1).setPreferredWidth(50);
		    aux.getColumnModel().getColumn(2).setPreferredWidth(110);
		    aux.getColumnModel().getColumn(3).setPreferredWidth(110);
		    aux.getColumnModel().getColumn(4).setPreferredWidth(110);
		    aux.getColumnModel().getColumn(5).setPreferredWidth(50);
		    aux.getColumnModel().getColumn(6).setPreferredWidth(50);
		    aux.getColumnModel().getColumn(7).setPreferredWidth(50);
		    aux.getColumnModel().getColumn(8).setPreferredWidth(20);
		    
		   
		return aux;
	}

}
