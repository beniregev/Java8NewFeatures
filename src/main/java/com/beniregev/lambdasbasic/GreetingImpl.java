package com.beniregev.lambdasbasic;

public class GreetingImpl implements Greeting {
    @Override
    public void perform() {
        System.out.println("Hello World!");
    }
}
