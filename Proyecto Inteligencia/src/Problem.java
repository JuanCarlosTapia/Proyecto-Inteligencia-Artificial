import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;

/*
 * Juan Carlos Tapia Flores  14133
 * Inteligencia Artificial
 * 
 * Clase que representa el problema
 * */

public class Problem {
	int size;
	// Estado inicial
	Point initialState;
	// Paredes
	LinkedHashMap<Point, Point> walls;
	// ESpacios vacios
	LinkedHashMap<Point, Point> spaces;
	
	// Puntos de llegada
	LinkedHashMap<Point, Point> goals;
	public Problem(int size, Point initialState, LinkedHashMap<Point, Point> walls, LinkedHashMap<Point, Point> spaces, LinkedHashMap<Point, Point> goals) {
		super();
		this.size = size;
		this.initialState = initialState;
		this.walls = walls;
		this.spaces = spaces;
		this.goals = goals;
	}
	
	// Metodo para las acciones. Recibe un estado (coordenada) y revisa en que direcciones se puede mover.
	public ArrayList<Integer> actions(Point state) {
		
		int x = (int) state.getX();
		int y = (int) state.getY();
		
		Point p = new Point(x, y);
		
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		// Cada accion esta representada con un numero.
		if (freeSpace(x+1, y)) {
			moves.add(1); 
		}
		
		if (freeSpace(x-1, y)) {
			moves.add(2); 
		}
		
		if (freeSpace(x, y+1)) {
			moves.add(3);
		}
		
		if (freeSpace(x, y-1)) {
			moves.add(4); 
		}
		
		
		
		return moves;
		
	}
	
	// Metodo que recibe una cordenada y una accion y devuelve el estado luego de esa accion.
	public Point result (Point state, int action ) {
		
		int x = (int) state.getX();
		int y = (int) state.getY();
		Point r = new Point(x,y);
		switch (action) {
			case 1: 
				r.setLocation(x+1, y);
				break;
			case 2:
				r.setLocation(x-1, y);
				break;
			case 3: 
				r.setLocation(x, y+1);
				break;
			case 4: 
				r.setLocation(x, y-1);
				break;
		}
		
		return r;
	}
	
	// Metodo que revisa que un estado sea un punto de llegada.
	public boolean goalTest(Point state) {
		
		if (goals.containsKey(state)) 
			return true;
		
		return false;
	}
	
	// Metodo que obtiene el costo de un camino
	public double pathCost(LinkedHashMap<Point, Point> path) {
		double pc = path.size()-1; 
		return pc;
		
	}
	
	// Metodo que revisa si un espacio esta vacio o no se sale del limite.
	public boolean freeSpace(int x, int y) {
		Point p = new Point(x, y);
		
		if (walls.containsKey(p)) 
			return false;
		
		if (x >= size || x < 0 )
			return false;
		
		if (y >= size || y < 0 )
			return false;
		return true;
	}
	
	
	// Metodo para hacer Graph Search. Recibe un entero que indica el metodo de busqueda.
	public LinkedHashMap<Point, Point> graphSearch (int method) {
		// Arreglo de caminos que contendra todas las fronteras.
		ArrayList<LinkedHashMap<Point, Point>> frontier = new ArrayList<LinkedHashMap<Point, Point>>();
		LinkedHashMap<Point, Point> f = new LinkedHashMap<Point, Point>();
		f.put(initialState, initialState);
		
		frontier.add(f);
		
		// Hashmap que contendra los cuadros explorados.
		LinkedHashMap<Point, Point> explored = new LinkedHashMap<Point, Point>();
		Point r = null;
		int iter= 0;
		long startTime = System.currentTimeMillis();
		while (true) {
			// El ciclo continua mientras haya fronteras por explorar.
			if (frontier.size()> 0 ) {
				LinkedHashMap<Point, Point> path = null;
				
				// La unica diferencia entre los metodos es que frontera toca explorar.
				switch (method) {
					
					// Breath Step First siempre recorre primero la mas corta, que es la que esta de primero
					case 1:
						 path = frontier.remove(0);
						break;
					case 2:
						// Depth Step First siempre recorre primero la mas larga, que es la que esta de ultimo
						path = path = frontier.remove(frontier.size()-1);
						break;
						
					// A* recorre la de menor heuristica. Hay 2 opciones. La euclidiana.
					case 3:
						path = path = getFrontierAStar(frontier, 1);
						break;
						// Y la maxima
					case 4:
						path = path = getFrontierAStar(frontier, 2);
						break;
				}
				// Se obtiene el punto al final del camino
				Point s = getLast(path);
				
				// Se añade a explorados
				explored.put(s, s);
				
				// Se revisa si es una meta, lo cual termina la ejecucion.
				if (goalTest(s)) {
					long endTime = System.currentTimeMillis() - startTime;
					//System.out.println("Breath Step First");
					System.out.println("Tiempo de ejecucion (milisegundos): "+ endTime);
					System.out.println("Iteraciones: "+iter);
					return path;
				}
				
				// Se obtienen las acciones posibles
				ArrayList<Integer> aList = actions(s); 
				
				// Se realizan las acciones
				for (int i = 0; i < aList.size(); i++) {
		        	int a = aList.get(i);
		        	r = result(s, a);
		        	// Si el resultado de la accion no esta explorado
					if (!explored.containsKey(r)) {
						
						// Se añade un nuevo camino a las fronteras.
						LinkedHashMap<Point, Point> newPath = new LinkedHashMap<Point, Point>();
						newPath.putAll(path);
						newPath.put(r, r);
						frontier.add(newPath);
					}
				}
				iter++;
			} else {
				return f;
			}
		}
	}
	
	// Metodo para obtener la frontera con menor heuristica.
	private LinkedHashMap<Point, Point> getFrontierAStar(ArrayList<LinkedHashMap<Point, Point>> frontier, int method) {
		double min = 99999999;
		int selected = -1; 
		for (int i = 0; i < frontier.size(); i++) {
			Point s  = getLast(frontier.get(i));
			
			double h = pathCost(frontier.get(i))+ getHeuristic(s, method);
			if (h < min) {
				selected = i;
				min = h;
			}
		}
		return frontier.remove(selected);
	}
	
	
	// metodo para obtener la heuristica de un punto
	private double  getHeuristic (Point state, int method){
		double min = 9999999;
		// Se debe revisar la heuristica relativa a cada meta y devolver la menor.
		for (Point key : goals.keySet()) {
			double h = 0;
			// La heuristica depende del metodo que se llame.
			switch (method) {
				case 1:
					h = HeuristicVectorCalc(state, key);
					break;
				case 2:
					h = HeuristicMaxCalc(state, key);
					break;
			}
			
			if (h < min) {
				min = h;
			}
        	
		}
		
		return min;
		
	}
	
	// Este metodo devuelve la hipotenusa entre 2 puntos
	private double HeuristicVectorCalc (Point a,  Point b) {
		
		double h = Math.sqrt(Math.pow(b.getX()-a.getX() , 2) + Math.pow(b.getY()-a.getY() , 2)) ; 
		
		return h;
	}
	
	// Este metodo devuelve la mayor distancia (X o Y) entre los 2 puntos
	private double HeuristicMaxCalc (Point a,  Point b) {
		
		double h = Math.max(Math.abs(b.getX()-a.getX()), Math.abs(b.getY() - a.getX()));
		return h;
	}

	// Metodo que devuelve el ultimo punto al final de un camino
	public Point getLast (LinkedHashMap<Point, Point> path) {
		Point p = null;
		for (Point key : path.keySet()) {
	        p = path.get(key);
	    }
		return p;
		
	}
	
}
