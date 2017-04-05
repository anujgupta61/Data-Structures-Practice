import java.util.Scanner ;

class MyArray {
  private int maxSize , currSize ;
  private int[] data ;

  public MyArray(int size) { // Constructor
    data = new int[size] ;
    this . maxSize = size ;
    System . out . println("Array having size " + size + " created ...") ;
  }

  public Boolean isEmpty() {
    if(currSize == 0)
      return true ;
    else
      return false ;
  }

  public Boolean isFull() {
    if(currSize == maxSize)
      return true ;
    else
      return false ;
  }

  public void insertElem(int i , int num) { // O(n)
    if(! this . isFull()) {
      if(i >= 0 && i <= currSize + 1) {
        for(int j = currSize - 1 ; j >= i ; j --)
          data[j + 1] = data[j] ;
        data[i] = num ;
        this . currSize ++ ;
        System . out . println(num + " inserted at index " + i + " ...") ;
      } else {
        System . out . println("index can be from 0 to " + (this . currSize + 1) + " . Try again ...") ;
      }
    } else {
      System . out . println("Array is full . Can't insert " + num + " ...") ;
    }
  }

  public void deleteElem(int i) { // O(n)
    if(! this . isEmpty()) {
      if(i >= 0 && i <= currSize + 1) {
        int num = data[i] ;
        for(int j = i ; j < currSize - 1 ; j ++)
          data[j] = data[j + 1] ;
        this . currSize -- ;
        System . out . println(num + " deleted" + " ...") ;
      } else {
        System . out . println("index can be from 0 to " + this . currSize + " . Try again ...") ;
      }
    } else {
      System . out . println("Array is empty . Can't delete ...") ;
    }
  }

  public void binarySearch(int start , int end , int num) { // O(log(n))
    int mid = (start + end) / 2 ;
    if(num == data[mid]) {
      System . out . println(num + " found at index " + mid + " ...") ;
      return ;
    }
    if(start == end) {
      System . out . println(num + " not found ...") ;
      return ;
    }
    if(num < data[mid])
      binarySearch(start , mid - 1 , num) ;
    else
      binarySearch(mid + 1 , end , num) ;
  }

  public void merge(int start , int mid , int end) {
    int s = end - start + 1 ;
    int[] temp = new int[s] ;
    int p1 = start , p2 = mid + 1 , j = 0 ;
    while(p1 <= mid && p2 <= end) {
      if(data[p1] <= data[p2])
        temp[j ++] = data[p1 ++] ;
      else
        temp[j ++] = data[p2 ++] ;
    }
    while(p1 <= mid)
      temp[j ++] = data[p1 ++] ;
    while(p2 <= end)
      temp[j ++] = data[p2 ++] ;
    for(int i = 0 ; i < j ; i ++)
      data[start + i] = temp[i] ;
  }

  public void mergeSortArray(int start , int end) { // O(n*log(n))
    if(start == end)
      return ;
    int mid = (start + end) / 2 ;
    mergeSortArray(start , mid) ;
    mergeSortArray(mid + 1 , end) ;
    merge(start , mid , end) ;
  }

  public int getElem(int i) { // O(1)
    return data[i] ;
  }

  public int getCurrSize() {
    return this . currSize ;
  }

  public void printArray() {
    System . out . print("Array : ") ;
    for(int i = 0 ; i < currSize ; i ++)
      System . out . print(data[i] + " ") ;
    System . out . print("\n") ;
  }
}

public class Array {
  public static void main(String[] args) {
    Scanner s = new Scanner(System . in) ;
    System . out . print("Enter size of array : ") ;
    int size = s . nextInt() ;
    MyArray arr = new MyArray(size) ;
    Boolean do_repeat = true ;
    while(do_repeat) {
      do_repeat = true ;
      System . out . println("\n1 : Display\n2 : Insert\n3 : Delete\n4 : Merge Sort\n5 : Binary Search\n6 : Get Element\n7 : Exit") ;
      System . out . print("Choice : ") ;
      int choice = s . nextInt() ;
      switch(choice) {
        case 1 : {
          arr . printArray() ;
          break ;
        }
        case 2 : {
          System . out . print("Enter number to insert and index : ") ;
          int num = s . nextInt() ;
          int index = s . nextInt() ;
          arr . insertElem(index , num) ;
          break ;
        }
        case 3 : {
          System . out . print("Enter index to delete : ") ;
          int indx = s . nextInt() ;
          arr . deleteElem(indx) ;
          break ;
        }
        case 4 : {
          arr . mergeSortArray(0 , arr . getCurrSize() - 1) ;
          System . out . println("Array has been sorted in Ascending order .") ;
          break ;
        }
        case 5 : {
          System . out . print("Enter number to search : ") ;
          int n = s . nextInt() ;
          arr . mergeSortArray(0 , arr . getCurrSize() - 1) ;
          arr . binarySearch(0 , arr . getCurrSize() - 1 , n) ;
          break ;
        }
        case 6 : {
          System . out . print("Enter index to get Element : ") ;
          int i = s . nextInt() ;
          System . out . println("Element is " + arr . getElem(i)) ;
          break ;
        }
        case 7 : {
          do_repeat = false ;
          break ;
        }
        default : {
          System . out . println("Invalid choice . Try again .") ;
          break ;
        }
      }
    }
  }
}
