package com.accounting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReportScreen {
    public static void showReport(Scanner scnr, TransactionList transactionList) {
        final String BOLD_START = "\u001B[1m";
        final String BOLD_END = "\u001B[0m";

        boolean isRunning = true;
        ReportOption reportChoice = null;

        while (isRunning) {
            System.out.printf("""
                    
                    •·················•·················•
                                   %sReports%s
                    •·················•·················•
                    Run report from...
                        (1) Month To Date
                        (2) Previous Month
                        (3) Year To Date
                        (4) Previous Year
                        (5) Search by Vendor
                        (6) Custom Search
                    
                        (0) Back to Ledger
                    > Enter choice (0-6):\s""", BOLD_START, BOLD_END);

            try {
                reportChoice = ReportOption.fromInputNum(Integer.parseInt(scnr.nextLine()));

                //only proceeds to switch if input is valid
                switch (reportChoice) {
                    case MONTH_TO_DATE -> transactionList.reportMonthToDate();
                    case PREVIOUS_MONTH -> transactionList.reportPreviousMonth();
                    case YEAR_TO_DATE -> transactionList.reportYearToDate();
                    case PREVIOUS_YEAR -> transactionList.reportPreviousYear();
                    case SEARCH_BY_VENDOR -> searchByVendor(scnr, transactionList);
                    case CUSTOM_SEARCH -> customSearch(scnr, transactionList);
                    case BACK_LEDGER -> {
                        System.out.println("\n•··· Returning to ledger... ···•");
                        isRunning = false;
                    }
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid menu choice. Please enter a number between 0-6.");
            }
        } //end while
    }

    private static void searchByVendor(Scanner scnr, TransactionList transactionList) {
        System.out.print("\nEnter vendor name: ");
        String vendorName = scnr.nextLine();

        transactionList.searchByVendor(vendorName);
    }

    private static void customSearch(Scanner scnr, TransactionList transactionList) {
        System.out.println("\n> For the following, please type your desired value OR enter to leave blank.");

        String input;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String description = "";
        String vendor = "";
        double minAmount = 0;
        double maxAmount = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        //get start date
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print("Start date (MM/dd/yyyy): ");
                input = scnr.nextLine().trim();

                if (!input.isEmpty()) {
                    startDate = LocalDate.parse(input, formatter);
                }
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Not a valid date format. Please follow MM/dd/yyyy exactly.");
            }
        }

        //get end date
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("End date (MM/dd/yyyy): ");
                input = scnr.nextLine().trim();

                if (!input.isEmpty()) {
                    endDate = LocalDate.parse(input, formatter);
                }
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Not a valid date format. Please follow MM/dd/yyyy exactly.");
            }
        }

        //get description
        System.out.print("Description: ");
        description = scnr.nextLine().trim();

        //get vendor
        System.out.print("Vendor: ");
        vendor = scnr.nextLine().trim();

        //get min amount
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Minimum amount: ");
                input = scnr.nextLine().trim();

                if (!input.isEmpty()) {
                    minAmount = Double.parseDouble(input);

                    if (minAmount < 0) {
                        System.out.println("Invalid amount. Please enter a nonnegative number.");
                    }
                }

                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Invalid amount. Please enter a nonnegative number.");
            }
        }

        //get max amount
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Maximum amount: ");
                input = scnr.nextLine().trim();

                if (!input.isEmpty()) {
                    maxAmount = Double.parseDouble(input);

                    if (maxAmount < 0) {
                        System.out.println("Invalid amount. Please enter a nonnegative number.");
                    }
                }
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Invalid amount. Please enter a nonnegative number.");
            }
        }

        transactionList.customSearch(startDate, endDate, description, vendor, minAmount, maxAmount);
    }

    // *** TEST METHODS ***

//    private static void reportMonthToDate() {
//        System.out.println("TEST: reportMonthToDate() entered");
//    }
//
//    private static void reportPreviousMonth() {
//        System.out.println("TEST: reportPreviousMonth() entered");
//    }
//
//    private static void reportYearToDate() {
//        System.out.println("TEST: reportYearToDate() entered");
//    }
//
//    private static void reportPreviousYear() {
//        System.out.println("TEST: reportPreviousYear() entered");
//    }
//


}
