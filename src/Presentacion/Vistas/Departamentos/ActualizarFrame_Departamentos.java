package Presentacion.Vistas.Departamentos;

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
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.Panel_AceptarCancelar;

public class ActualizarFrame_Departamentos extends JPanel{

	private static final long serialVersionUID = 1L;
	
	//ESTOS ATRIBUTOS HAY QUE VOLVER A METERLOS EN EL BO PARA PASARLO AL CONTROLADOR.
	private int id;
	private String nombre;

	
	/**
	 * Constructor
	 * 
	 * @param id
	 * @param nombre
	 * @param stock
	 * @param precio
	 * @param idMarca
	 * @param idProveedor
	 */
	public ActualizarFrame_Departamentos (int id, String nombre){
	
		this.id = id;
		this.nombre = nombre;
		
		initGUI();
		
	}//ActualizarFrame_Productos
	
	
	//*****************************************************************************************************************************************************************


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
						
					//NOMBRE
						
					JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
					nombrePanel.setPreferredSize(new Dimension(300, 50));
					nombrePanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 400, new Color(210,210,210)));
					nombrePanel.setBackground(new Color(210,210,210));
					
					JLabel nombreLabel = new JLabel("Nombre");
						nombreLabel.setFont(new Font("Arial", Font.BOLD, 13));
						nombreLabel.setBorder(BorderFactory.createEmptyBorder());

					final JTextField nombreText = new JTextField();
						nombreText.setFont(new Font("Arial", Font.BOLD, 12));
						nombreText.setPreferredSize(new Dimension(250,30));
						nombreText.setBorder(BorderFactory.createCompoundBorder(
										
							BorderFactory.createMatteBorder(0, 30, 0, 0, new Color(210,210,210)),
							
							BorderFactory.createLineBorder(Color.GRAY))
								
						);
								
								nombreText.setText(this.nombre);
								
						nombrePanel.add(nombreLabel);
						nombrePanel.add(nombreText);
					
					
				//------------------------------------------------------------------------------------------------------------------------------------------------
					
				rellenarPanel.add(nombrePanel);

				
					
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
					
						try{
		
							
							if(nombreText.getText().isEmpty()) {
								
								formatoErroneo();
							}
							else {
								
								Controlador.getInstancia().tratarPeticion(new Contexto("ModificarDepartamento", new DepartamentoBO(id, nombreText.getText())));
							}
							
						}
						
						catch(NumberFormatException except){
							
							formatoErroneo();
							
						};
					}//actionPerformed
			
				},
				
				//CANCELAR
				
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
					
						Controlador.getInstancia().tratarPeticion(new Contexto("CambiarDepartamentosVista", null));
				
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
