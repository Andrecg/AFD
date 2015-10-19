package AFD;

/**
 * Interface class to be implement by each state
 * @author Andre
 * @author Aurelio
 * @author Cesar
 */
public interface State {
	//Force all states to implement the following method
	State next(char symbol);
	String getInput();
}
