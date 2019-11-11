package com.beniregev.c_lambdasbasic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Java8LambdasDemo2 {
    private List<String> names = Arrays.asList("Avraham", "Sarah", "Itshak", "Rivka");
    public void simpleIterationOnListWithoutLambda() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        names.forEach(con);
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
    public void simpleIterationOnListUsingLambda() {
        //  Call using Lambda
        names.forEach(s -> System.out.println(s));

        //  Method Reference: Call by Method
        names.forEach(System.out::println);

    }

    public static void main(String[] args) {
        Java8LambdasDemo2 demo = new Java8LambdasDemo2();

        demo.simpleIterationOnListWithoutLambda();
        demo.simpleIterationOnListUsingLambda();
    }
}
