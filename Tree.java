/*
	Binary Search Tree
*/

import java.util.Scanner;

class Node {
	int data;
	Node left;
	Node right;
}

class MyTree {
	public Node root;

	public void insert(int num) {
		Node newNode = new Node();
		newNode.data = num;
		newNode.left = null; // These 2 lines can be removed,
		newNode.right = null; // as null is default value for objects.
		if(this.root == null) {
 			this.root = newNode;
		} else {
			Node curr = this.root;
			Node parent = null;
			while(true) {
				parent = curr;
				if(curr.data <= num) {
					curr = curr.right;
					if(curr == null) {
						parent.right = newNode;
						return;
					}
				} else {
					curr = curr.left;
					if(curr == null) {
						parent.left = newNode;
						return;
					}
				}
			}
		}
	}

	public void insertRecursive(int num, Node myRoot) {
		if(myRoot == null || myRoot.left == null || myRoot.right == null) {
			Node newNode = new Node();
			newNode.data = num;
			newNode.left = null;
			newNode.right = null;
			if(myRoot == null) {
				this.root = newNode;
				return;
			} else {
				if(myRoot.left == null && myRoot.data >= num) {
					myRoot.left = newNode;
					return;
				}
				if(myRoot.right == null && myRoot.data <= num) {
 					myRoot.right = newNode;
 					return;
				}
			}
		}
		if(myRoot.data <= num)
			insertRecursive(num, myRoot.right);
		else
			insertRecursive(num, myRoot.left);
	}

	public void search(int key) {
		Node curr = this.root;
		while(curr != null && curr.data != key) {
			if(curr.data <= key)
				curr = curr.right;
			else
				curr = curr.left;
		}
		if(curr == null)
			System.out.println("Number not found ...");
		else
			System.out.println("Number found ...");
		
	}

	public void inOrder(Node myRoot) {
		if(myRoot == null)
			return;
		inOrder(myRoot.left);
		System.out.print(myRoot.data + " ");
		inOrder(myRoot.right);
	}

	public void preOrder(Node myRoot) {
		if(myRoot == null)
			return;
		System.out.print(myRoot.data + " ");
		preOrder(myRoot.left);
		preOrder(myRoot.right);	
	}

	public void postOrder(Node myRoot) {
		if(myRoot == null)
			return;
		postOrder(myRoot.left);
		postOrder(myRoot.right);
		System.out.print(myRoot.data + " ");	
	}
}

public class Tree {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		MyTree mt = new MyTree();
		int choice = 0;
		while(choice != 7) {
			System.out.println("\n1: Insert data\n2: Insert data recursively\n3: Search data\n4: InOrder traversal\n5: PreOrder traversal\n"
				+ "6: PostOrder traversal\n7: Exit");
			System.out.print("Enter your choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					System.out.print("Enter data to insert: ");
					int num = scan.nextInt();
					mt.insert(num);
					System.out.println(num + " inserted successfully ...");
					break;
				}
				case 2: {
					System.out.print("Enter data to insert recursively: ");
					int num = scan.nextInt();
					mt.insertRecursive(num, mt.root);
					System.out.println(num + " inserted successfully ...");
					break;
				}
				case 3: {
					if(mt.root == null) {
						System.out.println("Tree is empty ...");
					} else {
						System.out.print("Enter data to search: ");
						int num = scan.nextInt();
						mt.search(num);
					}
					break;
				}
				case 4: {
					System.out.print("InOrder Traversal: ");
					mt.inOrder(mt.root);
					System.out.print("\n");
					break;
				}
				case 5: {
					System.out.print("PreOrder Traversal: ");
					mt.preOrder(mt.root);
					System.out.print("\n");
					break;
				}
				case 6: {
					System.out.print("PostOrder Traversal: ");
					mt.postOrder(mt.root);
					System.out.print("\n");
					break;
				}
				case 7: {
					break;
				}
				default: {
					System.out.println("Invalid choice. Try again.");
				}
			}
		}
	}
}