import java.io.Serializable;
import java.util.ArrayList;


public class Liga implements Serializable {
	
	//Atributos de clase Liga
	private int numEquipos;
	private String nombreLiga;
	private ArrayList<Equipo> equipos=new ArrayList<Equipo>();

	//Constructor de la clase Liga
	public Liga() {
		
		//Inicializaci�n de los atributos
		numEquipos=20;
		nombreLiga="Liga BBVA";
		equipos.ensureCapacity(numEquipos);
		for(int i=0;i<numEquipos;i++) {
			equipos.add(new Equipo());
		}
	}
	
	//Constructor de la clase Liga con atributos
	public Liga(int numeroE, String nombreL) {
		
		//Inicializaci�n de atributos
		numEquipos=numeroE;
		nombreLiga=nombreL;
		equipos.ensureCapacity(numEquipos);
		for(int i=0;i<numEquipos;i++) {
			equipos.add(new Equipo());
		}
	}
	
	//M�todo para poner nombre a la liga
	public void setNombreLiga(String nombre) {	
		nombreLiga=nombre;
	}
	
	//M�todo para obetener el noombre puesto a la liga
	public String getNombreLiga() {
		return nombreLiga;	
	}
	
	//M�todo para obtener el n�mero de equipos
	public int getNumEquipos() {
		return numEquipos;
	}
	
	//M�todo para obtener la posici�n del equipo
	public Equipo getEquipo(int posicion) {
		return equipos.get(posicion);
	}
	
	//M�todo para establecer un equipo en una posici�n determinada
	public void setEquipo(Equipo equipo, int posicion) {
		equipo=new Equipo();
		equipos.add(posicion, equipo);
		
	}
}
