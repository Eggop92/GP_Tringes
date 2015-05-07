package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import excepciones.GenericException;
import excepciones.carreraExistenteException;

public class GestorCarreras {

	private static GestorCarreras miGestorCarreras;
	
	private GestorCarreras(){}
	
	public static GestorCarreras getGestorCarreras(){
		if(miGestorCarreras==null){
			miGestorCarreras = new GestorCarreras();
		}
		return miGestorCarreras;
	}
	
	//public LinkedList<String> obtenerCarrera(String pFechaCarrera)
	public void añadirCarrera(String pFechaCarrera, String pFechaCampeonato, String pNombre, String pLugar, String pRecorrido, int pID_Modalidad)throws GenericException{
		String sql;
		try{
			sql="SELECT Estado FROM carrera WHERE  FechaCarrera='"+pFechaCarrera+"' and FechaCampeonato='"+pFechaCampeonato+"';";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			if(rdoSql.next()){
				int rdo = rdoSql.getInt("Estado");
				if(rdo==0){
					activarEstado(pFechaCarrera, pFechaCampeonato);
				}else{
					throw new carreraExistenteException();
				}
			}else{
				insertarCarrera(pFechaCarrera, pFechaCampeonato, pNombre, pLugar, pRecorrido, pID_Modalidad);
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}			
	}
	//Cambio estado de 0 a 1
	public void activarEstado(String pFechaCarrera, String pFechaCampeonato){
		String sql;
		try{
			sql="UPDATE carrera SET Estado=TRUE WHERE  FechaCarrera='"+pFechaCarrera+"', FechaCampeonato='"+pFechaCampeonato+"';";
			GestorBD.getInstance().actualizar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}			
	}
	
	public void insertarCarrera(String pFechaCarrera, String pFechaCamp, String pNombre, String pLugar, String pRecorrido, int pID_Modalidad){
		String sql;
		try{
			sql="INSERT INTO carrera VALUES ('"+pFechaCarrera+"', '"+pFechaCamp+"', '"+pNombre+"', '"+pLugar+"', '"+pRecorrido+"', TRUE, "+pID_Modalidad+");";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	//lista con las fechas de carreras y nombres de un campeonato
	public LinkedList<String> obtenerInfoCarrera(String pFechaCamp){
		LinkedList<String> carreras = new LinkedList<String>();
		String sql;
		try{
			sql="SELECT FechaCarrera, Nombre FROM carrera WHERE  FechaCampeonato= '"+pFechaCamp+"' AND Estado=TRUE;";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			while(rdoSql.next()){
				carreras.add(rdoSql.getString("FechaCarrera")+" - "+rdoSql.getString("Nombre"));
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return carreras;
	}

	public LinkedList<String> obtenerCarreras(){
		LinkedList<String> listaCarreras = null;
		//Utilizo el metodo de Gestor Carreras
		return listaCarreras;
	}
	
	public LinkedList<String> obtenerTrineosParticipantes (String laFechaCarrera,String laFechaCampeonato){
		LinkedList<String> listaTrineos = new LinkedList<String>();
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select ID_Trineo from recorridocarrera where FechaCarrera='"+laFechaCarrera+"' AND FechaCampeonato='"+laFechaCampeonato+"';");
			while (rdo.next()){
				listaTrineos.add(rdo.getString("ID_Trineo"));
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaTrineos;
	}
	public LinkedList<String> obtenerTrineosParticipantesDelClub (String idClub,String fechaCarrera, String fechaCampeonato){
		LinkedList<String> listaTrineos = new LinkedList<String>();
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select ID_Trineo from recorridocarrera where FechaCarrera='"+fechaCarrera+"' AND FechaCampeonato='"+fechaCampeonato+"' AND ID_Trineo in (Select ID_Trineo from trineo Where ID_Club='"+idClub+" and estado=true');");
			while (rdo.next()){
				listaTrineos.add(rdo.getString("ID_Trineo"));
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaTrineos;
	}
	public void modificacionInscripcionCarrera(String carrera, String campeonato, String trineo, boolean estaInscrito){
		LinkedList<String> listaTrineosParticipantes = obtenerTrineosParticipantes(carrera, campeonato);
		try {
			if(listaTrineosParticipantes.contains(trineo)){
				if(!estaInscrito){
					GestorBD.getInstance().actualizar("Delete From recorridocarrera where ID_Trineo='"+trineo+"' AND FechaCarrera='"+carrera+"' AND fechaCampeonato='"+campeonato+"'");
				}
			}
			else{
				if(estaInscrito){
					GestorBD.getInstance().actualizar("Insert into recorridocarrera values('"+carrera+"','"+campeonato+"','"+trineo+"',0,0)");
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		public LinkedList<String> cargarTrineosDeClub(String elNombreClub){
			LinkedList<String> listaTrineos= new LinkedList<String>();
			try {
				ResultSet rdo = GestorBD.getInstance().consulta("Select ID_Trineo from trineo where ID_Club='"+elNombreClub+"' and estado=TRUE;");
				while (rdo.next()){
					listaTrineos.add(rdo.getString("ID_Trineo"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listaTrineos;
		}
		
		public void modificacionInscripcionPerrosCarrera(String carrera, String campeonato, String trineo, String perro, boolean activado) {
			LinkedList<String> listaPerrosParticipantes = GestorPerros.getGestorPerros().obtenerPerrosQueCorren(campeonato, carrera);
			String[] junto = Funciones.separarCadena(perro);
			String idperro = junto[0];
			try {
				if(listaPerrosParticipantes.contains(perro)){
					if(!activado){
						GestorBD.getInstance().actualizar("Delete From llevara where ID_Trineo='"+trineo+"' AND FechaCarrera='"+carrera+"' AND fechaCampeonato='"+campeonato+"' AND ID_Perro='"+idperro+"'");
					}
				}
				else{
					if(activado){
						GestorBD.getInstance().actualizar("Insert into llevara values('"+idperro+"','"+trineo+"','"+carrera+"','"+campeonato+"')");
					}
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
	/**
	 * Cambia el estado de la carrera a false, eliminando la carrera.
	 * @param fechaCarrera
	 * @param fechaCampeonato
	 */
	public void eliminarCarrera(String fechaCarrera, String fechaCampeonato){
		if(fechaCarrera!=null && fechaCampeonato!=null){
			String SQL = "update carrera set estado=false where fechaCarrera='"+fechaCarrera+"' and fechaCampeonato='"+fechaCampeonato+"';";
			try{
				GestorBD.getInstance().actualizar(SQL);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	//obtenerCarreras(laFechaCampeonato)
		public LinkedList<String> obtenerCarreras(String laFechaCampeonato){
			
			LinkedList<String> datos = new LinkedList<String>();
			String nombre = null;
			String fechaCarrera = null;
			
			ResultSet resultado;
			try {
				resultado = GestorBD.getInstance().consulta("SELECT Nombre, FechaCarrera FROM carrera WHERE FechaCampeonato='"+laFechaCampeonato+"' and Estado=TRUE;");
			
				while(resultado.next()){ 
					nombre = resultado.getString("Nombre");
					fechaCarrera = resultado.getString("FechaCarrera");
					datos.add(fechaCarrera+" - "+nombre);
				}
				GestorBD.getInstance().cerrarConsulta(resultado);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return datos;	
			
		}
		
		//obtenerCalificaciones(laFechaCarrera,laFechaCampeonato,elIdTrineo)
		public LinkedList<Integer> obtenerCalificaciones(String laFechaCarrera,String laFechaCampeonato, int elIdTrineo){  // -- MIRAR EL ORDEN DE LOS ATRIBUTOS
			
			LinkedList<Integer> datos = new LinkedList<Integer>();
			int posicion = 0;
			int puntos = 0;
			
			ResultSet resultado;
			try {
				resultado = GestorBD.getInstance().consulta("SELECT Posicion,Puntos FROM recorridoCarrera WHERE FechaCampeonato='"+laFechaCampeonato+"' AND FechaCarrera='"+laFechaCarrera+"' and ID_Trineo='"+elIdTrineo+"';");
			
				if(resultado.next()== true){
					posicion = resultado.getInt("Posicion");
					puntos = resultado.getInt("Puntos");	
				}
				GestorBD.getInstance().cerrarConsulta(resultado);
				
				datos.add(posicion);
				datos.add(puntos);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return datos;	
		}
		
		
		//guardarCambiosCalificacion(nuevaPosicion,nuevaPuntuacion,laFechaCarrera,laFechaCampeonato,elIdTrineo )
		public void guardarCambiosCalificacion(int nuevaPosicion, int nuevaPuntuacion, String laFechaCarrera, String laFechaCampeonato, int elIdTrineo ){  // -- MIRAR EL ORDEN DE LOS ATRIBUTOS
			
			try {
				GestorBD.getInstance().actualizar("UPDATE recorridoCarrera SET Posicion="+nuevaPosicion+", Puntos="+nuevaPuntuacion+" WHERE FechaCarrera='"+laFechaCarrera+"' AND FechaCampeonato='"+laFechaCampeonato+"' AND ID_Trineo="+elIdTrineo+";");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//obtenerModalidadCarrera(laFechaCarrera,laFechaCampeonato)
		public LinkedList<String> obtenerDatosCarrera(String laFechaCarrera, String laFechaCampeonato){
			
			LinkedList<String> datos = new LinkedList<String>();
			String fechaCampeonato = "";
			String fechaCarrera = "";
			String nombre = "";
			String lugar = "";
			String recorrido = "";
			String idModalidad = "";
			
			String descripcion = "";
			
			ResultSet resultado;
			try {
				resultado = GestorBD.getInstance().consulta("SELECT FechaCampeonato,FechaCarrera,Nombre,Lugar,Recorrido,ID_Modalidad FROM carrera WHERE FechaCampeonato='"+laFechaCampeonato+"' AND FechaCarrera='"+laFechaCarrera+"';");
			if(resultado.next()){
				fechaCampeonato = resultado.getString("FechaCampeonato");
				fechaCarrera = resultado.getString("FechaCarrera");
				nombre = resultado.getString("Nombre");
				lugar = resultado.getString("Lugar");
				recorrido = resultado.getString("Recorrido");
				idModalidad = resultado.getInt("id_Modalidad")+"";
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			datos.add(fechaCampeonato);
			datos.add(fechaCarrera);
			datos.add(nombre);
			datos.add(lugar);
			datos.add(recorrido);
			datos.add(idModalidad);
			
			/*resultado = GestorBD.getInstance().consulta("SELECT ID_Modalidad,Descripcion FROM modalidad");
			while(resultado.next()==true){ //¿ES NECESARIO EL ID MODALIDAD?
				idModalidad = resultado.getString("ID_Modalidad");
				descripcion = resultado.getString("Descripcion");
				datos.add(idModalidad);
				datos.add(descripcion);
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			 */
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return datos;
					
		}
		
		//modificarDatosCarrera(nombreModif,idModalidadModif,lugarModif,recorridoModif,fechaCarreraModif,fechaCampeonatoModif, String laFechaCarrera,laFechaCampeonato)
		public void modificarDatosCarrera(String nombreModif, int idModalidadModif,String lugarModif,String recorridoModif,String fechaCarreraModif, String fechaCampeonatoModif, String laFechaCarrera, String laFechaCampeonato){
			
			try {
				GestorBD.getInstance().actualizar("UPDATE carrera SET Nombre='"+nombreModif+"', ID_Modalidad='"+idModalidadModif+"', Lugar='"+lugarModif+"', FechaCarrera='"+fechaCarreraModif+"', FechaCampeonato='"+fechaCampeonatoModif+"' WHERE FechaCarrera=' "+laFechaCarrera+"' AND FechaCampeonato='"+laFechaCampeonato+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	public LinkedList<String> obtenerModalidades(){
		LinkedList<String> rdo = new LinkedList<String>();
		try{
			String id, nombre;
			String sql = "select id_modalidad, descripcion from modalidad;";
			ResultSet sqlRdo= GestorBD.getInstance().consulta(sql);
			while(sqlRdo.next()){
				id= sqlRdo.getString("id_modalidad");
				nombre=sqlRdo.getString("descripcion");
				rdo.add(id+" - "+nombre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
		
	
}
