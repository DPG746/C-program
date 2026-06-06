//Implemeting  Linked list with Array 

class Node
{
    int data;

    Node next;

    Node(int data)
    {
        this.data = data;

        this.next = null;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        int arr[] = {10,20,30,40,50};

        Node head = null;

        Node curr = null;

        int choice = 1;

        switch(choice)
        {
            case 1:

                // Convert Array to Linked List
                for(int i=0; i<arr.length; i++)
                {
                    Node nn = new Node(arr[i]);

                    if(head == null)
                    {
                        head = nn;

                        curr = nn;
                    }
                    else
                    {
                        curr.next = nn;

                        curr = nn;
                    }
                }

                System.out.println("Linked List Created");

                break;

            default:

                System.out.println("Invalid Choice");
        }

        // Print Linked List
        Node temp = head;

        while(temp != null)
        {
            System.out.println(temp.data);

            temp = temp.next;
        }
    }
}



//Implementing Staxk with Array---------------------------------------------------

package stacks;
import java.util.Scanner;


class Stack{
	int arr[]= new int[5];
	int top=-1;
	
	void push(int data) {
		if(top==arr.length-1) {
			System.out.println("Overflow");
			
		}else {
			top++;
			arr[top]=data;
			
			System.out.println(data + "inserted");
		}
		
	}
	
	void pop() {
		if(top==-1) {
			System.out.println("Underflow");
			
		}else {
			System.out.println(arr[top]+ "deleted");
			top--;
			
		}
	}
	
	void display() {
		if(top==-1) {
			System.out.println("Stack is empty");
			
			
		}
		else {
			for(int i=top;i>=0;i--) {
				System.out.println(arr[i]);
				
			}
		}
	}
	
}



public class StackExample {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Stack s = new Stack();
		int data;
		int choice;
		
		do {
			System.out.println("1: For Push");
			System.out.println("2: For Pop");
			System.out.println("3: For display");
			
			choice = scan.nextInt();
			
			
			switch(choice) {
			case 1: 
				System.out.println("Enter data:");
				data=scan.nextInt();
				s.push(data);
				break;
				
			case 2:
				s.pop();
				break;
				
			case 3: 
				System.out.println("For Display");
				s.display();
				break;
				
			case 4: System.out.println("Invalid Input");
				break;
				
			default: System.out.println("Exit");
					
			
			}
			
		}while(choice!=4);
		scan.close();
		
		

	}

}
