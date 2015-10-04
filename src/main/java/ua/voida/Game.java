package ua.voida;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by jenya on 20.09.15.
 */
public class Game {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please select a type game: 1 (a user games)  or 2 (a computer games).");
        System.out.print("Type game : ");
        int values = Integer.parseInt(reader.readLine());
        switch (values) {
            case 1:
                userGame(unknownDigit());
                break;
            case 2:
                computerGame();
        }

    }

    public static byte[] unknownDigit() {
        byte[] digit = new byte[4];
        Random random = new Random();
        int i = 1;
        digit[0] = (byte) random.nextInt(10);
        while (i < 4) {
            byte digitElement = (byte) random.nextInt(10);
            boolean digitValues = true;
            for (int j = 0; j < i; j++) {
                if (digit[j] == digitElement) {
                    digitValues = false;
                    break;
                }
            }
            if (digitValues) {
                digit[i] = digitElement;
                i++;
            }
        }
        return digit;
    }

    public static String digitString() {
        byte[] digitArray = unknownDigit();
        String digit = "";
        for (byte element : digitArray) {
            digit += element;
        }
        return digit;
    }

    public static boolean contains(byte[] array, int digit) {
        boolean values = false;
        for (byte i : array) {
            if (i == digit) {
                values = true;
                break;
            }
        }
        return values;
    }

    public static void outDigit(byte[] computerDigit) {
        for (byte element : computerDigit) {
            System.out.print(element);
        }
        System.out.println();
    }

    public static byte[] bullCow(byte[] unknownDigit, String digit) {
        byte[] count = new byte[2];
        byte bull = 0, cow = 0;
        for (int i = 0; i < unknownDigit.length; i++) {
            if (digit.charAt(i) - 48 == unknownDigit[i]) {
                bull++;
            } else if (contains(unknownDigit, digit.charAt(i) - 48)) {
                cow++;
            }
        }
        count[0] = bull;
        count[1] = cow;
        System.out.println("Bull : " + bull + "  cow : " + cow);
        return count;
    }

    public static void delete(byte[] bullCowArray, List<String> listDigit, String digit ) {
        int count = bullCowArray[0] + bullCowArray[1];
        switch (count) {
            case 4 : {

            }
            case 3 : {

            }
            case 2 : {

            }
            case 1 : {
                Iterator<String> i = listDigit.iterator();
                while (i.hasNext()) {
                    for (int j = 0; j < digit.length(); j++) {
                        String digitString = "" + digit.charAt(j);
                        String s = i.next();
                        if (!s.contains(digitString)) {
                            i.remove();
                        }
                    }
                }
            } break;
        }
    }

    public static void userGame(byte[] computerDigit) throws Exception {
        outDigit(computerDigit);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int bull, step = 0;
        do {
            String userDigit = null;
            boolean values = true;
            while (values) {
                userDigit = reader.readLine();
                if (userDigit.length() != 4) {
                    continue;
                }
                for (char c : userDigit.toCharArray()) {
                    if (userDigit.replaceAll(String.valueOf(c), "").length() < 3) {
                        values = true;
                        break;
                    } else {
                        values = false;
                    }
                }
            }
            byte[] count = bullCow(computerDigit, userDigit);
            bull = count[0];
            step++;
            System.out.println("  step : " + step);
        } while (bull < 4);
    }

    public static boolean isUniqueDigits(int number) {
        boolean[] existingDigits = new boolean[10];
        for (int i = 0; i < 4; i++) {
            int digit = number % 10;
            if (existingDigits[digit]) {
                return false;
            } else {
                existingDigits[digit] = true;
            }
            number /= 10;
        }
        return true;
    }

    public static List<String> find() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (isUniqueDigits(i)) {
                String s = Integer.toString(i);
                if (s.length() < 4) {
                    s = "0" + s;
                }
                res.add(s);
            }
        }
        return res;
    }
    public static void computerGame() {
        byte[] computerDigit = unknownDigit();
        outDigit(computerDigit);
        Random random = new Random();
        byte[] bull;
        int step = 0;
        String digit;
        List<String> list = new ArrayList<>(find());
        do {
                digit = list.get(random.nextInt(list.size()));
                bull = bullCow(computerDigit, digit);
                delete(bull, list, digit);
                step++;
                System.out.println("step : " + step);
            } while (bull[0] < 4);


    }
}
