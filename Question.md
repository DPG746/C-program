

// ============================================================
// TOPIC 1: SINGLY LINKED LIST
// ------------------------------------------------------------
// QUESTION: Create a menu-driven program in Java that supports
// the following operations on a singly linked list:
//   - Insert at Beginning, End, Given Position
//   - Delete from Beginning, End, Given Position
//   - Traverse the list
// Handle invalid inputs like deleting from an empty list.
// ============================================================


class SLLNode {
    int data;
    SLLNode next;
    SLLNode(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {

    SLLNode head;

    // INSERT AT BEGINNING
    // Logic: newNode → oldHead, then head = newNode
    void insertAtBeginning(int data) {
        SLLNode newNode = new SLLNode(data);
        newNode.next = head;   // point to old head
        head = newNode;        // update head
        System.out.println(data + " inserted at beginning.");
    }

    // INSERT AT END
    // Logic: travel to last node (next==null), then last.next = newNode
    void insertAtEnd(int data) {
        SLLNode newNode = new SLLNode(data);
        if (head == null) {           // if list is empty
            head = newNode;
            System.out.println(data + " inserted at end (list was empty).");
            return;
        }
        SLLNode temp = head;
        while (temp.next != null) {   // travel to last node
            temp = temp.next;
        }
        temp.next = newNode;          // attach new node
        System.out.println(data + " inserted at end.");
    }

    // INSERT AT POSITION
    // Logic: travel to (position-1) node, then insert between
    void insertAtPosition(int data, int position) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            insertAtBeginning(data);
            return;
        }
        SLLNode newNode = new SLLNode(data);
        SLLNode temp = head;
        for (int i = 1; i < position - 1; i++) {  // go to (pos-1)th node
            if (temp == null) {
                System.out.println("Position out of range!");
                return;
            }
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range!");
            return;
        }
        newNode.next = temp.next;   // newNode points to next node
        temp.next = newNode;        // previous node points to newNode
        System.out.println(data + " inserted at position " + position);
    }

    // DELETE AT BEGINNING
    // Logic: head = head.next (skip first node)
    void deleteAtBeginning() {
        if (head == null) {
            System.out.println("List is empty! Cannot delete.");
            return;
        }
        System.out.println(head.data + " deleted from beginning.");
        head = head.next;   // move head forward
    }

    // DELETE AT END
    // Logic: travel to second-last node, set its next = null
    void deleteAtEnd() {
        if (head == null) {
            System.out.println("List is empty! Cannot delete.");
            return;
        }
        if (head.next == null) {   // only one node
            System.out.println(head.data + " deleted from end.");
            head = null;
            return;
        }
        SLLNode temp = head;
        while (temp.next.next != null) {  // stop at second-last
            temp = temp.next;
        }
        System.out.println(temp.next.data + " deleted from end.");
        temp.next = null;   // remove last node
    }

    // DELETE AT POSITION
    // Logic: travel to (position-1) node, skip the target node
    void deleteAtPosition(int position) {
        if (head == null) {
            System.out.println("List is empty! Cannot delete.");
            return;
        }
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            deleteAtBeginning();
            return;
        }
        SLLNode temp = head;
        for (int i = 1; i < position - 1; i++) {  // go to (pos-1)th node
            if (temp.next == null) {
                System.out.println("Position out of range!");
                return;
            }
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Position out of range!");
            return;
        }
        System.out.println(temp.next.data + " deleted from position " + position);
        temp.next = temp.next.next;   // skip target node
    }

    // TRAVERSE
    // Logic: start from head, print each node until null
    void traverse() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        SLLNode temp = head;
        System.out.print("List: ");
        while (temp != null) {
            System.out.print(temp.data + " → ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // MENU-DRIVEN MAIN
    static void run() {
        Scanner sc = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();
        int choice;
        do {
            System.out.println("\n===== SINGLY LINKED LIST MENU =====");
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete from Beginning");
            System.out.println("5. Delete from End");
            System.out.println("6. Delete from Position");
            System.out.println("7. Traverse");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    list.insertAtBeginning(sc.nextInt());
                    list.traverse();
                    break;
                case 2:
                    System.out.print("Enter value: ");
                    list.insertAtEnd(sc.nextInt());
                    list.traverse();
                    break;
                case 3:
                    System.out.print("Enter value: ");
                    int val = sc.nextInt();
                    System.out.print("Enter position: ");
                    list.insertAtPosition(val, sc.nextInt());
                    list.traverse();
                    break;
                case 4:
                    list.deleteAtBeginning();
                    list.traverse();
                    break;
                case 5:
                    list.deleteAtEnd();
                    list.traverse();
                    break;
                case 6:
                    System.out.print("Enter position: ");
                    list.deleteAtPosition(sc.nextInt());
                    list.traverse();
                    break;
                case 7:
                    list.traverse();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}


// ============================================================
// TOPIC 2: DOUBLY LINKED LIST
// ------------------------------------------------------------
// QUESTION: Design and implement a Doubly Linked List with:
//   - Insertion (beginning, end, position)
//   - Deletion (beginning, end, position)
//   - Forward Traversal
//   - Backward Traversal
// ============================================================


class DLLNode {
    int data;
    DLLNode prev;
    DLLNode next;
    DLLNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {

    DLLNode head;
    DLLNode tail;

    // INSERT AT BEGINNING
    void insertAtBeginning(int data) {
        DLLNode newNode = new DLLNode(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;    // newNode points forward to old head
            head.prev = newNode;    // old head points back to newNode
            head = newNode;         // update head
        }
        System.out.println(data + " inserted at beginning.");
    }

    // INSERT AT END
    void insertAtEnd(int data) {
        DLLNode newNode = new DLLNode(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;    // old tail points forward to newNode
            newNode.prev = tail;    // newNode points back to old tail
            tail = newNode;         // update tail
        }
        System.out.println(data + " inserted at end.");
    }

    // INSERT AT POSITION
    void insertAtPosition(int data, int position) {
        if (position <= 0) { System.out.println("Invalid position!"); return; }
        if (position == 1) { insertAtBeginning(data); return; }

        DLLNode newNode = new DLLNode(data);
        DLLNode temp = head;
        for (int i = 1; i < position - 1; i++) {
            if (temp == null) { System.out.println("Position out of range!"); return; }
            temp = temp.next;
        }
        if (temp == null) { System.out.println("Position out of range!"); return; }

        newNode.next = temp.next;       // newNode → next
        newNode.prev = temp;            // newNode ← temp
        if (temp.next != null)
            temp.next.prev = newNode;   // next node ← newNode
        else
            tail = newNode;             // if inserting at end, update tail
        temp.next = newNode;            // temp → newNode
        System.out.println(data + " inserted at position " + position);
    }

    // DELETE AT BEGINNING
    void deleteAtBeginning() {
        if (head == null) { System.out.println("List is empty!"); return; }
        System.out.println(head.data + " deleted from beginning.");
        if (head == tail) { head = tail = null; return; }  // only one node
        head = head.next;
        head.prev = null;   // remove back link
    }

    // DELETE AT END
    void deleteAtEnd() {
        if (tail == null) { System.out.println("List is empty!"); return; }
        System.out.println(tail.data + " deleted from end.");
        if (head == tail) { head = tail = null; return; }  // only one node
        tail = tail.prev;
        tail.next = null;   // remove forward link
    }

    // DELETE AT POSITION
    void deleteAtPosition(int position) {
        if (head == null) { System.out.println("List is empty!"); return; }
        if (position <= 0) { System.out.println("Invalid position!"); return; }
        if (position == 1) { deleteAtBeginning(); return; }

        DLLNode temp = head;
        for (int i = 1; i < position; i++) {
            if (temp == null) { System.out.println("Position out of range!"); return; }
            temp = temp.next;
        }
        if (temp == null) { System.out.println("Position out of range!"); return; }

        System.out.println(temp.data + " deleted from position " + position);
        if (temp.prev != null) temp.prev.next = temp.next;   // skip temp forward
        if (temp.next != null) temp.next.prev = temp.prev;   // skip temp backward
        if (temp == tail) tail = temp.prev;                   // update tail if needed
    }

    // FORWARD TRAVERSAL
    void traverseForward() {
        if (head == null) { System.out.println("List is empty!"); return; }
        DLLNode temp = head;
        System.out.print("Forward:  null ← ");
        while (temp != null) {
            System.out.print(temp.data + " ↔ ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // BACKWARD TRAVERSAL (start from tail, follow prev)
    void traverseBackward() {
        if (tail == null) { System.out.println("List is empty!"); return; }
        DLLNode temp = tail;
        System.out.print("Backward: null ← ");
        while (temp != null) {
            System.out.print(temp.data + " ↔ ");
            temp = temp.prev;
        }
        System.out.println("null");
    }

    // MENU-DRIVEN MAIN
    static void run() {
        Scanner sc = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();
        int choice;
        do {
            System.out.println("\n===== DOUBLY LINKED LIST MENU =====");
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete from Beginning");
            System.out.println("5. Delete from End");
            System.out.println("6. Delete from Position");
            System.out.println("7. Forward Traversal");
            System.out.println("8. Backward Traversal");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    list.insertAtBeginning(sc.nextInt());
                    list.traverseForward();
                    break;
                case 2:
                    System.out.print("Enter value: ");
                    list.insertAtEnd(sc.nextInt());
                    list.traverseForward();
                    break;
                case 3:
                    System.out.print("Enter value: "); int v = sc.nextInt();
                    System.out.print("Enter position: ");
                    list.insertAtPosition(v, sc.nextInt());
                    list.traverseForward();
                    break;
                case 4: list.deleteAtBeginning(); list.traverseForward(); break;
                case 5: list.deleteAtEnd(); list.traverseForward(); break;
                case 6:
                    System.out.print("Enter position: ");
                    list.deleteAtPosition(sc.nextInt());
                    list.traverseForward();
                    break;
                case 7: list.traverseForward(); break;
                case 8: list.traverseBackward(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}


// ============================================================
// TOPIC 3: STACK USING LINKED LIST
// ------------------------------------------------------------
// QUESTION: Implement a Stack using a Singly Linked List.
// Operations: Push, Pop, Peek (top element), Display, isEmpty
// LIFO: Last In First Out
// ============================================================


class StackNode {
    int data;
    StackNode next;
    StackNode(int data) { this.data = data; this.next = null; }
}

class StackUsingLL {

    StackNode top;  // top of stack = head of linked list

    boolean isEmpty() { return top == null; }

    // PUSH: insert at beginning (top)
    void push(int data) {
        StackNode newNode = new StackNode(data);
        newNode.next = top;   // new node points to current top
        top = newNode;        // new node becomes top
        System.out.println(data + " pushed to stack.");
    }

    // POP: delete from beginning (top)
    int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Stack is empty.");
            return -1;
        }
        int popped = top.data;
        top = top.next;   // move top down
        return popped;
    }

    // PEEK: see top element without removing
    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return top.data;
    }

    // DISPLAY
    void display() {
        if (isEmpty()) { System.out.println("Stack is empty!"); return; }
        StackNode temp = top;
        System.out.print("Stack (TOP → BOTTOM): ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        StackUsingLL stack = new StackUsingLL();
        int choice;
        do {
            System.out.println("\n===== STACK USING LINKED LIST =====");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek (Top)");
            System.out.println("4. Display");
            System.out.println("5. Check if Empty");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    stack.push(sc.nextInt());
                    stack.display();
                    break;
                case 2:
                    int popped = stack.pop();
                    if (popped != -1)
                        System.out.println("Popped: " + popped);
                    stack.display();
                    break;
                case 3:
                    int top = stack.peek();
                    if (top != -1)
                        System.out.println("Top element: " + top);
                    break;
                case 4: stack.display(); break;
                case 5: System.out.println(stack.isEmpty() ? "Stack is EMPTY" : "Stack is NOT empty"); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}


// ============================================================
// TOPIC 4: QUEUE USING LINKED LIST
// ------------------------------------------------------------
// QUESTION: Implement a Queue using a Singly Linked List.
// Operations: Enqueue, Dequeue, Peek (front), Display, isEmpty
// FIFO: First In First Out
// ============================================================


class QueueNode {
    int data;
    QueueNode next;
    QueueNode(int data) { this.data = data; this.next = null; }
}

class QueueUsingLL {

    QueueNode front;   // for dequeue (delete)
    QueueNode rear;    // for enqueue (insert)

    boolean isEmpty() { return front == null; }

    // ENQUEUE: insert at rear
    void enqueue(int data) {
        QueueNode newNode = new QueueNode(data);
        if (rear == null) {           // empty queue
            front = rear = newNode;
        } else {
            rear.next = newNode;      // attach to rear
            rear = newNode;           // update rear
        }
        System.out.println(data + " enqueued.");
    }

    // DEQUEUE: delete from front
    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! Queue is empty.");
            return -1;
        }
        int dequeued = front.data;
        front = front.next;           // move front forward
        if (front == null) rear = null;  // queue became empty
        return dequeued;
    }

    // PEEK: see front element
    int peek() {
        if (isEmpty()) { System.out.println("Queue is empty!"); return -1; }
        return front.data;
    }

    // DISPLAY
    void display() {
        if (isEmpty()) { System.out.println("Queue is empty!"); return; }
        QueueNode temp = front;
        System.out.print("Queue (FRONT → REAR): ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        QueueUsingLL queue = new QueueUsingLL();
        int choice;
        do {
            System.out.println("\n===== QUEUE USING LINKED LIST =====");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek (Front)");
            System.out.println("4. Display");
            System.out.println("5. Check if Empty");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    queue.enqueue(sc.nextInt());
                    queue.display();
                    break;
                case 2:
                    int d = queue.dequeue();
                    if (d != -1) System.out.println("Dequeued: " + d);
                    queue.display();
                    break;
                case 3:
                    int f = queue.peek();
                    if (f != -1) System.out.println("Front: " + f);
                    break;
                case 4: queue.display(); break;
                case 5: System.out.println(queue.isEmpty() ? "Queue is EMPTY" : "Queue is NOT empty"); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}


// ============================================================
// TOPIC 5: BUBBLE SORT
// ------------------------------------------------------------
// QUESTION: Implement Bubble Sort to sort an array in
// ascending order. Show array after each pass.
// Time Complexity: O(n²) | Space: O(1)
// ============================================================

class BubbleSort {
    static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {          // n-1 passes
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {  // compare adjacent
                if (arr[j] > arr[j + 1]) {
                    // SWAP
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.print("After pass " + (i + 1) + ": ");
            printArray(arr);
            if (!swapped) {   // optimization: stop if no swap in a pass
                System.out.println("Array already sorted! Stopping early.");
                break;
            }
        }
    }

    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print("Original array: ");
        printArray(arr);
        sort(arr);
        System.out.print("Sorted array:   ");
        printArray(arr);
    }
}


// ============================================================
// TOPIC 6: SELECTION SORT
// ------------------------------------------------------------
// QUESTION: Implement Selection Sort to sort an array.
// Show minimum element selected in each pass.
// Time Complexity: O(n²) | Space: O(1)
// ============================================================


class SelectionSort {
    static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;                         // assume first is minimum
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;                     // found new minimum
                }
            }
            // Swap minimum with first unsorted element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;

            System.out.print("After pass " + (i + 1) + " (placed " + arr[i] + "): ");
            printArray(arr);
        }
    }

    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print("Original array: ");
        printArray(arr);
        sort(arr);
        System.out.print("Sorted array:   ");
        printArray(arr);
    }
}


// ============================================================
// TOPIC 7: INSERTION SORT
// ------------------------------------------------------------
// QUESTION: Implement Insertion Sort to sort an array.
// Show array state after each insertion.
// Time Complexity: O(n²) worst, O(n) best | Space: O(1)
// ============================================================

class InsertionSort {
    static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];       // element to be inserted
            int j = i - 1;
            // Shift elements greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;       // place key in correct position

            System.out.print("After inserting " + key + ": ");
            printArray(arr);
        }
    }

    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print("Original array: ");
        printArray(arr);
        sort(arr);
        System.out.print("Sorted array:   ");
        printArray(arr);
    }
}


// ============================================================
// TOPIC 8: LINEAR SEARCH (Sequential Search)
// ------------------------------------------------------------
// QUESTION: Implement Linear Search to find an element in
// an array. Display the index if found, or not found message.
// Time Complexity: O(n)
// ============================================================

class LinearSearch {
    static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Checking index " + i + " → " + arr[i]);
            if (arr[i] == target) {
                return i;   // found at index i
            }
        }
        return -1;          // not found
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print("Enter element to search: ");
        int target = sc.nextInt();

        System.out.println("\nSearching for " + target + "...");
        int result = search(arr, target);
        if (result == -1)
            System.out.println(target + " NOT FOUND in array.");
        else
            System.out.println(target + " FOUND at index " + result);
    }
}


// ============================================================
// TOPIC 9: BINARY SEARCH
// ------------------------------------------------------------
// QUESTION: Implement Binary Search to find an element.
// IMPORTANT: Array must be SORTED first!
// Time Complexity: O(log n)
// ============================================================

class BinarySearch {
    static int search(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int step = 0;

        while (low <= high) {
            step++;
            int mid = (low + high) / 2;   // find middle index
            System.out.println("Step " + step + ": low=" + low + " mid=" + mid + " high=" + high + " arr[mid]=" + arr[mid]);

            if (arr[mid] == target) {
                return mid;               // found
            } else if (arr[mid] < target) {
                low = mid + 1;            // search right half
                System.out.println("  → " + target + " > " + arr[mid] + ", go RIGHT");
            } else {
                high = mid - 1;           // search left half
                System.out.println("  → " + target + " < " + arr[mid] + ", go LEFT");
            }
        }
        return -1;   // not found
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements (SORTED array): ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter SORTED elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print("Enter element to search: ");
        int target = sc.nextInt();

        System.out.println("\nBinary Search for " + target + "...");
        int result = search(arr, target);
        if (result == -1)
            System.out.println(target + " NOT FOUND in array.");
        else
            System.out.println(target + " FOUND at index " + result);
    }
}





// ============================================================
// TOPIC 1: MERGE SORT
// ------------------------------------------------------------
// QUESTION: Implement Merge Sort algorithm to sort an array
// in ascending order. Display array at each merge step.
// Time Complexity: O(n log n) always
// Space Complexity: O(n) — needs extra array
// ============================================================


// Java program for Merge Sort
import java.util.Scanner;

class MergeSort {
  
    // Merges two subarrays of a[]
    void merge(int a[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = a[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = a[m + 1 + j];
        // Merge the temp arrays
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            }
            else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts a[l..r] using merge()
    void sort(int a[], int l, int r)
    {
        if (l < r) {
            int m = (l + r) / 2;
            // Sort first and second halves
            sort(a, l, m);
            sort(a, m + 1, r);
            // Merge the sorted halves
            merge(a, l, m, r);
        }
    }

    // Driver method
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        // ── ONLY CHANGE: take input from user ──
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int a[] = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        // ── END OF CHANGE ──

        // Calling of Merge Sort
        MergeSort ob = new MergeSort();
        ob.sort(a, 0, a.length - 1);

        System.out.print("Sorted array: ");
        for (int i = 0; i < n; ++i)
            System.out.print(a[i] + " ");

        sc.close();
    }
}



// ============================================================
// TOPIC 2: QUICK SORT
// ------------------------------------------------------------
// QUESTION: Implement Quick Sort algorithm to sort an array.
// Use last element as pivot. Show pivot placement each step.
// Time Complexity: O(n log n) avg, O(n²) worst case
// Space Complexity: O(log n) — stack space for recursion
// ============================================================


    // Java program for implementation of QuickSort
import java.util.Scanner;

class QuickSort
{
    int partition(int a[], int low, int high)
    {
        int pivot = a[high]; 
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
          
            // If current element is smaller than or
            // equal to pivot
            if (a[j] <= pivot)
            {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i+1];
        a[i+1] = a[high];
        a[high] = temp;
        return i+1;
    }
    /* The main function that implements QuickSort()
      a[] --> Array to be sorted,
      l  --> Starting index,
      h  --> Ending index */
    void sort(int a[], int l, int h)
    {
        if (l < h)
        {
            int pi = partition(a, l, h);
            // Recursively sort elements before
            // partition and after partition
            sort(a, l, pi-1);
            sort(a, pi+1, h);
        }
    }
    // Driver program
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        // ── ONLY CHANGE: take input from user ──
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int a[] = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        // ── END OF CHANGE ──

        QuickSort ob = new QuickSort();
        ob.sort(a, 0, n-1);

        System.out.print("Sorted array: ");
        for (int i=0; i<n; ++i)
            System.out.print(a[i]+" ");

        sc.close();
    }
}


// ============================================================
// TOPIC 3: HEAP SORT
// ------------------------------------------------------------
// QUESTION: Implement Heap Sort algorithm to sort an array.
// Use Max Heap. Show heap after each heapify operation.
// Time Complexity: O(n log n) always
// Space Complexity: O(1) — sorts in place!
// ============================================================

class HeapSort {

    // HEAPIFY: make subtree rooted at index i a max heap
    // n = size of heap, i = root of subtree to heapify
    static void heapify(int[] arr, int n, int i) {
        int largest = i;          // assume root is largest
        int left    = 2 * i + 1; // left child index
        int right   = 2 * i + 2; // right child index

        // Check if left child exists and is greater than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // Check if right child exists and is greater than current largest
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is NOT root, swap and continue heapifying
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }

    static void sort(int[] arr) {
        int n = arr.length;

        // STEP 1: BUILD MAX HEAP
        // Start from last non-leaf node and heapify each node
        // Last non-leaf = n/2 - 1
        System.out.println("── Building Max Heap ──");
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        System.out.print("Max Heap built: ");
        printArray(arr, n);

        // STEP 2: EXTRACT MAX ONE BY ONE
        System.out.println("\n── Extracting Elements ──");
        for (int i = n - 1; i > 0; i--) {
            // Swap root (max) with last element
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            System.out.print("Placed " + arr[i] + " at index " + i + " → Heap: ");
            printArray(arr, i); // print only unsorted portion

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    static void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++) System.out.print(arr[i] + " ");
        if (size < arr.length) {
            System.out.print("| Sorted: ");
            for (int i = size; i < arr.length; i++) System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        System.out.print("\nOriginal array: ");
        printArray(arr);
        System.out.println();
        sort(arr);
        System.out.print("\nFinal sorted:   ");
        printArray(arr);
    }
}

