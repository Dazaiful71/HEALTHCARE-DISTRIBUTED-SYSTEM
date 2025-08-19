import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class DoctorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            DoctorService service = (DoctorService) registry.lookup("DoctorService");

            if(service.login("doctor1", "pass123")) {
                System.out.println("Login successful!");

                // Add Doctors
                System.out.println(service.addDoctor("D101", "Dr. Adams", "Cardiology"));
                System.out.println(service.addDoctor("D102", "Dr. Brown", "Dermatology"));

                // Prescriptions
                System.out.println(service.addPrescription("P101", "D101", "Take 1 tablet daily"));
                System.out.println(service.addPrescription("P102", "D102", "Apply cream twice a day"));
                System.out.println(service.getPrescriptions("P101"));

                // Billing
                Map<String, Double> services = new HashMap<>();
                services.put("Consultation", 50.0);
                services.put("Blood Test", 30.0);
                System.out.println(service.generateBill("P101", services));
                System.out.println(service.payBill("P101", 40.0));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
