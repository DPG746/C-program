/*
 * ============================================================
 *  LIBRARY MANAGEMENT SYSTEM  (Menu-Driven C++)
 *  Features:
 *   1. Add Book
 *   2. Display All Books
 *   3. Search Book (by ID or Title)
 *   4. Issue Book to Member
 *   5. Return Book
 *   6. Display Issued Books
 *   7. Add Member
 *   8. Display All Members
 *   9. Save & Load from File
 *   0. Exit
 * ============================================================
 */
 
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <stdexcept>
#include <iomanip>
using namespace std;
 
// ══════════════════════════════════════════════════════════
//  BASE CLASS: LibraryItem
// ══════════════════════════════════════════════════════════
class LibraryItem {
protected:
    int    id;
    string title;
    bool   isAvailable;
 
public:
    LibraryItem(int id, string title)
        : id(id), title(title), isAvailable(true) {}
 
    virtual void display() const = 0;  // Pure virtual
 
    int    getId()          const { return id; }
    string getTitle()       const { return title; }
    bool   getAvailability()const { return isAvailable; }
    void   setAvailability(bool val) { isAvailable = val; }
 
    virtual ~LibraryItem() {}
};
 
// ══════════════════════════════════════════════════════════
//  DERIVED CLASS: Book
// ══════════════════════════════════════════════════════════
class Book : public LibraryItem {
    string author;
    string genre;
    int    year;
 
public:
    Book(int id, string title, string author, string genre, int year)
        : LibraryItem(id, title), author(author), genre(genre), year(year) {}
 
    string getAuthor() const { return author; }
    string getGenre()  const { return genre;  }
    int    getYear()   const { return year;   }
 
    void display() const override {
        cout << left
             << setw(6)  << id
             << setw(25) << title
             << setw(20) << author
             << setw(15) << genre
             << setw(6)  << year
             << setw(12) << (isAvailable ? "Available" : "Issued")
             << "\n";
    }
 
    string toFileString() const {
        return to_string(id) + "|" + title + "|" + author + "|"
             + genre + "|" + to_string(year) + "|"
             + (isAvailable ? "1" : "0");
    }
};
 
// ══════════════════════════════════════════════════════════
//  MEMBER CLASS
// ══════════════════════════════════════════════════════════
class Member {
    int    memberId;
    string name;
    string contact;
    vector<int> issuedBookIds;  // list of book IDs issued
 
public:
    Member(int id, string name, string contact)
        : memberId(id), name(name), contact(contact) {}
 
    int    getMemberId() const { return memberId; }
    string getName()     const { return name; }
    string getContact()  const { return contact; }
 
    void issueBook(int bookId)  { issuedBookIds.push_back(bookId); }
    void returnBook(int bookId) {
        for (auto it = issuedBookIds.begin(); it != issuedBookIds.end(); ++it) {
            if (*it == bookId) { issuedBookIds.erase(it); return; }
        }
    }
 
    bool hasBook(int bookId) const {
        for (int id : issuedBookIds)
            if (id == bookId) return true;
        return false;
    }
 
    void display() const {
        cout << left
             << setw(8)  << memberId
             << setw(22) << name
             << setw(16) << contact
             << "Books: ";
        if (issuedBookIds.empty()) cout << "None";
        else for (int id : issuedBookIds) cout << id << " ";
        cout << "\n";
    }
};
 
// ─── ISSUE RECORD ────────────────────────────────────────
struct IssueRecord {
    int bookId;
    int memberId;
    string memberName;
    string bookTitle;
};
 
// ─── GLOBAL DATA ─────────────────────────────────────────
vector<Book>        books;
vector<Member>      members;
vector<IssueRecord> issueLog;
int nextBookId   = 101;
int nextMemberId = 501;
 
// ─── UTILITY ─────────────────────────────────────────────
void pause() {
    cout << "\nPress Enter to continue...";
    cin.ignore();
    cin.get();
}
 
void printBookHeader() {
    cout << left
         << setw(6)  << "ID"
         << setw(25) << "Title"
         << setw(20) << "Author"
         << setw(15) << "Genre"
         << setw(6)  << "Year"
         << setw(12) << "Status"
         << "\n"
         << string(84, '-') << "\n";
}
 
// ─── 1. ADD BOOK ─────────────────────────────────────────
void addBook() {
    cout << "\n===== ADD BOOK =====\n";
    string title, author, genre;
    int year;
 
    cout << "Title  : "; cin.ignore(); getline(cin, title);
    cout << "Author : "; getline(cin, author);
    cout << "Genre  : "; getline(cin, genre);
    cout << "Year   : "; cin >> year;
 
    if (year < 1000 || year > 2100)
        throw invalid_argument("Invalid publication year.");
 
    books.emplace_back(nextBookId++, title, author, genre, year);
    cout << "Book added! ID: " << (nextBookId - 1) << "\n";
}
 
// ─── 2. DISPLAY ALL BOOKS ────────────────────────────────
void displayAllBooks() {
    cout << "\n===== ALL BOOKS =====\n";
    if (books.empty()) { cout << "No books in library.\n"; return; }
 
    printBookHeader();
    for (auto& b : books) b.display();
    cout << "\nTotal: " << books.size() << " books\n";
}
 
// ─── 3. SEARCH BOOK ──────────────────────────────────────
void searchBook() {
    cout << "\n===== SEARCH BOOK =====\n";
    cout << "1. Search by ID\n2. Search by Title\nChoice: ";
    int ch; cin >> ch;
 
    if (ch == 1) {
        int id; cout << "Enter Book ID: "; cin >> id;
        for (auto& b : books) {
            if (b.getId() == id) {
                printBookHeader();
                b.display();
                return;
            }
        }
        cout << "Book ID " << id << " not found.\n";
 
    } else if (ch == 2) {
        string keyword;
        cout << "Enter title keyword: "; cin.ignore(); getline(cin, keyword);
        bool found = false;
        printBookHeader();
        for (auto& b : books) {
            // Case-insensitive partial match
            string t = b.getTitle();
            string k = keyword;
            for (char& c : t) c = tolower(c);
            for (char& c : k) c = tolower(c);
            if (t.find(k) != string::npos) {
                b.display();
                found = true;
            }
        }
        if (!found) cout << "No books matching \"" << keyword << "\".\n";
    }
}
 
// ─── 4. ISSUE BOOK ───────────────────────────────────────
void issueBook() {
    cout << "\n===== ISSUE BOOK =====\n";
    int bookId, memberId;
    cout << "Enter Book ID   : "; cin >> bookId;
    cout << "Enter Member ID : "; cin >> memberId;
 
    Book*   book   = nullptr;
    Member* member = nullptr;
 
    for (auto& b : books)
        if (b.getId() == bookId) { book = &b; break; }
    for (auto& m : members)
        if (m.getMemberId() == memberId) { member = &m; break; }
 
    if (!book)   throw runtime_error("Book ID not found.");
    if (!member) throw runtime_error("Member ID not found.");
    if (!book->getAvailability())
        throw runtime_error("Book is already issued to someone else.");
 
    book->setAvailability(false);
    member->issueBook(bookId);
    issueLog.push_back({bookId, memberId, member->getName(), book->getTitle()});
 
    cout << "Book \"" << book->getTitle()
         << "\" issued to " << member->getName() << " successfully.\n";
}
 
// ─── 5. RETURN BOOK ──────────────────────────────────────
void returnBook() {
    cout << "\n===== RETURN BOOK =====\n";
    int bookId, memberId;
    cout << "Enter Book ID   : "; cin >> bookId;
    cout << "Enter Member ID : "; cin >> memberId;
 
    Book*   book   = nullptr;
    Member* member = nullptr;
 
    for (auto& b : books)
        if (b.getId() == bookId) { book = &b; break; }
    for (auto& m : members)
        if (m.getMemberId() == memberId) { member = &m; break; }
 
    if (!book)   throw runtime_error("Book ID not found.");
    if (!member) throw runtime_error("Member ID not found.");
    if (!member->hasBook(bookId))
        throw runtime_error("This member did not issue this book.");
 
    book->setAvailability(true);
    member->returnBook(bookId);
 
    // Remove from issue log
    for (auto it = issueLog.begin(); it != issueLog.end(); ++it) {
        if (it->bookId == bookId && it->memberId == memberId) {
            issueLog.erase(it);
            break;
        }
    }
 
    cout << "Book \"" << book->getTitle() << "\" returned successfully.\n";
}
 
// ─── 6. DISPLAY ISSUED BOOKS ─────────────────────────────
void displayIssuedBooks() {
    cout << "\n===== CURRENTLY ISSUED BOOKS =====\n";
    if (issueLog.empty()) { cout << "No books currently issued.\n"; return; }
 
    cout << left
         << setw(8)  << "BookID"
         << setw(25) << "Title"
         << setw(10) << "MemberID"
         << setw(20) << "Member Name"
         << "\n"
         << string(63, '-') << "\n";
 
    for (auto& r : issueLog)
        cout << left
             << setw(8)  << r.bookId
             << setw(25) << r.bookTitle
             << setw(10) << r.memberId
             << setw(20) << r.memberName
             << "\n";
}
 
// ─── 7. ADD MEMBER ───────────────────────────────────────
void addMember() {
    cout << "\n===== ADD MEMBER =====\n";
    string name, contact;
    cout << "Name    : "; cin.ignore(); getline(cin, name);
    cout << "Contact : "; getline(cin, contact);
 
    members.emplace_back(nextMemberId++, name, contact);
    cout << "Member added! ID: " << (nextMemberId - 1) << "\n";
}
 
// ─── 8. DISPLAY ALL MEMBERS ──────────────────────────────
void displayMembers() {
    cout << "\n===== ALL MEMBERS =====\n";
    if (members.empty()) { cout << "No members registered.\n"; return; }
 
    cout << left
         << setw(8)  << "ID"
         << setw(22) << "Name"
         << setw(16) << "Contact"
         << "Issued Books\n"
         << string(60, '-') << "\n";
 
    for (auto& m : members) m.display();
    cout << "\nTotal Members: " << members.size() << "\n";
}
 
// ─── 9. SAVE TO FILE ─────────────────────────────────────
void saveToFile() {
    ofstream file("library_books.txt");
    if (!file) throw runtime_error("Cannot open file.");
 
    for (auto& b : books)
        file << b.toFileString() << "\n";
 
    file.close();
    cout << "Books saved to library_books.txt (" << books.size() << " records)\n";
}
 
// ─── 9b. LOAD FROM FILE ──────────────────────────────────
void loadFromFile() {
    ifstream file("library_books.txt");
    if (!file) throw runtime_error("No saved file found.");
 
    books.clear();
    string line;
    while (getline(file, line)) {
        // id|title|author|genre|year|available
        auto tok = [&](string& s, int& pos) {
            int next = s.find('|', pos);
            string t = s.substr(pos, next - pos);
            pos = next + 1;
            return t;
        };
 
        int pos = 0;
        int id     = stoi(tok(line, pos));
        string ti  = tok(line, pos);
        string au  = tok(line, pos);
        string ge  = tok(line, pos);
        int year   = stoi(tok(line, pos));
        bool avail = (line.substr(pos, 1) == "1");
 
        Book b(id, ti, au, ge, year);
        b.setAvailability(avail);
        books.push_back(b);
        if (id >= nextBookId) nextBookId = id + 1;
    }
    file.close();
    cout << "Loaded " << books.size() << " books from file.\n";
}
 
// ─── MAIN MENU ───────────────────────────────────────────
int main() {
    int choice;
    do {
        cout << "\n╔══════════════════════════════════════╗\n";
        cout << "║     LIBRARY MANAGEMENT SYSTEM        ║\n";
        cout << "╠══════════════════════════════════════╣\n";
        cout << "║  1. Add Book                         ║\n";
        cout << "║  2. Display All Books                ║\n";
        cout << "║  3. Search Book                      ║\n";
        cout << "║  4. Issue Book to Member             ║\n";
        cout << "║  5. Return Book                      ║\n";
        cout << "║  6. Display Issued Books             ║\n";
        cout << "║  7. Add Member                       ║\n";
        cout << "║  8. Display All Members              ║\n";
        cout << "║  9. Save Books to File               ║\n";
        cout << "║ 10. Load Books from File             ║\n";
        cout << "║  0. Exit                             ║\n";
        cout << "╚══════════════════════════════════════╝\n";
        cout << "Choice: ";
        cin >> choice;
 
        try {
            switch (choice) {
                case 1:  addBook();           break;
                case 2:  displayAllBooks();   break;
                case 3:  searchBook();        break;
                case 4:  issueBook();         break;
                case 5:  returnBook();        break;
                case 6:  displayIssuedBooks();break;
                case 7:  addMember();         break;
                case 8:  displayMembers();    break;
                case 9:  saveToFile();        break;
                case 10: loadFromFile();      break;
                case 0:  cout << "Goodbye!\n"; break;
                default: cout << "Invalid choice.\n";
            }
        } catch (exception& e) {
            cout << "Error: " << e.what() << "\n";
        }
 
        if (choice != 0) pause();
 
    } while (choice != 0);
 
    return 0;
}
 
