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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlBasesAdmiDAO;
import com.biblioteca.entidad.BasesAdministrativas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class frmBasesAdministrativas extends JFrame {

	private MySqlBasesAdmiDAO basesDAO=new MySqlBasesAdmiDAO();
	private JPanel contentPane;
	private JTable tblBases;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTextField txtDescripcion;
	private JTextField txtCodigo;
	private JLabel lblPlazo;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblCdigoDeSolicitud;
	public static JTextField txtSolContrata;
	private JButton btnBuscarSol;
	private JButton btnNuevo;
	private JComboBox cboplazo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBasesAdministrativas frame = new frmBasesAdministrativas();
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
	public frmBasesAdministrativas() {
		setResizable(false);
		setTitle("REGISTRAR BASES ADMINISTRATIVAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ravenna\\Desktop\\cursos de cibertec\\Tercer ciclo\\lenguaje de programaci\u00F3n\\proyecto\\WSLPT3GC\\GERENCIA CENTRAL DE LOGISTICA\\iconos\\maintenance.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1008, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 11, 992, 38);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("BASES ADMINISTRATIVAS");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setForeground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(332, 60, 650, 272);
		contentPane.add(scrollPane);
		
		tblBases = new JTable();
		tblBases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTblBases(arg0);
			}
		});
		tblBases.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descripci\u00F3n", "Plazo", "C.Empleado", "Fecha", "Estado", "C.Sol.Contrata"
			}
		));
		tblBases.getColumnModel().getColumn(1).setPreferredWidth(86);
		tblBases.getColumnModel().getColumn(6).setPreferredWidth(83);
		scrollPane.setViewportView(tblBases);
		
		btnRegistrar = new JButton("");
		btnRegistrar.setToolTipText("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnRegistrar(arg0);
			}
		});
		btnRegistrar.setIcon(new ImageIcon(frmBasesAdministrativas.class.getResource("/iconos/floppy-disk.png")));
		btnRegistrar.setBounds(22, 250, 60, 43);
		contentPane.add(btnRegistrar);
		
		btnActualizar = new JButton("");
		btnActualizar.setToolTipText("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setIcon(new ImageIcon(frmBasesAdministrativas.class.getResource("/iconos/edit.png")));
		btnActualizar.setBounds(92, 250, 53, 43);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("");
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setIcon(new ImageIcon(frmBasesAdministrativas.class.getResource("/iconos/delete (1).png")));
		btnEliminar.setBounds(155, 250, 60, 43);
		contentPane.add(btnEliminar);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(113, 103, 209, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBackground(new Color(240, 230, 140));
		txtCodigo.setBounds(113, 72, 78, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 78, 72, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(10, 109, 72, 14);
		contentPane.add(lblDescripcin);
		
		lblPlazo = new JLabel("Plazo");
		lblPlazo.setBounds(10, 137, 60, 14);
		contentPane.add(lblPlazo);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 170, 46, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(113, 165, 106, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		lblCdigoDeSolicitud = new JLabel("C\u00F3digo de sol. Contrata");
		lblCdigoDeSolicitud.setBounds(10, 208, 166, 14);
		contentPane.add(lblCdigoDeSolicitud);
		
		txtSolContrata = new JTextField();
		txtSolContrata.setEditable(false);
		txtSolContrata.setBounds(155, 205, 86, 20);
		contentPane.add(txtSolContrata);
		txtSolContrata.setColumns(10);
		
		btnBuscarSol = new JButton("");
		btnBuscarSol.setToolTipText("Buscar");
		btnBuscarSol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscarSol(e);
			}
		});
		btnBuscarSol.setIcon(new ImageIcon(frmBasesAdministrativas.class.getResource("/iconos/search.png")));
		btnBuscarSol.setBounds(251, 194, 53, 38);
		contentPane.add(btnBuscarSol);
		
		txtFecha.setText(fechaActual());
		
		btnNuevo = new JButton("");
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNuevo(e);
			}
		});
		btnNuevo.setIcon(new ImageIcon(frmBasesAdministrativas.class.getResource("/iconos/add-file.png")));
		btnNuevo.setBounds(225, 250, 60, 43);
		contentPane.add(btnNuevo);
		
		cboplazo = new JComboBox();
		cboplazo.setModel(new DefaultComboBoxModel(new String[] {"30 d\u00EDas", "60 d\u00EDas", "90 d\u00EDas"}));
		cboplazo.setBounds(113, 134, 102, 20);
		contentPane.add(cboplazo);
		listado();
		
	}
	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {
		
		
		String descripcion,plazo,fecha,cod_sol_con;
		
		cod_sol_con=txtSolContrata.getText();
		descripcion=txtDescripcion.getText();
		plazo=cboplazo.getSelectedItem().toString();
		fecha=txtFecha.getText();
		
		int posfila=tblBases.getSelectedRow();
		
		
		
		
		if(posfila==-1){
			String repeticion="nada";
			
			if(descripcion.trim().equals("")){
				mensaje("Campo descripci�n no puede estar vacio");
				txtDescripcion.requestFocus();
			}
			else if(descripcion.matches("[a-zA-Z������������ -/!#()& 1-9]{10,30}")==false){
				mensaje("Campo descripcion m�nimo 10 caracteres");
				txtDescripcion.requestFocus();
			}
			else if(repeticion.trim().equals(txtSolContrata.getText())){
				mensaje("Codigo de Solicitud de contrataci�n ya se registro anteriormente seleccione otro!");
			}
			
			
			else{
			BasesAdministrativas bean=new BasesAdministrativas();
			
			bean.setDes_bases(descripcion);
			bean.setPlazo(plazo);
			bean.setFecha(fecha);
			bean.setCod_sol(Integer.parseInt(cod_sol_con));
			bean.setEstado(1);
			bean.setCodEmp(1);
			
			int salida=basesDAO.save(bean);
			
			if(salida>0){
				mensaje("Bases Administrativas registradas");
				listado();
				limpiar();
			}
			else{
				mensaje("Error en el registro de las bases Administrativas");
			}}
			
			
		}
		
		
		else{
			String repeticion=tblBases.getValueAt(posfila,6).toString();
		
		if(descripcion.trim().equals("")){
			mensaje("Campo descripci�n no puede estar vacio");
			txtDescripcion.requestFocus();
		}
		else if(descripcion.matches("[a-zA-Z������������ -/!#()& 1-9]{10,30}")==false){
			mensaje("Campo descripcion m�nimo 10 caracteres");
			txtDescripcion.requestFocus();
		}
		else if(repeticion.trim().equals(txtSolContrata.getText())){
			mensaje("Codigo de Solicitud de contrataci�n ya se registro anteriormente seleccione otro!");
		}
		
		
		else{
		BasesAdministrativas bean=new BasesAdministrativas();
		
		bean.setDes_bases(descripcion);
		bean.setPlazo(plazo);
		bean.setFecha(fecha);
		bean.setCod_sol(Integer.parseInt(cod_sol_con));
		bean.setEstado(1);
		bean.setCodEmp(1);
		
		int salida=basesDAO.save(bean);
		
		if(salida>0){
			mensaje("Bases Administrativas registradas");
			listado();
			limpiar();
		}
		else{
			mensaje("Error en el registro de las bases Administrativas");
		}}
		}
		
	
	
	
	}
		
	
	
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		
		int cod_sol_con,salida,codigo;
		String descripcion,plazo,fecha;
		
		cod_sol_con=Integer.parseInt(txtSolContrata.getText());
		descripcion=txtDescripcion.getText();
		plazo=cboplazo.getSelectedItem().toString();
		fecha=txtFecha.getText();
		codigo=Integer.parseInt(txtCodigo.getText()) ;
		
		BasesAdministrativas bean=new BasesAdministrativas();
		
		bean.setCodigoBases(codigo);
		bean.setDes_bases(descripcion);
		bean.setPlazo(plazo);
		bean.setFecha(fecha);
		bean.setCod_sol(cod_sol_con);
		bean.setCodEmp(1);
		
		salida=basesDAO.update(bean);
		
		if(salida>0){
			mensaje("Bases Administrativas Actualizada");
			listado();
			limpiar();
		}
		else{
			mensaje("Error en el registro de las bases Administrativas");
		}
		
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		
		int confirmacion,salida,codigo;
		
		if(txtCodigo.getText().trim().equals("")){
			mensaje("Codigo no puede estar vacio");}
		else{
			confirmacion=JOptionPane.showConfirmDialog(null, "Seguro de eliminar ? ","Sistema",
					JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		if(confirmacion==0){
			codigo=Integer.parseInt(txtCodigo.getText());
			salida=basesDAO.delete(codigo);
			if(salida>0){
				mensaje("Bases Administrativas Eliminadas ");
				
				listado();
				limpiar();}
				else{
					mensaje("Error en la eliminaci�n");
				}	
				
			}}}
		
	protected void actionPerformedBtnBuscarSol(ActionEvent e) {
		
		frmBuscarSolContrata frm=new frmBuscarSolContrata();
		frm.setVisible(true);
	}
	protected void mouseClickedTblBases(MouseEvent arg0) {
		
		int posfila;
		String codigo,descripcion,plazo,cod_sol;
		
		posfila=tblBases.getSelectedRow();
		codigo=tblBases.getValueAt(posfila, 0).toString();
		descripcion=tblBases.getValueAt(posfila, 1).toString();
		plazo=tblBases.getValueAt(posfila, 2).toString();
		cod_sol=tblBases.getValueAt(posfila, 6).toString();
		
		txtCodigo.setText(codigo);
		txtDescripcion.setText(descripcion);
		cboplazo.setSelectedItem(plazo);
		txtSolContrata.setText(cod_sol);
		
		
		
	}
	
	void listado(){
		
		DefaultTableModel basesadmi= (DefaultTableModel) tblBases.getModel();
		
		basesadmi.setRowCount(0);
		ArrayList<BasesAdministrativas> lista=basesDAO.listAll();
		for(BasesAdministrativas bean:lista){
			Object row[]={
					bean.getCodigoBases(),bean.getDes_bases(),bean.getPlazo(),bean.getCodEmp(),bean.getFecha(),bean.getNombrestado(),bean.getCod_sol()
			};
			basesadmi.addRow(row);
		}
	}
	void limpiar(){
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtSolContrata.setText("");
		txtDescripcion.requestFocus();
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	public String fechaActual(){
		Date fecha=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("YYYY/MM/dd");
		return formato.format(fecha);
	}
	
	
	
	
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		limpiar();
	}
}
