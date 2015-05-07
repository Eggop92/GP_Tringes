package pruebas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import excepciones.GenericException;
import junit.framework.TestCase;
import Tringes.*;

public class PruebasEgo extends TestCase {
	
	// puebasModificar - Sara
	
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		try{
			GestorBD.getInstance().borrar("delete from ventas           where 1=1;");
			GestorBD.getInstance().borrar("delete from utiliza          where 1=1;");
			GestorBD.getInstance().borrar("delete from recorridocarrera where 1=1;");
			GestorBD.getInstance().borrar("delete from perropertenece   where 1=1;");
			GestorBD.getInstance().borrar("delete from llevara          where 1=1;");
			GestorBD.getInstance().borrar("delete from dueño            where 1=1;");
			GestorBD.getInstance().borrar("delete from apareamiento     where 1=1;");
			GestorBD.getInstance().borrar("delete from perro            where 1=1;");
			GestorBD.getInstance().borrar("delete from trineo           where 1=1;");
			GestorBD.getInstance().borrar("delete from club             where 1=1;");
			GestorBD.getInstance().borrar("delete from carrera          where 1=1;");
			GestorBD.getInstance().borrar("delete from campeonato       where 1=1;");
			GestorBD.getInstance().borrar("delete from modalidad        where 1=1;");
			GestorBD.getInstance().borrar("delete from usuario          where 1=1;");
		}
		catch (SQLException e){}
	}

	//GestorClubs
	//--modificarDatosClub(pID_Club:Int, pNombre:String, pPatrocinador:String)
	public void modificarDatosClubTest(){
		try{
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'Uno', 'Uno Uno', FALSE)");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		//Todos null
		try{
			//GestorClubs.getGestorClubs().modificarDatosClub(null, null, null);
			GestorClubs.getGestorClubs().modificarDatosClub((Integer) null, null, null); //modif-Sara
			fail("null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//Combinacion de nulls
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, null, null);
			fail("01, null, null");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {	//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		try{
			//GestorClubs.getGestorClubs().modificarDatosClub(null, "DOS", null);
			GestorClubs.getGestorClubs().modificarDatosClub((Integer) null, "DOS", null);// modif-Sara
			fail("null, 'DOS', null");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			//GestorClubs.getGestorClubs().modificarDatosClub(null, null, "Dos Dos");
			GestorClubs.getGestorClubs().modificarDatosClub((Integer) null, null, "Dos Dos");//modif-Sara
			fail("null, null, 'Dos Dos'");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "DOS", null);
			fail("01, 'DOS', null");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, null, "Dos dos");
			fail("01, null, 'Dos dos'");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//datos en blanco
		//No deberia de dejar introducir datos vacios o en blanco (solo espacios)
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "", "");
			fail("01, '', ''");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, " ", " ");
			fail("01, ' ', ' '");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "Dos", "");
			fail("01, 'Dos', ''");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");	
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "Dos", " ");
			fail("01, 'Dos', ' '");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "", "Dos Dos");
			fail("01, '', 'Dos Dos'");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, " ", "Dos Dos");
			fail("01, ' ', 'Dos Dos'");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Uno");
				assertEquals(rdo.getString("patrocinador"), "Uno Uno");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//correcto
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "Dos", "Dos dos");
			ResultSet rdo;
			try {
				rdo = GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
				rdo.next();
				assertEquals(rdo.getString("nombre"), "Dos");
				assertEquals(rdo.getString("patrocinador"), "Dos dos");
				rdo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(GenericException e){
			fail("01, 'Dos', 'Dos dos'");
		}
	}
	//--obtenerDatosClub(elIDClub)
	public void obtenerDatosClubTest(){ //nuevo-Sara
		try{
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'Uno', 'Uno Uno', TRUE)");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (02, 'Dos', 'Dos Dos', FALSE)");
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		//nulls
		try{
			LinkedList<String> rdo = GestorClubs.getGestorClubs().obtenerDatosClub((Integer) null);
			fail("");
		}
		catch(GenericException e){}
		
		//El club no existe
		try{
			LinkedList<String> rdo = GestorClubs.getGestorClubs().obtenerDatosClub(07);
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorClubs.getGestorClubs().obtenerDatosClub(-15);
			fail("");
		}
		catch(GenericException e){}
		//El club existe activo
		try{
			LinkedList<String> rdo = GestorClubs.getGestorClubs().obtenerDatosClub(01);

			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "01");
			assertEquals(rdo.get(1), "Uno");
			assertEquals(rdo.get(2), "Uno Uno");
		}
		catch(GenericException e){
			fail("");
		}
		//El club existe inactivo
		try{
			LinkedList<String> rdo = GestorClubs.getGestorClubs().obtenerDatosClub(02);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "02");
			assertEquals(rdo.get(1), "Dos");
			assertEquals(rdo.get(2), "Dos Dos");
		}
		catch(GenericException e){
			fail("");
		}
	}
	
	//GestorUsuarios
	//--modificarContraseña(elIDClub,nuevopass)
	//--modificarContraseña(elNumFederado, nuevoPass)
	public void modificarContraseñaTest(){
		try{
			GestorBD.getInstance().insertar("insert into usuario(nombreUsuario, contraseña, tipo) values (01, 'Pepe', 'C');");
		}
		catch (SQLException e){}
		//Nulls
		try{
			//GestorUsuarios.getGestorUsuarios().modificarContraseña(null, null);
			GestorUsuarios.getGestorUsuarios().modificarContraseña((Integer) null, null);//modif-Sara
			fail("null, null");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
				rdo.next();
				assertEquals(rdo.getString("nombreUsuario"), 01);
				assertEquals(rdo.getString("contraseña"), "Pepe");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, null);
			fail("01, null");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
				rdo.next();
				assertEquals(rdo.getString("nombreUsuario"), 01);
				assertEquals(rdo.getString("contraseña"), "Pepe");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			//GestorUsuarios.getGestorUsuarios().modificarContraseña(null, "Francisco");
			GestorUsuarios.getGestorUsuarios().modificarContraseña((Integer) null, "Francisco");//modif-Sara
			fail("null, 'Francisco'");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
				rdo.next();
				assertEquals(rdo.getString("nombreUsuario"), 01);
				assertEquals(rdo.getString("contraseña"), "Pepe");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//Vacios
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, "");
			fail("null, ''");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
				rdo.next();
				assertEquals(rdo.getString("nombreUsuario"), 01);
				assertEquals(rdo.getString("contraseña"), "Pepe");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, " ");
			fail("null, ' '");
		}
		catch(GenericException e){
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
				rdo.next();
				assertEquals(rdo.getString("nombreUsuario"), 01);
				assertEquals(rdo.getString("contraseña"), "Pepe");
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//correcto
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, "francisco");
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
				rdo.next();
				assertEquals(rdo.getString("nombreUsuario"), 01);
				assertEquals(rdo.getString("contraseña"), "francisco");
				rdo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(GenericException e){
			fail("01, 'francisco'");
		}
	}
	
	//GestorCarreras
	//--obtenerCarreras()
	public void obtenerCarrerasTest(){
		LinkedList<String> rdo;
		//El campeonato no existe
		try{
			GestorCarreras.getGestorCarreras().obtenerCarreras("2013-01-20");
			fail("2013-01-20");
		}
		catch(GenericException e){}
		//El campeonato esta dado de baja
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2012-01-20', 'Alaska 12', FALSE);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2012-01-20");
			fail("2013-01-20");
		}
		catch(GenericException e){}
		catch(SQLException e){}
		//El campeonato no tiene carreras
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-02-20', 'Alaska 13', TRUE);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-02-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),0);
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El campeonato tiene una carrera inactiva
		try{
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-02-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-02-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),0);
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El campeonato tiene una carrera
		try{
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-02-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-02-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),1);
			assertEquals(rdo.get(1), "2013-02-21");
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El campeonato tiene mas carreras
		try{
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-02-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),2);
			assertEquals(rdo.get(1), "2013-01-21");
			assertEquals(rdo.get(2), "2013-01-22");
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
	}
	
	//--obtenerCalificaciones(pID_Carrera:int,pFechacarrera:Date, pID_Trineo:int)
	//  debuelve una lista con los dos valores de calificacion
	public void obtenerCalificacionesTest(){
		LinkedList<Integer> rdo = null;//modif-Sara(inicializado a null)
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//Valores null
		try{
			//rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,null,null);
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,null,(Integer) null);//modif-Sara
			fail("null, null, null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			//rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21",null,null);
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21",null,(Integer) null);//modif-Sara
			fail("'2013-01-21', null, null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			//rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,"2013-01-20",null);
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,"2013-01-20",(Integer) null);//modif-Sara
			fail("null, '2013-01-20', null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,null,02);
			fail("null, null, 01");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			//rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",null);
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",(Integer) null);//modif-Sara
			fail("'2013-01-21', '2013-01-20', null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21",null,02);
			fail("'2013-01-21', null, 01");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,"2013-01-20",02);
			fail("null,'2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La carrera no existe por el campeonato
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-03-30",02);
			fail("'2013-01-21','2013-03-30',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La carrera no existe por la fecha de carrera
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-23","2013-01-20",02);
			fail("'2013-01-23','2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La carrera no esta activa
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-20","2013-01-20",02);
			fail("'2013-01-20','2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//El trineo no existe
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",07);
			fail("'2013-01-21','2013-01-20',07");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//El trineo no esta activo
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",01);
			fail("'2013-01-21','2013-01-20',01");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La combinacion de carrera y trineo no se da
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-22","2013-01-20",02);
			fail("'2013-01-22','2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//Todo es correcto
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",02);
			assertNotNull(rdo);
			assertEquals(rdo.size(),2);
			assertEquals(rdo.get(0),new Integer(5));
			assertEquals(rdo.get(1),new Integer(10));
		}
		catch(GenericException e){
			fail("'2013-01-21','2013-01-20',02");
		}
	}
	
	//--guardarCambiosCalificacion(nuevaPos:int,nuevaPuntuacion:int)
	//                             Ademas el trineo y la carrera
	public void guardarCambiosCalificacionTest(){
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//Combinaciones de null
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,null,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null, (Integer)null, null,null,(Integer)null);//modif-Sara
			fail("null, null, null, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,null,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null, (Integer)null,"2013-01-21", null,(Integer)null);//modif-Sara
			fail("'2013-01-21', null, null, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,null,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null, (Integer)null, null,"2013-01-20",(Integer)null);//modif-Sara
			fail("null, '2013-01-20', null, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,null,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null ,(Integer)null, null,null,02);//modif-Sara
			fail("null, null, 02, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null, null, null,(Integer)null);//modif-Sara
			fail("null, null, null, 6, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,null, null, (Integer)null);//modif-Sara
			fail("null, null, null, null, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,null,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,(Integer)null,"2013-01-21", "2013-01-20",(Integer)null);//modif-Sara
			fail("'2013-01-21', '2013-01-20', null, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02, null, null);			
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,(Integer)null,"2013-01-21", null, 02);//modif-Sara
			fail("'2013-01-21', null, 02, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null,"2013-01-21", null, (Integer)null);//modif-Sara
			fail("'2013-01-20', null, null, 6, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,"2013-01-21",null,(Integer)null);//modif-Sara
			fail("'2013-01-21', null, null, null, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,null,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,(Integer)null,null, "2013-01-20", 02);//modif-Sara
			fail("null, '2013-01-20', 02, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null,null, "2013-01-20", (Integer)null);//modif-Sara
			fail("null, '2013-01-20', null, 6, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,null, "2013-01-20",(Integer)null);//modif-Sara
			fail("null, '2013-01-20', null, null, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null,null, null,02);//modif-Sara
			fail("null, null, 02, 6, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,null, null, 02);//modif-Sara
			fail("null, null, 02, null, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,null, null,(Integer)null);//modif-Sara
			fail("null, null, null, 6, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,null,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,(Integer)null,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 02, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null,"2013-01-21", "2013-01-20", (Integer)null);//modif-Sara
			fail("'2013-01-21', '2013-01-20', null, 6, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,"2013-01-21", "2013-01-20",(Integer)null);//modif-Sara
			fail("'2013-01-21', '2013-01-20', null, null, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null,"2013-01-21", null, 02);//modif-Sara
			fail("'2013-01-21', null, 02, 6, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,"2013-01-21", null, 02);//modif-Sara
			fail("'2013-01-21', null, 02, null, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-21", null,(Integer)null);//modif-Sara
			fail("'2013-01-21', null, null, 6, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null,null, "2013-01-20", 02);//modif-Sara
			fail("null, '2013-01-20, 02, 6, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,null, "2013-01-20", 02);//modif-Sara
			fail("null, '2013-01-20', 02, null, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,null, "2013-01-20", (Integer)null);//modif-Sara
			fail("null, '2013-01-20', null, 6, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,null, null, 02);//modif-Sara
			fail("null, null, 02, 6, 8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,6,null);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,(Integer)null,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("null, null, null, null, null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,null,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion((Integer)null,8,"2013-01-21", "2013-01-20", 02);//modi-fSara
			fail("'2013-01-21', '2013-01-20', 02,6,null");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-21", "2013-01-20", (Integer)null);//modif-Sara
			fail("'2013-01-21', '2013-01-20', null,6,8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-21", null, 02);//modif-Sara
			fail("'2013-01-21', null, 02,6,8");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,null, "2013-01-20", 02);//modif-Sara
			fail("null, '2013-01-20', 02,6,8");
		}
		catch(GenericException e){}
		//La carrera no existe (por carrera)
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-23", "2013-01-20", 02,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-23", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-23', '2013-01-20', 02,6,8");
		}
		catch(GenericException e){}
		//(Por Campeonato)
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-22", "2013-03-20", 02,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-22", "2013-03-20", 02);//modif-Sara
			fail("'2013-01-22', '2013-03-20', 02,6,8");
		}
		catch(GenericException e){}
		//El trineo no existe
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 07,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-21", "2013-01-20", 07);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 07,6,8");
		}
		catch(GenericException e){}
		//La combinacion carrera - trineo no coincide
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-22", "2013-01-20", 02,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-22", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 07,6,8");
		}
		catch(GenericException e){}
		//Datos raros
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,-1,3);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(-1,3,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 02,-1,3");
		}
		catch(GenericException e){
			//comprobar no cambios
			ResultSet rdo;
			try { //modif-Sara (try/catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),5);
				assertEquals(rdo.getInt("puntos"), 10);
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,6,-8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,-8,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 02,6,-8");
		}
		catch(GenericException e){
			//comprobar no cambio
			ResultSet rdo;
			try { //modif-Sara (try/catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),5);
				assertEquals(rdo.getInt("puntos"), 10);
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,-6,-8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(-6,-8,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 02,-6,-8");
		}
		catch(GenericException e){
			//comprobar no cambio
			ResultSet rdo;
			try {//modif-Sara (try/catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),5);
				assertEquals(rdo.getInt("puntos"), 10);
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,0,-8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(0,-8,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 02,0,-8");
		}
		catch(GenericException e){
			//comprobar no cambio
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),5);
				assertEquals(rdo.getInt("puntos"), 10);
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,-6,0);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(-6,0,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 02,6,-8");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo;
			try { //modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),5);
				assertEquals(rdo.getInt("puntos"), 10);
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/*
		try{ NO ES NECESARIO, ¿que caso excepcional representa?
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,0,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(0,8,"2013-01-21", "2013-01-20", 02);//modif-Sara
			fail("'2013-01-21', '2013-01-20', 02,6,-8");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo;
			try {//modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),5);
				assertEquals(rdo.getInt("puntos"), 10);
				rdo.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		*/
		//Correcto
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,0,0);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(0,0,"2013-01-21", "2013-01-20", 02);//modif-Sara
			//Comprobar cambio
			ResultSet rdo;
			try { //modif-Sara(try/catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),0);
				assertEquals(rdo.getInt("puntos"), 0);
				rdo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 02,0,0");
		}
		try{
			//GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,6,8);
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(6,8,"2013-01-21", "2013-01-20", 02);//modif-Sara
			//Comprobar cambio
			ResultSet rdo;
			try {//modif-Sara(try-catch)
				rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
				rdo.next();
				assertEquals(rdo.getInt("posicion"),6);
				assertEquals(rdo.getInt("puntos"), 8);
				rdo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 02,6,8");
		}
	}
	
	//--obtenerModalidadCarrera(El_IDCarrera, laFechaCamp)
	public void obtenerModalidadCarreraTest(){ //modif-Sara(devuelve un Linked List de idModalidad y nombreModalidad)
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		//int rdo;
		LinkedList<String> rdo;
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			//GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			//GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//nulls
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera(null, null);
			fail("null, null");
		}
		catch(GenericException e){}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-21", null);
			fail("'2013-01-21', null");
		}
		catch(GenericException e){}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera(null, "2013-01-20");
			fail("null, '2013-01-20'");
		}
		catch(GenericException e){}
		//La carrera no es valida (carrera / campeonato)
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-23", "2013-01-20");
			fail("'2013-01-23', '2013-01-20'");
		}
		catch(GenericException e){}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-21", "2013-01-23");
			fail("'2013-01-21', '2013-01-23'");
		}
		catch(GenericException e){}
		//correcto La carrera esta inactiva
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-20", "2013-01-20");
			//assertEquals(rdo, 01);
			assertEquals(rdo.get(0),"2013-01-20");
			assertEquals(rdo.get(1),"2013-01-21");
			assertEquals(rdo.get(2),"Alaska 2 13");
			assertEquals(rdo.get(3),"Alaska");
			assertEquals(rdo.get(4),"chungo");
			assertEquals(rdo.get(5),"01");
			
		}
		catch(GenericException e){
			fail("'2013-01-20', '2013-01-20'");
		}
		//correcto la carrera esta activa
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-21", "2013-01-20");
			//assertEquals(rdo, 01);
			assertEquals(rdo.get(0),"2013-01-20");
			assertEquals(rdo.get(1),"2013-01-21");
			assertEquals(rdo.get(2),"Alaska 2 13");
			assertEquals(rdo.get(3),"Alaska");
			assertEquals(rdo.get(4),"chungo");
			assertEquals(rdo.get(5),"01");
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20'");
		}
	}
	//--modificarDatosCarrera (El_ID_carrera:int, nombreM:String,  ID_ModalidadM: int , fechaCarrera:date)
	public void modificarDatosCarreraTest(){
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-06-20', 'Alpes 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (02, 'Ocho Perros', 8);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			//GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			//GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//Valores null
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera(null, null, null, null, null, null, null, null, null);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera(null, (Integer) null, null, null, null, null, null, null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, null);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 2 2013",(Integer) null, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		/*	modif-Sara (no sirve porque el método no recibe el estado)
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", null, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', null, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		*/
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", null, true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 2 2013",01, "Alaska", null,"2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', null, true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", null, "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013", 01, null,"chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', null, 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", null, "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( null, 01, "Alaska", "chunguillo","2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', null, 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", null, "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", null);//modif-Sara
			fail("'2013-01-21', null, 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", null, "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 2 2013",01, "Alaska", "chunguillo","2013-01-21", "2013-01-20", null, "2013-01-20");//modif-Sara
			fail("null, '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", null, "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", "chunguillo", "2013-01-21", null, "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera(null, "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", "chunguillo", null, "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//Valores vacios
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 2 2013",01, "Alaska", "chunguillo", "", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013", 01,"Alaska", "chunguillo", "2013-01-21", "", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", "chunguillo","2013-01-21", "2013-01-20", "", "2013-01-20");//modif-Sara
			fail("'', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "");
			fail("'2013-01-21', '', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska",01,"chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "");//modif-Sara
			fail("'2013-01-21', '2013-01-20', '', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', '', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", "", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', '', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
	
		
		
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera(" ", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 2 2013",01, "Alaska", "chunguillo", " ", "2013-01-20", "2013-01-21", "2013-01-20" );//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		
		
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 2 2013",01,"Alaska", "chunguillo", "2013-01-21", " ", "2013-01-21", "2013-01-20");
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		
		
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", " ", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", " ", "2013-01-20");//modif_Sara
			fail("' ', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 2 2013",01, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", " ");//modif-Sara
			fail("'2013-01-21', ' ', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		
		
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( " ",01, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', ' ', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		
		
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, " ", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', ' ', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",01, "Alaska", " ", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', ' ', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		
		//La modalidad de la carrera no existe
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 07);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 2 2013",07, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//El nombre ya existe
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 1 2013",01, "Alaska", "chunguillo","2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//La fecha antigua no coincide
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-19", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 1 2013", 01,"Alaska", "chunguillo",  "2013-01-21", "2013-01-20","2013-01-19", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-02-20", "2013-01-21", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 1 2013",01, "Alaska", "chunguillo", "2013-01-21", "2013-02-20", "2013-01-21", "2013-01-20");//modif - Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//La nueva fecha esta ocupada
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-20", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 1 2013",01, "Alaska", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-20", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//El nuevo campeonato no existe
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-02-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 1 2013",01, "Alaska", "chunguillo",  "2013-01-21", "2013-02-20","2013-01-21", "2013-01-20");//modif-Sara
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//correctos
		//Modificar el resto
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 3 2013", "Alaska USA", "chunguillo", true, 02);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 3 2013",02, "Alaska USA", "chunguillo", "2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20");//modif-Sara
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Alaska 3 2013");
			assertEquals(rdo.getString("lugar"), "Alaska USA");
			assertEquals(rdo.getString("recorrido"), "chunguillo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 'Alaska 3 2013', 'Alaska USA', 'chunguillo', true, 07");
		}
		//Modificar la fecha
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-26", "2013-01-20", "Alaska 3 2013", "Alaska USA", "chunguillo", true, 02);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("Alaska 3 2013",02, "Alaska USA", "chunguillo",  "2013-01-26", "2013-01-20","2013-01-21", "2013-01-20");//modif-Sara
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-26' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-26");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 'Alaska 3 2013', 'Alaska USA', 'chunguillo', true, 07");
		}
		/*modif-Sara(no se cambia el estado porque no lo recibe como parametro)
		//CAmbiamos campeonato y estado 
		try{
			//GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-26", "2013-01-20", "2013-01-26", "2013-06-20", "Alaska 3 2013", "Alaska USA", "chunguillo", false, 02);
			GestorCarreras.getGestorCarreras().modificarDatosCarrera( "Alaska 3 2013",02, "Alaska USA", "chunguillo", "2013-01-26", "2013-01-20", "2013-01-26", "2013-06-20");//modif-Sara
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-26' and fechaCampeonato='2013-06-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-06-20");
			assertEquals(rdo.getString("nombre"), "Alaska 3 2013");
			assertEquals(rdo.getString("lugar"), "Alaska USA");
			assertEquals(rdo.getString("recorrido"), "chunguillo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 02);
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 'Alaska 3 2013', 'Alaska USA', 'chunguillo', true, 07");
		}
		*/
	}
	
	//GestorTrineos
	//--obtenerTrineosDeLaCarrera(lafechacarrera)
	public void obtenerTrineosDeLaCarreraTest(){
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//nulls
		try{
			GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera(null, null);
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-20", null);
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera(null, "2013-01-20");
			fail("null");
		}
		catch(GenericException e){}
		//no existe la carrera (carrera/campeonato)--> no hay trineos en la carrera
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-20", "2013-01-20");//modif-Sara (LikedList<Integer>-LinkedList<String>)
			assertNotNull(rdo);
			assertEquals(rdo.size(), 0);
		}
		catch(GenericException e){
			fail("null");
		}		
		//Hay un trineo
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-21", "2013-01-20");//modif-Sara (LikedList<Integer>-LinkedList<String>)
			assertNotNull(rdo);
			assertEquals(rdo.size(), 1);
			assertEquals(rdo.get(0), new Integer(2));
			//mirar lista
		}
		catch(GenericException e){
			fail("null");
		}
		try{
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',01,0,00);");
		}
		catch(SQLException e){}
		//Hay dos trineos
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-21", "2013-01-20");//modif-Sara (LikedList<Integer>-LinkedList<String>)
			assertNotNull(rdo);
			assertEquals(rdo.size(), 2);
			assertEquals(rdo.get(0), new Integer(2));
			assertEquals(rdo.get(1), new Integer(1));
			//mirar lista
		}
		catch(GenericException e){
			fail("null");
		}
	}
	//--modificarTrineo(ID_Trineo,modelo)
	public void modificarTrineoTest(){
		try{
			//GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			//GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', FALSE);");
			//GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//nulls
		try{
			//GestorTrineos.getGestorTrineos().modificarTrineo(null, null);
			GestorTrineos.getGestorTrineos().modificarTrineo((Integer)null,(Integer)null,null,null);//modif-Sara
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo((Integer)null,01, "fabricante1","2000-01-01");//modif-Sara
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01,(Integer)null, "fabricante1","2000-01-01");
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01,01, null,"2000-01-01");//nuevo-Sara
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01,01, "fabricante1",null);//nuevo-Sara
			fail("null");
		}
		catch(GenericException e){}
		//id_trineo no existe
		try{
			//GestorTrineos.getGestorTrineos().modificarTrineo(07, "fabricante 1");
			GestorTrineos.getGestorTrineos().modificarTrineo(07, 01, "fabricante1","2000-01-01");//modif-Sara
			fail("null");
		}
		catch(GenericException e){}
		//No blanco o espacios  //modif-Sara (todo)
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, 01,"", "2000-01-01");
			fail("null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 01);
			assertEquals(rdo.getInt("id_club"), 01);
			assertEquals(rdo.getString("fabricante"), "fabricante1");
			assertEquals(rdo.getString("fechaFabricacion"), "2000-01-01");
			rdo.close();
		}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, 01,"fabricante1", "");
			fail("null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 01);
			assertEquals(rdo.getInt("id_club"), 01);
			assertEquals(rdo.getString("fabricante"), "fabricante1");
			assertEquals(rdo.getString("fechaFabricacion"), "2000-01-01");
			rdo.close();
		}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, 01," ", "2000-01-01");
			fail("null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 01);
			assertEquals(rdo.getInt("id_club"), 01);
			assertEquals(rdo.getString("fabricante"), "fabricante1");
			assertEquals(rdo.getString("fechaFabricacion"), "2000-01-01");
			rdo.close();
		}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, 01,"fabricante1", " ");
			fail("null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 01);
			assertEquals(rdo.getInt("id_club"), 01);
			assertEquals(rdo.getString("fabricante"), "fabricante1");
			assertEquals(rdo.getString("fechaFabricacion"), "2000-01-01");
			rdo.close();
		}
		//correcto
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01,01,"fabricante2","2000-02-01");
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 01);
			assertEquals(rdo.getInt("id_club"), 01);
			assertEquals(rdo.getString("fabricante"), "fabricante2");
			assertEquals(rdo.getString("fechaFabricacion"), "2000-02-01");
			rdo.close();
		}
		catch(GenericException e){
			fail("null");
		}
	}
	
	public void obtenerDatosTrineoTest(){//nuevo-Sara
		try{
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', FALSE);");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		//nulls
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerDatosTrineo((Integer) null);
			fail("");
		}
		catch(GenericException e){}
		
		//El trineo no existe
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerDatosTrineo(07);
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerDatosTrineo(-15);
			fail("");
		}
		catch(GenericException e){}
		//El trineo existe activo
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerDatosTrineo(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 4);
			assertEquals(rdo.get(0), "01");
			assertEquals(rdo.get(1), "01");
			assertEquals(rdo.get(2), "Fabricante1");
			assertEquals(rdo.get(3), "2000-01-01");
		}
		catch(GenericException e){
			fail("");
		}
		//El trineo existe inactivo
		try{
			LinkedList<String> rdo = GestorTrineos.getGestorTrineos().obtenerDatosTrineo(02);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 4);
			assertEquals(rdo.get(0), "02");
			assertEquals(rdo.get(1), "01");
			assertEquals(rdo.get(2), "Fabricante1");
			assertEquals(rdo.get(3), "2000-01-01");
		}
		catch(GenericException e){
			fail("");
		}
	}
	
	//GestorPerros
	//--cargarDatosPerro():linkedkist<String>
	public void obtenerDatosPerroTest(){//modif-Sara(el método es obtenerDatosPerro)
		try{
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (01, 'perro1', 'Husky', 'M', TRUE);");
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (02, 'perro2', 'Husky', 'M', FALSE);");
		}
		catch(SQLException e){}
		
		//nulls
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().obtenerDatosPerro((Integer) null);
			fail("");
		}
		catch(GenericException e){}
		
		//El perro no existe
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().obtenerDatosPerro(07);
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().obtenerDatosPerro(-15);
			fail("");
		}
		catch(GenericException e){}
		//El perro existe activo
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().obtenerDatosPerro(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "perro1");
			assertEquals(rdo.get(1), "Husky");
			assertEquals(rdo.get(2), "M");
		}
		catch(GenericException e){
			fail("");
		}
		//El perro existe inactivo
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().obtenerDatosPerro(02);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "perro2");
			assertEquals(rdo.get(1), "Husky");
			assertEquals(rdo.get(2), "M");
		}
		catch(GenericException e){
			fail("");
		}
	}
	//--modificarDatosPerro(pID_Perro:Int, nombreModif:String, razaModif:String, sexoModif :String)
	public void modificarDatosPerroTest(){
		try{
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (02, 'perro2', 'Husky', 'M', TRUE);");
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (01, 'perro1', 'Husky', 'M', TRUE);");
		}
		catch(SQLException e){}
		//nulls
		try{
			//GestorPerros.getGestorPerros().modificarDatosPerro(null, null, null, null);
			GestorPerros.getGestorPerros().modificarDatosPerro((Integer) null, null, null, null);//modif-Sara
			fail("");
		}
		catch(GenericException e){}
		try{
			//GestorPerros.getGestorPerros().modificarDatosPerro(null, "perro 1", "HUSKY", "M");
			GestorPerros.getGestorPerros().modificarDatosPerro((Integer) null, "perro 1", "HUSKY", "M");//modif-Sara
			fail("");
		}
		catch(GenericException e){}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, null, "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", null, "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", null);
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//El id no existe
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(07, "perro 1", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//El nombre ya esta cogido
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro2", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//vacios y espacios
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, " ", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "", "M");
			fail("");
		}
		catch(GenericException e){}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", " ", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", "");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", " ");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//correcto
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", "H");
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro 1");
			assertEquals(rdo.getString("raza"), "HUSKY");
			assertEquals(rdo.getString("sexo"), "H");
			rdo.close();
		}
		catch(GenericException e){
			fail("");
		}
		
	}
	public void cargarDatosPerroTest(){
		
		LinkedList<String> rdo;
		//El dueño no existe
		try{
			GestorPerros.getGestorPerros().cargarDatosPerro(1111);
			fail("2013-01-20");
		}
		catch(GenericException e){}
		//El dueño esta dado de baja
		GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (02, 'dueño2', 'apellido2', FALSE);");
		try{
			rdo = GestorPerros.getGestorPerros().cargarDatosPerro(02);
			fail("2013-01-20");
		}
		catch(GenericException e){}
		catch(SQLException e){}
		//El dueño no tiene perros
		GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (01, 'dueño1', 'apellido1', TRUE);");
		try{
			rdo = GestorPerros.getGestorPerros().cargarDatosPerro(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(),0);
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El dueño tiene un perro inactivo
		try{
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (01, 'perro1', 'Husky', 'M', FALSE);");
			GestorBD.getInstance().insertar("insert into perropertenece values('2013-01-01',01,01,'2013-02-02')");
			rdo = GestorPerros.getGestorPerros().cargarDatosPerro(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(),0);
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El dueño tiene un perro
		try{
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (02, 'perro2', 'Husky', 'M', TRUE);");
			GestorBD.getInstance().insertar("insert into perropertenece values('2013-01-01',01,02,'2013-02-02')");
			rdo = rdo = GestorPerros.getGestorPerros().cargarDatosPerro(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(),1);
			assertEquals(rdo.get(1), "2013-02-21");
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El dueño tiene mas perros
		try{
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (03, 'perro3', 'Husky', 'M', TRUE);");
			GestorBD.getInstance().insertar("insert into perropertenece values('2013-01-01',01,03,'2013-02-02')");
			rdo = rdo = GestorPerros.getGestorPerros().cargarDatosPerro(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(),2);
			assertEquals(rdo.get(1), "2013-01-21");
			assertEquals(rdo.get(2), "2013-01-22");
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
	}
	
	//GestorDueños
	//--modificarDatosDueño ( elNumFederado:Int, NombreModif: String, apellidoModif:String)
	public void modificarDatosDueñoTest(){
		try{
			GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (01, 'dueño1', 'apellido1', TRUE);");
			GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (02, 'dueño2', 'apellido2', TRUE);");
		}
		catch(SQLException e){}
		//nulls
		try{
			//GestorDueños.getGestorDueños().modificarDatosDueño(null, null, null);
			GestorDueños.getGestorDueños().modificarDatosDueño((Integer) null, null, null);//modif-Sara
		}
		catch(GenericException e){}
		try{
			//GestorDueños.getGestorDueños().modificarDatosDueño(null, "DUEÑO 1", "APELLIDO 1");
			GestorDueños.getGestorDueños().modificarDatosDueño((Integer) null, "DUEÑO 1", "APELLIDO 1");//modif-Sara
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(01, null, "APELLIDO 1");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(01, "DUEÑO 1", null);
		}
		catch(GenericException e){}
		//El numfederado no existe
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(07, "DUEÑO 1", "APELLIDO 1");
		}
		catch(GenericException e){}
		//Vacios y espacios
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(01, "", "APELLIDO 1");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(01, " ", "APELLIDO 1");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(01, "DUEÑO 1", "");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(01, "DUEÑO 1", " ");
		}
		catch(GenericException e){}
		//correcto
		try{
			GestorDueños.getGestorDueños().modificarDatosDueño(01, "DUEÑO 1", "APELLIDO 1");
		}
		catch(GenericException e){}
	}
	
	public void obtenerDatosDueñoTest(){//nuevo-Sara
		try{
			GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (01, 'dueño1', 'apellido1', TRUE);");
			GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (02, 'dueño2', 'apellido2', FALSE);");
			}
		catch(SQLException e){}
		//Nulls
		try{
			LinkedList<String> rdo = GestorDueños.getGestorDueños().obtenerDatosDueño((Integer) null);
			fail("");
		}
		catch(GenericException e){}
		//Blancos y vacios
		/*
			LinkedList<String> rdo = GestorDueños.getGestorDueños().obtenerDatosDueño("");
			fail("");
		}
		catch(GenericException e){}
		*/
		/*
		try{
			LinkedList<String> rdo = GestorDueños.getGestorDueños().obtenerDatosDueño(" ");
			fail("");
		}
		catch(GenericException e){}
		*/
		//No existe el dueño
		try{
			LinkedList<String> rdo = GestorDueños.getGestorDueños().obtenerDatosDueño(111);;
			fail("");
		}
		catch(GenericException e){}
	
		//Existe el dueño activo
		try{
			LinkedList<String> rdo = GestorDueños.getGestorDueños().obtenerDatosDueño(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "01");
			assertEquals(rdo.get(1), "dueño1");
			assertEquals(rdo.get(2), "apellido1");
		}
		catch(GenericException e){
			fail("");
		}
		//Existe el dueño inactivo
		try{
			LinkedList<String> rdo = GestorDueños.getGestorDueños().obtenerDatosDueño(02);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "01");
			assertEquals(rdo.get(1), "dueño1");
			assertEquals(rdo.get(2), "apellido1");
		}
		catch(GenericException e){
			fail("");
		}
	}
	
	//GestorCampeonatos
	//--cargarDatosCampeonato()//modif-Sara(el metodo se llama obtenerDatosCampeonato)
	public void obtenerDatosCampeonatoTest(){
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2012-01-20', 'Alaska 12', FALSE);");
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
		}
		catch(SQLException e){}
		//Nulls
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().obtenerDatosCampeonato(null);
			fail("");
		}
		catch(GenericException e){}
		//Blancos y vacios
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().obtenerDatosCampeonato("");
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().obtenerDatosCampeonato(" ");
			fail("");
		}
		catch(GenericException e){}
		//No existe el campeonato
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().obtenerDatosCampeonato("2013-01-15");
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().obtenerDatosCampeonato("patata");
			fail("");
		}
		catch(GenericException e){}
		//Existe el campeonato activo
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().obtenerDatosCampeonato("2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(), 2);
			assertEquals(rdo.get(0), "2013-01-20");
			assertEquals(rdo.get(1), "Alaska 13");
		}
		catch(GenericException e){
			fail("");
		}
		//Existe el campeonato inactivo
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().obtenerDatosCampeonato("2012-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(), 2);
			assertEquals(rdo.get(0), "2012-01-20");
			assertEquals(rdo.get(1), "Alaska 12");
		}
		catch(GenericException e){
			fail("");
		}
	}
	
	public void modificarDatosCampeonato(){
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-06-20', 'Alpes 13', TRUE);");
		}
		catch(SQLException e){}
		//Valores null
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(null, null, null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato("2013-01-20", null, null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(null, "campeonato", null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(null, null, "2013-01-20");//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		
		//datos vacios o espacios
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato("", null, null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(null, "", null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(null, null, "");//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(" ", null, null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(null, " ", null);//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato(null, null, " ");//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}

		//La fecha antigua no coincide
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato("2013-01-20", "Alaska 13", "2013-01-21");//modif-Sara
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 13");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
	
		//correctos
		//Modificar el resto
		try{
			GestorCampeonatos.getGestorCampeonatos().modificarDatosCampeonato("2013-01-22", "Alaska 14", "2013-01-21");//modif-Sara
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select fechacampeonato, nombre, estado from campeonato where fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-22");
			assertEquals(rdo.getString("nombre"), "Alaska 14");
			assertEquals(rdo.getBoolean("estado"), true);
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 'Alaska 3 2013', 'Alaska USA', 'chunguillo', true, 07");
		}
		
	}

/*	
	
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		try{
			GestorBD.getInstance().borrar("delete from ventas           where 1=1;");
			GestorBD.getInstance().borrar("delete from utiliza          where 1=1;");
			GestorBD.getInstance().borrar("delete from recorridocarrera where 1=1;");
			GestorBD.getInstance().borrar("delete from perropertenece   where 1=1;");
			GestorBD.getInstance().borrar("delete from llevara          where 1=1;");
			GestorBD.getInstance().borrar("delete from dueño            where 1=1;");
			GestorBD.getInstance().borrar("delete from apareamiento     where 1=1;");
			GestorBD.getInstance().borrar("delete from perro            where 1=1;");
			GestorBD.getInstance().borrar("delete from trineo           where 1=1;");
			GestorBD.getInstance().borrar("delete from club             where 1=1;");
			GestorBD.getInstance().borrar("delete from carrera          where 1=1;");
			GestorBD.getInstance().borrar("delete from campeonato       where 1=1;");
			GestorBD.getInstance().borrar("delete from modalidad        where 1=1;");
			GestorBD.getInstance().borrar("delete from usuario          where 1=1;");
		}
		catch (SQLException e){}
	}

	//GestorClubs
	//--modificarDatosClub(pID_Club:Int, pNombre:String, pPatrocinador:String)
	public void modificarDatosClubTest(){
		try{
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'Uno', 'Uno Uno', FALSE);");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		//Todos null
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(null, null, null);
			fail("null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		
		//Combinacion de nulls
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, null, null);
			fail("01, null, null");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(null, "DOS", null);
			fail("null, 'DOS', null");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(null, null, "Dos Dos");
			fail("null, null, 'Dos Dos'");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "DOS", null);
			fail("01, 'DOS', null");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, null, "Dos dos");
			fail("01, null, 'Dos dos'");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		//datos en blanco
		//No deberia de dejar introducir datos vacios o en blanco (solo espacios)
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "", "");
			fail("01, '', ''");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, " ", " ");
			fail("01, ' ', ' '");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "Dos", "");
			fail("01, 'Dos', ''");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "Dos", " ");
			fail("01, 'Dos', ' '");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
		}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "", "Dos Dos");
			fail("01, '', 'Dos Dos'");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
			}
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, " ", "Dos Dos");
			fail("01, ' ', 'Dos Dos'");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Uno");
			assertEquals(rdo.getString("patrocinador"), "Uno Uno");
			rdo.close();
			}
		//correcto
		try{
			GestorClubs.getGestorClubs().modificarDatosClub(01, "Dos", "Dos dos");
			ResultSet rdo= GestorBD.getInstance().consulta("select nombre, patrocinador from club where id_Club=01;");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Dos");
			assertEquals(rdo.getString("patrocinador"), "Dos dos");
			rdo.close();
		}
		catch(GenericException e){
			fail("01, 'Dos', 'Dos dos'");
		}
	}
	
	//GestorUsuarios
	//--modificarContraseña(elIDClub,nuevopass)
	//--modificarContraseña(elNumFederado, nuevoPass)
	public void modificarContraseñaTest(){
		try{
			GestorBD.getInstance().insertar("insert into usuario(nombreUsuario, contraseña, tipo) values (01, 'Pepe', 'C');");
		}
		catch (SQLException e){}
		//Nulls
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(null, null);
			fail("null, null");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
			rdo.next();
			assertEquals(rdo.getString("nombreUsuario"), 01);
			assertEquals(rdo.getString("contraseña"), "Pepe");
			rdo.close();
		}
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, null);
			fail("01, null");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
			rdo.next();
			assertEquals(rdo.getString("nombreUsuario"), 01);
			assertEquals(rdo.getString("contraseña"), "Pepe");
			rdo.close();
		}
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(null, "Francisco");
			fail("null, 'Francisco'");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
			rdo.next();
			assertEquals(rdo.getString("nombreUsuario"), 01);
			assertEquals(rdo.getString("contraseña"), "Pepe");
			rdo.close();
		}
		//Vacios
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, "");
			fail("null, ''");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
			rdo.next();
			assertEquals(rdo.getString("nombreUsuario"), 01);
			assertEquals(rdo.getString("contraseña"), "Pepe");
			rdo.close();
		}
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, " ");
			fail("null, ' '");
		}
		catch(GenericException e){
			ResultSet rdo= GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
			rdo.next();
			assertEquals(rdo.getString("nombreUsuario"), 01);
			assertEquals(rdo.getString("contraseña"), "Pepe");
			rdo.close();
		}
		//correcto
		try{
			GestorUsuarios.getGestorUsuarios().modificarContraseña(01, "francisco");
			ResultSet rdo= GestorBD.getInstance().consulta("select nombreUsuario, contraseña from usuario where nombreUsuario=01;");
			rdo.next();
			assertEquals(rdo.getString("nombreUsuario"), 01);
			assertEquals(rdo.getString("contraseña"), "francisco");
			rdo.close();
		}
		catch(GenericException e){
			fail("01, 'francisco'");
		}
	}
	
	//GestorCarreras
	//--obtenerCarreras()
	public void obtenerCarrerasTest(){
		LinkedList<String> rdo;
		//El campeonato no existe
		try{
			GestorCarreras.getGestorCarreras().obtenerCarreras("2013-01-20");
			fail("2013-01-20");
		}
		catch(GenericException e){}
		//El campeonato esta dado de baja
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2012-01-20', 'Alaska 12', FALSE);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2012-01-20");
			fail("2013-01-20");
		}
		catch(GenericException e){}
		catch(SQLException e){}
		//El campeonato no tiene carreras
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),0);
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El campeonato tiene una carrera inactiva
		try{
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),0);
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El campeonato tiene una carrera
		try{
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),1);
			assertEquals(rdo.get(1), "2013-01-21");
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
		//El campeonato tiene una carrera
		try{
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			rdo = GestorCarreras.getGestorCarreras().obtenerCarreras("2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(),2);
			assertEquals(rdo.get(1), "2013-01-21");
			assertEquals(rdo.get(2), "2013-01-22");
		}
		catch(GenericException e){
			fail("2013-01-20");
		}
		catch(SQLException e){}
	}
	
	//--obtenerCalificaciones(pID_Carrera:int,pFechacarrera:Date, pID_Trineo:int)
	//  debuelve una lista con los dos valores de calificacion
	public void obtenerCalificacionesTest(){
		LinkedList<Integer> rdo;
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//Valores null
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,null,null);
			fail("null, null, null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21",null,null);
			fail("'2013-01-21', null, null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,"2013-01-20",null);
			fail("null, '2013-01-20', null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,null,02);
			fail("null, null, 01");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",null);
			fail("'2013-01-21', '2013-01-20', null");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21",null,02);
			fail("'2013-01-21', null, 01");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones(null,"2013-01-20",02);
			fail("null,'2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La carrera no existe por el campeonato
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-03-30",02);
			fail("'2013-01-21','2013-03-30',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La carrera no existe por la fecha de carrera
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-23","2013-01-20",02);
			fail("'2013-01-23','2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La carrera no esta activa
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-20","2013-01-20",02);
			fail("'2013-01-20','2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//El trineo no existe
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",07);
			fail("'2013-01-21','2013-01-20',07");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//El trineo no esta activo
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",01);
			fail("'2013-01-21','2013-01-20',01");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//La combinacion de carrera y trineo no se da
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-22","2013-01-20",02);
			fail("'2013-01-22','2013-01-20',02");
		}
		catch(GenericException e){
			assertNull(rdo);
		}
		//Todo es correcto
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerCalificaciones("2013-01-21","2013-01-20",02);
			assertNotNull(rdo);
			assertEquals(rdo.size(),2);
			assertEquals(rdo.get(0),new Integer(5));
			assertEquals(rdo.get(1),new Integer(10));
		}
		catch(GenericException e){
			fail("'2013-01-21','2013-01-20',02");
		}
	}
	
	//--guardarCambiosCalificacion(nuevaPos:int,nuevaPuntuacion:int)
	//                             Ademas el trineo y la carrera
	public void guardarCambiosCalificacionTest(){
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//Combinaciones de null
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,null,null);
			fail("null, null, null, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,null,null);
			fail("'2013-01-21', null, null, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,null,null);
			fail("null, '2013-01-20', null, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,null,null);
			fail("null, null, 02, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,6,null);
			fail("null, null, null, 6, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,null,8);
			fail("null, null, null, null, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,null,null);
			fail("'2013-01-21', '2013-01-20', null, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02,null,null);
			fail("'2013-01-21', null, 02, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,6,null);
			fail("'2013-01-20', null, null, 6, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,null,8);
			fail("'2013-01-21', null, null, null, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,null,null);
			fail("null, '2013-01-20', 02, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,6,null);
			fail("null, '2013-01-20', null, 6, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,null,8);
			fail("null, '2013-01-20', null, null, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,6,null);
			fail("null, null, 02, 6, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,null,8);
			fail("null, null, 02, null, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, null,6,8);
			fail("null, null, null, 6, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,null,null);
			fail("'2013-01-21', '2013-01-20', 02, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,6,null);
			fail("'2013-01-21', '2013-01-20', null, 6, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,null,8);
			fail("'2013-01-21', '2013-01-20', null, null, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02,6,null);
			fail("'2013-01-21', null, 02, 6, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02,null,8);
			fail("'2013-01-21', null, 02, null, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, null,6,8);
			fail("'2013-01-21', null, null, 6, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,6,null);
			fail("null, '2013-01-20, 02, 6, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,null,8);
			fail("null, '2013-01-20', 02, null, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", null,6,8);
			fail("null, '2013-01-20', null, 6, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, null, 02,6,8);
			fail("null, null, 02, 6, 8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,6,null);
			fail("null, null, null, null, null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,null,8);
			fail("'2013-01-21', '2013-01-20', 02,6,null");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", null,6,8);
			fail("'2013-01-21', '2013-01-20', null,6,8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", null, 02,6,8);
			fail("'2013-01-21', null, 02,6,8");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion(null, "2013-01-20", 02,6,8);
			fail("null, '2013-01-20', 02,6,8");
		}
		catch(GenericException e){}
		//La carrera no existe (por carrera)
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-23", "2013-01-20", 02,6,8);
			fail("'2013-01-23', '2013-01-20', 02,6,8");
		}
		catch(GenericException e){}
		//(Por Campeonato)
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-22", "2013-03-20", 02,6,8);
			fail("'2013-01-22', '2013-03-20', 02,6,8");
		}
		catch(GenericException e){}
		//El trineo no existe
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 07,6,8);
			fail("'2013-01-21', '2013-01-20', 07,6,8");
		}
		catch(GenericException e){}
		//La combinacion carrera - trineo no coincide
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-22", "2013-01-20", 02,6,8);
			fail("'2013-01-21', '2013-01-20', 07,6,8");
		}
		catch(GenericException e){}
		//Datos raros
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,-1,3);
			fail("'2013-01-21', '2013-01-20', 02,-1,3");
		}
		catch(GenericException e){
			//comprobar no cambios
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),5);
			assertEquals(rdo.getInt("puntos"), 10);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,6,-8);
			fail("'2013-01-21', '2013-01-20', 02,6,-8");
		}
		catch(GenericException e){
			//comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),5);
			assertEquals(rdo.getInt("puntos"), 10);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,-6,-8);
			fail("'2013-01-21', '2013-01-20', 02,-6,-8");
		}
		catch(GenericException e){
			//comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),5);
			assertEquals(rdo.getInt("puntos"), 10);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,0,-8);
			fail("'2013-01-21', '2013-01-20', 02,0,-8");
		}
		catch(GenericException e){
			//comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),5);
			assertEquals(rdo.getInt("puntos"), 10);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,-6,0);
			fail("'2013-01-21', '2013-01-20', 02,6,-8");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),5);
			assertEquals(rdo.getInt("puntos"), 10);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,0,8);
			fail("'2013-01-21', '2013-01-20', 02,6,-8");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),5);
			assertEquals(rdo.getInt("puntos"), 10);
			rdo.close();
		}
		//Correcto
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,0,0);
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),0);
			assertEquals(rdo.getInt("puntos"), 0);
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 02,0,0");
		}
		try{
			GestorCarreras.getGestorCarreras().guardarCambiosCalificacion("2013-01-21", "2013-01-20", 02,6,8);
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select posicion, puntos from recorridoCarrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20' and id_trineo=02;");
			rdo.next();
			assertEquals(rdo.getInt("posicion"),6);
			assertEquals(rdo.getInt("puntos"), 8);
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 02,6,8");
		}
	}
	
	//--obtenerModalidadCarrera(El_IDCarrera, laFechaCamp)
	public void obtenerModalidadCarreraTest(){
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		int rdo;
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			//GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			//GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//nulls
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera(null, null);
			fail("null, null");
		}
		catch(GenericException e){}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-21", null);
			fail("'2013-01-21', null");
		}
		catch(GenericException e){}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera(null, "2013-01-20");
			fail("null, '2013-01-20'");
		}
		catch(GenericException e){}
		//La carrera no es valida (carrera / campeonato)
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-23", "2013-01-20");
			fail("'2013-01-23', '2013-01-20'");
		}
		catch(GenericException e){}
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-21", "2013-01-23");
			fail("'2013-01-21', '2013-01-23'");
		}
		catch(GenericException e){}
		//correcto La carrera esta inactiva
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-20", "2013-01-20");
			assertEquals(rdo, 01);
		}
		catch(GenericException e){
			fail("'2013-01-20', '2013-01-20'");
		}
		//correcto la carrera esta activa
		try{
			rdo = GestorCarreras.getGestorCarreras().obtenerModalidadCarrera("2013-01-21", "2013-01-20");
			assertEquals(rdo, 01);
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20'");
		}
	}
	//--modificarDatosCarrera (El_ID_carrera:int, nombreM:String,  ID_ModalidadM: int , fechaCarrera:date)
	public void modificarDatosCarreraTest(){
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-06-20', 'Alpes 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (02, 'Ocho Perros', 8);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			//GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);");
			//GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			//GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//Valores null
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera(null, null, null, null, null, null, null, null, null);
			fail("null, null, null, null, null, null, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, null);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", null, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', null, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", null, true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', null, true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", null, "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', null, 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", null, "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', null, 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", null, "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', null, 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", null, "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("null, '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", null, "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera(null, "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//Valores vacios
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', '', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', '', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', '', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera(" ", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", " ", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", " ", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("' ', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", " ", "Alaska 2 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', ' ', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", " ", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', ' ', 'Alaska', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", " ", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', ' ', 'chunguillo', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", " ", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', ' ', true, 01");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//La modalidad de la carrera no existe
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 2 2013", "Alaska", "chunguillo", true, 07);
			fail("'2013-01-21', '2013-01-20', 'Alaska 2 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//El nombre ya existe
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//La fecha antigua no coincide
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-19", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-02-20", "2013-01-21", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//La nueva fecha esta ocupada
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-20", "2013-01-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//El nuevo campeonato no existe
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-02-20", "Alaska 1 2013", "Alaska", "chunguillo", true, 01);
			fail("'2013-01-21', '2013-01-20', 'Alaska 1 2013', 'Alaska', 'chunguillo', true, 07");
		}
		catch(GenericException e){
			//Comprobar no cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-21");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			assertEquals(rdo.getString("nombre"), "Alaska 2 13");
			assertEquals(rdo.getString("lugar"), "Alaska");
			assertEquals(rdo.getString("recorrido"), "Chungo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		//correctos
		//Modificar el resto
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-21", "2013-01-20", "Alaska 3 2013", "Alaska USA", "chunguillo", true, 02);
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-21' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("nombre"), "Alaska 3 2013");
			assertEquals(rdo.getString("lugar"), "Alaska USA");
			assertEquals(rdo.getString("recorrido"), "chunguillo");
			assertEquals(rdo.getBoolean("estado"), true);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 'Alaska 3 2013', 'Alaska USA', 'chunguillo', true, 07");
		}
		//Modificar la fecha
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-21", "2013-01-20", "2013-01-26", "2013-01-20", "Alaska 3 2013", "Alaska USA", "chunguillo", true, 02);
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-26' and fechaCampeonato='2013-01-20';");
			rdo.next();
			assertEquals(rdo.getString("fechaCarrera"), "2013-01-26");
			assertEquals(rdo.getString("fechacampeonato"), "2013-01-20");
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 'Alaska 3 2013', 'Alaska USA', 'chunguillo', true, 07");
		}
		//CAmbiamos campeonato y estado
		try{
			GestorCarreras.getGestorCarreras().modificarDatosCarrera("2013-01-26", "2013-01-20", "2013-01-26", "2013-06-20", "Alaska 3 2013", "Alaska USA", "chunguillo", false, 02);
			//Comprobar cambio
			ResultSet rdo = GestorBD.getInstance().consulta("select FechaCarrera, fechacampeonato, nombre, lugar, recorrido, estado, id_modalidad from carrera where fechaCarrera='2013-01-26' and fechaCampeonato='2013-06-20';");
			rdo.next();
			assertEquals(rdo.getString("fechacampeonato"), "2013-06-20");
			assertEquals(rdo.getString("nombre"), "Alaska 3 2013");
			assertEquals(rdo.getString("lugar"), "Alaska USA");
			assertEquals(rdo.getString("recorrido"), "chunguillo");
			assertEquals(rdo.getBoolean("estado"), false);
			assertEquals(rdo.getInt("id_modalidad"), 01);
			rdo.close();
		}
		catch(GenericException e){
			fail("'2013-01-21', '2013-01-20', 'Alaska 3 2013', 'Alaska USA', 'chunguillo', true, 07");
		}
	}
	
	//GestorTrineos
	//--obtenerTrineosDeLaCarrera(lafechacarrera)
	public void obtenerTrineosDeLaCarreraTest(){
		//Cargamos una modalidad, un campeonato, una carrera (activa e inactiva) y un trineo (activo e inactivo)
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//nulls
		try{
			GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera(null, null);
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-20", null);
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera(null, "2013-01-20");
			fail("null");
		}
		catch(GenericException e){}
		//no existe la carrera (carrera/campeonato)--> no hay trineos en la carrera
		try{
			LinkedList<Integer> rdo = GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-20", "2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(), 0);
		}
		catch(GenericException e){
			fail("null");
		}		
		//Hay un trineo
		try{
			LinkedList<Integer> rdo = GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-21", "2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(), 1);
			assertEquals(rdo.get(0), new Integer(2));
			//mirar lista
		}
		catch(GenericException e){
			fail("null");
		}
		try{
			GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',01,0,00);");
		}
		catch(SQLException e){}
		//Hay dos trineos
		try{
			LinkedList<Integer> rdo = GestorTrineos.getGestorTrineos().obtenerTrineosDeLaCarrera("2013-01-21", "2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(), 2);
			assertEquals(rdo.get(0), new Integer(2));
			assertEquals(rdo.get(1), new Integer(1));
			//mirar lista
		}
		catch(GenericException e){
			fail("null");
		}
	}
	//--modificarTrineo(ID_Trineo,modelo)
	public void modificarTrineoTest(){
		try{
			//GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
			//GestorBD.getInstance().insertar("insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);");
			//GestorBD.getInstance().insertar("insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-22', '2013-01-20', 'Alaska 3 13', 'Alaska', 'Chungo', TRUE, 01);");
			GestorBD.getInstance().insertar("insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', TRUE);");
			GestorBD.getInstance().insertar("insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', FALSE);");
			//GestorBD.getInstance().insertar("insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-21', '2013-01-20',02,5,10);");
		}
		catch(SQLException e){}
		//nulls
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(null, null);
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(null, "fabricante1");
			fail("null");
		}
		catch(GenericException e){}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, null);
			fail("null");
		}
		catch(GenericException e){}
		//id_trineo no existe
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(07, "fabricante 1");
			fail("null");
		}
		catch(GenericException e){}
		//No blanco o espacios
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, "");
			fail("null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 1);
			assertEquals(rdo.getString("fabricante"), "fabricante1");
			rdo.close();
		}
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, " ");
			fail("null");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 1);
			assertEquals(rdo.getString("fabricante"), "fabricante1");
			rdo.close();
		}
		//correcto
		try{
			GestorTrineos.getGestorTrineos().modificarTrineo(01, "fabricante 1");
			ResultSet rdo = GestorBD.getInstance().consulta("select id_trineo, fabricante from trineo where id_trineo=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_trineo"), 1);
			assertEquals(rdo.getString("fabricante"), "fabricante 1");
			rdo.close();
		}
		catch(GenericException e){
			fail("null");
		}
	}
	
	//GestorPerros
	//--cargarDatosPerro():linkedkist<String>
	public void cargarDatosPerroTest(){
		try{
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (01, 'perro1', 'Husky', 'M', TRUE);");
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (02, 'perro2', 'Husky', 'M', FALSE);");
		}
		catch(SQLException e){}
		//El perro no existe
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().cargarDatosPerro(07);
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().cargarDatosPerro(-15);
			fail("");
		}
		catch(GenericException e){}
		//El perro existe activo
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().cargarDatosPerro(01);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "perro1");
			assertEquals(rdo.get(1), "Husky");
			assertEquals(rdo.get(2), "M");
		}
		catch(GenericException e){
			fail("");
		}
		//El perro existe inactivo
		try{
			LinkedList<String> rdo = GestorPerros.getGestorPerros().cargarDatosPerro(02);
			assertNotNull(rdo);
			assertEquals(rdo.size(), 3);
			assertEquals(rdo.get(0), "perro2");
			assertEquals(rdo.get(1), "Husky");
			assertEquals(rdo.get(2), "M");
		}
		catch(GenericException e){
			fail("");
		}
	}
	//--modificarDatosPerro(pID_Perro:Int, nombreModif:String, razaModif:String, sexoModif :String)
	public void modificarDatosPerroTest(){
		try{
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (02, 'perro2', 'Husky', 'M', TRUE);");
			GestorBD.getInstance().insertar("insert into perro(id_perro, nombre, raza, sexo, estado) values (01, 'perro1', 'Husky', 'M', TRUE);");
		}
		catch(SQLException e){}
		//nulls
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(null, null, null, null);
			fail("");
		}
		catch(GenericException e){}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(null, "perro 1", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, null, "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", null, "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", null);
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//El id no existe
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(07, "perro 1", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//El nombre ya esta cogido
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro2", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//vacios y espacios
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, " ", "HUSKY", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "", "M");
			fail("");
		}
		catch(GenericException e){}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", " ", "M");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", "");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", " ");
			fail("");
		}
		catch(GenericException e){
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro1");
			assertEquals(rdo.getString("raza"), "husky");
			assertEquals(rdo.getString("sexo"), "M");
			rdo.close();
		}
		//correcto
		try{
			GestorPerros.getGestorPerros().modificarDatosPerro(01, "perro 1", "HUSKY", "H");
			ResultSet rdo = GestorBD.getInstance().consulta("select id_perro, nombre, raza, sexo from perro where id_perro=01;");
			rdo.next();
			assertEquals(rdo.getInt("id_perro"), 01);
			assertEquals(rdo.getString("nombre"), "perro 1");
			assertEquals(rdo.getString("raza"), "HUSKY");
			assertEquals(rdo.getString("sexo"), "H");
			rdo.close();
		}
		catch(GenericException e){
			fail("");
		}
	}
	
	//GestorDueños
	//--modificarDatosDueño ( elNumFederado:Int, NombreModif: String, apellidoModif:String)
	public void modificarDatosDueñoTest(){
		try{
			GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (01, 'dueño1', 'apellido1', TRUE);");
			GestorBD.getInstance().insertar("insert into dueño(numFederado, nombre, apellido, estado) values (02, 'dueño2', 'apellido2', TRUE);");
		}
		catch(SQLException e){}
		//nulls
		try{
			GestorDueños.getGestorDueños().modificarDatos(null, null, null);
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatos(null, "DUEÑO 1", "APELLIDO 1");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatos(01, null, "APELLIDO 1");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatos(01, "DUEÑO 1", null);
		}
		catch(GenericException e){}
		//El numfederado no existe
		try{
			GestorDueños.getGestorDueños().modificarDatos(07, "DUEÑO 1", "APELLIDO 1");
		}
		catch(GenericException e){}
		//Vacios y espacios
		try{
			GestorDueños.getGestorDueños().modificarDatos(01, "", "APELLIDO 1");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatos(01, " ", "APELLIDO 1");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatos(01, "DUEÑO 1", "");
		}
		catch(GenericException e){}
		try{
			GestorDueños.getGestorDueños().modificarDatos(01, "DUEÑO 1", " ");
		}
		catch(GenericException e){}
		//correcto
		try{
			GestorDueños.getGestorDueños().modificarDatos(01, "DUEÑO 1", "APELLIDO 1");
		}
		catch(GenericException e){}
	}
	
	//GestorCampeonatos
	//--cargarDatosCampeonato()
	public void cargarDatosCampeonatoTest(){
		try{
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2012-01-20', 'Alaska 12', FALSE);");
			GestorBD.getInstance().insertar("insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);");
		}
		catch(SQLException e){}
		//Nulls
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().cargarDatosCampeonato(null);
			fail("");
		}
		catch(GenericException e){}
		//Blancos y vacios
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().cargarDatosCampeonato("");
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().cargarDatosCampeonato(" ");
			fail("");
		}
		catch(GenericException e){}
		//No existe el campeonato
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().cargarDatosCampeonato("2013-01-15");
			fail("");
		}
		catch(GenericException e){}
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().cargarDatosCampeonato("patata");
			fail("");
		}
		catch(GenericException e){}
		//Existe el campeonato activo
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().cargarDatosCampeonato("2013-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(), 2);
			assertEquals(rdo.get(0), "2013-01-20");
			assertEquals(rdo.get(1), "Alaska 13");
		}
		catch(GenericException e){
			fail("");
		}
		//Existe el campeonato inactivo
		try{
			LinkedList<String> rdo = GestorCampeonatos.getGestorCampeonatos().cargarDatosCampeonato("2012-01-20");
			assertNotNull(rdo);
			assertEquals(rdo.size(), 2);
			assertEquals(rdo.get(0), "2012-01-20");
			assertEquals(rdo.get(1), "Alaska 12");
		}
		catch(GenericException e){
			fail("");
		}
	}
	
*/	
}
