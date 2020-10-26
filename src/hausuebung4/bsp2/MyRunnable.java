/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung4.bsp2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author Teresa
 */
public class MyRunnable implements Callable<Integer> {

    private List<Integer> list = new ArrayList<>();

    MyRunnable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() {
        return list.stream()
                .reduce(0, (a, b) -> a + b);

    }

}
