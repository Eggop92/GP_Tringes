package InterfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import excepciones.*;

import Tringes.Funciones;
import Tringes.GestorDueños;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IGGestionarDueños extends JFrame {

	private String idAdmin;
	private JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private JButton anadir;
	private JButton modificar;
	private JButton eliminar;
	private int numDueño;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestionarDueños window = new IGGestionarDueños();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public IGGestionarDueños() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if(table!=null){
					actualizarTabla();
				}
			}
		});
		frame.setTitle(Strings.IGGestionarDueños_TituloVentana);
		frame.setBounds(100, 100, 450, 300);
		setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel sur = new JPanel();
		frame.getContentPane().add(sur, BorderLayout.SOUTH);
		
		anadir = new JButton(Strings.BotonAnadir);
		anadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGAñadirModificarDueño dueño = new IGAñadirModificarDueño("Añadir",0); 
				dueño.show();
			}
		});
		sur.add(anadir);
		
		modificar = new JButton(Strings.BotonModificar);
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object dev = devolverSeleccionado();
				if(dev != null){
//					JOptionPane conf = new JOptionPane();
//					int resp = conf.showConfirmDialog(null, Strings.IGGestionarDueños_Confirmacion);
//					if(resp == conf.OK_OPTION){
					numDueño = Integer.parseInt(Funciones.separarCadena((String)dev)[0]);
					IGAñadirModificarDueño dueño = new IGAñadirModificarDueño("Modificar",numDueño); 
//					dueño.hacerVisible();
					dueño.show();
//					}
				}
				actualizarTabla();
			}
		});
		sur.add(modificar);
		
		eliminar = new JButton(Strings.BotonEliminar);
		eliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Object dev = devolverSeleccionado();
				if(dev != null){
					JOptionPane conf = new JOptionPane();
					int resp = conf.showConfirmDialog(null, Strings.IGGestionarDueños_Confirmacion);
					if(resp == 0){
						int numD = Integer.parseInt(Funciones.separarCadena((String)dev)[0]);
						try{
							GestorDueños.getGestorDueños().eliminarDueño(numD);
						}catch(HayPerrosException e1){
							JOptionPane error = new JOptionPane();
							error.showConfirmDialog(null, Strings.IGGestionarDueños_ErrorPerros, Strings.IGGestionarDueños_ErrorMensaje,0);
							
						}
						catch(GenericException e2){
							e2.printStackTrace();
						}
					}
				}
				
			}
		});
		sur.add(eliminar);
		
		JScrollPane centro = new JScrollPane();
		frame.getContentPane().add(centro, BorderLayout.CENTER);
		model = new DefaultTableModel(
	                new String[]{Strings.IGGestionarDueños_TituloTabla, " "}, 0) {
	 
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
		centro.setViewportView(table);
		//model.addRow(new Object[]{"v1", "v2"});
	}

	private void actualizarTabla(){
		while(model.getRowCount()>0){
			model.removeRow(0);
		}
		cargarTabla();
	}
	
	private void cargarTabla() {
		LinkedList<String> dueños = GestorDueños.getGestorDueños().obtenerDueños();
		Iterator<String> itr = dueños.iterator();
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
		//Mostramos error en caso de seleccion multiple
		JOptionPane conf = new JOptionPane();
		if(enc){
			conf.showMessageDialog(null, Strings.IGGestionarDueños_ErrorMultSelec);
		}
		else{
			if(dato!=-1){
				//Devolvemos dato
					rdo= model.getValueAt(dato, 0);
			}else{
				//Mostramos error de no seleccion
				conf.showMessageDialog(null, Strings.IGGestionarDueños_ErrorNoSelec);
			}
		}
		return rdo;
	}
	
	public void hacerVisible(){
		frame.setVisible(true);
	}

}
