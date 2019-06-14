package Presentacion.Vistas.Productos;

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

import Negocio.Producto.ProductoTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.Panel_AceptarCancelar;


public class AnadirFrame_Productos extends JPanel{
	

	private static final long serialVersionUID = 497812086499626421L;
	
	/**
	 * COnstructor
	 */
	public AnadirFrame_Productos (){
	
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
									
									
					nombrePanel.add(nombreLabel);
					nombrePanel.add(nombreText);
						
					
				//------------------------------------------------------------------------------------------------------------------------------------------------
					
					//PROVEEDOR
					
					JPanel proveedorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
						proveedorPanel.setPreferredSize(new Dimension(300, 50));
						proveedorPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 400, new Color(210,210,210)));
						proveedorPanel.setBackground(new Color(210,210,210));
						
						JLabel proveedorLabel = new JLabel("Proveedor");
							proveedorLabel.setFont(new Font("Arial", Font.BOLD, 13));
							proveedorLabel.setBorder(BorderFactory.createEmptyBorder());
	
						final JTextField proveedorText = new JTextField();
							proveedorText.setFont(new Font("Arial", Font.BOLD, 12));
							proveedorText.setPreferredSize(new Dimension(250,30));
							proveedorText.setBorder(BorderFactory.createCompoundBorder(
											
								
								BorderFactory.createMatteBorder(0, 15, 0, 0, new Color(210,210,210)),
								
								BorderFactory.createLineBorder(Color.GRAY))
									
							);
									
									
					proveedorPanel.add(proveedorLabel);
					proveedorPanel.add(proveedorText);
						
					
				//------------------------------------------------------------------------------------------------------------------------------------------------
					
					
					//STOCK
						
					JPanel stockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
						stockPanel.setPreferredSize(new Dimension(300, 50));
						stockPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 500, new Color(210,210,210)));
						stockPanel.setBackground(new Color(210,210,210));
							
						
						JLabel stockLabel = new JLabel("Stock ");
							stockLabel.setFont(new Font("Arial", Font.BOLD, 13));
							stockLabel.setBorder(BorderFactory.createEmptyBorder());
								
					
						final JTextField stockText = new JTextField();
							stockText.setFont(new Font("Arial", Font.BOLD, 12));
							stockText.setPreferredSize(new Dimension(105,30));
							stockText.setBorder(BorderFactory.createCompoundBorder(
										
								
								BorderFactory.createMatteBorder(0, 41, 0, 0, new Color(210,210,210)),
								
								BorderFactory.createLineBorder(Color.GRAY))
							);
							
							
						JLabel unidadesLabel = new JLabel(" unds.");
						unidadesLabel.setFont(new Font("Arial", Font.BOLD, 13));
						unidadesLabel.setBorder(BorderFactory.createEmptyBorder());
					
					stockPanel.add(stockLabel);
					stockPanel.add(stockText);
					stockPanel.add(unidadesLabel);
				
				
				//------------------------------------------------------------------------------------------------------------------------------------------------
	
					//PRECIO
						
					JPanel precioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
						precioPanel.setPreferredSize(new Dimension(300, 50));
						precioPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 550, new Color(210,210,210)));
						precioPanel.setBackground(new Color(210,210,210));
							
						
						JLabel precioLabel = new JLabel("Precio ");
							precioLabel.setFont(new Font("Arial", Font.BOLD, 13));
							precioLabel.setBorder(BorderFactory.createEmptyBorder());
								
					
						final JTextField precioText = new JTextField();
							precioText.setFont(new Font("Arial", Font.BOLD, 12));
							precioText.setPreferredSize(new Dimension(100,30));
							precioText.setBorder(BorderFactory.createCompoundBorder(
										
								BorderFactory.createMatteBorder(0, 36, 0, 0, new Color(210,210,210)),
								
								BorderFactory.createLineBorder(Color.GRAY))
							);
							
						
						JLabel eurosLabel = new JLabel(" €");
						eurosLabel.setFont(new Font("Arial", Font.BOLD, 13));
						eurosLabel.setBorder(BorderFactory.createEmptyBorder());
							
						precioPanel.add(precioLabel);
						precioPanel.add(precioText);
						precioPanel.add(eurosLabel);
						
				
				//------------------------------------------------------------------------------------------------------------------------------------------------

				rellenarPanel.add(nombrePanel);
				rellenarPanel.add(proveedorPanel);
				rellenarPanel.add(stockPanel);
				rellenarPanel.add(precioPanel);

					
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
						
							int stock = Integer.parseInt(stockText.getText());
							
							double precio = Double.parseDouble(precioText.getText());
							
							if(stock < 0 || precio < 0) {
								
								formatoErroneo();
							}
							else if(nombreText.getText().isEmpty() || proveedorText.getText().isEmpty() 
									|| stockText.getText().isEmpty() || precioText.getText().isEmpty() ) {
								
								formatoErroneo();
							}
							
							else {
								
								Controlador.getInstancia().tratarPeticion(new Contexto("CrearProducto", new ProductoTrans(nombreText.getText(), 
										
									      proveedorText.getText(),
												
										  stock,

										  precio,
										  
										  true)));
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
					
						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarProductoVista", null));
				
					}//actionPerformed
			
				}), 
					
				BorderLayout.NORTH);
					
						
		this.add(sCenterPanel, BorderLayout.SOUTH);
			

			
	}//initGUI
	
	
	private void formatoErroneo() {
		
		JOptionPane.showOptionDialog(new JFrame(), "El formato de los datos no es correcto.", "ERROR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
	}
	
}//AnadirFrame_Productos



