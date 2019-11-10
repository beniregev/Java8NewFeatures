package com.beniregev.foreach;

import java.util.function.Consumer;

public class MyConsumer implements Consumer<Integer> {
    public void accept(Integer intNumber) {
        System.out.println("Consumer implements value: " + intNumber);
    }
}
