package com.accounting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // *** CONSTRUCTORS ***

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // *** GETTERS ***

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
     * Returns a string in the following format: date|time|description|vendor|amount
     * @return a string of the transaction details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        sb.append(formatter.format(date)).append("|");

        formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        sb.append(formatter.format(time)).append("|").append(description).append("|")
                .append(vendor).append(String.format("|%.2f", amount));

        return sb.toString();
    }
}
