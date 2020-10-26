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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teresa
 */
public class Main {

    List<String> liste = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in, "Windows-1252");
        System.out.println("Geben Sie einen Teiler ein: ");
        int teiler = Integer.parseInt(scanner.nextLine());
        System.out.println("In wie viele Unterteile soll die Liste aufgeteilt werden?");
        int chunks = Integer.parseInt(scanner.nextLine());

        Main main = new Main();
        main.readFile();

        List<String> test;

        for (int i = 0; i < teiler / teiler; i++) {
            test = main.liste.subList((main.liste.size() / teiler) * i, teiler);

            Thread t = new Thread(new NumbersRunnable(test, teiler));
            t.start();
            t.join();
        }

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
}
