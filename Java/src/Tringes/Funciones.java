package Tringes;

public class Funciones {

	
	public static void ejemploSeparacionCadena(){
		//Creacion de la cadena
		int id = 132412;
		String nombre = "Juan";
		String compuesta = id + " - " + nombre;
		
		//metodo que separa la cadena
		String[] separada=separarCadena(compuesta);
		
		//recojer los datos en las variables de nuevo
		id = 0;
		nombre="";
		id=Integer.parseInt(separada[0]);
		nombre=separada[1];
		System.out.println("El id es " + id);
		System.out.println("El nombre es" + nombre);	
	}

	
	public static String[] separarCadena(String cadena){
		String[] lista = null;
		lista = cadena.split(" - ");
		return lista;
	}
	
	public static void main(String [ ] args){
		ejemploSeparacionCadena();
	}
	
}
