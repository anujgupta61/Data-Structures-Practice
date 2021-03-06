import java.util.Scanner ;

class Node {
  public int data ;
  public Node next ;
}

class MyLinkedList {
  public Node head ;

  public Node getNodeAtPos(int p) {
    if(p < 0)
      return null ;
    Node temp = this . head ;
    for(int i = 0 ; i < p ; i ++) {
      temp = temp . next ;
      if(temp == null)
        return null ;
    }
    return temp ;
  }

  public void reverseLinkedListIterative() {
    Node temp1 = this . head ;
    Node temp2 = this . head . next ;
    temp1 . next = null ;
    Node temp = temp1 ;
    while(temp2 != null) {
      temp1 = temp2 ;
      temp2 = temp2 . next ;
      temp1 . next = temp ;
      temp = temp1 ;
    }
    this . head = temp1 ;
    System . out . println("Linked list reversed ...") ;
  }

  public void reverseLinkedListInGroups(int k) {
    Node temp1 = this . head ;
    Node temp2 = this . head . next ;
    Node pre = null ;
    for(int j = 0 ; temp1 != null ; j += k) {
      Node temp = temp1 ;
      int i = 0 ;
      Node t = temp ;
      while(temp2 != null && i < k - 1) {
        temp1 = temp2 ;
        temp2 = temp2 . next ;
        temp1 . next = temp ;
        temp = temp1 ;
        i ++ ;
      }
      if(j == 0)
        this . head = temp1 ;
      else {
        pre . next = temp1 ;
      }
      temp1 = temp2 ;
      if(temp2 != null)
        temp2 = temp2 . next ;
      pre = t ;
    }
    if(temp2 == null)
      pre . next = null ;
  }

  public void reverseLinkedListRecursive2(Node pre , Node curr) { // Tail recursive
    if(curr . next == null) {
      this . head = curr ;
      curr . next = pre ;
      return ;
    }
    Node curr1 = curr . next ;
    curr . next = pre ;
    pre = curr ;
    reverseLinkedListRecursive2(pre , curr1) ;
  }

  public void reverseLinkedListRecursive1(Node first , Node rest) { // Head recursive
    if(rest == null) {
      return ;
    }
    if(rest . next == null)
      this . head = rest ;
    reverseLinkedListRecursive1(rest , rest . next) ;
    rest . next = first ;
  }

  private Node breakIntoHalf(Node source) {
    Node fast , slow ;
    if(source == null || source . next == null) {
      return null ;
    } else {
      slow = source ;
      fast = source . next ;
      while(fast != null) {
        fast = fast . next ;
        if(fast != null) {
          slow = slow . next ;
          fast = fast . next ;
        }
      }
      Node mid1 = slow . next ;
      slow . next = null ;
      return mid1 ;
    }
  }

  public Node mergeSort(Node head) { // O(nlogn)
    if(head == null || head . next == null)
      return head ;
    Node start = head ;
    Node mid1 = this . breakIntoHalf(head) ;
    start = mergeSort(start) ;
    mid1 = mergeSort(mid1) ;
    Node th = MyLinkedList . sortedMergeTwoSortedLinkedList(start , mid1) ;
    return th ;
  }

  public static Node sortedMergeTwoSortedLinkedList(Node first , Node second) {
    if(first == null || second == null) {
      return null ;
    }
    if(first . data <= second . data) {
      Node temp = first ;
      while(temp . next != null && temp . next . data <= second . data)
        temp = temp . next ;
      Node temp1 = temp . next ;
      temp . next = second ;
      sortedMergeTwoSortedLinkedList(temp1 , second) ;
    } else {
      Node temp = second ;
      while(temp . next != null && temp . next . data < first . data)
        temp = temp . next ;
      Node temp1 = temp . next ;
      temp . next = first ;
      sortedMergeTwoSortedLinkedList(first , temp1) ;
    }
    if(first . data <= second . data)
      return first ;
    else
      return second ;
  }

  public int getLengthIterative() {
    Node temp = this . head ;
    int len = 0 ;
    while(temp != null) {
      len ++ ;
      temp = temp . next ;
    }
    return len ;
  }

  public int getLengthRecursive(Node temp) {
    if(temp == null)
      return 0 ;
    else
      return getLengthRecursive(temp . next) + 1 ;
  }

  // Only Insertion takes O(1) time
  public void insertElem(int num , int pos) {
    Node n = new Node() ;
    n . data = num ;
    if(this . head == null) {
      if(pos > 0) {
        System . out . println("Invalid position ...") ;
      } else {
        n . next = null ;
        this . head = n ;
        System . out . println(num + " inserted at pos " + pos + " ...") ;
      }
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

  public void swapNodes(int p1 , int p2) {
    Node pre1 = this . getNodeAtPos(p1 - 1) ;
    Node curr1 = this . getNodeAtPos(p1) ;
    Node pre2 = this . getNodeAtPos(p2 - 1) ;
    Node curr2 = this . getNodeAtPos(p2) ;
    if(curr1 == null || curr2 == null) {
      System . out . println("Invalid positions ...") ;
      return ;
    }
    if(curr1 . equals(curr2)) {
      System . out . println("Both positions are same ...") ;
      return ;
    }
    if(pre1 == null)
      this . head = curr2 ;
    else
      pre1 . next = curr2 ;
    if(pre2 == null)
      this . head = curr1 ;
    else
      pre2 . next = curr1 ;
    Node temp = curr1 . next ;
    curr1 . next = curr2 . next ;
    curr2 . next = temp ;
    System . out . println("Swapping successful ...") ;
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
      System . out . println("Choose -\n1 : Insert\n2 : Display\n3 : Exit\n4 : Delete by value\n" +
      "5 : Delete by position\n6 : Get length iterative\n7 : Get length recursive\n8 : Swap nodes\n" +
      "9 : Get value at position\n10 : Reverse Linked list iteratively\n11 : Reverse Linked list recursively (Method 1)\n" +
      "12 : Reverse Linked list recursively (Method 2)\n13 : Merge two sorted Linked list\n" +
      "14 : Merge Sort\n15 : Reverse Linked list in groups") ;
      System . out . print("Your choice : ") ;
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
        case 6 : {
          int len_i = list . getLengthIterative() ;
          System . out . println("Length (Iterative) : " + len_i) ;
          break ;
        }
        case 7 : {
          int len_r = list . getLengthRecursive(list . head) ;
          System . out . println("Length (Recursive) : " + len_r) ;
          break ;
        }
        case 8 : {
          System . out . print("Enter position 1 and position 2 to swap : ") ;
          int p1 = reader . nextInt() ;
          int p2 = reader . nextInt() ;
          list . swapNodes(p1 , p2) ;
          break ;
        }
        case 9 : {
          System . out . print("Enter position : ") ;
          int p = reader . nextInt() ;
          Node t = list . getNodeAtPos(p) ;
          if(t != null)
            System . out . println(t . data) ;
          else
            System . out . println("Invalid position ...") ;
          break ;
        }
        case 10 : {
          if(list . head == null) {
            System . out . println("Linked list is empty ...") ;
            break ;
          }
          list . reverseLinkedListIterative() ;
          break ;
        }
        case 11 : {
          if(list . head == null) {
            System . out . println("Linked list is empty ...") ;
            break ;
          }
          list . reverseLinkedListRecursive1(null , list . head) ;
          System . out . println("Linked list reversed ...") ;
          break ;
        }
        case 12 : {
          if(list . head == null) {
            System . out . println("Linked list is empty ...") ;
            break ;
          }
          list . reverseLinkedListRecursive2(null , list . head) ;
          System . out . println("Linked list reversed ...") ;
          break ;
        }
        case 13 : {
          MyLinkedList list1 = new MyLinkedList() ;
          System . out . print("Enter number of nodes (> 0) in linked list to be merged : ") ;
          int n1 = reader . nextInt() ;
          if(n1 > 0) {
            System . out . print("Enter data in nodes of linked list to be merged : ") ;
            for(int i = 0 ; i < n1 ; i ++) {
              int num = reader . nextInt() ;
              list1 . insertElem(num , list1 . getLengthIterative()) ;
            }
            if(list . head != null)
              list . head = MyLinkedList . sortedMergeTwoSortedLinkedList(list1 . head , list . head) ;
            else
              list . head = list1 . head ;
          }
          System . out . print("After merging : ") ;
          list . printList() ;
          break ;
        }
        case 14 : {
          System . out . print("After sorting : ") ;
          list . head = list . mergeSort(list . head) ;
          list . printList() ;
          break ;
        }
        case 15 : {
          System . out . print("Enter group size : ") ;
          int k = reader . nextInt() ;
          list . reverseLinkedListInGroups(k) ;
          list . printList() ;
          break ;
        }
        default : {
          System . out . println("Invalid choice ... Try again ...") ;
        }
      }
    }
  }
}
