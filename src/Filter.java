import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Filter {

    public static List<LedgerEntry> filterEntriesByDate(List<LedgerEntry> ledger, String dateSpan) {
        List<LedgerEntry> filtered = new ArrayList<>();
        Date currentDate = new Date();
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        for (LedgerEntry entry : ledger) {
            Date entryDate = entry.getDate();
            Calendar entryCalendar = Calendar.getInstance();
            entryCalendar.setTime(entryDate);

            switch (dateSpan) {
                case "MONTH_TO_DATE":
                    if (entryCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                            entryCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) {
                        filtered.add(entry);
                    }
                    break;

                case "PREVIOUS_MONTH":
                    Calendar prevMonthCal = (Calendar) currentCalendar.clone();
                    prevMonthCal.add(Calendar.MONTH, -1);
                    if (entryCalendar.get(Calendar.YEAR) == prevMonthCal.get(Calendar.YEAR) &&
                            entryCalendar.get(Calendar.MONTH) == prevMonthCal.get(Calendar.MONTH)) {
                        filtered.add(entry);
                    }
                    break;

                case "YEAR_TO_DATE":
                    if (entryCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) {
                        filtered.add(entry);
                    }
                    break;

                case "PREVIOUS_YEAR":
                    if (entryCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) - 1) {
                        filtered.add(entry);
                    }
                    break;
            }
        }
        return filtered;
    }

    public static List<LedgerEntry> filterEntriesByVendor(List<LedgerEntry> ledger, String vendorName) {
        List<LedgerEntry> filtered = new ArrayList<>();
        for (LedgerEntry entry : ledger) {
            if (entry.getVendor().equalsIgnoreCase(vendorName)) {
                filtered.add(entry);
            }
        }
        return filtered;
    }

    public static List<LedgerEntry> filterEntriesByDeposit(List<LedgerEntry> ledger, boolean isDeposit) {
        List<LedgerEntry> filtered = new ArrayList<>();
        for (LedgerEntry entry : ledger) {
            if (isDeposit && entry.getAmount() > 0) {
                filtered.add(entry);
            } else if (!isDeposit && entry.getAmount() < 0) {
                filtered.add(entry);
            }
        }
        return filtered;
    }
}
