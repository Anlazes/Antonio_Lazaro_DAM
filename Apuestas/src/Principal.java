import java.awt.EventQueue;


public class Principal {

	public static void main(String[] args) {
		
			Equipo equipo=new Equipo(); //Creación de objeto equipo
		
			//Lanzador de la ventana
			VentanaEquipo frame = new VentanaEquipo(equipo);
			frame.setVisible(true);						
	}

}
