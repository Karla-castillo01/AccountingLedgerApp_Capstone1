import java.util.Scanner;

public class AccountingLedger {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayHomeScreen();
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "D":
                    System.out.println("Add Deposit selected");
                    break;
                case "P":
                    System.out.println("Make Payment selected");
                    break;
                case "L":
                    System.out.println("Ledger selected");
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    private static void displayHomeScreen() {
        // Code to display the home screen menu
    }

    private static void saveTransactions() {
    }
}