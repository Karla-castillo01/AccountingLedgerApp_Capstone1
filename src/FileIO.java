import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileIO {
    private static final String LEDGER_FILE = "transactions.csv";

    public static List<LedgerEntry> getLedger() {
        List<LedgerEntry> ledger = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new FileReader(LEDGER_FILE))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split("\\|");

                Date date = dateFormat.parse(parts[0] + " " + parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                LedgerEntry entry = new LedgerEntry(description, vendor, amount, date);
                ledger.add(entry);
            }
        } catch (Exception e) {
            System.err.println("Error reading the ledger file: " + e.getMessage());
        }
        return ledger;
    }

    public static void addCSVRow(List<String> row) {
        try {
            File ledger = new File(LEDGER_FILE);
            final boolean createHeaders = !ledger.exists();
            FileWriter fileWriter = new FileWriter(ledger, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            if (createHeaders) {
                List<String> headers = List.of("Date", "Time", "Description", "Vendor", "Amount");
                writeCSVRow(writer, headers);
            }
            writeCSVRow(writer, row);
            writer.close();
            fileWriter.close();
        } catch (Exception e) {
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

    public static void addToLedger(LedgerEntry entry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        List<String> row = List.of(
                dateFormat.format(entry.getDate()),
                timeFormat.format(entry.getDate()),
                entry.getDescription(),
                entry.getVendor(),
                String.valueOf(entry.getAmount())
        );
        FileIO.addCSVRow(row);
    }
}
