package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;

import excepciones.GenericException;
import excepciones.HayPerrosException;
import excepciones.due�oExistenteException;

public class GestorDue�os {

	private static GestorDue�os miGestorDue�os;
	
	private GestorDue�os(){}
	
	public static GestorDue�os getGestorDue�os(){
		if(miGestorDue�os==null){
			miGestorDue�os= new GestorDue�os();
		}
		return miGestorDue�os;
	}
	
	//public LinkedList<String> obtenerDue�o(int pNumFederado)
	public void a�adirDue�o(int pNumFederado, String pNombre, String pApellido, String pContrase�a)throws GenericException{
		String sql;
		try{
			
			sql="SELECT count(*) AS hay FROM usuario WHERE  NombreUsuario="+pNumFederado+";";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			rdoSql.next();
			if (rdoSql.getInt("hay")!=0){
				sql="SELECT Estado FROM due�o WHERE  NumFederado="+pNumFederado+";";
				ResultSet rdoSql2= GestorBD.getInstance().consulta(sql);
				if(rdoSql2.next()){
					boolean rdo = rdoSql2.getBoolean("Estado");
					if(!rdo){
						activarEstado(pNumFederado);
					}else{
						throw new due�oExistenteException();
					}
				}else{
					insertarDue�o(pNumFederado, pNombre, pApellido, pContrase�a);
				}
				rdoSql2.close();
			}else{
				insertarDue�o(pNumFederado, pNombre, pApellido, pContrase�a);
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
			sql="UPDATE due�o SET Estado=TRUE WHERE  NumFederado="+pNumFederado+";";	
			GestorBD.getInstance().actualizar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
			
	}
	// inserto el due�o q no existe
	public void insertarDue�o(int pNumFederado, String pNombre, String pApellido, String pContrase�a){
		String sql;
		try{
			sql="INSERT INTO usuario VALUES ("+pNumFederado+", '"+pContrase�a+"', 'D');";	
			GestorBD.getInstance().insertar(sql);
			sql="INSERT INTO due�o VALUES ("+pNumFederado+", '"+pNombre+"', '"+pApellido+"', TRUE);";	
			GestorBD.getInstance().insertar(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	//Es para cargar todos los nombres de los que NO sean el due�o, es para vender el perro.
	public LinkedList<String> cargarDue�o(String elIDDue�o){
		LinkedList<String> listaNombresNoDue�o=new LinkedList<String>();
		try {
			ResultSet rdo = GestorBD.getInstance().consulta("Select * from due�o;");
			while(rdo.next()){
				if(!rdo.getString("NumFederado").equals(elIDDue�o))
				listaNombresNoDue�o.add(rdo.getString("NumFederado")+" - "+rdo.getString("Nombre"));
				
			}
			rdo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaNombresNoDue�o;
	}

	/**
	 * Se elimina el due�o, poniendo el estado a false
	 * @param numFederado
	 * @throws GenericException
	 */
	public void eliminarDue�o(int numFederado) throws GenericException{
		String sql;
		try{
			sql="select count(*) as count from perroPertenece where numFederado="+numFederado+" and fechaFinDue�o is null;";
			ResultSet rdoSql= GestorBD.getInstance().consulta(sql);
			rdoSql.next();
			int rdo = rdoSql.getInt("count");
			rdoSql.close();
			if(rdo==0){
				//Calendar cal= Calendar.getInstance();
				//String Hoy = ""+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
				sql="update due�o set estado=false where numFederado="+numFederado+";";
				GestorBD.getInstance().actualizar(sql);
			}else{
				throw new HayPerrosException();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public String obtenerNombreDue�o(int numFederado){
		String rdo="";
		try{
			String SQL = "select nombre from due�o where numFederado='"+numFederado+"';";
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
	public LinkedList<String> obtenerDue�os() {
		LinkedList<String> rdo = new LinkedList<String>();
		try{
			String sql = "select numFederado, nombre from due�o where Estado=1;";
			ResultSet sqlRdo = GestorBD.getInstance().consulta(sql);
			String due�o;
			int num;
			while(sqlRdo.next()){
				num= sqlRdo.getInt("numFederado");
				due�o= sqlRdo.getString("nombre");
				rdo.add(num+" - "+due�o);
			}
			sqlRdo.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rdo;
	}
	
	
	//modificarDatosDue�o(elNumFederado,nombreModif,apellidoModif)
	public void modificarDatosDue�o(int elNumFederado, String nombreModif, String apellidoModif){
		
		try {
			GestorBD.getInstance().actualizar("UPDATE due�o SET Nombre='"+nombreModif+"',Apellido='"+apellidoModif+"' WHERE NumFederado="+elNumFederado+";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//obtenerDatosDue�o(elNumFederado)
	public LinkedList<String> obtenerDatosDue�o(int elNumFederado){
		
		LinkedList<String> datos = new LinkedList<String>();
		String numFederado = null;
		String nombre = null;
		String apellido = null;
		
		ResultSet resultado;
		try {
			resultado = GestorBD.getInstance().consulta("SELECT NumFederado,Nombre,Apellido FROM due�o WHERE NumFederado="+elNumFederado+";");
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
