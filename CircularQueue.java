import java.util.Scanner;

class MyCircularQueue {
	private final static int MAXSIZE = 5;
	private int front = -1, rear = -1;
	private int[] cqueue = new int[MAXSIZE];

	public boolean isFull() {
		int temp;
		if(this.rear == MAXSIZE - 1)
			temp = -1;
		else
			temp = this.rear;
		return this.front == temp + 1; 				
	}

	public boolean isEmpty() {
		return this.front == -1 && this.rear == -1;
	}

	public void enQ() {
		if(! this.isFull()) {
			System.out.print("Enter data to insert: ");
			Scanner scan = new Scanner(System.in);
			int data = scan.nextInt();
			if(this.rear == MAXSIZE - 1)
				this.rear = -1;
			this.cqueue[++ this.rear] = data;
			if(this.front == -1)
				this.front = 0;
			System.out.println(data + " has been inserted in circular queue ...");	
		} else {
			System.out.println("Circular queue is full ...");
		}
	}

	public void deQ() {
		if(! this.isEmpty()) {
			int data = this.cqueue[this.front];
			if(this.front == this.rear) {
 				this.front = -1;
 				this.rear = -1;
			} else {
				if(this.front == MAXSIZE - 1)
					this.front = 0;
				else
					this.front ++;
			}
			System.out.println(data + " has been deleted from circular queue ...");
		} else {
			System.out.println("Circular queue is empty ...");
		}
	}

	public void printQ() {
		System.out.println("Circular Queue -");
		if(! this.isEmpty()) {
			for(int i = this.front; i != this.rear; i ++) {
				System.out.print("\t" + this.cqueue[i]);
				if(i == MAXSIZE - 1)
					i = -1;
			}
			System.out.print("\t" + this.cqueue[this.rear] + "\n");
		} else {
			System.out.println("Circular queue is empty ...");
		}
	}
}

public class CircularQueue {
	public static void main(String[] args) {
		MyCircularQueue myCQ = new MyCircularQueue();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		while(choice != 4) {
			System.out.println("1: Insert data\n2: Delete data\n3: Print queue\n4: Exit");
			System.out.print("Enter your choice : ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					myCQ.enQ();
					break;
				}
				case 2: {
					myCQ.deQ();
					break;
				}
				case 3: {
					myCQ.printQ();
					break;
				}
				case 4: {
					break;
				}
				default: {
					System.out.println("Invalid input ... Try again ...");
				}
			}
		}
	}
}