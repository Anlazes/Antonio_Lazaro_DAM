import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

	//Constructor VentanaEquipo
	public VentanaEquipo(Equipo equipoAModificar) {
		
		//Asignación equipo
		equipo=equipoAModificar;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		etiqPartidosP.setBounds(10, 190, 92, 14);
		contentPane.add(etiqPartidosP);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(114, 27, 121, 20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		textoGolesF = new JTextField();
		textoGolesF.setBounds(112, 67, 123, 20);
		contentPane.add(textoGolesF);
		textoGolesF.setColumns(10);
		
		textoGolesC = new JTextField();
		textoGolesC.setBounds(112, 106, 123, 20);
		contentPane.add(textoGolesC);
		textoGolesC.setColumns(10);
		
		textoPartidosG = new JTextField();
		textoPartidosG.setBounds(112, 146, 123, 20);
		contentPane.add(textoPartidosG);
		textoPartidosG.setColumns(10);
		
		textoPartidosP = new JTextField();
		textoPartidosP.setBounds(112, 187, 123, 20);
		contentPane.add(textoPartidosP);
		textoPartidosP.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar Equipo");
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
		btnGuardar.setBounds(122, 228, 155, 23);
		contentPane.add(btnGuardar);
		
	}
	
	//Método para guardar en fichero
	private void guardarEnFichero() {	
		try {	//Abre el archivo
			salida = new ObjectOutputStream(new FileOutputStream("equipo.ser"));
			salida.writeObject(equipo); //Envía el registro como salida
			if (salida != null)
				salida.close();
		} catch (IOException ioEscepction) {
			System.err.println("Error al abrir el archivo");		
		}
	}
}
