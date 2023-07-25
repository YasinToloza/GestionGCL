package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.componente.JComboBoxDB;
import com.biblioteca.controlador.MySqlAbastecimientoDAO;
import com.biblioteca.controlador.MySqlBasesAdmiDAO;
import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.entidad.BasesAdministrativas;
import com.biblioteca.utils.GeneradorReporte;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class frmReporteBaseAdmin extends JFrame implements ActionListener, ItemListener {
	private MySqlBasesAdmiDAO BasesAdministrativaDAO=new MySqlBasesAdmiDAO();
	ArrayList<BasesAdministrativas> info=null;

	private JPanel contentPane;
	private JComboBox cboEstado;
	private JButton btnCerrar;
	private JButton btnReportar;
	private JTextField txtBuscar;
	private JRadioButton rbtnCodigo;
	private JRadioButton rbtnEstado;
	private JRadioButton rbtnFecha;
	private final ButtonGroup buttonGroup= new ButtonGroup();
	private JLabel lblInfo;
	private JTable tblSolicitud;
	private JButton btnBuscar;
	private JRadioButton rbtnTodo;
	private JPanel panel_2;
	private JLabel lblReporteDeBases;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmReporteBaseAdmin frame = new frmReporteBaseAdmin();
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
	public frmReporteBaseAdmin() {
		setResizable(false);
		setTitle("Reporte Bases Administrativas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 807, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Criterio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 126, 528, 48);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cboEstado = new JComboBoxDB("select * from tb_estado");
		cboEstado.addItemListener(this);
		cboEstado.setBounds(113, 15, 139, 22);
		
		panel.add(cboEstado);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(113, 16, 107, 20);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		lblInfo = new JLabel("Por Estado:");
		lblInfo.setBounds(28, 19, 83, 14);
		panel.add(lblInfo);
		btnReportar = new JButton("");
		btnReportar.setIcon(new ImageIcon(frmReporteBaseAdmin.class.getResource("/iconos/report.png")));
		btnReportar.setToolTipText("Reporte");
		btnReportar.addActionListener(this);
		btnReportar.setBounds(675, 126, 107, 48);
		contentPane.add(btnReportar);
		
		btnCerrar = new JButton("");
		btnCerrar.setIcon(new ImageIcon(frmReporteBaseAdmin.class.getResource("/iconos/maintenance.png")));
		btnCerrar.setToolTipText("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(607, 67, 107, 48);
		contentPane.add(btnCerrar);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(frmReporteBaseAdmin.class.getResource("/iconos/search (1).png")));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(548, 126, 107, 48);
		contentPane.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "B\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 67, 528, 48);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		rbtnEstado = new JRadioButton("Estado");
		rbtnEstado.setSelected(true);
		rbtnEstado.addActionListener(this);
		buttonGroup.add(rbtnEstado);
		rbtnEstado.setBounds(24, 18, 82, 23);
		panel_1.add(rbtnEstado);
		
		rbtnFecha = new JRadioButton("Fecha");
		rbtnFecha.addActionListener(this);
		buttonGroup.add(rbtnFecha);
		rbtnFecha.setBounds(199, 18, 74, 23);
		panel_1.add(rbtnFecha);
		
		rbtnCodigo = new JRadioButton("C\u00F3digo");
		rbtnCodigo.setBounds(108, 18, 89, 23);
		panel_1.add(rbtnCodigo);
		rbtnCodigo.addActionListener(this);
		buttonGroup.add(rbtnCodigo);
		
		rbtnTodo = new JRadioButton("Todo");
		rbtnTodo.addActionListener(this);
		buttonGroup.add(rbtnTodo);
		rbtnTodo.setBounds(275, 18, 74, 23);
		panel_1.add(rbtnTodo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 772, 251);
		contentPane.add(scrollPane);
		
		tblSolicitud = new JTable();
		tblSolicitud.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descripcion", "Plazo", "C.Empleado", "Fecha", "Estado", "C. Solicitud"
			}
		));
		tblSolicitud.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblSolicitud);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(0, 0, 791, 38);
		contentPane.add(panel_2);
		
		lblReporteDeBases = new JLabel("REPORTE DE BASES ADMINISTRATIVAS");
		lblReporteDeBases.setForeground(Color.WHITE);
		lblReporteDeBases.setFont(new Font("Tahoma", Font.BOLD, 23));
		panel_2.add(lblReporteDeBases);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rbtnTodo) {
			actionPerformedRbtnTodo(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
		if (e.getSource() == rbtnFecha) {
			actionPerformedRbtnFecha(e);
		}
		if (e.getSource() == rbtnEstado) {
			actionPerformedRbtnEstado(e);
		}
		if (e.getSource() == rbtnCodigo) {
			actionPerformedRbtnCodigo(e);
		}
		if (e.getSource() == btnReportar) {
			actionPerformedBtnReportar(e);
		}
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}
	
	protected void actionPerformedRbtnEstado(ActionEvent e) {
		if(rbtnEstado.isSelected()) {
			   lblInfo.setText("Por Estado : ");
			   cboEstado.setVisible(true);
			   txtBuscar.setVisible(false);
	}
	}
	
	protected void actionPerformedRbtnCodigo(ActionEvent e) {
		if(rbtnCodigo.isSelected()) {
		   lblInfo.setText("Por Código : ");
		   cboEstado.setVisible(false);
		   txtBuscar.setVisible(true);
		}
	} 
	
	protected void actionPerformedRbtnFecha(ActionEvent e) {
		if(rbtnFecha.isSelected()) {
			   lblInfo.setText("Por Fecha : ");
			   cboEstado.setVisible(false);
			   txtBuscar.setVisible(true);
	}
	}
	
	protected void actionPerformedRbtnTodo(ActionEvent e) {
		if(rbtnTodo.isSelected()) {
			   lblInfo.setText("Todos");
			   cboEstado.setVisible(false);
			   txtBuscar.setVisible(false);
	}
	}
	
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}
	protected void actionPerformedBtnReportar(ActionEvent e) {
		frmReporte frm=new frmReporte();
		String nombre;
		nombre="ReporteBasesAdmin.jasper";
		JRBeanCollectionDataSource data=new JRBeanCollectionDataSource(info);
		JasperPrint jasper=GeneradorReporte.genera(nombre, data, null);
		JRViewer viewer=new JRViewer(jasper);
		frm.panelReporte.add(viewer);
		frm.setVisible(true);
		
	}
	
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		if (rbtnEstado.isSelected()) {
		String edi,sep[];
		edi=cboEstado.getSelectedItem().toString();
		sep=edi.split("-");
		info=BasesAdministrativaDAO.listarSolicitudesConsulta(1,sep[0]);	
		}
		else if (rbtnCodigo.isSelected()) {
			info=BasesAdministrativaDAO.listarSolicitudesConsulta(2, txtBuscar.getText());
		}
		else if (rbtnFecha.isSelected()) {
			info=BasesAdministrativaDAO.listarSolicitudesConsulta(3, txtBuscar.getText());
		}
		else if (rbtnTodo.isSelected()) {
			info=BasesAdministrativaDAO.listarSolicitudesConsulta(4, "");
		}
		listarTodo(info);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
	
	void listarTodo(ArrayList<BasesAdministrativas> data){
		DefaultTableModel abastecimiento=(DefaultTableModel) tblSolicitud.getModel();	
		abastecimiento.setRowCount(0);
		for(BasesAdministrativas a:data){
			Object row[]={
					a.getCodigoBases(),a.getDes_bases(),a.getPlazo(),a.getCodEmp(),a.getFecha(),a.getNombrestado(),a.getCod_sol()};
			abastecimiento.addRow(row);
		}
	}
	
	}	

