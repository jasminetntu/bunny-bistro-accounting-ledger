package com.accounting;

public enum HomeOption {
    ADD_DEPOSIT("D"), MAKE_PAYMENT("P"), LEDGER("L"), EXIT("X");

    private final String letter;

    /**
     * Constructor for Home enum
     * @param letter associated letter
     */
    HomeOption(String letter) {
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
    public static HomeOption fromInputLetter(String inputLetter) {
        inputLetter = inputLetter.trim().toUpperCase();

        // iterate through all enum values
        for (HomeOption option : HomeOption.values()) {
            // check if input letter matches associated letter
            if (option.letter.equals(inputLetter)) {
                return option;
            }
        }

        // if no match is found, return null
        throw new IllegalArgumentException();
    }
}
