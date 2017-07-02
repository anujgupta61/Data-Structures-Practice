import java.util.Scanner;
import java.util.ArrayList;

class MyHeap {
	public ArrayList<Integer> heap = new ArrayList<>();

	private void heapifyUp(int index) {
		if(index < 1)
			return;
		int parent, max, maxi;
		if(index % 2 == 0) {
			if(this.heap.get(index) > this.heap.get(index - 1)) {
				max = this.heap.get(index);
				maxi = index;
			} else {
				max = this.heap.get(index - 1);
				maxi = index - 1;
			}
			parent = (index - 2) / 2;
		} else {
			if(index == this.heap.size() - 1) {
				max = this.heap.get(index);
				maxi = index;
			} else {
				if(this.heap.get(index) > this.heap.get(index + 1)) {
					max = this.heap.get(index);
					maxi = index;
				} else {
					max = this.heap.get(index + 1);
					maxi = index + 1;
				}	
			}
			parent = (index - 1) / 2;
		}
		if(this.heap.get(parent) < max) {
			int temp = this.heap.get(parent);
			this.heap.set(parent, max);
			this.heap.set(maxi, temp);
			heapifyUp(parent);
		}
	}

	// i is root of subtree
	// o(log n)
	private static ArrayList<Integer> heapifyDown(ArrayList<Integer> myHeap, int i) {
		if((2 * i + 1) < myHeap.size()) {
			int max, maxi;
			if((2 * i + 2) == myHeap.size()) {
				max = myHeap.get(2 * i + 1);
				maxi = 2 * i + 1;
			} else {
				if(myHeap.get(2 * i + 2) > myHeap.get(2 * i + 1)) {
					max = myHeap.get(2 * i + 2);
					maxi = 2 * i + 2;
				} else {
					max = myHeap.get(2 * i + 1);
					maxi = 2 * i + 1;
				}
			}
			if(myHeap.get(i) < max) {
				int temp = myHeap.get(i);
				myHeap.set(i, max);
				myHeap.set(maxi, temp);
				heapifyDown(myHeap, maxi);
			}
		}
		return myHeap;
	}

	public static ArrayList<Integer> fullHeapify(ArrayList<Integer> arr) {
		int size = arr.size();
		// size / 2 - 1 is lowest node having children
		for(int i = size / 2 - 1; i >= 0; i --)
			heapifyDown(arr, i);
		return arr;
	}

	public void insert(int num) {
		this.heap.add(num);
		heapifyUp(this.heap.size() - 1);
	}

	public static int delete(ArrayList<Integer> myHeap) {
		int lasti = myHeap.size() - 1;
		int last = myHeap.get(lasti);
		int root = myHeap.get(0);
		myHeap.set(0, last);
		myHeap.remove(lasti);
		myHeap = heapifyDown(myHeap, 0);
		return root;
	}

	// Ascending order - Using MaxHeap
	// O(n * log n)
	public static ArrayList<Integer> heapSort(ArrayList<Integer> arr) {
		arr = fullHeapify(arr);
		int size = arr.size();
		ArrayList<Integer> arr1 = new ArrayList<>();
		for(int i = 0; i < size; i ++) {
			int maxElem = delete(arr);
			arr1.add(0, maxElem);
		}
		return arr1;
	}

	public static void printHeap(ArrayList<Integer> h) {	
		for(int i = 0; i < h.size(); i ++)
			System.out.print(h.get(i) + " ");
		System.out.print("\n");
	}
}

public class Heap {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		MyHeap mh = new MyHeap();
		int choice = 0;
		while(choice != 6) {
			System.out.print("1: Insertion\n2: Deletion\n3: Print Heap\n4: Full heapify a binary tree\n5: Heap Sort\n6: Exit\n");
			System.out.print("Enter your choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					System.out.print("Enter number to insert: ");
					int num = scan.nextInt();
					mh.insert(num);
					System.out.print(num + " inserted successfully.\n");
					break;
				}
				case 2: {
					if(mh.heap.isEmpty()) {
						System.out.print("Heap is empty.");
					} else { 
						int root = MyHeap.delete(mh.heap);
						System.out.print(root + " deleted successfully.\n");
					}
					break;
				}
				case 3: {
					if(mh.heap.isEmpty()) {
						System.out.print("Heap is empty.");
					} else { 
						MyHeap.printHeap(mh.heap);
					}
					break;
				}
				case 4: {
					System.out.print("Enter number of nodes in Binary Tree: ");
					int n = scan.nextInt();
					ArrayList<Integer> temp = new ArrayList<>();
					System.out.print("Enter Binary Tree -\n");
					for(int i = 0; i < n; i ++) {
						int t = scan.nextInt();
						temp.add(t);
					}
					ArrayList<Integer> temp1 = MyHeap.fullHeapify(temp);
					System.out.print("After Full heapify, Heap is: ");
					MyHeap.printHeap(temp1);
					break;
				}
				case 5: {
					System.out.print("Enter number of elements in array: ");
					int n = scan.nextInt();
					ArrayList<Integer> temp = new ArrayList<>();
					System.out.print("Enter elements of array -\n");
					for(int i = 0; i < n; i ++) {
						int t = scan.nextInt();
						temp.add(t);
					}
					ArrayList<Integer> temp1 = MyHeap.heapSort(temp);
					System.out.print("After Heap sort, Array is: ");
					MyHeap.printHeap(temp1);
					break;
				}
				case 6: {
					break;
				}
				default: {
					System.out.print("Invalid choice. Try again.\n");
				}
			}
		}
	}
}