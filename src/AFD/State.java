package AFD;

public interface State {
	//Force all states to implement the following method
	State next(char symbol);
	
	String getInput();
}
