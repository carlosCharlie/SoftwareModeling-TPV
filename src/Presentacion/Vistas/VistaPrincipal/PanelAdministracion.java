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


public class PanelAdministracion extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public PanelAdministracion(){
		
		initGUI();
	}
	
	
	private void initGUI() {
	
		this.setLayout(new GridLayout(3, 1));
		this.setBackground(MainGUI.getBackgroundColor());
		
	//**************************************************************************************************************************	
		
		//CREACION DE BOTONES
		
		//EMPLEADOS
		
		JPanel empleadosPanel = new JPanel(new BorderLayout());
			empleadosPanel.setPreferredSize(new Dimension(110, 120));
			empleadosPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),4));
	
			JButton empleadosButton = new JButton("Empleados");
				empleadosButton.setBackground(new Color(210,210,210));
				empleadosButton.setFont(new Font("Arial", Font.BOLD, 14));
				empleadosButton.setForeground(MainGUI.getBackgroundColor());
				empleadosButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
				
				empleadosButton.addActionListener(new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("CambiarEmpleadosVista", null));
						
					}
					
				});
	
		empleadosPanel.add(empleadosButton);
		
		
		//--------------------------------------------------------------------------------------------------------------
	
		//TURNOS
		
		JPanel turnosPanel = new JPanel(new BorderLayout());
			turnosPanel.setPreferredSize(new Dimension(110, 120));
			turnosPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(),4));
	
			
			JButton turnosButton = new JButton("Turnos");
				turnosButton.setBackground(new Color(210,210,210));
				turnosButton.setFont(new Font("Arial", Font.BOLD, 14));
				turnosButton.setForeground(MainGUI.getBackgroundColor());
				turnosButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
				
				turnosButton.addActionListener(new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("CambiarTurnoVista", null));
						
					}
					
				});
	
				
		turnosPanel.add(turnosButton);
		
		
		//--------------------------------------------------------------------------------------------------------------
		
		//DEPARTAMENTOS
		
		JPanel departamentosPanel = new JPanel(new BorderLayout());
			departamentosPanel.setPreferredSize(new Dimension(110, 120));
			departamentosPanel.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor(), 4));
			
	
			JButton departamentosButton = new JButton("Departamentos");
				departamentosButton.setBackground(new Color(210,210,210));
				departamentosButton.setFont(new Font("Arial", Font.BOLD, 14));
				departamentosButton.setForeground(MainGUI.getBackgroundColor());
				departamentosButton.setBorder(BorderFactory.createLineBorder(Color.white,1));
				
				departamentosButton.addActionListener(new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Controlador.getInstancia().tratarPeticion(new Contexto("CambiarDepartamentosVista", null));
						
					}
						
				});
				
		departamentosPanel.add(departamentosButton);
	
		
	//--------------------------------------------------------------------------------------------------------------
	
		this.add(empleadosPanel);
		this.add(departamentosPanel);
		this.add(turnosPanel);
		
	}
	
	

}
