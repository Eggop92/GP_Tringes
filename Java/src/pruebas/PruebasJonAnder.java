package pruebas;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Tringes.GestorBD;
import Tringes.GestorCampeonatos;
import Tringes.GestorCarreras;
import Tringes.GestorClubs;
import Tringes.GestorDueños;
import Tringes.GestorPerros;
import Tringes.GestorTrineos;

public class PruebasJonAnder {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testObtenerCampeonato() {

		try{
			//obtener Campeonato que no este en la BD
			assertEquals(0, GestorCampeonatos.getGestorCampeonatos().obtenerCampeonato("6/6/2006").size());
			
			//Creo el Campeonato
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','EHU2013',1)");

			//obter Campeonato que este en la BD
			assertEquals("999", GestorCampeonatos.getGestorCampeonatos().obtenerCampeonato("6/6/2006").get(0)); 
		
			//Elimino El Campeonato de la BD
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testActivarEstadoCampeonato() {
		try {
			//Creo el Campeonato
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','EHU2013',0)");
			
			//Poner Estado a True de un Campeonato que esta a False
			GestorCampeonatos.getGestorCampeonatos().activarEstado("6/6/2006");
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from campeonato where FechaCampeonato='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Poner Estado a True de un Campeonato que esta a True
			GestorCampeonatos.getGestorCampeonatos().activarEstado("6/6/2006");
			rdo= GestorBD.getInstance().consulta("Select Estado from campeonato where FechaCampeonato='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Campeonato de la BD
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertarCampeonato() {
		try{
			//Creo el Campeonato
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','CampeonatoEHU',0)");
			
			//Campeonato en la DB que este ya a False
			//Poner Estado a True de un Campeonato que esta a False
			GestorCampeonatos.getGestorCampeonatos().insertarCampeonato("6/6/2006","EHU2013");
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from campeonato where FechaCampeonato='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Campeonato que este en la BD que este ya a true
			//Poner Estado a True de un Campeonato que esta a True
			GestorCampeonatos.getGestorCampeonatos().insertarCampeonato("6/6/2006","EHU2013");
			rdo= GestorBD.getInstance().consulta("Select Estado from campeonato where FechaCampeonato='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Campeonato de la BD
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
			
			//Campeonato que no este en la BD
			//Inserto un Campeonato que no este en la BD
			GestorCampeonatos.getGestorCampeonatos().insertarCampeonato("6/6/2006","EHU2013");
			
			//Compruebo que el Campeonato este en la BD y este a true
			rdo= GestorBD.getInstance().consulta("Select Estado from campeonato where FechaCampeonato='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Campeonato de la BD
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerInfoCampeonatos() {
		try{
			//Asumiento que al inicio no hay Campeonato en la BD
			assertEquals(0, GestorCampeonatos.getGestorCampeonatos().obtenerInfoCampeonato().size());
			
			//Creo el Campeonato
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','EHU2013',1");

			//obter Campeonato que este en la BD
			assertEquals(1, GestorCampeonatos.getGestorCampeonatos().obtenerInfoCampeonato().size()); 
		
			//Elimino El Campeonato de la BD
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		@Test
	public void testObtenerCarrera() {

		try{
			//obtener Carrera que no este en la BD
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','EHU2013',1");
			assertEquals(0, GestorCarreras.getGestorCarreras().obtenerCarrera("6/6/2006").size());
			
			//Creo el Carrera
			GestorBD.getInstance().actualizar("Insert Into Carrera values ('6/6/2006','EHU2013','Carrera1','Bilbao','Largo',1,1)");

			//obter Carrera que este en la BD
			assertEquals("999", GestorCarreras.getGestorCarreras().obtenerCarrera("6/6/2006").get(0)); 
		
			//Elimino El Carrera de la BD
			GestorBD.getInstance().borrar("Delete * from Carrera where FechaCarrera='6/6/2006'");
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testActivarEstadoCarrera() {
		try {
			//Creo el Carrera
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','EHU2013',1");
			GestorBD.getInstance().actualizar("Insert Into Carrera values ('6/6/2006','EHU2013','Carrera1','Bilbao','Largo',0,1");
			
			//Poner Estado a True de un Carrera que esta a False
			GestorCarreras.getGestorCarreras().activarEstado("6/6/2006");
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Carrera where FechaCarrera='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Poner Estado a True de un Carrera que esta a True
			GestorCarreras.getGestorCarreras().activarEstado("6/6/2006");
			rdo= GestorBD.getInstance().consulta("Select Estado from Carrera where FechaCarrera='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Carrera de la BD
			GestorBD.getInstance().borrar("Delete * from Carrera where FechaCarrera='6/6/2006'");
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertarCarrera() {
		try{
			//Creo el Carrera
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','EHU2013',1");
			GestorBD.getInstance().actualizar("Insert Into Carrera values ('6/6/2006','EHU2013','Carrera1','Bilbao','Largo',0,1)");
			
			//Carrera en la DB que este ya a False
			//Poner Estado a True de un Carrera que esta a False
			GestorCarreras.getGestorCarreras().insertarCarrera("6/6/2006","6/6/2006","Bilbao","Largo",1);
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Carrera where FechaCarrera='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Carrera que este en la BD que este ya a true
			//Poner Estado a True de un Carrera que esta a True
			GestorCarreras.getGestorCarreras().insertarCarrera("6/6/2006","6/6/2006","Bilbao","Largo",1);
			rdo= GestorBD.getInstance().consulta("Select Estado from Carrera where FechaCarrera='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Carrera de la BD
			GestorBD.getInstance().borrar("Delete * from Carrera where FechaCarrera='6/6/2006'");
			
			//Carrera que no este en la BD
			//Inserto un Carrera que no este en la BD
			GestorCarreras.getGestorCarreras().insertarCarrera("6/6/2006","6/6/2006","Bilbao","Largo",1);
			
			//Compruebo que el Carrera este en la BD y este a true
			rdo= GestorBD.getInstance().consulta("Select Estado from Carrera where FechaCarrera='6/6/2006'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Carrera de la BD
			GestorBD.getInstance().borrar("Delete * from Carrera where FechaCarrera='6/6/2006'");
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
			} 
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerInfoCarreras() {
		try{
			//En la BD no esta el campeonato
			assertEquals(0, GestorCarreras.getGestorCarreras().obtenerInfoCarreras("6/6/2006").size());
			
			//En la BD si esta el campeonato pero sin Carreras
			GestorBD.getInstance().actualizar("Insert Into campeonato values ('6/6/2006','EHU2013',1");
			assertEquals(0, GestorCarreras.getGestorCarreras().obtenerInfoCarreras("6/6/2006").size());
			
			//Creo la Carrera
			GestorBD.getInstance().actualizar("Insert Into Carrera values ('6/6/2006','CarreraEHU',1");

			//obtener Carreras que esten en la BD
			assertEquals(1, GestorCarreras.getGestorCarreras().obtenerInfoCarreras("6/6/2006").size()); 
		
			//Elimino El Carrera de la BD
			GestorBD.getInstance().borrar("Delete * from Carrera where FechaCarrera='6/6/2006'");
			GestorBD.getInstance().borrar("Delete * from campeonato where FechaCampeonato='6/6/2006'");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerClub() {
		
		try{
			//obtener Club que no este en la BD
			assertEquals(0, GestorClubs.getGestorClubs().obtenerClub("EUITI","EHU").size());//Compruebo que un club que no este en la basse de datos devuelva una lista vacia .
			
			//Creo el club		
			GestorBD.getInstance().actualizar("Insert Into club values (999,'EUITI','EHU',1)");

			//obter club que este en la BD
			assertEquals("999", GestorClubs.getGestorClubs().obtenerClub(999).get(0)); //Compruebo que devuelva los datos correctamente si el trineo existe en la base de datos.
		
			//Elimino El club de la BD
			GestorBD.getInstance().borrar("Delete * from club where ID_Club=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testActivarEstadoClub() {
		try {
			//Creo el Club		
			GestorBD.getInstance().actualizar("Insert Into club values (999,'EUITI','EHU',0)");
			
			//Poner Estado a True de un Club que esta a False
			GestorClubs.getGestorClubs().activarEstado(999);
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Club Where ID_Club=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Poner Estado a True de un Club que esta a True
			GestorClubs.getGestorClubs().activarEstado(999);
			rdo= GestorBD.getInstance().consulta("Select Estado from Club Where ID_Club=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El club de la BD
			GestorBD.getInstance().borrar("Delete * from club where ID_Club=999");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertarClub() {
		try{
			//Creo el club		
			GestorBD.getInstance().actualizar("Insert Into club values (999,'EUITI','EHU',0)");
			
			//club en la DB que este ya a False
			//Poner Estado a True de un club que esta a False
			GestorClubs.getGestorClubs().insertarClub(999);
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Club Where ID_Club=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//club que este en la BD que este ya a true
			//Poner Estado a True de un club que esta a True
			GestorClubs.getGestorClubs().insertarClub(999,"EUITI","EHU");
			rdo= GestorBD.getInstance().consulta("Select Estado from Club Where ID_Club=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El club de la BD
			GestorBD.getInstance().borrar("Delete * from Club where ID_Club=999");
			
			//club que no este en la BD
			//Inserto un club que no este en la BD
			GestorClubs.getGestorClubs().insertarClub(999,"EUITI","EHU");
			
			//Compruebo que el club este en la BD y este a true
			rdo= GestorBD.getInstance().consulta("Select Estado from Club Where ID_Club=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El club de la BD
			GestorBD.getInstance().borrar("Delete * from Club where ID_Club=999");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerInfoClub() {
		try{
			//Asumiento que al inicio no hay Clubs en la BD
			assertEquals(0, GestorClubs.getGestorClubs().obtenerInfoClub().size());//Compruebo que un club que no este en la basse de datos devuelva una lista vacia .
			
			//Creo el Club		
			GestorBD.getInstance().actualizar("Insert Into club values (999,'EUITI','EHU',1)");

			//obter club que este en la BD
			assertEquals(1, GestorClubs.getGestorClubs().obtenerInfoClub().size()); //Compruebo que devuelva los datos correctamente si el trineo existe en la base de datos.
		
			//Elimino El club de la BD
			GestorBD.getInstance().borrar("Delete * from club where ID_Club=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerDueño() {
		try{
			//obtener Dueño que no este en la BD
			assertEquals(0, GestorDueños.getGestorDueños().obtenerDueño(999).size());
			
			//Creo el Dueño
			GestorBD.getInstance().actualizar("Insert Into Dueño values (999,'Aitor','Tilla',1)");

			//obter Dueño que este en la BD
			assertEquals("999", GestorDueños.getGestorDueños().obtenerDueño(999).get(0)); 
		
			//Elimino El Dueño de la BD
			GestorBD.getInstance().borrar("Delete * from Dueño where NumFederado=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testActivarEstadoDueño() {
		try {
			//Creo el Dueño
			GestorBD.getInstance().actualizar("Insert Into Dueño values (999,'Aitor','Tilla',0)");
			
			//Poner Estado a True de un Dueño que esta a False
			GestorDueños.getGestorDueños().activarEstado(999);
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Dueño where NumFederado=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Poner Estado a True de un Dueño que esta a True
			GestorDueños.getGestorDueños().activarEstado(999);
			rdo= GestorBD.getInstance().consulta("Select Estado from Dueño where NumFederado=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Dueño de la BD
			GestorBD.getInstance().borrar("Delete * from Dueño where NumFederado=999");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertarDueño() {
		try{
			//Creo el Dueño
			GestorBD.getInstance().actualizar("Insert Into Dueño values (999,'Aitor','Tilla',0)");
			
			//Dueño en la DB que este ya a False
			//Poner Estado a True de un Dueño que esta a False
			GestorDueños.getGestorDueños().insertarDueño(999,"Aitor","Tilla");
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Dueño where NumFederado=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Dueño que este en la BD que este ya a true
			//Poner Estado a True de un Dueño que esta a True
			GestorDueños.getGestorDueños().insertarDueño(999,"Aitor","Tilla");
			rdo= GestorBD.getInstance().consulta("Select Estado from Dueño where NumFederado=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Dueño de la BD
			GestorBD.getInstance().borrar("Delete * from Dueño where NumFederado=999");
			
			//Dueño que no este en la BD
			//Inserto un Dueño que no este en la BD
			GestorDueños.getGestorDueños().insertarDueño(999,"Aitor","Tilla");
			
			//Compruebo que el Dueño este en la BD y este a true
			rdo= GestorBD.getInstance().consulta("Select Estado from Dueño where NumFederado=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Dueño de la BD
			GestorBD.getInstance().borrar("Delete * from Dueño where NumFederado=999");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		@Test
	public void testObtenerPerro() {
		try{
			//obtener Perro que no este en la BD
			assertEquals(0, GestorPerros.getGestorPerros().obtenerPerro("Zeus","Bulldog","Macho").size());
			
			//Creo el Perro
			GestorBD.getInstance().actualizar("Insert Into Perro values (999,'Zeus','Bulldog','Macho',1)");

			//obter Perro que este en la BD
			assertEquals("999", GestorPerros.getGestorPerros().obtenerPerro("Zeus","Bulldog","Macho").get(0)); 
		
			//Elimino El Perro de la BD
			GestorBD.getInstance().borrar("Delete * from Perro where ID_Perro=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testActivarEstadoPerro() {
		try {
			//Creo el Perro
			GestorBD.getInstance().actualizar("Insert Into Perro values (999,'Zeus','Bulldog','Macho',0)");
			
			//Poner Estado a True de un Perro que esta a False
			GestorPerros.getGestorPerros().activarEstado(999);
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Perro where ID_Perro=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Poner Estado a True de un Perro que esta a True
			GestorPerros.getGestorPerros().activarEstado(999);
			rdo= GestorBD.getInstance().consulta("Select Estado from Perro where ID_Perro=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El Perro de la BD
			GestorBD.getInstance().borrar("Delete * from Perro where ID_Perro=999");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertarPerroPertenencia() {
		fail("Not yet implemented");//Preguntar Mañana
	}

	@Test
	public void testObtenerDatosUtiliza() {
		try{
			GestorBD.getInstance().actualizar("Insert Into Perro values (999,'Zeus','Bulldog','Macho',1)");
			GestorBD.getInstance().actualizar("Insert Into club values (999,'EUITI','EHU',1)");
			
			//obtener Perro que no este en la BD
			assertEquals(0, GestorPerros.getGestorPerros().obtenerDatosUtiliza(999,999).size());
			
			//Creo el Perro
			GestorBD.getInstance().actualizar("Insert Into Utiliza values (999,999,'6/6/2006',1)");

			//obter Perro que este en la BD
			assertNotEquals(0, GestorPerros.getGestorPerros().obtenerDatosUtiliza(999,999).size()); 
		
			//Elimino El Perro de la BD
			GestorBD.getInstance().borrar("Delete * from Utiliza where ID_Perro=999 AND ID_Club=999");
			GestorBD.getInstance().borrar("Delete * from Club where ID_Club=999");
			GestorBD.getInstance().borrar("Delete * from Perro where ID_Perro=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertarUtiliza() {
		
		try{
			GestorBD.getInstance().actualizar("Insert Into Perro values (999,'Zeus','Bulldog','Macho',1)");
			GestorBD.getInstance().actualizar("Insert Into club values (999,'EUITI','EHU',1)");
			
			//Relaccion no esta en la BD
			ResultSet rdo= GestorBD.getInstance().consulta("Select Count * as Cant from Utiliza where ID_Perro=999 AND ID_Club=999");
			rdo.next();
			assertEquals(0, rdo.getInt("Cant"));	
			
			//Inserto La relaccion
			GestorPerros.getGestorPerros().insertarUtiliza(999,999);
			rdo= GestorBD.getInstance().consulta("Select Count * as Cant from Utiliza where ID_Perro=999 AND ID_Club=999");
			rdo.next();
			assertEquals(1, rdo.getInt("Cant"));	

			//Vuelvo a intentar insertar la relaccion
			GestorPerros.getGestorPerros().insertarUtiliza(999,999);
			rdo= GestorBD.getInstance().consulta("Select Count * as Cant from Utiliza where ID_Perro=999 AND ID_Club=999");
			rdo.next();
			assertEquals(1, rdo.getInt("Cant"));	
			
			//Elimino La relaccion de la BD
			GestorBD.getInstance().borrar("Delete * from Utiliza where ID_Perro=999 AND ID_Club=999");
			GestorBD.getInstance().borrar("Delete * from Club where ID_Club=999");
			GestorBD.getInstance().borrar("Delete * from Perro where ID_Perro=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testObtenerApareamiento() {
		try{
			GestorBD.getInstance().actualizar("Insert Into Perro values (999,'Zeus','Bulldog','Macho',1)");
		
			//obtener Apareamiento que no este en la BD
			assertEquals(0, GestorPerros.getGestorPerros().obtenerApareamiento(999,"6/6/2006").size());
			
			//Creo el Apareamiento
			GestorBD.getInstance().actualizar("Insert Into Apareamiento values (999,'6/6/2006',6)");

			//obter Apareamiento que este en la BD
			assertEquals("999", GestorPerros.getGestorPerros().obtenerApareamiento(999,"6/6/2006").get(0)); 
		
			//Elimino El Apareamiento de la BD
			GestorBD.getInstance().borrar("Delete * from Apareamiento where ID_Perro=999 AND Fecha='6/6/2006'");

		
			GestorBD.getInstance().borrar("Delete * from Perro where ID_Perro=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertarApareamiento() {//Por que se le pasa raza y sexo?
		try{
			GestorBD.getInstance().actualizar("Insert Into Perro values (999,'Zeus','Bulldog','Macho',1)");
		
			//Inserto informacion del apareamiento cuando no esta en la BD
			GestorPerros.getGestorPerros().insertarApareamiento(999,"6/6/2006",6);
			ResultSet rdo= GestorBD.getInstance().consulta("Select NumCrias as Crias from Apareamiento where ID_Perro=999 AND Fecha='6/6/2006'");
			rdo.next();
			assertEquals(6, rdo.getInt("Crias"));
			
			//Inserto informacion del apareamiento cuando ya esta en la BD
			GestorPerros.getGestorPerros().insertarApareamiento(999,"6/6/2006",6);
			ResultSet rdo= GestorBD.getInstance().consulta("Select NumCrias as Crias from Apareamiento where ID_Perro=999 AND Fecha='6/6/2006'");
			rdo.next();
			assertEquals(6, rdo.getInt("Crias"));
			
			GestorBD.getInstance().borrar("Delete * from Apareamiento where ID_Perro=999 AND Fecha='6/6/2006'");
			GestorBD.getInstance().borrar("Delete * from Perro where ID_Perro=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerTrineo() {		
		
		try{
			//obter trineo que no este en la BD
			assertEquals(0, GestorTrineos.getGestorTrineos().obtenerTrineo(999).size());//Compruebo que un trineo que no este en la basse de datos devuelca una lista vacia .
			
			//Creo el Trineo		
			GestorBD.getInstance().actualizar("Insert Into trineo values (999,1,'Fabricante','6/6/2006',1)");
	
			//obter trineo que este en la BD
			assertEquals("1", GestorTrineos.getGestorTrineos().obtenerTrineo(999).get(0)); //Compruebo que devuelva los datos correctamente si el trineo existe en la base de datos.
		
			//Elimino El trineo de la BD
					GestorBD.getInstance().borrar("Delete * from Trineo where ID_Trineo=999");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActivarEstadoTrineo() {
		
		try {
			//Creo el Trineo		
			GestorBD.getInstance().actualizar("Insert Into trineo values (999,1,'Fabricante','6/6/2006',0)");
			
			//Poner Estado a True de un Trineo que esta a False
			GestorTrineos.getGestorTrineos().activarEstado(999);
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Trineo Where ID_Trineo=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Poner Estado a True de un Trineo que esta a True
			GestorTrineos.getGestorTrineos().activarEstado(999);
			rdo= GestorBD.getInstance().consulta("Select Estado from Trineo Where ID_Trineo=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El trineo de la BD
			GestorBD.getInstance().borrar("Delete * from Trineo where ID_Trineo=999");
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void testInsertarTrineo() {

		try{
			//Creo el Trineo		
			GestorBD.getInstance().actualizar("Insert Into trineo values (999,1,'Fabricante','6/6/2006',0)");
			
			//Trineo en la DB que este ya a False
			//Poner Estado a True de un Trineo que esta a False
			GestorTrineos.getGestorTrineos().insertarTrineo(999,"Fabricante","6/6/2006",1);
			ResultSet rdo= GestorBD.getInstance().consulta("Select Estado from Trineo Where ID_Trineo=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));	
			
			//Trineo que este en la BD que este ya a true
			//Poner Estado a True de un Trineo que esta a True
			GestorTrineos.getGestorTrineos().insertarTrineo(999,"Fabricante","6/6/2006",1);
			rdo= GestorBD.getInstance().consulta("Select Estado from Trineo Where ID_Trineo=999");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El trineo de la BD
			GestorBD.getInstance().borrar("Delete * from Trineo where ID_Trineo=999");
			
			//Trineo que no este en la BD
			//Inserto un trineo que no este en la BD
			GestorTrineos.getGestorTrineos().insertarTrineos(999,"Fabricante","6/6/2006",1);
			
			//Compruebo que el trineo este en la BD y este a true
			rdo= GestorBD.getInstance().consulta("Select Estado from Trineo Where ID_Trineo='1'");
			rdo.next();
			assertEquals("1", rdo.getInt("Estado"));
			
			//Elimino El trineo de la BD
			GestorBD.getInstance().borrar("Delete * from Trineo where ID_Trineo=999");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
