package InterfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class IGPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGPrincipal frame = new IGPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public IGPrincipal() {
		setTitle(Strings.IGPrincipal_TituloVentana);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 378);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenidoA = new JLabel(Strings.IGPrincipal_LabelSuperior);
		lblBienvenidoA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBienvenidoA.setBounds(173, 11, 99, 14);
		contentPane.add(lblBienvenidoA);
		
		JLabel lblTringes = new JLabel(Strings.IGPrincipal_LabelTringes);
		lblTringes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTringes.setBounds(173, 36, 99, 35);
		contentPane.add(lblTringes);
		
		JLabel lblProgramaDeGestion = new JLabel(Strings.IGPrincipal_LabelInformacion);
		lblProgramaDeGestion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblProgramaDeGestion.setBounds(54, 68, 337, 44);
		contentPane.add(lblProgramaDeGestion);
		
		JButton btnIdentificarse = new JButton(Strings.IGPrincipal_BotonIdentificarse);
		btnIdentificarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGLogeo logeo = new IGLogeo();
				logeo.show();
			}
		});
		btnIdentificarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIdentificarse.setBounds(97, 123, 257, 37);
		contentPane.add(btnIdentificarse);
		
		JButton btnVerClasificacion = new JButton(Strings.IGPrincipal_BotonClasificacion);
		btnVerClasificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGClasificacion clasificacion = new IGClasificacion();
				clasificacion.show();
	
			}
		});
		btnVerClasificacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerClasificacion.setBounds(97, 171, 257, 57);
		contentPane.add(btnVerClasificacion);
		
		JLabel lblIdioma = new JLabel(Strings.IGPrincipal_LabelIdioma);
		lblIdioma.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdioma.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblIdioma.setBounds(129, 262, 132, 14);
		contentPane.add(lblIdioma);
		
		final JButton btnCastellano = new JButton(Strings.IGPrincipal_BotonCastellano);
		btnCastellano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Strings.IGPrincipal_BotonIngles.equals("English")){
					Strings.cambiarIdiomaACastellano();
					IGPrincipal principalCastellano = new IGPrincipal();
					principalCastellano.show();
					dispose();
				}
			}
		});
		btnCastellano.setBounds(97, 287, 124, 23);
		contentPane.add(btnCastellano);
		
		final JButton btnIngles = new JButton(Strings.IGPrincipal_BotonIngles);
		btnIngles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Strings.IGPrincipal_BotonIngles.equals("Ingles")){
					Strings.cambiarIdiomaAIngles();
					IGPrincipal principalIngles = new IGPrincipal();
					principalIngles.show();
					dispose();
				}
			}
		});
		btnIngles.setBounds(241, 287, 113, 23);
		contentPane.add(btnIngles);
	}
}
