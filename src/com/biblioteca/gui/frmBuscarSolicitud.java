package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlAbastecimientoDAO;
import com.biblioteca.controlador.MysqlProveedorDAO;
import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.entidad.Provedor;

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

public class frmBuscarSolicitud extends JFrame {

	private MySqlAbastecimientoDAO abastecimientoDAO=new MySqlAbastecimientoDAO();
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
					frmBuscarSolicitud frame = new frmBuscarSolicitud();
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
	public frmBuscarSolicitud() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("BUSCAR SOLICITUD APROBADA");
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
				"Codigo", "Solicitud", "Fecha", "Estado", "Tipo Solicitud", "Cod.Empleado"
			}
		));
		tblSolicitud.getColumnModel().getColumn(0).setPreferredWidth(55);
		tblSolicitud.getColumnModel().getColumn(1).setPreferredWidth(168);
		tblSolicitud.getColumnModel().getColumn(2).setPreferredWidth(91);
		tblSolicitud.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblSolicitud.getColumnModel().getColumn(5).setPreferredWidth(102);
		scrollPane.setViewportView(tblSolicitud);
		
		btnAgregar = new JButton("");
		btnAgregar.setToolTipText("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAgregar(e);
			}
		});
		btnAgregar.setIcon(new ImageIcon(frmBuscarSolicitud.class.getResource("/iconos/add (2).png")));
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
		btnBuscar.setIcon(new ImageIcon(frmBuscarSolicitud.class.getResource("/iconos/search.png")));
		btnBuscar.setBounds(579, 11, 50, 43);
		contentPane.add(btnBuscar);
		
		listado();
		
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		
		int posfila;
		posfila=tblSolicitud.getSelectedRow();
		
		if(posfila==-1){
			mensaje("Porfavor seleccione una solicitud en la tabla");
		}
		
		else{
			
			String cod;
		cod=tblSolicitud.getValueAt(posfila, 0).toString();
		frmMantenimientoSC.txtCodAbas.setText(cod);
		
		dispose();
		}
	}
	
	void listado1(String cod){
		DefaultTableModel modelo=(DefaultTableModel) tblSolicitud.getModel();
		
		modelo.setRowCount(0);
		
		ArrayList<Abastecimiento> lista=abastecimientoDAO.listarxcodsol(cod);
		
		for(Abastecimiento abas:lista){
			
		Object row[]={
				abas.getCodigoSol(),abas.getDescripSoli(),abas.getFecha(),abas.getCodigoEst(),abas.getCodigoTipSol(),abas.getCodemp()
		};
		modelo.addRow(row);
	}}
		
		void listado(){
			DefaultTableModel abastecimiento=(DefaultTableModel) tblSolicitud.getModel();
			
			abastecimiento.setRowCount(0);
			ArrayList<Abastecimiento> data=abastecimientoDAO.listartodo();
			
			for(Abastecimiento a:data){
				Object row[]={
						a.getCodigoSol(),a.getDescripSoli(),a.getFecha(),a.getCodigoEst(),a.getCodigoTipSol(),a.getCodemp()
				};
				abastecimiento.addRow(row);
			}
		}
		
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		
		if(txtCodigo.getText().trim().equals("")){
			mensaje("Por favor ingrese un codigo para buscar");
		}
		else{
		listado1(txtCodigo.getText());}
	}
	protected void keyPressedTxtCodigo(KeyEvent e) {
		
		listado();
		}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	
	}

