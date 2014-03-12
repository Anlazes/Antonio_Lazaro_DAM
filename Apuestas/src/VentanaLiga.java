import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaLiga extends JFrame {
	
	//Atributos de la clase
	private Equipo equipo;
	private Liga liga;
	private JPanel contentPane;
	private JTextField textoNombreLiga;
	private JTextField textoModificar;
	private JTextField textoNumero;


	//Constructor de la ventana
	public VentanaLiga(Liga ligaModificar) {
		
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
		
		JLabel etiqModificar = new JLabel("Equipo a modificar");
		etiqModificar.setBounds(21, 70, 107, 14);
		contentPane.add(etiqModificar);
		
		textoModificar = new JTextField();
		textoModificar.setBounds(21, 95, 128, 20);
		contentPane.add(textoModificar);
		textoModificar.setColumns(10);
		
		JLabel etiqNumero = new JLabel("N\u00FAmero de equipos");
		etiqNumero.setBounds(203, 11, 128, 14);
		contentPane.add(etiqNumero);
		
		textoNumero = new JTextField();
		textoNumero.setEnabled(false);
		textoNumero.setBounds(203, 36, 96, 20);
		contentPane.add(textoNumero);
		textoNumero.setColumns(10);
		textoNumero.setText(String.valueOf(liga.getNumEquipos()));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Crea y muestra la ventana para crear un equipo
				VentanaEquipo frameEquipo = new VentanaEquipo(liga.getEquipo(Integer.valueOf(textoModificar.getText())));
				frameEquipo.setVisible(true);
				frameEquipo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnModificar.setBounds(21, 143, 89, 23);
		contentPane.add(btnModificar);
		
		//Ponemos el nombre de la liga creada en el campo de texto
		textoNombreLiga.setText(liga.getNombreLiga());
	}
}
