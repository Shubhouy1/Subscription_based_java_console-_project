import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
    private HashMap<String, String> users = new HashMap<>();
    private final String FILE = "users.ser";

    public UserManager() {
        loadUsers();
    }

    public void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("User already exists!");
        } else {
            users.put(username, password);
            saveUsers();
            System.out.println("User registered!");
        }
    }

    public boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Login failed!");
        return false;
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            users = (HashMap<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new HashMap<>();
        }
    }
}