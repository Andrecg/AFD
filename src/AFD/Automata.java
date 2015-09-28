package AFD;

import java.util.Hashtable;

import AFD.Main.States;

public class Automata {

	//Defining constants for initial and final states
	private static final States INITIAL_STATE = States.S0;
    private static final States STRING = States.S2;
    private static final States INTEGER = States.S4;
    private static final States REAL = States.S6;
    private static final States ERROR = States.S10;
	
    private final String str;
    
    //Get input and create Hash
    public Automata(String str) {
        this.str = str;
        this.createHash();
    }
    
    //Initialing Hash
    private void createHash() {
    	Hashtable<String, String> hash = new Hashtable<String, String>();
    	hash.put("RES", "aleatorio");
    	hash.put("RES", "abs");
    	hash.put("RES", "algoritmo");
    	hash.put("RES", "arccos");
    	hash.put("RES", "arcsen");
    	hash.put("RES", "arctan");
    	hash.put("RES", "arquivo");
    	hash.put("RES", "asc");
    	hash.put("RES", "ate");
    	hash.put("RES", "caracter");
    	hash.put("RES", "caso");
    	hash.put("RES", "compr");
    	hash.put("RES", "copia");
    	hash.put("RES", "cos");
    	hash.put("RES", "cotan");
    	hash.put("RES", "cronometro");
    	hash.put("RES", "debug");
    	hash.put("RES", "declare");
    	hash.put("RES", "e");
    	hash.put("RES", "eco");
    	hash.put("RES", "enquanto");
    	hash.put("RES", "entao");
    	hash.put("RES", "escolha");
    	hash.put("RES", "escreva");
    	hash.put("RES", "exp");
    	hash.put("RES", "faca");
    	hash.put("RES", "falso");
    	hash.put("RES", "fimalgoritmo");
    	hash.put("RES", "fimenquanto");
    	hash.put("RES", "fimescolha");
    	hash.put("RES", "fimfuncao");
    	hash.put("RES", "fimpara");
    	hash.put("RES", "fimprocedimento");
    	hash.put("RES", "fimrepita");
    	hash.put("RES", "fimse");
    	hash.put("RES", "funcao");
    	hash.put("RES", "grauprad");
    	hash.put("RES", "inicio");
    	hash.put("RES", "int");
    	hash.put("RES", "interrompa");
    	hash.put("RES", "leia");
    	hash.put("RES", "literal");
    	hash.put("RES", "log");
    	hash.put("RES", "logico");
    	hash.put("RES", "logn");
    	hash.put("RES", "maiusc");
    	hash.put("RES", "mensagem");
    	hash.put("RES", "minusc");
    	hash.put("RES", "nao");
    	hash.put("RES", "numerico");
    	hash.put("RES", "numpcarac");
    	hash.put("RES", "ou");
    	hash.put("RES", "outrocaso");
    	hash.put("RES", "para");
    	hash.put("RES", "passo");
    	hash.put("RES", "pausa");
    	hash.put("RES", "pi");
    	hash.put("RES", "pos");
    	hash.put("RES", "procedimento");
    	hash.put("RES", "quad");
    	hash.put("RES", "radpgrau");
    	hash.put("RES", "raizq");
    	hash.put("RES", "rand");
    	hash.put("RES", "randi");
    	hash.put("RES", "repita");
    	hash.put("RES", "se");
    	hash.put("RES", "sen");
    	hash.put("RES", "senao");
    	hash.put("RES", "timer");
    	hash.put("RES", "tan");
    	hash.put("RES", "verdadeiro");
    	hash.put("RES", "xou");
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
            	System.out.println("STRING");
            }
            if(current == States.S4) {
            	current = current.next(symbol); 
            	System.out.println("INTEGER");
            }
            if(current == States.S6) {
            	current = current.next(symbol); 
            	System.out.println("REAL");
            }
            if(current == States.S8) {
            	current = current.next(symbol); 
            	System.out.println("IDENTIFIER ou RESERVED");
            	/*TODO
            	 * Save the word as it passes through the states
            	 * Look inside hash for this word
            	 * If it is not inside hash, put it on as "IDENT"
            	 * Otherwise show if it is RERSERVED or IDENTIFIER
            	 */
            }
            	
        }
    }
    
}
