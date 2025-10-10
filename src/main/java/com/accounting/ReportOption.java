package com.accounting;

public enum ReportOption {
    MONTH_TO_DATE(1), PREVIOUS_MONTH(2), YEAR_TO_DATE(3),
    PREVIOUS_YEAR(4), SEARCH_BY_VENDOR(5), BACK_LEDGER(0);

    private final int num;

    /**
     * Constructor for ReportOption enum
     * @param num associated integer
     */
    ReportOption(int num) {
        this.num = num;
    }

    /**
     * Gets integer associated with enum
     * @return integer (0-5)
     */
    public int getNum() {
        return num;
    }

    /**
     * Converts user's input string into corresponding Ledger enum (e.g., "A" -> DISPLAY_ALL)
     *
     * @param inputNum integer input
     * @return matching ReportOption enum
     * @throws IllegalArgumentException if input doesn't match any enums
     */
    public static ReportOption fromInputNum(int inputNum) {

        // iterate through all enum values
        for (ReportOption option : ReportOption.values()) {
            // check if input number matches associated letter
            if (option.num == inputNum) {
                return option;
            }
        }

        // if no match is found, throw an exception
        throw new IllegalArgumentException("Invalid menu choice: " + inputNum
                + ". Please enter A, D, P, R, or H.");
    }
}
