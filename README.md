# Account Ledger

## Project Description
Welcome to my Accounting Ledger project! This is a simple Java-based command-line application that helps users track deposits and payments while generating useful reports based on timeframes or vendors. Users can add transactions, view their full ledger, or filter entries by type or date.

# Project Features

Add deposits and payments\
View all ledger entries or filter by:\
Deposits only\
Payments only\
Date ranges (month-to-date, previous month, year-to-date, previous year)\
Vendor name\
Saves entries to `transaction.csv` and loads them each time the program runs\

### Home Screen : 
![Deposit_Payment_Exit](https://github.com/user-attachments/assets/c47b97d1-f9d3-46e1-8922-e949f84e47a5)

### Ledger Entries:
![Ledger_opt_a](https://github.com/user-attachments/assets/c5bdb7c5-defb-4a4f-84f5-effaf69b0760)

![deposit_payment_ledger](https://github.com/user-attachments/assets/18992cd8-f984-4a93-afa0-8171db27d3a3)

![reports_monthtodate](https://github.com/user-attachments/assets/aa4765d2-fb25-4677-a7a6-86c5856742b4)

![previous month, YTD, previous year](https://github.com/user-attachments/assets/06dbac59-a76b-45b7-a4d4-e934443e5a16)

![search by vendor_ back_ home](https://github.com/user-attachments/assets/13df0826-2c44-446d-b65e-7e6635397ac6)

---
## Interesting Code Snippet
One interesting feature of this app is the ability to filter ledger entries by different time periods. The filtering logic uses `Calendar` to compare the dates and filter out the relevant entries based on the timeframes.

Here's a snippet from the`Filter.java` class where I filter for previous month entries:
```
case "PREVIOUS_MONTH":
                    Calendar prevMonthCal = (Calendar) currentCalendar.clone();
                    prevMonthCal.add(Calendar.MONTH, -1);
                    if (entryCalendar.get(Calendar.YEAR) == prevMonthCal.get(Calendar.YEAR) &&
                            entryCalendar.get(Calendar.MONTH) == prevMonthCal.get(Calendar.MONTH)) {
                        filtered.add(entry);
                    }
                    break;
```



