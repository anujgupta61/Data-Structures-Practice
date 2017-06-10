import java . util . Scanner ;

class Node {
	int data ;
	Node prev ;
	Node next ;
}

class MyCircularDoublyLinkedList {
	Node first ;
	Node last ;

	public void printForward() {
		System . out . print("Linked list ( Forward manner ) - ") ;
		Node temp = this . first ;
		System . out . print(temp . data + "\t") ;
		while(temp . next != this . first) {
			temp = temp . next ;
			System . out . print(temp . data + "\t") ;
		}
		//System . out . print("first.data - " + this . first . data + " , first.prev.data - " + this . first . prev . data + " , last.data - " + 
		//	this . last . data + " , last.prev.data - " + this . last . prev . data) ;
		System . out . print("\n\n") ;
	}

	public void printBackward() {
		System . out . print("Linked list ( Backward manner ) - ") ;
		Node temp = this . last ;
		System . out . print(temp . data + "\t") ;
		while(temp . prev != this . last) {
			temp = temp . prev ;
			System . out . print(temp . data + "\t") ;
		}
		//System . out . print("first.data - " + this . first . data + " , first.next.data - " + this . first . next . data + " , last.data - " + 
		//	this . last . data + " , last.next.data - " + this . last . next . data) ;
		System . out . print("\n\n") ;
	}

	public int searchNum(int num) {
		Node temp = this . first ;
		if(temp . data == num) 
			return 0 ;
		int pos = 0 ;
		while(temp . next != this . first) {
			temp = temp . next ;
			pos ++ ; 
			if(temp . data == num) 
				return pos ;
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
			if(temp == this . first && i < pos) {
				System . out . println("Entered position is greater than length of Linked List ...") ;
				return ;
			}
		}
		Node newNode = new Node() ;
		newNode . data = n ;
		newNode . prev = temp ;
		if(pos > 0) {
			if(temp . next != this . first)
				temp . next . prev = newNode ;
			else {
				this . last = newNode ;
				this . first . prev = newNode ;
			}
			newNode . next = temp . next ;
			temp . next = newNode ;
		} else {
			if(this . first == null) {
				this . last = newNode ;
				newNode . next = newNode ;
			} else {
				newNode . next = this . first ;
			}
			this . first = newNode ;
			newNode . prev = this . last ;
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
			temp . prev . next = temp . next ;
			temp . next . prev = temp . prev ;
			if(temp . next == temp) {
				this . first = null ;
				this . last = null ;
			}
			if(temp . next != this . first && temp . prev == this . last) {
				this . last . next = temp . next ;
				this . first = temp . next ;
			}
			if(temp . next == this . first && temp . prev != this . last) {
				this . first . prev = temp . prev ;
				this . last = temp . prev ;
			}
			temp . next = null ;
			temp . prev = null ;
			System . out . println(num + " deleted ...") ;
		}
	}
}

public class CircularDoublyLinkedList {
	public static void main(String[] args) {
		MyCircularDoublyLinkedList cdll = new MyCircularDoublyLinkedList() ;
		int choice = 0 ;
		while(choice != -1) {
			System . out . println("Choose -\n1 : Print Linked list in forward manner\n2 : Print Linked list in backward manner\n3 : Search" +
			" number in Linked list\n4 : Insert element after a number\n5 : Insert element\n6 : Delete element\n7 : Exit") ;
			System . out . print("Enter your choice : ") ;
			Scanner scan = new Scanner(System . in) ;
			choice = scan . nextInt() ;
			switch(choice) {
				case 1 : {
					if(cdll . first == null)
						System . out . println("Linked list is empty ...") ;
					else {
						cdll . printForward() ;
					} 
					break ;
				}
				case 2 : {
					if(cdll . last == null)
						System . out . println("Linked list is empty ...") ;
					else {
						cdll . printBackward() ;
					}
					break ;
				}
				case 3 : {
					if(cdll . last == null)
						System . out . println("Linked list is empty ...") ;
					else {
						System . out . println("Enter number to search : ") ;
						int num = scan . nextInt() ;
						int pos = cdll . searchNum(num) ;
						if(pos == -1) 
							System . out . println(num + " does not exist ...") ;
						else
							System . out . println(num + " is in position " + pos + " ...") ;
					}
					break ;
				}
				case 4 : {
					if(cdll . last == null)
						System . out . println("Linked list does not have any number yet ...") ;
					else {
						System . out . println("Enter number after which insertion is to be done : ") ;
						int num = scan . nextInt() ;
						System . out . println("Enter number to insert : ") ;
						int n = scan . nextInt() ;
						cdll . insertAfter(num , n) ;
					}
					break ;
				}
				case 5 : {
					System . out . println("Enter number to insert : ") ;
					int num = scan . nextInt() ;
					System . out . println("Enter position to insert : ") ;
					int pos = scan . nextInt() ;
					cdll . insertNum(num , pos) ;
					break ;
				}
				case 6 : {
					if(cdll . last == null)
						System . out . println("Linked list is empty ...") ;
					else {
						System . out . println("Enter number to delete : ") ;
						int num = scan . nextInt() ;
						cdll . deleteNum(num) ;
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