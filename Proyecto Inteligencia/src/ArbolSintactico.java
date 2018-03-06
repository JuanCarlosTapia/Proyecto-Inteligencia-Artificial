import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Juan Carlos Tapia Flores  14133
 * Inteligencia Artificial
 * Clase que crea el arbol sintactico que contiene las partes de la red bayesiana
 * */

public class ArbolSintactico {
	Node raiz;
	int NumAmbito = 1;
	//String ambito = "Global";
	
	public ArbolSintactico(ParseTree tree) {
		super();
		
		raiz = CrearArbol(tree);
	}
	
	
	public Node CrearArbol(ParseTree tree ){
		//System.out.println(tree.getPayload());
    	if(tree.getChildCount()==0){
    		String ob =  tree.getPayload().toString();
    		
    		int i = ob.indexOf("'");
    		int j = ob.substring(i+1, ob.length()).indexOf("'")+i+1;
    	
    		Node newChild = new Node(ob.substring(i+1, j));
    		
    		return newChild;
    	}
    	else
    	{
    		Node newChild = new Node(tree.getPayload().getClass().toString().replaceAll("Context", "").replaceAll("RedBayesiana", "").replaceAll("Parser", "").replaceAll("$", "").replaceAll("class", "").substring(2));
    		
    		for(int i=0;i<tree.getChildCount();i++ ){
    			
    			
    			Node n = CrearArbol(tree.getChild(i));
    			
    			newChild.addHijos(n);
    		}
    		return newChild;
    		
    	}
		
	}
	
	
	
	
	
	
	public void MostrarArbol(Node tree, int nivel, DefaultMutableTreeNode node){
		nivel++;
		if(tree.getHijos().size()==0){
			
		
    		return;
    	}
    	else
    	{
    	
    		for(int i=0;i<tree.getHijos().size();i++ ){
    			//node.add(new DefaultMutableTreeNode(tree.getHijos().get(i).getData()+","+tree.getHijos().get(i).getAmbito()));
    			
    			node.add(new DefaultMutableTreeNode(tree.getHijos().get(i).getData()));
    			
    			//node.add(new DefaultMutableTreeNode(tree.getHijos().get(i).getData()));
    			MostrarArbol(tree.getHijos().get(i),nivel,(DefaultMutableTreeNode) node.getChildAt(i));
    		}
    		
    		
    	}
	}
	
	
	
	
	public void AsignarBloques(Node tree, String ambito){
		
		if(tree.getHijos().size()==0){
			
//			tree.setAmbito(ambito);
    		return;
    	}
    	else
    	{
    		
    	    if(tree.getData().equals("MethodDeclaration")){
    	    	//ambito = tree.getHijos().get(1).getData();
    	    	
    	    	
    	    }   
    	    
    	   
    	    
    	   // tree.setAmbito(ambito);
    	    
    	    	
    	    String am2 = ambito;
    	    
    	    if(tree.getData().equals("Block")){
    	    	am2 = ambito+ "-Block"+Integer.toString(NumAmbito);
    	    	NumAmbito++;
		   }
    	    
    		for(int i=0;i<tree.getHijos().size();i++ ){
    			 
    			AsignarBloques(tree.getHijos().get(i),am2);
    		}
    		
    		
    	}
	}
	
	
	
	 public static String tabs(int cantidad){
	    	String s = "";
	    	for(int i=0;i<cantidad;i++ ){
	    		s = s+"	";
	    	}
	    	return s;
	    }
	
	
	
	
}
