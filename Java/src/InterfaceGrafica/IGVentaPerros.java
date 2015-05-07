
package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.LinkedList;

import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Tringes.Funciones;
import Tringes.GestorCarreras;
import Tringes.GestorDueños;
import Tringes.GestorPerros;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;




public class IGVentaPerros extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JComboBox comboBox;
	String dueño;
	 DefaultTableModel modelTabla = new DefaultTableModel(
			 new String[]{Strings.IGVentaPerros_TituloTablaPerro, Strings.IGVentaPerros_TituloTablaVender}, 0) {
			
	        Class[] types = new Class[]{
	            java.lang.String.class, java.lang.Boolean.class
	        };

	        public Class getColumnClass(int columnIndex) {
	            return types[columnIndex];
	        }
	        
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	            if (columnIndex==modelTabla.getColumnCount()-1) {
	                return true;  //La columna ultima es editable.
	            }
	            return false;  //El resto de celdas no son editables.
	        }
	    };;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String title = null;
					IGVentaPerros frame = new IGVentaPerros(title);
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
	public IGVentaPerros(String elIDDueño) {
		dueño=elIDDueño;
		setTitle(Strings.IGVentaPerros_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPerrosConPosobilidad = new JLabel(Strings.IGVentaPerros_LabelSuperior);
		lblPerrosConPosobilidad.setBounds(10, 11, 414, 14);
		contentPane.add(lblPerrosConPosobilidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 414, 177);
		contentPane.add(scrollPane);
		
		table = new JTable(modelTabla);
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
		LinkedList<String> perros = GestorPerros.getGestorPerros().cargarPerrosDueño(elIDDueño);
		rellenarTabla(perros);
		
		JLabel lblComprador = new JLabel(Strings.IGVentaPerros_LabelComprador);
		lblComprador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComprador.setBounds(10, 221, 186, 20);
		contentPane.add(lblComprador);
		
		comboBox = new JComboBox();
		comboBox.setBounds(206, 221, 218, 20);
		LinkedList<String> dueños = GestorDueños.getGestorDueños().cargarDueño(elIDDueño);
		Iterator<String> iterador2 = dueños.iterator();
		comboBox.addItem("");
		while (iterador2.hasNext()){
			comboBox.addItem(iterador2.next());
		}
		contentPane.add(comboBox);
		
		JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarGambios();
			}
		});
		btnAceptar.setBounds(85, 260, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCerrar = new JButton(Strings.BotonCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(259, 260, 89, 23);
		contentPane.add(btnCerrar);
	}

	protected void guardarGambios() {
			int i=0;
			boolean activado;
			String idDueñoVenta,perro;
			String[] junto;
			junto=Funciones.separarCadena((String) comboBox.getSelectedItem());
			idDueñoVenta=junto[0];
			
			while(i<modelTabla.getRowCount()){
				activado = (boolean) modelTabla.getValueAt(i, 1);
				junto= Funciones.separarCadena((String) modelTabla.getValueAt(i, 0));
				perro=junto[0];
				if (activado){
					GestorPerros.getGestorPerros().venderPerro(perro, idDueñoVenta, dueño);
				}
				i++;
			}
			dispose();
	}
	
	private void rellenarTabla(LinkedList<String> perros){
		Iterator<String> iterador = perros.iterator();
        while(iterador.hasNext()){
        	modelTabla.addRow(new Object[]{iterador.next(), false});
        }
	}
}
