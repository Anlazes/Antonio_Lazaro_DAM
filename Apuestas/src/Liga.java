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
	private Connection conexion = null; //maneja la conexi�n a la base de datos
	private Statement instruccion = null; //instrucci�n de consulta
	private ResultSet conjuntoResultados = null; //maneja los resultados

	//Constructor de la clase Liga
	public Liga(Connection conexion) {
		
		//Inicializaci�n de los atributos
		numEquipos=20;
		nombreLiga="Liga BBVA";
		this.conexion=conexion;	
		//equipos.ensureCapacity(numEquipos);
		//for(int i=0;i<numEquipos;i++) {
			//equipos.add(new Equipo());
		//}
	}
	
	//Constructor de la clase Liga con atributos
	public Liga(Connection conexion, String nombreL) {
		
		//Inicializaci�n de atributos
		nombreLiga=nombreL;
		numEquipos=0;
		this.conexion=conexion;
		//equipos.ensureCapacity(numEquipos);
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
	
	//M�todo para quitar un equipo
	public void quitarEquipo(int posicion) {
		equipos.remove(posicion);
	}	
		
	//M�todo para a�adir un equipo
	public void anyadirEquipo() {
		numEquipos++;
		equipos.add(new Equipo());
		
	}
	
	//M�todo para leer liga desde DB
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
