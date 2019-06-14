package Presentacion.Vistas.Ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Negocio.Producto.ProductoTrans;
import Negocio.Venta.LineaVenta;
import Negocio.Venta.VentaTrans;
import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;
import Presentacion.Vistas.VistaPrincipal.MainGUI;
import Presentacion.Vistas.VistaPrincipal.RenderCelda;

public class Area_Venta extends JPanel {
	

	private static final long serialVersionUID = -7397721363971610627L;

	private String idModule = "VentaCompra";
	private int filas = 0;
	private ImageIcon icon;
	private Font fuente;
	private JTable tableVentas;
	private String[] nombreColumnas={"ID", "Nombre", "Cantidad", "Precio(€)"};
	private DefaultTableModel tableModel;
	private JTextField totalField;
	private JTextField importeField;
	private Double p = 0.0;
	private ActionListener closeAction;
	private ActionListener removeAction;
	
	/**
	 * Constructor
	 * 
	 * @param closeAction
	 */
	public Area_Venta(ActionListener closeAction, ActionListener removeAction) {
		
		this.closeAction = closeAction;
		this.removeAction = removeAction;
		
		initGUI();
		
	}//Area_Venta

	//***************************************************************************************************************************************************************

	/**
	 * Agrega todos los componentes Swing necesarios para poder visualizar la frame para poder realizar una venta.
	 */
	private void initGUI() {
		
		this.setLayout(new BorderLayout());
		this.setBackground(MainGUI.getBackgroundColor());
		
			fuente = new Font("Arial", Font.BOLD, 15);
		
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(10, 5, 0, 0, MainGUI.getBackgroundColor()),
				
														  BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),
																  
																						   "Venta Actual", TitledBorder.LEFT,  TitledBorder.TOP, 
																						   
																						   fuente, Color.WHITE)));
		
		//*******************************************************************************************************************************************
			
			//PANEL DDONDE SE ALOJA LA TABLA DE LA VENTA ACUTAL
		
			JPanel vPanel = new JPanel(new BorderLayout());			
				vPanel.setBackground(MainGUI.getBackgroundColor());				
			
				
				JPanel tableVentaPanel = new JPanel(new BorderLayout());
					tableVentaPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 0, MainGUI.getBackgroundColor()));
					

					this.tableVentas = createTableVentas();
					
					
					JScrollPane tableVentas_barra = new JScrollPane(this.tableVentas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						tableVentas_barra.setBorder(BorderFactory.createLineBorder(MainGUI.getBackgroundColor()));
					
				
				tableVentaPanel.add(tableVentas_barra, BorderLayout.CENTER);
				
				
			//*******************************************************************************************************************
				
				JPanel borderPanel = new JPanel(new BorderLayout());
				
					JPanel vPanelSouth = new JPanel(new BorderLayout());
						vPanelSouth.setBackground(new Color(210,210,210));
						vPanelSouth.setBorder(BorderFactory.createMatteBorder(0, 4, 1, 1, MainGUI.getBackgroundColor()));
					
						
						JLabel totalLabel = new JLabel("   Precio Total:");
							this.fuente = new Font("Arial", Font.BOLD, 15);
							totalLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, MainGUI.getBackgroundColor()));
							totalLabel.setFont(fuente);
							totalLabel.setForeground(MainGUI.getBackgroundColor());
											
							
						this.totalField = new JTextField();
							this.fuente = new Font("Arial", Font.BOLD, 20);
							this.totalField.setPreferredSize(new Dimension(130,30));
							this.totalField.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, MainGUI.getBackgroundColor()));
							this.totalField.setBackground(new Color(210,210,210));
							this.totalField.setFont(fuente);
							this.totalField.setForeground(Color.RED);
							this.totalField.setEditable(false);
							
							this.totalField.setText(this.p.toString());
							
							
					JPanel vPanelNorth = new JPanel(new BorderLayout());
						vPanelNorth.setBackground(new Color(210,210,210));
						vPanelNorth.setBorder(BorderFactory.createMatteBorder(0, 4, 1, 1, MainGUI.getBackgroundColor()));
					
							
						JLabel importeLabel = new JLabel("   Importe Entregado:");
							this.fuente = new Font("Arial", Font.BOLD, 15);	
							importeLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, MainGUI.getBackgroundColor()));
							importeLabel.setFont(fuente);
							importeLabel.setForeground(MainGUI.getBackgroundColor());
												
								
						this.importeField = new JTextField();
							this.fuente = new Font("Arial", Font.BOLD, 20);
							this.importeField.setPreferredSize(new Dimension(130,30));
							this.importeField.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, MainGUI.getBackgroundColor()));
							this.importeField.setBackground(new Color(210,210,210));
							this.importeField.setFont(fuente);
							this.importeField.setForeground(Color.BLUE);
						

				//***********************************************************************************************************
				
				vPanelSouth.add(totalLabel, BorderLayout.CENTER);
				vPanelSouth.add(this.totalField, BorderLayout.EAST);	
				
				vPanelNorth.add(importeLabel, BorderLayout.CENTER);
				vPanelNorth.add(this.importeField, BorderLayout.EAST);
				
				
			//*******************************************************************************************************************
				
				borderPanel.add(vPanelSouth, BorderLayout.NORTH);
				borderPanel.add(vPanelNorth, BorderLayout.SOUTH);
				
			vPanel.add(tableVentaPanel, BorderLayout.CENTER);
			vPanel.add(borderPanel, BorderLayout.SOUTH);
			
		//**********************************************************************************************************************************************

			JPanel bPanel = new JPanel(new BorderLayout(10,10));
				bPanel.setBorder(BorderFactory.createMatteBorder(4, 2, 2, 2, MainGUI.getBackgroundColor()));
				bPanel.setBackground(MainGUI.getBackgroundColor());
			
			
				//OK BUTTON
				JButton okButton = new JButton();
				
					//preferences
					okButton.setPreferredSize(new Dimension(80, 80));
					okButton.setBackground(new Color(210,210,210));
					okButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
					
					icon = new ImageIcon("src/Presentacion/Icons/shop.png");
					okButton.setIcon(icon);
					
					okButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							
							if(importeField.getText().isEmpty()){
								
								infMesanjeVenta("No ha introducido ningun importe para la venta.");
								
								
							}//if
							
							else{
						
								try{
									Double.parseDouble(importeField.getText());
									
									JPanel giveIdCliente = new JPanel(new BorderLayout());
										
										JLabel sentence = new JLabel("Introduzca la ID del cliente:");
										
										JPanel givePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
										
											JTextField give = new JTextField();
												give.setPreferredSize(new Dimension(80,30));
												fuente = new Font("Arial", Font.BOLD, 13);
												give.setFont(fuente);
												give.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
												
										givePanel.add(give);
												
									giveIdCliente.add(sentence, BorderLayout.NORTH);
									giveIdCliente.add(givePanel, BorderLayout.SOUTH);
										
								
									int giveId = JOptionPane.showConfirmDialog(new JFrame(), 
																				giveIdCliente, 
																				"ID Cliente", 
																				JOptionPane.DEFAULT_OPTION, 
																				JOptionPane.QUESTION_MESSAGE);
									
									Integer.parseInt(give.getText());
									
									if (giveId == 0){
										
										Double devolucion = Double.parseDouble(importeField.getText()) - Double.parseDouble(totalField.getText());
										
										if (devolucion < 0){
											infMesanjeVenta("El importe no es suficiente.");
										}//if
										
										else{
											
											
											if (devolucion > 0){
												infMesanjeVenta("La devolucion del importe son: " + devolucion + " €.");
												
											}//if
											
											String h = returnDate();
											
																					
											int id_Cliente = Integer.parseInt(give.getText());
											double total = Double.parseDouble(totalField.getText());
											ArrayList<LineaVenta> lVenta = new ArrayList<LineaVenta>();
											
											for (int i = 0; i < tableModel.getRowCount(); i++){
											
												lVenta.add(new LineaVenta((Integer)tableModel.getValueAt(i, 0), (Integer)tableModel.getValueAt(i, 2), (Double)tableModel.getValueAt(i, 3)));
												
											}//for
											VentaTrans ventaTmp = new VentaTrans(h, total, id_Cliente, lVenta);
											
											Controlador.getInstancia().tratarPeticion(new Contexto("CrearVenta", ventaTmp));
																					
										}//else		
									}//if
									
								}//try
								
								catch(NumberFormatException except){
									
									infMesanjeVenta("Formato del importe incorrecto");
								}
							}//else
							
						}//actionPerformed

						private String returnDate() {
							
							java.util.Date d = new java.util.Date();
							Date date = new Date(d.getTime());
							
							
							String h = date.toString();
							/*
							Integer tmp = d.getHours();
							
							if (tmp.toString().length() < 2)
								h += "0" + tmp.toString() + ":";
							else
								h+= tmp.toString() + ":";
							
							tmp = d.getMinutes();
							
							if (tmp.toString().length() < 2)
								h += "0" + tmp.toString() + ":";
							else
								h+= tmp.toString() + ":";
							
							tmp = d.getSeconds();
							
							if (tmp.toString().length() < 2)
								h += "0" + tmp.toString();
							else
								h+= tmp.toString();
							
							*/
							return h;
						}

					});//ActionListener
				
				//-----------------------------------------------------------------------------
					
				//CLOSE BUTTON
				JButton closeButton = new JButton();
				
					//preferences
					closeButton.setPreferredSize(new Dimension(80, 80));
					closeButton.setBackground(new Color(210,210,210));
					closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
					
					icon = new ImageIcon("src/Presentacion/Icons/close.png");
					closeButton.setIcon(icon);
					
					closeButton.addActionListener(this.closeAction);
					
				//-----------------------------------------------------------------------------

				//REMOVE BUTTON
				JButton removeButton = new JButton();
					
					//preferences
					removeButton.setPreferredSize(new Dimension(80, 80));
					removeButton.setBackground(new Color(210,210,210));
					removeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
					
					icon = new ImageIcon("src/Presentacion/Icons/b.png");
					removeButton.setIcon(icon);
					
					
					removeButton.addActionListener(removeAction);
					
				//----------------------------------------------------------------------------

			bPanel.add(okButton, BorderLayout.SOUTH);
			bPanel.add(removeButton, BorderLayout.CENTER);
			bPanel.add(closeButton, BorderLayout.NORTH);
			
	
		//**********************************************************************************************************************************************
	
				
		this.add(vPanel, BorderLayout.CENTER);
		this.add(bPanel, BorderLayout.EAST);
		
		
			
	}//initGUI
	
	

	private JTable createTableVentas() {
		
		this.tableModel = new DefaultTableModel(this.nombreColumnas, this.filas)  {
           
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int col){
				
                return false;
            }
        };
		
		JTable aux = new JTable(this.tableModel);
		
			JTableHeader th; 
				th = aux.getTableHeader();
				th.setFont(new Font("Atial", Font.BOLD, 15));
				th.setForeground(MainGUI.getBackgroundColor());
	
			
			TableCellRenderer renderer = new RenderCelda(this.idModule);
		
			aux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		    aux.setDefaultRenderer(Object.class, renderer);
		    
		    aux.setBackground(new Color(210,210,210));
		    aux.setGridColor(Color.WHITE);
	
		    aux.setRowHeight(25);
	    
		    aux.getColumnModel().getColumn(0).setPreferredWidth(60);
		
		    aux.getColumnModel().getColumn(1).setPreferredWidth(200); 
		    
		    aux.getColumnModel().getColumn(2).setPreferredWidth(100);
		    
		    aux.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		
		return aux;
		
	}//createTableVentas
	
	//**********************************************************************************************************************************************

	
	public void addProducto(ProductoTrans p){
		
		int i = 0;
		boolean encontrado = false;
		
		while(i < this.filas && !encontrado)
			
			if((int)this.tableVentas.getValueAt(i, 0) != p.getId())
				i++;
		
			else encontrado = true;
		
		//si ya existe en la lista de la venta --> actualizamos su Stock;
		if(encontrado)
			
			this.tableVentas.setValueAt(((int)this.tableVentas.getValueAt(i, 2) + 1), i, 2);
		
		//sino anadimos un producto nuevo a tabla de la lista ventas.
		else{

			this.tableModel.addRow(new Object[]{p.getId(), p.getNombre(), 1, p.getPrecio()});
			this.filas++;
			
		}
		
	}//addProducto
	
	//**********************************************************************************************************************************************

	
	public void removeProducto(int selected) {

		Double imp = (Double)this.tableVentas.getValueAt(selected, this.tableVentas.getColumnCount()-1);
		Integer cantidad = (Integer)this.tableVentas.getValueAt(selected, this.tableVentas.getColumnCount()-2);
		
		this.tableModel.removeRow(selected);
		this.filas--;
		
		Double tmp = Double.parseDouble(this.totalField.getText());
		
		tmp -= (imp*cantidad);
		
		this.totalField.setText(tmp.toString());
		
	}
	
	//**********************************************************************************************************************************************

	
	public void updateTotalImport(Double imp){
		
		if(totalField.getText().isEmpty())
			
			this.totalField.setText(imp.toString());
		
		else{
			
			Double tmp = Double.parseDouble(this.totalField.getText());
			
			tmp += imp;
			
			this.totalField.setText(tmp.toString());
		}		
	}
	
	//**********************************************************************************************************************************************

	
	public int emergMesanjeVenta(String texto) {
		
		return JOptionPane.showOptionDialog(new JFrame(), texto, "Venta",
				
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
	}
	
	//**********************************************************************************************************************************************

	
	public void infMesanjeVenta(String texto) {
		
		JOptionPane.showOptionDialog(new JFrame(), texto, "Error",
				
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		
	}
	
	public JTable getTable() {
		
		return this.tableVentas;
	}
	
}//Area_Venta




