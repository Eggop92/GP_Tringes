package InterfaceGrafica;


import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import excepciones.GenericException;
import excepciones.apareamientoExistenteException;
import Tringes.GestorPerros;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IGAñadirCamada extends JFrame {

	private JPanel contentPane;
	private JTextField textField_NumCachorros;
	private JTextField textField_1;
	private int idPerro;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAñadirCamada frame = new IGAñadirCamada(0);
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
	public IGAñadirCamada(int pIdPerro) {
		idPerro = pIdPerro;
		
		setTitle(Strings.IGAñadirCamada_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 301, 215);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndicaElNumero = new JLabel(Strings.IGAñadirCamada_LabelSuperior1);
		lblIndicaElNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIndicaElNumero.setBounds(10, 11, 272, 14);
		contentPane.add(lblIndicaElNumero);
		
		JLabel lblTenidoperroEn = new JLabel(Strings.IGAñadirCamada_LabelSuperior2);
		lblTenidoperroEn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTenidoperroEn.setBounds(10, 28, 272, 14);
		contentPane.add(lblTenidoperroEn);
		
		JLabel lblCach = new JLabel(Strings.IGAñadirCamada_LabelCachorros);
		lblCach.setBounds(10, 63, 79, 14);
		contentPane.add(lblCach);
		
		textField_NumCachorros = new JTextField();
		textField_NumCachorros.setBounds(99, 60, 183, 20);
		contentPane.add(textField_NumCachorros);
		textField_NumCachorros.setColumns(10);
		
		JLabel lblFecha = new JLabel(Strings.IGAñadirCamada_LabelFecha);
		lblFecha.setBounds(10, 97, 53, 14);
		contentPane.add(lblFecha);
		
		final JComboBox comboBox_Dia = new JComboBox();
		comboBox_Dia.setBounds(73, 94, 53, 20);
		comboBox_Dia.addItem("");
		for(int dia=1;dia<=31;dia++){
			comboBox_Dia.addItem(dia);
		}
		contentPane.add(comboBox_Dia);
	
		final JComboBox comboBox_Mes = new JComboBox();
		comboBox_Mes.setBounds(136, 94, 63, 20);
		comboBox_Mes.addItem("");
		for(int mes=1;mes<=12;mes++){
			comboBox_Mes.addItem(mes);
		}
		contentPane.add(comboBox_Mes);
		
		final JComboBox comboBox_Año = new JComboBox();
		comboBox_Año.setBounds(215, 94, 67, 20);
		comboBox_Año.addItem("");
		for(int año=2014;año>=1980;año--){
			comboBox_Año.addItem(año);
		}
		contentPane.add(comboBox_Año);
		
		/*textField_1 = new JTextField();
		textField_1.setBounds(73, 94, 100, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);*/
		
		JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_Año.getSelectedIndex()>0 && comboBox_Mes.getSelectedIndex()>0 && comboBox_Dia.getSelectedIndex()>0 && !textField_NumCachorros.getText().equals("")){
					String fecha = comboBox_Año.getSelectedItem()+"-"+comboBox_Mes.getSelectedItem()+"-"+comboBox_Dia.getSelectedItem();
					int numCrias = Integer.parseInt(textField_NumCachorros.getText());
					try {
						GestorPerros.getGestorPerros().añadirCamada(idPerro, fecha, numCrias);
						dispose();
					} catch (apareamientoExistenteException e1) {
						JOptionPane.showMessageDialog(null, Strings.IGAñadirCamada_ApareamientoExistente);
					}catch(GenericException e1){
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, Strings.IGAñadirModificar_ErrorRellenarCampos);
				}
			}
		});
		btnAceptar.setBounds(45, 145, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(Strings.BotonCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(178, 145, 89, 23);
		contentPane.add(btnCancelar);
	}

}
