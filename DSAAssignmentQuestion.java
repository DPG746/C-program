// Reverse Linked List
package Preparation;

import java.util.LinkedList;
import java.util.Scanner;

class Node{
	private int data;
	private Node next;
	
	public Node(int data) {
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	
}

public class ReverseLInkedList {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the number of nodes:");
		
		int n= scan.nextInt();
		
		Node head=null;
		Node curr=null;
		
		
		
		for(int i=0;i<n;i++) {
			int value = scan.nextInt();
			
			Node nn = new Node(value);
			
			if(head==null) {
				head=nn;
				
				curr=nn;
				
				
			}
			else {
				curr.setNext(nn);
				
				curr=nn;
			}
			
			
		}
		Node prev=null;
		
		Node next=null;
		
		curr=head;
		while(curr!=null) {
			next=curr.getNext();
			
			curr.setNext(prev);
			prev=curr;
			curr=next;
			
		}
		head =prev;
		
		Node temp=head;
		
		while(temp!=null) {
			System.out.println(temp.getData());
			temp=temp.getNext();
			
		}
		
		}
	

}


//Valid paranthesis using stack------------------------------------------------------------------------

package Preparation;

import java.util.Scanner;
import java.util.Stack;

public class ValidParanthesisusingStack {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String s = scan.nextLine();
		
		boolean valid=true;
		
		Stack<Character> ss= new Stack<>();
		
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			
			if(c=='(' || c== '[' || c=='{') {
				ss.push(c);
				
			}else if(ss.size()>0 && (c==')'  && ss.peek()=='(' || c==']' && ss.peek()=='[' || c=='}' && ss.peek()=='{')) {
				ss.pop();
				
			}else {
				valid=false;
				break;
			}
			
		}
		if(valid && ss.isEmpty()) {
			System.out.println("Valid Paranthesis");
			
		}else {
			System.out.println("Invalid Paranthesis");
		}
		scan.close();
	}
	

}


//Rotate Array Right-------------------------------------------------------------------------------------------------

package Preparation;

import java.util.Arrays;
import java.util.Scanner;

public class RotateArrayRIght {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] nums= {2,4,5,6,7};
		
		System.out.println("Enter degree to rotate");
		int k=scan.nextInt();
		
		int n=nums.length;
		
		int[] arr = new int[n];

	
		for(int i =0;i<n;i++) {
			arr[(i+k)%n]=nums[i];
			
		}
	
		System.out.println(Arrays.toString(arr));
		
	}

}


//Missing Number----------------------------------------------------------------------------------

package Preparation;

import java.util.Scanner;

public class MissingNumber {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n=scan.nextInt();
		int[] arr= new int[n];
		int cnt=0;
		for(int i=0;i<n;i++) {
			arr[i]=scan.nextInt();
			cnt+=arr[i];
			
		}
		
		int sum=n*(n+1)/2;
		int res=sum-cnt;
		
		System.out.println(res);
		
		
	}
	

}


//Binary Search First and Last Occurance--------------------------------------------------------------------
package Preparation;

import java.util.Scanner;

public class BinarySearchFIrstandLastOccurance {
	public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int[] nums= {1,2,2,3,7,7,8,8};
	int target=8;
	
	int l=0;
    int fast =-1;
    int last =-1;
    int r=nums.length-1;
    while(l<=r){
        int mid=(l+r)/2;

        if(nums[mid]==target){
            fast=mid;
            r=mid-1;

        }else if(nums[mid]<target){
            l=mid+1;


        }else{
            r=mid-1;
        }


    }


    //Last Occurance
    l=0;
    r=nums.length-1;
    while(l<=r){
        int mid=(l+r)/2;

        if(nums[mid]==target){
            last=mid;
            l=mid+1;

        }else if(nums[mid]<target){
            l=mid+1;


        }else{
            r=mid-1;
        }


    }
   // return new int[]{fast,last};

    System.out.println(fast+" "+last);
}
}

// Selection SOrt---------------------------------------------------------------------------

package Preparation;

import java.util.Scanner;

public class SelectionSort {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] arr= {4,6,7,5};
		int n=arr.length;
		
		for(int i=0;i<n-1;i++) {
			int minn=i;
			for(int j=i+1;j<n;j++) {
				if(arr[j]<arr[minn]) {
					minn=j;
					
				}
				
			}
			int temp=arr[i];
			arr[i]=arr[minn];
			arr[minn]=temp;
			
		}
		for(int x: arr) {
			System.out.print(x+" ");
			
		}
		
	}

}


//----------------------------------//---------------------------------------//-------------------------------------//------------------------------------//

//<PPPPPPPPPPpppppppppPPPPPPPPPPPP>


//Q- Problem - Solve the Tower of hanoi problem . Print the steps of disk movement

Solution - 
		import java.util.Scanner;

public class TowerOfHanoi {

    public static void solve(int n, char source, char auxiliary, char destination) {

        // Base Case
        if (n == 1) {
            System.out.println("Move Disk 1 from " + source + " to " + destination);
            return;
        }

        // Step 1: Move n-1 disks from source to auxiliary
        solve(n - 1, source, destination, auxiliary);

        // Step 2: Move nth disk from source to destination
        System.out.println("Move Disk " + n + " from " + source + " to " + destination);

        // Step 3: Move n-1 disks from auxiliary to destination
        solve(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        System.out.println("\nSteps to solve Tower of Hanoi:");

        solve(n, 'A', 'B', 'C');

        int totalMoves = (int) Math.pow(2, n) - 1;
        System.out.println("\nTotal Moves = " + totalMoves);

        sc.close();
    }
}

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

//Q-  Next Greater Element using Stack

Sol - import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] nge = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop();
            }

            if (st.isEmpty()) {
                nge[i] = -1;
            } else {
                nge[i] = st.peek();
            }

            st.push(arr[i]);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(nge[i] + " ");
        }
    }
}

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Q- Merge Sort

Sol - import java.util.*;

public class Main {

    static void merge(int arr[], int low, int mid, int high) {

        int[] temp = new int[high - low + 1];

        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= high) {
            temp[k++] = arr[j++];
        }

        for (i = low, k = 0; i <= high; i++, k++) {
            arr[i] = temp[k];
        }
    }

    static void mergeSort(int arr[], int low, int high) {

        if (low < high) {

            int mid = (low + high) / 2;

            mergeSort(arr, low, mid);

            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        mergeSort(arr, 0, n - 1);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

//Q- Detect Loop in Linked List (Floyd's Algorithm)

Sol - import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {

    static boolean detectLoop(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {

            Node newNode = new Node(sc.nextInt());
            nodes[i] = newNode;

            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int pos = sc.nextInt();

        if (pos != -1) {
            tail.next = nodes[pos - 1];
        }

        if (detectLoop(head))
            System.out.println("Loop Found");
        else
            System.out.println("No Loop");
    }
}

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Q- Kadane's Algorithm — Max Subarray Sum

Sol -    import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read size of array
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Read array elements
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Initialize current sum and maximum sum
        int currSum = arr[0];
        int maxSum = arr[0];

        // Traverse the array from second element
        for (int i = 1; i < n; i++) {

            // Either start a new subarray from current element
            // or extend the previous subarray
            currSum = Math.max(arr[i], currSum + arr[i]);

            // Update maximum sum found so far
            maxSum = Math.max(maxSum, currSum);
        }

        // Print maximum subarray sum
        System.out.println(maxSum);

        sc.close();
    }
}






//VVVVVVVVVVVVVVVVV VVVVVVVVVVVVVVVVV


//--Infix to Postfix--

import java.util.*;

public class InfixToPostfix {
    static int precedence(char ch) {
        if (ch == '+' || ch == '-') return 1;
        if (ch == '*' || ch == '/') return 2;
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine();

        Stack<Character> st = new Stack<>();
        String postfix = "";

        for (char ch : exp.toCharArray()) {

            if (Character.isLetterOrDigit(ch))
                postfix += ch;

            else if (ch == '(')
                st.push(ch);

            else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(')
                    postfix += st.pop();
                st.pop();
            }

            else {
                while (!st.isEmpty() &&
                        precedence(st.peek()) >= precedence(ch))
                    postfix += st.pop();

                st.push(ch);
            }
        }

        while (!st.isEmpty())
            postfix += st.pop();

        System.out.println(postfix);
    }
}


//------------------------------------------------------------------------------------


	
//--Evaluate Postfix--

import java.util.*;

public class EvaluatePostfix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String exp = sc.nextLine();
        String[] tokens = exp.split(" ");

        Stack<Integer> st = new Stack<>();

        for (String t : tokens) {

            if (Character.isDigit(t.charAt(0))) {
                st.push(Integer.parseInt(t));
            } else {
                int b = st.pop();
                int a = st.pop();

                switch (t) {
                    case "+":
                        st.push(a + b);
                        break;
                    case "-":
                        st.push(a - b);
                        break;
                    case "*":
                        st.push(a * b);
                        break;
                    case "/":
                        st.push(a / b);
                        break;
                }
            }
        }

        System.out.println(st.pop());
    }
}


--------------------------------------------------------------------------------------------------------

	
//--Count ways to Climb--

import java.util.*;

public class ClimbStairs {

    static int ways(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        return ways(n - 1) + ways(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(ways(n));
    }
}


-------------------------------------------------------------------------------------------

//--Quick Sort--

import java.util.*;

public class QuickSort {

    static int partition(int arr[], int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void quickSort(int arr[], int low, int high) {

        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        quickSort(arr, 0, n - 1);

        for (int x : arr)
            System.out.print(x + " ");
    }
}



//---------------------------------------------------------------------------------------------------

//--Heap Sort--

import java.util.*;

public class HeapSort {

    static void heapify(int arr[], int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {

            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }

        for (int x : arr)
            System.out.print(x + " ");
    }
}

//--------------------------------------------------------------------------------------------------



//--Second Largest Element--

import java.util.*;

public class SecondLargest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int arr[] = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int x : arr) {

            if (x > largest) {
                second = largest;
                largest = x;
            }
            else if (x > second && x != largest) {
                second = x;
            }
        }

        System.out.println(second);
    }
}




//--------------------------------------------------------------------------------------------------------------

//--Insert Node at Pos--

import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class InsertAtPosition {

    static Node head = null;

    static void insert(int pos, int val) {

        Node newNode = new Node(val);

        if (pos == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;

        for (int i = 1; i < pos - 1; i++)
            temp = temp.next;

        newNode.next = temp.next;
        temp.next = newNode;
    }

    static void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        insert(3, 99);

        display();
    }
}



//--------------------------------------------------------------------------------------------------------------

//--Delete Node by Value--

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class DeleteByValue {

    static Node head;

    static void delete(int val) {

        if (head.data == val) {
            head = head.next;
            return;
        }

        Node temp = head;

        while (temp.next != null &&
                temp.next.data != val) {
            temp = temp.next;
        }

        if (temp.next != null)
            temp.next = temp.next.next;
    }

    static void display() {

        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        delete(30);

        display();
    }
}





//--------------------------------------------------------------------------------------------------------------





//--Generate Binary Number--

import java.util.*;

public class BinaryNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Queue<String> q = new LinkedList<>();

        q.add("1");

        for (int i = 1; i <= n; i++) {

            String s = q.remove();

            System.out.print(s + " ");

            q.add(s + "0");
            q.add(s + "1");
        }
    }
}



//--------------------------------------------------------------------------------------------------------------




//--Priorty Queue--

import java.util.*;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        pq.add(10);
        pq.add(50);
        pq.add(20);

        pq.poll();

        while (!pq.isEmpty())
            System.out.print(pq.poll() + " ");
    }
}



//--------------------------------------------------------------------------------------------------------------



//--Jump Search--

import java.util.*;

public class JumpSearch {

    static int jumpSearch(int arr[], int key) {

        int n = arr.length;

        int step = (int) Math.sqrt(n);
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < key) {

            prev = step;
            step += (int) Math.sqrt(n);

            if (prev >= n)
                return -1;
        }

        while (arr[prev] < key) {
            prev++;

            if (prev == Math.min(step, n))
                return -1;
        }

        if (arr[prev] == key)
            return prev;

        return -1;
    }

    public static void main(String[] args) {

        int arr[] = {1,3,5,7,9,11};

        System.out.println(jumpSearch(arr, 7));
    }
}





//--------------------------------------------------------------------------------------------------------------


//--Circular Queue--

import java.util.*;

public class CircularQueue {

    static int size = 5;
    static int queue[] = new int[size];

    static int front = -1;
    static int rear = -1;

    static void enqueue(int data) {

        if ((rear + 1) % size == front) {
            System.out.println("Queue Full");
            return;
        }

        if (front == -1)
            front = 0;

        rear = (rear + 1) % size;
        queue[rear] = data;
    }

    static void dequeue() {

        if (front == -1) {
            System.out.println("Queue Empty");
            return;
        }

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % size;
        }
    }

    static void display() {

        int i = front;

        while (true) {

            System.out.print(queue[i] + " ");

            if (i == rear)
                break;

            i = (i + 1) % size;
        }
    }

    public static void main(String[] args) {

        enqueue(10);
        enqueue(20);
        dequeue();

        display();
    }
}






//--------------------------------------------------------------------------------------------------------------




//--Fibonacci Using Recursion--

import java.util.*;

public class Fibonacci {

    static int fib(int n) {

        if (n == 0 || n == 1)
            return n;

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i = 0; i < n; i++)
            System.out.print(fib(i) + " ");
    }
}





//--------------------------------------------------------------------------------------------------------------





//--First nonRepeating Char--

import java.util.*;

public class FirstNonRepeating {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        Queue<Character> q = new LinkedList<>();
        int freq[] = new int[256];

        for (char ch : str.toCharArray()) {

            freq[ch]++;
            q.add(ch);

            while (!q.isEmpty() &&
                    freq[q.peek()] > 1)
                q.remove();

            if (q.isEmpty())
                System.out.print("- ");
            else
                System.out.print(q.peek() + " ");
        }
    }
}





--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//1. Create a menu-driven program to perform operations on a Circular Singly Linked List
import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class Main {
    static Node last = null;

    static void insertBegin(int data) {
        Node n = new Node(data);

        if (last == null) {
            last = n;
            last.next = last;
        } else {
            n.next = last.next;
            last.next = n;
        }
    }

    static void insertEnd(int data) {
        Node n = new Node(data);

        if (last == null) {
            last = n;
            last.next = last;
        } else {
            n.next = last.next;
            last.next = n;
            last = n;
        }
    }

    static void deleteBegin() {
        if (last == null)
            return;

        if (last.next == last)
            last = null;
        else
            last.next = last.next.next;
    }

    static void deleteEnd() {
        if (last == null)
            return;

        if (last.next == last) {
            last = null;
            return;
        }

        Node temp = last.next;

        while (temp.next != last)
            temp = temp.next;

        temp.next = last.next;
        last = temp;
    }

    static void display() {
        if (last == null) {
            System.out.println("Empty");
            return;
        }

        Node temp = last.next;

        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != last.next);

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1.Insert Begin");
            System.out.println("2.Insert End");
            System.out.println("3.Delete Begin");
            System.out.println("4.Delete End");
            System.out.println("5.Display");
            System.out.println("6.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    insertBegin(sc.nextInt());
                    break;
                case 2:
                    insertEnd(sc.nextInt());
                    break;
                case 3:
                    deleteBegin();
                    break;
                case 4:
                    deleteEnd();
                    break;
                case 5:
                    display();
                    break;
                case 6:
                    return;
            }
        }
    }
}
-----------------------------------------------------------
//Q2. Sort Employee Records using Selection Sort
import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Employee[] e = new Employee[n];

        for (int i = 0; i < n; i++) {
            e[i] = new Employee(sc.nextInt(), sc.next(), sc.nextDouble());
        }

        for (int i = 0; i < n - 1; i++) {
            int min = i;

            for (int j = i + 1; j < n; j++) {
                if (e[j].salary < e[min].salary)
                    min = j;
            }

            Employee temp = e[i];
            e[i] = e[min];
            e[min] = temp;
        }

        for (Employee x : e)
            System.out.println(x.id + " " + x.name + " " + x.salary);
    }
}
----------------------------------------
//Q3. Sort Student Records using Bubble Sort
import java.util.*;

class Student {
    int roll;
    String name;
    int marks;

    Student(int roll, String name, int marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Student[] s = new Student[n];

        for (int i = 0; i < n; i++) {
            s[i] = new Student(sc.nextInt(), sc.next(), sc.nextInt());
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (s[j].marks < s[j + 1].marks) {
                    Student temp = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = temp;
                }
            }
        }

        for (Student x : s)
            System.out.println(x.roll + " " + x.name + " " + x.marks);
    }
}













-----------------------------------------------------------------------------------------------------------

//-------------	Linked List done By ME


	Node class:


	package SingleLinkedList;

public class Node {
	private int data;
	private Node next;
	
	public Node(int data) {
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}

}



Main Class:


	package SingleLinkedList;

public class Mainll {
	public static void main(String[] args) {
		
		Node head = null;
		
		head=insertAtFront(head,50);
		head=insertAtFront(head,40);
		head=insertAtFront(head,30);
		head=insertAtFront(head,20);
		head=insertAtFront(head,10);
		head=insertAtFront(head,5);
		printiteratelinkedList(head);
		
		head=insertAtEnd(head,60);
		head = insertAtIndex(head,75,4);
		printiteratelinkedList(head);
		head=deleteAtIndex(head,75,4);
		printiteratelinkedList(head);
		
		
		
		
		
																		
																//		head=deleteAtFront(head);
																//		printiteratelinkedList(head); // 5 gone 
																//		
																//		head=deleteAtFront(head);
																//		printiteratelinkedList(head); // 10 gone 
																//		
																		
																		
		
															//	Node ten = new Node(10);
															//	Node twenty = new Node(20);
															//	Node thirdy = new Node(30);
															//	Node fourty = new Node(40);
															//	Node fifty = new Node(50);
															//	
															//	Node head = ten;
															//	ten.setNext(twenty);
															//	twenty.setNext(thirdy);
															//	thirdy.setNext(fourty);
															//	fourty.setNext(fifty);
															//	fifty.setNext(null);
															//	Node temp = head;
																
															//	while(temp!=null) {
															//		System.out.println(temp.getData());
															//		temp=temp.getNext();
															//		}
																  //printiteratelinkedList(head);
																
																// printtillsecondLastNode(head);
	}
	
	public static void printiteratelinkedList(Node head) {
		System.out.println("Printing the Linked List");
		Node curr=head;
		
		while(curr !=null) {
			System.out.println(curr.getData());
			curr=curr.getNext();
			
		}
		
	}
	public static void printtillsecondLastNode(Node head) {
		Node curr = head;
		
		while(curr.getNext()!=null) {
			System.out.println(curr.getData());
			curr=curr.getNext();
			
		}
		
	}
	public static Node insertAtFront(Node head , int data) {
		Node nn = new Node(data);
		nn.setNext(null);
		
		if(head==null) {
			head=nn;
			
		}
		else {
			nn.setNext(head);
			head=nn;
		}
		return head;
	}
	
	public static Node insertAtEnd(Node head,int data) {
		Node nn1 = new Node(data);
		nn1.setNext(null);
		
		if(head==null) {
			head = nn1;
			return nn1;
			
		}
		Node curr=head;
		while(curr.getNext()!=null) {
			curr=curr.getNext();
			
		}
		curr.setNext(nn1);
		return head;
		
	}
	
	public static Node deleteAtFront(Node head) {
		
		Node curr=head;
		while(curr!=null) {
			head=head.getNext();
			return head;
			
		}
		return head;
		
	}
	
	public static Node insertAtIndex(Node head,int data,int index) {
		Node nunu= new Node(data);
		if(index==0) {
			nunu.setNext(head);
			head=nunu;
			return head;
			
		}
		
		Node curr=head;
		for(int i=0;i<index-1 && curr !=null;i++) {
			curr=curr.getNext();
			
		}
		if(curr == null) {
			System.out.println("Invalid Index");
			return head;
			
		}
			nunu.setNext(curr.getNext());
			curr.setNext(nunu);
			return head;
		
	}
	
	public static Node deleteAtIndex(Node head,int data,int index) {
		Node curr= head;
		Node prev = null;
		if(index ==0) {
			head=head.getNext();
			return head;
			
		}
		for(int i=0;i<index-1 && curr!=null;i++) {
			prev=curr;
			curr=curr.getNext();
			
		}
		if(curr==null) {
			System.out.println("Invalid Index");
			return head;
			
		}
		prev.setNext(curr.getNext());
		curr=curr.getNext();
		
		return head;
			
		
		
	}

}

