#include <iostream>
#include <fstream>
using namespace std;

struct Book
{
    int id;
    string title;
    string author;
    bool issued;
};

struct Member
{
    int id;
    string name;
};

Book books[100];
Member members[100];

int bookCount = 0;
int memberCount = 0;

// Add Book
void addBook()
{
    cout << "\nEnter Book ID: ";
    cin >> books[bookCount].id;

    cout << "Enter Book Title: ";
    cin >> books[bookCount].title;

    cout << "Enter Author Name: ";
    cin >> books[bookCount].author;

    books[bookCount].issued = false;
    bookCount++;

    cout << "Book Added Successfully!\n";
}

// Display Books
void displayBooks()
{
    if(bookCount == 0)
    {
        cout << "No Books Available!\n";
        return;
    }

    cout << "\n--- Book List ---\n";
    for(int i = 0; i < bookCount; i++)
    {
        cout << "\nID: " << books[i].id;
        cout << "\nTitle: " << books[i].title;
        cout << "\nAuthor: " << books[i].author;
        cout << "\nStatus: "
             << (books[i].issued ? "Issued" : "Available")
             << "\n";
    }
}

// Search Book
void searchBook()
{
    int id;
    cout << "Enter Book ID: ";
    cin >> id;

    for(int i = 0; i < bookCount; i++)
    {
        if(books[i].id == id)
        {
            cout << "\nBook Found!";
            cout << "\nTitle: " << books[i].title;
            cout << "\nAuthor: " << books[i].author;
            cout << "\nStatus: "
                 << (books[i].issued ? "Issued" : "Available")
                 << endl;
            return;
        }
    }

    cout << "Book Not Found!\n";
}

// Issue Book
void issueBook()
{
    int id;
    cout << "Enter Book ID to Issue: ";
    cin >> id;

    for(int i = 0; i < bookCount; i++)
    {
        if(books[i].id == id)
        {
            if(books[i].issued)
                cout << "Book Already Issued!\n";
            else
            {
                books[i].issued = true;
                cout << "Book Issued Successfully!\n";
            }
            return;
        }
    }

    cout << "Book Not Found!\n";
}

// Return Book
void returnBook()
{
    int id;
    cout << "Enter Book ID to Return: ";
    cin >> id;

    for(int i = 0; i < bookCount; i++)
    {
        if(books[i].id == id)
        {
            books[i].issued = false;
            cout << "Book Returned Successfully!\n";
            return;
        }
    }

    cout << "Book Not Found!\n";
}

// Display Issued Books
void displayIssuedBooks()
{
    cout << "\n--- Issued Books ---\n";

    for(int i = 0; i < bookCount; i++)
    {
        if(books[i].issued)
        {
            cout << books[i].id << " - "
                 << books[i].title << endl;
        }
    }
}

// Add Member
void addMember()
{
    cout << "\nEnter Member ID: ";
    cin >> members[memberCount].id;

    cout << "Enter Member Name: ";
    cin >> members[memberCount].name;

    memberCount++;

    cout << "Member Added Successfully!\n";
}

// Display Members
void displayMembers()
{
    if(memberCount == 0)
    {
        cout << "No Members Found!\n";
        return;
    }

    cout << "\n--- Member List ---\n";

    for(int i = 0; i < memberCount; i++)
    {
        cout << "ID: " << members[i].id
             << "  Name: " << members[i].name
             << endl;
    }
}

// Save Data
void saveData()
{
    ofstream file("library.txt");

    file << bookCount << endl;

    for(int i = 0; i < bookCount; i++)
    {
        file << books[i].id << " "
             << books[i].title << " "
             << books[i].author << " "
             << books[i].issued << endl;
    }

    file.close();

    cout << "Data Saved Successfully!\n";
}

// Load Data
void loadData()
{
    ifstream file("library.txt");

    if(!file)
    {
        cout << "File Not Found!\n";
        return;
    }

    file >> bookCount;

    for(int i = 0; i < bookCount; i++)
    {
        file >> books[i].id
             >> books[i].title
             >> books[i].author
             >> books[i].issued;
    }

    file.close();

    cout << "Data Loaded Successfully!\n";
}

int main()
{
    int choice;

    do
    {
        cout << "\n\n===== LIBRARY MANAGEMENT SYSTEM =====";
        cout << "\n1. Add Book";
        cout << "\n2. Display All Books";
        cout << "\n3. Search Book";
        cout << "\n4. Issue Book";
        cout << "\n5. Return Book";
        cout << "\n6. Display Issued Books";
        cout << "\n7. Add Member";
        cout << "\n8. Display All Members";
        cout << "\n9. Save & Load from File";
        cout << "\n0. Exit";

        cout << "\nEnter Choice: ";
        cin >> choice;

        switch(choice)
        {
            case 1: addBook(); break;
            case 2: displayBooks(); break;
            case 3: searchBook(); break;
            case 4: issueBook(); break;
            case 5: returnBook(); break;
            case 6: displayIssuedBooks(); break;
            case 7: addMember(); break;
            case 8: displayMembers(); break;
            case 9:
                saveData();
                loadData();
                break;
            case 0:
                cout << "Exiting Program...\n";
                break;
            default:
                cout << "Invalid Choice!\n";
        }

    } while(choice != 0);

    return 0;
}
