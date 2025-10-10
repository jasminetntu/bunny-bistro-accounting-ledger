package com.accounting;

public class Main {
    public static void main(String[] args) {
        // bold beginning & end
        String ANSI_BOLD = "\u001B[1m";
        String ANSI_RESET = "\u001B[0m";

        //title
        System.out.println(ANSI_BOLD + "•··· Bunny Bistro 🍵ིྀ༘" + ANSI_RESET + "₍ᐢ. .ᐢ₎₊˚⊹");
        System.out.println("•··· Sales & Purchases");

        //enter home screen
        HomeScreen.showHome();
    }
}
