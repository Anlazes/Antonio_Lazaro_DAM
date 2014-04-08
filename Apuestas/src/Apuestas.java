import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Apuestas extends JFrame {

	private JPanel contentPane;
	private JTextField textoNombreLiga;
	private Liga liga;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Lanzador de la ventana principal
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apuestas frame = new Apuestas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Apuestas() {
		setTitle("Administrador de Apuestas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministracion = new JLabel("Administraci\u00F3n de Ligas");
		lblAdministracion.setBounds(10, 11, 139, 14);
		contentPane.add(lblAdministracion);
		
		JLabel lblNombreLiga = new JLabel("Nombre de la liga");
		lblNombreLiga.setBounds(10, 36, 104, 14);
		contentPane.add(lblNombreLiga);
		
		textoNombreLiga = new JTextField();
		textoNombreLiga.setBounds(10, 61, 255, 20);
		contentPane.add(textoNombreLiga);
		textoNombreLiga.setColumns(10);
		
		JButton btnAdministrar = new JButton("Administrar");
		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Llamada al m�todo para abrir la ventana Liga
				abrirVentanaLiga(liga = new Liga(textoNombreLiga.getText())); 
	
			}
		});
		btnAdministrar.setBounds(10, 116, 115, 23);
		contentPane.add(btnAdministrar);
		
		JButton btnGenerar = new JButton("Generar apuesta");
		btnGenerar.setBounds(10, 176, 208, 23);
		contentPane.add(btnGenerar);
		
		JButton btnSeguimiento = new JButton("Seguimiento de apuestas");
		btnSeguimiento.setBounds(10, 211, 208, 23);
		contentPane.add(btnSeguimiento);
	}
	
	//M�todo para abrir la ventana Liga
		public void abrirVentanaLiga(Liga liga) {
			VentanaLiga frameLiga=new VentanaLiga(liga);
			frameLiga.setVisible(true);
			frameLiga.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
}
