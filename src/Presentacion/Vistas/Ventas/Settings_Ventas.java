package Presentacion.Vistas.Ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;

public class Settings_Ventas extends JPanel {
	

	private static final long serialVersionUID = 5998326195905068845L;

	private ImageIcon icon;
	private ActionListener listAction;
	private JPanel sPanel;
	private JButton searchButton;
	private JTextField searchArea;
	private JButton closeSearchButton;

	/**
	 * Constructor
	 * 
	 * @param listAction
	 */
	public Settings_Ventas(ActionListener listAction) {

		this.listAction = listAction;
	
		initGUI();
		
		
	}//Setting_Ventas

	/**
	 * Anade todos los elementos Swing necesarios para poder visualizar el frame para los botones de accion de ventas.
	 */
	private void initGUI() {
		
		this.setLayout(new BorderLayout(20,20));
		this.setPreferredSize(new Dimension(400,400));
		this.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),10));
		this.setBackground(MainGUI.getBackgroundColor());
		
		
		//*****************************************************************************************************
		
			this.sPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
				this.sPanel.setBorder(BorderFactory.createMatteBorder(40, 0, 7, 10, MainGUI.getBackgroundColor()));
				this.sPanel.setBackground(MainGUI.getBackgroundColor());
				
					//BARRA BUSCADORA
					
					this.searchArea = new JTextField();
					
						Font fuente = new Font("Arial", Font.BOLD, 13);
						
						//preferences
						this.searchArea.setFont(fuente);
						this.searchArea.setPreferredSize(new Dimension(200, 40));
						this.searchArea.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, MainGUI.getBackgroundColor()));
						
						this.searchArea.setEditable(true);
													
				
					//*******************************************************************************************
				
						
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
								
								if(!searchArea.getText().isEmpty()){
									
									sPanel.add(closeSearchButton);
									sPanel.add(searchArea);
									sPanel.add(searchButton);
								}

								Controlador.getInstancia().tratarPeticion(new Contexto("BuscarProductoVenta", searchArea.getText()));
								
							}
							
						});
						
						
					//CLOSE SEARCH BUTTON
					this.closeSearchButton = new JButton(new ImageIcon("src/Presentacion/Icons/close.png"));
						this.closeSearchButton.setPreferredSize(new Dimension(40, 40));
						this.closeSearchButton.setBorder(BorderFactory.createCompoundBorder(
								
															BorderFactory.createMatteBorder(0, 0, 0, 3, MainGUI.getBackgroundColor()), 
															
															BorderFactory.createLineBorder(Color.white,1))
							);
						
						this.closeSearchButton.setBackground(new Color(210,210,210));
						
						this.closeSearchButton.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								
								searchArea.setText("");
								
								sPanel.remove(closeSearchButton);

								Controlador.getInstancia().tratarPeticion(new Contexto("volverVentaVista", null));
								
							}
							
						});
						
				
				this.sPanel.add(searchArea);
				this.sPanel.add(searchButton);
				
				
		this.add(sPanel, BorderLayout.SOUTH);	
		
		
		//*****************************************************************************************************
			
			JPanel bPanel = new JPanel(new BorderLayout());
				bPanel.setBackground(MainGUI.getBackgroundColor());

					
				//LIST BUTTON
				JButton listButton = new JButton();
				
					//preferences
					listButton.setPreferredSize(new Dimension(100, 80));
					listButton.setBackground(new Color(210,210,210));
					
					listButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(30, 70, 0, 10, MainGUI.getBackgroundColor()),
																			
																			BorderFactory.createLineBorder(Color.WHITE, 1)));
					
					icon = new ImageIcon("src/Presentacion/Icons/list.png");
					listButton.setIcon(icon);
					
					listButton.addActionListener(listAction);
		
				;
			bPanel.add(listButton, BorderLayout.NORTH);
			
			
				
		this.add(bPanel, BorderLayout.CENTER);
				
	}//initGUI
	

}//Settings_Ventas
