package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import excepciones.GenericException;
import excepciones.trineoExistenteException;

public class GestorTrineos {

	private static GestorTrineos miGestorTrineos;
	
	private GestorTrineos(){}
	
	public static GestorTrineos getGestorTrineos(){
		if(miGestorTrineos==null){
			miGestorTrineos= new GestorTrineos();
		}
		return miGestorTrineos;
	}
	
	//public LinkedList<String> obtenerTrineo(int pID_Trineo)
	public void añadirTrineo(int pID_Trineo, int pID_Club, String pFabricante, String pFechaFabricacion)throws GenericException{
		String sql;
		try{
			sql="SELECT Estado FROM trineo WHERE  ID_Trineo="+pID_Trineo+";";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			if(rdoSql.next()){
				int rdo = rdoSql.getInt("Estado");
				if(rdo==0){
					activarEstado(pID_Trineo);
				}else{
					throw new trineoExistenteException();
				}
			}else{
				insertarTrineo(pID_Trineo, pFabricante, pFechaFabricacion, pID_Club);
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	//Cambio estado de 0 a 1
	public void activarEstado(int pID_Trineo){
		String sql;
		try{
			sql="UPDATE trineo SET Estado=TRUE WHERE  ID_Trineo="+pID_Trineo+";";	
			GestorBD.getInstance().actualizar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}			
	}
	//inserto un trineo q ya se q no existe
	public void insertarTrineo(int pID_Trineo, String pFabricante, String pFechaFabricacion, int pID_Club){
		String sql;
		try{
			sql="INSERT INTO trineo VALUES ("+pID_Trineo+", "+pID_Club+", '"+pFabricante+"', '"+pFechaFabricacion+"', TRUE);";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Elimina el trineo, poniendo el estado a false. -No tiene encuenta el nombreClub
	 * @param idTrineo
	 * @param nombreClub
	 * @throws GenericException
	 */
	public void eliminarTrineoDeClub(int idTrineo){
			String sql;
			try{
				sql="update trineo set estado=FALSE where id_Trineo='"+idTrineo+"';";
				GestorBD.getInstance().actualizar(sql);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	/**
	 * Devuelve la lista de ids de los trineos que pertenecen a ese club
	 * @param idClub
	 * @return
	 */
	public LinkedList<Integer> obtenerTrineosDeClub(int nomClub){
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		String sql = "select * from trineo where id_Club='"+nomClub+"' and estado=true;";
		int unTrineo;
		try{
			ResultSet sqlRdo = GestorBD.getInstance().consulta(sql);
			while(sqlRdo.next()){
				unTrineo = sqlRdo.getInt("id_trineo");
				rdo.add(new Integer(unTrineo));
			}
			sqlRdo.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	/**
	 * Devuelve la lista de perros que pertenecen a ese club
	 */
	public LinkedList<String> obtenerPerrosDeClub(int idClub){
		LinkedList<String> rdo = new LinkedList<String>();
		String unPerro, nom;
		try{
			String SQL = "select id_Perro, nombre from utiliza where id_Club='"+idClub+"' and where fechaFinUtilizacion is null";
			ResultSet rdoSQL = GestorBD.getInstance().consulta(SQL);
			while(rdoSQL.next()){
				unPerro = rdoSQL.getString("id_Perro");
				nom = rdoSQL.getString("nombre");
				rdo.add(unPerro+" - "+nom);
			}
			rdoSQL.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	
	//obtenerTrineosDeLaCarrera(laFechaCarrera,laFechaCampeonato)
	public LinkedList<String> obtenerTrineosDeLaCarrera(String laFechaCarrera, String laFechaCampeonato){

		LinkedList<String> datos = new LinkedList<String>();
		String idTrineo = null;
		
		ResultSet resultado; 
		try {
			resultado = GestorBD.getInstance().consulta("SELECT ID_Trineo FROM recorridoCarrera WHERE FechaCampeonato='"+laFechaCampeonato+"' AND FechaCarrera='"+laFechaCarrera+"';");
			while(resultado.next()){
				idTrineo = resultado.getInt("ID_Trineo")+"";
				datos.add(idTrineo);
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
		
	}
		
	//modificarTrineo(elIdTrineo,idClubModif,fabricanteModif,fechaFabricacionModif)
	public void modificarTrineo(int elIdTrineo, int idClubModif, String fabricanteModif, String fechaFabricacionModif){
		
		try {
			GestorBD.getInstance().actualizar("UPDATE trineo SET ID_Club='"+idClubModif+"', Fabricante='"+fabricanteModif+"', FechaFabricacion='"+fechaFabricacionModif+"' WHERE ID_Trineo="+elIdTrineo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//obtenerDatosTrineo(elIdTrineo)
	public LinkedList<String> obtenerDatosTrineo(int elIdTrineo){
		
		LinkedList<String> datos = new LinkedList<String>();
		String idTrineo = null;
		String idClub = null;
		String fabricante = null;
		String fechaFabricacion = null;
		
		ResultSet resultado;
		try {
			resultado = GestorBD.getInstance().consulta("SELECT ID_Trineo,ID_Club,Fabricante,FechaFabricacion FROM trineo WHERE ID_Trineo="+elIdTrineo);
		if(resultado.next()==true){
			idTrineo = resultado.getInt("ID_Trineo")+"";
			idClub = resultado.getInt("ID_Club")+"";
			fabricante = resultado.getString("Fabricante");
			fechaFabricacion = resultado.getDate("FechaFabricacion").toString();
		}
		GestorBD.getInstance().cerrarConsulta(resultado);
		
		datos.add(idTrineo);
		datos.add(idClub);
		datos.add(fabricante);
		datos.add(fechaFabricacion);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
				
	}
	
	
}
