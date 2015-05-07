package InterfaceGrafica;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import Tringes.Funciones;
import Tringes.GestorCampeonatos;
import Tringes.GestorUsuarios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;




public class IGClasificacion extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	
	private	Vector comboBoxItems;
    final DefaultComboBoxModel model;
    DefaultTableModel modelTabla = new DefaultTableModel(
            new String[]{Strings.IGClasificacion_TituloTablaTrineo, Strings.IGClasificacion_TituloTablaPuntos}, 0) {
		
        Class[] types = new Class[]{
            java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
					IGClasificacion frame = new IGClasificacion();
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
	public IGClasificacion() {
		setTitle(Strings.IGClasificacion_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCampeonato = new JLabel(Strings.IGClasificacion_LabelCampeonato);
		lblCampeonato.setBounds(100, 17, 65, 14);
		contentPane.add(lblCampeonato);
		
		comboBoxItems=new Vector();
		comboBoxItems.add("");
		model = new DefaultComboBoxModel(comboBoxItems);
		
		comboBox_1 = new JComboBox(model);
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getSource().equals(comboBox_1)){
					if(comboBox.getSelectedIndex()>0 && comboBox_1.getSelectedIndex()>0){
					vaciarTabla();
					String[] partido= Funciones.separarCadena((String) comboBox.getSelectedItem());
					String actual= partido[0];
					String[] partido2= Funciones.separarCadena((String) comboBox_1.getSelectedItem());
					String actual2= partido2[0];
					LinkedList<String> rdoCarrera= GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCarrera(actual,actual2);
					rellenarTabla(rdoCarrera);
					}
				}
			}
		});
		//comboBox_1.setEnabled(false);
		comboBox_1.setBounds(175, 49, 137, 20);
		contentPane.add(comboBox_1);		
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getSource().equals(comboBox)){
					if(comboBox.getSelectedIndex()>0){
						vaciarTabla();
						String[] partido= Funciones.separarCadena((String) comboBox.getSelectedItem());
						String actual= partido[0];
						LinkedList<String> rdoCampeonato= GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCampeonato(actual);
						rellenarTabla(rdoCampeonato);
						LinkedList<String> listaCarreras = GestorCampeonatos.getGestorCampeonatos().obtenerCarrerasDelCampeonato(actual);
						Iterator<String> iterator = listaCarreras.iterator();
						model.removeAllElements();
						model.addElement("");
						while(iterator.hasNext()){					
							model.addElement(iterator.next());
						}
					}
				}
			}
		});
		comboBox.setBounds(175, 14, 137, 20);
		
		LinkedList<String> listaCampeonatos = GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		Iterator<String> iterator = listaCampeonatos.iterator();
		comboBox.addItem("");
		while(iterator.hasNext()){
				comboBox.addItem(iterator.next());
			}
		
		contentPane.add(comboBox);
		
		JLabel lblCarrera = new JLabel(Strings.IGClasificacion_LabelCarrera);
		lblCarrera.setBounds(110, 52, 41, 14);
		contentPane.add(lblCarrera);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 414, 159);
		contentPane.add(scrollPane);
		
		table = new JTable(modelTabla);
		scrollPane.setViewportView(table);
	}
	
	private void rellenarTabla(LinkedList<String> datos){
        Iterator<String> iterador = datos.iterator();
        while(iterador.hasNext()){
        	String[] separado = Funciones.separarCadena(iterador.next());
        	//System.out.println(separado[0]+separado[1]);
        	modelTabla.addRow(separado);
        }
	}
	
	private void vaciarTabla(){
		int row = modelTabla.getRowCount();
    	for(int x = 0;x<row;x++ ){
    		modelTabla.removeRow(0);
    	}
	}
}
