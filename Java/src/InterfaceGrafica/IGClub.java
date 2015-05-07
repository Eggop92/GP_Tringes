package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;




import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IGClub extends JFrame {

	protected static final String String = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGClub frame = new IGClub("01");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param elIDClub 
	 */
	public IGClub(final String elIDClub) {
		setTitle(Strings.IGClub_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 256, 237);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenidonombreDel = new JLabel(Strings.IGClub_LabelSuperior);
		lblBienvenidonombreDel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBienvenidonombreDel.setBounds(5, 13, 230, 19);
		contentPane.add(lblBienvenidonombreDel);
		
		JButton btnNewButton = new JButton(Strings.IGClub_BotonGestionarCarreras);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGInscribirEnCarrera inscribir = new IGInscribirEnCarrera(elIDClub);
				inscribir.show();
			}
		});
		btnNewButton.setBounds(15, 45, 209, 38);
		contentPane.add(btnNewButton);
		
		JButton btnGestionDeTrineos = new JButton(Strings.IGClub_BotonGestionarTrineos);
		btnGestionDeTrineos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGGestionarTrineos trineos = new IGGestionarTrineos(elIDClub);
				trineos.hacerVisible();
			}
		});
		btnGestionDeTrineos.setBounds(15, 96, 209, 38);
		contentPane.add(btnGestionDeTrineos);
		
		JButton btnGestionDePerros = new JButton(Strings.IGClub_BotonGestionarPerrosClub);
		btnGestionDePerros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGGestionUtilizacionPerros perrosClub = new IGGestionUtilizacionPerros(elIDClub);
				perrosClub.show();
			}
		});
		btnGestionDePerros.setBounds(15, 147, 209, 38);
		contentPane.add(btnGestionDePerros);
	}

}
