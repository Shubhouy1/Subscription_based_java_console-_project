import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class SubscriptionManager {
    private List<Subscription> subscriptions = new ArrayList<>();
    private final String FILE = "subscriptions.ser";

    public SubscriptionManager() {
        loadSubscriptions();
    }
    private static final String[] PLAN_NAMES = { "Basic", "Standard", "Premium" };
    private static final double[] PLAN_AMOUNTS = { 199.0, 399.0, 599.0 };

    public void addSubscription(Scanner scanner) {
        System.out.println("Choose a Subscription Plan:");
        for (int i = 0; i < PLAN_NAMES.length; i++) {
            System.out.println((i + 1) + ". " + PLAN_NAMES[i] + " - Rs. " + PLAN_AMOUNTS[i]);
        }
        System.out.print("Enter choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= PLAN_NAMES.length) {
            String name = PLAN_NAMES[choice - 1];
            double amount = PLAN_AMOUNTS[choice - 1];
            LocalDate startDate = LocalDate.now();
            subscriptions.add(new Subscription(name, amount, startDate));
            saveSubscriptions();
            System.out.println("Subscription to " + name + " plan added successfully.");
        } else {
            System.out.println("Invalid choice. Subscription not added.");
        }
    }

    public void viewSubscriptions() {
        if (subscriptions.isEmpty()) {
            System.out.println("No subscriptions found.");
        } else {
            for (int i = 0; i < subscriptions.size(); i++) {
                System.out.println((i + 1) + ". " + subscriptions.get(i));
            }
        }
    }

    public void editSubscription(Scanner scanner) {
        viewSubscriptions();
        System.out.print("Enter index to edit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < subscriptions.size()) {
            System.out.println("Choose new plan:");
            for (int i = 0; i < PLAN_NAMES.length; i++) {
                System.out.println((i + 1) + ". " + PLAN_NAMES[i] + " - Rs. " + PLAN_AMOUNTS[i]);
            }
            System.out.print("Enter choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= PLAN_NAMES.length) {
                subscriptions.get(index).setPlanName(PLAN_NAMES[choice - 1]);
                subscriptions.get(index).setAmount(PLAN_AMOUNTS[choice - 1]);
                saveSubscriptions();
                System.out.println("Subscription updated.");
            } else {
                System.out.println("Invalid plan choice.");
            }
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void deleteSubscription(Scanner scanner) {
        viewSubscriptions();
        System.out.print("Enter index to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < subscriptions.size()) {
            subscriptions.remove(index);
            saveSubscriptions();
            System.out.println("Subscription deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void searchSubscription(Scanner scanner) {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Subscription s : subscriptions) {
            if (s.getPlanName().toLowerCase().contains(keyword)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching subscription found.");
        }
    }

    public void viewPayments() {
        if (subscriptions.isEmpty()) {
            System.out.println("No subscriptions found.");
            return;
        }

        System.out.println("Upcoming Payments:");
        for (Subscription s : subscriptions) {
            LocalDate nextPayment = s.getStartDate().plusMonths(1);
            System.out.println("Plan: " + s.getPlanName() + " | Amount: " + s.getAmount() + " | Next Payment Date: " + nextPayment);
        }
    }

    private void saveSubscriptions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(subscriptions);
        } catch (IOException e) {
            System.out.println("Error saving subscriptions: " + e.getMessage());
        }
    }

    private void loadSubscriptions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            subscriptions = (List<Subscription>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            subscriptions = new ArrayList<>();
        }
    }
}
