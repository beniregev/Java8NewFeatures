package com.beniregev.lambdasbasic;

/**
 * Before Java8 when we wanted to write a simple program to print "Hello World"
 * then we needed to write something like the following class {@code Greeter}.
 * <br />
 * @author Binyamin Regev
 */
public class Greeter {
    public void greet() {
        System.out.println("Greeter.greet() -- Hello World!");
    }

    public void greetUsingInterface(Greeting greeting) {
        greeting.perform();
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.greet();

        GreetingImpl greeting = new GreetingImpl();
        greeter.greetUsingInterface(greeting);
    }
}
