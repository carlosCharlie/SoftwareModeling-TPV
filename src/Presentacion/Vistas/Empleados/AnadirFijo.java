package Presentacion.Vistas.Empleados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Departamento.DepartamentoBO;
import Negocio.Empleado.FijoBO;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.Panel_AceptarCancelar;

public class AnadirFijo extends JPanel{
	

	private static final long serialVersionUID = -5789873939536436174L;
	
	private JTextField nombreText, direccionText, nssText, emailText, departamentoText, sueldoText, impuestosText;

	/**
	 * Constructor
	 */
	public AnadirFijo(){
		
		initAnadir();
		
	}

	/**
	 * Agrega todos los componenetes Swing necesarios para poder anadir un Cliente Particular
	 * 
	 */
	private void initAnadir() {
		
		this.setLayout(new BorderLayout());
		this.setBackground(MainGUI.getBackgroundColor());
			
	
			JPanel nCenterPanel = new JPanel(new BorderLayout());
				nCenterPanel.setPreferredSize(new Dimension(0,400));
					
			
				JPanel rellenarPanel = new JPanel(new GridLayout(6,1,3,3));
					rellenarPanel.setBackground(new Color(210,210,210));
					rellenarPanel.setBorder(BorderFactory.createCompoundBorder(
								
						BorderFactory.createMatteBorder(20, 20, 35, 250, MainGUI.getBackgroundColor()),
						
						BorderFactory.createLineBorder(Color.white))
					);
						
					
	
		
		//-----------------------------------------------------------------------------------------------------------------------------------------------
			//COMENZAMOS A ANADIR LOS DIFERENTES CAMPOS CORRESPONDIENTES.
			
			
			//NSS
			
			JPanel nssPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				nssPanel.setPreferredSize(new Dimension(300, 50));
				nssPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				nssPanel.setBackground(new Color(210,210,210));
				
				
				JLabel nssLabel = new JLabel("NSS");
					nssLabel.setFont(new Font("Arial", Font.BOLD, 13));
					nssLabel.setBorder(BorderFactory.createEmptyBorder());
					
				
			
		
				this.nssText = new JTextField();
					this.nssText.setFont(new Font("Arial", Font.BOLD, 12));
					this.nssText.setBorder(BorderFactory.createCompoundBorder(
						
							BorderFactory.createMatteBorder(0, 33, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					this.nssText.setPreferredSize(new Dimension(130,30));
					
				
			nssPanel.add(nssLabel, BorderLayout.WEST);
			nssPanel.add(this.nssText, BorderLayout.CENTER);
	
			
	
		//------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			//NOMBRE
			
			JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				nombrePanel.setPreferredSize(new Dimension(300, 50));
				nombrePanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				nombrePanel.setBackground(new Color(210,210,210));
				
				
				JLabel nombreLabel = new JLabel("Nombre");
					nombreLabel.setFont(new Font("Arial", Font.BOLD, 13));
					nombreLabel.setBorder(BorderFactory.createEmptyBorder());
					
	
				this.nombreText = new JTextField();
					this.nombreText.setFont(new Font("Arial", Font.BOLD, 12));
					this.nombreText.setBorder(BorderFactory.createCompoundBorder(
	
							BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					this.nombreText.setPreferredSize(new Dimension(200,30));
					
					
			nombrePanel.add(nombreLabel, BorderLayout.WEST);
			nombrePanel.add(this.nombreText, BorderLayout.CENTER);
		
		//------------------------------------------------------------------------------------------------------------------------------------------------
	
		
			//DIRECCION
			
			JPanel direccionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				direccionPanel.setPreferredSize(new Dimension(300, 50));
				direccionPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				direccionPanel.setBackground(new Color(210,210,210));
				
				
				JLabel direccionLabel = new JLabel("Direccion");
					direccionLabel.setFont(new Font("Arial", Font.BOLD, 13));
					direccionLabel.setBorder(BorderFactory.createEmptyBorder());

		
				this.direccionText = new JTextField();
					this.direccionText.setFont(new Font("Arial", Font.BOLD, 12));
					this.direccionText.setBorder(BorderFactory.createCompoundBorder(

							BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					
					this.direccionText.setPreferredSize(new Dimension(200,30));
					
					
			direccionPanel.add(direccionLabel, BorderLayout.WEST);
			direccionPanel.add(this.direccionText, BorderLayout.CENTER);
	
		
		//------------------------------------------------------------------------------------------------------------------------------------------------
		
		
			
			//EMAIL
			
			JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				emailPanel.setPreferredSize(new Dimension(300, 50));
				emailPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				emailPanel.setBackground(new Color(210,210,210));
				
				
				JLabel emailLabel = new JLabel("e-Mail ");
					emailLabel.setFont(new Font("Arial", Font.BOLD, 13));
					emailLabel.setBorder(BorderFactory.createEmptyBorder());
					
				
				this.emailText = new JTextField();
			
					this.emailText.setFont(new Font("Arial", Font.BOLD, 12));
					this.emailText.setBorder(BorderFactory.createCompoundBorder(
		
							BorderFactory.createMatteBorder(0, 14, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					
					this.emailText.setPreferredSize(new Dimension(300,30));
					
					
			emailPanel.add(emailLabel, BorderLayout.WEST);
			emailPanel.add(this.emailText, BorderLayout.CENTER);
	
		
			//------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			//DEPARTAMENTO
			
			JPanel departamentoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				departamentoPanel.setPreferredSize(new Dimension(300, 50));
				departamentoPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				departamentoPanel.setBackground(new Color(210,210,210));
				
				
				JLabel departamentoLabel = new JLabel("Departamento");
					departamentoLabel.setFont(new Font("Arial", Font.BOLD, 13));
					departamentoLabel.setBorder(BorderFactory.createEmptyBorder());
					
		
				this.departamentoText = new JTextField();
					this.departamentoText.setFont(new Font("Arial", Font.BOLD, 12));
					this.departamentoText.setBorder(BorderFactory.createCompoundBorder(
							
							BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					
					this.departamentoText.setPreferredSize(new Dimension(130,30));
					
					
			departamentoPanel.add(departamentoLabel, BorderLayout.WEST);
			departamentoPanel.add(this.departamentoText, BorderLayout.CENTER);
			
			
			//------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			//SUELDO
			
			JPanel sueldoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				sueldoPanel.setPreferredSize(new Dimension(300, 50));
				sueldoPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				sueldoPanel.setBackground(new Color(210,210,210));
				
				
				JLabel sueldoLabel = new JLabel("Sueldo");
					sueldoLabel.setFont(new Font("Arial", Font.BOLD, 13));
					sueldoLabel.setBorder(BorderFactory.createEmptyBorder());
					
		
				this.sueldoText = new JTextField();
					this.sueldoText.setFont(new Font("Arial", Font.BOLD, 12));
					this.sueldoText.setBorder(BorderFactory.createCompoundBorder(
							
							BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					
					this.sueldoText.setPreferredSize(new Dimension(130,30));
					
					
			sueldoPanel.add(sueldoLabel, BorderLayout.WEST);
			sueldoPanel.add(this.sueldoText, BorderLayout.CENTER);
			
			
			//------------------------------------------------------------------------------------------------------------------------------------------------

			
			//IMPUESTOS
			
			JPanel impuestosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				impuestosPanel.setPreferredSize(new Dimension(300, 50));
				impuestosPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				impuestosPanel.setBackground(new Color(210,210,210));
				
				
				JLabel impuestosLabel = new JLabel("Impuestos");
					impuestosLabel.setFont(new Font("Arial", Font.BOLD, 13));
					impuestosLabel.setBorder(BorderFactory.createEmptyBorder());
					
		
				this.impuestosText = new JTextField();
					this.impuestosText.setFont(new Font("Arial", Font.BOLD, 12));
					this.impuestosText.setBorder(BorderFactory.createCompoundBorder(
							
							BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					
					this.impuestosText.setPreferredSize(new Dimension(130,30));
					
					
			impuestosPanel.add(impuestosLabel, BorderLayout.WEST);
			impuestosPanel.add(this.impuestosText, BorderLayout.CENTER);
			
			
			
			
			
		//------------------------------------------------------------------------------------------------------------------------------------------------

	//------------------------------------------------------------------------------------------------------------------------------------------------
	
	
		rellenarPanel.add(nssPanel);	
		rellenarPanel.add(nombrePanel);		
		rellenarPanel.add(direccionPanel);
		rellenarPanel.add(emailPanel);
		rellenarPanel.add(departamentoPanel);
		rellenarPanel.add(sueldoPanel);
		rellenarPanel.add(impuestosPanel);
		
		
		
		nCenterPanel.add(rellenarPanel, BorderLayout.CENTER);
		
		
	this.add(nCenterPanel, BorderLayout.CENTER);
		
		//PANEL PARA LOS BOTONES --> ACEPTAR Y CANCELAR.
		
				JPanel sCenterPanel = new JPanel(new BorderLayout());
				
					sCenterPanel.setBackground(MainGUI.getBackgroundColor());
					sCenterPanel.setPreferredSize(new Dimension(0,250));
			
								
				sCenterPanel.add(new Panel_AceptarCancelar(
						
						//ACEPTAR
						new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									
									try {

										if(nssText.getText().isEmpty() || nombreText.getText().isEmpty() || emailText.getText().isEmpty() || 
												direccionText.getText().isEmpty() || sueldoText.getText().isEmpty() || impuestosText.getText().isEmpty()){
											
											formatoErroneo();
										}
										
										else {
											
											double sueldo = Double.parseDouble(sueldoText.getText());
											
											double impuestos = Double.parseDouble(impuestosText.getText());
											
											int nss = Integer.parseInt(nssText.getText());
											
											int departamento;
											
											if(!departamentoText.getText().isEmpty()) {
												
												departamento = Integer.parseInt(departamentoText.getText());
												
												Controlador.getInstancia().tratarPeticion(new Contexto("CrearEmpleado", new FijoBO(nombreText.getText(),
														
														new DepartamentoBO(departamento), 
														
														emailText.getText(), 
														
														nss, 
														
														true, 
														
														direccionText.getText(),
														
														sueldo,
														
														impuestos)));
											}//if
											
											//SI SE VA A CREAR UN EMPLEADO SIN DEPARTAMENTO
											else {
												
												Controlador.getInstancia().tratarPeticion(new Contexto("CrearEmpleado", new FijoBO(nombreText.getText(), 
														
														emailText.getText(), 
														
														nss, 
														
														true, 
														
														direccionText.getText(),
														
														sueldo,
														
														impuestos)));
												
											}
											
											
											
											
										

										}
										
									}
									
									catch (NumberFormatException except) {
									
										formatoErroneo();
									}

								}//actionPerformed
						
						}, 
						
						//CANCELAR
						new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
							
								Controlador.getInstancia().tratarPeticion(new Contexto("CambiarFijoVista", null));
						
							}//actionPerformed
					
						}//new actionListener
					), 
						
					BorderLayout.NORTH);
						
				
			this.add(sCenterPanel, BorderLayout.SOUTH);
		
	}//initRellenar

	

	private void formatoErroneo() {
		
		JOptionPane.showOptionDialog(new JFrame(), "El formato de los datos no es correcto.", "ERROR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
	}
	
}
