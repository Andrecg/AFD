package AFD;

public interface State {
	State next(char symbol);
}
