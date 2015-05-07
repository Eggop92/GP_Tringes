package InterfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import excepciones.*;
import Tringes.Funciones;
import Tringes.GestorCarreras;

public class IGAñadirModificarCarrera extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nombre;
	private JTextField textField_lugar;
	private JTextField textField_recorrido;
	private JComboBox comboBoxModalidad;
	private JComboBox comboBox_Dia;
	private JComboBox comboBox_Mes;
	private JComboBox comboBox_Año;
	private String fechaCampeonato;
	private String fechaCarrera;
	private String tipo;
	private JTextField textFieldCampeonato;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAñadirModificarCarrera frame = new IGAñadirModificarCarrera(null, null, null);//modif-Sara - ¿esta bien poenerlo a null?
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
public IGAñadirModificarCarrera(String pTipo,String pFechaCampeonato, String pFechaCarrera){
			
		fechaCampeonato = pFechaCampeonato;
		fechaCarrera = pFechaCarrera;
		tipo = pTipo;
		if(tipo=="Modificar"){
			setTitle(Strings.IGAñadirModificarCarrera_TituloVentanaModificar);
		}else{
			setTitle(Strings.IGAñadirModificarCarrera_TituloVentana);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 366);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFechaCarrera = new JLabel(Strings.IGAñadirModificarCarrera_lblFechaCarrera);
		lblFechaCarrera.setBounds(30, 30, 71, 14);
		contentPane.add(lblFechaCarrera);
		
		comboBox_Dia = new JComboBox();
		comboBox_Dia.setBounds(136, 27, 37, 20);
		comboBox_Dia.addItem("");
		for(int dia=1;dia<=31;dia++){
			comboBox_Dia.addItem(dia);
		}
		contentPane.add(comboBox_Dia);
		
		comboBox_Mes = new JComboBox();
		comboBox_Mes.setBounds(183, 27, 37, 20);
		comboBox_Mes.addItem("");
		for(int mes=1;mes<=12;mes++){
			comboBox_Mes.addItem(mes);
		}
		contentPane.add(comboBox_Mes);
		
		comboBox_Año = new JComboBox();
		comboBox_Año.setBounds(230, 27, 56, 20);
		comboBox_Año.addItem("");
		for(int año=2014;año>=1980;año--){
			comboBox_Año.addItem(año);
		}
		contentPane.add(comboBox_Año);
		
		JLabel lblFechaCampeonato = new JLabel(Strings.IGAñadirModificarCarrera_lblFechaCampeonato);
		lblFechaCampeonato.setBounds(30, 74, 100, 14);
		contentPane.add(lblFechaCampeonato);
		
		JLabel lblNombre = new JLabel(Strings.IGAñadirModificarCarrera_lblNombre);
		lblNombre.setBounds(30, 118, 52, 14);
		contentPane.add(lblNombre);
		
		JLabel lblLugar = new JLabel(Strings.IGAñadirModificarCarrera_lblLugar);
		lblLugar.setBounds(30, 162, 52, 14);
		contentPane.add(lblLugar);
		
		final JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboBox_Año.getSelectedIndex()>0 && comboBox_Mes.getSelectedIndex()>0 && comboBox_Dia.getSelectedIndex()>0 && !textField_nombre.getText().isEmpty() && !textField_lugar.getText().isEmpty() && !textField_recorrido.getText().isEmpty() && comboBoxModalidad.getSelectedIndex()>0){
					
					String fechaCarreraModif = ""+comboBox_Año.getSelectedItem()+"-"+comboBox_Mes.getSelectedItem()+"-"+comboBox_Dia.getSelectedItem();
					String fechaCampeonatoModif = textFieldCampeonato.getText();
					String nombreModif = textField_nombre.getText();
					String lugarModif = textField_lugar.getText();
					String recorridoModif = textField_recorrido.getText();
					String modalidadJunta = (String) comboBoxModalidad.getSelectedItem();
					String[] modalidadSeparada = Funciones.separarCadena(modalidadJunta);
					int modalidadModif = Integer.parseInt(modalidadSeparada[0]);
					
					if(tipo.equals("Añadir")){
						try {
							GestorCarreras.getGestorCarreras().añadirCarrera(fechaCarreraModif, fechaCampeonatoModif, nombreModif, lugarModif, recorridoModif, modalidadModif);
							JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_CarreraAñadida);
							dispose();
						}catch (carreraExistenteException e1){
							JOptionPane.showMessageDialog(null, Strings.IGAñadirModificarCarrera_CarreraDuplicada);
						} catch (GenericException e) {
							e.printStackTrace();
						}
					}else{
						GestorCarreras.getGestorCarreras().modificarDatosCarrera(nombreModif, modalidadModif, lugarModif, recorridoModif, fechaCarreraModif, fechaCampeonatoModif, fechaCarrera, fechaCampeonato);
						JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_CarreraModificada);
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_ErrorRellenarCampos);
				}
			}
		});
			
		btnAceptar.setBounds(30, 294, 100, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(Strings.BotonCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(171, 294, 100, 23);
		contentPane.add(btnCancelar);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(136, 115, 150, 20);
		contentPane.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		textField_lugar = new JTextField();
		textField_lugar.setBounds(136, 159, 150, 20);
		contentPane.add(textField_lugar);
		textField_lugar.setColumns(10);
		
		JLabel lblRecorrido = new JLabel(Strings.IGAñadirModificarCarrera_lblRecorrido/*"Recorrido:"*/);
		lblRecorrido.setBounds(30, 205, 52, 14);
		contentPane.add(lblRecorrido);
		
		textField_recorrido = new JTextField();
		textField_recorrido.setColumns(10);
		textField_recorrido.setBounds(136, 202, 150, 20);
		contentPane.add(textField_recorrido);
		
		JLabel lblIdmodalidad = new JLabel(Strings.IGAñadirModificarCarrera_lblIdModalidad/*"ID_Modalidad:"*/);
		lblIdmodalidad.setBounds(30, 247, 71, 14);
		contentPane.add(lblIdmodalidad);
		
		comboBoxModalidad = new JComboBox();
		comboBoxModalidad.setBounds(136, 244, 150, 20);
		comboBoxModalidad.addItem("");
		LinkedList<String> mod = GestorCarreras.getGestorCarreras().obtenerModalidades();
		Iterator<String> itr = mod.iterator();
		while(itr.hasNext()){
			comboBoxModalidad.addItem(itr.next());
		}
		contentPane.add(comboBoxModalidad);
		
		textFieldCampeonato = new JTextField();
		textFieldCampeonato.setBounds(136, 71, 150, 20);
		textFieldCampeonato.setText(pFechaCampeonato);
		textFieldCampeonato.setEditable(false);
		contentPane.add(textFieldCampeonato);
		textFieldCampeonato.setColumns(10);
		
		if(tipo.equals("Modificar")){
			cargarTextFieldsYComboBox(fechaCarrera, fechaCampeonato);
		}

	}


	private void cargarTextFieldsYComboBox(String pFechaCarrera, String pFechaCampeonato){
		
		LinkedList<String> rdo = GestorCarreras.getGestorCarreras().obtenerDatosCarrera(pFechaCarrera, pFechaCampeonato);
		//campeonato
		textFieldCampeonato.setText(rdo.get(0));
		//carrera
		int dia, mes, año;
		String fecha = rdo.get(1);
		año=Integer.parseInt(fecha.split("-")[0]);
		mes=Integer.parseInt(fecha.split("-")[1]);
		dia=Integer.parseInt(fecha.split("-")[2]);
		comboBox_Año.setSelectedItem(año);
		comboBox_Mes.setSelectedItem(mes);
		comboBox_Dia.setSelectedItem(dia);
		//nombre
		textField_nombre.setText(rdo.get(2));
		//lugar
		textField_lugar.setText(rdo.get(3));
		//recorrido
		textField_recorrido.setText(rdo.get(4));
		//Modalidad 
		String modalidad=rdo.get(5);
		int i =comboBoxModalidad.getItemCount()-1;
		boolean enc= false;
		String modAct;
		while(i>=0 && !enc){
			modAct= (String) comboBoxModalidad.getItemAt(i);
			if(modalidad.equals(Funciones.separarCadena(modAct)[0])){
				enc=true;
				comboBoxModalidad.setSelectedIndex(i);
			}else{
				i--;
			}
		}
		
	}
}
