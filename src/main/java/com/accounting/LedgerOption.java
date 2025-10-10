package com.accounting;

public enum LedgerOption {
    DISPLAY_ALL("A"), DISPLAY("D"), DISPLAY_PAYMENTS("P"),
    DISPLAY_REPORTS("R"), BACK_HOME("H");

    private final String letter;


    /**
     * Constructor for LedgerOption enum
     * @param letter associated letter
     */
    LedgerOption(String letter) {
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
     * @return matching LedgerOption enum
     * @throws IllegalArgumentException if input doesn't match any enums
     */
    public static LedgerOption fromInputLetter(String inputLetter) {
        inputLetter = inputLetter.trim().toUpperCase();

        // iterate through all enum values
        for (LedgerOption option : LedgerOption.values()) {
            // check if input letter matches associated letter
            if (option.letter.equals(inputLetter)) {
                return option;
            }
        }

        // if no match is found, throw an exception
        throw new IllegalArgumentException();
    }
}
