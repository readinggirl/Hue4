/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung4.bsp1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Teresa
 */
public class Main {

    List<String> liste = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in, "Windows-1252");
        System.out.println("Geben Sie einen Teiler ein: ");
        int teiler = Integer.parseInt(scanner.nextLine());
        System.out.println("In wie viele Unterteile soll die Liste aufgeteilt werden?");
        int chunks = Integer.parseInt(scanner.nextLine());

        Main main = new Main();
        main.readFile();
        List<Integer> list = main.splitToIntList();

        ForkJoinPool custom = new ForkJoinPool(chunks);
        custom.submit(() -> list.parallelStream().filter(n -> n % teiler == 0).forEach(System.out::println));
    }

    public void readFile() {
        try (Stream<String> stream = Files.lines(Paths.get("numbers.csv"))) {
            liste = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* public List<Integer> toIntList() {
        List<Integer> iList = new ArrayList();
        for (int i = 0; i < liste.size(); i++) {
            if (isNumeric(liste.get(i))) {
                iList.add(Integer.parseInt(liste.get(i)));
            }
        }
        return iList;
    } */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Integer> splitToIntList() {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < liste.size(); j++) {
            String[] val = liste.get(j).split(":");
            for (int i = 0; i < val.length; i++) {
                if (isNumeric(val[i])) {
                    list.add(Integer.parseInt(val[i]));
                }
            }
        }
        return list;
    }
}
