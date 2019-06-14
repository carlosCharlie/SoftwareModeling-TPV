package Presentacion.Vistas.Departamentos;

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

import Negocio.Departamento.DepartamentoBO;
import Negocio.Empleado.EmpleadoBO;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;
import Presentacion.Vistas.VistaPrincipal.Settings_General;
import Presentacion.Vistas.VistaPrincipal.TableModel;

public class DepartamentosGUI extends JPanel{

	private static final long serialVersionUID = 1L;

	private String idModule = "Departamento";
	private JTable table;
	private int filas;
	
	/**
	 * Constructor
	 *
	 * @param lista --> lista de productos para rellenar la tabla.
	 */
	public DepartamentosGUI(List<DepartamentoBO>lista){
		
	
		if (lista == null)
			
			this.filas = 0;
		
		else
			this.filas = lista.size();
		
		initGUI();
		
		
	}//ProductosGUI
	
	
	//*****************************************************************************************************

	private void initGUI() {
		
		//THIS --> PANEL CENTRO.
		
		this.setLayout(new BorderLayout());	
		
			
			JPanel informationPanel = new JPanel(new BorderLayout());
				informationPanel.setPreferredSize(new Dimension(700,300));
				informationPanel.setBorder(BorderFactory.createMatteBorder(10, 7, 1, 1, MainGUI.getBackgroundColor()));
			
				
				table = createTableDepartamentos(this.filas);
		
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
				
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de eliminar este departamento?", "Eliminar Departamento",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

							if(n == 0)
								Controlador.getInstancia().tratarPeticion(new Contexto("BorrarDepartamento", table.getValueAt(table.getSelectedRow(), 0)));
						
						}//else
					}//actionPerformed
				},
				
				//ADD
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarAnadirDepartamentoVista", null));
					}
					
				},
				
				//UPDATE
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if (table.getSelectedRow() == -1)
							MainGUI.notSelectedRow();
							
						else {
							
							int id_departamento = (int) table.getValueAt(table.getSelectedRow(), 0);
							String nombre_departamento = (String) table.getValueAt(table.getSelectedRow(), 1);
							
							
							DepartamentoBO departamento = new DepartamentoBO(id_departamento, nombre_departamento);
							
							
							Controlador.getInstancia().tratarPeticion(new Contexto("cambiarActualizarDepartamentoVista", departamento)); //--> debe pasarle un BO
							
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
							
							int n = JOptionPane.showOptionDialog(new JFrame(), "¿Estas seguro de activar este departamento?", "Activar Departamento",
									
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
							
							if(n == 0)
								Controlador.getInstancia().tratarPeticion(new Contexto("ActivarDepartamento", table.getValueAt(table.getSelectedRow(), 0)));
						}
					}
				},
				
				null,
				
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if (table.getSelectedRow() == -1)
							MainGUI.notSelectedRow();
						
						else {

								Controlador.getInstancia().tratarPeticion(new Contexto("CalcularSueldo", table.getValueAt(table.getSelectedRow(), 0)));
						}
					}
				}
				
				),
				
			BorderLayout.NORTH);
		
		
	}//initGUI


	private JTable createTableDepartamentos(int filas) {

		String[]nombreColumnas={"ID", "Nombre", "Empleados", "Activo"};

		
		TableModel tableModel = new TableModel(filas, nombreColumnas);
		
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
			
		return aux;
	}
			
	
	
	//*****************************************************************************************************
	
	
	/**
	 * Agrega los datos a la tabla de Productos.
	 * @param lista
	 */
	public void updateTable(List<DepartamentoBO>lista){
		
		List<EmpleadoBO> list_empleado = null;
		
		
		for(int j = 0; j < lista.size(); j++){
			
			this.table.setValueAt(lista.get(j).getId(), j, 0);
			this.table.setValueAt(lista.get(j).getNombre(),j,1);
			list_empleado = lista.get(j).getEmpleado();
			
			if(list_empleado.isEmpty()) {
				
				this.table.setValueAt("Este departamento aun no tiene empleados", j, 2);
			}
			else {
				
				String empleados = "";
				
				for(int i = 0; i < list_empleado.size(); i++) {
					
					if(list_empleado.get(i).getActivo())
						empleados += list_empleado.get(i).getId() + " -";
				}
				
				this.table.setValueAt(empleados, j, 2);
			}
			
			this.table.setValueAt(lista.get(j).getActivo(), j, 3);
		
		}//for
		
		this.repaint();
		
		this.setVisible(true); 
		
	}//updateTable
	
	
	
}//DepartamentosGUI





