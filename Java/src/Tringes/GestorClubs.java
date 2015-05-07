package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import excepciones.*;

public class GestorClubs {

	private static GestorClubs miGestorClubs;
	
	private GestorClubs(){}
	
	public static GestorClubs getGestorClubs(){
		if(miGestorClubs==null){
			miGestorClubs = new GestorClubs();
		}
		return miGestorClubs;
	}
	
	//public LinkedList<String> obtenerClub(String pNombre, String pPatrocinador)
	public String añadirClub(int ID_Club, String pNombre, String pPatrocinador)throws GenericException{
		String sql, contraseña="";
		try{
			sql="SELECT Estado FROM club WHERE  ID_CLUB="+ID_Club+";";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			if(rdoSql.next()){
				int rdo = rdoSql.getInt("Estado");
				if(rdo==0){
					contraseña = activarEstado(ID_Club);
				}else{
					throw new clubExistenteException();
				}
			}else{
				sql="select nombreUsuario from usuario where nombreUsuario='"+ID_Club+"';";
				rdoSql= GestorBD.getInstance().consulta(sql);
				if(rdoSql.next()){
					throw new IDEstaOcupadoException();
				}else{
					sql="select nombre from club where nombre='"+pNombre+"';";
					rdoSql= GestorBD.getInstance().consulta(sql);
					if(rdoSql.next()){
						throw new NombreClubEstaOcupadoException();
					}else{
						contraseña = insertarClub(ID_Club, pNombre, pPatrocinador);
					}
				}
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return contraseña;
	}
	//Cambio estado de 0 a 1
	public String activarEstado(int pID_Club){
		String sql, contraseña="";
		try{
			sql="UPDATE club SET Estado=TRUE WHERE  ID_CLUB="+pID_Club+";";	
			GestorBD.getInstance().actualizar(sql);
			sql="select contraseña from usuario where nombreUsuario='"+pID_Club+"';";
			ResultSet rdo =GestorBD.getInstance().consulta(sql);
			if(rdo.next()){
				contraseña = rdo.getString("contraseña");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
		return contraseña;
	}
	// inserto un club q se q no existe
	public String insertarClub(int pID_Club, String pNombre, String pPatrocinador) {
		String sql, contraseña="";
		try{
			contraseña= GestorUsuarios.getGestorUsuarios().generarContraseña();
			sql="INSERT INTO usuario VALUES ("+pID_Club+", '"+contraseña+"', 'C');";	
			GestorBD.getInstance().insertar(sql);
			sql="INSERT INTO club VALUES ("+pID_Club+", '"+pNombre+"', '"+pPatrocinador+"', TRUE);";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return contraseña;
	}
	// Obtengo todos los clubs que estan activos
	public LinkedList<String> obtenerInfoClub(){
		LinkedList<String> clubs = new LinkedList<String>();
		String sql;
		try{
			sql="SELECT ID_CLUB, Nombre FROM club WHERE  Estado=TRUE;";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			while(rdoSql.next()){
				clubs.add(rdoSql.getInt("ID_CLUB")+" - "+rdoSql.getString("Nombre"));
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return clubs;
	}
	
	public LinkedList<String> obtenerClub(){
		LinkedList<String> listaClubs = new LinkedList<String>();
		String actual = "";
		//listaClubs.add(actual);
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from club where estado=true");
			while(rdo.next()){
				actual= rdo.getString("ID_CLUB");
				actual= actual + " - " + rdo.getString("Nombre");
				listaClubs.add(actual);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaClubs;
		
	}
	public String obtenerIdUsuarioYNombre(String elIDUsuario) {
		String completo = "";
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select Nombre from club where ID_CLUB='"+elIDUsuario+"';");
			rdo.next();
			completo= elIDUsuario + " - " + rdo.getString("Nombre");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return completo;
	}
	
	/**
	 * Se elimina el club poniendo el estado a false, siempre que el club no tenga perros ni trineos activos
	 * @param nombreClub
	 * @throws GenericException
	 */
	public void eliminarClub(int id) throws GenericException{
		try{
			int idClub, numPerros, numTrineos;
			String SQL = "select count(*) as count from utiliza where id_club='"+id+"' and fechaFinUtilizacion is null;";
			ResultSet rdoSQL = GestorBD.getInstance().consulta(SQL);
			rdoSQL.next();
			numPerros = rdoSQL.getInt("count");
			rdoSQL.close();
			if(numPerros==0){
				SQL = "select count(*) as count from trineo where id_club='"+id+"' and estado=true;";
				rdoSQL = GestorBD.getInstance().consulta(SQL);
				rdoSQL.next();
				numTrineos = rdoSQL.getInt("count");
				if(numTrineos==0){
					SQL = "update club set estado=false where id_Club='"+id+"';";
					GestorBD.getInstance().actualizar(SQL);
				}
				else{
					throw new HayTrineosException();
				}
			}else{
				throw new HayPerrosException();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Se elimina el perro del club añadiendole la fecha de fin de utilizacion al dia actual de lanzamiento.
	 * @param object
	 * @param object2
	 * @throws GenericException
	 */
	public void eliminarPerroDeClub(int numPerro, int idClub) throws GenericException{

			try{
				Calendar cal= Calendar.getInstance();
				String hoy = ""+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
				String SQL = "update utiliza set fechafinutilizacion='"+hoy+"' where id_club='"+idClub+"' and id_Perro='"+numPerro+"';";
				GestorBD.getInstance().actualizar(SQL);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	/**
	 * Devuelve una lista con los datos de los clubes.
	 * @return
	 */
	public LinkedList<Club> obtenerClubs(){
		LinkedList<Club> rdo = new LinkedList<Club>();
		String sql = "select id_Club, nombre, estado from club where estado=TRUE;";
		String nombre;
		int idClub;
		boolean estado;
		Club unClub;
		try{
			ResultSet sqlRdo = GestorBD.getInstance().consulta(sql);
			while(sqlRdo.next()){
				nombre= sqlRdo.getString("nombre");
				idClub= sqlRdo.getInt("id_club");
				estado= sqlRdo.getBoolean("estado");
				unClub = new Club(idClub, nombre, estado);
				rdo.add(unClub);
			}
			sqlRdo.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	public LinkedList<String> obtenerPerrosDeClub(int nombreClub) {
		LinkedList<String> rdo = new LinkedList<String>();
		try{
			String sql = "select id_Perro, nombre from utiliza natural join perro where id_Club ='"+nombreClub+"' and fechaFinUtilizacion is null;";
			ResultSet sqlRdo = GestorBD.getInstance().consulta(sql);
			String perro;
			while(sqlRdo.next()){
				perro = sqlRdo.getString("id_Perro");
				perro= perro+" - "+sqlRdo.getString("nombre");
				rdo.add(perro);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	//modificarDatosClub(elIdClub,nombreModif,patrocinadorModif)
		public void modificarDatosClub(int elIdClub, String nombreModif,String patrocinadorModif){
			
			try {
				GestorBD.getInstance().actualizar("UPDATE club SET Nombre='"+nombreModif+"',Patrocinador='"+patrocinadorModif+"' WHERE ID_Club='"+elIdClub+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//obtenerDatosClub(elIdClub)
	public LinkedList<String> obtenerDatosClub(int elIdClub){
		
		LinkedList<String> datos = new LinkedList<String>();
		String idClub = null;
		String nombre = null;
		String patrocinador = null;
		
		ResultSet resultado;
		try {
			resultado = GestorBD.getInstance().consulta("SELECT ID_Club,Nombre,Patrocinador FROM club WHERE ID_Club="+elIdClub);
		if(resultado.next()==true){
			idClub = resultado.getInt("ID_Club")+"";
			nombre = resultado.getString("Nombre");
			patrocinador = resultado.getString("Patrocinador");
		}
		GestorBD.getInstance().cerrarConsulta(resultado);
		
		datos.add(idClub);
		datos.add(nombre);
		datos.add(patrocinador);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
				
	}

		
	
	
}
