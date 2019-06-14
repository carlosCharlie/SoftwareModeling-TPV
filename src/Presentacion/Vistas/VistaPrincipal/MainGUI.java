package Presentacion.Vistas.VistaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Negocio.Cliente.ClienteTrans;
import Negocio.Cliente.EmpresaTrans;
import Negocio.Cliente.ParticularTrans;
import Negocio.Departamento.DepartamentoBO;
import Negocio.Empleado.EmpleadoBO;
import Negocio.Empleado.FijoBO;
import Negocio.Empleado.TemporalBO;
import Negocio.Producto.ProductoTrans;
import Negocio.Turno.TurnoBO;
import Negocio.Venta.VentaTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.Clientes.ActualizarEmpresa;
import Presentacion.Vistas.Clientes.ActualizarParticular;
import Presentacion.Vistas.Clientes.AnadirEmpresa;
import Presentacion.Vistas.Clientes.AnadirParticular;
import Presentacion.Vistas.Clientes.ClientesGUI;
import Presentacion.Vistas.Clientes.EmpresaGUI;
import Presentacion.Vistas.Clientes.ParticularGUI;
import Presentacion.Vistas.Departamentos.ActualizarFrame_Departamentos;
import Presentacion.Vistas.Departamentos.AnadirFrame_Departamentos;
import Presentacion.Vistas.Departamentos.DepartamentosGUI;
import Presentacion.Vistas.Empleados.ActualizarFijo;
import Presentacion.Vistas.Empleados.ActualizarTemporal;
import Presentacion.Vistas.Empleados.AnadirFijo;
import Presentacion.Vistas.Empleados.AnadirTemporal;
import Presentacion.Vistas.Empleados.EmpleadosGUI;
import Presentacion.Vistas.Empleados.FijosGUI;
import Presentacion.Vistas.Empleados.TemporalesGUI;
import Presentacion.Vistas.Productos.ActualizarFrame_Productos;
import Presentacion.Vistas.Productos.AnadirFrame_Productos;
import Presentacion.Vistas.Productos.ProductosGUI;
import Presentacion.Vistas.Turnos.ActualizarFrame_Turno;
import Presentacion.Vistas.Turnos.AnadirFrame_Turno;
import Presentacion.Vistas.Turnos.TurnosGUI;
import Presentacion.Vistas.Ventas.ListaFrame_Ventas;
import Presentacion.Vistas.Ventas.VentasGUI;

public class MainGUI extends JFrame{

	private static final long serialVersionUID = 361589142254011987L;
	
	private JPanel mainPanel,westPanel, centerPanel,aPanel,tPanel;
	private ArrayList<?> lista;
	private List<?> lista_jpa;
	private JButton aButton, tButton;
	
	//ELEMENTOS PARA LA QUERYS
	private String[]nombreColumnas;
	private JFrame frame;
	private JTable table;
	/**
	 * Constructor.
	 * 
	 * Instancia el controlador de la aplicacion.
	 */
	public MainGUI(){

		initGUI();
		
	}//MainGUI

	/**
	 * Agrega todas las componentes Swing necesarias para crear el Frame principal donde iran alojadas las vistas de los modulos.
	 */
	private void initGUI() {
		 
			this.addWindowListener(new WindowListener(){
	
				//Se invoca cuando el usuario intenta cerrar la ventana.
				@Override
				public void windowClosing(WindowEvent e) {
	
					int n = JOptionPane.showOptionDialog(new JPanel(), "¿Estas seguro de cerrar la aplicacion?", "Cerrar",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
	
					if (n == 0){
						
						System.gc();
						
						System.exit(0);
					}
				}
	
				//Se invoca cuando la ventana se ajusta para que sea la ventana activa.
				@Override
				public void windowActivated(WindowEvent e) {
				}
	
				//Se invoca cuando una ventana se ha cerrado como el resultado de llamar a disponer en la ventana.
				@Override
				public void windowClosed(WindowEvent e) {
				}
				
				//Se invoca cuando una ventana ya no es la ventana activa.
				@Override
				public void windowDeactivated(WindowEvent e) {
				}
	
				//Se invoca cuando se cambia una ventana de una minimizada a un estado normal.
				@Override
				public void windowDeiconified(WindowEvent e) {
				}
	
				//Se invoca cuando se cambia una ventana desde un estado normal a un estado minimizado.
				@Override
				public void windowIconified(WindowEvent e) {
				}
	
				//Se invoca la primera vez que una ventana se hace visible.
				@Override
				public void windowOpened(WindowEvent e) {
				}			
			});
			
	//**************************************************************************************************************************	
	//**************************************************************************************************************************	
			
		//MAIN PANEL
			
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(true);
			
			
	//**************************************************************************************************************************	

		westPanel = new JPanel();
		westPanel.setBackground(MainGUI.getBackgroundColor());
		westPanel.setBorder(BorderFactory.createCompoundBorder(
			
			BorderFactory.createMatteBorder(0, 0, 0, 2, Color.WHITE), 
			
			BorderFactory.createMatteBorder(5, 5, 5, 5, MainGUI.getBackgroundColor())
		));
		
			westPanel.setLayout(new GridLayout(2,1));
		
			
			//PARTE DE TIENDA
				
			tPanel = new JPanel(new BorderLayout());
				tPanel.setPreferredSize(new Dimension(110, 120));
				tPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),4));
				
				tButton = new JButton(new ImageIcon("src/Presentacion/Icons/tienda.png"));
					tButton.setBackground(new Color(210,210,210));
					tButton.setFont(new Font("Arial", Font.BOLD, 14));
					tButton.setForeground(MainGUI.getBackgroundColor());
					tButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
					
					tButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {

							Controlador.getInstancia().tratarPeticion(new Contexto("cambiarPanelTiendaVista", null));						
						}
					});
			
			tPanel.add(tButton);
			
			
			//PARTE DE ADMINISTRACION
		
			aPanel = new JPanel(new BorderLayout());
				aPanel.setPreferredSize(new Dimension(110, 120));
				aPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),4));
			
				aButton = new JButton(new ImageIcon("src/Presentacion/Icons/administracion.png"));
					aButton.setBackground(new Color(210,210,210));
					aButton.setFont(new Font("Arial", Font.BOLD, 14));
					aButton.setForeground(MainGUI.getBackgroundColor());
					aButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
					aButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {

							Controlador.getInstancia().tratarPeticion(new Contexto("cambiarPanelAdministracionVista", null));						
						}
					});
				
			aPanel.add(aButton);
			
			
		westPanel.add(tPanel);
		westPanel.add(aPanel);
			
	//**************************************************************************************************************************	

		this.centerPanel = new JPanel();
		
		this.mainPanel.add(westPanel, BorderLayout.WEST);
		this.mainPanel.add(this.centerPanel, BorderLayout.CENTER);

		
	this.setContentPane(mainPanel);
	this.setSize(1300, 720);
	this.setLocation(20, 20);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.setVisible(true);
	
	
	}//MainGUI
	

	//**************************************************************************************************************************

	
	@SuppressWarnings("unchecked")
	
	/**
	 * Actualiza la vista del Frame segun el evento recibido como parametro.
	 */
	public void actualizar(Contexto contexto) {
		
		switch(contexto.getEvento()){

			//EVENTOS AUXILIARES.
		
			case "Error":
					
				if(contexto.getDatos().equals("Error al conectar con la base de datos.")){
					
					this.aButton.setEnabled(false);
					this.tButton.setEnabled(false);
					this.centerPanel.setBackground(getBackgroundColor());
					this.mainPanel.setEnabled(false);
					
				}
				errorMessage(contexto.getDatos());
				
				break;
				
				
			//-------------------------------------------------------------------------

				
			case "cambiarPanelTiendaVista":
				
				tPanel.remove(tPanel.getComponentCount()-1);
				tPanel.add(new PanelTienda());	
				
				aPanel.remove(aPanel.getComponentCount()-1);
				aPanel.add(this.aButton);
				
				break;

				
			//-------------------------------------------------------------------------
	
				
			case "cambiarPanelAdministracionVista":
				
				aPanel.remove(aPanel.getComponentCount()-1);
				aPanel.add(new PanelAdministracion());
				
				tPanel.remove(tPanel.getComponentCount()-1);
				tPanel.add(this.tButton);
				
				break;
				
				
			//******************************************************************************************************
			//******************************************************************************************************
					
				//PRODUCTOS
					
				case "cambiarProductoVista":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);
					
					this.lista = (ArrayList<ProductoTrans>)contexto.getDatos();
					
					mainPanel.add(new ProductosGUI((ArrayList<ProductoTrans>) this.lista),BorderLayout.CENTER);
					
					if(this.lista != null)
						((ProductosGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<ProductoTrans>) this.lista);
					
					this.setTitle("TPV - Productos");
					
					
					
					break;
					
				//-------------------------------------------------------------------------

				case "cambiarAnadirProductoVista":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);
					
					mainPanel.add(new AnadirFrame_Productos(),BorderLayout.CENTER);
					
					this.setTitle("TPV - Productos");
					
					
					break;
		
				//-------------------------------------------------------------------------

				case "cambiarActualizarProductoVista":
					
					this.lista = (ArrayList<Object>)contexto.getDatos();
					
					mainPanel.remove(mainPanel.getComponentCount()-1);
					
					mainPanel.add(new ActualizarFrame_Productos(Integer.parseInt(lista.get(0).toString()),//id
							
																(String)this.lista.get(1),//nombre
																
																(String)this.lista.get(2),//idProveedor
							
																this.lista.get(3).toString(),//stock
					
																this.lista.get(4).toString(), 
																
																(Boolean)this.lista.get(5)),
							
																BorderLayout.CENTER);
					
					this.setTitle("TPV - Productos");
					
					
					break;
					
					
					
					
			//******************************************************************************************************
			//******************************************************************************************************
		
				//CLIENTES
					
				case "cambiarClienteVista":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);
					
					this.lista = (ArrayList<ClienteTrans>)contexto.getDatos();
					
					mainPanel.add(new ClientesGUI((ArrayList<ClienteTrans>)this.lista),BorderLayout.CENTER);
					
					
					if(this.lista != null)
						((ClientesGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<ClienteTrans>)this.lista);
					
					this.setTitle("TPV - Clientes");
					
					break;
					
				//-------------------------------------------------------------------------
					
				case "cambiarEmpresaVista":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);
					
					this.lista = (ArrayList<EmpresaTrans>)contexto.getDatos();
					
					mainPanel.add(new EmpresaGUI(this.lista.size()),BorderLayout.CENTER);
					
					if(this.lista != null)
						((EmpresaGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<EmpresaTrans>)this.lista);

					
					this.setTitle("TPV - Clientes");
					
					break;
					
				//-------------------------------------------------------------------------

				case "cambiarParticularVista":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);
					
					this.lista = (ArrayList<ParticularTrans>)contexto.getDatos();

					
					mainPanel.add(new ParticularGUI(this.lista.size()),BorderLayout.CENTER);

					if(this.lista != null)
						((ParticularGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<ParticularTrans>)this.lista);

					
					this.setTitle("TPV - Clientes");
					
					break;
					
				//-------------------------------------------------------------------------
					
				case "cambiarAnadirParticular":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);

					
					mainPanel.add(new AnadirParticular(), BorderLayout.CENTER);

					
					this.setTitle("TPV - Clientes");
					
					break;
					
				//-------------------------------------------------------------------------
					
					
				case "cambiarAnadirEmpresa":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);

					mainPanel.add(new AnadirEmpresa(), BorderLayout.CENTER);
					
					this.setTitle("TPV - Clientes");
					
					break;
		
					
				//-------------------------------------------------------------------------

					
				case "cambiarActualizarEmpresa":
				
					mainPanel.remove(mainPanel.getComponentCount()-1);

					mainPanel.add(new ActualizarEmpresa((EmpresaTrans) contexto.getDatos()), BorderLayout.CENTER);
					
					this.setTitle("TPV - Clientes");
					
					break;
					
				//-------------------------------------------------------------------------

					
				case "cambiarActualizarParticular":
					
					mainPanel.remove(mainPanel.getComponentCount()-1);

					mainPanel.add(new ActualizarParticular((ParticularTrans) contexto.getDatos()), BorderLayout.CENTER);
					
					this.setTitle("TPV - Clientes");
					
					break;
					
				
		//******************************************************************************************************
		//******************************************************************************************************
				
			//VENTAS
				
			case "cambiarVentaVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				this.lista = (ArrayList<ProductoTrans>)contexto.getDatos();
			
				if(this.lista !=null)				
					for(int i = 0; i < lista.size(); i++){
					
						if(((ProductoTrans) lista.get(i)).getActivo() == false){
							
							this.lista.remove(i);
							i--;
						}
					}//for
				
				
				mainPanel.add(new VentasGUI((ArrayList<ProductoTrans>) lista),BorderLayout.CENTER);
				

				if(this.lista != null)
					((VentasGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<ProductoTrans>)this.lista);
				
				this.setTitle("TPV - Ventas");
				
				break;
				
			//-------------------------------------------------------------------------
				
			case "volverVentaVista":
				
				this.lista = (ArrayList<ProductoTrans>)contexto.getDatos();
				
				for(int i = 0; i < lista.size(); i++){
					
					if(((ProductoTrans) lista.get(i)).getActivo() == false){
						
						this.lista.remove(i);
						i--;
					}
					
				}
				
			
				((VentasGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).addRows((ArrayList<ProductoTrans>) this.lista);
				
				((VentasGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<ProductoTrans>) this.lista);

				break;
				
			//-------------------------------------------------------------------------

				
			case "cambiarListaVentaVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				this.lista = (ArrayList<VentaTrans>)contexto.getDatos();
				
				mainPanel.add(new ListaFrame_Ventas(lista.size()),BorderLayout.CENTER);
				
				((ListaFrame_Ventas)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<VentaTrans>) this.lista);
				
				this.setTitle("TPV - Ventas");
				
				break;
				
				//-------------------------------------------------------------------------

				
			case "AnadirProductoCarrito":
				
				ProductoTrans p = (ProductoTrans)contexto.getDatos();
				
				((VentasGUI) mainPanel.getComponent(mainPanel.getComponentCount()-1)).updateTotalImport(p.getPrecio());
				
				((VentasGUI) mainPanel.getComponent(mainPanel.getComponentCount()-1)).addProducto(p);
				
				this.setTitle("TPV - Ventas");
				
				break;
				
				//-------------------------------------------------------------------------
				
				
			case "QuitarProductoCarrito":
				
				((VentasGUI) mainPanel.getComponent(mainPanel.getComponentCount()-1)).removeProducto((int) contexto.getDatos());
				
				this.setTitle("TPV - Ventas");
				
				break;
				
				//-------------------------------------------------------------------------

			case "cambiarVentaVistaConProducto":
				
				this.lista = (ArrayList<ProductoTrans>)contexto.getDatos();
				
				((VentasGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).removeRows(this.lista.size());
				
				((VentasGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((ArrayList<ProductoTrans>) this.lista);
				
				this.setTitle("TPV - Ventas");
				
				break;
				
				
		//******************************************************************************************************
		//******************************************************************************************************
	
			//CONSULTAS
			
			case "crearClienteConXProducto":
				
				ArrayList<String> lista = (ArrayList<String>) contexto.getDatos();
				
				if(lista.size() == 0) {
					
					errorMessage("No se ha encontrado ningun cliente con ese numero de productos.");
				}
				else {
					
					this.frame = new JFrame("Lista de clientes");
				
						this.nombreColumnas = new String[1];
						this.nombreColumnas[0] = "Nombre";
						
						this.table = generarTabla(lista.size(), this.nombreColumnas);
			
						JScrollPane table_barra = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
					for(int i = 0; i < lista.size(); i++) {
						
						this.table.setValueAt(lista.get(i), i, 0);
					}//for
					
	
					frame.setContentPane(table_barra);
					frame.setSize(400, 400);
					frame.setLocation(50, 50);
					frame.setVisible(true);
					
				}//else
				
				break;
				
	
		//******************************************************************************************************

			
			case "crearClientePorFecha":
				
				HashMap<String, ArrayList<Integer>> listaCliente = (HashMap<String, ArrayList<Integer>>) contexto.getDatos();
				
				if(listaCliente.size() == 0) {
					
					errorMessage("No se ha encontrado ningun cliente con esa fecha.");
				}
				else {
				
					this.frame = new JFrame("Lista de clientes.");
					
						this.nombreColumnas = new String[2];
						this.nombreColumnas[0] = "Nombre";
						this.nombreColumnas[1] = "Productos";
						
					
						this.table = generarTabla(listaCliente.size(), this.nombreColumnas);
						
						JScrollPane table_barra = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						
						ArrayList<String> nombres = new ArrayList<String>();
						
						for ( String key : listaCliente.keySet() ) {
						    nombres.add(key);
						}
			
						for(int i = 0; i < listaCliente.size(); i++) {
						
							this.table.setValueAt(nombres.get(i), i, 0);
							this.table.setValueAt(listaCliente.get(nombres.get(i)), i, 1);
						}
						
					frame.setContentPane(table_barra);
					frame.setSize(400, 400);
					frame.setLocation(50, 50);
					frame.setVisible(true);
				}
				
				break;
				
		//******************************************************************************************************
		//**************************************		PARTE JPA 		****************************************
		//******************************************************************************************************
				
				//DEPARTAMENTOS
				
				
			case "cambiarDepartamentoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				this.lista_jpa = (List<DepartamentoBO>)contexto.getDatos();
				
				mainPanel.add(new DepartamentosGUI((List<DepartamentoBO>) this.lista_jpa),BorderLayout.CENTER);
				
				if(this.lista_jpa != null)
					((DepartamentosGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((List<DepartamentoBO>) this.lista_jpa);
				
				this.setTitle("TPV - Departamento");
				
				break;
				
			case "cambiarAnadirDepartamentoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new AnadirFrame_Departamentos(),BorderLayout.CENTER);
				
				this.setTitle("TPV - Departamento");
				
				break;
				
			case "cambiarActualizarDepartamentoVista":
				
				 DepartamentoBO departamento = (DepartamentoBO)contexto.getDatos();
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new ActualizarFrame_Departamentos(departamento.getId(),//id
						
															departamento.getNombre()),//nombre
																				
															BorderLayout.CENTER);
				
				this.setTitle("TPV - Departamento");
	
				break;
				
			case "CalcularSueldo":
				
				this.frame = new JFrame("Sueldo Departamento");
				
					JPanel panel = new JPanel();
					
					JLabel label = new JLabel();
					
					String sentence = "El sueldo del departamento es: " + contexto.getDatos().toString();
					
					label.setText(sentence);
					
					panel.add(label);				
				frame.setContentPane(panel);
				frame.setSize(300, 100);
				frame.setLocation(50, 50);
				frame.setVisible(true);
				
				this.setTitle("TPV - Departamento");
	
				break;
				
		//******************************************************************************************************
		//******************************************************************************************************
						
				//EMPLEADOS
				
			case "CambiarEmpleadoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				this.lista_jpa = (List<EmpleadoBO>)contexto.getDatos();
				
				mainPanel.add(new EmpleadosGUI((List<EmpleadoBO>) this.lista_jpa),BorderLayout.CENTER);
				
				if(this.lista_jpa != null)
					((EmpleadosGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((List<EmpleadoBO>) this.lista_jpa);
				
				this.setTitle("TPV - Empleado");
				
				break;
				
			
			case "CambiarFijoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				this.lista_jpa = (List<FijoBO>)contexto.getDatos();
				
				mainPanel.add(new FijosGUI((List<FijoBO>) this.lista_jpa),BorderLayout.CENTER);
				
				if(this.lista_jpa != null)
					((FijosGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((List<FijoBO>) this.lista_jpa);
				
				this.setTitle("TPV - Empleado Fijo");
				
				break;
				
				
			case "CambiarTemporalVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				this.lista_jpa = (List<TemporalBO>)contexto.getDatos();
				
				mainPanel.add(new TemporalesGUI((List<TemporalBO>) this.lista_jpa),BorderLayout.CENTER);
				
				if(this.lista_jpa != null)
					((TemporalesGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((List<TemporalBO>) this.lista_jpa);
				
				this.setTitle("TPV - Empleado Temporal");
				
				break;
				
				
			case "CambiarAnadirTemporalVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new AnadirTemporal(),BorderLayout.CENTER);
				
				this.setTitle("TPV - Empleado Temporal");
				
				break;
				
				
			case "CambiarAnadirFijoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new AnadirFijo(),BorderLayout.CENTER);
				
				this.setTitle("TPV - Empleado Fijo");
				
				break;
				
				
				
			case "CambiarActualizarFijoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new ActualizarFijo((FijoBO) contexto.getDatos()),BorderLayout.CENTER);
				
				this.setTitle("TPV - Empleado Fijo");
				
				break;
				
			case "CambiarActualizarTemporalVista":
		
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new ActualizarTemporal((TemporalBO) contexto.getDatos()),BorderLayout.CENTER);
				
				this.setTitle("TPV - Empleado Fijo");
				
				break;
		//******************************************************************************************************
		//******************************************************************************************************
						
				//TURNOS
				
			case "CambiarTurnoVista":
	
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				this.lista_jpa = (List<TurnoBO>)contexto.getDatos();
				
				mainPanel.add(new TurnosGUI((List<TurnoBO>) this.lista_jpa),BorderLayout.CENTER);
				
				if(this.lista_jpa != null)
					((TurnosGUI)mainPanel.getComponent((mainPanel.getComponentCount()-1))).updateTable((List<TurnoBO>) this.lista_jpa);
				
				this.setTitle("TPV - Turno");
				
				break;
				
			case "CambiarAnadirTurnoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new AnadirFrame_Turno(),BorderLayout.CENTER);
				
				this.setTitle("TPV - Turno");
				
				break;
				
			case "CambiarActualizarTurnoVista":
				
				mainPanel.remove(mainPanel.getComponentCount()-1);
				
				mainPanel.add(new ActualizarFrame_Turno((TurnoBO) contexto.getDatos()),BorderLayout.CENTER);
				
				this.setTitle("TPV - Turno");
				
				break;
				
		//******************************************************************************************************
		//******************************************************************************************************
				
			default:
			
				@SuppressWarnings("unused") 
				int dialog = JOptionPane.showOptionDialog(new JFrame(), "Se ha notifiado un evento que no existe.", "ERROR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				
				break;
		
		}//switch
		
		
		this.setVisible(true);
		this.repaint();
		
		
	}//update
	

	
	/**
	 * Da color de fondo uniforme a toda la aplicacion
	 * 
	 * @return
	 */
	public static Color getBackgroundColor() {
		
		return new Color(20,58,82);
		
	}//getBackgroundColor
	
	
	/**
	 * Lanza mensaje de aviso si en las tablas de los modulos queremos hacer una operacion
	 * sin haber seleccionado una fila sonre la que realizar la misma.
	 */
	public static void notSelectedRow(){
		
		@SuppressWarnings("unused")
		int dialog = JOptionPane.showOptionDialog(new JFrame(), "No se ha seleccionado ninguna fila.", null,
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

	}//notSelectedRow 
	
	/**
	 * 
	 * Lanza una venta de error avisando del problema que hay
	 *  
	 * @param ob --> cadena de texto con la explicacion del problema.
	 */
	private void errorMessage(Object ob){
		
		@SuppressWarnings("unused")
		int dialog = JOptionPane.showOptionDialog(new JFrame(), ob, "ERROR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		
	}//errorMessage

	
	private JTable generarTabla(int filas, String[]columnas) {
		

		TableModel tableModel = new TableModel(filas, columnas);
		
		JTable aux = new JTable(tableModel);
	
		JTableHeader th; 
		th = aux.getTableHeader();
		th.setFont(new Font("Atial", Font.BOLD, 15));
		th.setForeground(MainGUI.getBackgroundColor());

		TableCellRenderer renderer = new RenderCelda("Venta");
		aux.setDefaultRenderer(Object.class, renderer);
		
			aux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			aux.setRowHeight(30);
			    
			aux.getColumnModel().getColumn(0).setPreferredWidth(100);
	
		return aux;

	}

}//MainGUI


