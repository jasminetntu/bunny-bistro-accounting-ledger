package com.accounting;

public class MenuOption {
    public enum Home {
        ADD_DEPOSIT("D"), MAKE_PAYMENT("P"), LEDGER("L"), EXIT("X");

        private final String letter;

        /**
         * Constructor for Home enum
         * @param letter associated letter
         */
        Home(String letter) {
            this.letter = letter;
        }

        /**
         * Gets letter associated with enum
         * @return letter (D, P, L, X)
         */
        public String getLetter() {
            return letter;
        }

        /**
         * Converts user's input string into corresponding Home enum (e.g., "D" -> ADD_DEPOSIT)
         *
         * @param inputLetter single-letter input, case-insensitive
         * @return matching Home enum
         * @throws IllegalArgumentException if input doesn't match any enums
         */
        public static Home fromInputLetter(String inputLetter) {
            inputLetter = inputLetter.trim().toUpperCase();

            // iterate through all enum values
            for (Home option : Home.values()) {
                // check if input letter matches associated letter
                if (option.letter.equals(inputLetter)) {
                    return option;
                }
            }

            // if no match is found, throw an exception
            throw new IllegalArgumentException("Invalid menu choice: " + inputLetter
                    + ". Please enter D, P, L, or X.");
        }
    }

    public enum Ledger {
        DISPLAY_ALL("A"), DISPLAY("D"), DISPLAY_PAYMENTS("P"),
        DISPLAY_REPORTS("R"), BACK_HOME("H");

        private final String letter;


        /**
         * Constructor for Ledger enum
         * @param letter associated letter
         */
        Ledger(String letter) {
            this.letter = letter;
        }

        /**
         * Gets letter associated with enum
         * @return letter (A, D, P, R, H)
         */
        public String getLetter() {
            return letter;
        }

        /**
         * Converts user's input string into corresponding Ledger enum (e.g., "A" -> DISPLAY_ALL)
         *
         * @param inputLetter single-letter input, case-insensitive
         * @return matching Ledger enum
         * @throws IllegalArgumentException if input doesn't match any enums
         */
        public static Ledger fromInputLetter(String inputLetter) {
            inputLetter = inputLetter.trim().toUpperCase();

            // iterate through all enum values
            for (Ledger option : Ledger.values()) {
                // check if input letter matches associated letter
                if (option.letter.equals(inputLetter)) {
                    return option;
                }
            }

            // if no match is found, throw an exception
            throw new IllegalArgumentException("Invalid menu choice: " + inputLetter
                    + ". Please enter A, D, P, R, or H.");
        }
    }

    public enum Report {
        MONTH_TO_DATE(1), PREVIOUS_MONTH(2), YEAR_TO_DATE(3),
        PREVIOUS_YEAR(4), SEARCH_BY_VENDOR(5), BACK_LEDGER(0);

        private final int num;

        /**
         * Constructor for Report enum
         * @param num associated integer
         */
        Report(int num) {
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
         * @return matching Report enum
         * @throws IllegalArgumentException if input doesn't match any enums
         */
        public static Report fromInputNum(int inputNum) {

            // iterate through all enum values
            for (Report option : Report.values()) {
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
}
