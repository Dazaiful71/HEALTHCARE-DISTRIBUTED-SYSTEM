import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DoctorServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            DoctorServiceImpl service = new DoctorServiceImpl();
            registry.rebind("DoctorService", service);
            System.out.println("Doctor RMI Server running...");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
