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




//MMMMMMMMMMMMMMMMM KMMMMMMMMMMMM M------------------------------------------------

//+----- Second Largest Element in Array -------+


import java.util.*;

public class SecondLargest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            if(arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            }
            else if(arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }

        System.out.println("Second Largest = " + secondLargest);
    }
}



//--------------------------------------------------------------------------------------------------------------
//+------ Insert Node at Specific Position (Linked List)-----+


import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class InsertAtPosition {

    static Node insert(Node head, int pos, int val) {
        Node newNode = new Node(val);

        if(pos == 1) {
            newNode.next = head;
            return newNode;
        }

        Node curr = head;

        for(int i = 1; i < pos - 1 && curr != null; i++) {
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;

        return head;
    }

    static void display(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        head = insert(head, 3, 99);

        display(head);
    }
}




//--------------------------------------------------------------------------------------------------------------
//+------ Delete Node by Value (Linked List) ------+


import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class DeleteByValue {

    static Node delete(Node head, int val) {

        if(head == null)
            return null;

        if(head.data == val)
            return head.next;

        Node temp = head;

        while(temp.next != null && temp.next.data != val) {
            temp = temp.next;
        }

        if(temp.next != null) {
            temp.next = temp.next.next;
        }

        return head;
    }

    static void display(Node head) {
        while(head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        head = delete(head, 30);

        display(head);
    }
}



//--------------------------------------------------------------------------------------------------------------

//+------ Generate Binary Numbers 1 to N Using Queue ------+


import java.util.*;

public class BinaryNumbers {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<String> q = new LinkedList<>();

        q.add("1");

        for(int i = 1; i <= n; i++) {
            String front = q.remove();

            System.out.print(front + " ");

            q.add(front + "0");
            q.add(front + "1");
        }
    }
}



//--------------------------------------------------------------------------------------------------------------
//+----- Priority Queue (Array Implementation) -------+


import java.util.*;

public class PriorityQueueDemo {

    static ArrayList<Integer> list = new ArrayList<>();

    static void insert(int data) {

        int i = 0;

        while(i < list.size() && list.get(i) > data) {
            i++;
        }

        list.add(i, data);
    }

    static void delete() {

        if(list.size() == 0) {
            System.out.println("Queue Empty");
            return;
        }

        System.out.println("Deleted: " + list.remove(0));
    }

    static void display() {

        for(int x : list) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        insert(10);
        insert(50);
        insert(20);

        delete();

        display();
    }
}


//--------------------------------------------------------------------------------------------------------------



//1. Jump Search

import java.util.Scanner;

public class Main {

    public static int jumpSearch(int[] arr, int key) {
        int n = arr.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;

        // Jump forward
        while (prev < n && arr[Math.min(step, n) - 1] < key) {
            prev = step;
            step += (int) Math.sqrt(n);

            if (prev >= n)
                return -1;
        }

        // Linear search in the identified block
        for (int i = prev; i < Math.min(step, n); i++) {
            if (arr[i] == key)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int key = sc.nextInt();

        System.out.println(jumpSearch(arr, key));

        sc.close();
    }
}
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//2. Circular Queue :  Implement a circular queue with ENQUEUE, DEQUEUE, DISPLAY operations.


import java.util.Scanner;

class CircularQueue {
    int[] queue;
    int front, rear, size;

    CircularQueue(int size) {
        this.size = size;
        queue = new int[size];
        front = -1;
        rear = -1;
    }

    void enqueue(int value) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue is Full");
            return;
        }

        if (front == -1) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % size;
        }

        queue[rear] = value;
    }

    void dequeue() {
        if (front == -1) {
            System.out.println("Queue is Empty");
            return;
        }

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % size;
        }
    }

    void display() {
        if (front == -1) {
            System.out.println("Queue is Empty");
            return;
        }

        int i = front;
        while (true) {
            System.out.print(queue[i] + " ");
            if (i == rear)
                break;
            i = (i + 1) % size;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        CircularQueue cq = new CircularQueue(size);

        int n = sc.nextInt(); // number of operations

        for (int i = 0; i < n; i++) {
            String op = sc.next();

            if (op.equalsIgnoreCase("ENQUEUE")) {
                int val = sc.nextInt();
                cq.enqueue(val);
            } else if (op.equalsIgnoreCase("DEQUEUE")) {
                cq.dequeue();
            } else if (op.equalsIgnoreCase("DISPLAY")) {
                cq.display();
            }
        }

        sc.close();
    }
}
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//3. Fibonacci using Recursion

import java.util.Scanner;

public class Main {

    static int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }

        sc.close();
    }
}
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//4. First Non-Repeating Character in Stream

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String stream = sc.next();

        int[] freq = new int[256];
        Queue<Character> q = new LinkedList<>();

        for (char ch : stream.toCharArray()) {
            freq[ch]++;
            q.offer(ch);

            while (!q.isEmpty() && freq[q.peek()] > 1) {
                q.poll();
            }

            if (q.isEmpty()) {
                System.out.print("-1 ");
            } else {
                System.out.print(q.peek() + " ");
            }
        }

        sc.close();
    }
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



