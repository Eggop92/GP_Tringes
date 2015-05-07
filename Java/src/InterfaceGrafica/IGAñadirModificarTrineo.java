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

public class IGA�adirModificarTrineo extends JFrame{

	private String pTipo;
	private int pID_Club;
	private int pID_Trineo;
	
	private JFrame jfA�adirModificarTrineo;
	private JTextField textID_Trineo;
	private JTextField textFabricante;
	private JComboBox comboBox_Dia;
	private JComboBox comboBox_Mes;
	private JComboBox comboBox_A�o;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGA�adirModificarTrineo window = new IGA�adirModificarTrineo("Anadir",0,0);//sara- a�adido parametro del trineo
					window.jfA�adirModificarTrineo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public IGA�adirModificarTrineo( String pPTipo,  int pIDClub, int pIDTrineo) {
		pTipo=pPTipo;
		pID_Club= pIDClub;
		pID_Trineo= pIDTrineo;
		
		jfA�adirModificarTrineo = new JFrame();
		jfA�adirModificarTrineo.setBounds(100, 100, 324, 205);
		setResizable(false);
		jfA�adirModificarTrineo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfA�adirModificarTrineo.getContentPane().setLayout(null);
		
		if (pTipo.equals("Anadir")){
			jfA�adirModificarTrineo.setTitle(Strings.IGA�adirModificarTrineo_TituloVentanaA�adir);
		}else{
			setTitle(Strings.IGA�adirModificarTrineo_TituloVentanaModificar);
		}
		
		JLabel lblIdtrineo = new JLabel(Strings.IGA�adirModificarTrineo_lblIdtrineo);
		lblIdtrineo.setBounds(10, 11, 59, 17);
		jfA�adirModificarTrineo.getContentPane().add(lblIdtrineo);
		
		JLabel lblFabricante = new JLabel(Strings.IGA�adirModificarTrineo_lblFabricante);
		lblFabricante.setBounds(10, 50, 72, 14);
		jfA�adirModificarTrineo.getContentPane().add(lblFabricante);
		
		JLabel lblFechaDeFabricacion = new JLabel(Strings.IGA�adirModificarTrineo_lblFechaDeFabricacion);
		lblFechaDeFabricacion.setBounds(10, 84, 107, 14);
		jfA�adirModificarTrineo.getContentPane().add(lblFechaDeFabricacion);
		
		comboBox_Dia = new JComboBox();
		comboBox_Dia.setBounds(127, 81, 40, 20);
		comboBox_Dia.addItem("");
		for(int dia=1;dia<=31;dia++){
			comboBox_Dia.addItem(dia);
		}
		jfA�adirModificarTrineo.getContentPane().add(comboBox_Dia);
		
		comboBox_Mes = new JComboBox();
		comboBox_Mes.setBounds(177, 81, 40, 20);
		comboBox_Mes.addItem("");
		for(int mes=1;mes<=12;mes++){
			comboBox_Mes.addItem(mes);
		}
		jfA�adirModificarTrineo.getContentPane().add(comboBox_Mes);
		
		comboBox_A�o = new JComboBox();
		comboBox_A�o.setBounds(227, 81, 59, 20);
		comboBox_A�o.addItem("");
		for(int a�o=2014;a�o>=1980;a�o--){
			comboBox_A�o.addItem(a�o);
		}
		jfA�adirModificarTrineo.getContentPane().add(comboBox_A�o);
		
		
		textID_Trineo = new JTextField("");
		textID_Trineo.setBounds(79, 9, 207, 20);
		jfA�adirModificarTrineo.getContentPane().add(textID_Trineo);
		textID_Trineo.setColumns(10);
		
		textFabricante = new JTextField("");
		textFabricante.setBounds(89, 47, 197, 20);
		jfA�adirModificarTrineo.getContentPane().add(textFabricante);
		textFabricante.setColumns(10);
		
		
		final JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(43, 122, 89, 23);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID_Trineo=0;
				if (comboBox_A�o.getSelectedIndex()>0 && comboBox_Mes.getSelectedIndex()>0 && comboBox_Dia.getSelectedIndex()>0 && !textID_Trineo.getText().isEmpty() && !textFabricante.getText().isEmpty()){
					ID_Trineo =Integer.parseInt(textID_Trineo.getText());
					String Fabricante= textFabricante.getText();
					String FechaFabricacion= ""+comboBox_A�o.getSelectedItem()+"-"+comboBox_Mes.getSelectedItem()+"-"+comboBox_Dia.getSelectedItem();
					if (pTipo.equals("Anadir")){
						try {
							GestorTrineos.getGestorTrineos().a�adirTrineo(ID_Trineo, pID_Club, Fabricante, FechaFabricacion);
							JOptionPane.showMessageDialog(null, Strings.IGA�adirModificarTrineo_Confirmaci�nA�adido);
						} catch (GenericException e1) {
							e1.printStackTrace();
						}
					}else{
						GestorTrineos.getGestorTrineos().modificarTrineo(pID_Trineo, pID_Club, Fabricante, FechaFabricacion);
						JOptionPane.showMessageDialog(null, Strings.IGA�adirModificarTrineo_Confirmaci�nModificado);
					}
					jfA�adirModificarTrineo.dispose();
				}else{
						JOptionPane.showMessageDialog(btnAceptar, Strings.IGA�adirModificar_ErrorRellenarCampos);
				}
			}
		});
		jfA�adirModificarTrineo.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(175, 122, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jfA�adirModificarTrineo.dispose();
			}
		});
		jfA�adirModificarTrineo.getContentPane().add(btnCancelar);
		if(pTipo.equals("Modificar")){
			cargarTextFields(pID_Trineo);
		}
	}
	
		private void cargarTextFields(int pTrineo){
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerDatosTrineo(pTrineo);
			textID_Trineo.setText(rdo.get(0));
			textFabricante.setText(rdo.get(2));
			String fecha = rdo.get(3);
			int dia, mes, a�o;
			dia = Integer.parseInt(fecha.split("-")[2]);
			mes = Integer.parseInt(fecha.split("-")[1]);
			a�o = Integer.parseInt(fecha.split("-")[0]);
			comboBox_Dia.setSelectedItem(dia);
			comboBox_Mes.setSelectedItem(mes);
			comboBox_A�o.setSelectedItem(a�o);
		}
		
		public void hacerVisible(){
			jfA�adirModificarTrineo.setVisible(true);
		}
		
}
