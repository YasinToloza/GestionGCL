package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlSolicitudContrata;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.utils.GeneradorReporte;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class ReporteSolicitudContrata extends JFrame {

	private MySqlSolicitudContrata SolicitudContrataDAO=new MySqlSolicitudContrata();
	
	ArrayList<SolicitudContrata>info=null;
	
	private JPanel contentPane;
	private JTextField txtBusqueda;
	private JComboBox cboEstado;
	private JComboBox cboContrato;
	private JButton btnBuscar;
	private JButton btnReportar;
	private JTable tblSolContrata;
	private JRadioButton rbtnTipoDeContrato;
	private JRadioButton rbtnEstado;
	private JRadioButton rbtnCdigo;
	private JLabel lblinfo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rbtnTodos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteSolicitudContrata frame = new ReporteSolicitudContrata();
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
	public ReporteSolicitudContrata() {
		setResizable(false);
		setTitle("Reporte de Contratacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 975, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Consulta Por", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 59, 939, 62);
		contentPane.add(panel);
		
		rbtnCdigo = new JRadioButton("C\u00F3digo");
		buttonGroup.add(rbtnCdigo);
		rbtnCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedRdbtnCdigo(arg0);
			}
		});
		rbtnCdigo.setSelected(true);
		rbtnCdigo.setBounds(18, 26, 73, 23);
		panel.add(rbtnCdigo);
		
		rbtnEstado = new JRadioButton("Estado");
		buttonGroup.add(rbtnEstado);
		rbtnEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedRdbtnEstado(e);
			}
		});
		rbtnEstado.setBounds(93, 26, 84, 23);
		panel.add(rbtnEstado);
		
		rbtnTipoDeContrato = new JRadioButton("Tipo de contrato");
		buttonGroup.add(rbtnTipoDeContrato);
		rbtnTipoDeContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedRdbtnTipoDeContrato(e);
			}
		});
		rbtnTipoDeContrato.setBounds(181, 26, 125, 23);
		panel.add(rbtnTipoDeContrato);
		
		rbtnTodos = new JRadioButton("Todos");
		rbtnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedRbtnTodos(arg0);
			}
		});
		buttonGroup.add(rbtnTodos);
		rbtnTodos.setBounds(338, 26, 109, 23);
		panel.add(rbtnTodos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 0, 959, 48);
		contentPane.add(panel_1);
		
		JLabel lblConsultaSolicitudesDe = new JLabel("Reporte de Solicitudes de Contrataci\u00F3n");
		panel_1.add(lblConsultaSolicitudesDe);
		lblConsultaSolicitudesDe.setForeground(Color.WHITE);
		lblConsultaSolicitudesDe.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "CRITERIO", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 132, 939, 79);
		contentPane.add(panel_2);
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscar(e);
			}
		});
		btnBuscar.setToolTipText("BUSCAR");
		btnBuscar.setIcon(new ImageIcon(ReporteSolicitudContrata.class.getResource("/iconos/search (1).png")));
		btnBuscar.setBounds(679, 18, 83, 50);
		panel_2.add(btnBuscar);
		
		btnReportar = new JButton("");
		btnReportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnReportar(arg0);
			}
		});
		btnReportar.setToolTipText("REPORTE");
		btnReportar.setIcon(new ImageIcon(ReporteSolicitudContrata.class.getResource("/iconos/report.png")));
		btnReportar.setBounds(790, 18, 83, 50);
		panel_2.add(btnReportar);
		
		lblinfo = new JLabel("C\u00F3digo : ");
		lblinfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblinfo.setBounds(10, 35, 185, 14);
		panel_2.add(lblinfo);
		
		cboContrato = new JComboBox();
		cboContrato.setModel(new DefaultComboBoxModel(new String[] {"Licitaci\u00F3n", "Adjudicaci\u00F3n simplificada", "Contrataci\u00F3n directa"}));
		cboContrato.setBounds(142, 33, 191, 20);
		panel_2.add(cboContrato);
		
		cboEstado = new JComboBox();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"pendiente", "aprobado", "rechazado", "finalizado"}));
		cboEstado.setBounds(82, 33, 98, 20);
		panel_2.add(cboEstado);
		
		txtBusqueda = new JTextField();
		txtBusqueda.addKeyListener(new KeyAdapter() {
			
		});
		txtBusqueda.setBounds(82, 33, 124, 20);
		panel_2.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 222, 939, 424);
		contentPane.add(scrollPane);
		
		tblSolContrata = new JTable();
		tblSolContrata.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descripci\u00F3n", "Cod.Abas", "Cod.CCP", "Fecha", "Estado", "C.Empleado", "Tipo Contrato"
			}
		));
		tblSolContrata.getColumnModel().getColumn(0).setPreferredWidth(55);
		tblSolContrata.getColumnModel().getColumn(1).setPreferredWidth(139);
		tblSolContrata.getColumnModel().getColumn(2).setPreferredWidth(72);
		tblSolContrata.getColumnModel().getColumn(4).setPreferredWidth(83);
		tblSolContrata.getColumnModel().getColumn(7).setPreferredWidth(94);
		scrollPane.setViewportView(tblSolContrata);
		
		cboContrato.setVisible(false);
		txtBusqueda.setVisible(true);
		cboEstado.setVisible(false);
		
		
	}
	protected void actionPerformedRdbtnCdigo(ActionEvent arg0) {
		
		if(rbtnCdigo.isSelected()){
			lblinfo.setText("Código : ");
			cboContrato.setVisible(false);
			txtBusqueda.setVisible(true);
			cboEstado.setVisible(false);
		}
		
		
	}
	protected void actionPerformedRdbtnEstado(ActionEvent e) {
		
		if(rbtnEstado.isSelected()){
			lblinfo.setText("Estado : ");
			cboContrato.setVisible(false);
			txtBusqueda.setVisible(false);
			cboEstado.setVisible(true);
		}
		
		
	}
	protected void actionPerformedRdbtnTipoDeContrato(ActionEvent e) {
		
		if(rbtnTipoDeContrato.isSelected()){
			lblinfo.setText("Tipo de contrato : ");
			cboContrato.setVisible(true);
			txtBusqueda.setVisible(false);
			cboEstado.setVisible(false);
		}
		
		
	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	
	void listado(){
		ArrayList<SolicitudContrata> data= SolicitudContrataDAO.listAll();
		
		DefaultTableModel modelo=(DefaultTableModel) tblSolContrata.getModel();
		
		modelo.setRowCount(0);
		
		for(SolicitudContrata sol:data){
			Object row[]={
					sol.getCodigo(),sol.getDescripcion(),sol.getCod_sol(),sol.getCod_ccp(),sol.getFecha(),sol.getNombreEstado(),sol.getCodEmp(),sol.getTipocontrato()
			};
			modelo.addRow(row);
		}
	}
	void listado2(ArrayList<SolicitudContrata> data){
		
		
		DefaultTableModel modelo=(DefaultTableModel) tblSolContrata.getModel();
		
		modelo.setRowCount(0);
		
		for(SolicitudContrata sol:data){
			Object row[]={
					sol.getCodigo(),sol.getDescripcion(),sol.getCod_sol(),sol.getCod_ccp(),sol.getFecha(),sol.getNombreEstado(),sol.getCodEmp(),sol.getTipocontrato()
			};
			modelo.addRow(row);
		}
	}
	
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		
		
		
		
		if(rbtnCdigo.isSelected()){
			
		
			info=SolicitudContrataDAO.listarxcriterio(1, txtBusqueda.getText());
		}
		else if(rbtnEstado.isSelected()){
			
			String estado;
			estado=cboEstado.getSelectedItem().toString();
			if(estado=="pendiente")estado="1";
			else if(estado=="aprobado")estado="2";
			else if(estado=="rechazado")estado="3";
			else estado="4";
			info=SolicitudContrataDAO.listarxcriterio(2,estado);
			
		}
		else if(rbtnTipoDeContrato.isSelected()){
			String estado;
			estado=cboContrato.getSelectedItem().toString();
			info=SolicitudContrataDAO.listarxcriterio(3,estado);
			
		}
		else if(rbtnTodos.isSelected()) {
			info=SolicitudContrataDAO.listarxcriterio(4, "");
		}
		
		listado2(info);
		
		
		
	}
	
	
	
	protected void actionPerformedBtnReportar(ActionEvent arg0) {
		
		if(tblSolContrata.getRowCount()==0){
			mensaje("La tabla esta vacia para reportar,por favor haga una nueva busqueda");
		}
		else{
				frmReporte frm=new frmReporte();
				String nombre;
				nombre="reporteSolContrata.jasper";
				
				JRBeanCollectionDataSource data=new JRBeanCollectionDataSource(info);
				
				JasperPrint jasper=GeneradorReporte.genera(nombre, data, null);
				
				JRViewer viewer=new JRViewer(jasper);
				
				frm.panelReporte.add(viewer);
				
				
				frm.setVisible(true);
			
		}
	}
	protected void actionPerformedRbtnTodos(ActionEvent arg0) {
		txtBusqueda.setVisible(false);
		cboContrato.setVisible(false);
		cboEstado.setVisible(false);
		lblinfo.setText("Todos");
	}
}
