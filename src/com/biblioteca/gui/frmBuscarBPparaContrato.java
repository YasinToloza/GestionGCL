package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import com.biblioteca.controlador.MySqlSolicitudContrata;
import com.biblioteca.controlador.MysqlBuenaproDAO;
import com.biblioteca.entidad.Buenapro;
import com.biblioteca.entidad.SolicitudContrata;

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

public class frmBuscarBPparaContrato extends JFrame {

	private MysqlBuenaproDAO buenaProDAO=new MysqlBuenaproDAO();
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
					frmBuscarBPparaContrato frame = new frmBuscarBPparaContrato();
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
	public frmBuscarBPparaContrato() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("BUSCAR SOLICITUD APROBADA");
		setBounds(100, 100, 838, 396);
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
				"Codigo", "Asunto", "Referencia", "C.Sol.Contrata", "C.Prov", "C.Emp", "Fecha"
			}
		));
		tblSolicitud.getColumnModel().getColumn(0).setPreferredWidth(55);
		tblSolicitud.getColumnModel().getColumn(1).setPreferredWidth(168);
		tblSolicitud.getColumnModel().getColumn(2).setPreferredWidth(91);
		tblSolicitud.getColumnModel().getColumn(3).setPreferredWidth(92);
		scrollPane.setViewportView(tblSolicitud);
		
		btnAgregar = new JButton("");
		btnAgregar.setToolTipText("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAgregar(e);
			}
		});
		btnAgregar.setIcon(new ImageIcon(frmBuscarBPparaContrato.class.getResource("/iconos/add (2).png")));
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
		btnBuscar.setIcon(new ImageIcon(frmBuscarBPparaContrato.class.getResource("/iconos/search.png")));
		btnBuscar.setBounds(579, 11, 50, 43);
		contentPane.add(btnBuscar);
		
		listado();
		
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		
		int posfila;
		String cod,referencia,prov,asunto,fecha;
		posfila=tblSolicitud.getSelectedRow();
		
		
		if(posfila==-1){
			mensaje("Seleccione un solicitud de contratación de la tabla por favor!");
		}
		
		else{
		cod=tblSolicitud.getValueAt(posfila, 0).toString();
		referencia=tblSolicitud.getValueAt(posfila, 2).toString();
		prov=tblSolicitud.getValueAt(posfila, 4).toString();
		asunto=tblSolicitud.getValueAt(posfila, 1).toString();
		fecha=tblSolicitud.getValueAt(posfila, 6).toString();
		frmGenerarContrato.txtAsuntoBP.setText(asunto);
		frmGenerarContrato.txtReferenciaBP.setText(referencia);
		frmGenerarContrato.txtCodProv.setText(prov);
		frmGenerarContrato.txtFechaBP.setText(fecha);
		frmGenerarContrato.txtCodBuenPro.setText(cod);
		
		dispose();
		
	}}
	
	void listado1(String cod){
		DefaultTableModel contrata=(DefaultTableModel) tblSolicitud.getModel();
		
		contrata.setRowCount(0);
		ArrayList<Buenapro> data=buenaProDAO.listarxcritcontrato(cod);
		
		for(Buenapro bp:data){
			Object row[]={
					bp.getCodbuenapro(),bp.getAsunto(),bp.getReferencia(),bp.getCodsol(),bp.getCodprov(),bp.getCodemp(),bp.getFecha()
			};
		contrata.addRow(row);
	}}
		
		void listado(){
			DefaultTableModel contrata=(DefaultTableModel) tblSolicitud.getModel();
			
			contrata.setRowCount(0);
			ArrayList<Buenapro> data=buenaProDAO.listartodito();
			
			for(Buenapro bp:data){
				Object row[]={
						bp.getCodbuenapro(),bp.getAsunto(),bp.getReferencia(),bp.getCodsol(),bp.getCodprov(),bp.getCodemp(),bp.getFecha()
				};
				contrata.addRow(row);
			}
		}
		
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		
		if(txtCodigo.getText().trim().equals("")){
			mensaje("El codigo a buscar no puede estar vacio");
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

