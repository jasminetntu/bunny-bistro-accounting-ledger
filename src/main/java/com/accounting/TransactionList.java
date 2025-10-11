package com.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class TransactionList {
    private final List<Transaction> transactions = new ArrayList<>();

    // *** GENERAL METHODS ***

    public void addTransaction(String description, String vendor, double amount) {
        Transaction t = new Transaction(LocalDateTime.now(), description, vendor, amount);
        transactions.add(t);
        System.out.println("The following transaction has been added: " + t);
    }

    // *** FILE I/O METHODS ***

    public void loadFromCsv(String filePath) {
        transactions.clear(); //reset to load new

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currLine;
            String[] tranDetails;

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
        catch (IOException ignore) {}; //occurs if file is empty -> ignore to leave transactions empty
    }

    public void saveToCsv(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                bw.write(t.toCsvString() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("ERROR: Something went wrong while saving to file.");
        }
    }

    // *** PRINTING METHODS ***

    public void displayAll() {
        System.out.println("•··· All Transactions (Most to Least Recent) ···•");

        transactions.stream()
                .sorted(new Comparator<Transaction>() {
                    //sort based on most recent
                    @Override
                    public int compare(Transaction t1, Transaction t2) {
                        return -1 * t1.getDateAndTime().compareTo(t2.getDateAndTime());
                    }
                })
                .forEach(t -> System.out.println("> " + t));
    }

}
