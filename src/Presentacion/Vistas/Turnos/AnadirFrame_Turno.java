package Presentacion.Vistas.Turnos;

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

import Negocio.Turno.TurnoBO;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.Panel_AceptarCancelar;

public class AnadirFrame_Turno  extends JPanel{
	

	private static final long serialVersionUID = 497812086499626421L;
	
	/**
	 * COnstructor
	 */
	public AnadirFrame_Turno (){
	
		initGUI();
		
	}//AnadirFrame_Productos
	
	
	//*****************************************************************************************************

	/**
	 * Anade todos los componentes Swing necesarios para poder visualizar la frame para anadir un prodcuto
	 * 
	 */
	private void initGUI() {

		
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
							
					//HORA_ENTRADA
							
					JPanel hora_entradaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
						hora_entradaPanel.setPreferredSize(new Dimension(300, 50));
						hora_entradaPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 400, new Color(210,210,210)));
						hora_entradaPanel.setBackground(new Color(210,210,210));
						
						JLabel hora_entradaLabel = new JLabel("Hora de Entrada");
							hora_entradaLabel.setFont(new Font("Arial", Font.BOLD, 13));
							hora_entradaLabel.setBorder(BorderFactory.createEmptyBorder());
	
						final JTextField hora_entradaText = new JTextField();
							hora_entradaText.setFont(new Font("Arial", Font.BOLD, 12));
							hora_entradaText.setPreferredSize(new Dimension(250,30));
							hora_entradaText.setBorder(BorderFactory.createCompoundBorder(
											
								BorderFactory.createMatteBorder(0, 30, 0, 0, new Color(210,210,210)),
								
								BorderFactory.createLineBorder(Color.GRAY))
									
							);
									
									
					hora_entradaPanel.add(hora_entradaLabel);
					hora_entradaPanel.add(hora_entradaText);
						
					
				//------------------------------------------------------------------------------------------------------------------------------------------------
					
					//hora_salida
					
					JPanel hora_salidaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
						hora_salidaPanel.setPreferredSize(new Dimension(300, 50));
						hora_salidaPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 400, new Color(210,210,210)));
						hora_salidaPanel.setBackground(new Color(210,210,210));
						
						JLabel hora_salidaLabel = new JLabel("Hora de Salida");
							hora_salidaLabel.setFont(new Font("Arial", Font.BOLD, 13));
							hora_salidaLabel.setBorder(BorderFactory.createEmptyBorder());
	
						final JTextField hora_salidaText = new JTextField();
							hora_salidaText.setFont(new Font("Arial", Font.BOLD, 12));
							hora_salidaText.setPreferredSize(new Dimension(250,30));
							hora_salidaText.setBorder(BorderFactory.createCompoundBorder(
											
								
								BorderFactory.createMatteBorder(0, 15, 0, 0, new Color(210,210,210)),
								
								BorderFactory.createLineBorder(Color.GRAY))
									
							);
									
									
					hora_salidaPanel.add(hora_salidaLabel);
					hora_salidaPanel.add(hora_salidaText);
						
					
				
				//------------------------------------------------------------------------------------------------------------------------------------------------

				rellenarPanel.add(hora_entradaPanel);
				rellenarPanel.add(hora_salidaPanel);

					
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
					public void actionPerformed(ActionEvent e) {
						
						try {
							
							double entrada = Double.parseDouble(hora_entradaText.getText());
							double salida = Double.parseDouble(hora_salidaText.getText());
						
							if(hora_entradaText.getText().isEmpty() || hora_salidaText.getText().isEmpty()) {
								
								formatoErroneo();
							}
							
							else {
								
								Controlador.getInstancia().tratarPeticion(new Contexto("CrearTurno", new TurnoBO(entrada, salida, true)));
							}
						}
						
						catch (NumberFormatException except){
							
							formatoErroneo();
						}
							
					}//actionPerformed
			
				},
				
				//CANCELAR
				
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
					
						Controlador.getInstancia().tratarPeticion(new Contexto("CambiarTurnoVista", null));
				
					}//actionPerformed
			
				}), 
					
				BorderLayout.NORTH);
					
						
		this.add(sCenterPanel, BorderLayout.SOUTH);
			

			
	}//initGUI
	
	
	private void formatoErroneo() {
		
		JOptionPane.showOptionDialog(new JFrame(), "El formato de los datos no es correcto.", "ERROR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
	}
	
	
}
