import java.util.Scanner ;

class Node {
  public int data ;
  public Node next ;
}

class MyLinkedList {
  public Node head ;

  public int getLengthIterative()

  // Only Insertion takes O(1) time
  public void insertElem(int num , int pos) {
    Node n = new Node() ;
    n . data = num ;
    if(this . head == null) {
      n . next = null ;
      this . head = n ;
      System . out . println(num + " inserted at pos " + pos + " ...") ;
    } else {
      Node temp = this . head ;
      int i = 0 ;
      for( ; i < pos - 1 ; i ++) {
        if(temp == null)
          break ;
        else
          temp = temp . next ;
      }
      if(i < pos - 1 || temp == null) {
        System . out . println("Invalid position ...") ;
      } else {
          if(pos == 0) {
           n . next = this . head ;
           this . head = n ;
         } else {
            n . next = temp . next ;
            temp . next = n ;
          }
         System . out . println(num + " inserted at pos " + pos + " ...") ;
      }
    }
  }

  public void deleteByVal(int val) { // Delete only first occurence
    Node temp = this . head ;
    if(temp . data == val) {
      this . head = temp . next ;
      System . out . println(val + " deleted from Linked list ...") ;
      return ;
    }
    while(temp . next != null && temp . next . data != val)
      temp = temp . next ;
    if(temp . next == null) {
      System . out . println(val + " does not exist in Linked list ...") ;
    } else {
      temp . next = temp . next . next ;
      System . out . println(val + " deleted from Linked list ...") ;
    }
  }

  public void deleteByPos(int pos) {
    if(pos == 0) {
      System . out . println(this . head . data + " deleted from position " + pos + " ...") ;
      this . head = this . head . next ;
      return ;
    }
    int i = 0 ;
    Node temp = this . head ;
    for( ; i < pos - 1 ; i ++) {
      temp = temp . next ;
      if(temp == null)
        break ;
    }
    if(i < pos - 1 || temp . next == null) {
      System . out . println("Invalid position ...") ;
    } else {
      System . out . println(temp . next . data + " deleted from position " + pos + " ...") ;
      temp . next = temp . next . next ;
    }
  }

  public void printList() {
    if(this . head == null) {
      System . out . println("Linked list is empty ...") ;
      return ;
    }
    Node temp = this . head ;
    System . out . print("Linked list : ") ;
    while(temp != null) {
      System . out . print(temp . data + " ") ;
      temp = temp . next ;
    }
    System . out . print("\n") ;
  }
}

public class LinkedList {
  public static void main(String[] args) {
    MyLinkedList list = new MyLinkedList() ;
    int choice = 1 ;
    while(choice != 3) {
      System . out . println("Choose -\n1 : Insert\n2 : Display\n3 : Exit\n4 : Delete by value\n5 : Delete by position") ;
      Scanner reader = new Scanner(System . in) ;
      choice = reader . nextInt() ;
      switch(choice) {
        case 1 : {
          System . out . print("Enter number to insert and position (0 - indexed) : ") ;
          int num = reader . nextInt() ;
          int pos = reader . nextInt() ;
          list . insertElem(num , pos) ;
          break ;
        }
        case 2 : {
          list . printList() ;
          break ;
        }
        case 3 : {
          break ;
        }
        case 4 : {
          if(list . head == null) {
            System . out . println("Linked list is empty ...") ;
            break ;
          }
          System . out . print("Enter number to delete : ") ;
          int num = reader . nextInt() ;
          list . deleteByVal(num) ;
          break ;
        }
        case 5 : {
          if(list . head == null) {
            System . out . println("Linked list is empty ...") ;
            break ;
          }
          System . out . print("Enter position to delete : ") ;
          int pos = reader . nextInt() ;
          list . deleteByPos(pos) ;
          break ;
        }
        default : {
          System . out . println("Invalid choice ... Try again ...") ;
        }
      }
    }
  }
}
