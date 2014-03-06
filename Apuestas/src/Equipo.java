import java.io.Serializable;

public class Equipo implements Serializable {
	
	//Atributos de equipo
	private String nombreEquipo;
	private int golesFavor;
	private int golesEnContra;
	private int partidosGanados;
	private int partidosPerdidos;
	
	//Constructor de la clase Equipo
	public Equipo(String nom, int golesF, int golesC, int partidosG, int partidosP) {	
		
		//Inicialización de atributos
		nombreEquipo=nom;
		golesFavor=golesF;
		golesEnContra=golesC;
		partidosGanados=partidosG;
		partidosPerdidos=partidosP;		
	}
	
	//Constructor de la clase equipo sin especificar atributos
	public Equipo() {
		
		//Inicialización de atributos
		nombreEquipo="";
		golesFavor=0;
		golesEnContra=0;
		partidosGanados=0;
		partidosPerdidos=0;
	}
	
	//Método para poner nombre al equipo
	public void setNombre(String nombre) {
		nombreEquipo=nombre;
	}
	
	//Método para recuperar el nombre del equipo
	public String getNombre() {
		return nombreEquipo;	
	}
	
	//Método para poner los goles a favor
	public void setGolesFavor(int gFavor) {	
		golesFavor=gFavor;
	}
	
	//Método para recuperar los goles a favor
	public int getGolesFavor() {
		return golesFavor;
	}
	
	//Método para poner los goles en contra
	public void setGolesContra(int gContra) {
		golesEnContra=gContra;
	}
	
	//Método para recuperar los goles en contra
	public int getGolesContra() {
		return golesEnContra;
	}
	
	//Método para estableces los partidos ganados
	public void setPartidosGanados(int ganados) {
		partidosGanados=ganados;
	}
	
	//Método para recuperar los partidos ganados
	public int getPartidosGanados() {
		return partidosGanados;		
	}
	
	//Método para estableces los partidos perdidos
	public void setPartidosPerdidos(int perdidos) {
		partidosPerdidos=perdidos;
	}
	
	//Método para recuperar los partidos perdidos
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}
}
