package com.accounting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDateTime dateAndTime;
    private String description;
    private String vendor;
    private double amount;

    // *** CONSTRUCTORS ***

    public Transaction(LocalDateTime dateAndTime, String description, String vendor, double amount) {
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // *** GETTERS ***


    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public LocalDate getDate() {
        return dateAndTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateAndTime.toLocalTime();
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // *** SETTERS ***

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // *** OTHER ***

    /**
     * Returns a string in the following format:
     * {MMM dd, yyyy} | {hh:mm:ss a}
     *      Description: {description}
     *      Vendor: {vendor}
     *      Amount: +/-${amount}
     *
     * @return an expanded string of the transaction details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy | hh:mm:ss a");

        //append in 12-hour time for user readability
        sb.append(formatter.format(dateAndTime))
                .append("\n\tDescription: ").append(description)
                .append("\n\tVendor: ").append(vendor);

        //append amount based on deposit or payment
        if (amount < 0) { //payment
            sb.append(String.format("\n\tAmount: -$%.2f", amount * -1));
        }
        else { //deposit
            sb.append(String.format("\n\tAmount: +$%.2f", amount));
        }
        return sb.toString();
    }

    /**
     * Returns a string in the following format for CSV file:
     * date|time|description|vendor|amount
     *
     * @return a string of the transaction details
     */
    public String toCsvString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        //append in 24-hour time for clarity when reading file
        sb.append(formatter.format(dateAndTime)).append("|").append(description).append("|")
                .append(vendor).append(String.format("|%.2f", amount));

        return sb.toString();
    }
}
