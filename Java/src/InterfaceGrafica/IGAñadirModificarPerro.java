package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import excepciones.GenericException;
import excepciones.perroExistenteException;

import Tringes.GestorPerros;
import javax.swing.SwingConstants;

public class IGAñadirModificarPerro extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Nombre;
	private JTextField textField_Raza;
	private JTextField textField_Sexo;

	private int perro;
	private String tipo;
	private int numFederado;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAñadirModificarPerro frame = new IGAñadirModificarPerro(null,0, 0);
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
	public IGAñadirModificarPerro(String pTipo,int pPerro, int pNumFederado) {
		
		perro = pPerro;
		tipo=pTipo;
		numFederado=pNumFederado;
		
		if(tipo=="Modificar"){
			setTitle(Strings.IGAñadirModificarPerro_TituloVentanaModificar);
		}else{
			setTitle(Strings.IGAñadirModificarPerro_TituloVentana);
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 312, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(Strings.IGAñadirModificarPerro_LabelIDPerro);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 34, 99, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel(Strings.IGAñadirModificarPerro_LabelNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 78, 99, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel_1 = new JLabel(Strings.IGAñadirModificarPerro_LabelRaza);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 122, 99, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSexo = new JLabel(Strings.IGAñadirModificarPerro_LabelSexo);
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setBounds(10, 163, 99, 14);
		contentPane.add(lblSexo);
		
		final JButton btnAceptar = new JButton(Strings.BotonAceptar);
	
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int perroModif = Integer.parseInt(textField_ID.getText());
				String nombreModif = textField_Nombre.getText();
				String razaModif = textField_Raza.getText();
				String sexoModif = textField_Sexo.getText();
				
				if(!textField_ID.getText().isEmpty() && !textField_Nombre.getText().isEmpty() && !textField_Raza.getText().isEmpty() && !textField_Sexo.getText().isEmpty()){
					if(tipo.equals("Añadir")){
						try {
							GestorPerros.getGestorPerros().añadirPerroDueño(numFederado, perroModif, nombreModif, razaModif, sexoModif);
							JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificarPerro_PerroAñadido);
							dispose();
						}catch(perroExistenteException e){
							JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificarPerro_PerroExiste);
						} catch (GenericException e) {
							e.printStackTrace();
						}
					}else{
						GestorPerros.getGestorPerros().modificarDatosPerro(perroModif, nombreModif, razaModif, sexoModif);
						JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificarPerro_PerroModificado);
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_ErrorRellenarCampos);
				}
								
			}
		});
		btnAceptar.setBounds(22, 206, 99, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(Strings.BotonCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}});
		btnCancelar.setBounds(171, 206, 101, 23);
		contentPane.add(btnCancelar);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(122, 31, 150, 20);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(122, 75, 150, 20);
		contentPane.add(textField_Nombre);
		textField_Nombre.setColumns(10);
		
		textField_Raza = new JTextField();
		textField_Raza.setBounds(122, 119, 150, 20);
		contentPane.add(textField_Raza);
		textField_Raza.setColumns(10);
		
		textField_Sexo = new JTextField();
		textField_Sexo.setBounds(122, 160, 150, 20);
		contentPane.add(textField_Sexo);
		textField_Sexo.setColumns(10);
		
		if(tipo.equals("Modificar")){
		cargarTextFields(perro);
		}
	}
	
	private void cargarTextFields(int pPerro){
		LinkedList<String> rdo =GestorPerros.getGestorPerros().obtenerDatosPerro(pPerro);
		textField_ID.setText(rdo.get(0));
		textField_Nombre.setText(rdo.get(1));
		textField_Raza.setText(rdo.get(2));
		textField_Sexo.setText(rdo.get(3));
		textField_ID.setEnabled(false);
	}

}
