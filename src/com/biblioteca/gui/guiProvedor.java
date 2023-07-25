package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MysqlProveedorDAO;
import com.biblioteca.entidad.Provedor;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.interfaces.ProveedorDAO;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class guiProvedor extends JFrame {
	private MysqlProveedorDAO ProveedorDAO= new MysqlProveedorDAO();
	private JPanel contentPane;
	private JTextField txtPrecio;
	public static JTextField txtObjeto;
	private JTextField txtCel;
	private JTextField txtDir;
	private JTextField txtRuc;
	private JTextField txtRazon;
	private JTextField txtcodigo;
	private JTable tblProveedorl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiProvedor frame = new guiProvedor();
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
	public guiProvedor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 993, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 977, 431);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Razon social");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 86, 94, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Ruc");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 118, 94, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Direccion");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 149, 94, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Celular");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(10, 181, 94, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Codigo Objeto");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(97, 242, 94, 14);
		panel.add(label_4);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnRegistrar(arg0);
			}
		});
		btnRegistrar.setBounds(10, 323, 94, 33);
		panel.add(btnRegistrar);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPrecio.setColumns(10);
		txtPrecio.setBackground(Color.WHITE);
		txtPrecio.setBounds(114, 210, 132, 21);
		panel.add(txtPrecio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 55, 694, 365);
		panel.add(scrollPane);
		
		tblProveedorl = new JTable();
		tblProveedorl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTblProveedorl(arg0);
			}
		});
		tblProveedorl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "RAZON SOCIAL", "RUC", "DIRRECCION", "CELULAR", "PRECIO", "OBJETO", "ESTADO"
			}
		));
		tblProveedorl.getColumnModel().getColumn(1).setPreferredWidth(96);
		tblProveedorl.getColumnModel().getColumn(3).setPreferredWidth(111);
		tblProveedorl.getColumnModel().getColumn(4).setPreferredWidth(114);
		tblProveedorl.getColumnModel().getColumn(5).setPreferredWidth(83);
		tblProveedorl.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblProveedorl);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnActualizar(arg0);
			}
		});
		btnEliminar.setBounds(10, 368, 94, 33);
		panel.add(btnEliminar);
		
		JButton btnModificar = new JButton("Actualizar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnModificar(arg0);
			}
		});
		btnModificar.setBounds(135, 323, 94, 33);
		panel.add(btnModificar);
		
		JLabel label_5 = new JLabel("MANTENIMIENTO PROVEEDOR");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 22));
		label_5.setBackground(Color.BLACK);
		label_5.setBounds(277, 11, 411, 33);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Codigo");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(10, 55, 94, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Precio");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(10, 213, 94, 14);
		panel.add(label_7);
		
		JButton btnbuscarCod = new JButton("BUSCAR");
		btnbuscarCod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnbuscarCod(arg0);
			}
		});
		btnbuscarCod.setToolTipText("Buscar Abastecimiento");
		btnbuscarCod.setBounds(10, 242, 77, 46);
		panel.add(btnbuscarCod);
		
		txtObjeto = new JTextField();
		txtObjeto.setEditable(false);
		txtObjeto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtObjeto.setColumns(10);
		txtObjeto.setBackground(Color.WHITE);
		txtObjeto.setBounds(97, 267, 132, 21);
		panel.add(txtObjeto);
		
		txtCel = new JTextField();
		txtCel.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCel.setColumns(10);
		txtCel.setBackground(Color.WHITE);
		txtCel.setBounds(114, 178, 132, 21);
		panel.add(txtCel);
		
		txtDir = new JTextField();
		txtDir.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDir.setColumns(10);
		txtDir.setBackground(Color.WHITE);
		txtDir.setBounds(114, 146, 132, 21);
		panel.add(txtDir);
		
		txtRuc = new JTextField();
		txtRuc.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtRuc.setColumns(10);
		txtRuc.setBackground(Color.WHITE);
		txtRuc.setBounds(114, 115, 132, 21);
		panel.add(txtRuc);
		
		txtRazon = new JTextField();
		txtRazon.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtRazon.setColumns(10);
		txtRazon.setBackground(Color.WHITE);
		txtRazon.setBounds(114, 83, 132, 21);
		panel.add(txtRazon);
		
		txtcodigo = new JTextField();
		txtcodigo.setEditable(false);
		txtcodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcodigo.setColumns(10);
		txtcodigo.setBackground(new Color(250, 250, 210));
		txtcodigo.setBounds(114, 52, 132, 21);
		panel.add(txtcodigo);
		listado();
	}
	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {
		
String  razon,ruc,dirc,cel,pre,codObjeto;
		
		razon=txtRazon.getText();
		ruc=txtRuc.getText();
		dirc=txtDir.getText();
		cel=txtCel.getText();
		pre=txtPrecio.getText();
		codObjeto=txtObjeto.getText();
		
		
		if(razon.trim().equals("")){
			mensaje("Campo Razon social es obligatorio");
			txtRazon.requestFocus();}
		
		else if(razon.trim().matches("[a-z A-Z ÑñáÁéÉíÍóÓúÚ]{2,30}")==false){
			mensaje("Campo Razon social requiere al menos 2 caracteres");
			txtRazon.requestFocus();
		}
		else if(ruc.trim().equals("")){
			mensaje("campo Ruc es obligatorio");
			txtRuc.requestFocus();
		}
		else if(ruc.trim().matches("[0-9]{11}")==false){
			mensaje("Campo Ruc requiere al menos 11 digitos");
			txtRuc.requestFocus();
		}
		else if(dirc.trim().equals("")){
			mensaje("campo Direccion es obligatorio");
			txtDir.requestFocus();
		}
		else if(dirc.trim().matches("^.{5,50}")==false){
			mensaje("Campo Direccion requiere al menos 5 caracteres");
			txtDir.requestFocus();
		}
		else if(cel.trim().equals("")){
			mensaje("campo Celular  es obligatorio");
			txtCel.requestFocus();
		}
		else if(cel.trim().matches("[0-9]{9}")==false){
			mensaje("Campo Celular requiere al menos 9 digitos");
			txtCel.requestFocus();
		}
		else if(pre.trim().equals("")){
			mensaje("campo Precio  es obligatorio");
			txtPrecio.requestFocus();
		}
		else if(pre.trim().matches("[0-9]{4,7}")==false){
			mensaje("Campo Precio min:1000");
			txtPrecio.requestFocus();
		}
		else if(codObjeto.trim().equals("")){
			mensaje("Elija una Objeto,no puede estar vacia");
		}
		
		else{
		
	
		
		
		Provedor bean= new Provedor();
		
		bean.setRazon(razon);
		bean.setRuc(ruc);
		bean.setDirec(dirc);
		bean.setCelular(Integer.parseInt(cel));
		bean.setPrecio(Double.parseDouble(pre));
		bean.setCodObjeto(Integer.parseInt(codObjeto));
		bean.setEstado(1);
       int salida;
       salida=ProveedorDAO.registrar(bean);
		if(salida>0) {
			mensaje("proveedor registrado");
			listado();
		}
		else
			mensaje("Error en el registro");
		
		}
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}

	void listado(){
		ArrayList<Provedor> data= ProveedorDAO.listAll();
		
		DefaultTableModel modelo=(DefaultTableModel) tblProveedorl.getModel();
		
		modelo.setRowCount(0);
		
		for(Provedor p:data){
			Object row[]={
					p.getCodPro(),p.getRazon(),p.getRuc(),p.getDirec(),p.getCelular(),p.getPrecio(),p.getCodObjeto(),p.getMonEstado()
			};
			modelo.addRow(row);
		}
	}
	protected void actionPerformedBtnbuscarCod(ActionEvent arg0) {
		
	
		frmBuscarobjeto frm = new frmBuscarobjeto();
		frm.setVisible(true);

	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		
		
String  codigo,razon,ruc,dirc,cel,pre,codObjeto;
		
        codigo=txtcodigo.getText();
		razon=txtRazon.getText();
		ruc=txtRuc.getText();
		dirc=txtDir.getText();
		cel=txtCel.getText();
		pre=txtPrecio.getText();
		codObjeto=txtObjeto.getText();
		
		
		if(razon.trim().equals("")){
			mensaje("Campo Razon social es obligatorio");
			txtRazon.requestFocus();}
		
		else if(razon.trim().matches("[a-z A-Z ÑñáÁéÉíÍóÓúÚ]{2,30}")==false){
			mensaje("Campo Razon social requiere al menos 2 caracteres");
			txtRazon.requestFocus();
		}
		else if(ruc.trim().equals("")){
			mensaje("campo Ruc es obligatorio");
			txtRuc.requestFocus();
		}
		else if(ruc.trim().matches("[0-9]{11}")==false){
			mensaje("Campo Ruc requiere al menos 11 digitos");
			txtRuc.requestFocus();
		}
		else if(dirc.trim().equals("")){
			mensaje("campo Direccion es obligatorio");
			txtDir.requestFocus();
		}
		else if(dirc.trim().matches("^.{5,50}")==false){
			mensaje("Campo Direccion requiere al menos 5 caracteres");
			txtDir.requestFocus();
		}
		else if(cel.trim().equals("")){
			mensaje("campo Celular  es obligatorio");
			txtCel.requestFocus();
		}
		else if(cel.trim().matches("[0-9]{9}")==false){
			mensaje("Campo Celular requiere al menos 9 digitos");
			txtCel.requestFocus();
		}
		else if(pre.trim().equals("")){
			mensaje("campo Precio  es obligatorio");
			txtPrecio.requestFocus();
		}
		else if(pre.trim().matches("[0-9]{4,7}")==false){
			mensaje("Campo Precio min:1000");
			txtPrecio.requestFocus();
		}
		else if(codObjeto.trim().equals("")){
			mensaje("Elija una Objeto,no puede estar vacia");
		}
		
		else{
		
		Provedor bean= new Provedor();
		bean.setCodPro(Integer.parseInt(codigo));
		bean.setRazon(razon);
		bean.setRuc(ruc);
		bean.setDirec(dirc);
		bean.setCelular(Integer.parseInt(cel));
		bean.setPrecio(Double.parseDouble(pre));
		bean.setCodObjeto(Integer.parseInt(codObjeto));
       int salida;
       salida=ProveedorDAO.update(bean);
		if(salida>0) {
			mensaje("proveedor actualizado");
			listado();
		}
		else
			mensaje("Error en el actualizacion");
		
		}
	}
	protected void mouseClickedTblProveedorl(MouseEvent arg0) {
		
		int posFila;
		
		String  cod,razon,ruc,dirc,cel,pre,codObjeto;
	
	    posFila=tblProveedorl.getSelectedRow();
	    
	    
		cod=tblProveedorl.getValueAt(posFila, 0).toString();
		razon=tblProveedorl.getValueAt(posFila, 1).toString();
		ruc=tblProveedorl.getValueAt(posFila, 2).toString();
		dirc=tblProveedorl.getValueAt(posFila, 3).toString();
		cel=tblProveedorl.getValueAt(posFila, 4).toString();
		pre=tblProveedorl.getValueAt(posFila, 5).toString();
		codObjeto=tblProveedorl.getValueAt(posFila, 6).toString();
		
		txtcodigo.setText(cod);
		txtRazon.setText(razon);
		txtRuc.setText(ruc);	
		txtDir.setText(dirc);
		txtCel.setText(cel);
		txtPrecio.setText(pre);
		txtObjeto.setText(codObjeto);
	    
		
	}
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		
		
		int boton;
		boton=JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este registro?","Sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		
		if(boton==0){
			int cod,salida;
			cod=Integer.parseInt(txtcodigo.getText());
			salida=ProveedorDAO.deleate(cod);
			
			if(salida>0){
				mensaje("proveedor eliminada correctamente");
			}
			else mensaje("Error en la eliminación");
			
		}
		listado();


	}
}
