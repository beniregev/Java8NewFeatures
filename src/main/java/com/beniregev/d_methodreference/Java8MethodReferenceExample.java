package com.beniregev.d_methodreference;

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
        System.out.println("java8MethodReferencePrintln():");
        names.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void beforeJava8MethodReferenceWithMyOwnImplementation() {
        System.out.println("beforeJava8CallingMethodReferenceWithMyOwnImplementation(): ");

        String str = "Nice Company";
        MyPrinter mp = new MyPrinter();
        mp.print(str);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8NoMethodReferenceAnonymousInnerClass() {
        System.out.println("java8NoMethodReferenceAnonymousInnerClass(): ");

        MyPrinter mp = new MyPrinter();
        mp.print("Nice", new Parser() {
            @Override
            public String parse(String str) {
                return StringParser.staticConvert(str);
            }
        });
        mp.print("Nice Company", new Parser() {
            @Override
            public String parse(String str) {
                return StringParser.staticConvert(str);
            }
        });
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8MethodReferenceWithOwnImplementation() {
        System.out.println("java8MethodReferenceWithOwnImplementation(): ");

        MyPrinter mp = new MyPrinter();
        //  Lambda
        System.out.println("\tCalling a static method using Lambda");
        mp.print("Nice", (str -> StringParser.staticConvert(str)));
        mp.print("Nice Company", (str -> StringParser.staticConvert(str)));

        //  Method Reference - calling a static method
        System.out.println("\tMethod Reference - calling a static method");
        mp.print("Nice", (StringParser::staticConvert));
        mp.print("Nice Company", (StringParser::staticConvert));

        //  Method Reference - calling a non-static method
        System.out.println("\tMethod Reference - calling a non-static method");
        StringParser sp = new StringParser();
        mp.print("Nice", (sp::nonStaticConvert));
        mp.print("Nice Company", (sp::nonStaticConvert));


        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Java8MethodReferenceExample example = new Java8MethodReferenceExample();

        example.java8MethodReferencePrintln();
        example.beforeJava8MethodReferenceWithMyOwnImplementation();
        example.java8NoMethodReferenceAnonymousInnerClass();
        example.java8MethodReferenceWithOwnImplementation();
    }
}

class MyPrinter {
    public void print(String str) {
        System.out.println(str);
    }
    public void print(String str, Parser p) {
        str = p.parse(str);
        System.out.println(str);
    }
}

interface Parser {
    String parse(String str);
}

class StringParser {
    public static String staticConvert(String s) {
        if (s.length() <= 4)
            s = s.toUpperCase();
        else
            s = s.toLowerCase();
        return s;
    }

    public String nonStaticConvert(String s) {
        if (s.length() <= 4)
            s = s.toUpperCase();
        else
            s = s.toLowerCase();
        return s;
    }
}