package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.biblioteca.componente.JComboBoxDB;
import com.biblioteca.controlador.MySqlAbastecimientoDAO;
import com.biblioteca.controlador.MySqlBasesAdmiDAO;
import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.entidad.BasesAdministrativas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class frmConsultaBasesAdmi extends JFrame implements ActionListener, KeyListener, MouseListener {
	private MySqlBasesAdmiDAO BasesAdminDAO=new MySqlBasesAdmiDAO();
	ArrayList<BasesAdministrativas> info=null;

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JComboBoxDB cboEstado;
	private JTable tblBases;
	private JButton btnBuscar;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConsultaBasesAdmi frame = new frmConsultaBasesAdmi();
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
	public frmConsultaBasesAdmi() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 791, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Por Codigo:");
		lblNewLabel_1.setBounds(10, 97, 84, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setBounds(82, 94, 96, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(frmConsultaBasesAdmi.class.getResource("/iconos/search (1).png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(188, 83, 108, 37);
		contentPane.add(btnBuscar);
		
		cboEstado = new JComboBoxDB("select E.cod_est, E.des_est\r\n"
				+ "				from tb_estado E\r\n"
				+ "				where cod_est in ('2')");
		cboEstado.setBounds(306, 93, 108, 22);
		contentPane.add(cboEstado);
		
		btnModificar = new JButton("");
		btnModificar.setIcon(new ImageIcon(frmConsultaBasesAdmi.class.getResource("/iconos/maintenance.png")));
		btnModificar.setToolTipText("Modificar Estado");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(424, 83, 124, 37);
		contentPane.add(btnModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 139, 760, 251);
		contentPane.add(scrollPane);
		
		tblBases = new JTable();
		tblBases.addMouseListener(this);
		tblBases.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripcion", "Plazo", "C.Empleado", "Fecha", "Estado", "C.Sol Contrata"
			}
		));
		tblBases.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblBases);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 775, 38);
		contentPane.add(panel);
		
		JLabel lblConsultarBasesAdministrativas = new JLabel("CONSULTAR BASES ADMINISTRATIVAS PENDIENTES");
		lblConsultarBasesAdministrativas.setForeground(Color.WHITE);
		lblConsultarBasesAdministrativas.setFont(new Font("Tahoma", Font.BOLD, 23));
		panel.add(lblConsultarBasesAdministrativas);
		
		listarTodo();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		String codigo;
		codigo=txtCodigo.getText();
		if (codigo.trim().equals("")) {
			mensaje("Ingrese un codigo");
		}
		else
		   listaFiltrada(txtCodigo.getText());
	}
	
	protected void actionPerformedBtnModificar(ActionEvent e) {
		int codSol,codEst, salida;
		codSol=Integer.parseInt(txtCodigo.getText());
		codEst=cboEstado.getSelectedIndex()+2;
		BasesAdministrativas bin=new BasesAdministrativas();
		bin.setCod_sol(codSol);
		bin.setEstado(codEst);
		salida=BasesAdminDAO.ActualizarEstado(bin);
		if (salida>0) {
			mensaje("Estado actualizado");
			listarTodo();
		}
		else
			mensaje("Error en la actualizacion de la Solicitud");
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == txtCodigo) {
			keyPressedTxtCodigo(e);
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyPressedTxtCodigo(KeyEvent e) {
		listarTodo();
	}
	
	
void listarTodo(){
		
		DefaultTableModel basesadmi= (DefaultTableModel) tblBases.getModel();
		
		basesadmi.setRowCount(0);
		ArrayList<BasesAdministrativas> lista=BasesAdminDAO.listarTodoXConsulta();
		for(BasesAdministrativas bean:lista){
			Object row[]={
					bean.getCodigoBases(),bean.getDes_bases(),bean.getPlazo(),bean.getCodEmp(),bean.getFecha(),bean.getNombrestado(),bean.getCod_sol()
			};
			basesadmi.addRow(row);
		}
	}

void listaFiltrada(String cod){
	DefaultTableModel basesadmi=(DefaultTableModel) tblBases.getModel();	
	basesadmi.setRowCount(0);		
	ArrayList<BasesAdministrativas> lista=BasesAdminDAO.listaFiltrada(Integer.parseInt(txtCodigo.getText()));	
	for(BasesAdministrativas bean:lista){
		Object row[]={
				bean.getCodigoBases(),bean.getDes_bases(),bean.getPlazo(),bean.getCodEmp(),bean.getFecha(),bean.getNombrestado(),bean.getCod_sol()
		};
		basesadmi.addRow(row);
	}
}	

void mensaje(String m) {
	JOptionPane.showMessageDialog(this, m);
}

public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblBases) {
			mouseClickedTblBases(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblBases(MouseEvent e) {
		String codSol, codEst;
		int posFila;
		posFila=tblBases.getSelectedRow();
		codSol=tblBases.getValueAt(posFila, 0).toString();
		codEst=tblBases.getValueAt(posFila, 5).toString();	
		txtCodigo.setText(codSol);
		cboEstado.setSelectedItem(codEst);
	}
}
