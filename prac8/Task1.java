package com.company.makarov.pr8;
import java.util.Scanner;


public class Task1 {
    public static boolean isValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.UnicodeBlock.of(s.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC) &&
                    !(s.charAt(i) == '-')) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String text = "Иванов Иван Иванович";
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();

        String []ready_arr = text.split(" ");

        for (String ch: ready_arr)
        {
            if (!isValid(ch)) {
                System.out.println("Введенная строка не является ФИО");
                return;
            }
        }

        if (ready_arr.length != 2 && ready_arr.length != 3) {
            System.out.println("Введенная строка не является ФИО");
            return;
        }

        System.out.println("Фамилия: " + ready_arr[0]);
        System.out.println("Имя: " + ready_arr[1]);
        if (ready_arr.length == 3) {
            System.out.println("Отчество: " + ready_arr[2]);
        }
    }
}
