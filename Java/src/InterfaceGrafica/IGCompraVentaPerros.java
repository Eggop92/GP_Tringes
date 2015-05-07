package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;




import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class IGCompraVentaPerros extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String title = null;
					IGCompraVentaPerros frame = new IGCompraVentaPerros(title);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param elIDDueño 
	 */
	public IGCompraVentaPerros(final String elIDDueño) {
		setTitle(Strings.IGCompraVentaPerros_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 241);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(Strings.IGCompraVentaPerros_BotonConfirmarCompras);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGConfirmarCompraPerro confirmar = new IGConfirmarCompraPerro(elIDDueño);
				confirmar.show();
			}
		});
		btnNewButton.setBounds(21, 24, 294, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(Strings.IGCompraVentaPerros_BotonVenderPerro);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGVentaPerros venta = new IGVentaPerros(elIDDueño);
				venta.show();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(21, 91, 294, 44);
		contentPane.add(btnNewButton_1);
		
		JButton btnCancelar = new JButton(Strings.BotonCerrar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(226, 164, 89, 23);
		contentPane.add(btnCancelar);
	}

}
