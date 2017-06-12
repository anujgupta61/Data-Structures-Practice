import java . util . Scanner ;

class MyExpression {
	private int top = -1 ;
	private int topc = -1 ;
	private int tops = -1 ;
	private final static int MAXSIZE = 100 ;
	private float[] stk = new float[MAXSIZE] ;
	private char[] cstk = new char[MAXSIZE] ;
	private String[] sstk = new String[MAXSIZE] ;

	void push(float data) {
		this . stk[++ this . top] = data ;
	}

	float pop() {
		return this . stk[this . top --] ;
	}

	void pushc(char data) {
		this . cstk[++ this . topc] = data ;
	}

	char popc() {
		return this . cstk[this . topc --] ;
	}

	void pushs(String data) {
		this . sstk[++ this . tops] = data ;
	}

	String pops() {
		return this . sstk[this . tops --] ;
	}

	int getPrecedence(char op) {
		if(op == '^')
			return 4 ; // Highest
		if(op == '*' || op == '/')
			return 3 ;
		if(op == '+' || op == '-')
			return 2 ; // Lowest
		if(op == '(' || op == ')')
			return 1 ; 
		else
			return 0 ; // For empty stack
	}

	String infixToPostfix(String inf) {
		this . pushc('#') ; // For empty stack
		String postf = "" ;
		for(int i = 0 ; i < inf . length() ; i ++) {
			char c = inf . charAt(i) ;
			if(! isOp(c)) {
				postf += c ;
			} else {
				if(c == '(')
					this . pushc(c) ;
				else {
					if(c == ')') {
						while(this . cstk[topc] != '(') 
							postf += this . popc() ;
						this . popc() ; // For (
					} else {
						if(getPrecedence(c) > getPrecedence(this . cstk[topc])) {
							this . pushc(c) ;
						} else {
							while(getPrecedence(c) <= getPrecedence(this . cstk[topc]) && (c != '^')) { // Only Left associative
								postf += this . popc() ;
							}
							this . pushc(c) ;
						}
					}
				}
 			}
		}
		while(this . cstk[topc] != '#') {
			postf += this . popc() ;
		}
		this . popc() ;
		return postf ;
	}

	String infixToPrefix(String inf) {
		// Reverse infix expression , Exchange '(' and ')'
		String rinf = "" ;
		for(int i = inf . length() - 1 ; i >= 0 ; i --) {
			char c = inf . charAt(i) ;
			if(c != '(' && c != ')')
				rinf += c ;
			else {
				if(c == '(')
					rinf += ')' ;
				else 
					rinf += '(' ;
			}
		}
		// Convert to postfix
		String postf = infixToPostfix(rinf) ;
		return new StringBuilder(postf) . reverse() . toString() ;
	}

	String postfixToInfix(String postf) {
		for(int i = 0 ; i < postf . length() ; i ++) {
			char c = postf . charAt(i) ;
			if(isOp(c)) {
				String op2 = this . pops() ;
				String op1 = this . pops() ;
				String res = '(' + op1 + c + op2 + ')' ;
				this . pushs(res) ;
			} else {
				this . pushs(c + "") ;
			}
		}
		return this . pops() ;
	}

	String prefixToInfix(String pref) {
		for(int i = pref . length() - 1 ; i >= 0 ; i --) {
			char c = pref . charAt(i) ;
			if(isOp(c)) {
				String op1 = this . pops() ;
				String op2 = this . pops() ;
				String res = '(' + op1 + c + op2 + ')' ;
				this . pushs(res) ;
			} else {
				this . pushs(c + "") ;
			}
		}
		return this . pops() ;
	}

	String prefixToPostfix(String pref) {
		for(int i = pref . length() - 1 ; i >= 0 ; i --) {
			char c = pref . charAt(i) ;
			if(isOp(c)) {
				String op1 = this . pops() ;
				String op2 = this . pops() ;
				String res = op1 + op2 + c ;
				this . pushs(res) ;
			} else {
				this . pushs(c + "") ;
			}
		}
		return this . pops() ;
	}

	String postfixToPrefix(String postf) {
		for(int i = 0 ; i < postf . length() ; i ++) {
			char c = postf . charAt(i) ;
			if(isOp(c)) {
				String op2 = this . pops() ;
				String op1 = this . pops() ;
				String res = c + op1 + op2 ;
				this . pushs(res) ;
			} else {
				this . pushs(c + "") ;
			}
		}
		return this . pops() ;
	}

	boolean isOp(char ch) { 
		int val = (int)ch ; // Unicode value
		if(val >= 48 && val <= 57)
			return false ;
		else
			return true ;
	}

	float solve(float op1 , float op2 , char op) {
		if(op == '^')
			return (float)(Math . pow(op1 , op2)) ;
		if(op == '*')
			return op1 * op2 ;
		if(op == '/')
			return op1 / op2 ;
		if(op == '+')
			return op1 + op2 ;
		else
			return op1 - op2 ; 
	}

	float postfEval(String postf) { // Only binary operator
		for(int i = 0 ; i < postf . length() ; i ++) {
			char c = postf . charAt(i) ;
			if(isOp(c)) {
				float op2 = this . pop() ;
				float op1 = this . pop() ;
				float ans = solve(op1 , op2 , c) ;
				this . push(ans) ;
			} else {
				this . push((int)c - 48) ;
			}
		}
		return this . pop() ;
	}

	float prefEval(String pref) {
		for(int i = pref . length() - 1 ; i >= 0 ; i --) {
			char c = pref . charAt(i) ;
			if(isOp(c)) {
				float op1 = this . pop() ;
				float op2 = this . pop() ;
				float ans = solve(op1 , op2 , c) ;
				this . push(ans) ;
			} else {
				this . push((int)c - 48) ;
			}
		}
		return this . pop() ;
	}

	float infDirectEval(String inf) {
		this . pushc('#') ; // For empty stack
		for(int i = 0 ; i < inf . length() ; i ++) {
			char c = inf . charAt(i) ;
			if(! isOp(c)) {
				this . push((int)c - 48) ;
			} else {
				if(c == '(')
					this . pushc(c) ;
				else {
					if(c == ')') {
						while(this . cstk[topc] != '(') { 
							char op = this . popc() ;
							float op2 = this . pop() ;
							float op1 = this . pop() ;
							float ans = solve(op1 , op2 , op) ;
							this . push(ans) ;
						}
						this . popc() ; // For (
					} else {
						if(getPrecedence(c) > getPrecedence(this . cstk[topc])) {
							this . pushc(c) ;
						} else {
							while(getPrecedence(c) <= getPrecedence(this . cstk[topc]) && (c != '^')) { // Only Left associative
								char op = this . popc() ;
								float op2 = this . pop() ;
								float op1 = this . pop() ;
								float ans = solve(op1 , op2 , op) ;
								this . push(ans) ;
							}
							this . pushc(c) ;
						}
					}
				}
 			}
		}
		while(this . cstk[topc] != '#') {
			char op = this . popc() ;
			float op2 = this . pop() ;
			float op1 = this . pop() ;
			float ans = solve(op1 , op2 , op) ;
			this . push(ans) ;
		}
		return this . pop() ;
	}
}

public class Expression {
	public static void main(String[] args) {
		MyExpression myExp = new MyExpression() ;
		Scanner scan = new Scanner(System . in) ;
		int choice = 0 ;
		while(choice != 10) {
			System . out . println("1 : Infix To Postfix\n2 : Infix To Prefix\n3 : Postfix To Infix\n4 : Prefix To Infix\n5 : " + 
				"Prefix To Postfix\n6 : Postfix To Prefix\n7 : Postfix Evaluation\n8 : Prefix Evaluation\n9 : Infix Direct Evaluation\n" + 
				"10 : Exit") ;
			System . out . print("Enter your choice : ") ;
			choice = scan . nextInt() ;
			String exp ;
			switch(choice) {
				case 1 : {
					System . out . print("Enter Infix expression : ") ;
					exp = scan . next() ;
					System . out . println("Postfix expression : " + myExp . infixToPostfix(exp)) ;
					break ;
				}
				case 2 : {
					System . out . print("Enter Infix expression : ") ;
					exp = scan . next() ;
					System . out . println("Prefix expression : " + myExp . infixToPrefix(exp)) ;
					break ;
				}
				case 3 : {
					System . out . print("Enter Postfix expression : ") ;
					exp = scan . next() ;
					System . out . println("Infix expression : " + myExp . postfixToInfix(exp)) ;
					break ;
				}
				case 4 : {
					System . out . print("Enter Prefix expression : ") ;
					exp = scan . next() ;
					System . out . println("Infix expression : " + myExp . prefixToInfix(exp)) ;
					break ;
				}
				case 5 : {
					System . out . print("Enter Prefix expression : ") ;
					exp = scan . next() ;
					System . out . println("Postfix expression : " + myExp . prefixToPostfix(exp)) ;
					break ;
				}
				case 6 : {
					System . out . print("Enter Postfix expression : ") ;
					exp = scan . next() ;
					System . out . println("Prefix expression : " + myExp . postfixToPrefix(exp)) ;
					break ;
				}
				case 7 : {
					System . out . print("Enter Postfix expression to evaluate : ") ;
					exp = scan . next() ;
					System . out . println("Result : " + myExp . postfEval(exp)) ;
					break ;
				}
				case 8 : {
					System . out . print("Enter Prefix expression to evaluate : ") ;
					exp = scan . next() ;
					System . out . println("Result : " + myExp . prefEval(exp)) ;
					break ;
				}
				case 9 : {
					System . out . print("Enter Infix expression to evaluate : ") ;
					exp = scan . next() ;
					System . out . println("Result : " + myExp . infDirectEval(exp)) ;
					break ;
				}
				case 10 : {
					break ;
				}
				default : {
					System . out . println("Invalid input ... Try again ...") ;
				}
			}
		}
	}
}

// BrainStorming test case - (2^3+5*7-8/2^2)