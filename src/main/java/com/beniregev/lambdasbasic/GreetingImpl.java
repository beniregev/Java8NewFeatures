package com.beniregev.lambdasbasic;

public class GreetingImpl implements Greeting {
    @Override
    public void perform() {
        System.out.println("Greeter.greetUsingInterface() --> GreetingImpl.perform() -- Hello World!");
    }
}
