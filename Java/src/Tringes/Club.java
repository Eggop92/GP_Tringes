package Tringes;

public class Club {

	private int idClub;
	private String nombre;
	private boolean estado;
	
	public Club(int pIdClub, String pNombre, boolean pEstado){
		idClub=pIdClub;
		nombre= pNombre;
		estado=pEstado;
	}

	public int getIdClub() {
		return idClub;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean getEstado() {
		return estado;
	}
	
	
	
}
