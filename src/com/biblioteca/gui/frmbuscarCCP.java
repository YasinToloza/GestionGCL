package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlCCPDAO;
import com.biblioteca.entidad.CCP;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmbuscarCCP extends JFrame {

	private MySqlCCPDAO ccpDAO=new MySqlCCPDAO();
	private JPanel contentPane;
	private JTable tblSolicitud;
	private JTextField txtCodigo;
	private JButton btnAgregar;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmbuscarCCP frame = new frmbuscarCCP();
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
	public frmbuscarCCP() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("BUSCAR CCP");
		setBounds(100, 100, 806, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 25, 56, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 770, 281);
		contentPane.add(scrollPane);
		
		tblSolicitud = new JTable();
		tblSolicitud.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripci\u00F3n", "Monto", "C.Emp", "C.Estado", "Fecha"
			}
		));
		tblSolicitud.getColumnModel().getColumn(0).setPreferredWidth(55);
		tblSolicitud.getColumnModel().getColumn(1).setPreferredWidth(168);
		tblSolicitud.getColumnModel().getColumn(2).setPreferredWidth(91);
		tblSolicitud.getColumnModel().getColumn(3).setPreferredWidth(71);
		scrollPane.setViewportView(tblSolicitud);
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAgregar(e);
			}
		});
		btnAgregar.setIcon(new ImageIcon(frmbuscarCCP.class.getResource("/iconos/add (2).png")));
		btnAgregar.setBounds(651, 11, 56, 43);
		contentPane.add(btnAgregar);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressedTxtCodigo(e);
			}
		});
		txtCodigo.setBounds(76, 23, 245, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnBuscar = new JButton("");
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscar(e);
			}
		});
		btnBuscar.setIcon(new ImageIcon(frmbuscarCCP.class.getResource("/iconos/search.png")));
		btnBuscar.setBounds(579, 11, 50, 43);
		contentPane.add(btnBuscar);
		
		listado();
		
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		
		int posfila;
		String cod;
		posfila=tblSolicitud.getSelectedRow();
		if(posfila==-1){
			mensaje("Por favor seleccione un Certificado de Credito Presupuestario");
		}
		else{
		cod=tblSolicitud.getValueAt(posfila, 0).toString();
		frmMantenimientoSC.txtcodProv.setText(cod);
		
		dispose();
		
	}}
	
	void listado1(String cod){
		DefaultTableModel modelo=(DefaultTableModel) tblSolicitud.getModel();
		
		modelo.setRowCount(0);
		
		ArrayList<CCP> lista=ccpDAO.listarxcodsol(cod);
		
		for(CCP ccp:lista){
			
		Object row[]={
			ccp.getCodigoccp(),ccp.getDesccp(),ccp.getMontoccp(),ccp.getEmpleado(),ccp.getEstado(),ccp.getFecha()
		};
		modelo.addRow(row);
	}}
		
		void listado(){
			DefaultTableModel contrata=(DefaultTableModel) tblSolicitud.getModel();
			
			contrata.setRowCount(0);
			ArrayList<CCP> lista=ccpDAO.listartodo();
			
			for(CCP ccp:lista){
				Object row[]={
						ccp.getCodigoccp(),ccp.getDesccp(),ccp.getMontoccp(),ccp.getEmpleado(),ccp.getEstado(),ccp.getFecha()
				};
				contrata.addRow(row);
			}
		}
		
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		
		
		if(txtCodigo.getText().trim().equals("")){
			mensaje("El codigo no puede estar vacio para buscar");
		}
		else{
		listado1(txtCodigo.getText());
	}}
	protected void keyPressedTxtCodigo(KeyEvent e) {
		
		listado();
		}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	
	}

