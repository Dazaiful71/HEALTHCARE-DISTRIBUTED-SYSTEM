from xmlrpc.server import SimpleXMLRPCServer
from hashlib import sha256

# -------------------------
# Users & Authentication
# -------------------------
users = {
    "admin": sha256("adminpass".encode()).hexdigest(),
    "alice": sha256("alice123".encode()).hexdigest(),
    "bob": sha256("bob123".encode()).hexdigest()
}

patients = {}
appointments = []

def login(username, password):
    hashed = sha256(password.encode()).hexdigest()
    if username in users and users[username] == hashed:
        return {"status": "OK", "user": username}
    return {"status": "ERROR", "message": "Invalid credentials"}

# -------------------------
# Patient Management
# -------------------------
def add_patient(username, patient_id, name, age, gender):
    if username != "admin":
        return {"status": "ERROR", "message": "Unauthorized"}
    if patient_id in patients:
        return {"status": "ERROR", "message": "Patient already exists"}
    patients[patient_id] = {"name": name, "age": age, "gender": gender}
    return {"status": "OK", "message": f"Patient {name} added"}

def get_patient(username, patient_id):
    if patient_id not in patients:
        return {"status": "ERROR", "message": "Patient not found"}
    return {"status": "OK", "patient": patients[patient_id]}

# -------------------------
# Appointment Management
# -------------------------
def book_appointment(username, patient_id, doctor, date):
    if patient_id not in patients:
        return {"status": "ERROR", "message": "Invalid patient ID"}
    for appt in appointments:
        if appt["doctor"] == doctor and appt["date"] == date:
            return {"status": "ERROR", "message": "Slot unavailable"}
    appointment = {"patient_id": patient_id, "doctor": doctor, "date": date}
    appointments.append(appointment)
    return {"status": "OK", "message": f"Appointment booked with Dr. {doctor} on {date}"}

def list_appointments(username):
    return {"status": "OK", "appointments": appointments}

# -------------------------
# Run RPC Server
# -------------------------
server = SimpleXMLRPCServer(("localhost", 8000), allow_none=True)
print("Patient RPC Server running on port 8000...")

server.register_function(login, "login")
server.register_function(add_patient, "add_patient")
server.register_function(get_patient, "get_patient")
server.register_function(book_appointment, "book_appointment")
server.register_function(list_appointments, "list_appointments")

server.serve_forever()
