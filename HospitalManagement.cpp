/*
 * ============================================================
 *  HOSPITAL MANAGEMENT SYSTEM  (Menu-Driven C++)
 *  Features:
 *   1. Add Patient
 *   2. Display All Patients
 *   3. Search Patient by ID
 *   4. Update Patient Status
 *   5. Delete / Discharge Patient
 *   6. Display Doctor List
 *   7. Save Records to File
 *   8. Load Records from File
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
 
// ─── PATIENT CLASS ────────────────────────────────────────
class Patient {
private:
    int    id;
    string name;
    int    age;
    string disease;
    string doctorAssigned;
    string status;   // "Admitted" / "Discharged"
 
public:
    // Constructor
    Patient(int id, string name, int age, string disease,
            string doctor, string status = "Admitted")
        : id(id), name(name), age(age), disease(disease),
          doctorAssigned(doctor), status(status) {}
 
    // Getters
    int    getId()      const { return id; }
    string getName()    const { return name; }
    int    getAge()     const { return age; }
    string getDisease() const { return disease; }
    string getDoctor()  const { return doctorAssigned; }
    string getStatus()  const { return status; }
 
    // Setters
    void setStatus(string s) { status = s; }
    void setDoctor(string d) { doctorAssigned = d; }
 
    void display() const {
        cout << left
             << setw(6)  << id
             << setw(18) << name
             << setw(5)  << age
             << setw(18) << disease
             << setw(18) << doctorAssigned
             << setw(12) << status
             << "\n";
    }
 
    // For file saving
    string toFileString() const {
        return to_string(id) + "|" + name + "|" + to_string(age) + "|"
             + disease + "|" + doctorAssigned + "|" + status;
    }
};
 
// ─── DOCTOR (Base Class) ─────────────────────────────────
class Doctor {
protected:
    string name;
    string specialization;
 
public:
    Doctor(string n, string s) : name(n), specialization(s) {}
 
    virtual void display() const {
        cout << left << setw(22) << name << setw(20) << specialization;
    }
 
    string getName() const { return name; }
    virtual ~Doctor() {}
};
 
// ─── SENIOR DOCTOR (Derived Class) ───────────────────────
class SeniorDoctor : public Doctor {
    int experience;  // years
public:
    SeniorDoctor(string n, string s, int exp)
        : Doctor(n, s), experience(exp) {}
 
    void display() const override {
        Doctor::display();
        cout << setw(6) << experience << " yrs  [Senior]\n";
    }
};
 
// ─── JUNIOR DOCTOR (Derived Class) ───────────────────────
class JuniorDoctor : public Doctor {
public:
    JuniorDoctor(string n, string s) : Doctor(n, s) {}
 
    void display() const override {
        Doctor::display();
        cout << setw(6) << "-" << "       [Junior]\n";
    }
};
 
// ─── GLOBAL DATA ─────────────────────────────────────────
vector<Patient> patients;
int nextId = 1001;
 
// ─── UTILITY ─────────────────────────────────────────────
void printHeader() {
    cout << left
         << setw(6)  << "ID"
         << setw(18) << "Name"
         << setw(5)  << "Age"
         << setw(18) << "Disease"
         << setw(18) << "Doctor"
         << setw(12) << "Status"
         << "\n";
    cout << string(77, '-') << "\n";
}
 
void pause() {
    cout << "\nPress Enter to continue...";
    cin.ignore();
    cin.get();
}
 
// ─── 1. ADD PATIENT ──────────────────────────────────────
void addPatient() {
    cout << "\n===== ADD PATIENT =====\n";
    string name, disease, doctor;
    int age;
 
    cout << "Patient Name   : "; cin.ignore(); getline(cin, name);
    cout << "Age            : "; cin >> age;
 
    if (age <= 0 || age > 150)
        throw invalid_argument("Invalid age entered.");
 
    cout << "Disease        : "; cin.ignore(); getline(cin, disease);
    cout << "Assign Doctor  : "; getline(cin, doctor);
 
    patients.emplace_back(nextId++, name, age, disease, doctor);
    cout << "\nPatient added successfully! ID: " << (nextId - 1) << "\n";
}
 
// ─── 2. DISPLAY ALL ──────────────────────────────────────
void displayAll() {
    cout << "\n===== ALL PATIENTS =====\n";
    if (patients.empty()) { cout << "No patients found.\n"; return; }
 
    printHeader();
    for (auto& p : patients) p.display();
    cout << "\nTotal Patients: " << patients.size() << "\n";
}
 
// ─── 3. SEARCH BY ID ─────────────────────────────────────
void searchPatient() {
    cout << "\n===== SEARCH PATIENT =====\n";
    int id;
    cout << "Enter Patient ID: "; cin >> id;
 
    for (auto& p : patients) {
        if (p.getId() == id) {
            printHeader();
            p.display();
            return;
        }
    }
    cout << "Patient ID " << id << " not found.\n";
}
 
// ─── 4. UPDATE STATUS ────────────────────────────────────
void updateStatus() {
    cout << "\n===== UPDATE PATIENT STATUS =====\n";
    int id;
    cout << "Enter Patient ID: "; cin >> id;
 
    for (auto& p : patients) {
        if (p.getId() == id) {
            cout << "Current Status : " << p.getStatus() << "\n";
            cout << "1. Admitted\n2. Discharged\nChoice: ";
            int ch; cin >> ch;
            if (ch == 1) p.setStatus("Admitted");
            else if (ch == 2) p.setStatus("Discharged");
            else { cout << "Invalid choice.\n"; return; }
            cout << "Status updated successfully.\n";
            return;
        }
    }
    cout << "Patient not found.\n";
}
 
// ─── 5. DISCHARGE / DELETE PATIENT ───────────────────────
void dischargePatient() {
    cout << "\n===== DISCHARGE PATIENT =====\n";
    int id;
    cout << "Enter Patient ID to discharge: "; cin >> id;
 
    for (auto it = patients.begin(); it != patients.end(); ++it) {
        if (it->getId() == id) {
            cout << "Discharging: " << it->getName() << "\n";
            patients.erase(it);
            cout << "Patient record removed.\n";
            return;
        }
    }
    cout << "Patient not found.\n";
}
 
// ─── 6. DISPLAY DOCTORS ──────────────────────────────────
void displayDoctors() {
    cout << "\n===== DOCTOR LIST =====\n";
    cout << left << setw(22) << "Name" << setw(20) << "Specialization"
         << setw(6) << "Exp" << "  Type\n";
    cout << string(60, '-') << "\n";
 
    // Polymorphism demo
    vector<Doctor*> doctors = {
        new SeniorDoctor("Dr. Ramesh Patil",   "Cardiology",      15),
        new SeniorDoctor("Dr. Sunita Rao",     "Neurology",       12),
        new JuniorDoctor("Dr. Arjun Mehta",    "Orthopedics"        ),
        new JuniorDoctor("Dr. Priya Sharma",   "Dermatology"        ),
        new SeniorDoctor("Dr. Vikram Nair",    "General Surgery", 20),
        new JuniorDoctor("Dr. Anjali Desai",   "Pediatrics"         )
    };
 
    for (auto d : doctors) {
        d->display();
        delete d;
    }
}
 
// ─── 7. SAVE TO FILE ─────────────────────────────────────
void saveToFile() {
    ofstream file("hospital_records.txt");
    if (!file) throw runtime_error("Cannot open file for writing.");
 
    for (auto& p : patients)
        file << p.toFileString() << "\n";
 
    file.close();
    cout << "\nRecords saved to hospital_records.txt (" << patients.size() << " records)\n";
}
 
// ─── 8. LOAD FROM FILE ───────────────────────────────────
void loadFromFile() {
    ifstream file("hospital_records.txt");
    if (!file) throw runtime_error("Cannot open file. No saved records found.");
 
    patients.clear();
    string line;
    while (getline(file, line)) {
        // Format: id|name|age|disease|doctor|status
        int p1 = line.find('|');
        int p2 = line.find('|', p1+1);
        int p3 = line.find('|', p2+1);
        int p4 = line.find('|', p3+1);
        int p5 = line.find('|', p4+1);
 
        int id    = stoi(line.substr(0, p1));
        string nm = line.substr(p1+1, p2-p1-1);
        int age   = stoi(line.substr(p2+1, p3-p2-1));
        string dis= line.substr(p3+1, p4-p3-1);
        string doc= line.substr(p4+1, p5-p4-1);
        string st = line.substr(p5+1);
 
        patients.emplace_back(id, nm, age, dis, doc, st);
        if (id >= nextId) nextId = id + 1;
    }
    file.close();
    cout << "Loaded " << patients.size() << " records from file.\n";
}
 
// ─── MAIN MENU ───────────────────────────────────────────
int main() {
    int choice;
    do {
        cout << "\n╔══════════════════════════════════════╗\n";
        cout << "║     HOSPITAL MANAGEMENT SYSTEM       ║\n";
        cout << "╠══════════════════════════════════════╣\n";
        cout << "║  1. Add Patient                      ║\n";
        cout << "║  2. Display All Patients             ║\n";
        cout << "║  3. Search Patient by ID             ║\n";
        cout << "║  4. Update Patient Status            ║\n";
        cout << "║  5. Discharge Patient                ║\n";
        cout << "║  6. View Doctor List                 ║\n";
        cout << "║  7. Save Records to File             ║\n";
        cout << "║  8. Load Records from File           ║\n";
        cout << "║  0. Exit                             ║\n";
        cout << "╚══════════════════════════════════════╝\n";
        cout << "Choice: ";
        cin >> choice;
 
        try {
            switch (choice) {
                case 1: addPatient();      break;
                case 2: displayAll();      break;
                case 3: searchPatient();   break;
                case 4: updateStatus();    break;
                case 5: dischargePatient();break;
                case 6: displayDoctors();  break;
                case 7: saveToFile();      break;
                case 8: loadFromFile();    break;
                case 0: cout << "Goodbye!\n"; break;
                default: cout << "Invalid choice.\n";
            }
        } catch (exception& e) {
            cout << "Error: " << e.what() << "\n";
        }
 
        if (choice != 0) pause();
 
    } while (choice != 0);
 
    return 0;
}
 
