package Presentacion.Vistas.Turnos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import Negocio.Turno.AsignacionBO;
import Negocio.Turno.TurnoBO;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;
import Presentacion.Vistas.VistaPrincipal.Settings_General;
import Presentacion.Vistas.VistaPrincipal.TableModel;

public class TurnosGUI extends JPanel {
	

	private static final long serialVersionUID = -4642317986767786986L;

	private String idModule = "Turno";
	private JTable table;
	private int filas;
	
	/**
	 * Constructor
	 *
	 * @param lista --> lista de productos para rellenar la tabla.
	 */
	public TurnosGUI(List<TurnoBO>lista){
		
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
				
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de eliminar este turno?", "Eliminar Turno",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

							if(n == 0)
								Controlador.getInstancia().tratarPeticion(new Contexto("BorrarTurno", table.getValueAt(table.getSelectedRow(), 0)));
						
						}//else
					}//actionPerformed
				},
				
				//ADD
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("CambiarAnadirTurnoVista", null));
					}
					
				},
				
				//UPDATE
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if (table.getSelectedRow() == -1)
							MainGUI.notSelectedRow();
							
						else {
							
							int id_turno = (int) table.getValueAt(table.getSelectedRow(), 0);
							float hora_entrada= (float) table.getValueAt(table.getSelectedRow(), 1);
							float hora_salida = (float) table.getValueAt(table.getSelectedRow(), 2);
							
							TurnoBO turno = new TurnoBO(id_turno, hora_entrada, hora_salida);
							
							Controlador.getInstancia().tratarPeticion(new Contexto("CambiarActualizarTurnoVista", turno));
							
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
							
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de activar este turno?", "Activar Turno",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
							
							if(n == 0)
								Controlador.getInstancia().tratarPeticion(new Contexto("ActivarTurno", table.getValueAt(table.getSelectedRow(), 0)));
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

		String[]nombreColumnas={"ID", "Hora_Entrada", "Hora_Salida", "Empleado", "Activo"};

		
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
			    
			aux.getColumnModel().getColumn(0).setPreferredWidth(100);
			aux.getColumnModel().getColumn(1).setPreferredWidth(100);
			aux.getColumnModel().getColumn(2).setPreferredWidth(100);
			aux.getColumnModel().getColumn(3).setPreferredWidth(200);
			aux.getColumnModel().getColumn(4).setPreferredWidth(30);
			
		return aux;
		
	}//createTableProductos

	
	//*****************************************************************************************************

	
	/**
	 * Agrega los datos a la tabla de Productos.
	 * @param lista
	 */
	public void updateTable(List<TurnoBO>lista){
		
		List<AsignacionBO> list_asignacion = null;
		
		for(int j = 0; j < lista.size(); j++){
			
			this.table.setValueAt(lista.get(j).getId(), j, 0);
			this.table.setValueAt(lista.get(j).getHoraEntrada(),j,1);
			this.table.setValueAt(lista.get(j).getHoraSalida(), j, 2);
			list_asignacion = new ArrayList<AsignacionBO>();
			list_asignacion.addAll((lista.get(j).getAsignaciones()));
			
			if(list_asignacion == null) {
				
				this.table.setValueAt("Este turno aún no tiene empleados asociados", j, 3);
			}
			else {
				
				String empleados = "";
				
				for(int i = 0; i < list_asignacion.size(); i++) {
					
					empleados += list_asignacion.get(i).getEmpleado().getId() + " -";
				}
				
				this.table.setValueAt(empleados, j, 3);
			}
				this.table.setValueAt(lista.get(j).getActivo(), j, 4);
		
		}//for
		
		this.repaint();
		
		this.setVisible(true); 
		
	}//updateTable
	
	
	//*****************************************************************************************************


}
