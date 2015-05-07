package PruebasHPaz;

import java.sql.ResultSet;
import java.util.Calendar;

import excepciones.GenericException;

import Tringes.Club;
import Tringes.GestorBD;
import Tringes.GestorCampeonatos;
import Tringes.GestorCarreras;
import Tringes.GestorClubs;
import Tringes.GestorPerros;

import junit.framework.TestCase;

public class PruebasEliminarHPaz extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		//GestorBD.Borrar("DELETE FROM tabla WHERE condicion");
		GestorBD.getInstance().borrar("DELETE FROM utiliza WHERE ID_Club=999 AND ID_Perro=555");
		GestorBD.getInstance().borrar("DELETE FROM utiliza WHERE ID_Club=999 AND ID_Perro=556");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=666");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=667");
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_CLUB=998");
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_CLUB=999");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=555");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=556");
		GestorBD.getInstance().borrar("DELETE FROM due�o WHERE NumFederado=888");
		GestorBD.getInstance().borrar("DELETE FROM due�o WHERE NumFederado=889");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCampeonato='2013-03-11' AND FechaCarrera='2013-03-15'");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-03-11'");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-03-10'");
	}

/**------------------------------------------------------------------------
	                   Clase GestorClubs      
---------------------------------------------------------------------------*/	
	//ObtenerClubs();LinkedList<Club>
	public void testObtenerClubs() throws Exception{
		// pruebo que no me devuelve un null
		assertNotNull(GestorClubs.getGestorClubs().obtenerClubs());
		
		// Sino exixten datos en el sistema,mensaje de error de la interfaz ya q se le devuelve una lista vacia
		assertEquals(GestorClubs.getGestorClubs().obtenerClubs().size(), 0);
		
		//si existen datos en el sistema devuelve la lista de clubs activos
		GestorBD.getInstance().insertar("INSERT INTO club (ID_CLUB,Nombre, Patrocinador, Estado) VALUES (998,'Pabli Club', 'Firestone', 1)");
		GestorBD.getInstance().insertar("INSERT INTO club (ID_CLUB,Nombre, Patrocinador, Estado) VALUES (999,'Fani Club', 'Renault', 1)");
		assertFalse(GestorClubs.getGestorClubs().obtenerClubs().size()==0);	
	}
	
	//ObtenerPerrosdelClub(int);LinkedList<String>
	public void testObtenerPerrosdelClub() throws Exception{
		// pruebo que no me devuelve un null
		assertNotNull(GestorClubs.getGestorClubs().ObtenerPerrosdelClub(999));
		
		// entra un Null mensaje de error de la interfaz ya q se le devuelve una lista vacia
		assertEquals(GestorClubs.getGestorClubs().ObtenerPerrosdelClub(null).size ==0);
		
		// No existen datos en el sistema del IDClub Mensaje de error  y devuelve Lista vacia
		assertEquals(GestorClubs.getGestorClubs().ObtenerPerrosdelClub(998).size ==0);
		
		//Existen datos en el sistema de el club, devuelve una Lista con los nombres de los perros que corren para el club
		GestorBD.getInstance().insertar("INSERT INTO perro (ID_Perro,Nombre, Raza, Sexo, Estado) VALUES (555,'Chachito', 'Husky Siberiano', 'Macho', 1)");
		GestorBD.getInstance().insertar("INSERT INTO perro (ID_Perro,Nombre, Raza, Sexo, Estado) VALUES (556,'Gru�on', 'Alaskan Malamute','Macho', 1)");
		GestorBD.getInstance().insertar("INSERT INTO utiliza (ID_Club,ID_Perro, FechaInicioUtilizacion,FechaFinUtilizacion) VALUES (999,555,'2013-03-15',null)");
		GestorBD.getInstance().insertar("INSERT INTO utiliza (ID_Club,ID_Perro, FechaInicioUtilizacion,FechaFinUtilizacion) VALUES (999,556,'2013-03-14',null)");
		assertEquals(GestorClubs.getGestorClubs().ObtenerPerrosdelClub(999).size !=0);	
	}
	
	//EliminarClub(int);  
	public void testEliminarClub() throws Exception{
//		Entrada null devuelve mensaje de error
		try{
			GestorClubs.getGestorClubs().eliminarClub(null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		IdCLub no existe en el sistema,	Mensaje de error
		try{
			GestorClubs.getGestorClubs().eliminarClub(12345);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		IdCLub existe en el sistema de el club,	Deshabilitara el club para que no este disponible en la aplicacion
		GestorClubs.getGestorClubs().eliminarClub(999);
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT estado FROM club WHERE ID_CLUB=999");
		assertEquals(0, rdo.getInt("estado"));
		GestorBD.getInstance().cerrarConsulta(rdo);
	}
		
	//EliminarPerroDeClub(int,int);
	public void testEliminarPerroDeClub() throws Exception{
//		recibe dos null, Mensaje de error
		try{
			GestorClubs.getGestorClubs().eliminarPerroDeClub(null,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		recibe Null,IdCLub		Mensaje de error
		try{
			GestorClubs.getGestorClubs().eliminarPerroDeClub(null,999);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		IdPerro,null		Mensaje de error
		try{
			GestorClubs.getGestorClubs().eliminarPerroDeClub(555,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		IdPerro,IdCLub	No existen datos en el sistema Mensaje de error
		try{
			GestorClubs.getGestorClubs().eliminarPerroDeClub(554,997);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		IDPerro,IdCLub	Existen datos en el sistema de el club y el perro utilizado	Deshabilitara el uso del perro por el club
		
		GestorClubs.getGestorClubs().eliminarPerroDeClub(556,999);
		
		Calendar cal= Calendar.getInstance();
		String Hoy = ""+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT FechaFinUtilizacion FROM utiliza WHERE ID_CLUB=999 AND ID_Perro=556");
		rdo.next();
		assertEquals(Hoy, rdo.getInt("FechaFinUtilizacion"));
		GestorBD.getInstance().cerrarConsulta(rdo);
	}

/**------------------------------------------------------------------------
    	    			Clase Club
---------------------------------------------------------------------------*/	
	//Club(Int,String,boolean); 
	public void testClub() throws Exception{
//		Caso		Resultado
//		Null,null,null		Mensaje de error
		try{
			Club c2= new Club(null,null,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		IDClub,Null,null		Mensaje de error
		try{
			Club c3= new Club(996,null,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		Null,Nombre,null		Mensaje de error
		try{
			Club c4= new Club(null,"Babilon Club",null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		Null,null,Estado   	Mensaje de error
		try{
			Club c5= new Club(null,null,true);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		IDClub,Nombre,null		Mensaje de error
		try{
			Club c6= new Club(996,"Babilon Club",null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		IDClub,Null,Estado		Mensaje de error
		try{
			Club c7= new Club(996,null,true);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		Null,Nombre,Estado		Mensaje de error
		try{
			Club c8= new Club(null,"Babilon Club",true);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		IDClub,Nombre,Estado		Se creara una instancia de club Con los datos correctamente insertados
		//doy por supuesto que la constructora devuelve el objeto q ha creado
		Club c1= new Club(996,"Babilon Club",true);
		assertNotNull(c1);
		assertEquals(c1.GetID(),996);
		assertEquals(c1.GetNombre(),"Babilon Club");
		assertEquals(c1.GetEstado(),true);

	}
/**------------------------------------------------------------------------
	Clase GestorTrineos
---------------------------------------------------------------------------*/
	//ObtenerTrineosDeClub(String);LinkedList<String>
	// DECIRLE A JON Q TIENE Q SER INT LO Q RECIBA
	public void testObtenerTrineosDeClub() throws Exception{
		
		//pruebo que no sea null el resultado
		assertNotNull(GestorTrineos.getGestorTrineos().ObtenerTrineosDeClub(123456));

//		recibe Null, devuelve Lista vacia
		assertEquals(GestorTrineos.getGestorTrineos().ObtenerTrineosDeClub(null).size ==0);
		
//		IdTrineo	No existen datos en el sistema, devuelve Lista vacia
		assertEquals(GestorTrineos.getGestorTrineos().ObtenerTrineosDeClub(665).size==0);
		
//		IdTrineo	Existen datos en el sistema de el club	Lista con los nombres de los perros que corren para el club
		GestorBD.getInstance().insertar("INSERT INTO FROM trineo (ID_Trineo, ID_Club, Fabricante, FechaFabricacion, Estado) VALUES (666,998, 'TrineoWay', '2013-03-16', 1)");
		GestorBD.getInstance().insertar("INSERT INTO FROM trineo (ID_Trineo, ID_Club, Fabricante, FechaFabricacion, Estado) VALUES (667,998, 'TrineoFIFI', '2013-03-17',1)");
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT count(*) AS Cant FROM Trineo WHERE ID_CLUB=998");
		rdo.next();
		assertEquals(GestorTrineos.getGestorTrineos().ObtenerTrineosDeClub(998).size, rdo.getInt("Cant"));
		GestorBD.getInstance().cerrarConsulta(rdo);
	}
	
	//EliminarTrineoDeClub(String,String);
	// DECIRLE A JON Q TIENE Q SER INT LO Q RECIBE
	public void testEliminarTrineoDeClub() throws Exception{

//		Null,null  Mensaje de error
		try{
			GestorTrineos.getGestorTrineos().EliminarTrineoDeClub(null,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		Null,IdCLub		Mensaje de error
		try{
			GestorTrineos.getGestorTrineos().EliminarTrineoDeClub(null,998);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		IdTrineo,null		Mensaje de error
		try{
			GestorTrineos.getGestorTrineos().EliminarTrineoDeClub(665,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		IdTrineo,IdCLub	No existen datos en el sistema    Mensaje de error
		try{
			GestorTrineos.getGestorTrineos().EliminarTrineoDeClub(995,665);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}

		
//		IDTrineo,IdCLub	Existen datos en el sistema de el club y el trineo utilizado	Deshabilitara el uso del trineo por el club
		GestorTrineos.getGestorTrineos().EliminarTrineoDeClub(998,666);
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT Estado FROM Trineo WHERE ID_CLUB=998 AND ID_Trineo=666");
		rdo.next();
		assertEquals(0, rdo.getInt("Estado"));
		GestorBD.getInstance().cerrarConsulta(rdo);
		
	}
/**------------------------------------------------------------------------
	Clase GestorDue�os
---------------------------------------------------------------------------*/
	//ObtenerDue�os();LinkedList<Due�o>
	public void testObtenerDue�os() throws Exception{
		//pruebo que no sea null el resultado
		assertNotNull(GestorDue�os.getGestorDue�os().ObtenerDue�os());

//		Nada	No existen datos en el sistema Mensaje de error  y devuelve Lista vacia
		assertEquals(GestorDue�os.getGestorDue�os().ObtenerDue�os().size==0);
		
//		Nada	Existen datos en el sistema de los due�os	Lista con los due�os que est�n activos
		GestorBD.getInstance().insertar("INSERT INTO  FROM due�o(NumFederado, Nombre, Apellido, Estado) VALUES(888,'Alberto','Galindez', 1) ");
		GestorBD.getInstance().insertar("INSERT INTO  FROM due�o(NumFederado, Nombre, Apellido, Estado) VALUES(889,'Maria','Smith', 1) ");
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT count(*) AS Cant FROM due�o");
		rdo.next();
		assertEquals(GestorDue�os.getGestorDue�os().ObtenerDue�os().size, rdo.getInt("Cant"));
		GestorBD.getInstance().cerrarConsulta(rdo);
		
	}
	
	//ObtenerNombreDeDue�o(int);String 
	public void testObtenerNombreDeDue�o() throws Exception{

//		Recibe Null	 y devuelve String vacio
		assertEquals(GestorDue�os.getGestorDue�os().ObtenerNombreDeDue�o(null)=="");
		
//		NumFederado	No existen datos en el sistema y devuelve String vacio
		assertEquals(GestorDue�os.getGestorDue�os().ObtenerNombreDeDue�o(887),"");
		
//		NumFederado	Existen datos en el sistema del due�o	Nombre del due�o 
		assertEquals(GestorDue�os.getGestorDue�os().ObtenerNombreDeDue�o(889),"Maria");

	}

	//EliminarDue�o(int);
	public void testEliminarDue�o() throws Exception{

//		recibe Null		Mensaje de error
		try{
			GestorDue�os.getGestorDue�os().EliminarDue�o(null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		NumFederado,	No existen datos en el sistema,	Mensaje de error
		try{
			GestorDue�os.getGestorDue�os().EliminarDue�o(885);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
//		NumFederado,	Existen datos en el sistema del due�o,	Deshabilitara el due�o en el sistema
		GestorDue�os.getGestorDue�os().EliminarDue�o(888);
		
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT Estado FROM Due�o WHERE NumFederado=888");
		rdo.next();
		assertEquals(0, rdo.getInt("Estado"));
		GestorBD.getInstance().cerrarConsulta(rdo);

	}
/**------------------------------------------------------------------------
	Clase Due�o
---------------------------------------------------------------------------*/
	//Due�o(Int,String,boolean);
	public void testDue�o() throws Exception{

//		Null,null,null		Mensaje de error
		try{
			Due�o d2= new Due�o(null,null,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		NumFederado,Null,null		Mensaje de error
		try{
			Due�o d3= new Due�o(888,null,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		Null,Nombre,null		Mensaje de error
		try{
			Due�o d4= new Due�o(null,"Alberto",true);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		Null,null,Estado		Mensaje de error
		try{
			Due�o d5= new Due�o(null,null,true);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		NumFederado,Nombre,null		Mensaje de error
		try{
			Due�o d6= new Due�o(888,"Alberto",null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		NumFederado,Null,Estado		Mensaje de error
		try{
			Due�o d7= new Due�o(888,null,true);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		Null,Nombre,Estado		Mensaje de error
		try{
			Due�o d8= new Due�o(null,"Alberto",true);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		NumFederado,Nombre,Estado		Se creara una instancia de due�o Con los datos correctamente insertados
		//Tomando en cuenta que la constructora devuelve el onjeto creado
		Due�o d1= new Due�o(888,"Alberto",true);
		assertEquals(d1.getNumFederado(), 888);
		assertEquals(d1.getNombre(), "Alberto");
		assertEquals(d1.getEstado(), true);
	
	}
/**------------------------------------------------------------------------
	Clase GestorPerros
---------------------------------------------------------------------------*/
	//ObtenerDue�oDePerro(int);String
	public void testObtenerDue�oDePerro() throws Exception{

//		Null		Mensaje de error  y devuelve String vacio
		assertEquals(GestorPerros.getGestorPerros().ObtenerDue�oDePerro(null)=="");
		
//		IDPerro	No existen datos en el sistema, Mensaje de error  y devuelve String vacio
		assertEquals(GestorPerros.getGestorPerros().ObtenerDue�oDePerro(333)=="");
		
//		IDPerro	Existen datos en el sistema del due�o	Nombre del due�o
		GestorBD.getInstance().insertar("INSERT INTO FROM perropertenece (FechaComienzoDue�o, NumFederado, ID_Perro, FechaFinDue�o) VALUES('2013-03-10',889,555,null)");
		GestorBD.getInstance().insertar("INSERT INTO FROM perropertenece (FechaComienzoDue�o, NumFederado, ID_Perro, FechaFinDue�o) VALUES('2013-03-11',889,556,null)");
		assertEquals(GestorPerros.getGestorPerros().ObtenerDue�oDePerro(555),"Maria");

	}
	//ObtenerNombreDePerro(int);String
	public void testObtenerNombreDePerro() throws Exception{

//		Null		Mensaje de error  y devuelve String vacio
		assertEquals(GestorPerros.getGestorPerros().ObtenerNombreDePerro(null)=="");
		
//		IDPerro	No existen datos en el sistema, Mensaje de error  y devuelve String vacio
		assertEquals(GestorPerros.getGestorPerros().ObtenerNombreDePerro(554)=="");
		
//		IDPerro	Existen datos en el sistema del due�o	Nombre del perro
		assertEquals(GestorPerros.getGestorPerros().ObtenerNombreDePerro(555),"Chachito");

	}
	//EliminarPerro(int);
	public void testEliminarPerro() throws Exception{

//		Null		Mensaje de error
		try{
			GestorPerros.getGestorPerros().eliminarPerro(null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		IDPerro	No existen datos en el sistema	Mensaje de error
		try{
			GestorPerros.getGestorPerros().eliminarPerro(554);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		IDPerro	Existen datos en el sistema del due�o	Deshabilitara el perro en el sistema
		GestorPerros.getGestorPerros().eliminarPerro(555);
		
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT Estado FROM perro WHERE ID_Perro=555");
		rdo.next();
		assertEquals(0, rdo.getInt("Estado"));
		GestorBD.getInstance().cerrarConsulta(rdo);

	}
/**------------------------------------------------------------------------
	Clase GestorCampeonato
---------------------------------------------------------------------------*/
	//EliminarCampeonato(Date);
	public void testEliminarCampeonato() throws Exception{

//		Null		Mensaje de error
		try{
			GestorCampeonatos.getGestorCampeonatos().eliminarCampeonato(null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		FechaCampeonato	No existen datos en el sistema	Mensaje de error
		try{
			GestorCampeonatos.getGestorCampeonatos().eliminarCampeonato("2013-01-12");
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		FechaCampeonato	Existen datos en el sistema del due�o Si no tiene carreras	Deshabilitara el campeonato en el sistema
		GestorBD.getInstance().insertar("INSERT INTO FROM campeonato(FechaCampeonato, Nombre, Estado) VALUES('2013-03-10','El hielo frio',1)");
		
		GestorCampeonatos.getGestorCampeonatos().eliminarCampeonato("2013-03-10");
		
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT Estado FROM campeonato WHERE FechaCampeonato='2013-03-10'");
		rdo.next();
		assertEquals(0, rdo.getInt("Estado"));
		GestorBD.getInstance().cerrarConsulta(rdo);
	}
/**------------------------------------------------------------------------
	Clase GestorCarrera
---------------------------------------------------------------------------*/
	//EliminarCarrera(Date,date);
	public void testEliminarCarrera() throws Exception{

//		Null,null		Mensaje de error
		try{
			GestorCarreras.getGestorCarreras().eliminarCarrera(null,null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		FechaCarrera,null	No existen datos en el sistema	Mensaje de error
		try{
			GestorCarreras.getGestorCarreras().eliminarCarrera("2013-03-03",null);
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		null,FechaCampeonato	No existen datos en el sistema	Mensaje de error
		try{
			GestorCarreras.getGestorCarreras().eliminarCarrera(null,"2013-03-12");
			fail("Funcionamiento incorrecto.");
		}catch(GenericException e){
			System.out.println("Funcionamiento correcto.");
		}
		
//		FechaCarrera,FechaCampeonato	No existen datos en el sistema	Mensaje de error
		
		GestorBD.getInstance().insertar("INSERT INTO FROM campeonato(FechaCampeonato, Nombre, Estado) VALUES('2013-03-11','Heladeria',1)");
		GestorBD.getInstance().insertar("INSERT INTO FROM carrera(FechaCarrera, FechaCampeonato, Nombre,Lugar, Recorrido, Estado, ID_Modalidad) VALUES('2013-03-15', '2013-03-11','La Carrera XX','Alaska', 'Un recorrido X', 1, 777)");
		
		GestorCarreras.getGestorCarreras().eliminarCarrera("2013-03-15"," 2013-03-11");
		
		ResultSet rdo= GestorBD.getInstance().consulta("SELECT Estado FROM carrera WHERE FechaCampeonato='2013-03-11' AND FechaCarrera='2013-03-15'");
		rdo.next();
		assertEquals(0, rdo.getInt("Estado"));
		GestorBD.getInstance().cerrarConsulta(rdo);
	
	}
	
}
