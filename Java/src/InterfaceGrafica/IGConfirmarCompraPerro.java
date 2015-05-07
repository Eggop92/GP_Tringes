package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

import Tringes.Funciones;
import Tringes.GestorDueños;
import Tringes.GestorPerros;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class IGConfirmarCompraPerro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String dueño;
	DefaultTableModel modelTabla = new DefaultTableModel(
            new String[]{Strings.IGConfirmarCompraPerro_TituloTablaPerro, Strings.IGConfirmarCompraPerro_TituloTablaComprar}, 0) {
		
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
					IGConfirmarCompraPerro frame = new IGConfirmarCompraPerro(title);
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
	public IGConfirmarCompraPerro(String elIDDueño) {
		dueño=elIDDueño;
		setTitle(Strings.IGConfirmarCompraPerro_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 328);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblComprasPendientesDe = new JLabel(Strings.IGConfirmarCompraPerro_LabelSuperior);
		lblComprasPendientesDe.setBounds(10, 11, 191, 14);
		contentPane.add(lblComprasPendientesDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 414, 177);
		contentPane.add(scrollPane);
		
		table = new JTable(modelTabla);
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		LinkedList<String> listaPerrosConfirmacion = GestorPerros.getGestorPerros().cargarComprasPendientes(elIDDueño);
		rellenarTabla(listaPerrosConfirmacion);
		scrollPane.setViewportView(table);
		
		JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarGambios();
			}
		});
		btnAceptar.setBounds(85, 242, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCerrar = new JButton(Strings.BotonCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(259, 242, 89, 23);
		contentPane.add(btnCerrar);
		
	}
	
	protected void guardarGambios() {
		int i=0;
		boolean activado;
		String idDueñoVenta,perro;
		String[] junto;
		
		while(i<modelTabla.getRowCount()){
			activado = (boolean) modelTabla.getValueAt(i, 1);
			junto= Funciones.separarCadena((String) modelTabla.getValueAt(i, 0));
			perro=junto[0];
			if (activado){
				GestorPerros.getGestorPerros().confirmarCompra(perro, dueño);
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
