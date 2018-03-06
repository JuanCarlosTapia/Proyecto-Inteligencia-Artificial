
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.lang.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


/*
 * Juan Carlos Tapia Flores  14133
 * Inteligencia Artificial
 * */

public class Interfaz {

	private JFrame frame;
	private DirectDraw panel;
	int N = 20;
	private JTextField cuadros;
	private Problem problem;
	private BufferedImage image;
	private JTextArea redTextArea;
	private JFileChooser fileChooser;
	private JButton btnbfs;
	private JButton btndfs;
	private JButton btnaStar1;
	private JButton btnaStar2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public Interfaz() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1271, 853);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1253, 733);
		frame.getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Laberinto", null, panel_1, null);
		panel_1.setLayout(null);

		cuadros = new JTextField();
		panel_1.add(cuadros);
		cuadros.setText("15");
		cuadros.setBounds(55, 84, 116, 22);
		cuadros.setColumns(10);

		//Boton que genera el laberinto segun N cuadros
		JButton btnGenerar = new JButton("Generar");
		panel_1.add(btnGenerar);
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					N = Integer.parseInt(cuadros.getText());
					MostrarImagen("Imagen.bmp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnGenerar.setBounds(55, 136, 116, 25);

		btnaStar1 = new JButton("A* Euclidiana");
		panel_1.add(btnaStar1);
		btnaStar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MostrarImagen("Imagen.bmp");
					LinkedHashMap<Point, Point> path = problem.graphSearch(3);
					
					if (path.size()== 0){
						System.out.println("No hay solucion");
					}
					for (Point key : path.keySet()) {
						// System.out.println(key.getX() + " " + key.getY());
					}

					dibujarCamino(image, path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnaStar1.setBounds(49, 301, 122, 25);

		// Boton que activa el Depth Spet First
		btndfs = new JButton("DSF");
		panel_1.add(btndfs);
		btndfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MostrarImagen("Imagen.bmp");
					
					LinkedHashMap<Point, Point> path = problem.graphSearch(2);
					if (path.size()== 1){
						System.out.println("No hay solucion");
					}
					for (Point key : path.keySet()) {
						// System.out.println(key.getX() + " " + key.getY());
					}

					dibujarCamino(image, path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btndfs.setBounds(49, 245, 122, 25);

		//Boton que activa el Breath Step First
		btnbfs = new JButton("BSF");
		panel_1.add(btnbfs);
		btnbfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MostrarImagen("Imagen.bmp");
					LinkedHashMap<Point, Point> path = problem.graphSearch(1);
					for (Point key : path.keySet()) {
						//System.out.println(key.getX() + "  " + key.getY());
					}

					dibujarCamino(image, path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnbfs.setBounds(49, 193, 122, 25);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 51, 110, -49);
		panel_1.add(scrollPane);

		panel = new DirectDraw(1000, 1000);
		panel.setBounds(320, 40, 727, 650);
		panel_1.add(panel);
		panel.setLayout(null);

		//Boton que activa el A*
		btnaStar2 = new JButton("A* Maxima");
		btnaStar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MostrarImagen("Imagen.bmp");

					LinkedHashMap<Point, Point> path = problem.graphSearch(4);
					for (Point key : path.keySet()) {
						// System.out.println(key.getX() + " " + key.getY());
					}

					dibujarCamino(image, path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnaStar2.setBounds(55, 361, 116, 25);
		panel_1.add(btnaStar2);

		// Boton que busca la imagen.
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						// What to do with the file, e.g. display it in a TextArea
						N = Integer.parseInt(cuadros.getText());
						MostrarImagen(file.getAbsolutePath());
						// textarea.read( new FileReader( file.getAbsolutePath() ), null );
					} catch (IOException ex) {
						System.out.println("problem accessing file" + file.getAbsolutePath());
					}
				} else {
					System.out.println("File access cancelled by user.");
				}
			}
		});
		btnBrowse.setBounds(374, 13, 97, 25);
		panel_1.add(btnBrowse);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Redes Bayesianas", null, panel_2, null);
		panel_2.setLayout(null);

		// Boton que lee la red bayesiana.
		JButton btnNewButton = new JButton("Leer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Uso de ANTLR para leer la Red Bayesiana para ver que esta escrita correctamente.
				ANTLRInputStream input = new ANTLRInputStream(redTextArea.getText());
				RedBayesianaLexer lexer = new RedBayesianaLexer(input);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				MyErrorListener errorr = new MyErrorListener();
				lexer.removeErrorListeners();
				lexer.addErrorListener(errorr);
				RedBayesianaParser parser = new RedBayesianaParser(tokens);
				parser.removeErrorListeners();
				parser.addErrorListener(errorr);

				// Se crea el arbol de parseo
				ParseTree tree = parser.descripcion();
				
				// Se muestran los errores
				int errores = parser.getNumberOfSyntaxErrors();
				String s = "";
				for (int i = 0; i < errorr.ErrorMessages.size(); i++) {
					// System.out.println(errorr.ErrorMessages.get(i));
					s = s + errorr.ErrorMessages.get(i) + '\n';
				}
				System.out.println(s);

				if (errores == 0) {
					// Se construye un arbol para leer las partes del arbol sintactico.
					ArbolSintactico arbol = new ArbolSintactico(tree);

					arbol.AsignarBloques(arbol.raiz, "Global");
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(arbol.raiz.getData());
					arbol.MostrarArbol(arbol.raiz, -1, node);
					
					// Se analiza la semantica de la red bayesiana para obtener sus partes.
					Semantica sem = new Semantica();
					sem.Analizar(arbol.raiz);
					System.out.println("Variables: ");
					for (String key : sem.getVariables().keySet()) {
						System.out.println(key);
					}

					System.out.println("Parametros: ");
					for (String key : sem.getParametros().keySet()) {
						System.out.println("P( " + key + " ) = " + sem.getParametros().get(key));
					}

					System.out.println("Forma Compacta");
					System.out.println(sem.getFormaCompacta());

					// Prompt para ingresar queries.
					Scanner scanmer = new Scanner(System.in);
					while (true) {
						System.out.print("> ");
						String p = scanmer.nextLine();
						System.out.println("P(" + p + ")");
						System.out.println("> r:" + sem.Calcular(p));
					}
				}
			}
		});
		btnNewButton.setBounds(464, 178, 97, 25);
		panel_2.add(btnNewButton);

		redTextArea = new JTextArea();
		redTextArea.setText("P(A) = 0.30\r\n" + "P(B) = 0.23\r\n" + "P(C|A, B) = 0.20\r\n" + "P(C|!A, B) = 0.10\r\n"
				+ "P(C|A, !B) = 0.77\r\n" + "P(C|!A, !B) = 0.5\r\n" + "");
		redTextArea.setBounds(46, 66, 285, 299);
		panel_2.add(redTextArea);


	}

	// Metodo que muestra la imagen en cuadricula y obtiene las partes del Problema..
	private void MostrarImagen(String archivo) throws IOException {
		// if (fileChooser.getSelectedFile()getClass())
		File bmpFile = new File(archivo);
		image = ImageIO.read(bmpFile);
		int width = image.getWidth();
		int height = image.getHeight();

		int width2 = Math.round(image.getWidth() / N) * N;
		int height2 = Math.round(image.getHeight() / N) * N;
		// panel = new DirectDraw(width2, height2);

		boolean has_start = false;

		Point initialState = null;
		LinkedHashMap<Point, Point> goals = new LinkedHashMap<Point, Point>();
		LinkedHashMap<Point, Point> walls = new LinkedHashMap<Point, Point>();
		LinkedHashMap<Point, Point> free = new LinkedHashMap<Point, Point>();

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// System.out.println(x +" " + y);

				int colorRGB = elegirColor(image, x, y);
				int x_inicio = ((int) image.getWidth() / N) * x;
				int x_final = ((int) image.getWidth() / N) * (x + 1);

				int y_inicio = ((int) image.getHeight() / N) * y;
				int y_final = ((int) image.getHeight() / N) * (y + 1);

				if (x_inicio >= width2) {
					x_inicio = width2 - 1;
				}
				if (x_final >= width2) {
					x_final = width2 - 1;
				}
				if (y_inicio >= height2) {
					y_inicio = height2 - 1;
				}
				if (y_final >= height2) {
					y_final = height2 - 1;
				}
				
				// Se cuentan los colores en un cuadro.
				LinkedHashMap<Integer, Integer> conteoColores = new LinkedHashMap<Integer, Integer>();
				for (int i = x_inicio; i < x_final; i++) {
					for (int j = y_inicio; j < y_final; j++) {
						int color = image.getRGB(i, j);
						if (conteoColores.containsKey(colorRGB)) {
							int valorActual = conteoColores.get(colorRGB);
							conteoColores.put(colorRGB, valorActual + 1);
						} else {
							conteoColores.put(colorRGB, 1);
						}
					}
				}

				int color = mayor(conteoColores);
				Point p = new Point(x, y);
				
				// Luego de obtener el color que predomina en el cuadro, se realiza una accion dependiendo del color.
				switch (color) {
				// Rojo
				case -65536:
					// solo puede haber un punto de inicio.
					if (has_start) {
						color = -1;

					} else {
						has_start = true;
						initialState = p;

					}
					break;
				// Verde
				case -16711936:
					goals.put(p, p);
					break;
				// Negro
				case -16777216:
					walls.put(p, p);
					break;
				case -1:
					free.put(p, p);
					break;
				}

				panel.drawRect(color, x_inicio, y_inicio, x_final, y_final);
				panel.drawRect(Color.yellow, x_inicio, y_inicio, x_final, y_inicio + 1);
				panel.drawRect(Color.yellow, x_inicio, y_inicio, x_inicio + 1, y_final);
			}
		}
		

		frame.getContentPane().repaint();

		problem = new Problem(N, initialState, walls, free, goals);
		
		
	}

	// Se pinta el camino que devolvio la busqueda.
	private void dibujarCamino(BufferedImage image, LinkedHashMap<Point, Point> path) {
		int x_inicio = 0;
		int x_final = 0;
		int y_inicio = 0;
		int y_final = 0;
		for (Point key : path.keySet()) {
			int x = (int) key.getX();
			int y = (int) key.getY();

			x_inicio = ((int) image.getWidth() / N) * x;
			x_final = ((int) image.getWidth() / N) * (x + 1);

			y_inicio = ((int) image.getHeight() / N) * y;
			y_final = ((int) image.getHeight() / N) * (y + 1);
			if (problem.initialState.equals(key)) {
				panel.drawRect(Color.red, x_inicio, y_inicio, x_final, y_final);
			} else if (problem.goals.containsKey(key)) {
				panel.drawRect(Color.GREEN, x_inicio, y_inicio, x_final, y_final);
			} else {
				panel.drawRect(Color.pink, x_inicio, y_inicio, x_final, y_final);

			}
		}

		int width2 = Math.round(image.getWidth() / N) * N;
		int height2 = Math.round(image.getHeight() / N) * N;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// System.out.println(x +" " + y);

				x_inicio = ((int) image.getWidth() / N) * x;
				x_final = ((int) image.getWidth() / N) * (x + 1);

				y_inicio = ((int) image.getHeight() / N) * y;
				y_final = ((int) image.getHeight() / N) * (y + 1);

				if (x_inicio >= width2) {
					x_inicio = width2 - 1;
				}
				if (x_final >= width2) {
					x_final = width2 - 1;
				}
				if (y_inicio >= height2) {
					y_inicio = height2 - 1;
				}
				if (y_final >= height2) {
					y_final = height2 - 1;
				}

				panel.drawRect(Color.yellow, x_inicio, y_inicio, x_final, y_inicio + 1);
				panel.drawRect(Color.yellow, x_inicio, y_inicio, x_inicio + 1, y_final);
			}
		}
	}

	private int elegirColor(BufferedImage image, int x, int y) {
		LinkedHashMap<Integer, Integer> conteoColores = new LinkedHashMap<Integer, Integer>();

		int x_inicio = ((int) image.getWidth() / N) * x;
		int x_final = ((int) image.getWidth() / N) * (x + 1);

		int y_inicio = ((int) image.getHeight() / N) * y;
		int y_final = ((int) image.getHeight() / N) * (y + 1);

		for (int i = x_inicio; i < x_final; i++) {
			for (int j = y_inicio; j < y_final; j++) {
				int colorRGB = image.getRGB(i, j);
				if (conteoColores.containsKey(colorRGB)) {
					int valorActual = conteoColores.get(colorRGB);
					conteoColores.put(colorRGB, valorActual + 1);
				} else {
					conteoColores.put(colorRGB, 1);
				}
			}
		}

		return mayor(conteoColores);
	}

	private int mayor(LinkedHashMap<Integer, Integer> LinkedHashMap) {
		int MayorValor = 0;
		int MayorColor = 0;
		for (Integer key : LinkedHashMap.keySet()) {
			int value = LinkedHashMap.get(key);
			if (value > MayorValor) {
				MayorColor = key;
				MayorValor = value;
			}

		}

		return MayorColor;
	}
}
