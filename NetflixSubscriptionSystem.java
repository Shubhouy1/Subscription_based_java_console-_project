import java.util.Scanner;

public class NetflixSubscriptionSystem{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SubscriptionManager manager = new SubscriptionManager();
        UserManager userManager = new UserManager();
        
        System.out.println("=== Welcome to Netflix Subscription System ===");

        while (true) {
            System.out.println(" 1. Register\n 2. Login \n 3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                userManager.register(scanner);
            } else if (choice == 2) {
                if (userManager.login(scanner)) {
                    runSubscriptionMenu(scanner, manager);
                }
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
        scanner.close();
    }

    public static void runSubscriptionMenu(Scanner scanner, SubscriptionManager manager) {
        while (true) {
            System.out.println(" 1. Add Subscription \n 2. View Subscriptions\n 3. Edit Subscription");
            System.out.println(" 4. Delete Subscription \n 5. Search Subscriptions \n 6. View Payments\n 7. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: manager.addSubscription(scanner); break;
                case 2: manager.viewSubscriptions(); break;
                case 3: manager.editSubscription(scanner); break;
                case 4: manager.deleteSubscription(scanner); break;
                case 5: manager.searchSubscription(scanner); break;
                case 6: manager.viewPayments(); break;
                case 7: return;
                default: System.out.println("Invalid option!");
            }
        }
    }
}