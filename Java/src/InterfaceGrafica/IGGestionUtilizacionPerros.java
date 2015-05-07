package InterfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Tringes.*;
import excepciones.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IGGestionUtilizacionPerros extends JFrame{

	private String nombreClub;
	private JFrame frame;
	
	private JLabel labelClub;
	private JComboBox<String> comboBox;
	
	private DefaultTableModel model;
	private JTable table;
	
	private JButton anadir;
	private JButton eliminar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestionUtilizacionPerros window = new IGGestionUtilizacionPerros("");
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
	public IGGestionUtilizacionPerros(String nomClub) {
		initialize(nomClub);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String pNombreClub) {
		nombreClub= pNombreClub;
		
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if(table!=null){
					cargarTabla();
				}
			}
		});
		frame.setTitle(Strings.IGGestionUtilizacionPerros_TituloVentana);
		frame.setBounds(100, 100, 450, 300);
		setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel norte = new JPanel();
		frame.getContentPane().add(norte, BorderLayout.NORTH);
		
		labelClub = new JLabel(Strings.IGGestionUtilizacionPerros_LabelClub);
		norte.add(labelClub);
		
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getSource().equals(comboBox)&& comboBox.getSelectedIndex()!=0){
					cargarTabla();
					/*//CUando se cambia el club del comboBox
					//Se elimina el contenido de la tabla
					for(int i=model.getRowCount(); i>0; i--){
						model.removeRow(model.getRowCount()-1);
					}
					int nomClub;
					//Se obtiene el nuevo club seleccionado
					if(nombreClub==null||nombreClub==""){
						nomClub= Integer.parseInt(Funciones.separarCadena((String)comboBox.getSelectedItem())[0]);
					}else{
						nomClub=Integer.parseInt(nombreClub);
					}
					
					//Se obtienen los trineos del nuevo club
					LinkedList<String> perros = GestorClubs.getGestorClubs().obtenerPerrosDeClub(nomClub);
					//Se meten en la tabla
					Iterator<String> itr = perros.iterator();
					while(itr.hasNext()){
						String sPerro=itr.next();
						int perro = Integer.parseInt(Funciones.separarCadena(sPerro)[0]);
						String dueño= GestorPerros.getGestorPerros().obtenerDueñoDePerro(perro);
						model.addRow(new Object[]{sPerro, dueño, false});
					}*/
				}
			}
		});

		norte.add(comboBox);
		cargarCombobox(nombreClub);
		
		JScrollPane centro = new JScrollPane();
		frame.getContentPane().add(centro, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
                new String[]{Strings.IGGestionUtilizacionPerros_TituloTablaPerro, Strings.IGGestionUtilizacionPerros_TituloTablaDueño," "}, 0) {
 
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
	
		JPanel sur = new JPanel();
		frame.getContentPane().add(sur, BorderLayout.SOUTH);
		sur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		anadir = new JButton(Strings.BotonAnadir);
		anadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Llamar a AñadirPerroAlClub
				String club = (String)comboBox.getSelectedItem();
				int idClub = Integer.parseInt(Funciones.separarCadena(club)[0]);
				IGAñadirUtilizacionPerros IGAUP = new IGAñadirUtilizacionPerros(idClub);
			}
		});
		sur.add(anadir);
		
		eliminar = new JButton(Strings.BotonEliminar);
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object sel = devolverSeleccionado(0);
				if(sel!=null){
					JOptionPane conf = new JOptionPane();
					int resp = conf.showConfirmDialog(null, Strings.IGGestionarTrineos_Confirmacion);
					if(resp == conf.OK_OPTION){
							try{
								int perro= Integer.parseInt(Funciones.separarCadena((String)sel)[0]);
								int club;
								if(nombreClub==null||nombreClub==""){
									club=Integer.parseInt(Funciones.separarCadena((String)comboBox.getSelectedItem())[0]);
								}else{
									club=Integer.parseInt(nombreClub);
								}
								GestorClubs.getGestorClubs().eliminarPerroDeClub(perro, club);
							}
							catch(nombreNoExisteException e1){
								e1.printStackTrace();
							} 
							catch (GenericException e1) {
								e1.printStackTrace();
							}
					}
				}
				
			}
		});
		sur.add(eliminar);
		
		frame.setVisible(true);
	}

	
	private void cargarCombobox(String nombreClub) {
		if(nombreClub==null || nombreClub.equals("")){
			comboBox.addItem("");
			LinkedList<String> clubs = GestorClubs.getGestorClubs().obtenerClub();
			Iterator<String> itr = clubs.iterator();
			while(itr.hasNext()){
				comboBox.addItem(itr.next());
			}
		}else{
			comboBox.addItem(nombreClub);
			comboBox.setSelectedItem(nombreClub);
			comboBox.setEnabled(false);
			//comboBox.actionPerformed(null);
		}
	}
	
	private Object devolverSeleccionado(int col){
		Object rdo=null;
		//buscamos en la tabla el elemento seleccionado
		int i=0, dato=-1;
		boolean enc = false;
		Object cont;
		while(i<model.getRowCount() && !enc){
			cont = model.getValueAt(i, model.getColumnCount()-1);
			if((boolean)cont == true){
				if(dato==-1){
					dato=i;
				}else{
					enc=true;
				}
			}
			i++;
		}
		//Mostramos error en caso de seleccion multiple
		JOptionPane conf = new JOptionPane();
		if(enc){
			conf.showMessageDialog(null, Strings.IGGestionarDueños_ErrorMultSelec);
		}
		else{
			if(dato!=-1){
				//Devolvemos dato
					rdo= model.getValueAt(dato, col);
			}else{
				//Mostramos error de no seleccion
				conf.showMessageDialog(null, Strings.IGGestionarDueños_ErrorNoSelec);
			}
		}
		return rdo;
	}
	
	private void cargarTabla() {
		for(int i=model.getRowCount(); i>0; i--){
			model.removeRow(model.getRowCount()-1);
		}
		int nomClub;
		//Se obtiene el nuevo club seleccionado
		if(nombreClub==null||nombreClub==""){
			nomClub= Integer.parseInt(Funciones.separarCadena((String)comboBox.getSelectedItem())[0]);
		}else{
			nomClub=Integer.parseInt(nombreClub);
		}
		
		//Se obtienen los trineos del nuevo club
		LinkedList<String> perros = GestorClubs.getGestorClubs().obtenerPerrosDeClub(nomClub);
		//Se meten en la tabla
		Iterator<String> itr = perros.iterator();
		while(itr.hasNext()){
			String sPerro=itr.next();
			int perro = Integer.parseInt(Funciones.separarCadena(sPerro)[0]);
			String dueño= GestorPerros.getGestorPerros().obtenerDueñoDePerro(perro);
			model.addRow(new Object[]{sPerro, dueño, false});
		}
	}
}
