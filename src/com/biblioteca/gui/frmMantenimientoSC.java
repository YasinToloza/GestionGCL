package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Toolkit;
import com.toedter.calendar.JDayChooser;
import com.biblioteca.controlador.MySqlSolicitudContrata;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.interfaces.SolicitudContrataDAO;
import com.toedter.calendar.JDateChooser;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class frmMantenimientoSC extends JFrame {
	
	private MySqlSolicitudContrata SolicitudContrataDAO=new MySqlSolicitudContrata();
	
	//globales para cambiar estado a finalizado
	//public static int cod_sol,cod_CCP;
	
	private JPanel contentPane;
	private JButton btnGuardar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JTable tblContrata;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JButton btnBuscarAbas;
	private JLabel lblCodigoDeAbastecimiento;
	public static JTextField txtCodAbas;
	private JLabel lblProveedor;
	private JButton btnBuscarProv;
	public static JTextField txtcodProv;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JComboBox cboTContrato;
	private JButton btnNuevo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMantenimientoSC frame = new frmMantenimientoSC();
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
	public frmMantenimientoSC() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ravenna\\Desktop\\cursos de cibertec\\Tercer ciclo\\lenguaje de programaci\u00F3n\\proyecto\\WSLPT3GC\\GERENCIA CENTRAL DE LOGISTICA\\iconos\\maintenance.png"));
		setTitle("SOLICITUD DE CONTRATACI\u00D3N");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardar(e);
			}
		});
		btnGuardar.setToolTipText("GUARDAR");
		btnGuardar.setIcon(new ImageIcon(frmMantenimientoSC.class.getResource("/iconos/floppy-disk.png")));
		btnGuardar.setBounds(693, 361, 56, 41);
		contentPane.add(btnGuardar);
		
		btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBorrar(e);
			}
		});
		btnBorrar.setToolTipText("BORRAR");
		btnBorrar.setIcon(new ImageIcon(frmMantenimientoSC.class.getResource("/iconos/delete (1).png")));
		btnBorrar.setBounds(759, 361, 56, 41);
		contentPane.add(btnBorrar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEditar(e);
			}
		});
		btnEditar.setToolTipText("EDITAR");
		btnEditar.setIcon(new ImageIcon(frmMantenimientoSC.class.getResource("/iconos/edit.png")));
		btnEditar.setBounds(825, 361, 56, 41);
		contentPane.add(btnEditar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 673, 356);
		contentPane.add(scrollPane);
		
		tblContrata = new JTable();
		tblContrata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTblContrata(arg0);
			}
		});
		tblContrata.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripci\u00F3n", "Cod.Abas", "Cod.CCP", "Fecha", "Estado", "Cod.Empleado", "T.Contrato"
			}
		));
		tblContrata.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblContrata.getColumnModel().getColumn(1).setPreferredWidth(109);
		tblContrata.getColumnModel().getColumn(2).setPreferredWidth(64);
		tblContrata.getColumnModel().getColumn(3).setPreferredWidth(62);
		tblContrata.getColumnModel().getColumn(4).setPreferredWidth(81);
		tblContrata.getColumnModel().getColumn(6).setPreferredWidth(90);
		tblContrata.getColumnModel().getColumn(7).setPreferredWidth(92);
		scrollPane.setViewportView(tblContrata);
		
		JLabel lblMantenimientoContratacin = new JLabel("MANTENIMIENTO SOLICITUD DE  CONTRATACI\u00D3N");
		lblMantenimientoContratacin.setBackground(Color.BLACK);
		lblMantenimientoContratacin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMantenimientoContratacin.setForeground(Color.BLACK);
		lblMantenimientoContratacin.setBounds(189, 11, 637, 33);
		contentPane.add(lblMantenimientoContratacin);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigo.setForeground(Color.BLACK);
		lblCodigo.setBounds(692, 47, 57, 16);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBackground(new Color(255, 239, 213));
		txtCodigo.setBounds(693, 65, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setForeground(Color.BLACK);
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescripcin.setBounds(692, 96, 96, 16);
		contentPane.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(693, 123, 298, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnBuscarAbas = new JButton("");
		btnBuscarAbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnBuscarAbas(arg0);
			}
		});
		btnBuscarAbas.setIcon(new ImageIcon(frmMantenimientoSC.class.getResource("/iconos/search.png")));
		btnBuscarAbas.setToolTipText("Buscar Abastecimiento");
		btnBuscarAbas.setBounds(693, 154, 56, 41);
		contentPane.add(btnBuscarAbas);
		
		lblCodigoDeAbastecimiento = new JLabel("Codigo de abastecimiento");
		lblCodigoDeAbastecimiento.setForeground(Color.BLACK);
		lblCodigoDeAbastecimiento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoDeAbastecimiento.setBounds(759, 154, 189, 16);
		contentPane.add(lblCodigoDeAbastecimiento);
		
		txtCodAbas = new JTextField();
		txtCodAbas.setEditable(false);
		txtCodAbas.setBounds(759, 175, 86, 20);
		contentPane.add(txtCodAbas);
		txtCodAbas.setColumns(10);
		
		lblProveedor = new JLabel("Codigo CCP");
		lblProveedor.setForeground(Color.BLACK);
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProveedor.setBounds(759, 205, 135, 16);
		contentPane.add(lblProveedor);
		
		btnBuscarProv = new JButton("");
		btnBuscarProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnBuscarProv(arg0);
			}
		});
		btnBuscarProv.setIcon(new ImageIcon(frmMantenimientoSC.class.getResource("/iconos/search.png")));
		btnBuscarProv.setToolTipText("Buscar CCP");
		btnBuscarProv.setBounds(693, 218, 56, 41);
		contentPane.add(btnBuscarProv);
		
		txtcodProv = new JTextField();
		txtcodProv.setEditable(false);
		txtcodProv.setBounds(759, 227, 86, 20);
		contentPane.add(txtcodProv);
		txtcodProv.setColumns(10);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setBounds(693, 270, 57, 16);
		contentPane.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(759, 269, 86, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		listado();
		txtFecha.setText(fechaActual());
		
		cboTContrato = new JComboBox();
		cboTContrato.setModel(new DefaultComboBoxModel(new String[] {"Licitaci\u00F3n", "Adjudicaci\u00F3n simplificada", "Contrataci\u00F3n directa"}));
		cboTContrato.setBounds(810, 316, 164, 20);
		contentPane.add(cboTContrato);
		
		JLabel lblTipoDeContrato = new JLabel("Tipo de contrato");
		lblTipoDeContrato.setForeground(Color.BLACK);
		lblTipoDeContrato.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoDeContrato.setBounds(692, 317, 123, 16);
		contentPane.add(lblTipoDeContrato);
		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNuevo(arg0);
			}
		});
		btnNuevo.setIcon(new ImageIcon(frmMantenimientoSC.class.getResource("/iconos/add-file.png")));
		btnNuevo.setToolTipText("NUEVO");
		btnNuevo.setBounds(901, 361, 56, 41);
		contentPane.add(btnNuevo);
	}
	protected void actionPerformedBtnBuscarAbas(ActionEvent arg0) {
		frmBuscarSolicitud frm = new frmBuscarSolicitud();
		frm.setVisible(true);
	}
	protected void actionPerformedBtnBuscarProv(ActionEvent arg0) {
		
		frmbuscarCCP frm=new frmbuscarCCP();
		frm.setVisible(true);
	}
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		
		String descripcion,codigoAbas,codProv,fecha,tipocontrato;
		descripcion=txtDescripcion.getText();
		codigoAbas=txtCodAbas.getText();
		codProv=txtcodProv.getText();
		fecha=txtFecha.getText();
		tipocontrato=cboTContrato.getSelectedItem().toString();
		int posfila=tblContrata.getSelectedRow();
		
		
		if(posfila==-1){
			String repeticion="aea";
			String repeticion2="aea";
			
			if(descripcion.trim().equals("")){
				mensaje("Campo descripción es obligatorio");
				txtDescripcion.requestFocus();}
			
			else if(descripcion.trim().matches("^.{5,100}")==false){
				mensaje("Campo Descripción requiere al menos 5 caracteres");
				txtDescripcion.requestFocus();
			}
			else if(codigoAbas.trim().equals("")){
				mensaje("Elija una solicitud de abastecimiento,no puede estar vacia");
				
			}
			else if (codProv.trim().equals("")){
				mensaje("Elija una Solicitud CCP ,no puede estar vacio el campo");
			}
			else if(repeticion.trim().equals(txtCodAbas.getText())){
				mensaje("Codigo de abastecimiento ya se eligio anteriormente elija otro");
			}
			else if(repeticion2.trim().equals(txtcodProv.getText())){
				mensaje("Codigo de CCP ya se eligio anteriormente elija otro");
			}
			else {
				
				
				SolicitudContrata bean=new SolicitudContrata();
				bean.setDescripcion(descripcion);
				bean.setCod_sol(Integer.parseInt(codigoAbas));
				bean.setCod_ccp(Integer.parseInt(codProv));
				bean.setFecha(fecha);
				bean.setEstado(1);
				bean.setCodEmp(1);
				bean.setTipocontrato(tipocontrato);
				
				int salida;
				salida=SolicitudContrataDAO.save(bean);
				
				if(salida>0){
					mensaje("Solicitud de contratación registrada correctamente...");
					listado();
					limpiar();
				}
				else{
					mensaje("Error en el registro de Solicitud de contratación");
				}
				
				
			}
		}else{
			String repeticion=tblContrata.getValueAt(posfila,2).toString();
			String repeticion2=tblContrata.getValueAt(posfila,3).toString();
			if(descripcion.trim().equals("")){
				mensaje("Campo descripción es obligatorio");
				txtDescripcion.requestFocus();}
			
			else if(descripcion.trim().matches("^.{5,100}")==false){
				mensaje("Campo dirección requiere al menos 5 caracteres");
				txtDescripcion.requestFocus();
			}
			else if(codigoAbas.trim().equals("")){
				mensaje("Elija una solicitud de abastecimiento,no puede estar vacia");
				
			}
			else if (codProv.trim().equals("")){
				mensaje("Elija una Solicitud CCP ,no puede estar vacio el campo");
			}
			else if(repeticion.trim().equals(txtCodAbas.getText())){
				mensaje("Codigo de abastecimiento ya se eligio anteriormente elija otro");
			}
			else if(repeticion2.trim().equals(txtcodProv.getText())){
				mensaje("Codigo de CCP ya se eligio anteriormente elija otro");
			}
			else {
				
				
				SolicitudContrata bean=new SolicitudContrata();
				bean.setDescripcion(descripcion);
				bean.setCod_sol(Integer.parseInt(codigoAbas));
				bean.setCod_ccp(Integer.parseInt(codProv));
				bean.setFecha(fecha);
				bean.setEstado(1);
				bean.setCodEmp(1);
				bean.setTipocontrato(tipocontrato);
				
				int salida;
				salida=SolicitudContrataDAO.save(bean);
				
				if(salida>0){
					mensaje("Solicitud de contratación registrada correctamente...");
					listado();
					limpiar();
				}
				else{
					mensaje("Error en el registro de Solicitud de contratación");
				}
				
				
			}
		}
			
		
		
	}
	protected void actionPerformedBtnBorrar(ActionEvent e) {
		
		int boton;
		
		if(txtCodigo.getText().trim().equals("")){
			mensaje("Para eliminar seleccione un codigo");
		}
		else{
		boton=JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este registro?","Sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		
		if(boton==0){
			
			
			int cod,salida;
			cod=Integer.parseInt(txtCodigo.getText());
			salida=SolicitudContrataDAO.delete(cod);
			
			if(salida>0){
				mensaje("Solicitud de Abastecimiento eliminada correctamente");
			}
			else mensaje("Error en la eliminación");
			
		}
		listado();
		limpiar();
		
		}
	}

	protected void actionPerformedBtnEditar(ActionEvent e) {
		
		String descripcion,codigoAbas,codProv,fecha,codigo,tipocontrato;
		
		descripcion=txtDescripcion.getText();
		codigoAbas=txtCodAbas.getText();
		codProv=txtcodProv.getText();
		fecha=txtFecha.getText();
		codigo=txtCodigo.getText();
		tipocontrato=cboTContrato.getSelectedItem().toString();
		
		//validación
		if(codigo.trim().equals("")){
			mensaje("Campo codigo requerido");
		}
		
		else if(descripcion.trim().equals("")){
			mensaje("Campo descripción es obligatorio");
			txtDescripcion.requestFocus();}
		
		else if(descripcion.trim().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]{3,50}")==false){
			mensaje("Campo Descripción requiere al menos 5 caracteres");
			txtDescripcion.requestFocus();
		}
		else if(codigoAbas.trim().equals("")){
			mensaje("Elija una solicitud de abastecimiento,no puede estar vacia");
			
		}
		else if (codProv.trim().equals("")){
			mensaje("Elija una proveedor ,no puede estar vacio el campo");
		}
		else{
			
			SolicitudContrata bean=new SolicitudContrata();
			bean.setCodigo(Integer.parseInt(codigo));
			bean.setDescripcion(descripcion);
			bean.setCod_sol(Integer.parseInt(codigoAbas));
			bean.setCod_ccp(Integer.parseInt(codProv));
			bean.setFecha(fecha);
			bean.setCodEmp(1);
			bean.setTipocontrato(tipocontrato);
			
			
			int salida;
			salida=SolicitudContrataDAO.update(bean);
			
			if(salida>0){
				mensaje("Solicitud de contratación Actualizada correctamente...");
				listado();
				limpiar();
				
			}
			else{
				mensaje("Error en la Actualización de Solicitud de contratación");
			}
			
			
		}
	}
	
	
	void listado(){
		ArrayList<SolicitudContrata> data= SolicitudContrataDAO.listAll();
		
		DefaultTableModel modelo=(DefaultTableModel) tblContrata.getModel();
		
		modelo.setRowCount(0);
		
		for(SolicitudContrata sol:data){
			Object row[]={
					sol.getCodigo(),sol.getDescripcion(),sol.getCod_sol(),sol.getCod_ccp(),sol.getFecha(),sol.getNombreEstado(),sol.getCodEmp(),sol.getTipocontrato()
			};
			modelo.addRow(row);
		}
	}
	
	
	protected void mouseClickedTblContrata(MouseEvent arg0) {
		
		int posFila;
		
		String cod,des,cod_sol,cod_prov,fecha,tipocontrato;
		
		posFila=tblContrata.getSelectedRow();
		
		cod=tblContrata.getValueAt(posFila, 0).toString();
		des=tblContrata.getValueAt(posFila, 1).toString();
		cod_sol=tblContrata.getValueAt(posFila, 2).toString();
		cod_prov=tblContrata.getValueAt(posFila, 3).toString();
		fecha=tblContrata.getValueAt(posFila, 4).toString();
		tipocontrato=tblContrata.getValueAt(posFila, 7).toString();
		txtCodigo.setText(cod);
		txtDescripcion.setText(des);
		txtCodAbas.setText(cod_sol);
		txtcodProv.setText(cod_prov);
		txtFecha.setText(fecha);
		cboTContrato.setSelectedItem(tipocontrato);
		
		
		
	}
	
	public String fechaActual(){
		Date fecha=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("YYYY/MM/dd");
		return formato.format(fecha);
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	
	void limpiar(){
		txtCodAbas.setText("");
		txtDescripcion.setText("");
		txtcodProv.setText("");
		txtCodigo.setText("");
		
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		
		limpiar();
		
	}
	
	}
