import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.font.NumericShaper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class VentanaEquipo extends JFrame {
	
	//Atributos de la clase VentanaEquipo
	private Equipo equipo;
	private JPanel contentPane;
	private JTextField textoNombre;
	private JTextField textoGolesF;
	private JTextField textoGolesC;
	private JTextField textoPartidosG;
	private JTextField textoPartidosP;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private JComboBox<Equipo> comboBox;
	private boolean modifica;
	private Connection conexion = null; //maneja la conexión a la base de datos

	//Constructor VentanaEquipo
	public VentanaEquipo(Equipo equipoAModificar, JComboBox comboBox, boolean modifica) {
		
		//Conexión a la base de datos
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//establece la conexión a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/apuestas","root","");
		} catch (SQLException sqlexcep) {
			sqlexcep.printStackTrace();		
		} catch (ClassNotFoundException e) {			
				e.printStackTrace(); } 
		
		//Asignación equipo
		equipo=equipoAModificar;
		
		this.comboBox=comboBox;
		this.modifica=modifica;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etiqNombre = new JLabel("Nombre Equipo");
		etiqNombre.setBounds(10, 30, 92, 14);
		contentPane.add(etiqNombre);
		
		JLabel etiqGolesF = new JLabel("Goles a favor");
		etiqGolesF.setBounds(10, 70, 92, 14);
		contentPane.add(etiqGolesF);
		
		JLabel etiqGolesC = new JLabel("Goles en contra");
		etiqGolesC.setBounds(10, 109, 92, 14);
		contentPane.add(etiqGolesC);
		
		JLabel etiqPartidosG = new JLabel("Partidos ganados");
		etiqPartidosG.setBounds(10, 149, 92, 14);
		contentPane.add(etiqPartidosG);
		
		JLabel etiqPartidosP = new JLabel("Partidos perdidos");
		etiqPartidosP.setBounds(10, 190, 121, 14);
		contentPane.add(etiqPartidosP);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(143, 27, 121, 20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		textoGolesF = new JTextField();
		textoGolesF.setBounds(141, 67, 123, 20);
		contentPane.add(textoGolesF);
		textoGolesF.setColumns(10);
		
		textoGolesC = new JTextField();
		textoGolesC.setBounds(141, 106, 123, 20);
		contentPane.add(textoGolesC);
		textoGolesC.setColumns(10);
		
		textoPartidosG = new JTextField();
		textoPartidosG.setBounds(141, 146, 123, 20);
		contentPane.add(textoPartidosG);
		textoPartidosG.setColumns(10);
		
		textoPartidosP = new JTextField();
		textoPartidosP.setBounds(141, 187, 123, 20);
		contentPane.add(textoPartidosP);
		textoPartidosP.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar Datos");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Recogemos los datos introducidos en los campos de texto y se lo pasamos al objeto equipo
				equipo.setNombre(textoNombre.getText());
				equipo.setGolesFavor(Integer.valueOf(textoGolesF.getText()));
				equipo.setGolesContra(Integer.valueOf(textoGolesC.getText()));
				equipo.setPartidosGanados(Integer.valueOf(textoPartidosG.getText()));
				equipo.setPartidosPerdidos(Integer.valueOf(textoPartidosP.getText()));
				
				//Usamos el método para guardar los datos en un fichero	
				guardarEnFichero();			
			}
		});
		btnGuardar.setBounds(10, 254, 155, 23);
		contentPane.add(btnGuardar);
		
		JButton btnLeerDatos = new JButton("Leer Datos");
		btnLeerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llamada al método leerFichero
				recuperarFichero();
				//Introducimos los datos del fichero leido en los campos de texto
				textoNombre.setText(equipo.getNombre());
				textoGolesF.setText(String.valueOf(equipo.getGolesFavor()));
				textoGolesC.setText(String.valueOf(equipo.getGolesContra()));
				textoPartidosG.setText(String.valueOf(equipo.getPartidosGanados()));
				textoPartidosP.setText(String.valueOf(equipo.getPartidosPerdidos()));		
			}
		});
		btnLeerDatos.setBounds(244, 254, 163, 23);
		contentPane.add(btnLeerDatos);
		
		JButton btnSave = new JButton("Guardar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Llamada al método guardarEquipo
				guardarEquipo();
				
			}
		});
		btnSave.setBounds(295, 29, 92, 55);
		contentPane.add(btnSave);
		
		//Llamada al método leerEquipo para que aparezca en los campos de texto el equipo que vamos a modificar del array
		leerEquipo();
	}
	
	//Método para guardar en fichero
	private void guardarEnFichero() {	
		try {	//Abre el archivo
			salida = new ObjectOutputStream(new FileOutputStream("equipo.ser"));
			salida.writeObject(equipo); //Envía el registro como salida
			textoNombre.setText("");
			textoGolesC.setText("");
			textoGolesF.setText("");
			textoPartidosG.setText("");
			textoPartidosP.setText("");
			if (salida != null)
				salida.close();
		} catch (IOException ioExcepction) {
			System.err.println("Error al abrir el archivo");		
		}
	}
	
	//Método para recuperar los datos guardados en fichero
	private void recuperarFichero() {
		try {
			entrada = new ObjectInputStream(new FileInputStream("equipo.ser"));
			equipo=(Equipo)entrada.readObject();
			if (entrada != null)
				entrada.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//Método para guardar datos introducidos en equipo
	private void guardarEquipo() {
		equipo.setNombre(textoNombre.getText());
		equipo.setGolesFavor(Integer.valueOf(textoGolesF.getText()));
		equipo.setGolesContra(Integer.valueOf(textoGolesC.getText()));
		equipo.setPartidosGanados(Integer.valueOf(textoPartidosG.getText()));
		equipo.setPartidosPerdidos(Integer.valueOf(textoPartidosP.getText()));
		
		//Instrucción para que añada equipo si no es una modificación
		if(!modifica) {
			comboBox.addItem(equipo);
		//Sino cambia el nombre del equipo	
		}else{
			Equipo equipoElegido=(Equipo)comboBox.getSelectedItem();
			equipoElegido.setNombre(equipo.getNombre());
		}
	}
	
	//Método para poner equipo a modificar del array en los campos de texto
	private void leerEquipo() {
		textoNombre.setText(equipo.getNombre());
		textoGolesF.setText(String.valueOf(equipo.getGolesFavor()));
		textoGolesC.setText(String.valueOf(equipo.getGolesContra()));
		textoPartidosG.setText(String.valueOf(equipo.getPartidosGanados()));
		textoPartidosP.setText(String.valueOf(equipo.getPartidosPerdidos()));
	}
	
}

