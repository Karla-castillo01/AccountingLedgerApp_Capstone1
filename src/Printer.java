import java.util.List;

public class Printer {

    public static void printLedgerMenu() {
        System.out.println("\n--- Ledger Menu ---");
        System.out.println("\"A\") All Entries");
        System.out.println("\"D\") Deposits Only");
        System.out.println("\"P\") Payments Only");
        System.out.println("\"R\") Reports");
        System.out.println("\"H\") Home");
        System.out.print("Select an option: ");
    }

    public static void printHomeScreen() {
        System.out.println("=================================");
        System.out.println("Welcome to your Account Ledger!");
        System.out.println("=================================");

        System.out.println("\"D\") Add Deposit");
        System.out.println("\"P\") Make Payment");
        System.out.println("\"L\") Ledger");
        System.out.println("\"X\") Exit");
        System.out.print("Please select an option: ");
    }

    public static void printReportsMenu() {
        System.out.println("\n--- Reports Menu ---");
        System.out.println("\"1\") Month To Date");
        System.out.println("\"2\") Previous Month");
        System.out.println("\"3\") Year To Date");
        System.out.println("\"4\") Previous Year");
        System.out.println("\"5\") Search by Vendor");
        System.out.println("\"0\") Back");
        System.out.print("Select a report option: ");
    }

    public static void printEntries(List<LedgerEntry> entries) {
        System.out.println("\nDate       | Time       | Description          | Vendor               | Amount");
        System.out.println("-----------|-----------|----------------------|----------------------|--------");

        for (LedgerEntry entry : entries) {
            System.out.println(entry);
        }

        System.out.println("-------------------------------------------------------------------------");
    }
}
