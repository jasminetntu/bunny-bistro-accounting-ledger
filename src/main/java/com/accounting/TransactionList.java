package com.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

public class TransactionList {
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Comparator that sorts by most recent, used in all display & report methods.
     */
    private static final Comparator<Transaction> SORT_BY_MOST_RECENT =
            (t1, t2) -> -1 * t1.getDateAndTime().compareTo(t2.getDateAndTime());

    // *** GENERAL METHODS ***

    /**
     * Add a transaction to arraylist of all transactions.
     * @param description String
     * @param vendor String
     * @param amount double
     */
    public void addTransaction(String description, String vendor, double amount) {
        Transaction t = new Transaction(LocalDateTime.now(), description, vendor, amount);
        transactions.add(t);
        System.out.println("The following transaction has been added: " + t);
    }

    // *** FILE I/O METHODS ***

    /**
     * Loads transactions from CSV file to arraylist of transactions
     * @param filePath path to CSV file
     */
    public void loadFromCsv(String filePath) {
        transactions.clear(); //reset to load new

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currLine;
            String[] tranDetails;
            br.readLine(); //clear header line

            while ((currLine = br.readLine()) != null) {
                tranDetails = currLine.split("\\|", 5);

                //check if current line is valid transaction
                if (tranDetails.length == 5) {
                    // date|time|description|vendor|amount
                    // store date & time as LocalDateTime
                    transactions.add(new Transaction(LocalDateTime.of(LocalDate.parse(tranDetails[0]), LocalTime.parse(tranDetails[1])),
                            tranDetails[2], tranDetails[3], Double.parseDouble(tranDetails[4])));
                }
            }
        }
        catch (IOException ignore) {} //occurs if file is empty -> ignore to leave transactions empty
    }

    /**
     * Saves arraylist of transactions to CSV file in following format:
     * date|time|description|vendor|amount
     * @param filePath path to CSV file
     */
    public void saveToCsv(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("date|time|description|vendor|amount\n");

            for (Transaction t : transactions) {
                bw.write(t.toCsvString() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("ERROR: Something went wrong while saving to file.");
        }
    }

    // *** DISPLAY METHODS ***

    /**
     * Display all transactions, sorted by most recent.
     */
    public void displayAll() {
        System.out.println("\n•··· All Transactions ···•");

        transactions.stream()
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    /**
     * Display all deposits, sorted by most recent.
     */
    public void displayAllDeposits() {
        System.out.println("\n•··· All Deposits ···•");

        transactions.stream()
                .filter(t -> t.getAmount() > 0) //ensures only positive amounts
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    /**
     * Display all payments, sorted by most recent.
     */
    public void displayAllPayments() {
        System.out.println("\n•··· All Payments ···•");

        transactions.stream()
                .filter(t -> t.getAmount() < 0) //ensures only negative amounts
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    // *** REPORT METHODS ***

    /**
     * Displays a report of all transactions in the current month.
     */
    public void reportMonthToDate() {
        // System.out.println("TEST: reportMonthToDate() entered");
        System.out.println("\n•··· Month To Date Report ···•");

        LocalDate first_day_of_month = LocalDate.now().withDayOfMonth(1);
        transactions.stream()
                //filters based on: curr month/01/curr year <= date
                .filter(t -> !t.getDate().isBefore(first_day_of_month))
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    /**
     * Displays a report of all transactions in the previous month.
     */
    public void reportPreviousMonth() {
        //System.out.println("TEST: reportPreviousMonth() entered");
        System.out.println("\n•··· Previous Month Report ···•");

        LocalDate first_day_of_curr_month = LocalDate.now().withDayOfMonth(1);
        LocalDate first_day_of_prev_month = LocalDate.now().withDayOfMonth(1).minusMonths(1);

        transactions.stream()
                //filters based on: prev month/01 <= date < curr month/01 (curr year)
                .filter(t -> t.getDate().isBefore(first_day_of_curr_month)
                        && !t.getDate().isBefore(first_day_of_prev_month))
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    /**
     * Displays a report of all transactions in the current year.
     */
    public void reportYearToDate() {
        //System.out.println("TEST: reportYearToDate() entered");
        System.out.println("\n•··· Year To Date Report ···•");

        LocalDate first_day_of_curr_year = LocalDate.of(LocalDate.now().getYear(), 1, 1);

        transactions.stream()
                //filters based on: 01/01/curr year <= date
                .filter(t -> !t.getDate().isBefore(first_day_of_curr_year))
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    /**
     * Displays a report of all transactions in the previous year.
     */
    public void reportPreviousYear() {
        // System.out.println("TEST: reportPreviousYear() entered");
        System.out.println("\n•··· Previous Year Report ···•");

        LocalDate first_day_of_prev_year = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);
        LocalDate last_day_of_prev_year = LocalDate.of(LocalDate.now().getYear() - 1, 1, 31);

        transactions.stream()
                //filters based on: 01/01/prev year <= date <= 12/31/prev year
                .filter(t -> !t.getDate().isAfter(last_day_of_prev_year)
                        && !t.getDate().isBefore(first_day_of_prev_year))
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    /**
     * Displays a report of all transactions with a specified vendor.
     */
    public void searchByVendor(String vendorToSearch) {
        System.out.println("*** Searching by Vendor ***");

        transactions.stream()
                //filters based on matching vendor name
                .filter(t -> t.getVendor().equalsIgnoreCase(vendorToSearch))
                .sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

    /**
     * Displays a report of all transactions with specified details, including:
     * Start date, end date, description, vendor, minimum amount, and maximum amount.
     * May skip some details depending on if user left option blank.
     */
    public void customSearch(LocalDate startDate, LocalDate endDate, String description,
                             String vendor, double minAmount, double maxAmount) {
        System.out.println("\n*** Performing Custom Search ***");

        Stream<Transaction> tempStream = transactions.stream();

        // --> for each if statement, checks if user left "blank" by checking default values

        if (startDate != null) {
            //filter by: date >= startDate
            tempStream = tempStream.filter(t -> !t.getDate().isBefore(startDate));

            //test
        }

        if (endDate != null) {
            //filter by: date <= endDate
            tempStream = tempStream.filter(t -> !t.getDate().isAfter(endDate));
        }

        if (!description.isEmpty()) {
            //filter by: matching description (case-insensitive)
            tempStream = tempStream.filter(t -> t.getDescription().equalsIgnoreCase(description));
        }

        if (!vendor.isEmpty()) {
            //filter by: matching vendor (case-insensitive)
            tempStream = tempStream.filter(t -> t.getVendor().equalsIgnoreCase(vendor));
        }

        if (minAmount != 0) {
            //filter by: abs(amount) >= minAmount
            tempStream = tempStream.filter(t -> Math.abs(t.getAmount()) >= minAmount);
        }

        if (maxAmount != 0) {
            //filter by: abs(amount) <= maxAmount
            tempStream = tempStream.filter(t -> Math.abs(t.getAmount()) <= maxAmount);
        }

        //sort by most recent and print
        tempStream.sorted(SORT_BY_MOST_RECENT)
                .forEach(t -> System.out.println("> " + t));
    }

}
