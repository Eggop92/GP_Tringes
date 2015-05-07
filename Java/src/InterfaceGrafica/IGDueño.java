package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import Tringes.Funciones;




import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class IGDue�o extends JFrame {

	protected static final String String = null;
	private JPanel contentPane;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGDue�o frame = new IGDue�o(String);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public IGDue�o(final String elIDDue�o) {
		setTitle(Strings.IGDue�o_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 267, 210);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenidonombreDe = new JLabel(Strings.IGDue�o_LabelSuperior);
		lblBienvenidonombreDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBienvenidonombreDe.setBounds(9, 18, 233, 19);
		contentPane.add(lblBienvenidonombreDe);
		
		JButton btnNewButton = new JButton(Strings.IGDue�o_BotonCompraVenta);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGCompraVentaPerros compraVenta = new IGCompraVentaPerros(elIDDue�o);
				compraVenta.show();
			}
		});
		btnNewButton.setBounds(20, 55, 210, 40);
		contentPane.add(btnNewButton);
		
		JButton btnGestionarTusPerros = new JButton(Strings.IGDue�o_BotonGestionPerros);
		btnGestionarTusPerros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idDue�o =Integer.parseInt(Funciones.separarCadena(elIDDue�o)[0]) ;
				IGGestionarPerros perros = new IGGestionarPerros(idDue�o);
				perros.show();
			}
		});
		btnGestionarTusPerros.setBounds(20, 113, 210, 40);
		contentPane.add(btnGestionarTusPerros);
	}

}
