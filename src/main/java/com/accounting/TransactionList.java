package com.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionList {
    private final List<Transaction> transactions = new ArrayList<>();

    // *** GENERAL METHODS ***

    public void addTransaction(String description, String vendor, double amount) {
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        transactions.add(t);
        System.out.println("The following transaction has been added: " + t);
    }

    // *** FILE I/O METHODS ***

    public void loadFromCsv(String filePath) {
        transactions.clear(); //reset to load new

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currLine;
            String[] temp;

            while ((currLine = br.readLine()) != null) {
                temp = currLine.split("\\|", 5);

                //check if current line is valid transaction
                if (temp.length == 5) {
                    // date|time|description|vendor|amount
                    transactions.add(new Transaction(LocalDate.parse(temp[0]), LocalTime.parse(temp[1]),
                            temp[2], temp[3], Double.parseDouble(temp[4])));
                }
            }
        }
        catch (IOException ignore) {}; //occurs if file is empty -> ignore to leave transactions empty
    }

    public void saveToCsv(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                bw.write(t + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("ERROR: Something went wrong while saving to file.");
        }
    }

    // *** PRINTING METHODS ***


}
