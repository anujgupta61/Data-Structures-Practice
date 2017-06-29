import java.util.Scanner;

class MyGraph {
	private int n; // Vertices: 0 to n - 1
	private int[][] edg; // 0 - No edge, 1 - Edge
	private int[] stk;
	private int[] q;
	private int top = -1, front = -1, rear = -1;

	public void insertGraph() {
		System.out.print("Enter number of vertices: ");
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.edg = new int[this.n][this.n];
		this.stk = new int[this.n];
		this.q = new int[this.n];
		System.out.println("Now input edges(Adjacency matrix) -");
		for(int i = 0; i < this.n; i ++) {
			for(int j = 0; j < this.n; j ++)
				this.edg[i][j] = scan.nextInt();
		}
		System.out.println("Graph inserted successfully ...");		
	}

	private void push(int num) {
		this.stk[++ this.top] = num;
	}

	private int pop() {
		return this.stk[this.top --];
	}

	private boolean isStkEmpty() {
		return (this.top == -1);
	}

	private void enQ(int num) {
		if(this.front == -1 && this.rear == -1) { // Inserting first element
			this.front = 0;
			this.rear = 0;
		} else {
			this.rear = (this.rear + 1) % this.n;
		}
		this.q[this.rear] = num;
	}

	private int deQ() {
		int num = this.q[this.front];
		if(this.front == this.rear) {
			this.front = -1;
			this.rear = -1;
		} else {
			this.front = (this.front + 1) % this.n;
		}
		return num;
	}

	private boolean isQEmpty() {
		return (this.front == -1 && this.rear == -1);
	}

	public void depthFirstTraversal(int k) {
		if(k >= this.n) {
			System.out.println("Invalid vertex ...");
			return;
		}
		boolean[] visited = new boolean[this.n]; // Default value - false
		this.push(k);
		visited[k] = true;
		System.out.print("Depth First Traversal: ");
		System.out.print(k + " ");
		int[][] e = this.edg;
		while(! this.isStkEmpty()) {
			int i = this.stk[top], j = 0;
			// Find next unvisited adjacent node
			for(; j < this.n; j ++) {
				if(e[i][j] == 1 && ! visited[j])
					break;
			}
			if(j == this.n)
				this.pop();
			else {
				i = j;
				this.push(j);
				visited[j] = true;
				System.out.print(j + " ");
			}
		}
		System.out.print("\n");
	}

	public void breadthFirstTraversal(int k) {
		if(k >= this.n) {
			System.out.println("Invalid vertex ...");
			return;
		}
		boolean[] visited = new boolean[this.n];
		this.enQ(k);
		visited[k] = true;
		System.out.print("Breadth First Traversal: ");
		System.out.print(k + " ");
		int[][] e = this.edg;
		while(! this.isQEmpty()) {
			int i = this.deQ(), j = 0;
			// Find all unvisited adjacent nodes
			for(; j < this.n; j ++) {
				if(e[i][j] == 1 && ! visited[j]) {
					this.enQ(j);
					visited[j] = true;
					System.out.print(j + " ");
				}
			}
		}
		System.out.print("\n");
	}
}

public class Graph {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		MyGraph mg = new MyGraph();
		int choice = 0;
		while(choice != 4) {
			System.out.println("1: Insert Graph\n2: Depth First Traversal\n3: Breadth First Traversal\n4: Exit");
			System.out.print("Enter your choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					mg.insertGraph();
					break;
				}
				case 2: {
					System.out.print("Start vertex: ");
					int i = scan.nextInt();
					mg.depthFirstTraversal(i);
					break;
				}
				case 3: {
					System.out.print("Start vertex: ");
					int i = scan.nextInt();
					mg.breadthFirstTraversal(i);
					break;
				}
				case 4: {
					break;
				}
				default: {
					System.out.println("Invalid choice. Try again.");
				}
			}
		}
	}
}