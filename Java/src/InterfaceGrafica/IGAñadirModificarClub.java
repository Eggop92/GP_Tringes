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
import excepciones.*;
import Tringes.*;

public class IGA�adirModificarClub extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldPatrocinador;
	private int club;
	private String tipo;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGA�adirModificarClub frame = new IGA�adirModificarClub(null, 0);//modifSara
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
	public IGA�adirModificarClub(String pTipo, int pClub) {
				
		club = pClub;
		tipo = pTipo;
		if(tipo.equals("Modificar")){
			setTitle(Strings.IGA�adirModificarClub_TituloVentanaModificar);
		}else{
			setTitle(Strings.IGA�adirModificarClub_TituloVentana);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 257);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdClub = new JLabel(Strings.IGA�adirModificarClub_lblIdClub);
		lblIdClub.setBounds(30, 30, 71, 14);
		contentPane.add(lblIdClub);
		
		JLabel lblNombre = new JLabel(Strings.IGA�adirModificarClub_lblNombre);
		lblNombre.setBounds(30, 74, 100, 14);
		contentPane.add(lblNombre);
		
		JLabel lblPatrocinador = new JLabel(Strings.IGA�adirModificarClub_lblPatrocinador);
		lblPatrocinador.setBounds(30, 118, 71, 14);
		contentPane.add(lblPatrocinador);
		
		final JButton btnAceptar = new JButton(Strings.BotonAceptar);	
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String contrase�a;
				if(!textFieldID.getText().isEmpty() && !textFieldNombre.getText().isEmpty() && !textFieldPatrocinador.getText().isEmpty()){
					int clubModif = Integer.parseInt(textFieldID.getText());
					String nombreModif = textFieldNombre.getText();
					String patrocinadorModif = textFieldPatrocinador.getText();
					if(tipo.equals("A�adir")){
						try {
							contrase�a = GestorClubs.getGestorClubs().a�adirClub(clubModif, nombreModif, patrocinadorModif);
							JOptionPane.showMessageDialog(null, Strings.IGA�adirModificarClub_MostrarContrase�a+contrase�a);
							dispose();
						} catch (clubExistenteException e) {
							JOptionPane.showMessageDialog(null, Strings.IGA�adirModificarClub_ErrorDuplicado);
						}catch (IDEstaOcupadoException e){
							JOptionPane.showMessageDialog(null, Strings.IGA�adirModificarClub_ErrorDuplicado);
						}catch(NombreClubEstaOcupadoException e){
							JOptionPane.showMessageDialog(null, Strings.IGA�adirModificarClub_ErrorNombreDuplicado);
						} catch (GenericException e) {
							e.printStackTrace();
						}
					}else{
						GestorClubs.getGestorClubs().modificarDatosClub(clubModif, nombreModif, patrocinadorModif);
						
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(btnAceptar, Strings.IGA�adirModificar_ErrorRellenarCampos);
				}
			}
		});

		btnAceptar.setBounds(10, 182, 100, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(Strings.BotonCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(120, 182, 100, 23);
		contentPane.add(btnCancelar);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(136, 27, 150, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(136, 71, 150, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldPatrocinador = new JTextField();
		textFieldPatrocinador.setBounds(136, 115, 150, 20);
		contentPane.add(textFieldPatrocinador);
		textFieldPatrocinador.setColumns(10);
		
		JButton buttonContrasena = new JButton(Strings.IGA�adirModificarClub_BotonContr);
		buttonContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevaContrase�a = GestorUsuarios.getGestorUsuarios().generarContrase�a();
				GestorUsuarios.getGestorUsuarios().modificarContrase�a(club, nuevaContrase�a);
				JOptionPane.showMessageDialog(btnAceptar, Strings.IGA�adirModificarClub_MostrarContrase�a+nuevaContrase�a);
			}
		});
		buttonContrasena.setBounds(232, 182, 89, 23);
		contentPane.add(buttonContrasena);
		
		if(tipo=="Modificar"){
			cargarTextFields(club);
		}
	}
	
	private void cargarTextFields(int pClub){
		LinkedList<String> rdo = GestorClubs.getGestorClubs().obtenerDatosClub(pClub);
		textFieldID.setText(rdo.get(0));
		textFieldID.setEnabled(false);
		textFieldNombre.setText(rdo.get(1));
		textFieldPatrocinador.setText(rdo.get(2));
	}
}
