/**
 * @author Jasmine Tu
 * Capstone 1 - Accounting Ledger App
 *
 * Entry point.
 */

package com.accounting;

public class Main {
    public static void main(String[] args) {
        // bold beginning & end
        String BOLD_START = "\u001B[1m";
        String BOLD_END = "\u001B[0m";

        //title
        System.out.printf("""
                •·················•·················•
                      %sBunny Bistro%s 🍵ིྀ༘₍ᐢ. .ᐢ₎₊˚⊹
                        > Sales & Purchases <""", BOLD_START, BOLD_END);

        //enter home screen
        HomeScreen.showHome();
    }
}
