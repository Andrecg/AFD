package AFD;

public abstract class State {

	String value;
	
	abstract void append(char character);
	
	abstract String getValue();
}
