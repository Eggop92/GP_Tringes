package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import Tringes.Funciones;
import Tringes.GestorCampeonatos;
import Tringes.GestorCarreras;
import Tringes.GestorTrineos;




import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.SwingConstants;

public class IGGestorCalificaciones extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private JComboBox comboBox_Campe;
	private JComboBox comboBox_Carreras;
	private JComboBox comboBox_Trineo;
	
	private Vector comboBoxItems_Carreras;
	final DefaultComboBoxModel modelo_Carreras;
	private Vector comboBoxItems_Trineos;
	final DefaultComboBoxModel modelo_Trineos;
	
	private String elCampeonato;
	private String laCarrera;
	private int elTrineo;
	private int laPosicion;
	private int laPuntuacion;
	


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestorCalificaciones frame = new IGGestorCalificaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public IGGestorCalificaciones() {
		setTitle(Strings.IGGestorCalificaciones_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 293);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCampeonato = new JLabel(Strings.IGGestorCalificaciones_LabelCampeonato);
		lblCampeonato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCampeonato.setBounds(10, 14, 95, 14);
		contentPane.add(lblCampeonato);
		
		JLabel lblNewLabel = new JLabel(Strings.IGGestorCalificaciones_LabelCarrera);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 51, 95, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTrineo = new JLabel(Strings.IGGestorCalificaciones_LabelTrineo);
		lblTrineo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrineo.setBounds(10, 90, 95, 14);
		contentPane.add(lblTrineo);
		
		comboBox_Campe = new JComboBox(); 
		comboBox_Campe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(comboBox_Campe) && comboBox_Campe.getSelectedIndex()>0){
					String campeonatoJunto =(String) comboBox_Campe.getSelectedItem();	//saber que ha seleccionado para poder cargar los siguientes datos
					String[] campeonatoSeparado =Funciones.separarCadena(campeonatoJunto);
					elCampeonato = campeonatoSeparado[0];
					if(comboBox_Campe.getSelectedIndex() > 0){
						cargarComboBoxCarreras(elCampeonato);
					}else{
						JOptionPane.showMessageDialog(comboBox_Campe, Strings.IGAñadirModificar_ErrorSeleccionarCampos);
					}
				}
			}
		});
	
		comboBox_Campe.setBounds(115, 11, 200, 20);
		contentPane.add(comboBox_Campe);
		
	
		comboBoxItems_Carreras= new Vector();
		comboBoxItems_Carreras.add("");
		modelo_Carreras= new DefaultComboBoxModel(comboBoxItems_Carreras);
		comboBox_Carreras = new JComboBox(modelo_Carreras);
		comboBox_Carreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource().equals(comboBox_Carreras) && comboBox_Carreras.getSelectedIndex()>0){
						String carreraJunta =(String) comboBox_Carreras.getSelectedItem();	//saber que ha seleccionado para poder cargar los siguientes datos
						String[] carreraSeparada = Funciones.separarCadena(carreraJunta);
						laCarrera = carreraSeparada[0];
						if(comboBox_Carreras.getSelectedObjects().length == 1){
							cargarComboBoxTrineos(elCampeonato, laCarrera);
						}else{
							JOptionPane.showMessageDialog(comboBox_Carreras, Strings.IGAñadirModificar_ErrorSeleccionarCampos);
						}
					}
				}
			});
		
		comboBox_Carreras.setBounds(115, 48, 200, 20);
		contentPane.add(comboBox_Carreras);
		
		
		comboBoxItems_Trineos= new Vector();
		comboBoxItems_Trineos.add("");
		modelo_Trineos= new DefaultComboBoxModel(comboBoxItems_Trineos);
		comboBox_Trineo = new JComboBox(modelo_Trineos);
		comboBox_Trineo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(comboBox_Trineo) && comboBox_Trineo.getSelectedIndex()>0){
					elTrineo = Integer.parseInt((String)comboBox_Trineo.getSelectedItem());	//saber que ha seleccionado para poder cargar los siguientes datos
					if(comboBox_Trineo.getSelectedObjects().length == 1){
						cargarTextFieldsPosicionPuntuacion(elCampeonato, laCarrera, elTrineo);
					}else{
						JOptionPane.showMessageDialog(comboBox_Trineo, Strings.IGAñadirModificar_ErrorSeleccionarCampos);
					}
				}
			}
		});
		comboBox_Trineo.setBounds(115, 87, 200, 20);
		contentPane.add(comboBox_Trineo);
		
		JLabel lblPosicion = new JLabel(Strings.IGGestorCalificaciones_LabelPosicion);
		lblPosicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosicion.setBounds(10, 132, 95, 14);
		contentPane.add(lblPosicion);
		
		JLabel lblPuntos = new JLabel(Strings.IGGestorCalificaciones_LabelPuntos);
		lblPuntos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuntos.setBounds(10, 174, 95, 14);
		contentPane.add(lblPuntos);
		
		textField = new JTextField();
		textField.setBounds(115, 129, 112, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(115, 171, 112, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton(Strings.BotonGuardar);
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty()){
						laPosicion = Integer.parseInt(textField.getText());
						laPuntuacion = Integer.parseInt(textField_1.getText());
						GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(laPosicion, laPuntuacion, laCarrera, elCampeonato, elTrineo);
					}else{
						JOptionPane.showMessageDialog(textField, Strings.IGAñadirModificar_ErrorRellenarCampos);
					}
				}
			});
		btnNewButton.setBounds(237, 128, 112, 60);
		contentPane.add(btnNewButton);
		
		JButton btnCerrar = new JButton(Strings.BotonCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCerrar.setBounds(147, 215, 89, 23);
		contentPane.add(btnCerrar);
		
		cargarComboBoxCampeonatos();
	}
	
	private void cargarComboBoxCampeonatos(){
		LinkedList<String> campeonatos = GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		comboBox_Campe.addItem("");
		for(int i=0;i<campeonatos.size();i++){
			comboBox_Campe.addItem(campeonatos.get(i));
		}
	}

	private void cargarComboBoxCarreras(String pCampeonato){
		modelo_Carreras.removeAllElements();
		modelo_Carreras.addElement("");
		LinkedList<String> carreras = GestorCarreras.getGestorCarreras().obtenerCarreras(pCampeonato);
		String carrera;
		for(int i=0;i<carreras.size();i++){
			carrera= carreras.get(i);
			modelo_Carreras.addElement(carrera);
		}
	}

	private void cargarComboBoxTrineos(String pCampeonato,String pCarrera){
		modelo_Trineos.removeAllElements();
		LinkedList<String> trineos = GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera(pCarrera, pCampeonato);
		modelo_Trineos.addElement("");
		String trineo;
		for(int i=0;i<trineos.size();i++){
			trineo = trineos.get(i);
			modelo_Trineos.addElement(trineo);
		}
	}

	private void cargarTextFieldsPosicionPuntuacion(String pCampeonato,String pCarrera,int pTrineo){
		LinkedList<Integer> calificaciones = GestorCarreras.getGestorCarreras().obtenerCalificaciones(pCarrera, pCampeonato,pTrineo);
		textField.setText(calificaciones.get(0).toString());
		textField_1.setText(calificaciones.get(1).toString());
	}
	
		
}
