package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import excepciones.*;

public class GestorPerros {
	
	private static GestorPerros miGestorPerros;
	
	private GestorPerros(){}
	
	public static GestorPerros getGestorPerros(){
		if(miGestorPerros == null){
			miGestorPerros = new GestorPerros();
		}
		return miGestorPerros;
	}
	
	public void a�adirPerroDue�o(int pNumFederado, int pID_Perro, String pNombre, String pRaza, String pSexo)throws GenericException{
		String sql;
		try{
			sql="SELECT Estado FROM perro WHERE  ID_Perro="+pID_Perro+" AND Nombre='"+pNombre+"';";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			if(rdoSql.next()){
				int rdo = rdoSql.getInt("Estado");
				if(rdo==0){
					activarEstado(pID_Perro);
				}else{
					throw new perroExistenteException();
				}
			}else{
				insertarPerro(pNumFederado, pID_Perro, pNombre, pRaza, pSexo);
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	//Cambio estado de 0 a 1
	public void activarEstado(int pID_Perro){
		String sql;
		try{
			sql="UPDATE perro SET Estado=TRUE WHERE  ID_Perro="+pID_Perro+";";	
			GestorBD.getInstance().actualizar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}

	public void insertarPerro(int pNumFederado, int pID_Perro, String pNombre, String pRaza, String pSexo){
		String sql;
		try{
			sql="INSERT INTO perro VALUES ("+pID_Perro+", '"+pNombre+"', '"+pRaza+"', '"+pSexo+"', TRUE);";	
			GestorBD.getInstance().insertar(sql);
			Calendar cal= Calendar.getInstance();
			String Hoy = ""+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
			sql="INSERT INTO perropertenece VALUES ('"+Hoy+"', "+pNumFederado+", "+pID_Perro+", null);";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void obtenerDatosUtiliza(int pID_Club, int pID_Perro)throws GenericException{

		String sql;
		try{
			sql="SELECT count(*) AS num FROM utiliza WHERE ID_Club="+pID_Club+" AND ID_Perro="+pID_Perro+";";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			if(!rdoSql.next()){
				insertarUtiliza(pID_Club, pID_Perro);
			}else{
				throw new perroExistenteException();
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}


	public void insertarUtiliza(int pID_Club, int pID_Perro){
		String sql;
		try{
			Calendar cal= Calendar.getInstance();
			String Hoy = ""+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
			sql="INSERT INTO utiliza VALUES ("+pID_Club+", "+pID_Perro+", '"+Hoy+"', null);";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void a�adirCamada(int pID_Perro, String pFecha, int pNumCrias)throws GenericException{
		String sql;
		try{
			sql="SELECT count(*) AS num FROM apareamiento WHERE ID_Perro="+pID_Perro+" AND Fecha='"+pFecha+"';";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			rdoSql.next();
			if(rdoSql.getInt("num")==0){
				insertarApareamiento(pID_Perro, pFecha, pNumCrias);
			}else{
				throw new apareamientoExistenteException();
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void insertarApareamiento(int pID_Perro, String pFecha, int pNumCrias){
		String sql;
		try{
			sql="INSERT INTO apareamiento VALUES ('"+pFecha+"', "+pID_Perro+", "+pNumCrias+");";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public LinkedList<String> cargarPerros(String elIDClub){
		LinkedList<String> listaPerros=new LinkedList<String>();
		String perro;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from utiliza where FechaFinUtilizacion is null AND ID_Club='"+elIDClub+"';");
			while(rdo.next()){
				perro=rdo.getString("ID_Perro");
				ResultSet rdo2 = GestorBD.getInstance().consulta("Select Nombre from perro where ID_Perro='"+perro+"'"); 
				rdo2.next();
				perro = perro +" - "+ rdo2.getString("Nombre");
				listaPerros.add(perro);
				rdo2.close();
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPerros;
	}
	
	public LinkedList<String> obtenerPerrosQueCorrenEnTrineo(String trineo,String campeonato,String carrera){
		LinkedList<String> listaPerros=new LinkedList<String>();
		String perro;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from llevara where ID_Trineo='"+trineo+"' AND FechaCarrera='"+carrera+"' AND fechaCampeonato='"+campeonato+"';");
			while(rdo.next()){
				perro=rdo.getString("ID_Perro");
				ResultSet rdo2 = GestorBD.getInstance().consulta("Select Nombre from perro where ID_Perro='"+perro+"'"); 
				rdo2.next();
				perro = perro +" - "+ rdo2.getString("Nombre");
				listaPerros.add(perro);
				rdo2.close();
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPerros;
	}
	public LinkedList<String> obtenerPerrosQueNoCorrenEnElTrineo(String idClub,String trineo,String campeonato,String carrera){
		LinkedList<String> listaPerros=new LinkedList<String>();
		String perro;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from llevara where FechaCarrera='"+carrera+"' AND fechaCampeonato='"+campeonato+"' and ID_Trineo in (select id_Trineo from utiliza where ID_Club='"+ idClub +"' and ID_Trineo!='"+trineo+"' and fechafinUtilizacion is not null);");
			while(rdo.next()){
				perro=rdo.getString("ID_Perro");
				ResultSet rdo2 = GestorBD.getInstance().consulta("Select Nombre from perro where ID_Perro='"+perro+"'"); 
				rdo2.next();
				perro = perro +" - "+ rdo2.getString("Nombre");
				listaPerros.add(perro);
				rdo2.close();
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPerros;
	}
	public LinkedList<String> obtenerPerrosQueCorren(String campeonato, String carrera) {
		LinkedList<String> listaPerros=new LinkedList<String>();
			String perro;
			try {
				ResultSet rdo = GestorBD.getInstance().consulta("Select * from llevara where FechaCarrera='"+carrera+"' AND fechaCampeonato='"+campeonato+"';");
				while(rdo.next()){
					perro=rdo.getString("ID_Perro");
					ResultSet rdo2 = GestorBD.getInstance().consulta("Select Nombre from perro where ID_Perro='"+perro+"'"); 
					rdo2.next();
					perro = perro +" - "+ rdo2.getString("Nombre");
					listaPerros.add(perro);
					rdo2.close();
				}
				rdo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listaPerros;
		}
	
	public LinkedList<String> cargarPerrosDue�o(String elIDDue�o){
		LinkedList<String> listaPerros=new LinkedList<String>();
		String perro;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from perropertenece where numFederado='"+elIDDue�o+"' AND fechaFinDue�o is null");
			while(rdo.next()){
				perro=rdo.getString("ID_Perro");
				ResultSet rdo2 = GestorBD.getInstance().consulta("Select Nombre from perro where ID_Perro='"+perro+"';");
				rdo2.next();
				perro= perro + " - " + rdo2.getString("Nombre");
				listaPerros.add(perro);
				rdo2.close();
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPerros;
	}
	
	public void venderPerro(String elIDPerro,String elNumFederado ,String elNombreDue�o){
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select Count(*) as Cantidad From ventas where ID_Perro='"+elIDPerro+"' AND NumVendedor='"+elNombreDue�o+"'");
			rdo.next();
			if(rdo.getInt("Cantidad")<1){
				String fecha;
				GregorianCalendar calendario = new GregorianCalendar(); 
				int diames=calendario.get(Calendar.DAY_OF_MONTH);
				int mes=calendario.get(Calendar.MONTH)+1;
				int a�o=calendario.get(Calendar.YEAR);
				fecha=a�o+"-"+mes+"-"+diames;
				GestorBD.getInstance().actualizar("Insert Into ventas values('"+elIDPerro+"','"+elNombreDue�o+"','"+elNumFederado+"','"+fecha+"',false)");
			}
			else{
				//Mensaje ya existe una proposicion de compra de este perro
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<String> cargarComprasPendientes(String elIDDue�o){
		LinkedList<String> listaCompras = new LinkedList<String>();
		String perro;
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select ID_Perro from ventas where NumComprador='"+elIDDue�o+"'");
			while(rdo.next()){
				perro=rdo.getString("ID_Perro");
				ResultSet rdo2 = GestorBD.getInstance().consulta("Select Nombre from perro where ID_Perro='"+perro+"';");
				rdo2.next();
				perro= perro + " - " + rdo2.getString("Nombre");
				listaCompras.add(perro);
				rdo2.close();
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCompras;
	}
	
	public void confirmarCompra(String elIDPerro, String elNombreDue�o){
		String fecha;
		GregorianCalendar calendario = new GregorianCalendar(); 
		int diames=calendario.get(Calendar.DAY_OF_MONTH);
		int mes=calendario.get(Calendar.MONTH)+1;
		int a�o=calendario.get(Calendar.YEAR);
		fecha=a�o+"-"+mes+"-"+diames;
		try {
			//Confirmar Compra
			GestorBD.getInstance().actualizar("Update ventas Set confirmado=true where ID_Perro='"+elIDPerro+"' and NumComprador='"+elNombreDue�o+"'");
			//Eliminar due�o perro
			GestorBD.getInstance().actualizar("Update perropertenece Set FechaFinDue�o='"+fecha+"' where ID_Perro='"+elIDPerro+"'");
			//A�adir el Nuevo Due�o de perro
			GestorBD.getInstance().actualizar("Insert Into perropertenece values('"+fecha+"','"+elNombreDue�o+"','"+elIDPerro+"',null)");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Elimina el perro introducido estableciendo el estado a false y cancela la utilizaci�n del 
	 * perro del club correspondiente
	 * @param idPerro
	 */
	public void eliminarPerro(int idPerro){
		String sql;
		Calendar cal= Calendar.getInstance();
		String hoy = ""+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
		try{
			sql="update perro set estado=FALSE where id_Perro='"+idPerro+"';";
			GestorBD.getInstance().actualizar(sql);
			sql = "update utiliza set fechaFinUtilizacion='"+hoy+"' where id_Perro='"+idPerro+"';";
			GestorBD.getInstance().actualizar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public String obtenerDue�oDePerro(int idPerro){
		String rdo= "";
		try{
			String SQL = "select nombre from due�o where numFederado in (select numFederado from perropertenece where id_Perro="+idPerro+" and FechaFinDue�o is null);";
			ResultSet rdoSQL = GestorBD.getInstance().consulta(SQL);
			if(rdoSQL.next()){
				rdo= rdoSQL.getString("nombre");
			}
			rdoSQL.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	public String obtenerNombrePerro(int idPerro){
		String rdo= null;
		try{
			String SQL = "select nombre from perro where id_Perro='"+idPerro+"';";
			ResultSet rdoSQL = GestorBD.getInstance().consulta(SQL);
			if(rdoSQL.next()){
				rdo= rdoSQL.getString("nombre");
			}
			rdoSQL.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	
	public LinkedList<String> cargarPerrosDeDue�o(int numFederado){ 
		
		LinkedList<String> rdo = new LinkedList<String>();
		String nombre, id;
		String sql = "select ID_Perro, nombre from perroPertenece natural join perro where numFederado="+numFederado+" and fechafindue�o is null and estado=true;";
		try{
			ResultSet sqlRdo = GestorBD.getInstance().consulta(sql);
			while(sqlRdo.next()){
				nombre= sqlRdo.getString("nombre");
				id= sqlRdo.getInt("ID_Perro")+"";
				id= id+" - "+nombre;
				rdo.add(id);
			}
			sqlRdo.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	public void modificarDatosPerro(int elIdPerro, String nombreModif, String razaModif, String sexoModif){
		
		try {
			GestorBD.getInstance().actualizar("UPDATE perro SET Nombre='"+nombreModif+"', Raza='"+razaModif+"', Sexo='"+sexoModif+"' WHERE ID_Perro="+elIdPerro+";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<String> obtenerDatosPerro(int elIdPerro){
		
		LinkedList<String> datos = new LinkedList<String>();
		String idPerro = null;
		String nombre = null;
		String raza = null;
		String sexo = null;
		
		ResultSet resultado;
		try {
			resultado = GestorBD.getInstance().consulta("SELECT ID_Perro,Nombre,Raza,Sexo FROM perro WHERE ID_Perro="+elIdPerro+";");
		if(resultado.next()==true){
			idPerro = resultado.getInt("ID_Perro")+"";
			nombre = resultado.getString("Nombre");
			raza = resultado.getString("Raza");
			sexo = resultado.getString("Sexo");
		}
		GestorBD.getInstance().cerrarConsulta(resultado);
		
		datos.add(idPerro);
		datos.add(nombre);
		datos.add(raza);
		datos.add(sexo);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
				
	}

	public LinkedList<String> obtenerPerrosSinUtilizar(){
		LinkedList<String> rdo = new LinkedList<String>();
		String nombre, id;
		String SQL = "select id_Perro, nombre from perro where id_Perro not in (select id_Perro from utiliza where fechafinUtilizacion is null);";
		try{
			ResultSet rdoSQL= GestorBD.getInstance().consulta(SQL);
			while(rdoSQL.next()){
				id= rdoSQL.getString("id_perro");
				nombre = rdoSQL.getString("nombre");
				rdo.add(id+" - "+nombre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
}
