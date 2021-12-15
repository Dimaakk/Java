package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        String r = text.replaceAll("\\D+", "");
        if (r.length() == 11 && r.charAt(0) == 8) {
            r.replaceFirst("8", "7");
        }
        else if (r.length() == 10 && r.charAt(0) == '9')
            r = (new StringBuilder(r)).insert(0, "7").toString();

        String regex = "^((8|7))?\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(r);
        if (!matcher.matches()) {
            System.out.println("Неверный формат номера");
            return;
        }

        System.out.println("+7" + " (" + r.substring(1, 4) + ") " +
                    r.substring(4, 7) + "-" + r.substring(7, 9) +
                    "-" + r.substring(9, 11));

    }
}
