# Java RMI – Doctor, Prescription & Billing System

## What it Does
This system manages doctors, prescriptions, and patient billing. Features include:
- Doctor/Admin login
- Add and view doctors
- Add and view prescriptions
- Generate bills
- Pay bills partially or fully
  
The client connects to the server to perform these actions.

---

## Files
- `DoctorService.java` → Remote interface for RMI
- `DoctorServiceImpl.java` → Implementation of RMI methods
- `DoctorServer.java` → Starts the RMI server
- `DoctorClient.java` → Client that interacts with the server
- `rmi_classes/` → Optional folder for compiled `.class` files

## Requirements
- Java JDK 8 or higher
- Terminal / Command Prompt

## How to Run
1. Compile all Java files:
javac DoctorService.java DoctorServiceImpl.java DoctorServer.java DoctorClient.java
Start the RMI registry in a separate terminal:

Edit
rmiregistry 1099
Start the server in another terminal:

Edit
java DoctorServer
Run the client in a new terminal:
Edit
java DoctorClient

You will see output showing:
Doctor login success
Doctors added
Prescriptions added
Bills generated and partially paid

Default Doctor Account
Username: doctor1
Password: pass123

Note: All data is stored in-memory, so it resets if the server restarts.
