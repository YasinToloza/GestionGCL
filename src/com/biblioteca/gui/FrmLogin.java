package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.biblioteca.controlador.MysqlEmpleadoDAO;
import com.biblioteca.entidad.empleado;
import com.biblioteca.utils.MySqlConexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FrmLogin extends JFrame {

	private MysqlEmpleadoDAO empleadoDAO=new MysqlEmpleadoDAO();

	private JPanel contentPane;
	private JTextField txtusuario;
	private JPasswordField txtclave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setResizable(false);
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 306, 356);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 170, 94, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClave.setBounds(10, 201, 94, 14);
		contentPane.add(lblClave);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(85, 168, 184, 20);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		JButton button = new JButton("INICIAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedButton(arg0);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBackground(new Color(0, 153, 102));
		button.setAutoscrolls(true);
		button.setBounds(77, 250, 163, 25);
		contentPane.add(button);
		
		txtclave = new JPasswordField();
		txtclave.setBounds(85, 199, 184, 20);
		contentPane.add(txtclave);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/iconos/usuario.png")));
		lblNewLabel.setBounds(83, 11, 139, 157);
		contentPane.add(lblNewLabel);
	}
	protected void actionPerformedButton(ActionEvent arg0) {
			
	
				String vLogin,vClave;
				vLogin=txtusuario.getText();
				vClave=new String(txtclave.getPassword());
				empleado emp=empleadoDAO.iniciosesion(vLogin, vClave);
				if(emp==null) {
					JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrectos");
					txtusuario.setText("");
					txtclave.setText("");
					txtusuario.requestFocus();
				}
				else {
					
					GuiPrincipal frm=new GuiPrincipal();
					frm.lblcodigo.setText(""+emp.getCodigo());
					frm.lblNombre.setText(emp.getNombres()+" "+emp.getApellidos());
					frm.cod_car=emp.getCodcar();
					frm.cod_div=emp.getCodDivi();
					frm.setVisible(true);
					dispose();
				}
	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(null, m);
		}
}
