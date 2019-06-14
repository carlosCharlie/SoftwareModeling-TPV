package Presentacion.Vistas.VistaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;

public class PanelTienda extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelTienda(){
		
		initGUI();	
	}
	
	
	private void initGUI() {
	
		this.setLayout(new GridLayout(3, 1));
		this.setBackground(MainGUI.getBackgroundColor());
		
	//**************************************************************************************************************************	
		
		//CREACION DE BOTONES
		
		//PRODUCTOS
		
		JPanel productosPanel = new JPanel(new BorderLayout());
			productosPanel.setPreferredSize(new Dimension(110, 120));
			productosPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),4));
	
			JButton productosButton = new JButton("Productos");
				productosButton.setBackground(new Color(210,210,210));
				productosButton.setFont(new Font("Arial", Font.BOLD, 14));
				productosButton.setForeground(MainGUI.getBackgroundColor());
				productosButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
				
				productosButton.addActionListener(new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarProductoVista", null));
						
					}
					
				});
	
		productosPanel.add(productosButton);
		
		
		//--------------------------------------------------------------------------------------------------------------
	
		//CLIENTES
		
		JPanel clientesPanel = new JPanel(new BorderLayout());
			clientesPanel.setPreferredSize(new Dimension(110, 120));
			clientesPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),4));
	
			
			JButton clientesButton = new JButton("Clientes");
				clientesButton.setBackground(new Color(210,210,210));
				clientesButton.setFont(new Font("Arial", Font.BOLD, 14));
				clientesButton.setForeground(MainGUI.getBackgroundColor());
				clientesButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
				
				clientesButton.addActionListener(new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("cambiarClienteVista", null));
						
					}
					
				});
	
				
		clientesPanel.add(clientesButton);
		
		
		//--------------------------------------------------------------------------------------------------------------
		
		//VENTAS
		
		JPanel ventasPanel = new JPanel(new BorderLayout());
			ventasPanel.setPreferredSize(new Dimension(110, 120));
			ventasPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(), 4));
			
	
			JButton ventaButton = new JButton("Ventas");
				ventaButton.setBackground(new Color(210,210,210));
				ventaButton.setFont(new Font("Arial", Font.BOLD, 14));
				ventaButton.setForeground(MainGUI.getBackgroundColor());
				ventaButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
				
				ventaButton.addActionListener(new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("iniciarVista", null));
						
					}
						
				});
				
		ventasPanel.add(ventaButton);
	
		
	//--------------------------------------------------------------------------------------------------------------
	
		this.add(ventasPanel);
		this.add(productosPanel);
		this.add(clientesPanel);
		
	}

}
	