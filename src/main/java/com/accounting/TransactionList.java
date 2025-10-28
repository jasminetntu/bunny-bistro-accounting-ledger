package com.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class TransactionList {
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Comparator that sorts by most recent, used in all display & report methods.
     */
    private final Comparator<Transaction> SORT_BY_MOST_RECENT =
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
        System.out.println("\nThe following transaction has been added:\n" + t.toDescriptiveString());
    }

    public void getTransactionsFromFile() {
        CSVFileManager csvFileManager = new CSVFileManager();
        transactions = csvFileManager.loadFromCsv();
    }

    public void saveTransactionsToFile() {
        CSVFileManager csvFileManager = new CSVFileManager();
        csvFileManager.saveToCsv(transactions);
    }

    // *** DISPLAY METHODS ***

    /**
     * Display all transactions, sorted by most recent.
     */
    public void displayAll() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        }
        else {
            transactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    /**
     * Display all deposits, sorted by most recent.
     */
    public void displayAllDeposits() {
        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = transactions.stream()
                //filters based on: positive amounts
                .filter(t -> t.getAmount() > 0)
                .toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No deposits found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    /**
     * Display all payments, sorted by most recent.
     */
    public void displayAllPayments() {
        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = transactions.stream()
                //filters based on: negative amounts
                .filter(t -> t.getAmount() < 0)
                .toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No payments found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    // *** REPORT METHODS ***

    /**
     * Displays a report of all transactions in the current month.
     */
    public void reportMonthToDate() {
        LocalDate first_day_of_month = LocalDate.now().withDayOfMonth(1);

        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = transactions.stream()
                //filters based on: curr month/01/curr year <= date
                .filter(t -> !t.getDate().isBefore(first_day_of_month))
                .toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No transactions during current month found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    /**
     * Displays a report of all transactions in the previous month.
     */
    public void reportPreviousMonth() {
        LocalDate first_day_of_curr_month = LocalDate.now().withDayOfMonth(1);
        LocalDate first_day_of_prev_month = LocalDate.now().withDayOfMonth(1).minusMonths(1);

        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = transactions.stream()
                //filters based on: prev month/01 <= date < curr month/01 (curr year)
                .filter(t -> t.getDate().isBefore(first_day_of_curr_month)
                        && !t.getDate().isBefore(first_day_of_prev_month))
                .toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No transactions from last month found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    /**
     * Displays a report of all transactions in the current year.
     */
    public void reportYearToDate() {
        LocalDate first_day_of_curr_year = LocalDate.of(LocalDate.now().getYear(), 1, 1);

        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = transactions.stream()
                //filters based on: 01/01/curr year <= date
                .filter(t -> !t.getDate().isBefore(first_day_of_curr_year))
                .toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No transactions from this year found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    /**
     * Displays a report of all transactions in the previous year.
     */
    public void reportPreviousYear() {
        LocalDate first_day_of_prev_year = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);
        LocalDate last_day_of_prev_year = LocalDate.of(LocalDate.now().getYear() - 1, 12, 31);

        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = transactions.stream()
                //filters based on: 01/01/prev year <= date <= 12/31/prev year
                .filter(t -> !t.getDate().isAfter(last_day_of_prev_year)
                        && !t.getDate().isBefore(first_day_of_prev_year))
                .toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No transactions from last year found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    /**
     * Displays a report of all transactions with a specified vendor.
     */
    public void searchByVendor(String vendorToSearch) {
        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = transactions.stream()
                //filters based on: matching vendor
                .filter(t -> t.getVendor().toLowerCase().contains(vendorToSearch.toLowerCase()))
                .toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No transactions from \"" + vendorToSearch + "\" found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }
    }

    /**
     * Displays a report of all transactions with specified details, including:
     * Start date, end date, description, vendor, minimum amount, and maximum amount.
     * May skip some details depending on if user left option blank.
     */
    public void customSearch(LocalDate startDate, LocalDate endDate, String description,
                             String vendor, String depositOrPayment, double minAmount, double maxAmount) {
        Stream<Transaction> tempStream = transactions.stream();

        // --> for each if statement, checks if user left "blank" by checking default values

        if (startDate != null) {
            //filter by: date >= startDate
            tempStream = tempStream.filter(t -> !t.getDate().isBefore(startDate));
        }

        if (endDate != null) {
            //filter by: date <= endDate
            tempStream = tempStream.filter(t -> !t.getDate().isAfter(endDate));
        }

        if (!description.isEmpty()) {
            //filter by: matching description (case-insensitive)
            tempStream = tempStream.filter(t -> t.getDescription().toLowerCase().contains(description.toLowerCase()));
        }

        if (!vendor.isEmpty()) {
            //filter by: matching vendor (case-insensitive)
            tempStream = tempStream.filter(t -> t.getVendor().toLowerCase().contains(vendor.toLowerCase()));
        }

        if (depositOrPayment.equalsIgnoreCase("deposit")) {
            //filter by: positive amounts
            tempStream = tempStream.filter(t -> t.getAmount() >= 0);
        }
        else if (depositOrPayment.equalsIgnoreCase("payment")) {
            //filter by: negative amounts
            tempStream = tempStream.filter(t -> t.getAmount() <= 0);
        } //don't filter if both dep and pay

        if (minAmount != 0) {
            //filter by: abs(amount) >= minAmount | abs() -> works for both dep and pay
            tempStream = tempStream.filter(t -> Math.abs(t.getAmount()) >= minAmount);
        }

        if (maxAmount != 0) {
            //filter by: abs(amount) <= maxAmount | abs() -> works for both dep and pay
            tempStream = tempStream.filter(t -> Math.abs(t.getAmount()) <= maxAmount);
        }

        //collect matching results into list to check if empty
        List<Transaction> matchingTransactions = tempStream.toList();

        if (matchingTransactions.isEmpty()) {
            System.out.println("No matching transactions found.");
        }
        else { //print results only if there are matching transactions
            matchingTransactions.stream()
                    .sorted(SORT_BY_MOST_RECENT)
                    .forEach(System.out::println);
        }

    }
}
