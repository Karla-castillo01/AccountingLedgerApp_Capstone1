import java.text.SimpleDateFormat;
import java.util.Date;

public class LedgerEntry {
    private final Date date;
    private final String description;
    private final String vendor;
    private final double amount;

    public LedgerEntry(String description, String vendor, double amount) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.date = new Date();
    }

    public LedgerEntry(String description, String vendor, double amount, Date date) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return String.format("%s | %s | %-20s | %-20s | %.2f",
                dateFormat.format(this.date),
                timeFormat.format(this.date),
                this.description,
                this.vendor,
                this.amount);
    }
}

