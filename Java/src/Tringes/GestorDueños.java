package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;

import excepciones.GenericException;
import excepciones.HayPerrosException;
import excepciones.dueñoExistenteException;

public class GestorDueños {

	private static GestorDueños miGestorDueños;
	
	private GestorDueños(){}
	
	public static GestorDueños getGestorDueños(){
		if(miGestorDueños==null){
			miGestorDueños= new GestorDueños();
		}
		return miGestorDueños;
	}
	
	//public LinkedList<String> obtenerDueño(int pNumFederado)
	public void añadirDueño(int pNumFederado, String pNombre, String pApellido, String pContraseña)throws GenericException{
		String sql;
		try{
			
			sql="SELECT count(*) AS hay FROM usuario WHERE  NombreUsuario="+pNumFederado+";";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			rdoSql.next();
			if (rdoSql.getInt("hay")!=0){
				sql="SELECT Estado FROM dueño WHERE  NumFederado="+pNumFederado+";";
				ResultSet rdoSql2= GestorBD.getInstance().consulta(sql);
				if(rdoSql2.next()){
					boolean rdo = rdoSql2.getBoolean("Estado");
					if(!rdo){
						activarEstado(pNumFederado);
					}else{
						throw new dueñoExistenteException();
					}
				}else{
					insertarDueño(pNumFederado, pNombre, pApellido, pContraseña);
				}
				rdoSql2.close();
			}else{
				insertarDueño(pNumFederado, pNombre, pApellido, pContraseña);
			}
			rdoSql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	//cambia el estado de 0 a 1
	public void activarEstado(int pNumFederado){
		String sql;
		try{
			sql="UPDATE dueño SET Estado=TRUE WHERE  NumFederado="+pNumFederado+";";	
			GestorBD.getInstance().actualizar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
			
	}
	// inserto el dueño q no existe
	public void insertarDueño(int pNumFederado, String pNombre, String pApellido, String pContraseña){
		String sql;
		try{
			sql="INSERT INTO usuario VALUES ("+pNumFederado+", '"+pContraseña+"', 'D');";	
			GestorBD.getInstance().insertar(sql);
			sql="INSERT INTO dueño VALUES ("+pNumFederado+", '"+pNombre+"', '"+pApellido+"', TRUE);";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	//Es para cargar todos los nombres de los que NO sean el dueño, es para vender el perro.
	public LinkedList<String> cargarDueño(String elIDDueño){
		LinkedList<String> listaNombresNoDueño=new LinkedList<String>();
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from dueño;");
			while(rdo.next()){
				if(!rdo.getString("NumFederado").equals(elIDDueño))
				listaNombresNoDueño.add(rdo.getString("NumFederado")+" - "+rdo.getString("Nombre"));
				
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaNombresNoDueño;
	}

	/**
	 * Se elimina el dueño, poniendo el estado a false
	 * @param numFederado
	 * @throws GenericException
	 */
	public void eliminarDueño(int numFederado) throws GenericException{
		String sql;
		try{
			sql="select count(*) as count from perroPertenece where numFederado="+numFederado+" and fechaFinDueño is null;";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			rdoSql.next();
			int rdo = rdoSql.getInt("count");
			rdoSql.close();
			if(rdo==0){
				//Calendar cal= Calendar.getInstance();
				//String Hoy = ""+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
				sql="update dueño set estado=false where numFederado="+numFederado+";";
				GestorBD.getInstance().actualizar(sql);
			}else{
				throw new HayPerrosException();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public String obtenerNombreDueño(int numFederado){
		String rdo="";
		try{
			String SQL = "select nombre from dueño where numFederado='"+numFederado+"';";
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
	/**
	 * @return
	 */
	public LinkedList<String> obtenerDueños() {
		LinkedList<String> rdo = new LinkedList<String>();
		try{
			String sql = "select numFederado, nombre from dueño where Estado=1;";
			ResultSet sqlRdo = GestorBD.getInstance().consulta(sql);
			String dueño;
			int num;
			while(sqlRdo.next()){
				num= sqlRdo.getInt("numFederado");
				dueño= sqlRdo.getString("nombre");
				rdo.add(num+" - "+dueño);
			}
			sqlRdo.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	
	//modificarDatosDueño(elNumFederado,nombreModif,apellidoModif)
	public void modificarDatosDueño(int elNumFederado, String nombreModif, String apellidoModif){
		
		try {
			GestorBD.getInstance().actualizar("UPDATE dueño SET Nombre='"+nombreModif+"',Apellido='"+apellidoModif+"' WHERE NumFederado="+elNumFederado+";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//obtenerDatosDueño(elNumFederado)
	public LinkedList<String> obtenerDatosDueño(int elNumFederado){
		
		LinkedList<String> datos = new LinkedList<String>();
		String numFederado = null;
		String nombre = null;
		String apellido = null;
		
		ResultSet resultado;
		try {
			resultado = GestorBD.getInstance().consulta("SELECT NumFederado,Nombre,Apellido FROM dueño WHERE NumFederado="+elNumFederado+";");
		if(resultado.next()==true){
			numFederado = resultado.getInt("NumFederado")+"";
			nombre = resultado.getString("Nombre");
			apellido = resultado.getString("Apellido");
		}
		GestorBD.getInstance().cerrarConsulta(resultado);
		
		datos.add(numFederado);
		datos.add(nombre);
		datos.add(apellido);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
				
	}

		
	
}
