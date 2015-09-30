package AFD;

public class Main {
	
	//Test if character is a number {0:9}
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
	
	//Test if character is a letter {Aa:zZ}
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
	
	//Test if character is a special character
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
	
	static String read = "";
	
	enum States implements State {
		
		//All final states (S2,S4,S6,S8) return to Initial State (S0)
		S0 { //INITIAL STATE
			@Override
	        public State next(char symbol) {
				read = "";
				read += symbol;
				if(isNumber(symbol)) return S3;
				else if(isAlph(symbol)) return S7;
				else if(symbol == '"') return S1;
				else if(symbol == ' ') return S0;
				else if(symbol == '\n') return S0;
				else return S10; //ERROR
	        }

			@Override
			public String getInput() {
				return read;
			}
		},
		
		S1 { 
			@Override
	        public State next(char symbol) {
				if((symbol == ' ') || isNumber(symbol) || isAlph(symbol) || isSpecial(symbol)) {
					read += symbol;
					return S1;
				}
				else if(symbol == '"') {
					read += symbol;
					return S2;
				}
				else {
					read += symbol;
					return S10; //ERROR
				}
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		
		S2 { //STRING
			@Override
	        public State next(char symbol) {
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		S3 { 
			@Override
	        public State next(char symbol) {
				if(isNumber(symbol)) {
					read += symbol;
					return S3;
				}
				else if(symbol == ' ' || symbol == '\n') return S4;
				else if(symbol == '.') {
					read += symbol;
					return S5;
				}
				else {
					read += symbol;
					return S10; //ERROR
				}
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		S4 { //INTEGER
			@Override
	        public State next(char symbol) {
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		S5 {
			@Override
	        public State next(char symbol) {
				if(isNumber(symbol)) {
					read += symbol;
					return S5;
				}
				else if(symbol == ' ' || symbol == '\n') return S6;
				else return S10; //ERROR
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		S6 { //REAL
			@Override
	        public State next(char symbol) {
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		S7 { 
			@Override
	        public State next(char symbol) {
				if(isNumber(symbol) || isAlph(symbol)) {
					read += symbol;
					return S7;
				}
				else if(symbol == ' ' || symbol == '\n' || symbol == ':' || symbol == ',') return S8;
				else {
					read += symbol;
					return S10;
				}
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		S8 { //IDENTIFIER or RESERVED WORD
			@Override
	        public State next(char symbol) {
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		
		S10 { //ERROR
			@Override
	        public State next(char symbol) {
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		};
	}
	
	public static void main(String[] args) {
		//Run an example with several tokens inside one string 'simulating' a visuAlg file
		Automata aut1 = new Automata(	'"' + "string" + '"' + 
										" 1\n" +
										"212311\n" + 
										"1.0 " + 
										"12323.01123 " + 
										"algoritmo " +
										"teste1:" +
										"1A ");
		aut1.find();
	}
}
