package InterfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Tringes.Funciones;
import Tringes.GestorPerros;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;

public class IGAñadirUtilizacionPerros  extends JFrame{

	private JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private int club;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAñadirUtilizacionPerros window = new IGAñadirUtilizacionPerros(0);
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
	public IGAñadirUtilizacionPerros(int idClub) {
		initialize(idClub);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int idClub) {
		club = idClub;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object sel = devolverSeleccionado(0);
				if(sel !=null){
					int idPerro = Integer.parseInt(Funciones.separarCadena((String)sel)[0]);
					GestorPerros.getGestorPerros().insertarUtiliza(club, idPerro);
					JOptionPane aviso = new JOptionPane();
					aviso.showMessageDialog(null, Strings.IGAñadirModificarPerro_PerroAñadido);
					frame.dispose();
				}
			}
		});
		panel.add(btnAceptar);
		
		JButton btnCerrar = new JButton(Strings.BotonCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(btnCerrar);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
                new String[]{Strings.IGGestionUtilizacionPerros_TituloTablaPerro," "}, 0) {
 
            Class[] types = new Class[]{
                java.lang.String.class,  java.lang.Boolean.class
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
        cargarTabla();
        frame.setVisible(true);
	}
	
	private void cargarTabla(){
		int i=0;
		LinkedList<String> perros;
		Iterator<String> itr;
		perros= GestorPerros.getGestorPerros().obtenerPerrosSinUtilizar();
		itr= perros.iterator();
		while(itr.hasNext()){
			model.addRow(new Object[]{itr.next(), false});
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
			conf.showMessageDialog(null, Strings.IGGestionarPerros_ErrorMultSelec);
		}
		else{
			if(dato!=-1){
				//Devolvemos dato
					rdo= model.getValueAt(dato, col);
			}else{
				//Mostramos error de no seleccion
				conf.showMessageDialog(null, Strings.IGGestionarPerros_ErrorNoSelec);
			}
		}
		return rdo;
	}
}
