import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;

public class DoctorServiceImpl extends UnicastRemoteObject implements DoctorService {
    private Map<String, String> doctors = new HashMap<>();
    private Map<String, List<String>> prescriptions = new HashMap<>();
    private Map<String, Double> bills = new HashMap<>();
    private Map<String, String> users = new HashMap<>();

    protected DoctorServiceImpl() throws RemoteException {
        super();
        users.put("doctor1", "pass123");
        users.put("admin", "admin123");
    }

    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public String addDoctor(String id, String name, String specialization) {
        doctors.put(id, name + " (" + specialization + ")");
        return "Doctor " + name + " added successfully!";
    }

    public String getDoctor(String id) {
        return doctors.getOrDefault(id, "Doctor not found!");
    }

    public String addPrescription(String patientId, String doctorId, String prescription) {
        if(!doctors.containsKey(doctorId)) return "Doctor not found!";
        prescriptions.putIfAbsent(patientId, new ArrayList<>());
        prescriptions.get(patientId).add("From " + doctors.get(doctorId) + ": " + prescription);
        return "Prescription added for patient " + patientId;
    }

    public List<String> getPrescriptions(String patientId) {
        return prescriptions.getOrDefault(patientId, new ArrayList<>());
    }

    public String generateBill(String patientId, Map<String, Double> services) {
        double total = 0.0;
        for(double price : services.values()) total += price;
        bills.put(patientId, total);
        return "Bill for patient " + patientId + ": $" + total;
    }

    public String payBill(String patientId, double amount) {
        if(!bills.containsKey(patientId)) return "No bill found!";
        double remaining = bills.get(patientId) - amount;
        if(remaining <= 0) {
            bills.remove(patientId);
            return "Bill fully paid for patient " + patientId;
        }
        bills.put(patientId, remaining);
        return "Partial payment accepted. Remaining: $" + remaining;
    }
}
