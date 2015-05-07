package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import Tringes.Funciones;
import Tringes.GestorCarreras;
import Tringes.GestorClubs;
import Tringes.GestorPerros;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.FeatureDescriptor;




public class IGAsignarPerrosACarrera extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String club;
	JComboBox comboBox;
	
	DefaultComboBoxModel model;
    DefaultTableModel modelTabla = new DefaultTableModel(
            new String[]{Strings.IGAsignarPerrosACarrera_TituloTablaPerro, Strings.IGAsignarPerrosACarrera_TituloTablaInscrito}, 0) {
		
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

	/*/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String title = null;
					IGAsignarPerrosACarrera frame = new IGAsignarPerrosACarrera(title, title, title);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param fechaCarrera 
	 * @param fechaCampeonato 
	 * @param idClub 
	 */
	public IGAsignarPerrosACarrera(final String idClub, final String fechaCampeonato, final String fechaCarrera) {
		club=idClub;
		setTitle(Strings.IGAsignarPerrosACarrera_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTrineo = new JLabel(Strings.IGAsignarPerrosACarrera_LabelTrineo);
		lblTrineo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrineo.setBounds(88, 11, 46, 14);
		contentPane.add(lblTrineo);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource().equals(comboBox)&&comboBox.getSelectedIndex()>0){
					LinkedList<String> perrosDelClub = GestorPerros.getGestorPerros().cargarPerros(idClub);
					LinkedList<String> perrosDelClubNoUtilizadosEnCarrera = GestorPerros.getGestorPerros().obtenerPerrosQueNoCorrenEnElTrineo(idClub, (String) comboBox.getSelectedItem(),fechaCampeonato,fechaCarrera);
					LinkedList<String> perrosUtilizadosEnCarrera = GestorPerros.getGestorPerros().obtenerPerrosQueCorrenEnTrineo((String) comboBox.getSelectedItem(),fechaCampeonato,fechaCarrera);
					vaciarTabla();
					rellenarTabla(perrosDelClubNoUtilizadosEnCarrera, perrosUtilizadosEnCarrera,perrosDelClub);
				}
			}
		});
		comboBox.setBounds(144, 8, 175, 20);
		LinkedList<String> listaTrineos = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantesDelClub(idClub,fechaCarrera, fechaCampeonato);
		Iterator<String> iterator = listaTrineos.iterator();
		comboBox.addItem("");
		while(iterator.hasNext()){
				comboBox.addItem(iterator.next());
			}
		comboBox.setBounds(147, 11, 143, 20);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 414, 171);
		contentPane.add(scrollPane);
		
		table = new JTable(modelTabla);
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
		
		JButton btnAceptar = new JButton(Strings.BotonAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarGambios(fechaCampeonato, fechaCarrera);
			}
		});
		btnAceptar.setBounds(85, 227, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(Strings.BotonCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(259, 227, 89, 23);
		contentPane.add(btnCancelar);
	}
	
	protected void guardarGambios(String campeonato,String carrera) {
		int i=0;
		boolean activado;
		String trineo;
		trineo=(String) comboBox.getSelectedItem();
		while(i<modelTabla.getRowCount()){
			activado = (boolean) modelTabla.getValueAt(i, 1);
			GestorCarreras.getGestorCarreras().modificacionInscripcionPerrosCarrera(carrera,campeonato, trineo, (String) modelTabla.getValueAt(i, 0), activado);
			i++;
		}
		
	}
	
	private void rellenarTabla(LinkedList<String> perrosNoUtilizados,LinkedList<String> perrosParticipantes,LinkedList<String> perrosClub){
		Iterator<String> iterador = perrosNoUtilizados.iterator();
		Iterator<String> iterador2 = perrosParticipantes.iterator();
		Iterator<String> iterador3 = perrosClub.iterator();
		
        String perro;
        while(iterador2.hasNext()){
        	perro=iterador2.next();
        	modelTabla.addRow(new Object[]{perro, true});
        }
        while(iterador3.hasNext()){
        	perro=iterador3.next();
        	if(!perrosNoUtilizados.contains(perro)&&!perrosParticipantes.contains(perro)){
            	modelTabla.addRow(new Object[]{perro, false});
        	}
        }
        
	}
	
	private void vaciarTabla(){
		int row = modelTabla.getRowCount();
    	for(int x = 0;x<row;x++ ){
    		modelTabla.removeRow(0);
    	}
	}
}
