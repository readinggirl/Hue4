/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung4.bsp3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author Teresa
 */
public class JavaStreamsTester {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = upperCase.toLowerCase();
        String digits = "1234567890";
        String chars = upperCase + lowerCase + digits;

        for (int j = 0; j < 10; j++) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int length = (int) (Math.random() * 100);
            Random random = new Random();
            while (i < length) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
                i++;
            }

            strings.add(sb.toString());
        }
        strings.add("");
        for (int i = 0; i < strings.size(); i++) {
            System.out.print(strings.get(i) + " ");
        }
        System.out.println("");
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add((int) (Math.random() * 100));
        }

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
//test if methods work
        System.out.println("numberofemptystrings");
        System.out.println(getCountEmptyString(strings));

        System.out.println("countoflength3");
        System.out.println(getCountLength3(strings));

        System.out.println("withoutemptystrings");
        for (int i = 0; i < deleteEmptyStrings(strings).size(); i++) {
            System.out.println(deleteEmptyStrings(strings).get(i));
        }

        System.out.println("mergedstring");
        System.out.println(getMergedString(strings, ";"));

        System.out.println("\nnumbers");
        System.out.println("Squares");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(getSquares(numbers).get(i));
        }
        System.out.println("");
        System.out.println("max");
        System.out.println(getMax(numbers));
        System.out.println("min");
        System.out.println(getMin(numbers));
        System.out.println("sum");
        System.out.println(getSum(numbers));
        System.out.println("average");
        System.out.println(getAverage(numbers));
    }

    private static int getCountEmptyString(List<String> strings) {
        return (int) strings.stream().filter(x -> x.isEmpty()).count();
    }

    private static int getCountLength3(List<String> strings) {
        return (int) strings.stream().filter(x -> x.length() == 3).count();
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
    }

    private static String getMergedString(List<String> strings, String separator) {
        return strings.stream().collect(Collectors.joining(separator));
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream().map(n -> n * n).collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream().max(Integer::compare).get();
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream().min(Integer::compare).get();
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> a + b);
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) numbers.stream().mapToDouble(x -> x).average().getAsDouble();
    }
}
