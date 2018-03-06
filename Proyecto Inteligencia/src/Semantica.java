import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultMutableTreeNode;


/*
 * Juan Carlos Tapia Flores  14133
 * Inteligencia Artificial
 * Clase para controlar la semantica de la Red Bayesiana.
 * */


public class Semantica {
	public HashMap<String, String> variables;
	public LinkedHashMap<String, Float> parametros;
	public LinkedHashMap<String, Float> parametrosCompletos;
	public LinkedHashMap<String, String> componentesCompacta;
	
	public Semantica(){
		variables = new HashMap<String, String>();
		parametros = new LinkedHashMap<String, Float>();
		parametrosCompletos = new LinkedHashMap<String, Float>();
		componentesCompacta = new LinkedHashMap<String, String>();
	}
	
	
	
   public void Evaluar(Node arbol){
	   Analizar(arbol);
	   
   }
   
	// Metodo que recorre el arbol para obtener las partes. Es una funcion recursiva.
	public String Analizar(Node tree){
		
		if(tree.getHijos().size()==0){
			
		
    		return "";
    	}
    	else
    	{
    		// Conjunto de expresiones
    		if(tree.getData().equals("Descripcion")){
    			for(int i=0;i<tree.getHijos().size();i++ ){
    				Analizar(tree.getHijos().get(i));	
    			}
    		}
    		
    		
    		// Una expresion seria  P(A,!C|B) = 0.3
    		if(tree.getData().equals("Expresion")){
    			String parametro = Analizar(tree.getHijos().get(1));
    			float valor = Float.parseFloat(tree.getHijos().get(4).getData());
    			parametros.put(parametro, valor);
    			
    			if (!parametro.contains("!")) {
    				parametrosCompletos.put(parametro.trim(), valor);
        			parametrosCompletos.put("!"+parametro.trim(), 1-valor);
    			} else {
    				parametrosCompletos.put(parametro.trim(), valor);
        			parametrosCompletos.put(parametro.substring(1), 1-valor);
    			}
    			componentesCompacta.put(parametro.replaceAll("!", ""), parametro.replaceAll("!", ""));

    		}
    		// Un Parametro seria  A,!C|B
    		if(tree.getData().equals("Parametro")){
    			String parametro = "";
    			for(int i=0;i<tree.getHijos().size();i=i+2 ){	
    				if (i!=0) {
    					parametro += "|";
    				}
    				parametro += Analizar(tree.getHijos().get(i));
    			}
    			return parametro;
    		}
    		
    		// Unos eventos seria A,!C  y B
    		if(tree.getData().equals("Evento")){
    			String evento = "";
    			for(int i=0;i<tree.getHijos().size();i=i+2 ){	
    				if (i!=0) {
    					evento += ",";
    				}
    				evento += Analizar(tree.getHijos().get(i));
    				
    			}
    			return evento;
    		}
    		
    		
    		// Unas logicas serian A  y !C
    		if(tree.getData().equals("Logica")){
    			// Aqui se van guardando las variables que se encuentren
    			if (tree.getHijos().size() == 1) {
    				String var = tree.getHijos().get(0).getData();
    				variables.put(var, var);
    				return var;
    			} else {
    				String var = tree.getHijos().get(1).getData();
    				variables.put(var, var);
    				return "!"+var;
    			}
    		}
    	}
 		return null;
	}
	
	
	
	// Metodo para calcular un query
	public float Calcular(String operacion) {
		operacion = operacion.replace(" ", "");
		if(parametrosCompletos.containsKey(operacion)) {
			return parametrosCompletos.get(operacion);
		}
		
		String operacionMod = "";
		
		// Se transforma la operacion hasta llegar a la forma compacta. 
		if (operacion.contains("|")) {
			//System.out.println("Cadena: ");
			operacionMod = ReglaCadena(operacion);
			System.out.println("Forma conjunta: "+operacionMod);
			operacionMod = ProbabilidadTotal(operacionMod);
			System.out.println("Forma expandida: "+operacionMod);
			operacionMod = FormaCompacta(operacionMod);
			System.out.println("Forma compacta: "+operacionMod);
		} else {
			
			operacionMod = ProbabilidadTotal(operacion);
			System.out.println("Forma expandida: "+operacionMod);
			operacionMod = FormaCompacta(operacionMod);
			System.out.println("Forma compacta: "+operacionMod);
		}
		
		// Se calcula el resultado
		return CalcularExpresion(operacionMod);
		
	}

	// Metodo para calcular la expresion. Recibe la operacion en terminos de forma compacta.
	private float CalcularExpresion(String operacion) {
		
		// Se separa la operacion en division, sumas y multiplicaciones.
		String[] componentesDivision = operacion.split(Pattern.quote(" / "));
		float resultadoTotal = 0;
		for(int i=0;i<componentesDivision.length;i++ ){
			String[] componentesSuma = componentesDivision[i].split(Pattern.quote(" + "));
			float resultadoSuma = 0;
			for(int j=0;j<componentesSuma.length;j++ ){
				
				String[] componentesMultiplicacion = componentesSuma[j].split(Pattern.quote(" * "));
				float resultadoMultiplicacion = 1;
				for(int k=0;k<componentesMultiplicacion.length;k++ ){
					String componente = componentesMultiplicacion[k].replace("P(", "").replace(")", "").replaceAll(" " , "");
					
					// Se llama al Hashmap para obtener el valor.
					resultadoMultiplicacion *= parametrosCompletos.get(componente.trim());	
				}
				resultadoSuma += resultadoMultiplicacion;	
			}
					
				
			if (i == 0) {
				resultadoTotal = resultadoSuma;
			} else {
				resultadoTotal /= resultadoSuma;
			}
			
		
		}
		return resultadoTotal;
	}


	// Metodo que devuelve las partes de una operacion en terminos de la forma compacta.
	private String FormaCompacta(String operacion) {
		
		// Se divide la operacion en division y sumas
		String[] componentesDivision = operacion.split(Pattern.quote(" / "));
		String totalCompacta = "";
		for(int i=0;i<componentesDivision.length;i++ ){
			if (i!=0) {
				totalCompacta+=" / ";
			}
			String[] componentesSuma = componentesDivision[i].split(Pattern.quote(" + "));
			for(int j=0;j<componentesSuma.length;j++ ){
				if (j!=0) {
					totalCompacta+=" + ";
				}
				
				String componente = componentesSuma[j].replace("P(", "").replace(")", "").replaceAll(" ", "");
				
				// Se llama a la forma compacta.
				String formaCompacta = getFormaCompacta();
				
				String[] variables = componente.split(Pattern.quote(","));
				// Y se reemplazan las variables.
				for(int k=0;k<variables.length;k++ ){
					String var = variables[k].replace("!", "");
					formaCompacta = formaCompacta.replaceAll(Pattern.quote(var), variables[k]);
				}
				totalCompacta += formaCompacta;
				
			}
		}
		return totalCompacta;
		
	}


	// Se devuelve una operacion transformada con la regla de probabilidad total
	private String ProbabilidadTotal(String operacion) {
		String total = "";
		
		// Se divide la operacion en divisiones.
		String[] componentes = operacion.split(Pattern.quote(" / "));
		for(int j=0;j<componentes.length;j++ ){
			if (j!=0) {
				total+=" / ";
			}
			String componente = componentes[j].replace("P(", "").replace(")", "");
			
			// Se obtienen las variables que faltan para meter como sumatoria
			ArrayList<String> variablesFaltantes = getVariablesFaltantes(componente);
			
			if (variablesFaltantes.size() != 0) {
				// Se obtienen las posibilidades para combinaciones con las variables que faltan
				String[] PosibildadesVariablesFaltantes = getPosibilidadesSumatoria(variablesFaltantes);
				
				// Se crean las expresiones. 
				for(int i=0;i<PosibildadesVariablesFaltantes.length;i++ ){
					if (i!=0) {
						total+=" + ";
					}
					total +=  "P("+componente;
					total += ","+PosibildadesVariablesFaltantes[i];
					total += ")";
				}
			} else {
				total +=  "P("+componente;
				total += ") ";
			}
			
		}
		
		return total;
	}

	// Devuelve las posibilidades logicas de un conjunto de variables. EJ:  A, B devuelve:  A,B  A,!B  !A,B  !A!B
	private String[] getPosibilidadesSumatoria(ArrayList<String> listaVariables) {
		int numPosibilidades = (int) Math.pow(2, listaVariables.size());
		String[] posibilidades = new String[numPosibilidades];
		for(int i=0;i<posibilidades.length;i++ ){
			posibilidades[i] = "";
 		}
		for(int i=0;i<listaVariables.size();i++ ){
			int j=0;
			while (j<numPosibilidades){
				
				int pow = (int) Math.pow(2, listaVariables.size()-i-1);
				
				for(int k=0;k<pow;k++ ){
					if (!posibilidades[j].equals("")) {
						posibilidades[j] += ",";
					}
					posibilidades[j] += listaVariables.get(i);
					j++;
					
				}
				
				for(int k=0;k<pow;k++ ){
					if (!posibilidades[j].equals("")) {
						posibilidades[j] += ",";
					}
					posibilidades[j] += "!"+listaVariables.get(i);
					j++;
				}
				
			}
			
		}
		return posibilidades;
		
	}
	
	// Obtiene las variables que no estan incluidas en un parametro.
	private ArrayList<String> getVariablesFaltantes(String parametro) {
		ArrayList<String> variablesFaltantes = new ArrayList<String>();
		
		String[] variablesActuales = parametro.split(Pattern.quote(","));
		HashMap<String, String> variablesActualesHashMap = new HashMap<String, String>();
		
		for(int i=0;i<variablesActuales.length;i++ ){
			variablesActualesHashMap.put(variablesActuales[i].replace("!", ""), variablesActuales[i].replace("!", ""));
		}
		
		for (String var : variables.keySet()) {
			if (!variablesActualesHashMap.containsKey(var)) {
				variablesFaltantes.add(var);
			}
		}
		
		return variablesFaltantes;
	}


	// Metodo que aplica la regla de la cadena
	private String ReglaCadena (String operacion) {
		String[] partes = operacion.split(Pattern.quote("|"));
		String operacionMod = "P("+partes[0]+","+partes[1]+") / P("+partes[1]+")";
		
		return operacionMod;
	}
	
	// Metodo que devuelve la forma compacta de la red Bayesiana.
	public String getFormaCompacta () {
		String compacta = "";
		for (String key : componentesCompacta.keySet()) {
        	compacta += " * P("+key+")";
		}
		return compacta.substring(2);
		
	}
	
	public  HashMap<String, String> getVariables() {
		return this.variables;
	}

	public void setErrores(HashMap<String, String> variables) {
		this.variables = variables;
	}

	public LinkedHashMap<String, Float> getParametros() {
		return parametros;
	}

	public void setParametros(LinkedHashMap<String, Float> parametros) {
		this.parametros = parametros;
	}
	
	
	
}
