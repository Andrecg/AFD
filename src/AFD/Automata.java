package AFD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.LinkedList;

public class Automata {

	//Defining constants for initial and final states
	private static final States INITIAL_STATE = States.S0;
    private static final States STRING = States.S2;
    private static final States INTEGER = States.S4;
    private static final States REAL = States.S6;
    private static final States ERROR = States.S10;
	
    private String str;
    
    static Hashtable<String, String> hash;
    static LinkedList<String> word, type;
    
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
				else if(symbol == '/') return S9;
				else if(symbol == ' ') return S0;
				else if(symbol == '\n') return S0;
				else return S12; //ERROR
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
					return S12; //ERROR
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
				word.add(read);
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
					return S12; //ERROR
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
				word.add(read);
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
				else return S12; //ERROR
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
					return S12;
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
				word.add(read);
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		
		S9 { 
			@Override
	        public State next(char symbol) {
				if(symbol == '/') {
					read += symbol;
					return S10;
				}
				else return S12;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		},
		
		S10 { 
			@Override
	        public State next(char symbol) {
				if(symbol == '\n') return S11;
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
		
		S11 { //COMMENT
			@Override
	        public State next(char symbol) {
				word.add(read);
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
		},

		S12 { //ERROR
			@Override
	        public State next(char symbol) {
				word.add(read);
				System.out.print(read);
				return S0;
	        }

			@Override
			public String getInput() {
				return read;
			}
			
		};
	}
  
    //Get input and create Hash
    public Automata(String name) {
    	word = new LinkedList<String>();
    	type = new LinkedList<String>();
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
            	type.add("STRING");
            	System.out.println(" -> STRING");
            }
            if(current == States.S4) {
            	current = current.next(symbol);
            	type.add("INTEGER");
            	System.out.println(" -> INTEGER");
            }
            if(current == States.S6) {
            	current = current.next(symbol);
            	type.add("REAL");
            	System.out.println(" -> REAL");
            }
            if(current == States.S8) {
            	current = current.next(symbol); 
            	String word = current.getInput();
            	if(!this.hash.containsKey(word)) {
            		hash.put(word, "IDENTIFIER");
            	}
            	type.add(hash.get(word));
            	System.out.println(" -> " + hash.get(word));
            }
            if(current == States.S11) {
            	current = current.next(symbol);
            	type.add("COMMENT");
            	System.out.println(" -> COMMENT");
            }
            if(current == States.S12) {
            	current = current.next(symbol);
            	type.add("ERROR");
            	System.out.println(" -> ERROR");
            }
        }
    }
   
    public LinkedList<String> getWord() {
    	return Automata.word;
    }
    
    public LinkedList<String> getType() {
    	return Automata.type;
    }
    
}
