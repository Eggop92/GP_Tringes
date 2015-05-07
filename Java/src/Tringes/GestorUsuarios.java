package Tringes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;

public class GestorUsuarios {
	private static GestorUsuarios miGestorUsuarios;
		
	private GestorUsuarios(){}
		
	public static GestorUsuarios getGestorUsuarios(){
		if(miGestorUsuarios == null){
			miGestorUsuarios = new GestorUsuarios();
		}
		return miGestorUsuarios;
	}

		
	public String identificarse(String elUsuario, String laContraseña){
		String tipo="" ;
			try {
				ResultSet rdo = GestorBD.getInstance().consulta("Select Tipo From usuario where NombreUsuario='"+elUsuario+"' and Contraseña='"+laContraseña+"';");
				while(rdo.next()){
					tipo=rdo.getString("Tipo");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return tipo;
	}
	
	
		//modificarContraseña(nombreUsuario,nuevaContraseña)
		public void modificarContraseña(int nombreUsuario, String nuevaContraseña){	
			
			try {
				GestorBD.getInstance().actualizar("UPDATE usuario SET Contraseña='"+nuevaContraseña+"' WHERE nombreUsuario='"+nombreUsuario+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public String generarContraseña(){
			String Contraseña="", sql;
			String valores="abcdefghijklmnñopqrstuvwxyz0123456789ABCDEFGHYJKLMNÑOPQRSTUVWXYZ_.-*";//68
			int numAleatorio, contador=1;
			while(contador<=8){
				numAleatorio=(int)(Math.random()*(68+0));
				Contraseña=Contraseña+valores.charAt(numAleatorio);
				contador++;
			}
			try {
				sql="SELECT count(*) AS num FROM usuario WHERE Contraseña='"+Contraseña+"';";
				ResultSet rdo =GestorBD.getInstance().consulta(sql);
				if (rdo.next()){
					if(rdo.getInt("num")!=0){
						Contraseña=generarContraseña();
					}
				}
				rdo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Contraseña;
		}	



}
