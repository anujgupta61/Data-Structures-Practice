import java . util . Scanner ;

class Node {
	int data ;
	Node prev ;
	Node next ;
}

class MyDoublyLinkedList {
	Node first ;
	Node last ;

	public void printForward() {
		System . out . print("Linked list ( Forward manner ) - ") ;
		Node temp = first ;
		while(temp != null) {
			System . out . print(temp . data + "\t") ;
			temp = temp . next ;
		}
		//System . out . print("first.data - " + this . first . data + " , last.data - " + this . last . data) ;
		System . out . print("\n\n") ;
	}

	public void printBackward() {
		System . out . print("Linked list ( Backward manner ) - ") ;
		Node temp = last ;
		while(temp != null) {
			System . out . print(temp . data + "\t") ;
			temp = temp . prev ;
		}
		//System . out . print("first.data - " + this . first . data + " , last.data - " + this . last . data) ;
		System . out . print("\n\n") ;
	}

	public int searchNum(int num) {
		Node temp = this . first ;
		int pos = 0 ;
		while(temp != null) {
			if(temp . data == num) 
				return pos ;
			temp = temp . next ;
			pos ++ ; 
		}
		return -1 ;
	}

	public void insertNum(int n , int pos) {
		Node temp = null ;
		for(int i = 0 ; i < pos ; i ++) {
			if(i == 0) {
				temp = this . first ;
				continue ;
			}
			temp = temp . next ;
			if(temp == null && i < pos) {
				System . out . println("Entered position is greater than length of Linked List ...") ;
				return ;
			}
		}
		Node newNode = new Node() ;
		newNode . data = n ;
		newNode . prev = temp ;
		if(temp != null) {
			if(temp . next != null)
				temp . next . prev = newNode ;
			else
				this . last = newNode ;
			newNode . next = temp . next ;
			temp . next = newNode ;
		} else {
			if(this . first == null)
				this . last = newNode ;
			newNode . next = this . first ;
			this . first = newNode ;
		}
		System . out . println(n + " inserted at position " + pos + "...") ;
	}

	public void insertAfter(int num , int n) { // After num , insert n
		int pos = searchNum(num) ;
		if(pos == -1)
			System . out . println(num + " does not exist ...") ;
		else  {
			insertNum(n , pos + 1) ;
			System . out . println(n + " inserted after " + num + " ...") ;
		}
	}

	public void deleteNum(int num) {
		int pos = searchNum(num) ;
		if(pos == -1)
			System . out . println(num + " does not exist ...") ;
		else  {
			Node temp = this . first ;
			for(int i = 0 ; i < pos ; i ++)
				temp = temp . next ;
			if(temp . prev != null)
				temp . prev . next = temp . next ;
			if(temp . next != null)
				temp . next . prev = temp . prev ;
			if(temp . next == null && temp . prev == null) {
				this . first = null ;
				this . last = null ;
			}
			if(temp . next != null && temp . prev == null) {
				this . first = temp . next ;
			}
			if(temp . next == null && temp . prev != null) {
				this . last = temp . prev ;
			}
			temp . next = null ;
			temp . prev = null ;
			System . out . println(num + " deleted ...") ;
		}
	}
}

public class DoublyLinkedList {
	public static void main(String[] args) {
		MyDoublyLinkedList dll = new MyDoublyLinkedList() ;
		int choice = 0 ;
		while(choice != -1) {
			System . out . println("Choose -\n1 : Print Linked list in forward manner\n2 : Print Linked list in backward manner\n3 : Search" +
			" number in Linked list\n4 : Insert element after a number\n5 : Insert element\n6 : Delete element\n7 : Exit") ;
			System . out . print("Enter your choice : ") ;
			Scanner scan = new Scanner(System . in) ;
			choice = scan . nextInt() ;
			switch(choice) {
				case 1 : {
					if(dll . first == null)
						System . out . println("Linked list is empty ...") ;
					else {
						dll . printForward() ;
					} 
					break ;
				}
				case 2 : {
					if(dll . last == null)
						System . out . println("Linked list is empty ...") ;
					else {
						dll . printBackward() ;
					}
					break ;
				}
				case 3 : {
					if(dll . last == null)
						System . out . println("Linked list is empty ...") ;
					else {
						System . out . println("Enter number to search : ") ;
						int num = scan . nextInt() ;
						int pos = dll . searchNum(num) ;
						if(pos == -1) 
							System . out . println(num + " does not exist ...") ;
						else
							System . out . println(num + " is in position " + pos + " ...") ;
					}
					break ;
				}
				case 4 : {
					if(dll . last == null)
						System . out . println("Linked list does not have any number yet ...") ;
					else {
						System . out . println("Enter number after which insertion is to be done : ") ;
						int num = scan . nextInt() ;
						System . out . println("Enter number to insert : ") ;
						int n = scan . nextInt() ;
						dll . insertAfter(num , n) ;
					}
					break ;
				}
				case 5 : {
					System . out . println("Enter number to insert : ") ;
					int num = scan . nextInt() ;
					System . out . println("Enter position to insert : ") ;
					int pos = scan . nextInt() ;
					dll . insertNum(num , pos) ;
					break ;
				}
				case 6 : {
					if(dll . last == null)
						System . out . println("Linked list is empty ...") ;
					else {
						System . out . println("Enter number to delete : ") ;
						int num = scan . nextInt() ;
						dll . deleteNum(num) ;
					}
						break ;
				}
				case 7 : {
					choice = -1 ;
					break ;
				}
				default : {
					System . out . println("Invalid choice ... Try again ...") ;
				}
			}
		}
	}
}