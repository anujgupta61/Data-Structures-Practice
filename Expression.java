import java . util . Scanner ;

class MyExpression {
	private int top = -1 ;
	private final static int MAXSIZE = 100 ;
	private float[] stk = new float[MAXSIZE] ;
	private char[] cstk = new char[MAXSIZE] ;

	void push(float data) {
		this . stk[++ this . top] = data ;
	}

	float pop() {
		return this . stk[this . top --] ;
	}

	void pushc(char data) {
		this . cstk[++ this . top] = data ;
	}

	char popc() {
		return this . cstk[this . top --] ;
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
						while(this . cstk[top] != '(') 
							postf += this . popc() ;
						this . popc() ; // For (
					} else {
						if(getPrecedence(c) > getPrecedence(this . cstk[top])) {
							this . pushc(c) ;
						} else {
							while(getPrecedence(c) <= getPrecedence(this . cstk[top]) && (c != '^')) { // Only Left associative
								postf += this . popc() ;
							}
							this . pushc(c) ;
						}
					}
				}
 			}
		}
		while(this . cstk[top] != '#') {
			postf += this . popc() ;
		}
		this . popc() ;
		return postf ;
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
}

public class Expression {
	public static void main(String[] args) {
		MyExpression exp = new MyExpression() ;
		Scanner scan = new Scanner(System . in) ;
		System . out . print("Enter infix expression : ") ;
		String inf = scan . next() ;
		String postf = exp . infixToPostfix(inf) ;
		System . out . print("Converting to postfix expression : " + postf + "\n") ;
		System . out . print("Evaluating expression : " + exp . postfEval(postf) + "\n") ;
	}
}

// BrainStorming test case - (2^3+5*7-8/2^2)