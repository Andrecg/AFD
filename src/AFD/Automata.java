package AFD;

import AFD.Main.States;

public class Automata {

	private static final States INITIAL_STATE = States.S0;
    private static final States STRING = States.S2;
    private static final States INTEGER = States.S4;
    private static final States REAL = States.S6;
    private static final States ERROR = States.S10;
	
    private final String str;
    
    public Automata(String str) {
        this.str = str;
    }

    public String getCadeia() {
        return str;
    }
 
    public State find() {
    	State current = INITIAL_STATE;
        for (char symbol : str.toCharArray()) {
            current = current.next(symbol);
        }
        return current;
    }
    
}
