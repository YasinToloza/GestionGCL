package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MysqlProveedorDAO;
import com.biblioteca.entidad.Provedor;
import com.biblioteca.interfaces.ProveedorDAO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmbuscarProveedor extends JFrame {
	private MysqlProveedorDAO Proveedor=new MysqlProveedorDAO();
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTable tblProveedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmbuscarProveedor frame = new FrmbuscarProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmbuscarProveedor() {
		setResizable(false);
		setTitle("Buscar Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 804, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(76, 23, 245, 20);
		contentPane.add(txtCodigo);
		
		JLabel label = new JLabel("Codigo");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 25, 56, 14);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 770, 281);
		contentPane.add(scrollPane);
		
		tblProveedor = new JTable();
		tblProveedor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "RAZON SOCIAL", "RUC", "DIRECCION", "FONO", "PRECIO", "OBJETO", "ESTADO"
			}
		));
		tblProveedor.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(tblProveedor);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnBuscar(arg0);
			}
		});
		btnBuscar.setIcon(new ImageIcon(FrmbuscarProveedor.class.getResource("/iconos/search.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setBounds(579, 11, 50, 43);
		contentPane.add(btnBuscar);
		
		JButton btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAgregar(e);
			}
		});
		btnAgregar.setIcon(new ImageIcon(FrmbuscarProveedor.class.getResource("/iconos/add (2).png")));
		btnAgregar.setToolTipText("Agregar");
		btnAgregar.setBounds(651, 11, 56, 43);
		contentPane.add(btnAgregar);
		listado();
	}
	
	
	void listado1(String cod){
		DefaultTableModel contrata=(DefaultTableModel) tblProveedor.getModel();
		
		contrata.setRowCount(0);
		
		ArrayList<Provedor> lista=Proveedor.listarProvedorxCod(cod);
		
		for(Provedor abas:lista){
			Object row[]={
					abas.getCodPro(),abas.getRazon(),abas.getRuc(),abas.getDirec(),abas.getCelular(),abas.getPrecio(),abas.getCodPro(),abas.getMonEstado()
			};
			contrata.addRow(row);
		}
}
	void listado(){
		DefaultTableModel contrata=(DefaultTableModel) tblProveedor.getModel();
		
		contrata.setRowCount(0);
		ArrayList<Provedor> listartodo=Proveedor.listartodo();
		
		for(Provedor abas:listartodo){
			Object row[]={
					abas.getCodPro(),abas.getRazon(),abas.getRuc(),abas.getDirec(),abas.getCelular(),abas.getPrecio(),abas.getCodPro(),abas.getMonEstado()
			};
			contrata.addRow(row);
		}
}
	
	
	
	protected void keyPressedTxtCodigo(KeyEvent e) {
		listado();
		
		}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		if(txtCodigo.getText().trim().equals("")){
			mensaje("El codigo a buscar no puede estar vacio");
		}
		else{
		listado1(txtCodigo.getText());
	}
		
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		
		
		int posfila;
		posfila=tblProveedor.getSelectedRow();
		
		if(posfila==-1){
			mensaje("Porfavor seleccione una solicitud en la tabla");
		}
		
		else{
			
			String cod;
		cod=tblProveedor.getValueAt(posfila, 0).toString();
		FrmBuenapro.txtProve.setText(cod);
		
		dispose();

		
	}
	}}
