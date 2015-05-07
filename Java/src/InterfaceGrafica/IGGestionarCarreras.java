package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JTable;

import Tringes.Funciones;
import Tringes.GestorCampeonatos;
import Tringes.GestorCarreras;
import Tringes.GestorTrineos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




public class IGGestionarCarreras extends JFrame {
	
	private JPanel contentPane;

	private JComboBox<String> comboBox ; 
	private JTable table;
	private DefaultTableModel model;
	private JButton btnAñadirCarrera;
	private JButton btnModificarCarrera;
	private JButton btnEliminarCarrera;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestionarCarreras frame = new IGGestionarCarreras();
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
	public IGGestionarCarreras() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if(table!=null){
					cargarTabla();
					//todoscd
				}
			}
		});
		setTitle(Strings.IGGestionarCarreras_TituloVentana/*"Gesti\u00F3n Carreras"*/);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 430);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel sur = new JPanel();
		contentPane.add(sur, BorderLayout.SOUTH);
		
		btnAñadirCarrera = new JButton(Strings.BotonAnadir/*"A\u00F1adir Carrera"*/);
		sur.add(btnAñadirCarrera);
		btnAñadirCarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fechaCampeonato = Funciones.separarCadena((String)comboBox.getSelectedItem())[0];
				IGAñadirModificarCarrera IGAMC = new IGAñadirModificarCarrera("Añadir", fechaCampeonato, "");
				IGAMC.show();
			}
		});
		
		btnModificarCarrera = new JButton(Strings.BotonModificar);
		sur.add(btnModificarCarrera);
		btnModificarCarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fechaCamp, fechaCarrera;
				fechaCamp= Funciones.separarCadena((String) comboBox.getSelectedItem())[0];
				fechaCarrera = (String)devolverSeleccionado();
				fechaCarrera = Funciones.separarCadena(fechaCarrera)[0];
				if(fechaCarrera !=null){
					IGAñadirModificarCarrera frame = new IGAñadirModificarCarrera("Modificar",fechaCamp,fechaCarrera);
					frame.setVisible(true);
				}
			}
		});
		
		btnEliminarCarrera = new JButton(Strings.BotonEliminar);
		btnEliminarCarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fechaCampeonato = Funciones.separarCadena((String) comboBox.getSelectedItem())[0];
				String fechaCarrera = (String)devolverSeleccionado();
				fechaCarrera = Funciones.separarCadena(fechaCarrera)[0];
				if(fechaCarrera !=null){
					JOptionPane conf = new JOptionPane();
					int resp = conf.showConfirmDialog(null, Strings.IGGestionarCarreras_Confirmacion);
					if(resp == conf.OK_OPTION){
						GestorCarreras.getGestorCarreras().eliminarCarrera(fechaCarrera, fechaCampeonato);
					}
				}	
			}
		});
		sur.add(btnEliminarCarrera);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblCampeonatos = new JLabel(Strings.IGGestionarCarreras_lblCampeonatos/*"Campeonatos:"*/);
		panel_1.add(lblCampeonatos);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.equals(e.getSource())&&comboBox.getSelectedIndex()>0){
					cargarTabla();
				}
			}
		});
		panel_1.add(comboBox);
		
		JScrollPane centro = new JScrollPane();
		contentPane.add(centro, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
                new String[]{Strings.IGGestionarCarreras_lblCarreras, " "}, 0) {
 
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Boolean.class
            };
 
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (columnIndex==model.getColumnCount()-1) {
                    return true;  //La columna ultima es editable.
                }
                return false;  //El resto de celdas no son editables.
            }
        };
	
        table = new JTable(model);
        centro.setViewportView(table);
        
        cargarCombobox();
		
	}
	
	private void cargarCombobox() {
		comboBox.addItem("");
		LinkedList<String> campeo = GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		Iterator<String> itr = campeo.iterator();
		while(itr.hasNext()){
			comboBox.addItem(itr.next());
		}
	}
	private void cargarTabla(){
		for(int i=model.getRowCount(); i>0; i--){
			model.removeRow(model.getRowCount()-1);
		}
		String fecha = Funciones.separarCadena((String)comboBox.getSelectedItem())[0];
		LinkedList<String> carreras = GestorCarreras.getGestorCarreras().obtenerCarreras(fecha);
		Iterator<String> itr= carreras.iterator();
		while(itr.hasNext()){
			model.addRow(new Object[]{itr.next(), false});
		}
	}
	private Object devolverSeleccionado(){
		Object rdo=null;
		//buscamos en la tabla el elemento seleccionado
		int i=0, dato=-1;
		boolean enc = false;
		Object cont;
		while(i<model.getRowCount() && !enc){
			cont = model.getValueAt(i, 1);
			if((boolean)cont == true){
				if(dato==-1){
					dato=i;
				}else{
					enc=true;
				}
			}
			i++;
		}
		//Mostramos error en caso de seleccion multiple o sin seleccion
		JOptionPane conf = new JOptionPane();
		if(enc){
			conf.showMessageDialog(null, Strings.IGGEstionarCarreras_ErrorMultSelecl);
		}
		else{
			if(dato!=-1){
					rdo= model.getValueAt(dato, 0);
			}else{
				conf.showMessageDialog(null, Strings.IGGEstionarCarreras_ErrorNoSelec);
			}
		}
		return rdo;
	}
}
