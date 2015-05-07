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
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;


import excepciones.*;
import Tringes.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IGGestionarClubs extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnEliminarClub;
	private JButton btnAñadirClub;
	private JButton btnModificarClub;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestionarClubs frame = new IGGestionarClubs();
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
	public IGGestionarClubs() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if(table!=null){
					cargarTabla();
				}
			}
		});
		setTitle(Strings.IGGestionarClubs_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 430);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnAñadirClub = new JButton(Strings.IGGestionarClubs_btnAñadir);
		panelSur.add(btnAñadirClub);
		btnAñadirClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGAñadirModificarClub IGAMC = new IGAñadirModificarClub("Añadir", 0);
				IGAMC.setVisible(true);
			}
		});
		
		btnModificarClub = new JButton(Strings.IGGestionarClubs_btnModificar);
		btnModificarClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object sel = devolverSeleccionado();
				if(sel !=null){
					int club = Integer.parseInt(Funciones.separarCadena((String)sel)[0]);
					IGAñadirModificarClub IGAMC = new IGAñadirModificarClub("Modificar", club);
					IGAMC.setVisible(true);
				}	
			}
		});
		panelSur.add(btnModificarClub);
			
		btnEliminarClub = new JButton(Strings.IGGestionarClubs_btnEliminar);
		btnEliminarClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sel = (String)devolverSeleccionado();
				if(sel !=null){
					sel= Funciones.separarCadena(sel)[0];
					JOptionPane conf = new JOptionPane();
					int resp = conf.showConfirmDialog(null, Strings.IGGestionarCarreras_Confirmacion);
					if(resp == conf.OK_OPTION){
						try {
							GestorClubs.getGestorClubs().eliminarClub(Integer.parseInt(sel));
							cargarTabla();
						} catch(HayTrineosException e2){
							conf.showMessageDialog(null, Strings.IGGestionarClubs_ErrorTrineos);
						} catch(HayPerrosException e3){
							conf.showMessageDialog(null, Strings.IGGestionarClubs_ErrorPerros);
						} catch (GenericException e1) {
							e1.printStackTrace();
						}
					}
				}	
			}
		});
		panelSur.add(btnEliminarClub);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		model = new DefaultTableModel(
                new String[]{Strings.IGGestionarClubs_lblClubs, " "}, 0) {
 
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
        cargarTabla();
        scrollPane.setViewportView(table);
		
	}
	
	private void cargarTabla() {
		for(int i=model.getRowCount(); i>0; i--){
			model.removeRow(model.getRowCount()-1);
		}
		LinkedList<String> clubs = GestorClubs.getGestorClubs().obtenerClub();
		Iterator<String> itr = clubs.iterator();
		while(itr.hasNext()){
			model.addRow(new Object[]{(String)itr.next(), new Boolean(false)});
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
			conf.showMessageDialog(null, Strings.IGGEstionarClubs_ErrorMultSelecl);
		}
		else{
			if(dato!=-1){
					rdo= model.getValueAt(dato, 0);
			}else{
				conf.showMessageDialog(null, Strings.IGGEstionarClubs_ErrorNoSelec);
			}
		}
		return rdo;
	}
}
