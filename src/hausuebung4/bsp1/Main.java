/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung4.bsp1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Teresa
 */
public class Main {

    List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "Windows-1252");
        System.out.println("Geben Sie einen Teiler ein: ");
        int teiler = Integer.parseInt(scanner.nextLine());
        System.out.println("In wie viele Unterteile soll die Liste aufgeteilt werden?");
        int chunks = Integer.parseInt(scanner.nextLine());

    }

    public void readFile() {
        try {
            list = Files.lines(new File("numbers.csv").toPath())
                    .skip(1)
                    .map(s -> s.split(":"))
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public

}
