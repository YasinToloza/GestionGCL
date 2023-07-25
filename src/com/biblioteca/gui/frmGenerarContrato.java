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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import com.biblioteca.controlador.MySqlContratoDAO;
import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.entidad.BasesAdministrativas;
import com.biblioteca.entidad.Buenapro;
import com.biblioteca.entidad.CCP;
import com.biblioteca.entidad.Contrato;
import com.biblioteca.entidad.Provedor;
import com.biblioteca.entidad.SolicitudContrata;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class frmGenerarContrato extends JFrame {

	
	private MySqlContratoDAO contratoDAO=new MySqlContratoDAO();
	
	public static JPanel contentPane;
	public static JTextField txtCodContrato;
	public static JTextField txtFechaContrata;
	public static JTextField txtSolContrata;
	public static JTextField txtDesSolContrata;
	public static JTextField txtTipocontratoSol;
	public static JTextField txtFechaSolContrata;
	public static JTextField txtCodSabas;
	public static JTextField txtCodBases;
	public static JTextField txtDesBases;
	public static JTextField txtPlazoBases;
	public static JTextField txtFechaBases;
	public static JTextField txtCodBuenPro;
	public static JTextField txtAsuntoBP;
	public static JTextField txtReferenciaBP;
	public static JTextField txtFechaBP;
	public static JTextField txtCodProv;
	private JButton btnGeneraContrato;
	private JButton btnNuevo;
	private JButton btnBuscarBases;
	private JButton btnBuscarSolContrata;
	public static JTextField txtCodCCP;
	private JLabel xd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmGenerarContrato frame = new frmGenerarContrato();
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
	public frmGenerarContrato() {
		setResizable(false);
		setTitle("Generar Contrato");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSolicitudDeContratacin = new JLabel("Solicitud de contrataci\u00F3n");
		lblSolicitudDeContratacin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSolicitudDeContratacin.setBounds(20, 117, 212, 14);
		contentPane.add(lblSolicitudDeContratacin);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 777, 32);
		contentPane.add(panel);
		
		JLabel lblGenerarContrato = new JLabel("GENERAR CONTRATO");
		panel.add(lblGenerarContrato);
		lblGenerarContrato.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGenerarContrato.setForeground(Color.WHITE);
		
		txtCodContrato = new JTextField();
		txtCodContrato.setEditable(false);
		txtCodContrato.setBounds(632, 43, 135, 20);
		contentPane.add(txtCodContrato);
		txtCodContrato.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(562, 46, 60, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblFecha = new JLabel("Fecha : ");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha.setBounds(10, 82, 60, 14);
		contentPane.add(lblFecha);
		
		txtFechaContrata = new JTextField();
		txtFechaContrata.setEditable(false);
		txtFechaContrata.setBounds(66, 80, 96, 20);
		contentPane.add(txtFechaContrata);
		txtFechaContrata.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 123, 757, 102);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("C\u00F3digo : ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 25, 60, 14);
		panel_1.add(label);
		
		txtSolContrata = new JTextField();
		txtSolContrata.setEditable(false);
		txtSolContrata.setColumns(10);
		txtSolContrata.setBounds(65, 23, 69, 20);
		panel_1.add(txtSolContrata);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescripcin.setBounds(10, 61, 86, 14);
		panel_1.add(lblDescripcin);
		
		txtDesSolContrata = new JTextField();
		txtDesSolContrata.setEditable(false);
		txtDesSolContrata.setColumns(10);
		txtDesSolContrata.setBounds(86, 59, 220, 20);
		panel_1.add(txtDesSolContrata);
		
		JLabel lblTipoDeContrato = new JLabel("Tipo de Contrato");
		lblTipoDeContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoDeContrato.setBounds(169, 25, 135, 14);
		panel_1.add(lblTipoDeContrato);
		
		txtTipocontratoSol = new JTextField();
		txtTipocontratoSol.setEditable(false);
		txtTipocontratoSol.setColumns(10);
		txtTipocontratoSol.setBounds(285, 23, 135, 20);
		panel_1.add(txtTipocontratoSol);
		
		JLabel lblFecha_1 = new JLabel("Fecha");
		lblFecha_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha_1.setBounds(320, 62, 86, 14);
		panel_1.add(lblFecha_1);
		
		txtFechaSolContrata = new JTextField();
		txtFechaSolContrata.setEditable(false);
		txtFechaSolContrata.setColumns(10);
		txtFechaSolContrata.setBounds(374, 59, 86, 20);
		panel_1.add(txtFechaSolContrata);
		
		JLabel lblCdigoSabastecimiento = new JLabel("C\u00F3digo S.Abastecimiento");
		lblCdigoSabastecimiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCdigoSabastecimiento.setBounds(446, 25, 168, 14);
		panel_1.add(lblCdigoSabastecimiento);
		
		txtCodSabas = new JTextField();
		txtCodSabas.setEditable(false);
		txtCodSabas.setColumns(10);
		txtCodSabas.setBounds(608, 23, 69, 20);
		panel_1.add(txtCodSabas);
		
		btnBuscarSolContrata = new JButton("");
		btnBuscarSolContrata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscarSolContrata(e);
			}
		});
		btnBuscarSolContrata.setToolTipText("BUSCAR");
		btnBuscarSolContrata.setIcon(new ImageIcon(frmGenerarContrato.class.getResource("/iconos/search.png")));
		btnBuscarSolContrata.setBounds(687, 54, 60, 37);
		panel_1.add(btnBuscarSolContrata);
		
		JLabel label_1 = new JLabel("C\u00F3digo CCP");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(489, 64, 119, 14);
		panel_1.add(label_1);
		
		txtCodCCP = new JTextField();
		txtCodCCP.setEditable(false);
		txtCodCCP.setColumns(10);
		txtCodCCP.setBounds(583, 61, 69, 20);
		panel_1.add(txtCodCCP);
		
		btnGeneraContrato = new JButton("");
		btnGeneraContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGeneraContrato(e);
			}
		});
		btnGeneraContrato.setToolTipText("GENERAR CONTRATO");
		btnGeneraContrato.setIcon(new ImageIcon(frmGenerarContrato.class.getResource("/iconos/floppy-disk.png")));
		btnGeneraContrato.setBounds(589, 520, 74, 49);
		contentPane.add(btnGeneraContrato);
		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNuevo(e);
			}
		});
		btnNuevo.setToolTipText("NUEVO CONTRATO");
		btnNuevo.setIcon(new ImageIcon(frmGenerarContrato.class.getResource("/iconos/add-file.png")));
		btnNuevo.setBounds(673, 520, 74, 49);
		contentPane.add(btnNuevo);
		
		JLabel lblBasesAdministrativas = new JLabel("Bases Administrativas");
		lblBasesAdministrativas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBasesAdministrativas.setBounds(20, 254, 212, 14);
		contentPane.add(lblBasesAdministrativas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 260, 757, 102);
		contentPane.add(panel_2);
		
		JLabel label_2 = new JLabel("C\u00F3digo : ");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 25, 60, 14);
		panel_2.add(label_2);
		
		txtCodBases = new JTextField();
		txtCodBases.setEditable(false);
		txtCodBases.setColumns(10);
		txtCodBases.setBounds(65, 23, 69, 20);
		panel_2.add(txtCodBases);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(10, 61, 86, 14);
		panel_2.add(label_3);
		
		txtDesBases = new JTextField();
		txtDesBases.setEditable(false);
		txtDesBases.setColumns(10);
		txtDesBases.setBounds(86, 59, 220, 20);
		panel_2.add(txtDesBases);
		
		JLabel lblPlazo = new JLabel("Plazo");
		lblPlazo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPlazo.setBounds(205, 25, 60, 14);
		panel_2.add(lblPlazo);
		
		txtPlazoBases = new JTextField();
		txtPlazoBases.setEditable(false);
		txtPlazoBases.setColumns(10);
		txtPlazoBases.setBounds(257, 23, 135, 20);
		panel_2.add(txtPlazoBases);
		
		xd = new JLabel("Fecha");
		xd.setFont(new Font("Tahoma", Font.BOLD, 12));
		xd.setBounds(320, 62, 86, 14);
		panel_2.add(xd);
		
		txtFechaBases = new JTextField();
		txtFechaBases.setEditable(false);
		txtFechaBases.setColumns(10);
		txtFechaBases.setBounds(374, 59, 86, 20);
		panel_2.add(txtFechaBases);
		
		btnBuscarBases = new JButton("");
		btnBuscarBases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscarBases(e);
			}
		});
		btnBuscarBases.setToolTipText("BUSCAR");
		btnBuscarBases.setIcon(new ImageIcon(frmGenerarContrato.class.getResource("/iconos/search.png")));
		btnBuscarBases.setBounds(509, 38, 60, 37);
		panel_2.add(btnBuscarBases);
		
		JLabel lblExpedienteBuenaPro = new JLabel("Expediente Buena Pro");
		lblExpedienteBuenaPro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpedienteBuenaPro.setBounds(20, 391, 212, 14);
		contentPane.add(lblExpedienteBuenaPro);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 397, 757, 102);
		contentPane.add(panel_3);
		
		JLabel label_4 = new JLabel("C\u00F3digo : ");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 25, 60, 14);
		panel_3.add(label_4);
		
		txtCodBuenPro = new JTextField();
		txtCodBuenPro.setEditable(false);
		txtCodBuenPro.setColumns(10);
		txtCodBuenPro.setBounds(65, 23, 69, 20);
		panel_3.add(txtCodBuenPro);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAsunto.setBounds(10, 61, 86, 14);
		panel_3.add(lblAsunto);
		
		txtAsuntoBP = new JTextField();
		txtAsuntoBP.setEditable(false);
		txtAsuntoBP.setColumns(10);
		txtAsuntoBP.setBounds(86, 59, 220, 20);
		panel_3.add(txtAsuntoBP);
		
		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReferencia.setBounds(161, 25, 86, 14);
		panel_3.add(lblReferencia);
		
		txtReferenciaBP = new JTextField();
		txtReferenciaBP.setEditable(false);
		txtReferenciaBP.setColumns(10);
		txtReferenciaBP.setBounds(243, 23, 135, 20);
		panel_3.add(txtReferenciaBP);
		
		JLabel label_8 = new JLabel("Fecha");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(320, 62, 86, 14);
		panel_3.add(label_8);
		
		txtFechaBP = new JTextField();
		txtFechaBP.setEditable(false);
		txtFechaBP.setColumns(10);
		txtFechaBP.setBounds(374, 59, 86, 20);
		panel_3.add(txtFechaBP);
		
		JLabel lblCodigoDeProveedor = new JLabel("Codigo Proveedor");
		lblCodigoDeProveedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigoDeProveedor.setBounds(388, 25, 168, 14);
		panel_3.add(lblCodigoDeProveedor);
		
		txtCodProv = new JTextField();
		txtCodProv.setEditable(false);
		txtCodProv.setColumns(10);
		txtCodProv.setBounds(513, 23, 69, 20);
		panel_3.add(txtCodProv);
		
		JButton btnBuscarBuenaPro = new JButton("");
		btnBuscarBuenaPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscarBuenaPro(e);
			}
		});
		btnBuscarBuenaPro.setToolTipText("BUSCAR");
		btnBuscarBuenaPro.setIcon(new ImageIcon(frmGenerarContrato.class.getResource("/iconos/search.png")));
		btnBuscarBuenaPro.setBounds(687, 36, 60, 37);
		panel_3.add(btnBuscarBuenaPro);
		
		
		txtFechaContrata.setText(fechaActual());
		txtCodContrato.setText(contratoDAO.numeroContrato()+"");
	}
	protected void actionPerformedBtnGeneraContrato(ActionEvent e) {
		
		
		GuiPrincipal frm=new GuiPrincipal();
		String empleado=frm.lblcodigo.getText();
		Contrato ct=new Contrato();
		BasesAdministrativas bases=new BasesAdministrativas();
		Provedor prov=new Provedor();
		SolicitudContrata solcon=new SolicitudContrata();
		Abastecimiento abas=new Abastecimiento();
		CCP ccp=new CCP();
		int codsolcontrata=Integer.parseInt(txtSolContrata.getText());
		int codbases=Integer.parseInt(txtCodBases.getText());
		int codexpBP=Integer.parseInt(txtCodBuenPro.getText());
		
		
		
		ct.setCod_contrato(Integer.parseInt(txtCodContrato.getText()));
		ct.setCod_emp(Integer.parseInt(frm.lblcodigo.getText()));
		ct.setCod_sol_con(codsolcontrata);
		ct.setFechaContrato(txtFechaContrata.getText());
		ct.setCod_bases(codbases);
		ct.setCod_expBP(codexpBP);
		
		bases.setCodigoBases(codbases);
		bases.setEstado(4);
		
		prov.setCodPro(Integer.parseInt(txtCodProv.getText()));
		prov.setEstado(3);
		
		solcon.setCodigo(codsolcontrata);
		solcon.setEstado(4);
		
		abas.setCodigoSol(Integer.parseInt(txtCodSabas.getText()));
		abas.setCodigoEst(4);
		
		ccp.setCodigoccp(Integer.parseInt(txtCodCCP.getText()));
		ccp.setEstado(4);
		
		
		
		int salida;
		salida=contratoDAO.save(ct, bases, ccp, prov,solcon , abas);
		
		if(salida>0){
			mensaje("Contrato generado con exito!");
		}
		else{
			mensaje("Error en el registro");
		}
		
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
	}
	protected void actionPerformedBtnBuscarSolContrata(ActionEvent e) {
		
		frmBuscarSolConparaContrato frm= new frmBuscarSolConparaContrato();
		frm.setVisible(true);
	}
	protected void actionPerformedBtnBuscarBases(ActionEvent e) {
		
		frmBuscarbasesParaContrato frm=new frmBuscarbasesParaContrato();
		frm.setVisible(true);
	}
	
	protected void actionPerformedBtnBuscarBuenaPro(ActionEvent e) {
		
		frmBuscarBPparaContrato frm=new frmBuscarBPparaContrato();
		
		frm.setVisible(true);
	}
	
	
	public String fechaActual(){
		Date fecha=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("YYYY-MM-dd");
		return formato.format(fecha);
	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
}
