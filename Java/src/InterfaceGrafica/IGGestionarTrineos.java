package InterfaceGrafica;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Tringes.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IGGestionarTrineos extends JFrame{

	private String nombreClub;
	private JFrame frame;
	private JPanel norte;
	private JComboBox<String> comboBox ;
	private JScrollPane centro;
	private DefaultTableModel model;
	private JTable table;
	private JPanel sur;
	private JButton eliminar;
	private JButton modificar;
	private JButton anadir;
	private JTable table_1;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestionarTrineos window = new IGGestionarTrineos("");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public IGGestionarTrineos(String nombreClub) {
		initialize(nombreClub);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize(String pNombreClub) {
		nombreClub = pNombreClub;
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if(table!=null){
					refrescarTabla();
				}
			}
		});
		frame.setBounds(100, 100, 450, 300);
		setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle(Strings.IGGestionarTrineos_TituloVentana);
		norte = new JPanel();
		frame.getContentPane().add(norte, BorderLayout.NORTH);
		
		JLabel labelClub = new JLabel(Strings.IGGestionarTrineos_LabelClub);
		norte.add(labelClub);
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getSource().equals(comboBox)&&comboBox.getSelectedIndex()!=0){
					refrescarTabla();
				}
			}
		});
		norte.add(comboBox);
		
		
		sur = new JPanel();
		frame.getContentPane().add(sur, BorderLayout.SOUTH);
		anadir = new JButton(Strings.BotonAnadir);
		
		anadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nombreClub==null || nombreClub.equals("")){
						String ID_Club_Nombre = (String)comboBox.getSelectedItem();
						int ID_Club = Integer.parseInt(Funciones.separarCadena(ID_Club_Nombre)[0]);
						IGAñadirModificarTrineo añadirModifTrineo = new IGAñadirModificarTrineo("Anadir",ID_Club,0);
						añadirModifTrineo.hacerVisible();
				}else{
					String ID_Club_Nombre = (String)comboBox.getSelectedItem();
					int ID_Club = Integer.parseInt(Funciones.separarCadena(ID_Club_Nombre)[0]);
					IGAñadirModificarTrineo añadirModifTrineo = new IGAñadirModificarTrineo("Anadir",ID_Club,0);
					añadirModifTrineo.hacerVisible();
				}
			}
		});
		sur.add(anadir);
		
		
		modificar = new JButton(Strings.BotonModificar);
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object sel = devolverSeleccionado();
				if(sel !=null){
					int club= Integer.parseInt(Funciones.separarCadena((String)comboBox.getSelectedItem())[0]);
					IGAñadirModificarTrineo amv= new IGAñadirModificarTrineo("Modificar",club,(int) sel);
					amv.hacerVisible();
					refrescarTabla();
				}	
			}
		});
		sur.add(modificar);
		eliminar = new JButton(Strings.BotonEliminar);
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object sel = devolverSeleccionado();
				if(sel !=null){
					JOptionPane conf = new JOptionPane();
					int resp = conf.showConfirmDialog(null, Strings.IGGestionarTrineos_Confirmacion);
					if(resp == conf.OK_OPTION){
						GestorTrineos.getGestorTrineos().eliminarTrineoDeClub((int)sel);
						refrescarTabla();
					}
				}	
			}
		});
		sur.add(eliminar);
		
		centro = new JScrollPane();
		frame.getContentPane().add(centro, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
                new String[]{Strings.IGGestionarTrineos_TituloTrineo, " "}, 0) {
 
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
        
        table_1 = new JTable();
        centro.setColumnHeaderView(table_1);
        cargarCombobox(nombreClub);
		
	}

	private void cargarCombobox(String idClub) {
		if(idClub==null || idClub.equals("")){
			LinkedList<String> clubs = GestorClubs.getGestorClubs().obtenerClub();
			comboBox.addItem("");
			Iterator<String> itr = clubs.iterator();
			while(itr.hasNext()){
				comboBox.addItem(itr.next());
			}
		}else{
			comboBox.removeAllItems();
			String nClub = GestorClubs.getGestorClubs().obtenerDatosClub(Integer.parseInt(idClub)).get(1);
			nClub=idClub+" - "+nClub;
			comboBox.addItem("");
			comboBox.addItem(nClub);
			comboBox.setSelectedIndex(1);
			comboBox.actionPerformed(new ActionEvent(comboBox, 1, null));
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
			conf.showMessageDialog(null, Strings.IGGestionarTrineos_ErrorMultSelec);
		}
		else{
			if(dato!=-1){
					rdo= model.getValueAt(dato, 0);
			}else{
				conf.showMessageDialog(null, Strings.IGGestionarTrineos_ErrorNoSelec);
			}
		}
		return rdo;
	}

	public void hacerVisible(){
		frame.setVisible(true);
	}
	
	private void refrescarTabla(){
		//Cuando se cambia el club del comboBox
		//Se elimina el contenido de la tabla
		for(int i=model.getRowCount(); i>0; i--){
			model.removeRow(model.getRowCount()-1);
		}
		int nomClub;
		//Se obtiene el nuevo club seleccionado
		if(nombreClub==null || nombreClub==""){
			nomClub= Integer.parseInt(Funciones.separarCadena((String)comboBox.getSelectedItem())[0]);
		}
		else{
			nomClub= Integer.parseInt(nombreClub);
		}
		
		//Se obtienen los trineos del nuevo club
		LinkedList<Integer> trineos = GestorTrineos.getGestorTrineos().obtenerTrineosDeClub(nomClub);
		//Se meten en la tabla
		Iterator<Integer> itr = trineos.iterator();
		while(itr.hasNext()){
			model.addRow(new Object[]{itr.next(), false});
		}
	}
}
