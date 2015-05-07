package InterfaceGrafica;

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
import excepciones.dueñoExistenteException;
import Tringes.GestorDueños;
import Tringes.GestorUsuarios;

public class IGAñadirModificarDueño extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFrame frame;
	private String dueño;
	private String tipo;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAñadirModificarDueño frame = new IGAñadirModificarDueño("", 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					IGGestionarClubs frame = new IGGestionarClubs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});
	}*/

	public IGAñadirModificarDueño(String pTipo, final int pDueño){
		dueño =""+pDueño+"";
		tipo = pTipo;
		
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumFederado = new JLabel(Strings.IGAñadirModificarDueño_lblNumFederado);
		lblNumFederado.setBounds(30, 30, 88, 14);
		contentPane.add(lblNumFederado);
		
		JLabel lblNombre = new JLabel(Strings.IGAñadirModificarDueño_lblNombre);
		lblNombre.setBounds(30, 74, 100, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel(Strings.IGAñadirModificarDueño_lblApellido);
		lblApellido.setBounds(30, 118, 71, 14);
		contentPane.add(lblApellido);
		
		final JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				
				if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()){
					int dueñoModif = Integer.parseInt(textField.getText());
					String nombreModif = textField_1.getText();
					String apellidoModif = textField_2.getText();
					String nuevaContraseña;
					if(tipo.equals("Añadir")){
						try {
							System.out.println("DEBUG: pDueño:"+dueñoModif);
							nuevaContraseña = GestorUsuarios.getGestorUsuarios().generarContraseña();
							GestorDueños.getGestorDueños().añadirDueño(dueñoModif, nombreModif, apellidoModif, nuevaContraseña);

							JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirAñadirDatosContraseña+": "+nuevaContraseña);
							dispose();
							
						} catch (dueñoExistenteException e) {
							JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_ErrorDueñoExistente);
							//public static String IGAñadirModificar_ErrorExisteNumFederado = "Ya existe un usuario con el numero de federado insertado";
							e.printStackTrace();
						} catch (GenericException e) {
							e.printStackTrace();
						}
					}else{

						GestorDueños.getGestorDueños().modificarDatosDueño(pDueño, nombreModif, apellidoModif);
						nuevaContraseña = GestorUsuarios.getGestorUsuarios().generarContraseña();
						GestorUsuarios.getGestorUsuarios().modificarContraseña(pDueño, nuevaContraseña);
						JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirAñadirDatosContraseña+": "+nuevaContraseña);
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_ErrorRellenarCampos);
				}
			
			}
		});
		btnAceptar.setBounds(30, 182, 100, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(Strings.BotonCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(171, 182, 100, 23);
		contentPane.add(btnCancelar);
		
		textField = new JTextField();
		textField.setBounds(136, 27, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 71, 150, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(136, 115, 150, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		if(tipo=="Modificar"){
			setTitle(Strings.IGAñadirModificarDueño_TituloVentanaModificar);
			//textField.setText(dueño);
			textField.setEditable(false);
			cargarTextFields(pDueño);
			
			
		}else{
			setTitle(Strings.IGAñadirModificarDueño_TituloVentanaAñadir);
		}
	}
	
		private void cargarTextFields(int pDueño){
			LinkedList<String> rdo = GestorDueños.getGestorDueños().obtenerDatosDueño(pDueño);
			textField.setText(rdo.get(0));
			textField_1.setText(rdo.get(1));
			textField_2.setText(rdo.get(2));
		}

}
