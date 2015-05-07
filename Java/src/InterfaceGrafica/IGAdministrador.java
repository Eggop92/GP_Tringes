package InterfaceGrafica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IGAdministrador extends JFrame {

	protected static final String String = null;
	private JPanel contentPane;

	/*/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAdministrador frame = new IGAdministrador("5");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param elIDAdmin 
	 */
	public IGAdministrador(final String elIDAdmin) {
		setTitle(Strings.IGAdministrador_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 265, 589);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministrador = new JLabel(Strings.IGAdministrador_LabelTitulo);
		lblAdministrador.setForeground(new Color(0, 0, 0));
		lblAdministrador.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdministrador.setBounds(37, 18, 185, 19);
		contentPane.add(lblAdministrador);
		
		JButton btnNewButton = new JButton(Strings.IGAdministrador_BotonIncribirCarrera);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGInscribirEnCarrera inscribir = new IGInscribirEnCarrera(elIDAdmin);
				inscribir.show();
			}
		});
		btnNewButton.setBounds(23, 55, 213, 44);
		contentPane.add(btnNewButton);
		
		JButton btnGestinDeTrineos = new JButton(Strings.IGAdministrador_BotonGestionTrineos);
		btnGestinDeTrineos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGGestionarTrineos trineos = new IGGestionarTrineos("");
				trineos.hacerVisible();
			}
		});
		btnGestinDeTrineos.setBounds(23, 117, 213, 44);
		contentPane.add(btnGestinDeTrineos);
		
		JButton btnGestionDePerros = new JButton(Strings.IGAdministrador_BotonGestionPerrosClub);
		btnGestionDePerros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//IGGestionUtilizacionPerros perrosClub = new IGGestionUtilizacionPerros(elIDAdmin);
				IGGestionUtilizacionPerros perrosClub = new IGGestionUtilizacionPerros("");
				//perrosClub.show();
			}
		});
		btnGestionDePerros.setBounds(23, 179, 213, 44);
		contentPane.add(btnGestionDePerros);
		
		JButton btnGestionDeClubs = new JButton(Strings.IGAdministrador_BotonGestionClubs);
		btnGestionDeClubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGGestionarClubs clubs = new IGGestionarClubs();
				clubs.show();
			}
		});
		btnGestionDeClubs.setBounds(23, 241, 213, 44);
		contentPane.add(btnGestionDeClubs);
		
		JButton btnGestionDeCampeonatos = new JButton(Strings.IGAdministrador_BotonGestionCampeonatos);
		btnGestionDeCampeonatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGGestionarCampeonatos campeonatos = new IGGestionarCampeonatos();
				campeonatos.show();
			}
		});
		btnGestionDeCampeonatos.setBounds(23, 303, 213, 44);
		contentPane.add(btnGestionDeCampeonatos);
		
		JButton btnGestionDeCarreras = new JButton(Strings.IGAdministrador_BotonGestionCarreras);
		btnGestionDeCarreras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGGestionarCarreras carreras = new IGGestionarCarreras();
				carreras.show();
			}
		});
		btnGestionDeCarreras.setBounds(23, 365, 213, 44);
		contentPane.add(btnGestionDeCarreras);
		
		JButton btnGestionDeClasificaciones = new JButton(Strings.IGAdministrador_BotonGestionClasificaciones);
		btnGestionDeClasificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGGestorCalificaciones calificaciones = new IGGestorCalificaciones();
				calificaciones.show();
			}
		});
		btnGestionDeClasificaciones.setBounds(23, 427, 213, 44);
		contentPane.add(btnGestionDeClasificaciones);
		
		JButton btnGestionDeDueos = new JButton(Strings.IGAdministrador_BotonGestionDueños);
		btnGestionDeDueos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGGestionarDueños dueños = new IGGestionarDueños();
				dueños.hacerVisible();
			}
		});
		btnGestionDeDueos.setBounds(23, 489, 213, 44);
		contentPane.add(btnGestionDeDueos);
	}

}
