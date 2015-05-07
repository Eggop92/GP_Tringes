package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import Tringes.Funciones;
import Tringes.GestorPerros;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IGGestionarPerros extends JFrame {

	private JPanel contentPane;
	private int numFederado;
	private DefaultTableModel model;
	private JTable table=null;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGGestionarPerros frame = new IGGestionarPerros(3);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public IGGestionarPerros(int pNumFederado) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if(table!=null){
					cargarTabla();
				}
			}
		});
		numFederado = pNumFederado;

		setTitle(Strings.IGGestionarPerros_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 430);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		JButton btnAñadir = new JButton(Strings.BotonAnadir);
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGAñadirModificarPerro IGAMP = new IGAñadirModificarPerro("Añadir", 0, numFederado);
				IGAMP.show();
			}
		});
		panelSur.add(btnAñadir);

		JButton btnModificar = new JButton(Strings.BotonModificar);
		panelSur.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object sel = devolverSeleccionado();
				if(sel!=null){
					int idPerro = Integer.parseInt(Funciones.separarCadena((String)sel)[0]);
					IGAñadirModificarPerro IGAMP = new IGAñadirModificarPerro("Modificar", idPerro, numFederado);
					IGAMP.show();
				}
			}
		});

		JButton btnEliminar = new JButton(Strings.BotonEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object sel = devolverSeleccionado();
				if(sel!=null){
					JOptionPane conf = new JOptionPane();
					int resp = conf.showConfirmDialog(null, Strings.IGGestionarTrineos_Confirmacion);
					if(resp == conf.OK_OPTION){
						int idPerro = Integer.parseInt(Funciones.separarCadena((String)sel)[0]);
						GestorPerros.getGestorPerros().eliminarPerro(idPerro);
					}
				}
			}
		});
		panelSur.add(btnEliminar);

		JButton btnCamada = new JButton(Strings.IGGestionarPerros_BotonAñadirCamada);
		btnCamada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object sel = devolverSeleccionado();
				if(sel!=null){
					int perro = Integer.parseInt(Funciones.separarCadena((String)sel)[0]);
					IGAñadirCamada IGAC = new IGAñadirCamada(perro);
					IGAC.show();
				}
			}
		});
		panelSur.add(btnCamada);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		model = new DefaultTableModel(
                new String[]{Strings.IGGestionarPerros_LabelPerros, " "}, 0) {
 
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
		cargarTabla();
	}
	
	private void cargarTabla(){
		//Se elimina el contenido de la tabla
		
		for(int i=model.getRowCount(); i>0; i--){
			model.removeRow(model.getRowCount()-1);
		}
		//cargamos la tabla de nuevo
		LinkedList<String> perros = GestorPerros.getGestorPerros().cargarPerrosDeDueño(numFederado);
		Iterator<String> itr = perros.iterator();
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
			conf.showMessageDialog(null, Strings.IGGestionarPerros_ErrorMultSelec);
		}
		else{
			if(dato!=-1){
					rdo= model.getValueAt(dato, 0);
			}else{
				conf.showMessageDialog(null, Strings.IGGestionarPerros_ErrorNoSelec);
			}
		}
		return rdo;
	}

}
