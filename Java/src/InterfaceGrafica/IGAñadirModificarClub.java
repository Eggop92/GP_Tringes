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

public class IGAñadirModificarClub extends JFrame {
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
					IGAñadirModificarClub frame = new IGAñadirModificarClub(null, 0);//modifSara
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
	public IGAñadirModificarClub(String pTipo, int pClub) {
				
		club = pClub;
		tipo = pTipo;
		if(tipo.equals("Modificar")){
			setTitle(Strings.IGAñadirModificarClub_TituloVentanaModificar);
		}else{
			setTitle(Strings.IGAñadirModificarClub_TituloVentana);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 257);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdClub = new JLabel(Strings.IGAñadirModificarClub_lblIdClub);
		lblIdClub.setBounds(30, 30, 71, 14);
		contentPane.add(lblIdClub);
		
		JLabel lblNombre = new JLabel(Strings.IGAñadirModificarClub_lblNombre);
		lblNombre.setBounds(30, 74, 100, 14);
		contentPane.add(lblNombre);
		
		JLabel lblPatrocinador = new JLabel(Strings.IGAñadirModificarClub_lblPatrocinador);
		lblPatrocinador.setBounds(30, 118, 71, 14);
		contentPane.add(lblPatrocinador);
		
		final JButton btnAceptar = new JButton(Strings.BotonAceptar);	
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String contraseña;
				if(!textFieldID.getText().isEmpty() && !textFieldNombre.getText().isEmpty() && !textFieldPatrocinador.getText().isEmpty()){
					int clubModif = Integer.parseInt(textFieldID.getText());
					String nombreModif = textFieldNombre.getText();
					String patrocinadorModif = textFieldPatrocinador.getText();
					if(tipo.equals("Añadir")){
						try {
							contraseña = GestorClubs.getGestorClubs().añadirClub(clubModif, nombreModif, patrocinadorModif);
							JOptionPane.showMessageDialog(null, Strings.IGAñadirModificarClub_MostrarContraseña+contraseña);
							dispose();
						} catch (clubExistenteException e) {
							JOptionPane.showMessageDialog(null, Strings.IGAñadirModificarClub_ErrorDuplicado);
						}catch (IDEstaOcupadoException e){
							JOptionPane.showMessageDialog(null, Strings.IGAñadirModificarClub_ErrorDuplicado);
						}catch(NombreClubEstaOcupadoException e){
							JOptionPane.showMessageDialog(null, Strings.IGAñadirModificarClub_ErrorNombreDuplicado);
						} catch (GenericException e) {
							e.printStackTrace();
						}
					}else{
						GestorClubs.getGestorClubs().modificarDatosClub(clubModif, nombreModif, patrocinadorModif);
						
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificar_ErrorRellenarCampos);
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
		
		JButton buttonContrasena = new JButton(Strings.IGAñadirModificarClub_BotonContr);
		buttonContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevaContraseña = GestorUsuarios.getGestorUsuarios().generarContraseña();
				GestorUsuarios.getGestorUsuarios().modificarContraseña(club, nuevaContraseña);
				JOptionPane.showMessageDialog(btnAceptar, Strings.IGAñadirModificarClub_MostrarContraseña+nuevaContraseña);
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
