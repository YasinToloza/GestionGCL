package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlSolicitudContrata;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.interfaces.SolicitudContrataDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ConsultaSolContratacion extends JFrame {

	
	private MySqlSolicitudContrata solcontrata=new MySqlSolicitudContrata();
	private JPanel contentPane;
	private JTextField txtCodigoSol;
	private JTable tblConsultaContrata;
	private JButton btnBuscar;
	private JButton btnAprobar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaSolContratacion frame = new ConsultaSolContratacion();
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
	public ConsultaSolContratacion() {
		setResizable(false);
		setTitle("SOLICITUDES DE CONTRATACI\u00D3N");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 951, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 935, 40);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Consulta de Solicitudes de Contrataci\u00F3n");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCdigo.setForeground(Color.BLACK);
		lblCdigo.setBounds(10, 56, 64, 26);
		contentPane.add(lblCdigo);
		
		txtCodigoSol = new JTextField();
		txtCodigoSol.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				keyPressedTxtCodigoSol(arg0);
			}
		});
		txtCodigoSol.setBounds(86, 61, 120, 20);
		contentPane.add(txtCodigoSol);
		txtCodigoSol.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 915, 269);
		contentPane.add(scrollPane);
		
		tblConsultaContrata = new JTable();
		tblConsultaContrata.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripci\u00F3n", "Estado", "C.Sol.Abastecimiento", "Cod.CCP", "Fecha", "C.Empleado", "T.Contrato"
			}
		));
		tblConsultaContrata.getColumnModel().getColumn(0).setPreferredWidth(48);
		tblConsultaContrata.getColumnModel().getColumn(1).setPreferredWidth(111);
		tblConsultaContrata.getColumnModel().getColumn(3).setPreferredWidth(115);
		tblConsultaContrata.getColumnModel().getColumn(7).setPreferredWidth(92);
		scrollPane.setViewportView(tblConsultaContrata);
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnBuscar(arg0);
			}
		});
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setIcon(new ImageIcon(ConsultaSolContratacion.class.getResource("/iconos/search.png")));
		btnBuscar.setBounds(232, 51, 64, 40);
		contentPane.add(btnBuscar);
		
		btnAprobar = new JButton("");
		btnAprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAprobar(e);
			}
		});
		btnAprobar.setToolTipText("Aprobar Solicitud");
		btnAprobar.setIcon(new ImageIcon(ConsultaSolContratacion.class.getResource("/iconos/Aprobar.png")));
		btnAprobar.setBounds(323, 51, 64, 40);
		contentPane.add(btnAprobar);
		
		listado();
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		
		if(txtCodigoSol.getText().trim().equals("")){
			mensaje("campo codigo no puede estar vacio");
		}
		else{
		listado1(txtCodigoSol.getText());}
	}
	protected void actionPerformedBtnAprobar(ActionEvent e) {
		
		int posfila,salida;
		int boton;
		posfila=tblConsultaContrata.getSelectedRow();
		if(posfila==-1){
			mensaje("Por favor seleccione una solicitud de contratación para aprobar");
		}
		
		else{
			String cod;
			cod=tblConsultaContrata.getValueAt(posfila, 0).toString();
		boton=JOptionPane.showConfirmDialog(null, "Desea aprobar la solicitud de contratación con codigo "+cod,"Sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(boton==0){
			int boton2=JOptionPane.showConfirmDialog(null, "Esta seguro de aprobar esta solicitud? Una vez aprobada "
					+ "no se puede cambiar","ADVERTENCIA", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		if(boton2==0){
			SolicitudContrata bean=new SolicitudContrata();
			
			bean.setEstado(2);
			bean.setCodigo(Integer.parseInt(cod));
			
			salida=solcontrata.actualizarestado((bean));
			
			if(salida>0){
				mensaje("Solicitud de contratación Aprobada!");
				listado();
			}
			else{
				mensaje("Error en la aprobación");
			}
			
		}
		else{
			
		}
		}
		
		else{
			
		}}
	}
	protected void keyPressedTxtCodigoSol(KeyEvent arg0) {
		listado();
	}
	
	void listado1(String cod){
		DefaultTableModel modelo=(DefaultTableModel) tblConsultaContrata.getModel();
		
		modelo.setRowCount(0);
		
		ArrayList<SolicitudContrata> lista=solcontrata.listarxestado1(cod);
		
		for(SolicitudContrata abas:lista){
			
		Object row[]={
				abas.getCodigo(),abas.getDescripcion(),abas.getEstado(),abas.getCod_sol(),abas.getCod_ccp(),abas.getFecha(),abas.getCodEmp(),abas.getTipocontrato()
		};
		modelo.addRow(row);}
	}
	
	
	void listado(){
		DefaultTableModel contrata=(DefaultTableModel) tblConsultaContrata.getModel();
		
		contrata.setRowCount(0);
		ArrayList<SolicitudContrata> data=solcontrata.listaparaaprobar();
		
		for(SolicitudContrata abas:data){
			Object row[]={
					abas.getCodigo(),abas.getDescripcion(),abas.getEstado(),abas.getCod_sol(),abas.getCod_ccp(),abas.getFecha(),abas.getCodEmp(),abas.getTipocontrato()
			};
			contrata.addRow(row);
		}
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	
}
