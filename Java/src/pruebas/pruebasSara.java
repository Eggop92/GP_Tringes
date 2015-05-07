package pruebas;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import junit.framework.AssertionFailedError;

import org.junit.Test;
import org.junit.runner.notification.Failure;

import excepciones.GenericException;

import Tringes.*;

public class pruebasSara {

//ojo!!!!! - modificar que en los metodos donde paso la fecha de la carrera tambien tengo que pasar la fecha del cmapeonato!!!!!!
	
	//¿QUE FORMATO VAN A TENER LAS FECHAS?
	
/*		FECHAS
	Calendar fecha2 = Calendar.getInstance();
	Date fecha = fecha2.getTime();
	
	int dia = fecha2.get(Calendar.DAY_OF_MONTH);
	int mes = fecha2.get(Calendar.MONTH);
	int año = fecha2.get(Calendar.YEAR);
	int hora = fecha2.get(Calendar.HOUR_OF_DAY);
	int minuto = fecha2.get(Calendar.MINUTE);
	int segundo = fecha2.get(Calendar.SECOND);
	String laFecha = año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
*/
	//"2013-01-01 10:00:00"
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	protected void setUp() throws Exception {
		
	}
	
	protected void tearDown() throws Exception {
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM dueño");
		GestorBD.getInstance().borrar("DELETE FROM campeonato");
		GestorBD.getInstance().borrar("DELETE FROM modalidad");
		GestorBD.getInstance().borrar("DELETE FROM carrera");
		GestorBD.getInstance().borrar("DELETE FROM club");
		GestorBD.getInstance().borrar("DELETE FROM trineo");
		GestorBD.getInstance().borrar("DELETE FROM utiliza");		
		GestorBD.getInstance().borrar("DELETE FROM perroPertenece");	
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera");
		GestorBD.getInstance().borrar("DELETE FROM utiliza");		
		GestorBD.getInstance().borrar("DELETE FROM llevara");	
		GestorBD.getInstance().borrar("DELETE FROM ventas");			
	}

///////////////////////////////////////////////////////////////////////////////
    	//GestorUsuarios-identificarse(elNombreIntro,laContraseñaIntro)//
//////////////////////////////////////////////////////////////////////////////	
	public void testIdentificarse(){
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (1,'contraseña','dueño')");
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (1,'nombre','apellido',true)");//¿ES NECESARIO CREAR EL DUEÑO AL QUE HACE REFERENCIA LA TABLA USUARIO? -- al reves, la clave extranjera esta en dueño
		
		
//// 1 ////----	nombreIntro=null , contraseña=null ----////
		
		try{
			GestorUsuarios.getGestorUsuarios().identificarse(null,null);
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////----	nombreIntro=dato , contraseña=null ----////
		
		try{
			GestorUsuarios.getGestorUsuarios().identificarse(1,null); //("nombre1",null)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////----	nombreIntro=null , contraseña=dato ----////	
		
		try{
			GestorUsuarios.getGestorUsuarios().identificarse(null,"contraseña"); //(null,"contraseña1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////----	nombreIntro=dato incorrecto , contraseña=dato incorrecto ----////
		
		try{
			GestorUsuarios.getGestorUsuarios().identificarse(2,"contraseña2"); //("nombre2","contraseña2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 5 ////----	nombreIntro=dato correcto , contraseña=dato incorrecto ----////
		
		try{
			GestorUsuarios.getGestorUsuarios().identificarse(1,"contraseña2"); //("nombre1","contraseña2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 6 ////----	nombreIntro=dato incorrecto , contraseña=dato correcto ----////
		
		try{
			GestorUsuarios.getGestorUsuarios().identificarse(2,"contraseña"); //("nombre2","contraseña1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 7 ////----	nombreIntro=dato correcto , contraseña=dato correcto ----////
		
		try{
			GestorUsuarios.getGestorUsuarios().identificarse(1,"contraseña"); //("nombre1","contraseña1")
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		
	/*	
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='dueño'");	
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=1 AND Nombre='nombre' AND Apellido='apellido' AND Estado=true"); //¿ES NECESARIO ELIMINAR EL DUEÑO O AL ELIMINAR EL USUARIO SE ELIMINA EL DUEÑO?
	*/
	
	/* 
	   COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM dueño");	
	*/
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////
	//GestorCampeonatos-obtenerCampeonatos()//
//////////////////////////////////////////////////////////////////////////////
	public void testObtenerCampeonatos(){
		
//// 1 ////----	no hay ningun campeonato en la BD ----////		
		
		ArrayList<String>l1 = GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		assertEquals(l1.size(), 0);
		
//// 2 ////----	hay un solo campeonato en la BD ----////		
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");
		
		ArrayList<String>l2= GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		assertEquals(l2.size(), 1);
		
//// 3 ////----	hay dos o mas campeonatos en la BD ----////		
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-03-01 10:00:00','nombreCampeonato2',true)");

		ArrayList<String>l3 = GestorCampeonatos.getGestorCampeonatos().obtenerCampeonatos();
		assertEquals(l3.size(), 2);
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-03-01 10:00:00' AND Nombre='nombreCampeonato2' AND Estado=true");	
		 */
		
		/* 
		   COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
			GestorBD.getInstance().borrar("DELETE FROM campeonato");	
		*/
	}

///////////////////////////////////////////////////////////////////////////////
	//GestorCampeonatos-obtenerCarrerasCampeonato(elNombreCampeonato)//
//////////////////////////////////////////////////////////////////////////////
	public void testObtenerCarrerasCampeonato(){
		
//// 1 ////----	el campeonato no existe ----////		
		
		try{
			GestorCampeonatos.getGestorCampeonatos().obtenerCarrerasCampeonato("nombreCampeonato"); //("nombreCampeonato1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////----	el campeonato existe , no hay carreras ----////		
	
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");
		
		ArrayList<String>l1 = GestorCampeonatos.getGestorCampeonatos().obtenerCarreras("nombreCampeonato"); //("nombreCampeonato2")
		assertEquals(l1.size(), 0);
		
//// 3 ////----	el campeonato existe , hay una carrera ----////		
		
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
	
		ArrayList<String>l2 = GestorCampeonatos.getGestorCampeonatos().obtenerCarreras("nombreCampeonato"); // ("nombreCampeonato2")
		assertEquals(l2.size(), 1);
		
//// 4 ////----	el campeonato existe , hay dos o mas carreras ----////		

		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-03 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		ArrayList<String>l3 = GestorCampeonatos.getGestorCampeonatos().obtenerCarreras("nombreCampeonato"); // ("nombreCampeonato2")
		assertEquals(l3.size(), 2);
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-03 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");
		*/
		
		/* 
		   COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
			GestorBD.getInstance().borrar("DELETE FROM campeonato");	
			GestorBD.getInstance().borrar("DELETE FROM modalidad");
			GestorBD.getInstance().borrar("DELETE FROM carrera");	
		*/
		
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorCampeonatos-obtenerPuntosEnCarrera(elNombreCampeonato,laFechaCarrera)//
//////////////////////////////////////////////////////////////////////////////
	public void testObtenerPuntosEnCarrera(){
		
//// 1 ////----	el campeonato no existe ----////		
		
		try{
			GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCarrera("nombreCampeonato","2013-02-02 10:00:00"); //("elNombreCampeonato1","laFechaCarrera")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 2 ////----	el campeonato existe , la carrera no existe ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-03-01 10:00:00','nombreCampeonato2',true)");
		
		try{
			GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCarrera("nombreCampeonato2","2013-02-02 10:00:00"); // ("elNombreCampeonato2","laFechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 3 ////----	el campeonato existe , la carrera existe , la carrera no pertenece al campeonato ----///
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		try{
			GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCarrera("nombreCampeonato2","2013-02-02 10:00:00"); // ("elNombreCampeonato1","laFechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////----	el campeonato existe ,la carrera existe , la carrera está asociada al campeonato , no hay trineos en esa carrera ----////	
	
		/*  SE INDICA EN LA PROPIA CARRERA PORQUE HAY QUE INDICAR LA FECHA CAMPEONATO
		 * GestorBD.getGestorBD().execSQL("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',null,0,0)"); //¿COMO INDICO QUE EL TRINEO NO ESTA EN LA CARRERA, PONIENDO NULL O NO PONIENDO NADA?
		 */
		
		ArrayList<String>l1 = GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCarreras("NombreCampeonato","2013-02-02 10:00:00"); // ("elNombreCampeonato1","laFechaCarrera1")
		assertEquals(l1.size(), 0);
		
//// 5 ////----	el campeonato existe ,la carrera existe , la carrera está asociada al campeonato , hay un trineo en esa carrera ----////	
	
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (2,'nombreClub','patrocinador',true)");
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (111,2,'fabricante','2012-01-01 10:00:00',true)");
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',111,0,0)"); 
		
		ArrayList<String>l2 = GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCarreras("nombreCampeonato","2013-02-02 10:00:00"); // ("elNombreCampeonato1","laFechaCarrera1")
		assertEquals(l2.size(), 1);
		
//// 6 ////----	el campeonato existe ,la carrera existe , la carrera está asociada al campeonato , hay dos o mas trineos en esa carrera ----////	
	
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (112,2,'fabricante','2012-01-01 10:00:00',true)");
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',112,0,0)"); 
		
		ArrayList<String>l3 = GestorCampeonatos.getGestorCampeonatos().obtenerPuntosEnCarreras("nombreCampeonato","2013-02-02 10:00:00"); //("elNombreCampeonato1","laFechaCarrera1")
		assertEquals(l3.size(), 2);
		
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-03-01 10:00:00' AND Nombre='nombreCampeonato2' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");
		//  NO  GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=null AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=2 AND Nombre='nombre' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=111 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=111 AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=112 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
		*/
		
		/*
		  COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		  GestorBD.getInstance().borrar("DELETE FROM campeonato");	
	   	  GestorBD.getInstance().borrar("DELETE FROM modalidad");	
		  GestorBD.getInstance().borrar("DELETE FROM carrera");	
		  GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera");
		  GestorBD.getInstance().borrar("DELETE FROM usuario");
		  GestorBD.getInstance().borrar("DELETE FROM club");
	      GestorBD.getInstance().borrar("DELETE FROM trineo");			
		 */
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorCarreras-obtenerCarreras()//
//////////////////////////////////////////////////////////////////////////////
	public void testObtenerCarreras(){
		
//// 1 ////----	no hay ninguna carrera en la BD ----////		
		
		LinkedList<String>l1 = GestorCarreras.getGestorCarreras().obtenerCarreras();
		assertEquals(l1.size(), 0);
		
//// 2 ////----	hay un solo carrera en la BD ----////			
		
		try {
			GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try {
			GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinkedList<String>l2= GestorCarreras.getGestorCarreras().obtenerCarreras();
		assertEquals(l2.size(), 1);
		
//// 3 ////----	hay dos o mas carreras en la BD ----////		
		
		try {
			GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-03 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinkedList<String>l3 = GestorCarreras.getGestorCarreras().obtenerCarreras();
		assertEquals(l3.size(), 2);
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-03 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");
		*/
		
		/*
		  COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		  GestorBD.getInstance().borrar("DELETE FROM campeonatro");
		  GestorBD.getInstance().borrar("DELETE FROM modalidad");
		  GestorBD.getInstance().borrar("DELETE FROM carrera");		
		 */
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorCarreras-obtenerTrineosParticipantes(nombreClub,fechaCarrera)//
//////////////////////////////////////////////////////////////////////////////
	/*
	 * MODIFICACION !!
	 * GestorCarreras-obtenerTrineosParticipantes(nombreClub,fechaCarrera,fechaCampeonato)
	 */
	public void testObtenerTrineosParticipantes(){
		
//// 1 ////---- el club no existe , la carrera no existe ----////		
		
		try{
			// MODIFICADO - GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-02-02 10:00:00"); // ("nombreClub1","fechaCarrera1")
			GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); // ("nombreClub1","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////---- el club no existe , la carrera existe ----////		
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");		
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		try{
			//GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub", "2013-02-02 10:00:00"); // ("nombreClub1","fechaCarrera2")
			GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub", "2013-02-02 10:00:00","2013-02-01 10:00:00"); // ("nombreClub1","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////---- el club existe , la carrera no existe ----////		
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (2,'nombreClub','patrocinador',true)");
		
		try{
			//GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-03-02 10:00:00"); // ("nombreClub2","fechaCarrera1")
			GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-04-02 10:00:00","2013-04-01 10:00:00"); // ("nombreClub2","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////---- el club existe , la carrera existe , no hay trineos asociados ----////		
		
		/*  NO - PARA QUE  NO HAYA TRINEOS ASOCIADOS SIMPLEMENTE NO EXISTE LA RELACIÓN RECORRIDOCARRERA
		 * GestorBD.getGestorBD().execSQL("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',null,0,0)"); //¿COMO INDICO QUE EL TRINEO NO ESTA EN LA CARRERA, PONIENDO NULL O NO PONIENDO NADA?
		 */
		
		//ArrayList<String>l1 = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-02-02 10:00:00"); // ("nombreClub2","fechaCarrera2")
		ArrayList<String>l1 = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); // ("nombreClub2","fechaCarrera2")
		assertEquals(l1.size(), 0);
		
//// 5 ////---- el club existe , la carrera existe , hay un trineo asociado ----////		
		
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (111,2,'fabricante','2012-01-01 10:00:00',true)");
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',111,0,0)");
		
		//ArrayList<String>l2 = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-03-02 10:00:00"); //("nombreClub2","fechaCarrera2")
		ArrayList<String>l2 = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","fechaCarrera2")
		assertEquals(l2.size(), 1);
		
//// 6 ////---- el club existe , la carrera existe , hay dos o mas trineos asociados ----////		

		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (112,2,'fabricante','2012-01-01 10:00:00',true)");
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',112,0,0)");
		
		//ArrayList<String>l3 = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-03-02 10:00:00");// ("nombreClub2","fechaCarrera2")
		ArrayList<String>l3 = GestorCarreras.getGestorCarreras().obtenerTrineosParticipantes("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00");// ("nombreClub2","fechaCarrera2")
		assertEquals(l3.size(), 2);
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=2 AND Nombre='nombreClub' AND Patrocinador='patrocinador' AND Estado=true");
		// NO GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=null AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=111 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=111 AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=112 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
		 */
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM campeonato);
		GestorBD.getInstance().borrar("DELETE FROM modalidad);
		GestorBD.getInstance().borrar("DELETE FROM carrera);
		GestorBD.getInstance().borrar("DELETE FROM usuario);
		GestorBD.getInstance().borrar("DELETE FROM club);
		GestorBD.getInstance().borrar("DELETE FROM trineo");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera);
		*/
	}
	
//---- VERSION1 ko --------------------------------------------------------------------------------------------------------------------
	
/*///////////////////////////////////////////////////////////////////////////////
	//GestorCarreras-modificacionInscripcionCarrera(fechaCarrera,idTrineo,inscrito)//	RECORRIDO CARRERRA
//////////////////////////////////////////////////////////////////////////////
	public void testModificacionInscripcionCarrera(){
		
		 * MODIFICACION !!
		 * GestorCarreras-modificacionInscripcionCarrera(fechaCarrera,fechaCampeonato,idTrineo,inscrito)
		 
		
//// 1 ////---- la carrera no existe , el trineo no existe ----////	
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",111,false); //("fechaCarrera1","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",111,false); //("fechaCarrera1","idTrineo1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 2 ////---- la carrera no existe , el trineo existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (2,'nombreClub','patrocinador',true)");
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (111,2,'fabricante','2012-01-01 10:00:00',true)");
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",111,false); //("fechaCarrera1","idTrineo2",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",111,false); //("fechaCarrera1","idTrineo2",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////---- la carrera existe , el trineo no existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");		
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////---- la carrera existe , el trineo no existe , el trineo participaba en la carrera, inscrito = true ----////

		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',112,0,0)"); //¿ES CON ESTA RELACION CON LA QUE INDICO QUE EL TRINEOO PARTICIPABA EN LA CARRERA?
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",112,true); //("fechaCarrera2","idTrineo1",true)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",112,true); //("fechaCarrera2","idTrineo1",true)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE SIGUE EN LA BD !!! (COMO EL TRINEO YA PARTICIPABA EN LA BD E INSCRITO=TRUE TIENE QUE SEGUIR IGUAL)
		Date fechaCarrera;
		Date fechaCampeonato;
		int idTrineo;
		int posicion;
		int puntos;
		
		try{	
		ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
		if(resultado.next()==true){
			fechaCarrera=resultado.getDate("FechaCarrera");
			fechaCampeonato=resultado.getDate("FechaCampeonato");
			idTrineo=resultado.getInt("ID_Trineo");
			posicion=resultado.getInt("Posicion");
			puntos=resultado.getInt("Puntos");
		}
		GestorBD.getInstance().cerrarConsulta(resultado);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
		assertEquals(fechaCarrera,"2013-02-02 10:00:00");
		assertEquals(fechaCampeonato,"2013-02-01 10:00:00");
		assertEquals(idTrineo,112);
		assertEquals(posicion,0);
		assertEquals(puntos,0);
		
		
	 * EJEMPLO
	 	ResultSet resultado2=GestorBD.getInstance().consulta("select VecesAcertada from pregunta where idPregunta=2");
		if(resultado2.next()==true){
			numDespues1=resultado2.getInt("VecesAcertada");
		}
		GestorBD.getInstance().cerrarConsulta(resultado2);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
//// 5 ////---- la carrera existe , el trineo no existe , el trineo participaba en la carrera, inscrito = false ----////	
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE SE HA ELIMINADO DE LA BD !!! (COMO EL TRINEO PARTICIPABA EN LA BD PERO AHORA INSCRITO=TRUE TIENE QUE ELIMINARSE)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
			if(resultado.next()==true){
				fail("ha fallado");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
		
//// 6 ////---- la carrera existe , el trineo no existe , el trineo no participaba en la carrera, inscrito = true ----////

		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",113,true);//("fechaCarrera2","idTrineo1",true)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",113,true);//("fechaCarrera2","idTrineo1",true)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE SE HA AÑADIDO DE LA BD !!! (COMO EL TRINEO NO PARTICIPABA EN LA BD PERO AHORA INSCRITO=TRUE TIENE QUE AÑADIRSE)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=113 AND Posicion=0 AND Puntos=0");
			if(resultado.next()==true){
				fechaCarrera=resultado.getDate("FechaCarrera");
				fechaCampeonato=resultado.getDate("FechaCampeonato");
				idTrineo=resultado.getInt("ID_Trineo");
				posicion=resultado.getInt("Posicion");
				puntos=resultado.getInt("Puntos");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
			assertEquals(fechaCarrera,"2013-02-02 10:00:00");
			assertEquals(fechaCampeonato,"2013-02-01 10:00:00");
			assertEquals(idTrineo,113);
			assertEquals(posicion,0);
			assertEquals(puntos,0);
			
		
//// 7 ////---- la carrera existe , el trineo no existe , el trineo no participaba en la carrera, inscrito = false ----////

		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",113,false); //("fechaCarrera2","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",113,false); //("fechaCarrera2","idTrineo1",false)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE NO SE HA MODIFICADO LA BD !!! (COMO EL TRINEO NO PARTICIPABA EN LA BD E INSCRITO=FALSE TIENE QUE QUEDARSE IGUAL)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=113 AND Posicion=0 AND Puntos=0");
			if(resultado.next()==true){
				fail("ha falllado");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
		
		
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=2 AND Nombre='nombreClub' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=111 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-01 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");	
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
		 
			
		
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario);
		GestorBD.getInstance().borrar("DELETE FROM club);
		GestorBD.getInstance().borrar("DELETE FROM trineo);
		GestorBD.getInstance().borrar("DELETE FROM campeonato);
		GestorBD.getInstance().borrar("DELETE FROM modalidad);
		GestorBD.getInstance().borrar("DELETE FROM carrera");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera);
		
	}*/
//-------------------------------------------------------------------------------------------------------------------------------------

	
//---- VERSION2 ok --------------------------------------------------------------------------------------------------------------------
///////////////////////////////////////////////////////////////////////////////
	//GestorCarreras-modificacionInscripcionCarrera(fechaCarrera,idTrineo,inscrito)//	RECORRIDO CARRERRA
//////////////////////////////////////////////////////////////////////////////
	public void testModificacionInscripcionCarrera(){
		/*
		 * MODIFICACION !!
		 * GestorCarreras-modificacionInscripcionCarrera(fechaCarrera,fechaCampeonato,idTrineo,inscrito)
		 */
		
//// 1 ////---- la carrera no existe , el trineo no existe ----////	
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",111,false); //("fechaCarrera1","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",111,false); //("fechaCarrera1","idTrineo1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 2 ////---- la carrera no existe , el trineo existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES(2,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (2,'nombreClub','patrocinador',true)");
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (111,2,'fabricante','2012-01-01 10:00:00',true)");
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",111,false); //("fechaCarrera1","idTrineo2",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",111,false); //("fechaCarrera1","idTrineo2",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////---- la carrera existe , el trineo no existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");		
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////---- la carrera existe , el trineo existe , el trineo participaba en la carrera, inscrito = true ----////

		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',111,0,0)"); //¿ES CON ESTA RELACION CON LA QUE INDICO QUE EL TRINEOO PARTICIPABA EN LA CARRERA?
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",112,true); //("fechaCarrera2","idTrineo1",true)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",111,true); //("fechaCarrera2","idTrineo1",true)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE SIGUE EN LA BD !!! (COMO EL TRINEO YA PARTICIPABA EN LA BD E INSCRITO=TRUE TIENE QUE SEGUIR IGUAL)
			Date fechaCarrera;
			Date fechaCampeonato;
			int idTrineo;
			int posicion;
			int puntos;
		try{	
		ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=111 AND Posicion=0 AND Puntos=0");
		if(resultado.next()==true){
			fechaCarrera=resultado.getDate("FechaCarrera");
			fechaCampeonato=resultado.getDate("FechaCampeonato");
			idTrineo=resultado.getInt("ID_Trineo");
			posicion=resultado.getInt("Posicion");
			puntos=resultado.getInt("Puntos");
		}
		GestorBD.getInstance().cerrarConsulta(resultado);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
		assertEquals(fechaCarrera,"2013-02-02 10:00:00");
		assertEquals(fechaCampeonato,"2013-02-01 10:00:00");
		assertEquals(idTrineo,111);
		assertEquals(posicion,0);
		assertEquals(puntos,0);
		
	/*	
	 * EJEMPLO
	 	ResultSet resultado2=GestorBD.getInstance().consulta("select VecesAcertada from pregunta where idPregunta=2");
		if(resultado2.next()==true){
			numDespues1=resultado2.getInt("VecesAcertada");
		}
		GestorBD.getInstance().cerrarConsulta(resultado2);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}*/
		
//// 5 ////---- la carrera existe , el trineo existe , el trineo participaba en la carrera, inscrito = false ----////	
		
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",111,false); //("fechaCarrera2","idTrineo1",false)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE SE HA ELIMINADO DE LA BD !!! (COMO EL TRINEO PARTICIPABA EN LA BD PERO AHORA INSCRITO=TRUE TIENE QUE ELIMINARSE)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=111 AND Posicion=0 AND Puntos=0");
			if(resultado.next()==true){
				fail("ha fallado");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
		
//// 6 ////---- la carrera existe , el trineo existe , el trineo no participaba en la carrera, inscrito = true ----////

			GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (112,2,'fabricante','2012-01-01 10:00:00',true)");
		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",113,true);//("fechaCarrera2","idTrineo1",true)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",112,true);//("fechaCarrera2","idTrineo1",true)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE SE HA AÑADIDO DE LA BD !!! (COMO EL TRINEO NO PARTICIPABA EN LA BD PERO AHORA INSCRITO=TRUE TIENE QUE AÑADIRSE)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
			if(resultado.next()==true){
				fechaCarrera=resultado.getDate("FechaCarrera");
				fechaCampeonato=resultado.getDate("FechaCampeonato");
				idTrineo=resultado.getInt("ID_Trineo");
				posicion=resultado.getInt("Posicion");
				puntos=resultado.getInt("Puntos");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
			assertEquals(fechaCarrera,"2013-02-02 10:00:00");
			assertEquals(fechaCampeonato,"2013-02-01 10:00:00");
			assertEquals(idTrineo,112);
			assertEquals(posicion,0);
			assertEquals(puntos,0);
			
		
//// 7 ////---- la carrera existe , el trineo existe , el trineo no participaba en la carrera, inscrito = false ----////

		try{
			//GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00",113,false); //("fechaCarrera2","idTrineo1",false)
			GestorCarreras.getGestorCarreras().modificacionInscripcionCarrera("2013-02-02 10:00:00","2013-02-01 10:00:00",112,false); //("fechaCarrera2","idTrineo1",false)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE NO SE HA MODIFICADO LA BD !!! (COMO EL TRINEO NO PARTICIPABA EN LA BD E INSCRITO=FALSE TIENE QUE QUEDARSE IGUAL)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
			if(resultado.next()==true){
				fail("ha falllado");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
		/*
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=2 AND Nombre='nombreClub' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=111 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-01 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");	
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=111 AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=112 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		*/
			
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario);
		GestorBD.getInstance().borrar("DELETE FROM club);
		GestorBD.getInstance().borrar("DELETE FROM trineo);
		GestorBD.getInstance().borrar("DELETE FROM campeonato);
		GestorBD.getInstance().borrar("DELETE FROM modalidad);
		GestorBD.getInstance().borrar("DELETE FROM carrera");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera);
		*/
	}
//------------------------------------------------------------------------------------------------------------------------	

///////////////////////////////////////////////////////////////////////////////
	//GestorCarreras-cargarTrineos(nombreClub,fechaCarrera)//
//////////////////////////////////////////////////////////////////////////////
	/*
	 * MODIFICACION !!
	 * GestorCarreras-cargarTrineos(nombreClub,fechaCarrera,fechaCampeonato)
	 */
	
	public void testCargarTrineos(){

//// 1 ////---- el club no existe , la carrera no existe ----////		
		
		try{
			//GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00"); //("nombreClub1","fechaCarrera1")
			GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub1","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////---- el club no existe , la carrera existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");		
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		try{
			//GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00"); //("nombreClub1","fechaCarrera2")
			GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub1","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////---- el club existe , la carrera no existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (2,'nombreClub','patrocinador',true)");
		
		try{
			//GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-03-02 10:00:00"); //("nombreClub2","fechaCarrera1")
			GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-03-02 10:00:00","2013-03-01 10:00:00"); //("nombreClub2","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////---- el club existe , la carrera existe , el club no tiene trineos inscritos----////
		
		//ArrayList<String>l1 = GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00"); //("nombreClub2","fechaCarrera2")
		ArrayList<String>l1 = GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","fechaCarrera2")
		assertEquals(l1.size(), 0);
	
//// 5 ////---- el club existe , la carrera existe , el club tiene un unico trineo inscrito----////
		
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (111,2,'fabricante','2013-02-02 10:00:00',true)"); // ¿ASI SE INDICA QUE EL TRINEO ESTA INSCRITO EN EL CLUB?
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',111,0,0)"); //¿ES NECESARIO ESTA RELACION PARA INDICAR QUE EL TRINEO PARTICIPA EN LA CARRERA?
		
		//ArrayList<String>l2 = GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00"); //("nombreClub2","fechaCarrera2")
		ArrayList<String>l2 = GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","fechaCarrera2")
		assertEquals(l2.size(), 1);
		
//// 6 ////---- el club existe , la carrera existe , el club tiene dos o mas trineos inscritos----////
		
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (112,2,'fabricante','2012-01-01 10:00:00',true)");
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',112,0,0)");  //¿ES NECESARIO ESTA RELACION PARA INDICAR QUE EL TRINEO PARTICIPA EN LA CARRERA?
		
		//ArrayList<String>l3 = GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00"); //("nombreClub2","fechaCarrera2")
		ArrayList<String>l3 = GestorCarreras.getGestorCarreras().cargarTrineos("nombreClub","2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","fechaCarrera2")
		assertEquals(l3.size(), 2);
	
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");	
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=2 AND Nombre='nombreClub' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=111 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=111 AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=112 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion=FECHA AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=112 AND Posicion=0 AND Puntos=0");
		*/
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM campeonato);
		GestorBD.getInstance().borrar("DELETE FROM modalidad);
		GestorBD.getInstance().borrar("DELETE FROM carrera);
		GestorBD.getInstance().borrar("DELETE FROM usuario);
		GestorBD.getInstance().borrar("DELETE FROM club);
		GestorBD.getInstance().borrar("DELETE FROM trineo");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera);
		*/
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorClubes-obtenerClubs()//
//////////////////////////////////////////////////////////////////////////////
	public void testObtenerClubs(){
		
//// 1 ////----	no hay ningun club registrado ----////		
		
		LinkedList<Club>l1 = GestorClubs.getGestorClubs().obtenerClubs();
		assertEquals(l1.size(), 0);
		
//// 2 ////----	hay un club registrado ----////	
		
		try {
			GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','club')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//!!!nuevo
		try {
			GestorBD.getInstance().insertar("INSERT INTO club VALUES (2,'nombreClub','patrocinador',true)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinkedList<Club>l2= GestorClubs.getGestorClubs().obtenerClubs();
		assertEquals(l2.size(), 1);
		
//// 3 ////----	hay dos o mas clubes registrados ----////	
		
		try {
			GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (3,'contraseña','club')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//!!!nuevo
		try {
			GestorBD.getInstance().insertar("INSERT INTO club VALUES (3,'nombreClub','patrocinador',true)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LinkedList<Club>l3 = GestorClubs.getGestorClubs().obtenerClubs();
		assertEquals(l3.size(), 2);
		
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=2 AND Nombre='nombreClub' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=3 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=3 AND Nombre='nombreClub' AND Patrocinador='patrocinador' AND Estado=true");
		*/
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario);
		GestorBD.getInstance().borrar("DELETE FROM club);
		*/
	}
	
	
///////////////////////////////////////////////////////////////////////////////
	//GestorPerros-cargarPerros(nombreClub,idTrineo,fechaCarrera)//					¡¡ACABAR!!
//////////////////////////////////////////////////////////////////////////////
	/*
	 * MODIFICACION !!
	 * GestorCarreras-cargarPerros(nombreClub,idTrineo,fechaCarrera,fechaCampeonato)
	 */
	
	public void testCargarPerros(){
	
//// 1 ////----	el club no existe, la carrera no existe, el trineo no existe ----////	
		
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombreClub1",111,"2013-02-02 10:00:00");//("nombreClub1","idTrineo1","fechaCarrera1")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub1",111,"2013-02-02 10:00:00","2013-02-01 10:00:00");//("nombreClub1","idTrineo1","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////----	el club no existe, la carrera no existe, el trineo existe ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (1,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (1,'nombreClub','patrocinador',true)"); 
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (111,1,'fabricante','2012-01-01 10:00:00',true)");//¿ES NECESARIO CREAR EL CLUB PARA CREAR EL TRINEO?
		
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombreClub1",111,"2013-02-02 10:00:00");//("nombreClub1","idTrineo2","fechaCarrera1")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub1",111,"2013-02-02 10:00:00","2013-02-01 10:00:00");//("nombreClub1","idTrineo2","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////----	el club no existe, la carrera existe, el trineo no existe ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");		
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',22)");
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombreClub1",222,"2013-02-02 10:00:00"); //("nombreClub1","idTrineo1","fechaCarrera2")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub1",222,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub1","idTrineo1","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////----	el club no existe, la carrera existe, el trineo existe ----////	
		
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombreClub1",111,"2013-02-02 10:00:00"); //("nombreClub1","idTrineo2","fechaCarrera2")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub1",111,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub1","idTrineo2","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 5 ////----	el club existe, la carrera no existe, el trineo no existe ----////	
		
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombre",222,"2013-03-02 10:00:00"); //("nombreClub2","idTrineo1","fechaCarrera1")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub",222,"2013-03-02 10:00:00","2013-03-01 10:00:00"); //("nombreClub2","idTrineo1","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 6 ////----	el club existe, la carrera no existe, el trineo existe ----////	

		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombre",111,"2013-03-02 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera1")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub",111,"2013-03-02 10:00:00","2013-03-01 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 7 ////----	el club existe, la carrera existe, el trineo no existe ----////	

		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombre",222,"2013-02-02 10:00:00"); //("nombreClub2","idTrineo1","fechaCarrera2")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub",222,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","idTrineo1","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 8 ////----	el club existe, la carrera existe, el trineo existe, el trineo no corre en esa carrera ----////	
			
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombre",111,"2013-02-02 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub",111,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 9 ////----	el club existe, la carrera existe, el trineo existe, el trineo no es de ese club ----////	
	
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (2,'nombreClub2','patrocinador',true)"); 
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (222,2,'fabricante','2012-01-01 10:00:00',true)");//¿ES NECESARIO CREAR EL CLUB PARA CREAR EL TRINEO?
	
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombre",222,"2013-02-02 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub",222,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}

//// 10 ////---- el club existe, la carrera existe, el trineo existe, el trineo corre en esa carrera, el trineo no es de ese club ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',222,0,0)");
		
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombre",222,"2013-02-02 10:00:00");//("nombreClub2","idTrineo2","fechaCarrera2")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub",222,"2013-02-02 10:00:00","2013-02-01 10:00:00");//("nombreClub2","idTrineo2","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}

//// 11 ////---- el club existe, la carrera existe, el trineo existe, el trineo es de ese club, el trineo no corre en esa carrera ----////	
	
		try{
			//GestorPerros.getGestorPerros().cargarPerros("nombre2",222,"2013-02-02 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
			GestorPerros.getGestorPerros().cargarPerros("nombreClub",111,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 12 ////---- el club existe, la carrera existe, el trineo existe, el trineo corre en esa carrera, el trineo es de ese club, el club no tiene perros ----////	
		
		//ArrayList<String>l1 = GestorPerros.getGestorPerros().cargarPerros("nombre2",222,"2013-02-02 10:00:00");//("nombreClub2","idTrineo2","fechaCarrera2")
		ArrayList<String>l1 = GestorPerros.getGestorPerros().cargarPerros("nombreClub2",222,"2013-02-02 10:00:00","2013-02-01 10:00:00");//("nombreClub2","idTrineo2","fechaCarrera2")
		assertEquals(l1.size(), 0);
		
//// 13 ////---- el club existe, la carrera existe, el trineo existe, el trineo corre en esa carrera, el trineo es de ese club, el club tiene un perro ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (21,'nombre','raza','sexo',true)");
		GestorBD.getInstance().insertar("INSERT INTO utiliza VALUES (2,21,'2013-11-01 10:00:00','2013-12-01 10:00:00')");
		
		//ArrayList<String>l2 = GestorPerros.getGestorPerros().cargarPerros("nombre2",222,"2013-02-02 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
		ArrayList<String>l2 = GestorPerros.getGestorPerros().cargarPerros("nombreClub2",222,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
		assertEquals(l2.size(), 1);
		
//// 14 ////---- el club existe, la carrera existe, el trineo existe, el trineo corre en esa carrera, el trineo es de ese club, el club tiene dos o mas perros ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (22,'nombre','raza','sexo',true)");
		GestorBD.getInstance().insertar("INSERT INTO utiliza VALUES (2,22,'2013-11-01 10:00:00','2013-12-01 10:00:00')");
		
		//ArrayList<String>l3 = GestorPerros.getGestorPerros().cargarPerros("nombre2",222,"2013-02-02 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
		ArrayList<String>l3 = GestorPerros.getGestorPerros().cargarPerros("nombreClub2",222,"2013-02-02 10:00:00","2013-02-01 10:00:00"); //("nombreClub2","idTrineo2","fechaCarrera2")
		assertEquals(l3.size(), 2);
		
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=1 AND Nombre='nombreClub' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=111 AND ID_Club=1 AND Fabricante='fabricante' AND FechaFabricacion='2011-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");	
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=2 AND Nombre='nombreClub2' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=222 AND ID_Club=2 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=222 AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=21 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM utiliza WHERE ID_Club=2 AND ID_Perro=21 AND FechaInicioUtilizacion='2013-11-01 10:00:00' AND FechaFinUtilización='2013-12-01 10:00:00'");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=22 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM utiliza WHERE ID_Club=2 AND ID_Perro=22 AND FechaInicioUtilizacion='2013-11-01 10:00:00' AND FechaFinUtilización='2013-12-01 10:00:00'");		
		*/
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM club");
		GestorBD.getInstance().borrar("DELETE FROM trineo");
		GestorBD.getInstance().borrar("DELETE FROM campeonato");
		GestorBD.getInstance().borrar("DELETE FROM modalidad");
		GestorBD.getInstance().borrar("DELETE FROM carrera");	
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera");
		GestorBD.getInstance().borrar("DELETE FROM perro");
		GestorBD.getInstance().borrar("DELETE FROM utiliza");
		*/
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorPerros-tirarPerro(idPerro,idTrineo,fechaCarrera,tiraOno)//				LLEVARA 	¡¡ACABAR!!
//////////////////////////////////////////////////////////////////////////////
	/*
	 * MODIFICACION !!
	 * GestorPerros-tirarPerro(idPerro,idTrineo,fechaCarrera,fechaCampeonato,tirarOno)
	 */
	public void testTirarPerro(){
		
//// 1 ////---- El trineo no existe, La carrera no existe, El perro no existe ----////
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false); //("idPerro1","idTrineo1","fechaCarrera1",false)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro1","idTrineo1","fechaCarrera1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}

//// 2 ////---- El trineo no existe, La carrera no existe, El perro existe  ----////
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (21,'nombre','raza','sexo',true)");
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera1",false)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}

//// 3 ////---- El trineo no existe, La carrera existe, El perro no existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO campeonato VALUES ('2013-02-01 10:00:00','nombreCampeonato',true)");		
		GestorBD.getInstance().insertar("INSERT INTO modalidad VALUES (11,'descripcion',2)");  //HABÍA PUESTO COOMO NUMMAXPERROS 22 PERO LO HE CAMBIADO A 2 PORQUE SINO VOY A TENER QUE CREAR MUCHOS PERROS
		GestorBD.getInstance().insertar("INSERT INTO carrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00','nombreCarrera','lugar','recorrido',true,11)");
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(22,111,"2013-02-02 10:00:00",false);//("idPerro1","idTrineo1","fechaCarrera2",false)
			GestorPerros.getGestorPerros().tirarPerro(22,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false);//("idPerro1","idTrineo1","fechaCarrera2",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}

//// 4 ////---- El trineo no existe, La carrera existe, El perro existe ----////
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera2",false);
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera2",false);
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}

//// 5 ////---- El trineo existe, La carrera no existe, El perro no existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (1,'contraseña','club')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO club VALUES (1,'nombre','patrocinador',true)"); 
		GestorBD.getInstance().insertar("INSERT INTO trineo VALUES (111,1,'fabricante','2012-01-01 10:00:00',true)");
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(22,111,"2013-03-02 10:00:00",false);
			GestorPerros.getGestorPerros().tirarPerro(22,111,"2013-03-02 10:00:00","2013-03-01 10:00:00",false);
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 6 ////---- El trineo existe, La carrera no existe, El perro existe ----////
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-03-02 10:00:00",false);//("idPerro2","idTrineo2","fechaCarrera1",false);
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-03-02 10:00:00","2013-03-01 10:00:00",false);//("idPerro2","idTrineo2","fechaCarrera1",false);
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 7 ////---- El trineo existe, La carrera existe, El perro no existe ----////
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(22,111,"2013-02-02 10:00:00",false); //("idPerro1","idTrineo2","fechaCarrera2",false)
			GestorPerros.getGestorPerros().tirarPerro(22,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro1","idTrineo2","fechaCarrera2",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
	
//// 8 ////---- El trineo existe, La carrera existe, El perro existe, El trineo no corre en esa carrera ----////
		
		/* MAL - PARA QUE EL TRINEO NO CORRA EN LA CARRERA CON LA QUE VOY HA HACER LA PRUEBA LO TENGO QUE RELACIONAR CON OTRA CARRERA O NO RELACIONARLO CON ESA CARRERA
		 * GestorBD.getGestorBD().execSQL("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',111,0,0)");
		 */
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false); //("idPerro1","idTrineo1","fechaCarrera1",false)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro1","idTrineo1","fechaCarrera1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 9 ////---- El trineo existe, La carrera existe, El perro existe, El club del perro y del trineo no coincide ----////
		
		GestorBD.getInstance().insertar("INSERT INTO utiliza VALUES (2,21,'2013-11-01 10:00:00','2013-12-01 10:00:00')"); //¿ES ASI COMO INDICO EL CLUB DEL PERRO?
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false); //("idPerro1","idTrineo1","fechaCarrera1",false)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro1","idTrineo1","fechaCarrera1",false)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 10 ////---- El club existe, La carrera existe, El trineo existe, El trineo corre en esa carrera, El club del perro y del trineo coincide, TiraONo = true, El perro no estaba inscrito, El número de perros que tira es menor al permitido por la modalidad ----////
		
		GestorBD.getInstance().insertar("INSERT INTO recorridoCarrera VALUES ('2013-02-02 10:00:00','2013-02-01 10:00:00',111,0,0)");
		GestorBD.getInstance().insertar("INSERT INTO utiliza VALUES (1,21,'2013-11-01 10:00:00','2013-12-01 10:00:00')"); //¿ES ASI COMO INDICO EL CLUB DEL PERRO?
		//¿COMO COMPRUEBO QUE EL NUMERO DE PERROS QUE TIRA ES MENOR AL PERMITIDO POR LA MODALIDAD?
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (22,'nombre','raza','sexo',true)");// CREO OTRO PERRO PARA AÑADIRLO A LA RELACION LLEVARA(NO PUEDO AÑADIR EL PROPIO PERRO PORQUE EN ESTE CASO NO ESTÁ INSCRITO)
		GestorBD.getInstance().insertar("INSERT INTO llevara VALUES (22,111'2013-02-02 10:00:00','2013-02-01 10:00:00')"); // SOLO TIRA UN PERRO
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",true); //("idPerro2","idTrineo1","fechaCarrera1",true)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",true); //("idPerro2","idTrineo1","fechaCarrera1",true)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		//COMPROBAR QUE SE HA AÑADIDO EN LA BD !!! (COMO EL PERRO NO ESTABA INSCRITO PERO AHORA TIRAONO=TRUE TIENE QUE AÑADIRSE)
		int idPerro;
		int idTrineo;
		Date fechaCarrera;
		Date fechaCampeonato;
		
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM llevara WHERE ID_Perro=21 AND ID_Trineo=111 AND FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00'");
			if(resultado.next()==true){
				idPerro=resultado.getInt("ID_Perro");
				idTrineo=resultado.getInt("ID_Trineo");
				fechaCarrera=resultado.getDate("FechaCarrera");
				fechaCampeonato=resultado.getDate("FechaCampeonato");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
			assertEquals(idPerro,21);
			assertEquals(idTrineo,111);
			assertEquals(fechaCarrera,"2013-02-02 10:00:00");
			assertEquals(fechaCampeonato,"2013-02-01 10:00:00");
			
		
//// 11 ////---- El club existe, La carrera existe, El trineo existe, El trineo corre en esa carrera, El club del perro y del trineo coincide, TiraONo = true, El perro no estaba inscrito, El número de perros que tira es igual al permitido por la modalidad ----////

		//¿COMO COMPRUEBO QUE EL NUMERO DE PERROS QUE TIRA ES IGUAL AL PERMITIDO POR LA MODALIDAD?
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (23,'nombre','raza','sexo',true)");// CREO OTRO PERRO PARA AÑADIRLO A LA RELACION LLEVARA(NO PUEDO AÑADIR EL PROPIO PERRO PORQUE EN ESTE CASO NO ESTÁ INSCRITO)
		GestorBD.getInstance().insertar("INSERT INTO llevara VALUES (23,111,'2013-02-02 10:00:00','2013-02-01 10:00:00')"); // TIRAN LOS DOS PERROS PERMITIDOS POR LA MODALIDAD
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",true); //("idPerro2","idTrineo1","fechaCarrera1",true)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",true); //("idPerro2","idTrineo1","fechaCarrera1",true)
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//------- orden cambiado ----------------------------------------------------------------------------------------------------------------------			
//// 14 ////---- El club existe, La carrera existe, El trineo existe, El trineo corre en esa carrera, El club del perro y del trineo coincide, TiraONo = false, El perro no estaba inscrito----////
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera1",false)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera1",false)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE NO SE HA MODIFICADO LA BD !!! (COMO EL PERRO NO ESTABA INSCRITO Y TIRAONO=FALSE SIGUE IGUAL)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM llevara WHERE ID_Perro=21 AND ID_Trineo=111 AND FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00'");
			if(resultado.next()==true){
				fail("ha falllado");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
//-----------------------------------------------------------------------------------------------------------------------------			

//// 12 ////---- El club existe, La carrera existe, El trineo existe, El trineo corre en esa carrera, El club del perro y del trineo coincide, TiraONo = true, El perro estaba inscrito ----////

		//¿EL PERRO A QUE ESTA INSCRITO?  -  llevara(idPerro,idTrineo) Ó utiliza(idClub,idPerro) ???
			GestorBD.getInstance().insertar("INSERT INTO llevara VALUES (21,111,'2013-02-02 10:00:00','2013-02-01 10:00:00')"); //¿ES ASI COMO INDICO EL CLUB DEL PERRO?
			//  NO  GestorBD.getInstance().insertar("INSERT INTO utiliza VALUES (21,111,'2013-02-02 10:00:00','2013-02-01 10:00:00')"); //¿ES ASI COMO INDICO EL CLUB DEL PERRO?
		
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",true); //("idPerro2","idTrineo1","fechaCarrera1",true)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",true); //("idPerro2","idTrineo1","fechaCarrera1",true)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE NO SE HA MODIFICADO EN LA BD !!! (COMO EL PERRO ESTA INSCRITO Y TIRAONO=TRUE SIGUE IGUAL)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM llevara WHERE ID_Perro=21 AND ID_Trineo=111 AND FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00'");
			if(resultado.next()==true){
				idPerro=resultado.getInt("ID_Perro");
				idTrineo=resultado.getInt("ID_Trineo");
				fechaCarrera=resultado.getDate("FechaCarrera");
				fechaCampeonato=resultado.getDate("FechaCampeonato");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
			assertEquals(idPerro,21);
			assertEquals(idTrineo,111);
			assertEquals(fechaCarrera,"2013-02-02 10:00:00");
			assertEquals(fechaCampeonato,"2013-02-01 10:00:00");
		
		
//// 13 ////---- El club existe, La carrera existe, El trineo existe, El trineo corre en esa carrera, El club del perro y del trineo coincide, TiraONo = false, El perro estaba inscrito  ----////
	
		//¿EL PERRO A QUE ESTA INSCRITO?  -  llevara(idPerro,idTrineo) Ó utiliza(idClub,idPerro) ???
		
		try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false);//("idPerro2","idTrineo1","fechaCarrera1",false)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false);//("idPerro2","idTrineo1","fechaCarrera1",false)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE SE HA AÑADIDO EN LA BD !!! (COMO EL PERRO NO ESTABA INSCRITO PERO AHORA TIRAONO=FALSE TIENE QUE ELIMINARSE)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM llevara WHERE ID_Perro=22 AND ID_Trineo=111 AND FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00'");
			if(resultado.next()==true){
				idPerro=resultado.getInt("ID_Perro");
				idTrineo=resultado.getInt("ID_Trineo");
				fechaCarrera=resultado.getDate("FechaCarrera");
				fechaCampeonato=resultado.getDate("FechaCampeonato");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			
			assertEquals(idPerro,22);
			assertEquals(idTrineo,111);
			assertEquals(fechaCarrera,"2013-02-02 10:00:00");
			assertEquals(fechaCampeonato,"2013-02-01 10:00:00");
		
//------- orden cambiado ----------------------------------------------------------------------------------------------------------------------			
//// 14 ////---- El club existe, La carrera existe, El trineo existe, El trineo corre en esa carrera, El club del perro y del trineo coincide, TiraONo = false, El perro no estaba inscrito----////
		
	/*	try{
			//GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera1",false)
			GestorPerros.getGestorPerros().tirarPerro(21,111,"2013-02-02 10:00:00","2013-02-01 10:00:00",false); //("idPerro2","idTrineo1","fechaCarrera1",false)
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		//COMPROBAR QUE NO SE HA MODIFICADO LA BD !!! (COMO EL PERRO NO ESTABA INSCRITO Y TIRAONO=FALSE SIGUE IGUAL)
		try{	
			ResultSet resultado=GestorBD.getInstance().consulta("SELECT * FROM llevara WHERE ID_Perro=21 AND ID_Trineo=111 AND FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00'");
			if(resultado.next()==true){
				fail("ha falllado");
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}*/
//-----------------------------------------------------------------------------------------------------------------------------				
		
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=21 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM campeonato WHERE FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCampeonato' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM modalidad WHERE ID_Modalidad=11 AND Descripcion='descripcion' AND NumMaxPerros=22");
		GestorBD.getInstance().borrar("DELETE FROM carrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND Nombre='nombreCarrera' AND Lugar='lugar' AND Recorrido='recorrido' AND Estado=true AND ID_Modalidad=11");	
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='club'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM club WHERE ID_Club=1 AND Nombre='nombre1' AND Patrocinador='patrocinador' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM trineo WHERE ID_Trineo=111 AND ID_Club=1 AND Fabricante='fabricante' AND FechaFabricacion='2012-01-01 10:00:00' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera WHERE FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00' AND ID_Trineo=111 AND Posicion=0 AND Puntos=0");
		GestorBD.getInstance().borrar("DELETE FROM utiliza WHERE ID_Club=2 AND ID_Perro=21 AND FechaInicioUtilizacion='2013-11-01 10:00:00' AND FechaFinUtilización='2013-12-01 10:00:00'");		
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=22 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM llevara WHERE ID_Perro=22 AND ID_Trineo=111 AND FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00'");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=23 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM llevara WHERE ID_Perro=23 AND ID_Trineo=111 AND FechaCarrera='2013-02-02 10:00:00' AND FechaCampeonato='2013-02-01 10:00:00'");	
		// NO GestorBD.getInstance().borrar("DELETE FROM utiliza WHERE ID_Club=1 AND ID_Perro=21 AND FechaInicioUtilizacion='2013-11-01 10:00:00' AND FechaFinUtilización='2013-12-01 10:00:00'");				
		*/
			
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM perro");
		GestorBD.getInstance().borrar("DELETE FROM campeonato");
		GestorBD.getInstance().borrar("DELETE FROM modalidad");
		GestorBD.getInstance().borrar("DELETE FROM carrera");	
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM club");
		GestorBD.getInstance().borrar("DELETE FROM trineo");
		GestorBD.getInstance().borrar("DELETE FROM recorridoCarrera");
		GestorBD.getInstance().borrar("DELETE FROM utiliza");		
		GestorBD.getInstance().borrar("DELETE FROM llevara");	
		*/
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorPerros-cargaPerros(nombreDueño)////
//////////////////////////////////////////////////////////////////////////////
	public void testCargaPerros(){
		
//// 1 ////---- el dueño no existe ----////	

		try{
			GestorPerros.getGestorPerros().cargaPerros("nombre"); //("nombreDueño1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////---- el dueño existe , no tiene perros  ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (1,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (1,'nombre','apellido',true)");
		
		ArrayList<String>l1 = GestorPerros.getGestorPerros().cargaPerros("nombre"); //("nombreDueño2")
		assertEquals(l1.size(), 0);
		
//// 3 ////---- el dueño existe , tiene un perro----////
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (21,'nombre','raza','sexo',true)");
		GestorBD.getInstance().insertar("INSERT INTO perroPertenece VALUES ('2013-11-01 10:00:00',1,21,'2013-12-01 10:00:00')");
		
		ArrayList<String>l2 = GestorPerros.getGestorPerros().cargaPerros("nombre");  //("nombreDueño2")
		assertEquals(l2.size(), 1);
		
//// 4 ////---- el dueño existe , tiene dos o mas perros----////	
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (22,'nombre','raza','sexo',true)");
		GestorBD.getInstance().insertar("INSERT INTO perroPertenece VALUES ('2013-11-01 10:00:00',1,22,'2013-12-01 10:00:00')");
		
		ArrayList<String>l3 = GestorPerros.getGestorPerros().cargaPerros("nombre"); //("nombreDueño2")
		assertEquals(l3.size(), 2);
		
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=1 AND Nombre='nombre' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=21 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM perroPertenece WHERE FechaComienzoDueño='2013-11-01 10:00:00' AND NumFederado=1 AND ID_Perro=21 AND FechaFinDueño='2013-22-01 10:00:00'");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=22 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM perroPertenece WHERE FechaComienzoDueño='2013-11-01 10:00:00' AND NumFederado=1 AND ID_Perro=22 AND FechaFinDueño='2013-12-01 10:00:00'");
		*/
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM dueño");
		GestorBD.getInstance().borrar("DELETE FROM perro");
		GestorBD.getInstance().borrar("DELETE FROM perroPertenece");
		*/		
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorPerros-venderPerro(idPerroSelec,numDueñoSelec,nombreDueño)//
//////////////////////////////////////////////////////////////////////////////
	public void testVenderPerro(){  //¿QUIEN ES EL COMPRADOR Y QUIEN ES EL VENDEDOR?
		
//// 1 ////---- el vendedor no existe ----////	
		
		try{
			GestorPerros.getGestorPerros().venderPerros(21,2,"nombreVendedor"); //("idPerroSelec","numDueñoSelec","nombreDueño1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////---- el vendedor existe , el comprador no existe ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (21,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (1,'nombreVendedor','apellido',true)");
		
		try{
			GestorPerros.getGestorPerros().cargarPerros(1,2,"nombreVendedor"); //("idPerroSelec","numDueñoSelec1","nombreDueño2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////---- el vendedor existe , el comprador existe, el perro no existe ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (2,'nombreComprador','apellido',true)");
		
		try{
			GestorPerros.getGestorPerros().cargarPerros(21,2,"nombreVendedor"); //("idPerroSelec1","numDueñoSelec2","nombreDueño2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////---- el vendedor existe , el comprador existe, el perro existe, el perro no pertenece al vendedor ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (21,'nombre','raza','sexo',true)");
		
		try{
			GestorPerros.getGestorPerros().cargarPerros(21,2,"nombreVendedor"); //("idPerroSelec2","numDueñoSelec2","nombreDueño2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 5 ////---- el vendedor existe , el comprador existe, el perro existe, el perro pertenece al vendedor ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO perroPertenece VALUES ('2013-11-01 10:00:00',1,21,'2013-12-01 10:00:00')");
		
		try{
			GestorPerros.getGestorPerros().cargarPerros(21,2,"nombreVendedor"); //("idPerroSelec2","numDueñoSelec2","nombreDueño2")
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=1 AND Nombre='nombreVendedor' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=2 AND Nombre='nombreComprador' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=21 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM perroPertenece WHERE FechaComienzoDueño='2013-11-01 10:00:00' AND NumFederado=1 AND ID_Perro=21 AND FechaFinDueño='2013-12-01 10:00:00'");
		*/
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM dueño");
		GestorBD.getInstance().borrar("DELETE FROM perro");
		GestorBD.getInstance().borrar("DELETE FROM perroPertenece");
		*/	
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorPerros-cargarComprasPendientes(nombreDueño)//
//////////////////////////////////////////////////////////////////////////////
	public void testCargarComprasPendientes(){
	
//// 1 ////---- el dueño no existe  ----////	
		
		try{
			GestorPerros.getGestorPerros().cargarComprasPendientes("nombreComprador"); //("nombreDueño1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////---- el dueño existe , no tiene compras pendientes  ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (1,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (1,'nombreComprador','apellido',true)");
		
		ArrayList<String>l1 = GestorPerros.getGestorPerros().cargaComprasPendientes("nombreComprador");//("nombreDueño2")
		assertEquals(l1.size(), 0);
		
//// 3 ////---- el dueño existe , tiene una compra pendiente ----////
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (21,'nombre','raza','sexo',true)");
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (2,'nombreVendedeor2','apellido',true)");
		GestorBD.getInstance().insertar("INSERT INTO ventas VALUES (21,2,1,'2012-01-01 10:00:00',false)");
		
		ArrayList<String>l2 = GestorPerros.getGestorPerros().cargaComprasPendientes("nombreComprador"); //("nombreDueño2")
		assertEquals(l2.size(), 1);
		
//// 4 ////---- el dueño existe , tiene dos o mas compras pendientes ----////	
		
		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (22,'nombre','raza','sexo',true)");
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (3,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (3,'nombreVendedeor3','apellido',true)");
		GestorBD.getInstance().insertar("INSERT INTO ventas VALUES (22,3,1,'2012-01-01 10:00:00',false)");
		
		ArrayList<String>l3 = GestorPerros.getGestorPerros().cargaComprasPendientes("nombreComprador"); //("nombreDueño2")
		assertEquals(l3.size(), 2);
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=1 AND Nombre='nombreComprador' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=21 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=2 AND Nombre='nombreVendedor2' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM ventas WHERE ID_Perro=21 AND NumVendedor=2 AND NumComprador=1 AND Fecha='2012-01-01 10:00:00' AND Confirmado=false");
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=22 AND NOMBRE='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=3 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=3 AND Nombre='nombreVendedor3' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM ventas WHERE ID_Perro=22 AND NumVendedor=3 AND NumComprador=1 AND Fecha='2012-01-01 10:00:00' AND Confirmado=false");
		 */
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM dueño");
		GestorBD.getInstance().borrar("DELETE FROM perro");
		GestorBD.getInstance().borrar("DELETE FROM ventas");
		*/	
	}
		
	
///////////////////////////////////////////////////////////////////////////////
	//GestorPerros-confirmarCompra(idPerroSelec,nombreDueño)//
//////////////////////////////////////////////////////////////////////////////
	public void testConfirmarCompra(){
		
//// 1 ////---- el perro no existe , el dueño no existe ----////	
		
		try{
			GestorPerros.getGestorPerros().confirmarCompra(21,"nombreVendedor"); //("idPerroSelec1","nombreDueño1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////---- el perro existe , el dueño no existe ----////	

		GestorBD.getInstance().insertar("INSERT INTO perro VALUES (21,'nombre','raza','sexo',true)");

		
		try{
			GestorPerros.getGestorPerros().confirmarCompra(21,"nombreVendedor"); // ("idPerroSelec2","nombreDueño1")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 3 ////---- el perro no existe , el dueño existe ----////
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (1,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (1,'nombreVendedor','apellido',true)");
		
		try{
			GestorPerros.getGestorPerros().confirmarCompra(24,"nombreVendedor"); //("idPerroSelec1","nombreDueño2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 4 ////---- el perro existe , el dueño existe, el perro no fue vendido al dueño ----////
		
		try{
			GestorPerros.getGestorPerros().confirmarCompra("21","nombreVendedor"); //("idPerroSelec2","nombreDueño2")
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 5 ////---- el perro no existe , el dueño existe, el perro fue vendido al dueño ----////
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (2,'nombreComprador','apellido',true)");
		GestorBD.getInstance().insertar("INSERT INTO ventas VALUES (21,1,2,'2012-01-01 10:00:00',true)");  //¿COMO INDICO QUE EL PERRO FUE VENDIDO AL DUEÑO?
		
		try{
			GestorPerros.getGestorPerros().confirmarCompra("21","nombre"); // ("idPerroSelec2","nombreDueño2")
		}
		catch(GenericException e){
			fail("ha fallado");
		}
		
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM perro WHERE ID_Perro=21 AND Nombre='nombre' AND Raza='raza' AND Sexo='sexo' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=1 AND Nombre='nombreVendedor' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=2 AND Nombre='nombreComprador' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM ventas WHERE ID_Perro=21 AND NumVendedor=1 AND NumComprador=2 AND Fecha='2012-01-01 10:00:00' AND Confirmado=true");
		 */
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM perro");
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM dueño");
		GestorBD.getInstance().borrar("DELETE FROM ventas");
		*/	
	}
	
///////////////////////////////////////////////////////////////////////////////
	//GestorDueños-cargaDueños(elNombreDueño)//
//////////////////////////////////////////////////////////////////////////////
	public void testCargaDueños(){
		
//// 1 ////----	el dueño no existe ----////		
		
		try{
			GestorDueños.getGestorDueños().cargarDueños("nombreDueño");
			fail("ha fallado");
		}
		catch(GenericException e){
			
		}
		
//// 2 ////----	el dueño existe, es el unico que existe ----////		
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (1,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (1,'nombreDueño1','apellido',true)");
		
		ArrayList<String>l1 = GestorDueños.getGestorDueños().cargaDueños();
		assertEquals(l1.size(), 0);
		
//// 3 ////----	el dueño existe, solo hay otro dueño más ----////			
		
		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (2,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (2,'nombreDueño2','apellido',true)");
		
		ArrayList<String>l2= GestorClubs.getGestorClubs().cargarDueños();
		assertEquals(l2.size(), 1);
		
//// 4 ////----	el dueño existe, hay mas de dos dueños mas ----////		

		GestorBD.getInstance().insertar("INSERT INTO usuario VALUES (3,'contraseña','dueño')");//!!!nuevo
		GestorBD.getInstance().insertar("INSERT INTO dueño VALUES (3,'nombreDueño3','apellido',true)");
		
		ArrayList<String>l3 = GestorClubs.getGestorClubs().cargarDueños();
		assertEquals(l3.size(), 2);
		
		
		/*
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=1 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=1 AND Nombre='nombreDueño1' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=2 AND Contraseña='contraseña' AND Tipo='dueño'"); //nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=2 AND Nombre='nombreDueño2' AND Apellido='apellido' AND Estado=true");
		GestorBD.getInstance().borrar("DELETE FROM usuario WHERE NombreUsuario=3 AND Contraseña='contraseña' AND Tipo='dueño'");//nuevo!!!
		GestorBD.getInstance().borrar("DELETE FROM dueño WHERE NumFederado=3 AND Nombre='nombreDueño3' AND Apellido='apellido' AND Estado=true");
		 */
		
		/*
		 COMO LO QUE QUIERO ES BORRAR TODO LO QUE HAYA INSERTADO EN LAS TABLAS, ¿CON HACERLO ASÍ YA VALE?
		GestorBD.getInstance().borrar("DELETE FROM usuario");
		GestorBD.getInstance().borrar("DELETE FROM dueño");
		*/	
	}

}
