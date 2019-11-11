package com.beniregev.e_methodreference;

import java.util.Arrays;
import java.util.List;

public class Java8MethodReferenceExample {
    private List<String> names = Arrays.asList("Avraham", "Sarah", "Itshak", "Rivka");

    /**
     * In Java 8 we can pass a method as an argument to a method.
     *      <code>names.forEach(::println);</code>
     * But we need to tell that it's a method we are sending, we do that with 2 colons "::".
     *      <code>names.forEach(::println);</code>
     * And we need to tell where the method is located:
     *      <code>names.forEach(System.out::println);</code>
     * <h4>We Are done!</h4>>
     */
    public void java8MethodReferencePrintln() {
        names.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Java8MethodReferenceExample example = new Java8MethodReferenceExample();

        example.java8MethodReferencePrintln();
    }
}
