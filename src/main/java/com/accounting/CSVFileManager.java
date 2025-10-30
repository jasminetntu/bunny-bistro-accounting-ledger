package com.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVFileManager {
    final String FILE_PATH = "src/main/resources/transactions.csv";

    /**
     * Loads transactions from CSV file to arraylist of transactions, returning the arraylist.
     * @return an arraylist of transactions
     */
    public List<Transaction> loadFromCsv() {
        List<Transaction> transactions = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT
                .builder()
                .setDelimiter('|')     // field separator
                .setQuote('"')         // quote character
                .setCommentMarker('#') // comment character
                .setIgnoreEmptyLines(true)
                .setIgnoreSurroundingSpaces(true)
                .setHeader()
                .setSkipHeaderRecord(true)
                .get();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            CSVParser parser = format.parse(br);

            if (!parser.iterator().hasNext()) {
                System.out.println("Error: The CSV file is empty or contains only a header.");
            } else {
                for (CSVRecord record : parser) {
                    try {
                        if (record.size() == 5) {
                            transactions.add(
                                    new Transaction(LocalDateTime.of(LocalDate.parse(record.get("date")), LocalTime.parse(record.get("time"))),
                                    record.get("description"), record.get("vendor"), Double.parseDouble(record.get("amount"))
                                    ));
                        }
                        else { //throw error if line is invalid
                            throw new IOException("Error: Line " + parser.getCurrentLineNumber() + " must have 5 fields.");
                        }
                    }
                    catch (IOException e) {  //catch if line has wrong number of field
                        System.out.println(e.getMessage());
                    }
                    catch (DateTimeParseException e) { //catch incorrect date/time format
                        System.out.println("Error: Date or time is not correctly formatted on line " + parser.getCurrentLineNumber());
                    }
                    catch (NumberFormatException e) { //catch invalid price
                        System.out.println("Error: Price is not numeric on line " + parser.getCurrentLineNumber());
                    }
                    catch (Exception e) {
                        System.out.println("Error: UNKNOWN");
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error: Unable to read CSV file.");
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
