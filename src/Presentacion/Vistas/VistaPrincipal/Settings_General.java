package Presentacion.Vistas.VistaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;


public class Settings_General extends JPanel{
	

	private static final long serialVersionUID = 1369398654562060538L;

	private ImageIcon icon;
	private JTextField searchArea;
	private JPanel chooseCliente;
	private JPanel chooseEmpleado;
	private JPanel sPanel;
	private JButton searchButton;
	private JComboBox<?> consultaBox;
	private JTextField give;
	private ActionListener removeAction;
	private ActionListener addAction;
	private ActionListener updateAction;
	private ActionListener activateAction;
	private ActionListener devolverAction;
	private ActionListener calcularAction;
	
	String idModule;

	JButton closeSearchButton;
	
	/**
	 * Constructor
	 * 
	 * @param idModule --> id identificativo de cada modulo
	 * 
	 * Diferentes acciones para que la aplicacion tenga funcionabilidad.
	 * 
	 * @param removeAction
	 * @param addAction
	 * @param updateAction
	 * @param activateAction
	 */
	public Settings_General(String idModule,
			
							ActionListener removeAction, 
							
							ActionListener addAction, 
							
							ActionListener updateAction,
							
							ActionListener activateAction,
							
							ActionListener devolverAction,
							
							ActionListener calcularAction){
		
		this.removeAction = removeAction;
		this.addAction = addAction;
		this.updateAction = updateAction;
		this.activateAction = activateAction;
		this.devolverAction = devolverAction;
		this.calcularAction = calcularAction;
		
		
		this.idModule = idModule;
		
		initSettings();
		
		
	}//Settings_General Builder
	
	/**
	 * Agrega todos los componentes Swing necesarios para visualizar la vista con botones de accion.
	 */

	private void initSettings() {
		

		this.setLayout(new BorderLayout(20,20));
		this.setPreferredSize(new Dimension(100,100));
		this.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),10));
		this.setBackground(MainGUI.getBackgroundColor());

		
		//**************************************************************************************************************************
			
			//BUTTONS PANEL --> iran alojados los botones de accion del modulo
		
			JPanel buttonsPanel = new JPanel(new BorderLayout());
				buttonsPanel.setPreferredSize(new Dimension(550,20));
				buttonsPanel.setBackground(MainGUI.getBackgroundColor());

				
				JPanel bPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					bPanel.setBackground(MainGUI.getBackgroundColor());
					
					
				//COMENZAMOS CON LA CREACION DE BOTONES	
				//*****************************************************************************************
	
					//ADD BUTTON
					JButton addButton = new JButton();
					
						//preferences
						addButton.setPreferredSize(new Dimension(120, 40));
						addButton.setBackground(new Color(210,210,210));
						addButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
						
						icon = new ImageIcon("src/Presentacion/Icons/add.png");
						addButton.setIcon(icon);
						
						addButton.addActionListener(addAction);
			
				
			//*****************************************************************************************
				
					//REMOVE BUTTON
					JButton removeButton = new JButton();
						
						//preferences
						removeButton.setPreferredSize(new Dimension(120, 40));
						removeButton.setBackground(new Color(210,210,210));
						removeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
						
						icon = new ImageIcon("src/Presentacion/Icons/b.png");
						removeButton.setIcon(icon);
						
						removeButton.addActionListener(removeAction);
				
			
			//*****************************************************************************************
					
					//VALIDATE BUTTON
					JButton validateButton = new JButton();
						
						//preferences
						validateButton.setPreferredSize(new Dimension(120, 40));
						validateButton.setBackground(new Color(210,210,210));
						validateButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
						
						icon = new ImageIcon("src/Presentacion/Icons/ok.png");
						validateButton.setIcon(icon);
						
						validateButton.addActionListener(activateAction);
				
				
			//*****************************************************************************************
				
					//UPDATE BUTTON
					JButton updateButton = new JButton();
					
						//preferences
						updateButton.setPreferredSize(new Dimension(120, 40));
						updateButton.setBackground(new Color(210,210,210));
						updateButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
						
						icon = new ImageIcon("src/Presentacion/Icons/update.png");
						updateButton.setIcon(icon);
						
						updateButton.addActionListener(updateAction);
						
						
			//*****************************************************************************************
						
					//DEVOLUCION BUTTON
					JButton DevolucionButton = new JButton();
					
						//preferences
					DevolucionButton.setPreferredSize(new Dimension(120, 40));
					DevolucionButton.setBackground(new Color(210,210,210));
					DevolucionButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
						
						icon = new ImageIcon("src/Presentacion/Icons/devolver.png");
						DevolucionButton.setIcon(icon);
						
						DevolucionButton.addActionListener(devolverAction);
						
						
			//*****************************************************************************************
					
					//BOTON PARA LA GENERACION DE LA CONSULTA DE LA ENTIDAD CLIENTES
					//CONSULTA BUTTON
					JButton consultaButton = new JButton();
					
						//preferences
						consultaButton.setPreferredSize(new Dimension(40, 40));
						consultaButton.setBackground(new Color(210,210,210));
						consultaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
						
						icon = new ImageIcon("src/Presentacion/Icons/consulta.png");
						consultaButton.setIcon(icon);
						
						consultaButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								// 0 = primera consulta.
								if(consultaBox.getSelectedIndex() == 0) {

									int giveInt = mensajeQuery("Introduzca la fecha:");
									
									if (giveInt == 0){
										
										if(give.getText().isEmpty())
											errMensaje("No ha introducido ninguna fecha.");
		
										else
											Controlador.getInstancia().tratarPeticion(new Contexto("ProductoPorFecha", give.getText()));
										
									}//else
								}//if
								
								else {
									
									int giveInt = mensajeQuery("Introduzca el numero de productos:");
									
									if (giveInt == 0){
										
										if(give.getText().isEmpty())
											errMensaje("No ha introducido ningún numero.");
		
										else {
											
											try {
												int n = Integer.parseInt(give.getText());
												Controlador.getInstancia().tratarPeticion(new Contexto("ClienteConXProducto", n));
											}
											catch(NumberFormatException e1) {
												errMensaje("Introduzca un valor correcto.");
											}
										}//else	
									}//if
								}//else
							}//actionPerformed
						});
						
			
			//FINALIZA LA CREACION DE BOTONES
			//*****************************************************************************************

						
			//COMO BOX PARA LA ELECCION DE LA CONSULTA EN LA ENTIDAD DE CLIENTES.
			String[] consultaString = { "Buscar productos por fecha", "Clientes que han comprado el primer dia"};
			this.consultaBox = new JComboBox<Object>(consultaString);
			this.consultaBox.setPreferredSize(new Dimension(235,40));
			this.consultaBox.setBorder(BorderFactory.createMatteBorder(0, 60, 0, 0, MainGUI.getBackgroundColor()));
	        //consultaBox.setSelectedIndex(2);
			

				
				

					

		Font f = new Font("Arial", Font.BOLD, 13);
			
		JButton anadirAsignacionButton = new JButton(" Añadir Asignacion");
			anadirAsignacionButton.setPreferredSize(new Dimension(130,40));
			anadirAsignacionButton.setBackground(new Color(210,210,210));
			anadirAsignacionButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
					
			anadirAsignacionButton.setForeground(MainGUI.getBackgroundColor());
			anadirAsignacionButton.setFont(f);
			
			anadirAsignacionButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {

					Font fuente = new Font("Arial", Font.BOLD, 13);
					
					JPanel giveID = new JPanel(new BorderLayout());
					
					JPanel givePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					
					giveID.add(givePanel, BorderLayout.SOUTH);
					
						
					
					
					
					//CUADRO EMERGENTE QUE PIDE EL ID DEL EMPLEADO.
					
					JLabel sentence = new JLabel("Introduzca la ID del empleado:");
					giveID.add(sentence, BorderLayout.NORTH);
					
					JTextField giveEmpleado = new JTextField();
					giveEmpleado.setPreferredSize(new Dimension(80,30));
					fuente = new Font("Arial", Font.BOLD, 13);
					giveEmpleado.setFont(fuente);
					giveEmpleado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				
					givePanel.add(giveEmpleado);

					JOptionPane.showConfirmDialog(new JFrame(), 
																giveID, 
																"ID Empleado", 
																JOptionPane.DEFAULT_OPTION, 
																JOptionPane.QUESTION_MESSAGE);
					
				
					//CUADRO EMERGENTE QUE PIDE EL ID DEL TURNO

					sentence.setText("Introduzca la ID del turno:"); 
					giveID.add(sentence, BorderLayout.NORTH);

					JTextField giveTurno = new JTextField();
					giveTurno.setPreferredSize(new Dimension(80,30));
					fuente = new Font("Arial", Font.BOLD, 13);
					giveTurno.setFont(fuente);
					giveTurno.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				
					givePanel.add(giveTurno);
					
					JOptionPane.showConfirmDialog(new JFrame(), 
							giveID, 
							"ID Turno", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.QUESTION_MESSAGE);
					
					//****************************************************************************************

					
					//CUADRO EMERGENTE QUE PIDE LAS HORAS

					sentence.setText("Introduzca el numero de horas:"); 
					giveID.add(sentence, BorderLayout.NORTH);

					JTextField giveHoras = new JTextField();
					giveHoras.setPreferredSize(new Dimension(80,30));
					fuente = new Font("Arial", Font.BOLD, 13);
					giveHoras.setFont(fuente);
					giveHoras.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				
					givePanel.add(giveHoras);
					
					
					JOptionPane.showConfirmDialog(new JFrame(), 
							giveID, 
							"Horas", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.QUESTION_MESSAGE);
					
					
					//****************************************************************************************
				
					//a este hashmap debes añdirle el elemento giveHoras para pasarlo por el contexto.
					
					HashMap<String, Object> anadirAsignacion = new HashMap<String, Object>();
					
					
					
					try {
						
						int turno = Integer.parseInt(giveTurno.getText());
						int empleado = Integer.parseInt(giveEmpleado.getText());
						float horas = Float.parseFloat(giveHoras.getText());
						
						anadirAsignacion.put("turno", turno);
						anadirAsignacion.put("empleado", empleado);
						anadirAsignacion.put("horas", horas);
						
						Controlador.getInstancia().tratarPeticion(new Contexto("AnadirAsignacion", anadirAsignacion));
					}
					catch(NumberFormatException e1) {
						
						errMensaje("Formato erroneo");
					}
				}
				
			});

		JButton quitarAsignacionButton = new JButton("Quitar Asignacion");
			quitarAsignacionButton.setPreferredSize(new Dimension(130,40));
			quitarAsignacionButton.setBackground(new Color(210,210,210));
			quitarAsignacionButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
			quitarAsignacionButton.setForeground(MainGUI.getBackgroundColor());
			quitarAsignacionButton.setFont(f);
			
			quitarAsignacionButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					Font fuente = new Font("Arial", Font.BOLD, 13);
					
					JPanel giveID = new JPanel(new BorderLayout());
					
					JPanel givePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					
						JTextField giveEmpleado = new JTextField();
						giveEmpleado.setPreferredSize(new Dimension(80,30));
							fuente = new Font("Arial", Font.BOLD, 13);
							giveEmpleado.setFont(fuente);
							giveEmpleado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
						
					givePanel.add(giveEmpleado);
					
					giveID.add(givePanel, BorderLayout.SOUTH);
					
					//CUADRO EMERGENTE QUE PIDE EL ID DEL EMPLEADO.
					
					JLabel sentence = new JLabel("Introduzca la ID del empleado:");
					giveID.add(sentence, BorderLayout.NORTH);

					JOptionPane.showConfirmDialog(new JFrame(), 
																giveID, 
																"ID Empleado", 
																JOptionPane.DEFAULT_OPTION, 
																JOptionPane.QUESTION_MESSAGE);
					

					//****************************************************************************************

					//CUADRO EMERGENTE QUE PIDE EL ID DEL TURNO

					sentence.setText("Introduzca la ID del turno:"); 
					giveID.add(sentence, BorderLayout.NORTH);

					JTextField giveTurno = new JTextField();
					giveTurno.setPreferredSize(new Dimension(80,30));
					fuente = new Font("Arial", Font.BOLD, 13);
					giveTurno.setFont(fuente);
					giveTurno.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				
					givePanel.add(giveTurno);
					
					JOptionPane.showConfirmDialog(new JFrame(), 
							giveID, 
							"ID Turno", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.QUESTION_MESSAGE);
					
					//****************************************************************************************
					
					HashMap<String, Object> quitarAsignacion = new HashMap<String, Object>();
					
					try {
						
						int turno = Integer.parseInt(giveTurno.getText());
						int empleado = Integer.parseInt(giveEmpleado.getText());
						
						quitarAsignacion.put("turno", turno);
						quitarAsignacion.put("empleado", empleado);
					
						Controlador.getInstancia().tratarPeticion(new Contexto("QuitarAsignacion", quitarAsignacion));
					}
					catch(NumberFormatException e1) {
						
						errMensaje("Formato erroneo");
					}
				}
			});

			
			
			
			//SEARCH PANEL --> alojada la barra buscadora.
	
			JPanel searchPanel = new JPanel(new BorderLayout());
				searchPanel.setBackground(MainGUI.getBackgroundColor());
				searchPanel.setPreferredSize(new Dimension(550, 20));
				

				this.sPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
					this.sPanel.setBorder(BorderFactory.createMatteBorder(40, 0, 7, 0, MainGUI.getBackgroundColor()));
			
					
					//BARRA BUSCADORA
				
					this.searchArea = new JTextField();
					
						Font fuente = new Font("Arial", Font.BOLD, 13);
						
						//preferences
						this.searchArea.setFont(fuente);
						this.searchArea.setPreferredSize(new Dimension(200, 40));
						this.searchArea.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, MainGUI.getBackgroundColor()));
						
						this.searchArea.setEditable(true);
													
						
						
					//SEARCH BUTTON
						
					this.searchButton = new JButton();
			
						this.searchButton.setPreferredSize(new Dimension(100, 40));
						this.searchButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
						this.searchButton.setBackground(new Color(210,210,210));
						
						icon = new ImageIcon("src/Presentacion/Icons/lookf.png");
						this.searchButton.setIcon(icon);
						
						
						this.searchButton.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								
								if(idModule.equals("Departamento") || idModule.equals("Turno")) {
									
									int id_search = 0;
									
									try {
										
										id_search = Integer.parseInt(searchArea.getText());
									}
									catch(NumberFormatException except){
										
										errMensaje("Debe introducir un ID para la busqueda");							
									}
									
									Controlador.getInstancia().tratarPeticion(new Contexto("Buscar" + idModule, id_search));
									
								}//if
								
								else if(idModule.equals("Empleado") || idModule.equals("EmpleadoFijo") || idModule.equals("EmpleadoTemporal")) {
									
									
									int nss_search = -1;
									
									try {
										
										nss_search = Integer.parseInt(searchArea.getText());
									}
									catch(NumberFormatException except){
										
										errMensaje("Debe introducir un NSS para la busqueda");							
									}
									
									Controlador.getInstancia().tratarPeticion(new Contexto("BuscarEmpleado", nss_search));
								}
	
								else {
									Controlador.getInstancia().tratarPeticion(new Contexto("Buscar" + idModule, searchArea.getText()));
								}//else
							}
	
						});
					
					
					this.sPanel.add(searchArea);
					this.sPanel.add(searchButton);	
				
			if(!this.idModule.equals("VentaProducto") && !this.idModule.equals("Venta")) {
				
				bPanel.add(validateButton);
				bPanel.add(removeButton);
			}
			
		
			
			if(this.idModule.equals("Cliente")) {
				
				bPanel.add(consultaBox);
				bPanel.add(consultaButton);
			}
			
			//SI EL MODULO ES DIFERENTE DE VENTAS --> SE PONE EL BOTON MOFICAR.
			if(!this.idModule.equals("Venta") && !this.idModule.equals("Cliente") && !this.idModule.equals("Empleado")
					 && !this.idModule.equals("Turno")){
				
				bPanel.add(updateButton);
				bPanel.add(addButton);
				
			}
			
			if(this.idModule.equals("Departamento")) {
				
				buttonsPanel.setPreferredSize(new Dimension(800,20));
				
				JButton sueldoButton = new JButton("Calcular Sueldo");
				
				//preferences
				sueldoButton.setPreferredSize(new Dimension(120, 40));
				sueldoButton.setBackground(new Color(210,210,210));
				sueldoButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
				
				sueldoButton.addActionListener(calcularAction);
				
				bPanel.add(sueldoButton);
			}
			
			if(this.idModule.equals("Turno")) {
				
				buttonsPanel.setPreferredSize(new Dimension(800,20));
				bPanel.add(updateButton);
				bPanel.add(addButton);
				bPanel.add(anadirAsignacionButton);
				bPanel.add(quitarAsignacionButton);
			}
		
			else if(this.idModule.equals("Cliente")){
				
					Font f1 = new Font("Arial", Font.BOLD, 13);
				
					chooseCliente = new JPanel(new BorderLayout());
						//chooseCliente.setPreferredSize(new Dimension(400,0));
						chooseCliente.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 80, MainGUI.getBackgroundColor()));
						chooseCliente.setBackground(MainGUI.getBackgroundColor());
						
	
						
						JPanel r = new JPanel(new GridLayout(2,1));						
							r.setPreferredSize(new Dimension(150,0));
							
							JButton particularButton = new JButton("Particular");
								particularButton.setPreferredSize(new Dimension(80, 35));
								particularButton.setBackground(new Color(210,210,210));
								particularButton.setBorder(BorderFactory.createCompoundBorder(
										
										BorderFactory.createMatteBorder(0, 10, 4, 0, MainGUI.getBackgroundColor()), 
										
										BorderFactory.createLineBorder(Color.WHITE)
								));
										
								particularButton.setForeground(MainGUI.getBackgroundColor());
								particularButton.setFont(f1);
								
								particularButton.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent e) {

										Controlador.getInstancia().tratarPeticion(new Contexto("cambiarParticularVista", null));
										
										
									}
									
								});
					
							JButton empresaButton = new JButton("Empresa");
								empresaButton.setPreferredSize(new Dimension(80, 35));
								empresaButton.setBackground(new Color(210,210,210));
								empresaButton.setBorder(BorderFactory.createCompoundBorder(
										
										BorderFactory.createMatteBorder(4, 10, 0, 0, MainGUI.getBackgroundColor()),
										
										BorderFactory.createLineBorder(Color.WHITE)
								));
								
								empresaButton.setForeground(MainGUI.getBackgroundColor());
								empresaButton.setFont(f1);
								
								empresaButton.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent e) {

										Controlador.getInstancia().tratarPeticion(new Contexto("cambiarEmpresaVista", null));
										
										
									}
									
								});
						
						r.add(particularButton);
						r.add(empresaButton);
						
					chooseCliente.add(r, BorderLayout.EAST);
				
				
			}//else if (CLIENTE)
			
			else if(this.idModule.equals("Empleado")){
				
				Font f1 = new Font("Arial", Font.BOLD, 13);
			
				chooseEmpleado = new JPanel(new BorderLayout());
					chooseEmpleado.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 80, MainGUI.getBackgroundColor()));
					chooseEmpleado.setBackground(MainGUI.getBackgroundColor());
					

					
					JPanel r = new JPanel(new GridLayout(2,1));						
						r.setPreferredSize(new Dimension(150,0));
						
						JButton temporalButton = new JButton("Temporal");
							temporalButton.setPreferredSize(new Dimension(80, 35));
							temporalButton.setBackground(new Color(210,210,210));
							temporalButton.setBorder(BorderFactory.createCompoundBorder(
									
									BorderFactory.createMatteBorder(0, 10, 4, 0, MainGUI.getBackgroundColor()), 
									
									BorderFactory.createLineBorder(Color.WHITE)
							));
									
							temporalButton.setForeground(MainGUI.getBackgroundColor());
							temporalButton.setFont(f1);
							
							temporalButton.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {

									Controlador.getInstancia().tratarPeticion(new Contexto("CambiarTemporalVista", null));
									
									
								}
								
							});
				
						JButton fijoButton = new JButton("Fijo");
							fijoButton.setPreferredSize(new Dimension(80, 35));
							fijoButton.setBackground(new Color(210,210,210));
							fijoButton.setBorder(BorderFactory.createCompoundBorder(
									
									BorderFactory.createMatteBorder(4, 10, 0, 0, MainGUI.getBackgroundColor()),
									
									BorderFactory.createLineBorder(Color.WHITE)
							));
							
							fijoButton.setForeground(MainGUI.getBackgroundColor());
							fijoButton.setFont(f1);
							
							fijoButton.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {

									Controlador.getInstancia().tratarPeticion(new Contexto("CambiarFijoVista", null));
									
									
								}
								
							});
					
					r.add(temporalButton);
					r.add(fijoButton);
					
				chooseEmpleado.add(r, BorderLayout.EAST);
			
			
			}//else if (EMPLEADO)
			
			
			
			//ANTERIOR ESTRUCUTURA DEL BOTON BACK AL FINAL DEL SCRIPT. -->
			
			//PANEL PARA EL BOTON BACK
			
			JPanel b = new JPanel(new BorderLayout());
			b.setBackground(MainGUI.getBackgroundColor());
		
			JPanel backPanel = new  JPanel(new FlowLayout(FlowLayout.LEFT));
				backPanel.setBackground(MainGUI.getBackgroundColor());
		
				//BACK BUTTON
				JButton backButton = new JButton();
					backButton.setPreferredSize(new Dimension(120, 40));
					backButton.setBackground(new Color(210,210,210));
					backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));

					icon = new ImageIcon("src/Presentacion/Icons/back.png");
					backButton.setIcon(icon);
					
			
			//SI ES EL MODULO VENTAS EL BOTON BACK LO AÑADIMOS EN EL CENTRO POR QUE EN EL LADO ESTE IRÁ ALOJADA LA BARRA BUSCADORA.
			if( this.idModule.equals("Venta")) {

				backButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("iniciarVista", null));
					}
				});
						
				bPanel.add(backButton);
				bPanel.add(DevolucionButton);
			}
			
			/*//SI ESTAMOS EN LAS VISTAS DE CLIENTE EMPRESA / PARTICULAR PONEMOS EL BOTON BACK LOS AÑADIMOS EN EL LADO ESTE.
			if (this.idModule.equals("ClienteEmpresa") || this.idModule.equals("ClienteParticular")){

				backButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {

						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarClienteVista", null));
					}

				});
						
				backPanel.add(backButton);
				b.add(backPanel, BorderLayout.SOUTH);
				searchPanel.add(b, BorderLayout.EAST);
				
			}*/
				
			//AÑADIMOS EL PANEL DONDE VAN ALOJADOS LOS BOTONES PRINCIPALES DE LAS ENTIDADES (ELIMINAR, AÑADIR, MODIFICAR...)
			buttonsPanel.add(bPanel, BorderLayout.SOUTH);
			
			if (this.idModule.equals("Cliente")) {
				
				searchPanel.add(chooseCliente, BorderLayout.WEST);
				JPanel extra = new JPanel();
				extra.setPreferredSize(new Dimension(40,40));
				extra.setBackground(MainGUI.getBackgroundColor());
				searchPanel.add(extra, BorderLayout.CENTER);
				searchPanel.add(this.sPanel, BorderLayout.EAST);
			}
			
			else if(!(this.idModule.equals("ClienteParticular") || this.idModule.equals("ClienteEmpresa")))
				searchPanel.add(this.sPanel, BorderLayout.EAST);
			
			
			if (this.idModule.equals("Empleado")) {
				
				searchPanel.add(chooseEmpleado, BorderLayout.WEST);
				JPanel extra = new JPanel();
				extra.setPreferredSize(new Dimension(40,40));
				extra.setBackground(MainGUI.getBackgroundColor());
				searchPanel.add(extra, BorderLayout.CENTER);
				searchPanel.add(this.sPanel, BorderLayout.EAST);
			}
			
			else if(!(this.idModule.equals("EmpleadoFijo") || this.idModule.equals("EmpleadoTemporal")))
				searchPanel.add(this.sPanel, BorderLayout.EAST);
				
						
		this.add(buttonsPanel, BorderLayout.WEST);
		this.add(searchPanel, BorderLayout.EAST);
		
		
		
	}//initSettings
	
	public void errMensaje(String texto) {
		
		JOptionPane.showOptionDialog(new JFrame(), texto, "Error",
				
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		
	}
	
	public int mensajeQuery(String texto) {
		
		JPanel giveFecha = new JPanel(new BorderLayout());
		
			JLabel sentence = new JLabel(texto);
			
			JPanel givePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
				this.give = new JTextField();
					this.give.setPreferredSize(new Dimension(160,30));
					Font fuente = new Font("Arial", Font.BOLD, 13);
					this.give.setFont(fuente);
					this.give.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
					
					
			givePanel.add(give);
				
		giveFecha.add(sentence, BorderLayout.NORTH);
		giveFecha.add(givePanel, BorderLayout.SOUTH);
		
		return JOptionPane.showConfirmDialog(new JFrame(), giveFecha, "Consulta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	
}//Settings_General


/*if (this.idModule.equals("ClienteEmpresa") || this.idModule.equals("ClienteParticular") || this.idModule.equals("Venta")){

JPanel b = new JPanel(new BorderLayout());
	b.setBackground(MainGUI.getBackgroundColor());

	JPanel backPanel = new  JPanel(new FlowLayout(FlowLayout.LEFT));
		backPanel.setBackground(MainGUI.getBackgroundColor());

		//BACK BUTTON
		JButton backButton = new JButton();
			backButton.setPreferredSize(new Dimension(120, 40));
			backButton.setBackground(new Color(210,210,210));
			backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));

			icon = new ImageIcon("src/Presentacion/Icons/back.png");
			backButton.setIcon(icon);
			
			backButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(idModule.equals("ClienteEmpresa") || idModule.equals("ClienteParticular"))
					
						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarClienteVista", null));
					
					else
						
						Controlador.getInstancia().tratarPeticion(new Contexto("iniciarVista", null));
				}

			});
			
	backPanel.add(backButton);
	
	b.add(backPanel, BorderLayout.SOUTH);

searchPanel.add(b, BorderLayout.CENTER);
}*/

