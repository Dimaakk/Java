

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // практика 10
        // одинаковые цифры и буквы без 00х
        String[] letter = {"A","B","E","K","M","H","O","P","C","T","Y","X"};


//        try {
//            System.setOut(new PrintStream(new File("Output.txt")));
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        Arrays.sort(letter);
        HashSet<String> arrNumber = new HashSet<>();
        //одинаковые буквы
        for(String a123: letter) {
            for(int reg = 1; reg <= 199; reg++) {
                for(int j = 1; j <= 999; j++) {
//                    System.out.printf("%s%03d%s%s%d\n", a123,j,a123,a123,reg);
                    arrNumber.add(String.format("%s%03d%s%s%d",a123,j,a123,a123,reg));
                }
            }
        }
        //одинаковые цифры
        for(int i = 111; i <= 999; i += 111) {
            for (String a1 : letter) {
                for (String a2 : letter) {
                    for (String a3 : letter) {
                        for (int reg = 1; reg <= 199; reg++) {
//                            System.out.printf("%s%03d%s%s%d\n", a1, i, a2, a3, reg);
                            arrNumber.add(String.format("%s%03d%s%s%d", a1, i, a2, a3, reg));
                        }
                    }
                }
            }
        }
        ArrayList<String> arrNum = new ArrayList<>(arrNumber);
        System.out.println("Кол-во номеров: " + arrNum.size());

        //часть 2
        System.out.println("Введите номер для поиска:");
        Scanner in = new Scanner(System.in);
        String number = in.nextLine();
        //часть 2_1
        System.out.println("Поиск перебором: ");
        long first_time = System.nanoTime();
        if (arrNum.contains(number)) {
            System.out.print("номер найден");
        }
        else
            System.out.print("номер не найден");
        long rez_time = System.nanoTime() - first_time;
        System.out.println(", поиск занял " + rez_time + " нс или " + (double)rez_time / 1000000000 + " секунд");

        //часть 2_2
        System.out.println("Бинарный поиск: ");
        ArrayList<String> sorted_arr = new ArrayList<>(arrNum);
        Collections.sort(sorted_arr);

        first_time = System.nanoTime();
        if (Collections.binarySearch(sorted_arr, number) != -1) {
            System.out.print("номер найден");
        }
        else
            System.out.print("номер не найден");
        rez_time = System.nanoTime() - first_time;
        System.out.println(", поиск занял " + rez_time + " нс или " + (double)rez_time / 1000000000 + " секунд");



        //часть 2_3
        System.out.println("Поиск в HashSet: ");
        HashSet<String> hash_arr = new HashSet<>(arrNum);

        first_time = System.nanoTime();
        if (hash_arr.contains(number)) {
            System.out.print("номер найден");
        }
        else
            System.out.print("номер не найден");
        rez_time = System.nanoTime() - first_time;
        System.out.println(", поиск занял " + rez_time + " нс или " + (double)rez_time / 1000000000 + " секунд");

        //часть 2_4
        System.out.println("Поиск в TreeSet: ");
        TreeSet<String> tree = new TreeSet<>(arrNum);

        first_time = System.nanoTime();
        if (tree.contains(number)) {
            System.out.print("номер найден");
        }
        else
            System.out.print("номер не найден");
        rez_time = System.nanoTime() - first_time;
        System.out.println(", поиск занял " + rez_time + " нс или " + (double)rez_time / 1000000000 + " секунд");


    }
}
