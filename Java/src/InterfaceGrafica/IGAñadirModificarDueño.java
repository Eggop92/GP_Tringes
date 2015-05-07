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
import excepciones.due�oExistenteException;
import Tringes.GestorDue�os;
import Tringes.GestorUsuarios;

public class IGA�adirModificarDue�o extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFrame frame;
	private String due�o;
	private String tipo;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGA�adirModificarDue�o frame = new IGA�adirModificarDue�o("", 0);
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

	public IGA�adirModificarDue�o(String pTipo, final int pDue�o){
		due�o =""+pDue�o+"";
		tipo = pTipo;
		
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumFederado = new JLabel(Strings.IGA�adirModificarDue�o_lblNumFederado);
		lblNumFederado.setBounds(30, 30, 88, 14);
		contentPane.add(lblNumFederado);
		
		JLabel lblNombre = new JLabel(Strings.IGA�adirModificarDue�o_lblNombre);
		lblNombre.setBounds(30, 74, 100, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel(Strings.IGA�adirModificarDue�o_lblApellido);
		lblApellido.setBounds(30, 118, 71, 14);
		contentPane.add(lblApellido);
		
		final JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				
				if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()){
					int due�oModif = Integer.parseInt(textField.getText());
					String nombreModif = textField_1.getText();
					String apellidoModif = textField_2.getText();
					String nuevaContrase�a;
					if(tipo.equals("A�adir")){
						try {
							System.out.println("DEBUG: pDue�o:"+due�oModif);
							nuevaContrase�a = GestorUsuarios.getGestorUsuarios().generarContrase�a();
							GestorDue�os.getGestorDue�os().a�adirDue�o(due�oModif, nombreModif, apellidoModif, nuevaContrase�a);

							JOptionPane.showMessageDialog(btnAceptar, Strings.IGA�adirA�adirDatosContrase�a+": "+nuevaContrase�a);
							dispose();
							
						} catch (due�oExistenteException e) {
							JOptionPane.showMessageDialog(btnAceptar, Strings.IGA�adirModificar_ErrorDue�oExistente);
							//public static String IGA�adirModificar_ErrorExisteNumFederado = "Ya existe un usuario con el numero de federado insertado";
							e.printStackTrace();
						} catch (GenericException e) {
							e.printStackTrace();
						}
					}else{

						GestorDue�os.getGestorDue�os().modificarDatosDue�o(pDue�o, nombreModif, apellidoModif);
						nuevaContrase�a = GestorUsuarios.getGestorUsuarios().generarContrase�a();
						GestorUsuarios.getGestorUsuarios().modificarContrase�a(pDue�o, nuevaContrase�a);
						JOptionPane.showMessageDialog(btnAceptar, Strings.IGA�adirA�adirDatosContrase�a+": "+nuevaContrase�a);
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(btnAceptar, Strings.IGA�adirModificar_ErrorRellenarCampos);
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
			setTitle(Strings.IGA�adirModificarDue�o_TituloVentanaModificar);
			//textField.setText(due�o);
			textField.setEditable(false);
			cargarTextFields(pDue�o);
			
			
		}else{
			setTitle(Strings.IGA�adirModificarDue�o_TituloVentanaA�adir);
		}
	}
	
		private void cargarTextFields(int pDue�o){
			LinkedList<String> rdo = GestorDue�os.getGestorDue�os().obtenerDatosDue�o(pDue�o);
			textField.setText(rdo.get(0));
			textField_1.setText(rdo.get(1));
			textField_2.setText(rdo.get(2));
		}

}
