/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung4.bsp2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Teresa
 */
public class Beispiel2 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Scanner scan = new Scanner(System.in, "Windows-1252");
        int n = 0;
        List<Integer> list = new LinkedList<>();
        final int teiler = 100;

        while (n == 0) {
            System.out.println("Geben Sie bitte eine Zahl n ein:");
            try {
                n = Integer.parseInt(scan.nextLine()) + 1;
            } catch (NumberFormatException e) {
                System.out.println("Das ist keine Zahl");
            }

            for (int i = 0; i < n; i++) {
                list.add(i);
            }
        }

        final int teilbereiche = list.size() / teiler;
        ExecutorService exc = Executors.newCachedThreadPool();
        List<Integer> sublist;
        List<MyRunnable> tasks = new LinkedList<>();
        int i;
        for (i = 0; i < teilbereiche; i++) {
            sublist = list.subList(i * teiler, i * teiler + teiler);
            tasks.add(new MyRunnable(sublist));
        }
        int rest = list.size() - teiler * i;
        sublist = list.subList(teiler * i, list.size());
        tasks.add(new MyRunnable(sublist));

        List<Future<Integer>> res = exc.invokeAll(tasks);
        exc.shutdown();
        List<Integer> ints = new LinkedList<>();
        for (Future<Integer> re : res) {
            ints.add(re.get());
        }

        int result = ints.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(result);

    }

}
