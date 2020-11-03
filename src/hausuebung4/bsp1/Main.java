/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung4.bsp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        List<Integer> list = main.removeFalse();

        ForkJoinPool custom = new ForkJoinPool(chunks);
        custom.submit(() -> list.parallelStream().filter(n -> (n % teiler) == 0)).get().forEach(System.out::println);
    }

    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("numbers.csv")));
            String line = br.readLine();
            while (line != null) {
                liste.addAll(Arrays.asList(line.split(":")));
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Integer> removeFalse() {
        List<Integer> iList = new ArrayList();
        for (int i = 0; i < liste.size(); i++) {
            if (isNumeric(liste.get(i))) {
                iList.add(Integer.parseInt(liste.get(i)));
            }
        }
        return iList;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
