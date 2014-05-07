import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class ControllerDB {
	
	private String nombre;
	//DB
	Connection conexion = null; //maneja la conexión
	Statement instruccion = null; //instrucción de consulta
	ResultSet conjuntoResultados = null; //maneja los resultados
	
	public ControllerDB() {
		//Nos conectamos a la base de datos
		crearConexion();
	}
	
	public void crearConexion(){
		//Conectamos a la base de datos
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//establece la conexión a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/apuestas","root","");
		}catch( SQLException excepcionSql ){
			excepcionSql.printStackTrace();
		}// fin de catch
		catch( ClassNotFoundException noEncontroClase ) {
			noEncontroClase.printStackTrace();
		}
	}	
	//En este método leeremos de la base de datos los equipos existentes
	// 1.- Leer de la base de datos
	// 2.- Actualizar el combobox
	public void leerEquipos(JComboBox<String> listadoEquipos){
		try {		
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		//consulta la base de datos
		conjuntoResultados = instruccion.executeQuery("SELECT nombreEquipo FROM equipos");		
			while (conjuntoResultados.next()) { 
			this.nombre=(String)conjuntoResultados.getObject("nombreEquipo");
		
			listadoEquipos.addItem(nombre);
			}	
			
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
		
	}
	
	//En este método leeremos de la base de datos los equipos existentes
	// 1.- Crearemos la consulta a la base de datos (Statement)
	// 2.- Lanzaremos la consulta
	// 3.- Eliminaremos todos los elementos del combobox
	// 4.- Volveremos a rellenar el combobox
	public void insertarEquipos(int idLiga,String nombreEquipo,int golesFavor,int golesEnContra, int partidosGanados,int partidosPerdidos,JComboBox<String> listadoEquipos){
		//Aquí realizaremos la consulta
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		String slq_ins="INSERT INTO equipos(idLiga, nombreEquipo, golesFavor, golesEnContra, partidosGanados, partidosPerdidos)";
		slq_ins=slq_ins+"VALUES("+idLiga+",'"+nombreEquipo+"',"+golesFavor+","+golesEnContra+","+partidosGanados+","+partidosPerdidos+")";
		instruccion.executeUpdate(slq_ins);
		//Actualización del combobox
		listadoEquipos.removeAllItems();
		leerEquipos(listadoEquipos);
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
	
	public String toString() {
		return nombre;
	}
}
