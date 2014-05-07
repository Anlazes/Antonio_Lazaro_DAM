import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JComboBox;


public class VentanaLiga extends JFrame {
	
	//Atributos de la clase
	private Equipo equipo;
	private Liga liga;
	private JPanel contentPane;
	private JTextField textoNombreLiga;
	private JTextField textoNumero;
	private JComboBox<Equipo> comboBox;
	private Connection conexion = null; //maneja la conexión a la base de datos



	//Constructor de la ventana
	public VentanaLiga(Liga ligaModificar) {
		
		//Conexión a la base de datos
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//establece la conexión a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/apuestas","root","");
		} catch (SQLException sqlexcep) {
			sqlexcep.printStackTrace();		
		} catch (ClassNotFoundException e) {			
				e.printStackTrace(); } 
		
		//Asignación de liga
		liga=ligaModificar;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etiqNombreLiga = new JLabel("Nombre de la Liga");
		etiqNombreLiga.setBounds(21, 11, 107, 14);
		contentPane.add(etiqNombreLiga);
		
		textoNombreLiga = new JTextField();
		textoNombreLiga.setBounds(21, 36, 128, 20);
		contentPane.add(textoNombreLiga);
		textoNombreLiga.setColumns(10);
		
		JLabel etiqModificar = new JLabel("Equipos");
		etiqModificar.setBounds(21, 76, 107, 14);
		contentPane.add(etiqModificar);
		
		JLabel etiqNumero = new JLabel("N\u00FAmero de equipos");
		etiqNumero.setBounds(203, 11, 128, 14);
		contentPane.add(etiqNumero);
		
		textoNumero = new JTextField();
		textoNumero.setEnabled(false);
		textoNumero.setBounds(203, 36, 96, 20);
		contentPane.add(textoNumero);
		textoNumero.setColumns(10);
		textoNumero.setText(String.valueOf(liga.getNumEquipos()));
		
		//Botón para modificar un equipo de la liga
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llama al método para abrir la ventana Equipo para modificar
				abrirVentanaEquipo((Equipo) comboBox.getSelectedItem(),true);
			}
		});
		btnModificar.setBounds(210, 140, 89, 23);
		contentPane.add(btnModificar);
		
		//Ponemos el nombre de la liga creada en el campo de texto
		textoNombreLiga.setText(liga.getNombreLiga());
		
		comboBox = new JComboBox<Equipo>();
		comboBox.setBounds(21, 101, 181, 20);
		contentPane.add(comboBox);
		
		//Botón para añadir un equipo a la liga
		JButton btnAnyadir = new JButton("+");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llama al método para añadir un equipo al arrayList
				liga.anyadirEquipo();
				//Llama al método para abrir la ventana Equipo para añadir un equipo
				abrirVentanaEquipo(liga.getEquipo(liga.getNumEquipos()-1), false);
				
			}
		});
		btnAnyadir.setBounds(21, 140, 48, 23);
		contentPane.add(btnAnyadir);
		
		//Botón para quitar un equipo de la liga
		JButton btnQuitar = new JButton("-");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Llamada al método para eliminar un equipo en la posición seleccionada en comboBox
				liga.quitarEquipo(comboBox.getSelectedIndex());
				//Eliminamos del comboBox el item seleccionado
				comboBox.removeItem((Equipo)comboBox.getSelectedItem());
			}
		});
		btnQuitar.setBounds(91, 140, 48, 23);
		contentPane.add(btnQuitar);
	}
	
	//Método para abrir la ventana Equipo
	public void abrirVentanaEquipo(Equipo equipo, boolean modifica) {
		VentanaEquipo frameEquipo=new VentanaEquipo(equipo, this.comboBox,modifica);
		frameEquipo.setVisible(true);
		frameEquipo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
