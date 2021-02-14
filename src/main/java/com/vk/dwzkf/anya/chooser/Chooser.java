package com.vk.dwzkf.anya.chooser;

import com.vk.dwzkf.anya.chooser.util.Console;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chooser {
    private static char[] chars = "abcdefghj;'llmzxcknqw[peopwoiutywerp[zxc!@#$%*(&)(*!_@#(*)(!@%&!@*%".toCharArray();
    private static final Random random = new Random();
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final int DELAY_TIME = 25;

    public static void randomString(List<String> strings, int repeatTimes, int sleepTime, int startRow, String whatFinding) {
        int index = nextInt(strings.size() - 1, 0);
        String finalString = strings.get(index);
        int size = finalString.length() + whatFinding.length() + 10 + 40;
        char[] buf = new char[size];
        Arrays.fill(buf, ' ');
        for (int i = 0; i < repeatTimes; i++) {
            for (int j = 5; j < size - 5; j++) {
                buf[j] = chars[nextInt(chars.length - 1, 0)];
            }
            Console.moveCursor(startRow, 0);
            for (int j = 0; j < buf.length; j++) {
                Console.print(buf[j]);
                sleep(sleepTime);
            }
        }
        Console.moveCursor(startRow, 0);
        for (int j = 0; j < buf.length; j++) {
            Console.print('#');
            sleep(DELAY_TIME);
        }
        Console.moveCursor(startRow, 0);
        int startPos = finalString.length() + (whatFinding.length()) + 25;
        int endPos = startPos + finalString.length() - 1;
        int nextChar = 0;
        char[] string = finalString.toCharArray();
        Arrays.fill(buf, ' ');
        char[] ch2 = ("["+whatFinding+"] FOUND:").toCharArray();
        for (int i = 0; i < ch2.length; i++) {
            buf[i]=ch2[i];
        }
        for (int i = 0; i < buf.length; i++) {
            if (i >= startPos && i <= endPos) {
                Console.print(string[nextChar++]);
                sleep(DELAY_TIME);
            } else {
                Console.print('%');
                Console.moveCursor(startRow, i+1);
                sleep(DELAY_TIME);
                Console.print(buf[i]);
            }
        }
    }

    private static int nextInt(int maxPlus, int start) {
        if (maxPlus == 0) {
            return start;
        }
        return (Math.abs(random.nextInt()) % (maxPlus + 1)) + start;
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }
}
