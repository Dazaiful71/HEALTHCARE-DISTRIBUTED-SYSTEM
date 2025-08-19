# Python RPC – Patient & Appointment System

## What it Does
This system manages patients and their appointments. Features include:
- Admin login
- Add new patients
- View patient details
- Book appointments
- List all appointments

The client connects to the server to perform these actions.

---

## Files
- `PatientServiceRPC.py` → Python RPC server (handles all patient data)
- `PatientClientRPC.py` → Python client (simulates user interaction)

---

## Requirements
- Python 3.x installed

---

## How to Run
1. Open a terminal and start the **server**:
python PatientServiceRPC.py

The server will start on localhost port 8000.
Open another terminal and run the client:

Edit
python PatientClientRPC.py

You will see output showing:
Admin login success
Patients added
Appointments booked
List of all appointments

Default Admin Account
Username: admin
Password: adminpass

Note: All data is stored in-memory, so it resets if the server restarts.
