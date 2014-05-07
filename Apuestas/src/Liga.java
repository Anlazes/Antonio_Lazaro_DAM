import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;




public class Liga implements Serializable {
	
	//Atributos de clase Liga
	private int numEquipos;
	private String nombreLiga;
	private ArrayList<Equipo> equipos;
	
	//Base de datos
	private Connection conexion = null; //maneja la conexión a la base de datos
	private Statement instruccion = null; //instrucción de consulta
	private ResultSet conjuntoResultados = null; //maneja los resultados

	//Constructor de la clase Liga
	public Liga(Connection conexion) {
		
		//Inicialización de los atributos
		numEquipos=24;
		nombreLiga="Liga Española";
		this.conexion=conexion;	
		//equipos.ensureCapacity(numEquipos);
		//for(int i=0;i<numEquipos;i++) {
			//equipos.add(new Equipo());
		//}
	}
	
	//Constructor de la clase Liga con atributos
	public Liga(Connection conexion, String nombreL) {
		
		//Inicialización de atributos
		nombreLiga=nombreL;
		numEquipos=0;
		this.conexion=conexion;
		//equipos.ensureCapacity(numEquipos);
		for(int i=0;i<numEquipos;i++) {
			equipos.add(new Equipo(conexion));
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
		return equipos.get(posicion);
	}
	
	//Método para establecer un equipo en una posición determinada
	public void setEquipo(Equipo equipo, int posicion) {
		equipo=new Equipo(conexion);
		equipos.add(posicion, equipo);		
	}
	
	//Método para quitar un equipo
	public void quitarEquipo(int posicion) {
		equipos.remove(posicion);
	}	
		
	//Método para añadir un equipo
	public void anyadirEquipo() {
		numEquipos++;
		equipos.add(new Equipo(conexion));
		
	}
	
	//Método para leer liga desde DB
	public void leerLiga() {
		try {
		//consulta base de datos
		instruccion = (Statement) conexion.createStatement();
		conjuntoResultados = instruccion.executeQuery("SELECT idLiga,nombre,numEquipos FROM ligas LIMIT 1");
		conjuntoResultados.next();
		//almacena liga
		this.nombreLiga=(String)conjuntoResultados.getObject("nombre");
		this.numEquipos=(int)conjuntoResultados.getObject("numEquipos");
		
		} catch (SQLException exc) {
		exc.printStackTrace();
		}
		
   }
	
}
