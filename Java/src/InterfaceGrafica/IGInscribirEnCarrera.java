package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import Tringes.Funciones;
import Tringes.GestorCampeonatos;
import Tringes.GestorCarreras;
import Tringes.GestorClubs;
import Tringes.GestorTrineos;
import Tringes.GestorUsuarios;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;




public class IGInscribirEnCarrera extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox_Club;
	private JComboBox comboBox_Camp;
	private JComboBox comboBox_Carrera;
	
	private	Vector comboBoxItems;
    final DefaultComboBoxModel model;
    DefaultTableModel modelTabla = new DefaultTableModel(
            new String[]{Strings.IGInscribirEnCarrera_TituloTablaTrineo, Strings.IGInscribirEnCarrera_TituloTablaInscrito}, 0) {
		
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
    
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String title = null;
					IGInscribirEnCarrera frame = new IGInscribirEnCarrera("1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param elIDAdmin 
	 */
	public IGInscribirEnCarrera(String elIDUsuario) {
		setTitle(Strings.IGInscribirEnCarrera_TituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 426);
		setResizable(false);
		comboBoxItems=new Vector();
		comboBoxItems.add("");
		model = new DefaultComboBoxModel(comboBoxItems);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClub = new JLabel(Strings.IGInscribirEnCarrera_LabelClub);
		lblClub.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClub.setBounds(10, 14, 127, 14);
		contentPane.add(lblClub);
		
		JLabel lblCarrera = new JLabel(Strings.IGInscribirEnCarrera_LabelCarrera);
		lblCarrera.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCarrera.setBounds(10, 74, 127, 14);
		contentPane.add(lblCarrera);
		
		comboBox_Club = new JComboBox();
		comboBox_Club.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getSource().equals(comboBox_Club)){
					//No tiene que hacer nada
				}
			}
		});
		LinkedList<String> listaTrineos = GestorClubs.getGestorClubs().obtenerClub();
		Iterator<String> iterator = listaTrineos.iterator();
		comboBox_Club.addItem("");
		while(iterator.hasNext()){
				comboBox_Club.addItem(iterator.next());
			}
		comboBox_Club.setBounds(147, 11, 185, 20);
		
		if(!elIDUsuario.equals("5")){
			String elClubLogeado = GestorClubs.getGestorClubs().obtenerIdUsuarioYNombre(elIDUsuario);
			comboBox_Club.setSelectedItem(elClubLogeado);
			comboBox_Club.setEnabled(false);
		}
		contentPane.add(comboBox_Club);
		
		comboBox_Carrera = new JComboBox(model);
		comboBox_Carrera.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource().equals(comboBox_Carrera)){
					if(comboBox_Camp.getSelectedIndex()>0 /*&& comboBox_Club.getSelectedIndex()>0*/ && comboBox_Carrera.getSelectedIndex()>0){
						String[] junto;
						String idClub,fechaCarrera,fechaCampeonato;
						junto=Funciones.separarCadena((String) comboBox_Club.getSelectedItem());
						idClub=junto[0];
						junto=Funciones.separarCadena((String) comboBox_Camp.getSelectedItem());
						fechaCampeonato=junto[0];
						junto=Funciones.separarCadena((String) comboBox_Carrera.getSelectedItem());
						fechaCarrera=junto[0];
						LinkedList<String> listaTrineos = GestorCarreras.getGestorCarreras().cargarTrineosDeClub(idClub);
						LinkedList<String> listaTrineosParticipantes = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes(fechaCarrera, fechaCampeonato);
						vaciarTabla();
						rellenarTabla(listaTrineos, listaTrineosParticipantes);
					}
				}
			}
		});
		comboBox_Carrera.setBounds(147, 71, 185, 20);
		contentPane.add(comboBox_Carrera);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 414, 191);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(modelTabla);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
		
		JButton btnGuardarCambios = new JButton(Strings.IGInscribirEnCarrera_BotonGuardarCambios);
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(modelTabla.getRowCount()>0){
					guardarGambios();
				}
			}
		});
		btnGuardarCambios.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGuardarCambios.setBounds(10, 304, 188, 38);
		contentPane.add(btnGuardarCambios);
		
		JButton btnAsignarPerros = new JButton(Strings.IGInscribirEnCarrera_BotonAsignarPerros);
		btnAsignarPerros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_Carrera.getSelectedIndex()>0&&comboBox_Carrera.getSelectedIndex()>0){
					String[] junto;
					String idClub,fechaCarrera,fechaCampeonato;
					junto=Funciones.separarCadena((String) comboBox_Club.getSelectedItem());
					idClub=junto[0];
					junto=Funciones.separarCadena((String) comboBox_Camp.getSelectedItem());
					fechaCampeonato=junto[0];
					junto=Funciones.separarCadena((String) comboBox_Carrera.getSelectedItem());
					fechaCarrera=junto[0];
					IGAsignarPerrosACarrera asignar = new IGAsignarPerrosACarrera(idClub,fechaCampeonato,fechaCarrera);
					asignar.show();
				}
			}
		});
		btnAsignarPerros.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAsignarPerros.setBounds(235, 304, 189, 38);
		contentPane.add(btnAsignarPerros);
		
		JButton btnCerrar = new JButton(Strings.BotonCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(150, 363, 127, 23);
		contentPane.add(btnCerrar);
		
		JLabel lblCampeonato = new JLabel(Strings.IGInscribirEnCarrera_LabelCampeonato);
		lblCampeonato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCampeonato.setBounds(10, 46, 127, 14);
		contentPane.add(lblCampeonato);
		
		comboBox_Camp = new JComboBox();
		comboBox_Camp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getSource().equals(comboBox_Camp)){
					if(comboBox_Camp.getSelectedIndex()>0){
						vaciarTabla();
						String[] partido= Funciones.separarCadena((String) comboBox_Camp.getSelectedItem());
						String actual= partido[0];
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
		comboBox_Camp.setBounds(147, 43, 185, 20);
		LinkedList<String> listaCampeonatos = GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		Iterator<String> iterator2 = listaCampeonatos.iterator();
		comboBox_Camp.addItem("");
		while(iterator2.hasNext()){
				comboBox_Camp.addItem(iterator2.next());
			}
		contentPane.add(comboBox_Camp);
	}
	
	protected void guardarGambios() {
		int i=0;
		boolean activado;
		String trineo,fechaCarrera;
		String[] junto;
		String carrera,campeonato;
		junto=Funciones.separarCadena((String) comboBox_Camp.getSelectedItem());
		campeonato=junto[0];
		junto=Funciones.separarCadena((String) comboBox_Carrera.getSelectedItem());
		carrera=junto[0];
		while(i<modelTabla.getRowCount()){
			activado = (boolean) modelTabla.getValueAt(i, 1);
			trineo = (String) modelTabla.getValueAt(i, 0);
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera(carrera,campeonato, trineo, activado);
			i++;
		}
		
	}

	private void rellenarTabla(LinkedList<String> trineos,LinkedList<String> trineosParticipantes){
		Iterator<String> iterador = trineos.iterator();
        boolean participa;
        String idTrineo;
        while(iterador.hasNext()){
        	idTrineo=iterador.next();
        	participa=trineosParticipantes.contains(idTrineo);
        	modelTabla.addRow(new Object[]{idTrineo, participa});
        }
	}
	
	private void vaciarTabla(){
		int row = modelTabla.getRowCount();
    	for(int x = 0;x<row;x++ ){
    		modelTabla.removeRow(0);
    	}
	}
}
