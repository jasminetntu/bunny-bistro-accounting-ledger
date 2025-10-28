package com.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {
    final String FILE_PATH = "src/main/resources/transactions.csv";

    /**
     * Loads transactions from CSV file to arraylist of transactions, returning the arraylist.
     * @return an arraylist of transactions
     */
    public List<Transaction> loadFromCsv() {
        List<Transaction> transactions = new ArrayList<>();
        int lineCount = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String currLine;
            String[] tranDetails;
            String fileIsNotEmpty = br.readLine(); //clear header line and check if empty

            if (fileIsNotEmpty == null) { //throw error if file is empty
                throw new IOException("Error: File is empty. No data to be loaded.");
            }

            while ((currLine = br.readLine()) != null) {
                lineCount++;
                tranDetails = currLine.split("\\|", 5);

                try {
                    //check if current line is valid transaction
                    if (tranDetails.length == 5) {
                        // date|time|description|vendor|amount
                        // store date & time as LocalDateTime

                        transactions.add(new Transaction(LocalDateTime.of(LocalDate.parse(tranDetails[0]), LocalTime.parse(tranDetails[1])),
                                tranDetails[2], tranDetails[3], Double.parseDouble(tranDetails[4])));
                    } else { //throw error is line is invalid
                        throw new IOException("Error: Line " + lineCount + " must have 5 fields.");
                    }
                }
                catch (IOException e) {  //catch if line has wrong number of field
                    System.out.println(e.getMessage());
                }
                catch (DateTimeParseException e) { //catch incorrect date/time format
                    System.out.println("Error: Date or time is not correctly formatted on line " + lineCount);
                }
                catch (NumberFormatException e) { //catch invalid price
                    System.out.println("Error: Price is not numeric on line " + lineCount);
                }
            }
        }
        catch (IOException e) {  //catch empty file error
            System.out.println(e.getMessage());
        }

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
