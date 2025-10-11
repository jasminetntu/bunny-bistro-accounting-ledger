package com.accounting;

import java.util.Scanner;

public class ReportScreen {
    public static void showReport(Scanner scnr) {
        boolean isRunning = true;
        ReportOption reportChoice = null;

        while (isRunning) {
            System.out.print("""
                    
                    •·················•·················•
                                   Reports
                    •·················•·················•
                    Run report from...
                        (1) Month To Date
                        (2) Previous Month
                        (3) Year To Date
                        (4) Previous Year
                        (5) Search by Vendor
                        (6) Custom Search
                    
                        (0) Back to Ledger
                    > Enter choice (0-6):\s""");

            try {
                reportChoice = ReportOption.fromInputNum(Integer.parseInt(scnr.nextLine()));

                //only proceeds to switch if input is valid
                switch (reportChoice) {
                    case MONTH_TO_DATE -> reportMonthToDate();
                    case PREVIOUS_MONTH -> reportPreviousMonth();
                    case YEAR_TO_DATE -> reportYearToDate();
                    case PREVIOUS_YEAR -> reportPreviousYear();
                    case SEARCH_BY_VENDOR -> searchByVendor();
                    case CUSTOM_SEARCH -> customSearch();
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

    private static void reportMonthToDate() {
        System.out.println("TEST: reportMonthToDate() entered");
    }

    private static void reportPreviousMonth() {
        System.out.println("TEST: reportPreviousMonth() entered");
    }

    private static void reportYearToDate() {
        System.out.println("TEST: reportYearToDate() entered");
    }

    private static void reportPreviousYear() {
        System.out.println("TEST: reportPreviousYear() entered");
    }

    private static void searchByVendor() {
        System.out.println("TEST: searchByVendor() entered");
    }

    private static void customSearch() {
        System.out.println("TEST: customSearch() entered");
    }

}
