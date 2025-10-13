package com.accounting;

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
                        (6) Custom Search --> DO LAST (OPTIONAL)
                    
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
                    case SEARCH_BY_VENDOR -> transactionList.searchByVendor();
                    case CUSTOM_SEARCH -> transactionList.customSearch();
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
//    private static void searchByVendor() {
//        System.out.println("TEST: searchByVendor() entered");
//    }
//
//    private static void customSearch() {
//        System.out.println("TEST: customSearch() entered");
//    }
//
}
