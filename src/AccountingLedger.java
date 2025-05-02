import java.util.List;
import java.util.Scanner;

public class AccountingLedger {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        application:
        while (true) {
            Printer.printHomeScreen();
            String option = scanner.nextLine().toUpperCase();
            switch (option) {
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
                    System.out.println("Invalid option");
            }
        }
        scanner.close();
    }

    private static void displayLedger() {
        List<LedgerEntry> ledger = FileIO.getLedger();
        if (ledger.isEmpty()) {
            System.out.println("Ledger is empty");
            return;
        }

        ledger.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        while (true) {
            Printer.printLedgerMenu();
            String option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "A":
                    Printer.printEntries(ledger);
                    break;
                case "D":
                    List<LedgerEntry> depositEntries = Filter.filterEntriesByDeposit(ledger, true);
                    if (depositEntries.isEmpty()) {
                        System.out.println("No deposits in Ledger");
                    } else {
                        Printer.printEntries(depositEntries);
                    }
                    break;
                case "P":
                    List<LedgerEntry> paymentEntries = Filter.filterEntriesByDeposit(ledger, false);
                    if (paymentEntries.isEmpty()) {
                        System.out.println("No payments in Ledger");
                    } else {
                        Printer.printEntries(paymentEntries);
                    }
                    break;
                case "R":
                    reportsMenu();
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void addDeposit() {
        System.out.println("Enter deposit information");
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        LedgerEntry entry = new LedgerEntry(description, vendor, amount);
        FileIO.addToLedger(entry);
    }

    private static void makePayment() {
        System.out.println("Please enter payment information");
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -Math.abs(amount);
        LedgerEntry entry = new LedgerEntry(description, vendor, amount);
        FileIO.addToLedger(entry);
    }

    private static void reportsMenu() {
        List<LedgerEntry> ledger = FileIO.getLedger();
        if (ledger.isEmpty()) {
            System.out.println("Ledger is empty");
            return;
        }
        ledger.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        reportsMenu:
        while (true) {
            Printer.printReportsMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    List<LedgerEntry> filteredByMonthToDate = Filter.filterEntriesByDate(ledger, "MONTH_TO_DATE");
                    if (filteredByMonthToDate.isEmpty()) {
                        System.out.println("No entries from previous month");
                    } else {
                        Printer.printEntries(filteredByMonthToDate);
                    }
                    break;
                case "2":
                    List<LedgerEntry> filteredByPreviousMonth = Filter.filterEntriesByDate(ledger, "PREVIOUS_MONTH");
                    if (filteredByPreviousMonth.isEmpty()) {
                        System.out.println("No entries from previous month");
                    } else {
                        Printer.printEntries(filteredByPreviousMonth);
                    }
                    break;
                case "3":
                    List<LedgerEntry> filteredByYearToDate = Filter.filterEntriesByDate(ledger, "YEAR_TO_DATE");
                    if (filteredByYearToDate.isEmpty()) {
                        System.out.println("No entries from year to date");
                    } else {
                        Printer.printEntries(filteredByYearToDate);
                    }
                    break;
                case "4":
                    List<LedgerEntry> filteredByPreviousYear = Filter.filterEntriesByDate(ledger, "PREVIOUS_YEAR");
                    if (filteredByPreviousYear.isEmpty()) {
                        System.out.println("No entries from previous year");
                    } else {
                        Printer.printEntries(filteredByPreviousYear);
                    }
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendorName = scanner.nextLine();
                    List<LedgerEntry> filteredByVendorEntries = Filter.filterEntriesByVendor(ledger, vendorName);
                    if (filteredByVendorEntries.isEmpty()) {
                        System.out.println("No vendors with name \"" + vendorName + "\"");
                    } else {
                        Printer.printEntries(filteredByVendorEntries);
                    }
                    break;
                case "0":
                    break reportsMenu;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}