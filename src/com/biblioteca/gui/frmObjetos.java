package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlObjetoDAO;
import com.biblioteca.entidad.Objeto;
import com.biblioteca.interfaces.ObjetoDAO;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class frmObjetos extends JFrame implements ActionListener, MouseListener {
      private MySqlObjetoDAO ObjetoDAO=new MySqlObjetoDAO();
	
	private JPanel contentPane;
	private JTextField txtCodigoObj;
	private JTextField txtNombreObj;
	private JTextField txtDescObj;
	private JTable tblObjeto;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnNuevo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmObjetos frame = new frmObjetos();
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
	public frmObjetos() {
		setResizable(false);
		setTitle("Mantenimiento de Objetos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mantenimiento de Objetos");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel.setBounds(154, 11, 286, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo de Objeto");
		lblNewLabel_1.setBounds(238, 55, 105, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(116, 107, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Descripcion");
		lblNewLabel_3.setBounds(380, 107, 84, 14);
		contentPane.add(lblNewLabel_3);
		
		txtCodigoObj = new JTextField();
		txtCodigoObj.setBackground(Color.LIGHT_GRAY);
		txtCodigoObj.setEditable(false);
		txtCodigoObj.setBounds(238, 80, 86, 20);
		contentPane.add(txtCodigoObj);
		txtCodigoObj.setColumns(10);
		
		txtNombreObj = new JTextField();
		txtNombreObj.setBounds(26, 132, 229, 20);
		contentPane.add(txtNombreObj);
		txtNombreObj.setColumns(10);
		
		txtDescObj = new JTextField();
		txtDescObj.setBounds(298, 132, 229, 20);
		contentPane.add(txtDescObj);
		txtDescObj.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 226, 562, 176);
		contentPane.add(scrollPane);
		
		tblObjeto = new JTable();
		tblObjeto.addMouseListener(this);
		tblObjeto.setFillsViewportHeight(true);
		tblObjeto.setBackground(Color.WHITE);
		tblObjeto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Descripcion"
			}
		));
		scrollPane.setViewportView(tblObjeto);
		
		btnAgregar = new JButton("");
		btnAgregar.setIcon(new ImageIcon(frmObjetos.class.getResource("/iconos/add (2).png")));
		btnAgregar.setToolTipText("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(37, 179, 97, 36);
		contentPane.add(btnAgregar);
		
		btnModificar = new JButton("");
		btnModificar.setIcon(new ImageIcon(frmObjetos.class.getResource("/iconos/edit.png")));
		btnModificar.setToolTipText("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(170, 179, 97, 36);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(frmObjetos.class.getResource("/iconos/delete (1).png")));
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(292, 179, 97, 36);
		contentPane.add(btnEliminar);
		
		btnNuevo = new JButton("");
		btnNuevo.setIcon(new ImageIcon(frmObjetos.class.getResource("/iconos/add-file.png")));
		btnNuevo.setToolTipText("NUEVO");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNuevo(arg0);
			}
		});
		btnNuevo.setBounds(433, 179, 97, 36);
		contentPane.add(btnNuevo);
		
		listado();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		String nomobj, descobj;
		nomobj=txtNombreObj.getText();
		descobj=txtDescObj.getText();
		if(nomobj.trim().equals("")) {
			mensaje("El campo 'Nombre' no puede estar vacio");
			txtNombreObj.requestFocus();
		}
		else if(nomobj.matches("[a-zA-z].{5,20}")==false) {
			mensaje("el campo 'Nombre' debe contener solo letras y minimo 5 caracteres, maximo 20");
			txtNombreObj.requestFocus();
		}
		else if(descobj.trim().equals("")) {
			mensaje("el campo 'Descripcion' no puede estar vacio");
			txtDescObj.requestFocus();
		}
		else if(nomobj.matches("[a-zA-z].{5,100}")==false) {
			mensaje("el campo 'Descripcion' debe contener solo letras y minimo 5 caracteres, maximo 100");
			txtDescObj.requestFocus();
		}
		else {
		int salida;
		String NomObj, DescObj;
		NomObj=txtNombreObj.getText();
		DescObj=txtDescObj.getText();
		Objeto bin=new Objeto();
		bin.setNomObj(NomObj);
		bin.setDescObj(DescObj);
		salida=ObjetoDAO.agregar(bin);
		if(salida>0) {
			mensaje("objeto Añadido");
			listado();
			limpiar();
		}
		else
			mensaje("Error al Anadir el Objeto");
	}
	}
	
	
	protected void actionPerformedBtnModificar(ActionEvent e) {
		String nomobj, descobj;
		nomobj=txtNombreObj.getText();
		descobj=txtDescObj.getText();
		if(nomobj.trim().equals("")) {
			mensaje("El campo 'Nombre' no puede estar vacio");
			txtNombreObj.requestFocus();
		}
		else if(nomobj.matches("[a-zA-z].{5,20}")==false) {
			mensaje("el campo 'Nombre' debe contener solo letras y minimo 5 caracteres, maximo 20");
			txtNombreObj.requestFocus();
		}
		else if(descobj.trim().equals("")) {
			mensaje("el campo 'Descripcion' no puede estar vacio");
			txtDescObj.requestFocus();
		}
		else if(nomobj.matches("[a-zA-z].{5,100}")==false) {
			mensaje("el campo 'Descripcion' debe contener solo letras y minimo 5 caracteres, maximo 100");
			txtDescObj.requestFocus();
		}
		else {
		int codobj,salida;
		String nomObj, descObj;
		codobj=Integer.parseInt(txtCodigoObj.getText());
		nomObj=txtNombreObj.getText();
		descObj=txtDescObj.getText();
		Objeto bin=new Objeto();
		bin.setCodigoObj(codobj);
		bin.setNomObj(nomObj);
		bin.setDescObj(descObj);
		salida=ObjetoDAO.actualiza(bin);
		if(salida>0) {
			mensaje("Objeto Actualizado");
			listado();
			limpiar();
		}
		else
			mensaje("Error al Actualizar el Objeto");
}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		int button, codobj, salida;
		button=JOptionPane.showConfirmDialog(null, "Seguro que desea elimniar el objeto ?","Sistema",
				           JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(button==0) {
			codobj=Integer.parseInt(txtCodigoObj.getText());
			salida=ObjetoDAO.elimina(codobj);
			if(salida>0) {
				mensaje("Objeto Eliminado");
				listado();
				limpiar();
			}
			else
				mensaje("Error al eliminar el Objeto");
		}
	}
	
	void listado() {
		DefaultTableModel dtmObjeto=(DefaultTableModel) tblObjeto.getModel();
		dtmObjeto.setRowCount(0);
		ArrayList<Objeto> lista=ObjetoDAO.listar();
		for(Objeto bin:lista) {
			Object row[]= {bin.getCodigoObj(), bin.getNomObj(), bin.getDescObj()};
			dtmObjeto.addRow(row);
		}		
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblObjeto) {
			mouseClickedTblObjeto(e);
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
	protected void mouseClickedTblObjeto(MouseEvent e) {
		int posFila;
		String codobj, nomobj, descobj;
		posFila=tblObjeto.getSelectedRow();
		codobj=tblObjeto.getValueAt(posFila, 0).toString();
		nomobj=tblObjeto.getValueAt(posFila, 1).toString();
		descobj=tblObjeto.getValueAt(posFila, 2).toString();
		txtCodigoObj.setText(codobj);
		txtNombreObj.setText(nomobj);
		txtDescObj.setText(descobj);
	}
	
	
	
	void limpiar(){
		txtCodigoObj.setText("");
		txtDescObj.setText("");
		txtNombreObj.setText("");
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		
		limpiar();
	}
}
