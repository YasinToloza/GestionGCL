package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Frame;

public class GuiPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mnSolAbas;
	public static JLabel lblNombre;
	public static JLabel lblcodigo;
	public static int cod_car,cod_div;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPrincipal frame = new GuiPrincipal();
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
	public GuiPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setTitle("PRINCIPAL");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 380);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Sistema");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Cerrar Sesion");
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnMantenimineto = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimineto);
		
		mnSolAbas = new JMenuItem("Solicitud de Abastecimiento");
		mnSolAbas.addActionListener(this);
		mnMantenimineto.add(mnSolAbas);
		
		JMenuItem mnsolContrata = new JMenuItem("Solicitud de Contratacion");
		mnsolContrata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedMnsolContrata(arg0);
			}
		});
		mnMantenimineto.add(mnsolContrata);
		
		JMenuItem mnProcesoSeleccion = new JMenuItem("Registro Proveedores-Proceso de selecci\u00F3n");
		mnProcesoSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnProcesoSeleccion(e);
			}
		});
		mnMantenimineto.add(mnProcesoSeleccion);
		
		JMenuItem mnBasesAdmin = new JMenuItem("Bases Administrativas");
		mnBasesAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnBasesAdmin(e);
			}
		});
		mnMantenimineto.add(mnBasesAdmin);
		
		JMenuItem mnObjeto = new JMenuItem("Objeto");
		mnObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnObjeto(e);
			}
		});
		mnMantenimineto.add(mnObjeto);
		
		JMenuItem mnExpBP = new JMenuItem("Expediente Buena Pro");
		mnExpBP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnExpBP(e);
			}
		});
		mnMantenimineto.add(mnExpBP);
		
		JMenuItem mnConCCP = new JMenuItem("Certificado de credito Presupuestario");
		mnConCCP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnConCCP(e);
			}
		});
		mnMantenimineto.add(mnConCCP);
		
		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		JMenuItem mnConSolAbas = new JMenuItem("Solicitud de Abastecimiento");
		mnConSolAbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedMnConSolAbas(arg0);
			}
		});
		mnConsulta.add(mnConSolAbas);
		
		JMenuItem mnConSolCont = new JMenuItem("Solicitud de contrataci\u00F3n");
		mnConSolCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnConSolCont(e);
			}
		});
		mnConsulta.add(mnConSolCont);
		
		JMenuItem mnConBases = new JMenuItem("Bases Administrativas");
		mnConBases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnConBases(e);
			}
		});
		mnConsulta.add(mnConBases);
		
		JMenu mnNewMenu_3 = new JMenu("Contratar");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mnContrato = new JMenuItem("Elaborar Contrato");
		mnContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnContrato(e);
			}
		});
		mnNewMenu_3.add(mnContrato);
		
		JMenu mnNewMenu_4 = new JMenu("Reporte");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mnRPSolAbas = new JMenuItem("Solicitudes de Abastecimiento");
		mnRPSolAbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnRPSolAbas(e);
			}
		});
		mnNewMenu_4.add(mnRPSolAbas);
		
		JMenuItem mnRPSolContrata = new JMenuItem("Solicitudes de contrataci\u00F3n");
		mnRPSolContrata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnRPSolContrata(e);
			}
		});
		mnNewMenu_4.add(mnRPSolContrata);
		
		JMenuItem mnRPBases = new JMenuItem("Bases Administrativas");
		mnRPBases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMnRPBases(e);
			}
		});
		mnNewMenu_4.add(mnRPBases);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido: ");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBienvenido.setBounds(10, 11, 98, 22);
		contentPane.add(lblBienvenido);
		
		lblNombre = new JLabel("NOMBRE Y APELLIDO");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(104, 15, 255, 14);
		contentPane.add(lblNombre);
		
		JLabel lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigo.setBounds(386, 15, 72, 14);
		contentPane.add(lblCodigo);
		
		lblcodigo = new JLabel("1");
		lblcodigo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcodigo.setBounds(445, 15, 46, 14);
		contentPane.add(lblcodigo);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnSolAbas) {
			actionPerformedMntmNewMenuItem_1(e);
		}
		if (e.getSource() == mntmNewMenuItem) {
			actionPerformedMntmNewMenuItem(e);
		}
	}
	protected void actionPerformedMntmNewMenuItem(ActionEvent e) {
		dispose();
	}
	protected void actionPerformedMntmNewMenuItem_1(ActionEvent e) {
		
		if(cod_car>=1&cod_div==1){
			frmAbastecimiento frm=new frmAbastecimiento();
			frm.setVisible(true);
		}
		
		else if(cod_car==5){
			frmAbastecimiento frm=new frmAbastecimiento();
			frm.setVisible(true);
		}
		else{
			mensaje("Solo personal de Mesa de partes con cargo de secretario o mayor!");
		}
		
		
	
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
	
	
	protected void actionPerformedMnsolContrata(ActionEvent arg0) {
		
		if(cod_car==6&cod_div==8){
			frmMantenimientoSC frm=new frmMantenimientoSC();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmMantenimientoSC frm=new frmMantenimientoSC();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal de Gerencia de Programacion con cargo representante");
		}
		
		
	}
	protected void actionPerformedMnProcesoSeleccion(ActionEvent e) {
		
		if(cod_car>=3&cod_div==5){
			guiProvedor frm=new guiProvedor();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			guiProvedor frm=new guiProvedor();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del area de ADHOC y con cargo mayor a supervisor");
		}
		
		
		
	}
	protected void actionPerformedMnBasesAdmin(ActionEvent e) {
		if(cod_car>=4&cod_div==6){
			frmBasesAdministrativas frm=new frmBasesAdministrativas();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmBasesAdministrativas frm=new frmBasesAdministrativas();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de ADHOC y con cargo mayor a supervisor");
		}
	}
	protected void actionPerformedMnObjeto(ActionEvent e) {
		if(cod_car>=1&cod_div==5){
			frmObjetos frm=new frmObjetos();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmObjetos frm=new frmObjetos();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de ADHOC y con cargo mayor a supervisor");
		}
	}
	protected void actionPerformedMnExpBP(ActionEvent e) {
		if(cod_car>=3&cod_div==5){
			FrmBuenapro frm=new FrmBuenapro();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			FrmBuenapro frm=new FrmBuenapro();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de ADHOC y con cargo mayor a supervisor");
		}
	}
	protected void actionPerformedMnConCCP(ActionEvent e) {
		if(cod_car>=1&cod_div==5){
			FrmCcp frm=new FrmCcp();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			FrmCcp frm=new FrmCcp();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de ADHOC y con cargo mayor a supervisor");
		}
	}
	protected void actionPerformedMnConSolAbas(ActionEvent arg0) {
		
		
		if(cod_car>=4&cod_div==2){
			frmConsultaAbastecimiento frm=new frmConsultaAbastecimiento();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmConsultaAbastecimiento frm=new frmConsultaAbastecimiento();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de Programaci�n y con cargo mayor a supervisor");
		}
		
		
	}
	protected void actionPerformedMnConSolCont(ActionEvent e) {
		
		if(cod_car>=4&cod_div==2){
			ConsultaSolContratacion frm=new ConsultaSolContratacion();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			ConsultaSolContratacion frm=new ConsultaSolContratacion();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de Programaci�n y con cargo mayor a supervisor");
		}
		
		
	}
	protected void actionPerformedMnConBases(ActionEvent e) {
		
		if(cod_car>=4&cod_div==2){
			frmConsultaBasesAdmi frm=new frmConsultaBasesAdmi();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmConsultaBasesAdmi frm=new frmConsultaBasesAdmi();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de Programaci�n y con cargo mayor a supervisor");
		}
		
	}
	protected void actionPerformedMnContrato(ActionEvent e) {
		
		if(cod_car>=4&cod_div==2){
			frmGenerarContrato frm=new frmGenerarContrato();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmGenerarContrato frm=new frmGenerarContrato();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de Programaci�n y con cargo mayor a supervisor");
		}
	}
	protected void actionPerformedMnRPSolAbas(ActionEvent e) {
		
		if(cod_car>=4&cod_div==2){
			frmReporteSolAbas frm=new frmReporteSolAbas();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmReporteSolAbas frm=new frmReporteSolAbas();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de Programaci�n y con cargo mayor a supervisor");
		}
		
	}
	protected void actionPerformedMnRPSolContrata(ActionEvent e) {
		
		if(cod_car>=4&cod_div==2){
			ReporteSolicitudContrata frm=new ReporteSolicitudContrata();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			ReporteSolicitudContrata frm=new ReporteSolicitudContrata();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de Programaci�n y con cargo mayor a supervisor");
		}
		
	}
	protected void actionPerformedMnRPBases(ActionEvent e) {
		
		if(cod_car>=4&cod_div==2){
			frmReporteBaseAdmin frm =new frmReporteBaseAdmin();
			frm.setVisible(true);
		}
		else if(cod_car==5){
			frmReporteBaseAdmin frm =new frmReporteBaseAdmin();
			frm.setVisible(true);
		}
		
		else{
			mensaje("Solo personal del ar�a de Programaci�n y con cargo mayor a supervisor");
		}
		
		
	}
}
