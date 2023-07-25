package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

import com.biblioteca.componente.JComboBoxDB;
import com.biblioteca.controlador.MySqlAbastecimientoDAO;
import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.interfaces.AbastecimientoDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmAbastecimiento extends JFrame implements ActionListener {
	private MySqlAbastecimientoDAO abastecimientoDAO=new MySqlAbastecimientoDAO();

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTable tblSolicitud;
	private JTextField txtCodigo;
	private JButton btnIngresar;
	private JTextField txtFecha;
	private JComboBox cboSolicitud;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAbastecimiento frame = new frmAbastecimiento();
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
	public frmAbastecimiento() {
		setResizable(false);
		setTitle("Solicitud de Abastecimiento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descripcion");
		lblNewLabel.setBounds(18, 122, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(18, 178, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(18, 147, 251, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(279, 49, 610, 348);
		contentPane.add(scrollPane);
		
		tblSolicitud = new JTable();
		tblSolicitud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTblSolicitud(arg0);
			}
		});
		tblSolicitud.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripcion", "Fecha", "Estado", "Tipo Solicitud", "Cod.Empleado"
			}
		));
		tblSolicitud.getColumnModel().getColumn(0).setPreferredWidth(89);
		tblSolicitud.getColumnModel().getColumn(1).setPreferredWidth(216);
		tblSolicitud.getColumnModel().getColumn(2).setPreferredWidth(113);
		tblSolicitud.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblSolicitud.getColumnModel().getColumn(4).setPreferredWidth(88);
		tblSolicitud.getColumnModel().getColumn(5).setPreferredWidth(103);
		scrollPane.setViewportView(tblSolicitud);
		
		btnIngresar = new JButton("");
		btnIngresar.setToolTipText("Ingresar");
		btnIngresar.setIcon(new ImageIcon(frmAbastecimiento.class.getResource("/iconos/add (2).png")));
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(28, 297, 96, 40);
		contentPane.add(btnIngresar);
		
		JLabel lblNewLabel_4 = new JLabel("Codigo");
		lblNewLabel_4.setBounds(18, 56, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtCodigo.setEnabled(false);
		txtCodigo.setBackground(Color.YELLOW);
		txtCodigo.setBounds(18, 81, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnActualizar = new JButton("");
		btnActualizar.setToolTipText("Actualizar");
		btnActualizar.setIcon(new ImageIcon(frmAbastecimiento.class.getResource("/iconos/edit.png")));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(152, 297, 96, 40);
		contentPane.add(btnActualizar);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de Solicitud");
		lblNewLabel_2.setBounds(18, 234, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(14, 203, 167, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		cboSolicitud = new JComboBoxDB("select * from tb_Tipo_Solicitud ");
		cboSolicitud.setBounds(18, 259, 111, 22);
		contentPane.add(cboSolicitud);
		
		btnEliminar = new JButton("");
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setIcon(new ImageIcon(frmAbastecimiento.class.getResource("/iconos/delete (1).png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(152, 348, 96, 40);
		contentPane.add(btnEliminar);
		
		txtFecha.setText(fechaActual());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 899, 38);
		contentPane.add(panel);
		
		JLabel lblRegistrarSolicitudDe = new JLabel("REGISTRAR SOLICITUD DE ABASTECIMIENTO");
		lblRegistrarSolicitudDe.setForeground(Color.WHITE);
		lblRegistrarSolicitudDe.setFont(new Font("Tahoma", Font.BOLD, 23));
		panel.add(lblRegistrarSolicitudDe);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(this);
		btnNewButton.setIcon(new ImageIcon(frmAbastecimiento.class.getResource("/iconos/maintenance.png")));
		btnNewButton.setToolTipText("Cerrar");
		btnNewButton.setBounds(28, 348, 96, 40);
		contentPane.add(btnNewButton);
		listado();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		String descripcion, estado, fecha;
		descripcion=txtDescripcion.getText();
		fecha=txtFecha.getText();
		estado=cboSolicitud.getSelectedItem().toString();
		
		if(descripcion.trim().equals("")) {
			mensaje("Campo Descripción es obligatorio...");
			txtDescripcion.requestFocus();
		}
		else{
			
		
		Abastecimiento bean=new Abastecimiento();
		bean.setDescripSoli(descripcion);
		bean.setFecha(fecha);
		
		String aba[]=estado.split("-");
		
		bean.setCodigoTipSol(Integer.parseInt(aba[0]));
		bean.setCodigoEst(1);
		bean.setCodemp(1);
		int salida;
		salida=abastecimientoDAO.save(bean);
		if(salida>0) {
			mensaje("Solicitud Registrada");
			listado();
			limpiar();
		}
		else 
			mensaje("Error al regisrar la solicitud");
		}
	}
	
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		
		String  descripcion, estado, fecha, codigo;
		codigo=txtCodigo.getText();
		descripcion=txtDescripcion.getText();
		fecha=txtFecha.getText();
		
		if (codigo.trim().equals("")) {
			mensaje("prueba");
		}
		
		else if (descripcion.trim().equals("")) {
			mensaje("Campo Descripcion es obligatorio...");
			txtDescripcion.requestFocus();
		}
		
		else {
	    estado=cboSolicitud.getSelectedItem().toString();
		Abastecimiento bean=new Abastecimiento();
		bean.setCodigoSol(Integer.parseInt(codigo));
		bean.setDescripSoli(descripcion);
		bean.setFecha(fecha);
		
		String aba[]=estado.split("-");
		
		bean.setCodigoTipSol(Integer.parseInt(aba[0]));
		bean.setCodemp(1);
		int salida;
		salida=abastecimientoDAO.update(bean);
		
		if(salida>0) {
			mensaje("Solicitud Actualizada");
			listado();
			limpiar();
		}
		else 
			mensaje("Error en la actualizacion de la solicitud");
	}}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		String cod;
		cod=txtCodigo.getText();
		
		if(cod.trim().equals("")) {
			mensaje("Seleccione un dato para eliminar");
		}
		else {
		int boton;
		boton=JOptionPane.showConfirmDialog(null, "Seguro de eliminar?","Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(boton==0) {
			int salida;
			salida= abastecimientoDAO.delete(Integer.parseInt(cod));
			if(salida>0) {
				mensaje("Solicitud eliminada");
				listado();
			}
			else 
				mensaje("Error en la eliminacion de la solicitud");
			
		}		
	}}
	
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	
	public static String fechaActual() {	
		Date fecha=new Date();
		SimpleDateFormat formatofecha= new SimpleDateFormat("YYYY/MM/dd");
		return formatofecha.format(fecha);
	}
	
	void listado(){
		DefaultTableModel abastecimiento=(DefaultTableModel) tblSolicitud.getModel();
		
		abastecimiento.setRowCount(0);
		ArrayList<Abastecimiento> data=abastecimientoDAO.listAll();
		
		for(Abastecimiento a:data){
			Object row[]={
					a.getCodigoSol(),a.getDescripSoli(),a.getFecha(),a.getNombreTipo(),a.getNombreSolicitud(),a.getCodemp()
			};
			abastecimiento.addRow(row);
		}
	}
	
	void limpiar() {
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtCodigo.requestFocus();
    
}
	
	
	protected void mouseClickedTblSolicitud(MouseEvent arg0) {
		
		int posFila;	
		String cod,des,fecha,tiposol;	
		posFila=tblSolicitud.getSelectedRow();	
		cod=tblSolicitud.getValueAt(posFila, 0).toString();
		des=tblSolicitud.getValueAt(posFila, 1).toString();
		fecha=tblSolicitud.getValueAt(posFila, 2).toString();
		tiposol=tblSolicitud.getValueAt(posFila, 4).toString();	
		txtCodigo.setText(cod);
		txtDescripcion.setText(des);
		txtFecha.setText(fecha);
		cboSolicitud.setSelectedItem(tiposol);
		
		
		
		
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		dispose();
	}
}
