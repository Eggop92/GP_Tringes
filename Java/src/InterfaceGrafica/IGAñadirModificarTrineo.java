package InterfaceGrafica;

import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import excepciones.GenericException;

import Tringes.GestorTrineos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.JComboBox;

public class IGAñadirModificarTrineo extends JFrame{

	private String pTipo;
	private int pID_Club;
	private int pID_Trineo;
	
	private JFrame jfAñadirModificarTrineo;
	private JTextField textID_Trineo;
	private JTextField textFabricante;
	private JComboBox comboBox_Dia;
	private JComboBox comboBox_Mes;
	private JComboBox comboBox_Año;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAñadirModificarTrineo window = new IGAñadirModificarTrineo("Anadir",0,0);//sara- añadido parametro del trineo
					window.jfAñadirModificarTrineo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public IGAñadirModificarTrineo( String pPTipo,  int pIDClub, int pIDTrineo) {
		pTipo=pPTipo;
		pID_Club= pIDClub;
		pID_Trineo= pIDTrineo;
		
		jfAñadirModificarTrineo = new JFrame();
		jfAñadirModificarTrineo.setBounds(100, 100, 324, 205);
		setResizable(false);
		jfAñadirModificarTrineo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfAñadirModificarTrineo.getContentPane().setLayout(null);
		
		if (pTipo.equals("Anadir")){
			jfAñadirModificarTrineo.setTitle(Strings.IGAñadirModificarTrineo_TituloVentanaAñadir);
		}else{
			setTitle(Strings.IGAñadirModificarTrineo_TituloVentanaModificar);
		}
		
		JLabel lblIdtrineo = new JLabel(Strings.IGAñadirModificarTrineo_lblIdtrineo);
		lblIdtrineo.setBounds(10, 11, 59, 17);
		jfAñadirModificarTrineo.getContentPane().add(lblIdtrineo);
		
		JLabel lblFabricante = new JLabel(Strings.IGAñadirModificarTrineo_lblFabricante);
		lblFabricante.setBounds(10, 50, 72, 14);
		jfAñadirModificarTrineo.getContentPane().add(lblFabricante);
		
		JLabel lblFechaDeFabricacion = new JLabel(Strings.IGAñadirModificarTrineo_lblFechaDeFabricacion);
		lblFechaDeFabricacion.setBounds(10, 84, 107, 14);
		jfAñadirModificarTrineo.getContentPane().add(lblFechaDeFabricacion);
		
		comboBox_Dia = new JComboBox();
		comboBox_Dia.setBounds(127, 81, 40, 20);
		comboBox_Dia.addItem("");
		for(int dia=1;dia<=31;dia++){
			comboBox_Dia.addItem(dia);
		}
		jfAñadirModificarTrineo.getContentPane().add(comboBox_Dia);
		
		comboBox_Mes = new JComboBox();
		comboBox_Mes.setBounds(177, 81, 40, 20);
		comboBox_Mes.addItem("");
		for(int mes=1;mes<=12;mes++){
			comboBox_Mes.addItem(mes);
		}
		jfAñadirModificarTrineo.getContentPane().add(comboBox_Mes);
		
		comboBox_Año = new JComboBox();
		comboBox_Año.setBounds(227, 81, 59, 20);
		comboBox_Año.addItem("");
		for(int año=2014;año>=1980;año--){
			comboBox_Año.addItem(año);
		}
		jfAñadirModificarTrineo.getContentPane().add(comboBox_Año);
		
		
		textID_Trineo = new JTextField("");
		textID_Trineo.setBounds(79, 9, 207, 20);
		jfAñadirModificarTrineo.getContentPane().add(textID_Trineo);
		textID_Trineo.setColumns(10);
		
		textFabricante = new JTextField("");
		textFabricante.setBounds(89, 47, 197, 20);
		jfAñadirModificarTrineo.getContentPane().add(textFabricante);
		textFabricante.setColumns(10);
		
		
		final JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(43, 122, 89, 23);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID_Trineo=0;
				if (comboBox_Año.getSelectedIndex()>0 && comboBox_Mes.getSelectedIndex()>0 && comboBox_Dia.getSelectedIndex()>0 && !textID_Trineo.getText().isEmpty() && !textFabricante.getText().isEmpty()){
					ID_Trineo =Integer.parseInt(textID_Trineo.getText());
					String Fabricante= textFabricante.getText();
					String FechaFabricacion= ""+comboBox_Año.getSelectedItem()+"-"+comboBox_Mes.getSelectedItem()+"-"+comboBox_Dia.getSelectedItem();
					if (pTipo.equals("Anadir")){
						try {
							GestorTrineos.getGestorTrineos().añadirTrineo(ID_Trineo, pID_Club, Fabricante, FechaFabricacion);
							JOptionPane.showMessageDialog(null, Strings.IGAñadirModificarTrineo_ConfirmaciónAñadido);
						} catch (GenericException e1) {
							e1.printStackTrace();
						}
					}else{
						GestorTrineos.getGestorTrineos().modificarTrineo(pID_Trineo, pID_Club, Fabricante, FechaFabricacion);
						JOptionPane.showMessageDialog(null, Strings.IGAñadirModificarTrineo_ConfirmaciónModificado);
					}
					jfAñadirModificarTrineo.dispose();
				}else{
						JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_ErrorRellenarCampos);
				}
			}
		});
		jfAñadirModificarTrineo.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(175, 122, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jfAñadirModificarTrineo.dispose();
			}
		});
		jfAñadirModificarTrineo.getContentPane().add(btnCancelar);
		if(pTipo.equals("Modificar")){
			cargarTextFields(pID_Trineo);
		}
	}
	
		private void cargarTextFields(int pTrineo){
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerDatosTrineo(pTrineo);
			textID_Trineo.setText(rdo.get(0));
			textFabricante.setText(rdo.get(2));
			String fecha = rdo.get(3);
			int dia, mes, año;
			dia = Integer.parseInt(fecha.split("-")[2]);
			mes = Integer.parseInt(fecha.split("-")[1]);
			año = Integer.parseInt(fecha.split("-")[0]);
			comboBox_Dia.setSelectedItem(dia);
			comboBox_Mes.setSelectedItem(mes);
			comboBox_Año.setSelectedItem(año);
		}
		
		public void hacerVisible(){
			jfAñadirModificarTrineo.setVisible(true);
		}
		
}
