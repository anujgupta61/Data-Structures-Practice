import java . util . Scanner ;

class MyStack {
	private int top = -1 ;
	private final static int MAXSIZE = 5 ;
	private int[] stk = new int[MAXSIZE] ;

	boolean isEmpty() {
		if(this . top == -1)
			return true ;
		else
			return false ;
	}

	boolean isFull() {
		if(this . top == MAXSIZE - 1)
			return true ;
		else
			return false ;
	}

	int peek() {
		if(this . isEmpty()) {
			System . out . println("Stack is empty ...") ;
			return -1 ; 
		}
		else
			return this . stk[this . top] ;
	}

	void push() {
		if(this . isFull()) {
			System . out . println("Stack is full . MaxSize is " + this . MAXSIZE + " . Can not push data .") ;
		} else {
			System . out . print("Enter data to push : ") ;
			Scanner scan = new Scanner(System . in) ;
			int data = scan . nextInt() ;
			this . stk[++ this . top] = data ;
			System . out . println(data + " has been pushed ...") ;
		}
	}

	void pop() {
		if(this . isEmpty())
			System . out . println("Stack is empty ...") ;
		else {
			int data = this . stk[this . top --] ;
			System . out . println(data + " has been popped ...") ;
		}
	}

	void printStk() {
		if(this . isEmpty())
			System . out . println("Stack is empty ...") ;
		else {
			System . out . println("Stack -") ;
			for(int i = this . top ; i >= 0 ; i --)
				System . out . println("\t" + this . stk[i]) ;
		}
	}
}

public class Stack {
	public static void main(String[] args) {
		MyStack mstk = new MyStack() ;
		Scanner scan = new Scanner(System . in) ;
		int choice = 0 ;
		while(choice != 4) {
			System . out . println("1 : Print stack\n2 : Push\n3 : Pop\n4 : Exit") ;
			System . out . print("Enter your choice : ") ;
			choice = scan . nextInt() ;
			switch(choice) {
				case 1 : {
					mstk . printStk() ;
					break ;
				}
				case 2 : {
					mstk . push() ;
					break ;
				}
				case 3 : {
					mstk . pop() ; 
					break ;
				}
				case 4 : {
					break ;
				}
				default : {
					System . out . println("Invalid input . Try again .") ;
				}
			}
		}
	}
}