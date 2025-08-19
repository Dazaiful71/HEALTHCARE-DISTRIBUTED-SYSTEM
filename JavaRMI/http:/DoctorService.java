import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface DoctorService extends Remote {
    boolean login(String username, String password) throws RemoteException;
    String addDoctor(String id, String name, String specialization) throws RemoteException;
    String getDoctor(String id) throws RemoteException;
    String addPrescription(String patientId, String doctorId, String prescription) throws RemoteException;
    List<String> getPrescriptions(String patientId) throws RemoteException;
    String generateBill(String patientId, Map<String, Double> services) throws RemoteException;
    String payBill(String patientId, double amount) throws RemoteException;
}
