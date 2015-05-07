package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import excepciones.GenericException;
import excepciones.HayCarrerasException;
import excepciones.campeonatoExistenteException;

public class GestorCampeonatos {
	
	private static GestorCampeonatos miGestorCampeonatos;

	private GestorCampeonatos() {}
	
	public static GestorCampeonatos getGestorCampeonatos(){
		if(miGestorCampeonatos==null){
			miGestorCampeonatos = new GestorCampeonatos();
		}
		return miGestorCampeonatos;
	}

	//public LinkedList<String> obtenerCampeonato(String pFechaCamp)
	public void añadirCampeonato(String pFechaCamp, String pNombre)throws GenericException{
		String sql;
		try {
			sql="SELECT Estado FROM Campeonato WHERE fechaCampeonato= '"+pFechaCamp+"';";
			ResultSet capeonato= GestorBD.getInstance().consulta(sql);
			if(capeonato.next()){
				int estado = capeonato.getInt("Estado");
				
				if(estado == 0){
					activarEstado(pFechaCamp);
				}else{
					throw new campeonatoExistenteException();
				}
			}
			else{
				insertarCampeonato(pFechaCamp,pNombre);
			}
			capeonato.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
	}
	//Cambio estado de 0 a 1
	public void activarEstado(String pFechaCamp){
		String sql;
		try{
			sql = "UPDATE campeonato SET Estado=TRUE WHERE fechaCampeonato='"+pFechaCamp+"';";
			GestorBD.getInstance().actualizar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}			
	}
	// sabiendo q no existe
	public void insertarCampeonato(String pFechaCamp, String pNombre){
		String sql;
		try{
			sql= "INSERT INTO Campeonato VALUES('"+pFechaCamp+"', '"+pNombre+"', 1);";
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	} 

	public LinkedList<String> obtenerInfoCampeonato(){
		LinkedList<String> campeonatos = new LinkedList<String>();
		String sql;
		try {
			sql="SELECT FechaCampeonato, Nombre FROM Campeonato WHERE Estado= TRUE);";
			ResultSet capeonato= GestorBD.getInstance().consulta(sql);
			while(capeonato.next()){
				campeonatos.add(capeonato.getString("FechaCampeonato")+" - "+capeonato.getString("Nombre"));
			}
			capeonato.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return campeonatos;
	}

	public LinkedList<String> obtenerCampeonatos(){
		LinkedList<String> listaCampeonatos = new LinkedList<>();
		String actual = null;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from campeonato where estado=true;");
			while(rdo.next()){
				actual=rdo.getString("FechaCampeonato");
				actual =actual + " - " + rdo.getString("Nombre");
				listaCampeonatos.addLast(actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCampeonatos;
	}
	public LinkedList<String> obtenerCarrerasDelCampeonato(String elNombreCampeonato){
		LinkedList<String> listaCarreras = new LinkedList<>();
		String actual = null;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from Carrera where FechaCampeonato = '"+ elNombreCampeonato +"' and estado=TRUE");
			while (rdo.next()){
				actual = rdo.getString("FechaCarrera");
				actual = actual + " - " + rdo.getString("Nombre");
				listaCarreras.add(actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCarreras;
	}
	
	public LinkedList<String> obtenerPuntosEnCarrera(String elNombreCampeonato, String laFechaDeCarrera){
		LinkedList<String> rdoCarreras = new LinkedList<>();
		String actual;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select DISTINCT(ID_Trineo),Puntos from recorridocarrera where FechaCarrera ='"+laFechaDeCarrera+"' and fechaCampeonato='"+elNombreCampeonato+"'");
			while(rdo.next()){
				actual= rdo.getString("ID_Trineo");
				actual=actual + " - " + rdo.getString("Puntos");
				rdoCarreras.add(actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rdoCarreras;
	}
	
	public LinkedList<String> obtenerPuntosEnCampeonato(String elNombreCampeonato) {
		LinkedList<String> rdoCarreras = new LinkedList<>();
		String actual;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select Sum(Puntos)as PuntosT,ID_Trineo from recorridocarrera where fechaCampeonato='"+elNombreCampeonato+"' Group by ID_Trineo");
			while(rdo.next()){
				actual= rdo.getString("ID_Trineo");
				actual=actual + " - " + rdo.getString("PuntosT");
				rdoCarreras.add(actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rdoCarreras;
	}
	
	
	/**
	 * Cambia el estado del campeonato a false, eliminando el campeonato, si el campeonato no tiene 
	 * carreras activas asociadas.
	 * @param fechaCamp
	 * @throws GenericException
	 */
	public void eliminarCampeonato(String fechaCamp) throws GenericException{
		if(fechaCamp!=null){
			int numCarreras=0;
			String SQL = "select count(*) as count from carrera where estado=true and fechaCampeonato='"+fechaCamp+"';";
			try {
				ResultSet rdoSQL= GestorBD.getInstance().consulta(SQL);
				if(rdoSQL.next()){
					numCarreras = rdoSQL.getInt("count");
					rdoSQL.close();
					if(numCarreras == 0){
						SQL = "update campeonato set estado=false where fechaCampeonato='"+fechaCamp+"';";
						GestorBD.getInstance().actualizar(SQL);
					}else{
						throw new HayCarrerasException();
					}
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	
	//modificarDatosCampeonato(fechaCampeonatoModif,nombreModif,laFechaCampeonato)
		public void modificarDatosCampeonato(String fechaCampeonatoModif, String nombreModif,String laFechaCampeonato){
			
			try {
				GestorBD.getInstance().actualizar("UPDATE campeonato SET FechaCampeonato='"+fechaCampeonatoModif+"', Nombre='"+nombreModif+"' WHERE FechaCampeonato='"+laFechaCampeonato+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//obtenerDatosCampeonato(laFechaCampeonato)
		public LinkedList<String> obtenerDatosCampeonato(String laFechaCampeonato){
			
			LinkedList<String> datos = new LinkedList<String>();
			String fechaCampeonato = null;
			String nombre = null;
			
			ResultSet resultado;
			try {
				resultado = GestorBD.getInstance().consulta("SELECT FechaCampeonato,Nombre FROM campeonato WHERE FechaCampeonato='"+laFechaCampeonato+"';");
			if(resultado.next()==true){
				fechaCampeonato = resultado.getDate("FechaCampeonato").toString();
				nombre = resultado.getString("Nombre");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			datos.add(fechaCampeonato);
			datos.add(nombre);
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return datos;
					
		}


	
	
}
