/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung4.bsp1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Teresa
 */
public class NumbersRunnable implements Runnable {

    List<String> lis = new ArrayList<>();
    List<Integer> inList = new ArrayList<>();
    List<Integer> teilbareNr = new ArrayList<>();
    final int teiler;

    public NumbersRunnable(List<String> list, int teiler) {
        lis = list;
        this.teiler = teiler;
    }

    @Override
    public void run() {

        inList = toIntList(lis);
        Predicate<Integer> teilbar = i -> i % teiler == 0;

        for (int i = 0; i < inList.size(); i++) {
            if (teilbar.test(inList.get(i))) {
                teilbareNr.add(inList.get(i));
                System.out.println(inList.get(i));
            }
        }

    }

    public synchronized List<Integer> toIntList(List<String> liste) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            if (isNumber(liste.get(i)) == true) {
                intList.add(Integer.parseInt(liste.get(i)));
            }
        }
        return intList;
    }

    public synchronized boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }

    }

}
