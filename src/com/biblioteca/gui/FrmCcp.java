package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlCCPDAO;
import com.biblioteca.entidad.CCP;
import com.biblioteca.interfaces.CCPDAO;


import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmCcp extends JFrame {

	private MySqlCCPDAO CCPDAO= new MySqlCCPDAO();
	
	private JPanel contentPane;
	private JTextField txtFecha;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtMonto;
	private JTable tblCCP;
   public static int monto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCcp frame = new FrmCcp();
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
	public FrmCcp() {
		setTitle("Certificacion de CCP");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1027, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreditoPresupuestario = new JLabel("\r\nCERTIFIACI\u00D3N DE CREDITO PRESUPUESTARIO");
		lblCreditoPresupuestario.setForeground(Color.BLACK);
		lblCreditoPresupuestario.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCreditoPresupuestario.setBackground(Color.BLACK);
		lblCreditoPresupuestario.setBounds(245, 11, 531, 33);
		contentPane.add(lblCreditoPresupuestario);
		
		JLabel label = new JLabel("Codigo");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 55, 94, 14);
		contentPane.add(label);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescripcion.setBounds(10, 102, 94, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMonto.setBounds(10, 148, 62, 14);
		contentPane.add(lblMonto);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha.setBounds(10, 226, 62, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setText((String) null);
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(69, 224, 86, 20);
		contentPane.add(txtFecha);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(new Color(255, 239, 213));
		txtCodigo.setBounds(10, 75, 86, 20);
		contentPane.add(txtCodigo);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(10, 127, 298, 20);
		contentPane.add(txtDescripcion);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(10, 173, 103, 20);
		contentPane.add(txtMonto);
		txtMonto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(327, 55, 674, 376);
		contentPane.add(scrollPane);
		
		tblCCP = new JTable();
		tblCCP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTblCCP(arg0);
			}
		});
		tblCCP.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "DESCRIPCI\u00D6N", "MONTO", "COD EMPLEADO", "ESTADO", "FECHA"
			}
		));
		tblCCP.getColumnModel().getColumn(0).setPreferredWidth(73);
		tblCCP.getColumnModel().getColumn(1).setPreferredWidth(201);
		tblCCP.getColumnModel().getColumn(3).setPreferredWidth(98);
		tblCCP.getColumnModel().getColumn(4).setPreferredWidth(83);
		tblCCP.getColumnModel().getColumn(5).setPreferredWidth(107);
		tblCCP.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCCP);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNewButton(arg0);
			}
		});
		btnNewButton.setBounds(10, 311, 89, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNewButton_1(arg0);
			}
		});
		btnNewButton_1.setBounds(117, 311, 89, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnActualizar(arg0);
			}
		});
		btnActualizar.setBounds(10, 365, 89, 38);
		contentPane.add(btnActualizar);
		
		listado();
		txtFecha.setText(fechaActual());
		
	}
	protected void actionPerformedBtnNewButton(ActionEvent arg0) {
		
		
	String descripcion,monto,fecha;
		
		descripcion=txtDescripcion.getText();
		monto=txtMonto.getText();
		fecha=txtFecha.getText();
		
		if(descripcion.trim().equals("")){
			mensaje("Campo descripción es obligatorio");
			txtDescripcion.requestFocus();}
		
		else if(descripcion.trim().matches("^.{3,50}")==false){
			mensaje("Campo descripción requiere al menos 5 caracteres");
			txtDescripcion.requestFocus();
		}
		else if(monto.trim().equals("")){
			mensaje("campo monto es obligatorio");
			txtMonto.requestFocus();
		}
		else if(monto.trim().matches("[0-9]{4,6}")==false){
			mensaje("Campo monto min:1000");
			txtMonto.requestFocus();
		}
		else{
		
		CCP bean=new CCP();
		bean.setDesccp(descripcion);
		bean.setMontoccp(Double.parseDouble(monto));
		bean.setEmpleado(1);
		bean.setEstado(1);
		bean.setFecha(fecha);

		int salida;
		
		salida=CCPDAO.save(bean);
		
		if(salida>0){
			mensaje("certificación de credito presupuestario registrada correctamente...");
			listado();
		}
		else{
			mensaje("Error en el registro de certificación de credito presupuestario...");
		}
		}
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}

	void listado(){
		ArrayList<CCP> data= CCPDAO.listAll();
		
		DefaultTableModel modelo=(DefaultTableModel) tblCCP.getModel();
		
		modelo.setRowCount(0);
		
		for(CCP c:data){
			Object row[]={
					c.getCodigoccp(),c.getDesccp(),c.getMontoccp(),c.getEmpleado(),c.getNombreEstado(),c.getFecha(),
			};
			modelo.addRow(row);
		}
}
	public String fechaActual(){
		Date fecha=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("YYYY/MM/dd");
		return formato.format(fecha);
}
	protected void mouseClickedTblCCP(MouseEvent arg0) {
		
		
	int posFila;
		
		String  cod,des,monto,fecha;
	
	    posFila=tblCCP.getSelectedRow();
	    
		cod=tblCCP.getValueAt(posFila, 0).toString();
		des=tblCCP.getValueAt(posFila, 1).toString();
		monto=tblCCP.getValueAt(posFila, 2).toString();
		fecha=tblCCP.getValueAt(posFila, 5).toString();

		
		txtCodigo.setText(cod);
		txtDescripcion.setText(des);
		txtMonto .setText(monto);
		txtFecha.setText(fecha);
        
	}
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		
		
String cod,descripcion,monto,fecha;
		
		descripcion=txtDescripcion.getText();
		monto=txtMonto.getText();
		fecha=txtFecha.getText();
		cod=txtCodigo.getText();
		
		if(descripcion.trim().equals("")){
			mensaje("Campo descripción es obligatorio");
			txtDescripcion.requestFocus();}
		
		else if(descripcion.trim().matches("^.{3,50}")==false){
			mensaje("Campo descripción requiere al menos 5 caracteres");
			txtDescripcion.requestFocus();
		}
		else if(monto.trim().equals("")){
			mensaje("campo monto es obligatorio");
			txtMonto.requestFocus();
		}
		else if(monto.trim().matches("[0-9]{4,7}")==false){
			mensaje("Campo monto error");
			txtMonto.requestFocus();
		}
		else{
		
		CCP bean=new CCP();
		bean.setCodigoccp(Integer.parseInt(cod));
		bean.setDesccp(descripcion);
		bean.setMontoccp(Double.parseDouble(monto));
		bean.setEmpleado(1);
		bean.setFecha(fecha);

		int salida;
		
		salida=CCPDAO.update(bean);
		
		if(salida>0){
			mensaje("certificación de credito presupuestario Actualizado correctamente...");
			listado();
		}
		else{
			mensaje("Error en la Actualizacion");
		}
		}
	}
	protected void actionPerformedBtnNewButton_1(ActionEvent arg0) {
		
		
		int boton;
		boton=JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este registro?","Sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		
		if(boton==0){
			int cod,salida;
			cod=Integer.parseInt(txtCodigo.getText());
			salida=CCPDAO.delete(cod);
			
			if(salida>0){
				mensaje("certificación de credito presupuestario  eliminada correctamente");
			}
			else mensaje("Error en la eliminación");
			
		}
		listado();
		

		
	}
	

}
