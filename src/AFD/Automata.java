package AFD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Hashtable;

import AFD.Main.States;

public class Automata {

	//Defining constants for initial and final states
	private static final States INITIAL_STATE = States.S0;
    private static final States STRING = States.S2;
    private static final States INTEGER = States.S4;
    private static final States REAL = States.S6;
    private static final States ERROR = States.S10;
	
    private String str;
    private Hashtable<String, String> hash;
    
    //Get input and create Hash
    public Automata(String name) {
        this.str = name;
        File file= new File(str);
        //If file exists it's a path otherwise is a string to be validated
        if(file.exists()) {
        	try{
        		BufferedReader buf = new BufferedReader(new FileReader(str));
        		str = "";
        		String line;
        		while((line = buf.readLine()) != null) {
        			str += line + '\n';
        		}
        	}
        	catch(Exception e){};
        }
        this.createHash();
    }
    
    //Initialing Hash
    private void createHash() {
    	hash = new Hashtable<String, String>();
    	hash.put("aleatorio","RESERVED WORD");
    	hash.put("abs","RESERVED WORD");
    	hash.put("algoritmo","RESERVED WORD");
    	hash.put("arccos","RESERVED WORD");
    	hash.put("arcsen","RESERVED WORD");
    	hash.put("arctan","RESERVED WORD");
    	hash.put("arquivo","RESERVED WORD");
    	hash.put("asc","RESERVED WORD");
    	hash.put("ate","RESERVED WORD");
    	hash.put("caracter","RESERVED WORD");
    	hash.put("caso","RESERVED WORD");
    	hash.put("compr","RESERVED WORD");
    	hash.put("copia","RESERVED WORD");
    	hash.put("cos","RESERVED WORD");
    	hash.put("cotan","RESERVED WORD");
    	hash.put("cronometro","RESERVED WORD");
    	hash.put("debug","RESERVED WORD");
    	hash.put("declare","RESERVED WORD");
    	hash.put("e","RESERVED WORD");
    	hash.put("eco","RESERVED WORD");
    	hash.put("enquanto","RESERVED WORD");
    	hash.put("entao","RESERVED WORD");
    	hash.put("escolha","RESERVED WORD");
    	hash.put("escreva","RESERVED WORD");
    	hash.put("exp","RESERVED WORD");
    	hash.put("faca","RESERVED WORD");
    	hash.put("falso","RESERVED WORD");
    	hash.put("fimalgoritmo","RESERVED WORD");
    	hash.put("fimenquanto","RESERVED WORD");
    	hash.put("fimescolha","RESERVED WORD");
    	hash.put("fimfuncao","RESERVED WORD");
    	hash.put("fimpara","RESERVED WORD");
    	hash.put("fimprocedimento","RESERVED WORD");
    	hash.put("fimrepita","RESERVED WORD");
    	hash.put("fimse","RESERVED WORD");
    	hash.put("funcao","RESERVED WORD");
    	hash.put("grauprad","RESERVED WORD");
    	hash.put("inicio","RESERVED WORD");
    	hash.put("int","RESERVED WORD");
    	hash.put("interrompa","RESERVED WORD");
    	hash.put("leia","RESERVED WORD");
    	hash.put("literal","RESERVED WORD");
    	hash.put("log","RESERVED WORD");
    	hash.put("logico","RESERVED WORD");
    	hash.put("logn","RESERVED WORD");
    	hash.put("maiusc","RESERVED WORD");
    	hash.put("mensagem","RESERVED WORD");
    	hash.put("minusc","RESERVED WORD");
    	hash.put("nao","RESERVED WORD");
    	hash.put("numerico","RESERVED WORD");
    	hash.put("numpcarac","RESERVED WORD");
    	hash.put("ou","RESERVED WORD");
    	hash.put("outrocaso","RESERVED WORD");
    	hash.put("para","RESERVED WORD");
    	hash.put("passo","RESERVED WORD");
    	hash.put("pausa","RESERVED WORD");
    	hash.put("pi","RESERVED WORD");
    	hash.put("pos","RESERVED WORD");
    	hash.put("procedimento","RESERVED WORD");
    	hash.put("quad","RESERVED WORD");
    	hash.put("radpgrau","RESERVED WORD");
    	hash.put("raizq","RESERVED WORD");
    	hash.put("rand","RESERVED WORD");
    	hash.put("randi","RESERVED WORD");
    	hash.put("repita","RESERVED WORD");
    	hash.put("se","RESERVED WORD");
    	hash.put("sen","RESERVED WORD");
    	hash.put("senao","RESERVED WORD");
    	hash.put("timer","RESERVED WORD");
    	hash.put("tan","RESERVED WORD");
    	hash.put("var","RESERVED WORD");
    	hash.put("verdadeiro","RESERVED WORD");
    	hash.put("xou","RESERVED WORD");
    }

    //Get string
    public String getString() {
        return str;
    }
 
    //Run each character of string through AFD
    public void find() {
    	State current = INITIAL_STATE;
        for (char symbol : str.toCharArray()) {
            current = current.next(symbol);  
            if(current == States.S2) {
            	current = current.next(symbol); //Return to S0 after each final state
            	System.out.println(" -> STRING");
            }
            if(current == States.S4) {
            	current = current.next(symbol); 
            	System.out.println(" -> INTEGER");
            }
            if(current == States.S6) {
            	current = current.next(symbol); 
            	System.out.println(" -> REAL");
            }
            if(current == States.S8) {
            	current = current.next(symbol); 
            	String word = current.getInput();
            	if(!this.hash.containsKey(word)) {
            		hash.put(word, "IDENTIFIER");
            	}
            	System.out.println(" -> " + hash.get(word));
            }
            if(current == States.S11) {
            	current = current.next(symbol); 
            	System.out.println(" -> COMMENT");
            }
            if(current == States.S12) {
            	current = current.next(symbol); 
            	System.out.println(" -> ERROR");
            }
        }
    }
   
}
