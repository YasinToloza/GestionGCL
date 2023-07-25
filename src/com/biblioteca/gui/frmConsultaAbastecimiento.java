package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import com.biblioteca.componente.JComboBoxDB;
import com.biblioteca.controlador.MySqlAbastecimientoDAO;
import com.biblioteca.entidad.Abastecimiento;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class frmConsultaAbastecimiento extends JFrame implements ActionListener, KeyListener, MouseListener {
	private MySqlAbastecimientoDAO AbastecimientoDAO=new MySqlAbastecimientoDAO();
	ArrayList<Abastecimiento> info=null;

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTable tblSolicitud;
	private JButton btnBuscar;
	private JButton btnModificarEstado;
	private JComboBox cboEstado;
	private JPanel panel;
	private JLabel lblConsultarSolicitudesPendientes;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConsultaAbastecimiento frame = new frmConsultaAbastecimiento();
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
	public frmConsultaAbastecimiento() {
		setResizable(false);
		setTitle("Solicitud Pendiente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Por Codigo");
		lblNewLabel_1.setBounds(10, 77, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(76, 74, 75, 20);
		contentPane.add(txtCodigo);
		
		btnBuscar = new JButton("");
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setIcon(new ImageIcon(frmConsultaAbastecimiento.class.getResource("/iconos/search (1).png")));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(161, 67, 86, 35);
		contentPane.add(btnBuscar);
		
		btnModificarEstado = new JButton("");
		btnModificarEstado.setIcon(new ImageIcon(frmConsultaAbastecimiento.class.getResource("/iconos/edit.png")));
		btnModificarEstado.setToolTipText("Modificar Estado");
		btnModificarEstado.addActionListener(this);
		btnModificarEstado.setBounds(384, 67, 95, 35);
		contentPane.add(btnModificarEstado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 742, 251);
		contentPane.add(scrollPane);
		
		tblSolicitud = new JTable();
		tblSolicitud.addMouseListener(this);
		tblSolicitud.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripcion", "Fecha", "Estado", "Tipo Solicitud", "Codigo Empleado"
			}
		));
		scrollPane.setViewportView(tblSolicitud);
		
		cboEstado = new JComboBoxDB("select E.cod_est, E.des_est\r\n"
				+ "				from tb_estado E\r\n"
				+ "				where cod_est in ('2','3')");
		cboEstado.setBounds(266, 73, 108, 22);
		contentPane.add(cboEstado);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 762, 38);
		contentPane.add(panel);
		
		lblConsultarSolicitudesPendientes = new JLabel("CONSULTAR SOLICITUDES DE ABASTECIMIENTO PENDIENTES");
		lblConsultarSolicitudesPendientes.setForeground(Color.WHITE);
		lblConsultarSolicitudesPendientes.setFont(new Font("Tahoma", Font.BOLD, 23));
		panel.add(lblConsultarSolicitudesPendientes);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(this);
		btnNewButton.setIcon(new ImageIcon(frmConsultaAbastecimiento.class.getResource("/iconos/maintenance.png")));
		btnNewButton.setToolTipText("Cerrar");
		btnNewButton.setBounds(493, 67, 95, 35);
		contentPane.add(btnNewButton);
		
		
		listarTodo();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
		if (e.getSource() == btnModificarEstado) {
			actionPerformedBtnModificarEstado(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		String codigo;
		codigo=txtCodigo.getText();
		if (codigo.trim().equals("")) {
			mensaje("Ingrese un codigo");
		}
		else	
		listaFiltrada(txtCodigo.getText());
	}
	
	protected void actionPerformedBtnModificarEstado(ActionEvent e) {
		int codEst, salida;
		String codSol;
		codSol=txtCodigo.getText();
		codEst=cboEstado.getSelectedIndex()+2;
		
		if (codSol.trim().equals("")) {
			mensaje("Seleccione un dato para actualizar");
            txtCodigo.requestFocus();
		}
		else {
		Abastecimiento bin=new Abastecimiento();
		bin.setCodigoSol(Integer.parseInt(codSol));
		bin.setCodigoEst(codEst);
		salida=AbastecimientoDAO.actualizaEstado(bin);
		if (salida>0) {
			mensaje("Estado actualizado");
			listarTodo();
		}
		else
			mensaje("Error en la actualizacion de la Solicitud");
	}}
	
	
	
	void listaFiltrada(String cod){
		DefaultTableModel modelo=(DefaultTableModel) tblSolicitud.getModel();	
		modelo.setRowCount(0);		
		ArrayList<Abastecimiento> lista=AbastecimientoDAO.listarForConsulta(Integer.parseInt(txtCodigo.getText()));		
		for(Abastecimiento abas:lista){			
		Object row[]={
				abas.getCodigoSol(),abas.getDescripSoli(),abas.getFecha(),abas.getNombreTipo(),abas.getNombreSolicitud(),abas.getCodemp()};
		modelo.addRow(row);
	}}
	
	void listarTodo(){
		DefaultTableModel abastecimiento=(DefaultTableModel) tblSolicitud.getModel();
		
		abastecimiento.setRowCount(0);
		ArrayList<Abastecimiento> data=AbastecimientoDAO.listarTodoConsultaSol();
		
		for(Abastecimiento a:data){
			Object row[]={
					a.getCodigoSol(),a.getDescripSoli(),a.getFecha(),a.getNombreTipo(),a.getNombreSolicitud(),a.getCodemp()};
			abastecimiento.addRow(row);
		}
	
}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == txtCodigo) {
			keyPressedTxtCodigo(e);
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyPressedTxtCodigo(KeyEvent e) {
		listarTodo();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblSolicitud) {
			mouseClickedTblSolicitud(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblSolicitud(MouseEvent e) {
		String codSol,codEst;
		int posFila;
		posFila=tblSolicitud.getSelectedRow();
		codSol=tblSolicitud.getValueAt(posFila, 0).toString();
		codEst=tblSolicitud.getValueAt(posFila, 3).toString();		
		txtCodigo.setText(codSol);
		cboEstado.setSelectedItem(codEst);
		
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		dispose();
	}
	}
