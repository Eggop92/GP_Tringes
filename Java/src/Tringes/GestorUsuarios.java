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

		
	public String identificarse(String elUsuario, String laContrase�a){
		String tipo="" ;
			try {
				ResultSet rdo = GestorBD.getInstance().consulta("Select Tipo From usuario where NombreUsuario='"+elUsuario+"' and Contrase�a='"+laContrase�a+"';");
				while(rdo.next()){
					tipo=rdo.getString("Tipo");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return tipo;
	}
	
	
		//modificarContrase�a(nombreUsuario,nuevaContrase�a)
		public void modificarContrase�a(int nombreUsuario, String nuevaContrase�a){	
			
			try {
				GestorBD.getInstance().actualizar("UPDATE usuario SET Contrase�a='"+nuevaContrase�a+"' WHERE nombreUsuario='"+nombreUsuario+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public String generarContrase�a(){
			String Contrase�a="", sql;
			String valores="abcdefghijklmn�opqrstuvwxyz0123456789ABCDEFGHYJKLMN�OPQRSTUVWXYZ_.-*";//68
			int numAleatorio, contador=1;
			while(contador<=8){
				numAleatorio=(int)(Math.random()*(68+0));
				Contrase�a=Contrase�a+valores.charAt(numAleatorio);
				contador++;
			}
			try {
				sql="SELECT count(*) AS num FROM usuario WHERE Contrase�a='"+Contrase�a+"';";
				ResultSet rdo =GestorBD.getInstance().consulta(sql);
				if (rdo.next()){
					if(rdo.getInt("num")!=0){
						Contrase�a=generarContrase�a();
					}
				}
				rdo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Contrase�a;
		}	



}
