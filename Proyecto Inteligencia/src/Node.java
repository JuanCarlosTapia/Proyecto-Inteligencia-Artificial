/*
 * Juan Carlos Tapia Flores  14133
 * Inteligencia Artificial
 * 
 * Clase que contiene un nodo del arbol sintactico
 * */

import java.util.ArrayList;

public class Node {
	
	// Atributos
	
		 private String data;
       
        private ArrayList<Node>  hijos = new ArrayList<Node>();
        
      
        public Node(String data) {
			super();
			this.data = data;
		}
        

		public String getData() {
			return data;
		}



		public void setData(String data) {
			this.data = data;
		}


		public ArrayList<Node> getHijos() {
			return hijos;
		}



		public void setHijos(ArrayList<Node> hijos) {
			this.hijos = hijos;
		}
        
        public void addHijos(Node hijo){
        	
        	hijos.add(hijo);
        }
        
}
        
        
 
        
       
		
		
		