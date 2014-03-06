import java.io.Serializable;


public class Liga implements Serializable {
	
	//Atributos de clase Liga
	private int numEquipos;
	private String nombreLiga;
	private Equipo equipos[];

	//Constructor de la clase Liga
	public Liga() {
		
		//Inicialización de los atributos
		numEquipos=20;
		nombreLiga="Liga BBVA";
		equipos=new Equipo[numEquipos];
		for(int i=0;i<numEquipos;i++) {
			equipos[i]=new Equipo();
		}
	}
	
	//Constructor de la clase Liga con atributos
	public Liga(int numeroE, String nombreL) {
		
		//Inicialización de atributos
		numEquipos=numeroE;
		nombreLiga=nombreL;
		equipos=new Equipo[numEquipos];
		for(int i=0;i<numEquipos;i++) {
			equipos[i]=new Equipo();
		}
	}
	
	public void setNombreLiga(String nombre) {	
		nombreLiga=nombre;
	}

	public String getNombreLiga() {
		return nombreLiga;	
	}
	
	public int getNumEquipos() {
		return numEquipos;
	}
	
	public Equipo getEquipo(int posicion) {
		return equipos[posicion];	
	}
	
	public void setEquipo(Equipo equipo, int posicion) {
		equipo=new Equipo();
		equipo=equipos[posicion];
		
	}
}
