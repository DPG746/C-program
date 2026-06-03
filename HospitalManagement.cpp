#include <iostream>
#include <fstream>
using namespace std;

struct Patient
{
    int id;
    string name;
    int age;
    string disease;
    string status;
};

Patient p[100];
int countPatient = 0;

// Add Patient
void addPatient()
{
    cout << "\nEnter Patient ID: ";
    cin >> p[countPatient].id;

    cout << "Enter Name: ";
    cin >> p[countPatient].name;

    cout << "Enter Age: ";
    cin >> p[countPatient].age;

    cout << "Enter Disease: ";
    cin >> p[countPatient].disease;

    cout << "Enter Status (Admitted/Recovered): ";
    cin >> p[countPatient].status;

    countPatient++;
    cout << "Patient Added Successfully!\n";
}

// Display All Patients
void displayPatients()
{
    if(countPatient == 0)
    {
        cout << "No Records Found!\n";
        return;
    }

    cout << "\n--- Patient Records ---\n";
    for(int i = 0; i < countPatient; i++)
    {
        cout << "\nID: " << p[i].id;
        cout << "\nName: " << p[i].name;
        cout << "\nAge: " << p[i].age;
        cout << "\nDisease: " << p[i].disease;
        cout << "\nStatus: " << p[i].status << endl;
    }
}

// Search Patient
void searchPatient()
{
    int id;
    cout << "Enter Patient ID: ";
    cin >> id;

    for(int i = 0; i < countPatient; i++)
    {
        if(p[i].id == id)
        {
            cout << "\nPatient Found!";
            cout << "\nName: " << p[i].name;
            cout << "\nAge: " << p[i].age;
            cout << "\nDisease: " << p[i].disease;
            cout << "\nStatus: " << p[i].status << endl;
            return;
        }
    }

    cout << "Patient Not Found!\n";
}

// Update Status
void updateStatus()
{
    int id;
    cout << "Enter Patient ID: ";
    cin >> id;

    for(int i = 0; i < countPatient; i++)
    {
        if(p[i].id == id)
        {
            cout << "Enter New Status: ";
            cin >> p[i].status;
            cout << "Status Updated Successfully!\n";
            return;
        }
    }

    cout << "Patient Not Found!\n";
}

// Delete Patient
void deletePatient()
{
    int id;
    cout << "Enter Patient ID to Discharge: ";
    cin >> id;

    for(int i = 0; i < countPatient; i++)
    {
        if(p[i].id == id)
        {
            for(int j = i; j < countPatient - 1; j++)
            {
                p[j] = p[j + 1];
            }

            countPatient--;
            cout << "Patient Discharged Successfully!\n";
            return;
        }
    }

    cout << "Patient Not Found!\n";
}

// Doctor List
void displayDoctors()
{
    cout << "\n--- Doctor List ---\n";
    cout << "1. Dr. Ravi - Cardiologist\n";
    cout << "2. Dr. Priya - Neurologist\n";
    cout << "3. Dr. Kumar - Orthopedic\n";
    cout << "4. Dr. Meena - Pediatrician\n";
}

// Save File
void saveToFile()
{
    ofstream file("patients.txt");

    for(int i = 0; i < countPatient; i++)
    {
        file << p[i].id << " "
             << p[i].name << " "
             << p[i].age << " "
             << p[i].disease << " "
             << p[i].status << endl;
    }

    file.close();
    cout << "Records Saved Successfully!\n";
}

// Load File
void loadFromFile()
{
    ifstream file("patients.txt");

    countPatient = 0;

    while(file >> p[countPatient].id
               >> p[countPatient].name
               >> p[countPatient].age
               >> p[countPatient].disease
               >> p[countPatient].status)
    {
        countPatient++;
    }

    file.close();
    cout << "Records Loaded Successfully!\n";
}

int main()
{
    int choice;

    do
    {
        cout << "\n\n===== HOSPITAL MANAGEMENT SYSTEM =====";
        cout << "\n1. Add Patient";
        cout << "\n2. Display All Patients";
        cout << "\n3. Search Patient by ID";
        cout << "\n4. Update Patient Status";
        cout << "\n5. Delete / Discharge Patient";
        cout << "\n6. Display Doctor List";
        cout << "\n7. Save Records to File";
        cout << "\n8. Load Records from File";
        cout << "\n0. Exit";

        cout << "\nEnter Choice: ";
        cin >> choice;

        switch(choice)
        {
            case 1: addPatient(); break;
            case 2: displayPatients(); break;
            case 3: searchPatient(); break;
            case 4: updateStatus(); break;
            case 5: deletePatient(); break;
            case 6: displayDoctors(); break;
            case 7: saveToFile(); break;
            case 8: loadFromFile(); break;
            case 0: cout << "Exiting Program...\n"; break;
            default: cout << "Invalid Choice!\n";
        }

    } while(choice != 0);

    return 0;
}
