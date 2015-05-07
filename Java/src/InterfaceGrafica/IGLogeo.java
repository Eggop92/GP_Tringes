package InterfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import Tringes.GestorUsuarios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class IGLogeo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGLogeo frame = new IGLogeo();
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
	public IGLogeo() {
		setTitle(Strings.IGLogeo_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 261, 232);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduzcaSusCredenciales = new JLabel(Strings.IGLogeo_LabelSuperior);
		lblIntroduzcaSusCredenciales.setBounds(21, 11, 207, 14);
		contentPane.add(lblIntroduzcaSusCredenciales);
		
		JLabel lblUsuario = new JLabel(Strings.IGLogeo_LabelUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(21, 51, 66, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel(Strings.IGLogeo_LabelContraseña);
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setBounds(21, 96, 73, 14);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(105, 48, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(105, 93, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = GestorUsuarios.getGestorUsuarios().identificarse(textField.getText(),textField_1.getText());
				if(tipo.equals("A")){
					IGAdministrador menu=new IGAdministrador(textField.getText());
					menu.show();
					dispose();
				}
				else if(tipo.equals("D")){
					IGDueño menu=new IGDueño(textField.getText());
					menu.show();
					dispose();
				}
				else if(tipo.equals("C")){
					IGClub menu=new IGClub(textField.getText());
					menu.show();
					dispose();					
				}
				else{
					JOptionPane.showMessageDialog(null,Strings.ErrorNoExisteUsuario); 
				}
			}
		});
		btnAceptar.setBounds(21, 147, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(Strings.BotonCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(139, 147, 89, 23);
		contentPane.add(btnCancelar);
	}
}
