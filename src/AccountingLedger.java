import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class AccountingLedger {
    private static final String LEDGER_FILE = "transactions.csv";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        application: while (true) {
            displayHomeScreen();
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    break application;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    private static void displayLedger() {

    }

    private static List<LedgerEntry> getLedger() {
        // read csv
        // convert each row into LedgerEntry
        return List.of();
    }

    private static void displayHomeScreen() {
        System.out.println("=================================");
        System.out.println("Welcome to your Account Ledger!");
        System.out.println("=================================");

        System.out.println("\"D\") Add Deposit");
        System.out.println("\"P\") Make Payment");
        System.out.println("\"L\") Ledger");
        System.out.println("\"X\") Exit");
        System.out.print("Please select an option: ");
        String response = scanner.nextLine();

    }

    private static void addDeposit() {
        System.out.println("Enter deposit information:");
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        LedgerEntry entry = new LedgerEntry(description, vendor, amount);
        addToLedger(entry);
    }

    private static void makePayment() {
        System.out.println("Please enter payment information:");
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine()); // Parse amount
        amount = -Math.abs(amount); // Ensure amount is negative
        LedgerEntry entry = new LedgerEntry(description, vendor, amount);
        addToLedger(entry);
    }

    private static void addToLedger(LedgerEntry entry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> row = List.of(
                dateFormat.format(entry.getDate()),
                entry.getDescription(),
                entry.getVendor(),
                String.valueOf(entry.getAmount())
        );
        addCSVRow(row);
    }

    private static void addCSVRow(List<String> row) {
        try {
            File ledger = new File(LEDGER_FILE);
            final boolean createHeaders = !ledger.exists();
            FileWriter fileWriter = new FileWriter(ledger,true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            if (createHeaders) {
                List<String> headers = List.of("DateTime", "Description", "Vendor", "Amount");
                writeCSVRow(writer, headers);
            }
            writeCSVRow(writer, row);
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    private static void writeCSVRow(BufferedWriter writer, List<String> row) throws IOException {
        for (int i = 0; i < row.size(); i++) {
            writer.write(row.get(i));
            if (i < row.size() - 1) {
                writer.write("|");
            }
        }
        writer.newLine();
    }
}