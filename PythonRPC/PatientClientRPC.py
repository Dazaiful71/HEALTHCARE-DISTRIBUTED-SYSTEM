import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://localhost:8000/", allow_none=True)

# -------------------------
# Login
# -------------------------
resp = proxy.login("admin", "adminpass")
if resp["status"] != "OK":
    print("Login failed!")
    exit()
user = resp["user"]

# Add Patients
print(proxy.add_patient(user, "P101", "Alice", 29, "Female"))
print(proxy.add_patient(user, "P102", "Bob", 34, "Male"))

# View Patient
print(proxy.get_patient(user, "P101"))

# Book Appointments
print(proxy.book_appointment(user, "P101", "Smith", "2025-08-20"))
print(proxy.book_appointment(user, "P102", "John", "2025-08-21"))

# View All Appointments
print(proxy.list_appointments(user))
