package AFD;

public class Main {
	
	static boolean isNumber(char symbol) {
		switch(symbol) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				return true;
			default:
				return false;
		}
	}
	
	static boolean isAlph(char symbol) {
		switch(symbol) {
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'y':
			case 'z':
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'Y':
			case 'Z':
				return true;
			default:
				return false;
		}
	}
	
	static boolean isSpecial(char symbol) {
		switch(symbol) {
			case '!':
			case '@':
			case '#':
			case '$':
			case '%':
			case '¨':
			case '&':
			case '*':
			case '(':
			case ')':
			case '-':
			case '_':
			case '=':
			case '+':
			case '\\':
			case '|':
			case ',':
			case '>':
			case '.':
			case '<':
			case ';':
			case ':':
			case '~':
			case '^':
			case ']':
			case '}':
			case '[':
			case '{':
			case '´':
			case '`':
			case '/':
				return true;
			default:
				return false;
		}
		
	}
	
	enum States implements State {

		S0 {
			@Override
	        public State next(char symbol) {
				if(isNumber(symbol)) return S3;
				else if(isAlph(symbol)) return S0; //S5
				else if(symbol == '"') return S1;
				else return S0; //ERROR
	        }
		},
		
		S1 {
			@Override
	        public State next(char symbol) {
				if((symbol == ' ') || isNumber(symbol) || isAlph(symbol) || isSpecial(symbol)) return S1;
				else if(symbol == '"') return S2;
				else return S10; //ERROR
	        }
			
		},
		
		S2 { //STRING
			@Override
	        public State next(char symbol) {
				return S2;
	        }
			
		},
		S3 { 
			@Override
	        public State next(char symbol) {
				if(isNumber(symbol)) return S3;
				else if(symbol == ' ' || symbol == '\n') return S4;
				else if(symbol == '.') return S5;
				else return S10; //ERROR
	        }
			
		},
		S4 { //INTEGER
			@Override
	        public State next(char symbol) {
				return S4;
	        }
			
		},
		S5 {
			@Override
	        public State next(char symbol) {
				if(isNumber(symbol)) return S5;
				else if(symbol == ' ' || symbol == '\n') return S6;
				else return S10; //ERROR
	        }
			
		},
		S6 { //REAL
			@Override
	        public State next(char symbol) {
				return S6;
	        }
			
		},
		
		S10 { //ERROR
			@Override
	        public State next(char symbol) {
				return S10;
	        }
			
		};
	}
	
	public static void main(String[] args) {
		Automata aut1 = new Automata('"' + "string" + '"');
		Automata aut2 = new Automata("1 ");
		Automata aut3 = new Automata("212311 ");
		Automata aut4 = new Automata("1.0 ");
		Automata aut5 = new Automata("12323.01123 ");
		System.out.println(aut1.find());
		System.out.println(aut2.find());
		System.out.println(aut3.find());
		System.out.println(aut4.find());
		System.out.println(aut5.find());
		
	}
}
