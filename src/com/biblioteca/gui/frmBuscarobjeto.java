package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.entidad.Objeto;
import com.biblioteca.controlador.MySqlObjetoDAO;



import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;

public class frmBuscarobjeto extends JFrame {
	

	private MySqlObjetoDAO Objeto=new MySqlObjetoDAO();
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTable tblObjeto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBuscarobjeto frame = new frmBuscarobjeto();
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
	public frmBuscarobjeto() {
		setResizable(false);
		setTitle("Buscar Objeto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 770, 281);
		contentPane.add(scrollPane);
		
		tblObjeto = new JTable();
		tblObjeto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "MONBRE", "DESCRIPCION"
			}
		));
		tblObjeto.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblObjeto.getColumnModel().getColumn(2).setPreferredWidth(111);
		scrollPane.setViewportView(tblObjeto);
		
		JLabel label = new JLabel("Codigo");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 25, 56, 14);
		contentPane.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(76, 23, 245, 20);
		contentPane.add(txtCodigo);
		
		JButton btnbuscar = new JButton("");
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnbuscar(arg0);
			}
		});
		btnbuscar.setIcon(new ImageIcon(frmBuscarobjeto.class.getResource("/iconos/search.png")));
		btnbuscar.setToolTipText("Buscar");
		btnbuscar.setBounds(579, 11, 50, 43);
		contentPane.add(btnbuscar);
		
		JButton btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAgregar(e);
			}
		});
		btnAgregar.setIcon(new ImageIcon(frmBuscarobjeto.class.getResource("/iconos/add (2).png")));
		btnAgregar.setToolTipText("Agregar");
		btnAgregar.setBounds(651, 11, 56, 43);
		contentPane.add(btnAgregar);
		listado();
	}
	void listado1(String cod){
		DefaultTableModel contrata=(DefaultTableModel) tblObjeto.getModel();
		
		contrata.setRowCount(0);
		
		ArrayList<Objeto> lista=Objeto.listarxcod(cod);
		
		for(Objeto abas:lista){
			Object row[]={
					abas.getCodigoObj(),abas.getNomObj(),abas.getDescObj()
			};
			contrata.addRow(row);
		}
}
	void listado(){
		DefaultTableModel contrata=(DefaultTableModel) tblObjeto.getModel();
		
		contrata.setRowCount(0);
		ArrayList<Objeto> listartodo=Objeto.listartodo();
		
		for(Objeto abas:listartodo){
			Object row[]={
					abas.getCodigoObj(),abas.getNomObj(),abas.getDescObj()
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
	
	protected void actionPerformedBtnbuscar(ActionEvent arg0) {
		
		
		if(txtCodigo.getText().trim().equals("")){
			mensaje("Por favor ingrese un codigo para buscar");
		}
		else{
		listado1(txtCodigo.getText());}
		
		
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		
		int posfila;
		posfila=tblObjeto.getSelectedRow();
		
		if(posfila==-1){
			mensaje("Porfavor seleccione una solicitud en la tabla");
		}
		
		else{
			
			String cod;
		cod=tblObjeto.getValueAt(posfila, 0).toString();
		guiProvedor.txtObjeto.setText(cod);
		
		dispose();
	}
	}
}
