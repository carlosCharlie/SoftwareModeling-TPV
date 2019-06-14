package Presentacion.Vistas.VistaPrincipal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel_AceptarCancelar extends JPanel {

	private static final long serialVersionUID = -2957465178454803283L;
	
	private JButton aceptarButton;
	private JButton cancelarButton;
	
	/**
	 * Constructor
	 * 
	 * Diferentes acciones para que se ejecute la opcion elegida en Settings_General.
	 * 
	 * @param AceptarAction
	 * @param CancelarAction
	 */
	public Panel_AceptarCancelar(ActionListener AceptarAction, ActionListener CancelarAction){
		
		initPanelAC(AceptarAction, CancelarAction);
		
	}

	private void initPanelAC(ActionListener AceptarAction,ActionListener CancelarAction) {
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.setPreferredSize(new Dimension(200,80));
		this.setBackground(MainGUI.getBackgroundColor());
		this.setBorder(BorderFactory.createMatteBorder(0, 20, 0, 500, MainGUI.getBackgroundColor()));
		
		
			this.aceptarButton = new JButton(new ImageIcon("src/Presentacion/Icons/ok.png"));
				aceptarButton.setFont(new Font("Arial", Font.BOLD, 14));
				aceptarButton.setPreferredSize(new Dimension(120,35));
				aceptarButton.setBackground(new Color(210,210,210));
				aceptarButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
					
			this.cancelarButton = new JButton(new ImageIcon("src/Presentacion/Icons/close.png"));
				cancelarButton.setFont(new Font("Arial", Font.BOLD, 14));
				cancelarButton.setPreferredSize(new Dimension(120,35));
				cancelarButton.setBackground(new Color(210,210,210));
				cancelarButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				
				
		this.aceptarButton.addActionListener(AceptarAction);
		this.cancelarButton.addActionListener(CancelarAction);
		
		
		
		this.add(aceptarButton);
		this.add(cancelarButton);
		
		
	}//initPanelAC
	
}//Panel_AceptarCancelar

