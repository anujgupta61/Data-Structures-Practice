import java . util . Scanner ;

class MyQueue {
	private int front = 0 ;
	private int rear = -1 ;
	private final static int MAXSIZE = 5 ;
	private int[] queue = new int[MAXSIZE] ;
	private int itemCount = 0 ;

	public boolean isFull() {
		//return this . rear == MAXSIZE - 1 ;
		return this . itemCount == MAXSIZE ;
	}

	public boolean isEmpty() {
		//return this . front > this . rear ;
		return this . itemCount == 0 ;
	}

	public void enQ() {
		if(! this . isFull()) {
			System . out . print("Enter data to insert : ") ;
			Scanner scan = new Scanner(System . in) ;
			int data = scan . nextInt() ;
			if(this . rear == this . MAXSIZE - 1)
				this . rear = -1 ;
			this . queue[++ this . rear] = data ;
			System . out . println(data + " has been inserted in queue ...") ;
			this . itemCount ++ ;
		} else {
			System . out . println("Queue is full . Can not insert data . MaxSize is " + this . MAXSIZE) ;
		}
	}

	public void deQ() {
		if(! this . isEmpty()) {
			int data = this . queue[this . front ++] ;
			if(this . front == this . MAXSIZE)
				this . front = 0 ;
			System . out . println(data + " has been deleted ...") ;
			this . itemCount -- ;
		} else {
			System . out . println("Queue is empty . Can not delete data .") ;
		}
	}

	public void printQ() {
		if(this . isEmpty())
			System . out . println("Queue is empty ...") ;
		else {
			System . out . println("Front - " + this . front + " , Rear - " + this . rear) ;
			System . out . print("Queue - ") ;
			for(int i = this . front , j = 0 ; j < this . itemCount ; i ++ , j ++) {
				if(i == this . MAXSIZE)
					i = 0 ;
				System . out . print(this . queue[i] + " ") ;
			}
			System . out . println("\n") ;	
		}
	}
}

public class Queue {
	public static void main(String[] args) {
		MyQueue myQ = new MyQueue() ;
		Scanner scan = new Scanner(System . in) ;
		int choice = 0 ;
		while(choice != 4) {
			System . out . println("1 : Insert data\n2 : Delete data\n3 : Print queue\n4 : Exit") ;
			System . out . print("Enter your choice : ") ;
			choice = scan . nextInt() ;
			switch(choice) {
				case 1 : {
					myQ . enQ() ;
					break ;
				}
				case 2 : {
					myQ . deQ() ;
					break ;
				}
				case 3 : {
					myQ . printQ() ;
					break ;
				}
				case 4 : {
					break ;
				}
				default : {
					System . out . println("Invalid input ... Try again ...") ;
				}
			}
		}
	}
}