package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MysqlBuenaproDAO;
import com.biblioteca.entidad.Buenapro;
import com.biblioteca.entidad.Objeto;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.entidad.empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmBuenapro extends JFrame {
	
	private MysqlBuenaproDAO BuenaproDAO= new MysqlBuenaproDAO();
	
	private JPanel contentPane;
	public static JTextField txtProve;
	public static JTextField txtSol;
	private JTextField txtAsunto;
	private JTextField txtCodigo;
	private JTextField txtFecha;
	private JTable tblBuenapro;
	private JTextField txtReferencia;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBuenapro frame = new FrmBuenapro();
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
	public FrmBuenapro() {
		setTitle("Expediente de BuenaPro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1004, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExpedienteBuenaPro = new JLabel("EXPEDIENTE BUENA PRO");
		lblExpedienteBuenaPro.setForeground(Color.BLACK);
		lblExpedienteBuenaPro.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblExpedienteBuenaPro.setBackground(Color.BLACK);
		lblExpedienteBuenaPro.setBounds(365, 11, 411, 33);
		contentPane.add(lblExpedienteBuenaPro);
		
		JLabel label = new JLabel("Codigo");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(33, 64, 94, 14);
		contentPane.add(label);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAsunto.setBounds(33, 89, 94, 14);
		contentPane.add(lblAsunto);
		
		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReferencia.setBounds(33, 120, 94, 14);
		contentPane.add(lblReferencia);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha.setBounds(30, 379, 94, 14);
		contentPane.add(lblFecha);
		
		JButton buttonprovedor = new JButton("BUSCAR");
		buttonprovedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedButton_1(e);
			}
		});
		buttonprovedor.setToolTipText("Buscar Abastecimiento");
		buttonprovedor.setBounds(21, 248, 77, 46);
		contentPane.add(buttonprovedor);
		
		JLabel lblCodigoProveedor = new JLabel("Codigo Proveedor");
		lblCodigoProveedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigoProveedor.setBounds(108, 252, 118, 14);
		contentPane.add(lblCodigoProveedor);
		
		JLabel lblCodigoSolContratacion = new JLabel("Codigo Sol Contratacion");
		lblCodigoSolContratacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigoSolContratacion.setBounds(105, 177, 150, 14);
		contentPane.add(lblCodigoSolContratacion);
		
		txtProve = new JTextField();
		txtProve.setEditable(false);
		txtProve.setColumns(10);
		txtProve.setBounds(108, 277, 86, 20);
		contentPane.add(txtProve);
		
		txtSol = new JTextField();
		txtSol.setEditable(false);
		txtSol.setColumns(10);
		txtSol.setBounds(105, 202, 86, 20);
		contentPane.add(txtSol);
		
		txtAsunto = new JTextField();
		txtAsunto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAsunto.setColumns(10);
		txtAsunto.setBackground(Color.WHITE);
		txtAsunto.setBounds(106, 89, 132, 21);
		contentPane.add(txtAsunto);
		
		txtReferencia = new JTextField();
		txtReferencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtReferencia.setColumns(10);
		txtReferencia.setBackground(Color.WHITE);
		txtReferencia.setBounds(106, 118, 132, 21);
		contentPane.add(txtReferencia);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(new Color(250, 250, 210));
		txtCodigo.setBounds(106, 62, 132, 21);
		contentPane.add(txtCodigo);
		
		txtFecha = new JTextField();
		txtFecha.setText((String) null);
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(92, 377, 86, 20);
		contentPane.add(txtFecha);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 55, 694, 401);
		contentPane.add(scrollPane);
		
		tblBuenapro = new JTable();
		tblBuenapro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTblBuenapro(arg0);
			}
		});
		tblBuenapro.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "ASUNTO", "REFERENCIA", "COD SOL CONTRATACION", "COD PROVEEDOR", "COD EMPLE", "FECHA"
			}
		));
		tblBuenapro.getColumnModel().getColumn(3).setPreferredWidth(144);
		tblBuenapro.getColumnModel().getColumn(4).setPreferredWidth(114);
		tblBuenapro.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblBuenapro);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnRegistrar(e);
			}
		});
		btnRegistrar.setBounds(18, 404, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(120, 404, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(18, 433, 89, 23);
		contentPane.add(btnActualizar);
		
		listado();
		txtFecha.setText(fechaActual());
		
		JButton buttonsolcon = new JButton("BUSCAR");
		buttonsolcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedButton_2(e);
			}
		});
		buttonsolcon.setToolTipText("Buscar Abastecimiento");
		buttonsolcon.setBounds(18, 174, 77, 46);
		contentPane.add(buttonsolcon);
		
		
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		
		
	String asunto,referencia,codsol,codccp,codProv,fecha;
		
	    asunto=txtAsunto.getText();
	    referencia=txtReferencia.getText();
	    codsol=txtSol.getText();
	    codProv=txtProve.getText();
		fecha=txtFecha.getText();
		
		if(asunto.trim().equals("")){
			mensaje("Campo Asunto es obligatorio");
			txtAsunto.requestFocus();}
		
		else if(asunto.trim().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{5,30}")==false){
			mensaje("Campo dirección requiere al menos 5 caracteres");
			txtAsunto.requestFocus();
		}
		else if(referencia.trim().equals("")){
				mensaje("campo Referencia es obligatorio");
				txtReferencia.requestFocus();
		}
		else if(referencia.trim().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{5,30}")==false){
				mensaje("Campo Referencia requiere al menos 5 caracteres");
				txtReferencia.requestFocus();
			
		}
		else if(codsol.trim().equals("")){
			mensaje("Elija una solicitud de abastecimiento,no puede estar vacia");
			
		}
		else if (codProv.trim().equals("")){
			mensaje("Elija una Solicitud CCP ,no puede estar vacio el campo");
		}
	
		else {
		
		Buenapro bean=new Buenapro();
		bean.setAsunto(asunto);
		bean.setReferencia(referencia);
		bean.setCodsol(Integer.parseInt(codsol));
		bean.setCodprov(Integer.parseInt(codProv));
		bean.setCodemp(1);
		bean.setFecha(fecha);

		int salida;
		salida=BuenaproDAO.save(bean);
		
		if(salida>0){
			mensaje("Expediente de buena pro registrada correctamente...");
			listado();
		}
		else{
			mensaje("Error en el registro de Expediente de buena pro");
		}
		
		}	}
	

	void listado(){
		ArrayList<Buenapro> data= BuenaproDAO.listAll();
		
		DefaultTableModel modelo=(DefaultTableModel) tblBuenapro.getModel();
		modelo.setRowCount(0);
		for(Buenapro Bue:data){
			Object row[]={
					Bue.getCodbuenapro(),Bue.getAsunto(),Bue.getReferencia(),Bue.getCodsol(),Bue.getCodprov(),Bue.getCodemp(),Bue.getFecha()
			};
			modelo.addRow(row);
		}
	
	}

	
	public String fechaActual(){
		Date fecha=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("YYYY/MM/dd");
		return formato.format(fecha);
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	protected void actionPerformedButton_1(ActionEvent e) {
		
		FrmbuscarProveedor frm = new FrmbuscarProveedor();
		frm.setVisible(true);	

	}
	protected void actionPerformedButton_2(ActionEvent e) {
		
		frmBuscarSolContrataBuenapro frm = new frmBuscarSolContrataBuenapro();
		frm.setVisible(true);
		
	}
	protected void mouseClickedTblBuenapro(MouseEvent arg0) {
		
		
int posFila;
		
		String  cod,asunto,referencia,cod_sol,cod_prov,fecha;
		
	    posFila=tblBuenapro.getSelectedRow();
	    
	    

	    
		cod=tblBuenapro.getValueAt(posFila, 0).toString();
		asunto=tblBuenapro.getValueAt(posFila, 1).toString();
		referencia=tblBuenapro.getValueAt(posFila, 2).toString();
		cod_sol=tblBuenapro.getValueAt(posFila, 3).toString();
		cod_prov=tblBuenapro.getValueAt(posFila, 4).toString();
		fecha=tblBuenapro.getValueAt(posFila, 6).toString();

		txtCodigo.setText(cod);
		txtAsunto.setText(asunto);
		txtReferencia.setText(referencia);
		txtSol.setText(cod_sol);	
		txtProve.setText(cod_prov);
		txtFecha.setText(fecha);

	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		
		
		
String codigo,asunto,referencia,codsol,codccp,codProv,fecha,monto;
		
        codigo=txtCodigo.getText();
	    asunto=txtAsunto.getText();
	    referencia=txtReferencia.getText();
	    codsol=txtSol.getText();

	    codProv=txtProve.getText();
		fecha=txtFecha.getText();
		if(asunto.trim().equals("")){
			mensaje("Campo Asunto es obligatorio");
			txtAsunto.requestFocus();}
		
		else if(asunto.trim().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{5,30}")==false){
			mensaje("Campo dirección requiere al menos 5 caracteres");
			txtAsunto.requestFocus();
		}
		else if(referencia.trim().equals("")){
				mensaje("campo Referencia es obligatorio");
				txtReferencia.requestFocus();
		}
		else if(referencia.trim().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{5,30}")==false){
				mensaje("Campo Referencia requiere al menos 5 caracteres");
				txtReferencia.requestFocus();
			
		}
		else if(codsol.trim().equals("")){
			mensaje("Elija una solicitud de abastecimiento,no puede estar vacia");
			
		}
		else if (codProv.trim().equals("")){
			mensaje("Elija una Solicitud CCP ,no puede estar vacio el campo");
		}
	
		else {
	
			
		Buenapro bean=new Buenapro();
		bean.setCodbuenapro(Integer.parseInt(codigo));
		bean.setAsunto(asunto);
		bean.setReferencia(referencia);
		bean.setCodsol(Integer.parseInt(codsol));
		bean.setCodprov(Integer.parseInt(codProv));
		bean.setCodemp(1);
		bean.setFecha(fecha);

		int salida;
		salida=BuenaproDAO.update(bean);
		
		if(salida>0){
			mensaje("Expediente de buena pro Actualizado correctamente...");
			listado();
		}
		else{
			mensaje("Error en la Actualizacion");
		}
		
		}}
	
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		
		int boton;
		boton=JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este registro?","Sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		
		if(boton==0){
			int cod,salida;
			cod=Integer.parseInt(txtCodigo.getText());
			salida=BuenaproDAO.delete(cod);
			
			if(salida>0){
				mensaje("eliminada correctamente");
			}
			else mensaje("Error en la eliminación");
			
		}
		listado();
		
	}
}
