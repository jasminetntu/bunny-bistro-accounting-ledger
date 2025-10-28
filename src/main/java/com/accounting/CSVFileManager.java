package com.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {
    final String FILE_PATH = "src/main/resources/transactions.csv";

    /**
     * Loads transactions from CSV file to arraylist of transactions
     * @return an arraylist of transactions
     */
    public List<Transaction> loadFromCsv() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
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

        return transactions;
    }

    /**
     * Saves arraylist of transactions to CSV file in following format:
     * date|time|description|vendor|amount
     * @param transactions an arraylist of transactions
     */
    public void saveToCsv(List<Transaction> transactions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("date|time|description|vendor|amount\n");

            for (Transaction t : transactions) {
                bw.write(t.toCsvString() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("ERROR: Something went wrong while saving to file.");
        }
    }
}
