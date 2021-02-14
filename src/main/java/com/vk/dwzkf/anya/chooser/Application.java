package com.vk.dwzkf.anya.chooser;

import com.vk.dwzkf.anya.chooser.util.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        for (int i = 0; i < 80; i++) {
            System.out.println();
        }
        Console.clear();
        String whatWeFinding = readStr("What we finding: ");
        int times = readInt("repeat times");
        int sleepTime = readInt("sleep time");
        int filmsSize = readInt("variants count");
        List<String> films = new ArrayList<>();
        for (int i = 0; i < filmsSize; i++) {
            String str = readString(whatWeFinding+" ",i);
            films.add(str);
        }
        Console.clear();
        System.out.println(String.format("FINDING %s...", whatWeFinding));
        for (int i = 0; i < 25; i++) {
            System.out.print(" ");
        }
        Chooser.randomString(films, times, sleepTime, 2, whatWeFinding);
    }

    public static int readInt(String forWhat) {
        System.out.print("Print "+forWhat+":");
        while (true) {
            try {
                return Integer.parseInt(readStr());
            } catch (Exception e) {
                System.out.println("Try again. Something wrong.");
            }
        }
    }
    public static String readString(String name, int pos) {
        System.out.print(name+(pos+1)+": ");
        return readStr();
    }

    public static String readStr() {
        int i = 10;
        while (i > 0) {
            try {
                return bufferedReader.readLine().trim();
            } catch (Exception e) {
                System.out.println("Exception. Try again.");
            }
            i--;
        }
        throw new IllegalArgumentException();
    }

    public static String readStr(String message) {
        System.out.print(message);
        int i = 10;
        while (i > 0) {
            try {
                return bufferedReader.readLine().trim();
            } catch (Exception e) {
                System.out.println("Exception. Try again.");
            }
            i--;
        }
        throw new IllegalArgumentException();
    }
}
