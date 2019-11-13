package com.beniregev.c_lambdasbasic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * <ul>
 * Demonstrating Java 8 Lambda Expressions.
 * <li>Simple Iteration on {@link List} without Lambda.</li>
 * <li>java 8 Simple Iteration On {@link List}.</li>
 * </ul>
 * </p>
 * @author Binyamin Regev e-mail: beniregev@gmail.com
 * @since 1.8
 */
public class Java8LambdasDemo2 {
    private List<String> names = Arrays.asList("Avraham", "Sarah", "Itshak", "Rivka");
    public void simpleIterationOnListWithoutLambda() {
        System.out.println("simpleIterationOnListWithoutLambda(): ");
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        names.forEach(con);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * What did I do to convert the following code to a Lambda?
     *      <code>
     *          Consumer<String> con = new Consumer<String>() {
     *              @Override
     *              public void accept(String s) {
     *                  System.out.println(s);
     *              }
     *          };
     *          names.forEach(con);
     *      </code>
     * 1. {@link Consumer} is a functional interface, that means we can use Lambda Expression.
     * 2. We remove the code from "new Consumer<..." to "...public void accept(" - it's something Java compiler can assume from the code.
     * 3. And now we don't need the round and curly parenthesis ") {" and add an arrow "->".
     * 4. We keep "System.out.println(s), but remove the curly parenthesis after it because
     *    we have only a single statement.
     * 5. We put everything in one line and we'll get:
     *      <code>
     *          Consumer<String> con = String s -> System.out.println(s);
     *          names.forEach(con);
     *      </code>
     * 6. Using Lambdas we don't need to state the datatype of the argumentm so, "String" goes as well:
     *      <code>
     *          Consumer<String> con = s -> System.out.println(s);
     *          names.forEach(con);
     *      </code>
     * 7. We can cut  "s -> System.out.println(s)" and paste it in place of "con" and get:
     *      <code>names.forEach(s -> System.out.println(s));</code>
     *
     * <h4>And that is how we transfer from functional interface to Lambda Expression</h4>
     */
    public void java8SimpleIterationOnList() {
        System.out.println("java8SimpleIterationOnList(): ");
        //  Call using Lambda
        System.out.println("\tUsing Lambda Expression: ");
        names.forEach(s -> System.out.println(s));
        System.out.println("-------------------------------------------------");
        //  Method Reference: Call by Method
        System.out.println("\tUsing Method Reference -- Call by Method: ");
        names.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8LambdasExpressions() {
        System.out.println("java8LambdasExpressions(): ");
        List<String> phrases = Arrays.asList("do. or do not. there is no try",
                "That's what I do, I drink and I know things",
                "In the game of Thrones you win or you die",
                "How did you survived a knife to your heart? I didn't.",
                "Hold the door",
                "Live Long and prosper",
                "I have always been, and will always be, your friend",
                "The good of the many always outway the good of the few, or the one",
                "He is the one who was, you are the one who is, and he's the one who will be"
        );
        phrases.forEach(name -> {
            String[] substrings = name.split(" ");
            List<String> listOfSubstrings = Arrays.asList(substrings);
            String stringOfSubstrings = String.join("-", listOfSubstrings);
            System.out.println("\tSubstrings: \"" + stringOfSubstrings + "\"");
        });
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Java8LambdasDemo2 demo = new Java8LambdasDemo2();

        demo.simpleIterationOnListWithoutLambda();
        demo.java8SimpleIterationOnList();
        demo.java8LambdasExpressions();
    }
}
