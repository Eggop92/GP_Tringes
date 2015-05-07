package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import excepciones.*;
import Tringes.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IGGestionarCampeonatos extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JTable table;
	private JButton añadir;
	private JButton modificar;
	private JButton eliminar;
	private JButton gestionCarreras;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestionarCampeonatos frame = new IGGestionarCampeonatos();
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
	public IGGestionarCampeonatos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if(table!=null){
					refrescarTabla();
				}
			}
		});

		setTitle(Strings.IGGestionarCampeonatos_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 430);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
                new String[]{Strings.IGGestionarCampeonatos_lblCampeonatos, " "}, 0) {
 
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
		scrollPane.setViewportView(table);
		refrescarTabla();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		añadir = new JButton(Strings.BotonAnadir);
		añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IGAñadirModificarCampeonato IGAMC = new IGAñadirModificarCampeonato("Añadir", "");
				IGAMC.show();
				refrescarTabla();
			}
		});
		panel.add(añadir);
		
		modificar = new JButton(Strings.BotonModificar);
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object sel = devolverSeleccionado();
				if(sel !=null){
					String camp = Funciones.separarCadena((String)sel)[0];
					IGAñadirModificarCampeonato IGAMC = new IGAñadirModificarCampeonato("Modificar", camp);
					IGAMC.show();
					refrescarTabla();
				}	
			}
		});
		panel.add(modificar);
		
		eliminar = new JButton(Strings.BotonEliminar);
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object sel = devolverSeleccionado();
				if(sel !=null){
					JOptionPane conf = new JOptionPane();
					int resp = conf.showConfirmDialog(null, Strings.IGGestionarCampeonatos_Confirmacion);
					if(resp == conf.OK_OPTION){
						try {
							String camp = Funciones.separarCadena((String)sel)[0];
							GestorCampeonatos.getGestorCampeonatos().eliminarCampeonato(camp);
						} catch (HayCarrerasException e1) {
							conf.showMessageDialog(null, Strings.IGGestionarCampeonatos_HayCarreras);
						} catch (GenericException e1) {
							e1.printStackTrace();
						}
						
					}
				}	
			}
		});
		panel.add(eliminar);
		
		gestionCarreras = new JButton(Strings.IGGestionarCampeonatos_btnGestionarCarreras);
		gestionCarreras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGGestionarCarreras IGGC = new IGGestionarCarreras();
				IGGC.show();
			}
		});
		panel.add(gestionCarreras);
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
			conf.showMessageDialog(null, Strings.IGGestionarCampeonatos_ErrorMultSelec);
		}
		else{
			if(dato!=-1){
					rdo= model.getValueAt(dato, 0);
			}else{
				conf.showMessageDialog(null, Strings.IGGestionarCampeonatos_ErrorNoSelec);
			}
		}
		return rdo;
	}
	
	private void refrescarTabla(){
		//Se elimina el contenido de la tabla
		for(int i=model.getRowCount(); i>0; i--){
			model.removeRow(model.getRowCount()-1);
		}
		//Se obtienen los campeonatos
		LinkedList<String> campeonatos = GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		//Se meten en la tabla
		Iterator<String> itr = campeonatos.iterator();
		while(itr.hasNext()){
			model.addRow(new Object[]{itr.next(), false});
		}
	}
	
}
