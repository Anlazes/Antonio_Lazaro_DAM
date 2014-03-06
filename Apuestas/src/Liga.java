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
	
	//Método para poner nombre a la liga
	public void setNombreLiga(String nombre) {	
		nombreLiga=nombre;
	}
	
	//Método para obetener el noombre puesto a la liga
	public String getNombreLiga() {
		return nombreLiga;	
	}
	
	//Método para obtener el número de equipos
	public int getNumEquipos() {
		return numEquipos;
	}
	
	//Método para obtener la posición del equipo
	public Equipo getEquipo(int posicion) {
		return equipos[posicion];	
	}
	
	//Método para establecer un equipo en una posición determinada
	public void setEquipo(Equipo equipo, int posicion) {
		equipo=new Equipo();
		equipo=equipos[posicion];
		
	}
}
