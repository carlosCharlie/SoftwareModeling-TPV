package Presentacion.Vistas.Clientes;

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

import Negocio.Cliente.ParticularTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.Panel_AceptarCancelar;



public class AnadirParticular extends JPanel{
	

	private static final long serialVersionUID = -5789873939536436174L;
	
	private JTextField nombreText, apellidoText, dniText, emailText, telefonoText;

	/**
	 * Constructor
	 */
	public AnadirParticular(){
		
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
			
			
			//DNI
			
			JPanel dniPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				dniPanel.setPreferredSize(new Dimension(300, 50));
				dniPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				dniPanel.setBackground(new Color(210,210,210));
				
				
				JLabel dniLabel = new JLabel("DNI");
					dniLabel.setFont(new Font("Arial", Font.BOLD, 13));
					dniLabel.setBorder(BorderFactory.createEmptyBorder());
					
				
			
		
				this.dniText = new JTextField();
					this.dniText.setFont(new Font("Arial", Font.BOLD, 12));
					this.dniText.setBorder(BorderFactory.createCompoundBorder(
						
							BorderFactory.createMatteBorder(0, 33, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					this.dniText.setPreferredSize(new Dimension(130,30));
					
				
			dniPanel.add(dniLabel, BorderLayout.WEST);
			dniPanel.add(this.dniText, BorderLayout.CENTER);
	
			
	
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
	
		
			//APELLIDO
			
			JPanel apellidoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				apellidoPanel.setPreferredSize(new Dimension(300, 50));
				apellidoPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				apellidoPanel.setBackground(new Color(210,210,210));
				
				
				JLabel apellidoLabel = new JLabel("Apellido");
					apellidoLabel.setFont(new Font("Arial", Font.BOLD, 13));
					apellidoLabel.setBorder(BorderFactory.createEmptyBorder());

		
				this.apellidoText = new JTextField();
					this.apellidoText.setFont(new Font("Arial", Font.BOLD, 12));
					this.apellidoText.setBorder(BorderFactory.createCompoundBorder(

							BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					
					this.apellidoText.setPreferredSize(new Dimension(200,30));
					
					
			apellidoPanel.add(apellidoLabel, BorderLayout.WEST);
			apellidoPanel.add(this.apellidoText, BorderLayout.CENTER);
	
		
	
		//------------------------------------------------------------------------------------------------------------------------------------------------
	
		
			//TELEFONO
			
			JPanel telefonoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
				telefonoPanel.setPreferredSize(new Dimension(300, 50));
				telefonoPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 50, new Color(210,210,210)));
				telefonoPanel.setBackground(new Color(210,210,210));
				
				
				JLabel telefonoLabel = new JLabel("Telefono");
					telefonoLabel.setFont(new Font("Arial", Font.BOLD, 13));
					telefonoLabel.setBorder(BorderFactory.createEmptyBorder());
					
		
				this.telefonoText = new JTextField();
					this.telefonoText.setFont(new Font("Arial", Font.BOLD, 12));
					this.telefonoText.setBorder(BorderFactory.createCompoundBorder(
							
							BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
						);
					
					
					this.telefonoText.setPreferredSize(new Dimension(130,30));
					
					
			telefonoPanel.add(telefonoLabel, BorderLayout.WEST);
			telefonoPanel.add(this.telefonoText, BorderLayout.CENTER);
	
		
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

	//------------------------------------------------------------------------------------------------------------------------------------------------
	
	
		rellenarPanel.add(dniPanel);	
		rellenarPanel.add(nombrePanel);		
		rellenarPanel.add(apellidoPanel);
		rellenarPanel.add(telefonoPanel);
		rellenarPanel.add(emailPanel);
		
		
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

										if(dniText.getText().isEmpty() || nombreText.getText().isEmpty() || 
											Integer.parseInt(telefonoText.getText())< 0|| telefonoText.getText().isEmpty() || emailText.getText().isEmpty() || 
												apellidoText.getText().isEmpty()) {
											
											formatoErroneo();
										}
										
										else {
											
											int telefono = Integer.parseInt(telefonoText.getText());
											
											Controlador.getInstancia().tratarPeticion(new Contexto("CrearCliente", new ParticularTrans(nombreText.getText(),
													
													telefono, 
													
													emailText.getText(), 
													
													dniText.getText(), 
													
													true, 
													
													apellidoText.getText())));

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
							
								Controlador.getInstancia().tratarPeticion(new Contexto("cambiarParticularVista", null));
						
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
	
}//RellenarParticular


