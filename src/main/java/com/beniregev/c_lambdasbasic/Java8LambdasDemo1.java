package com.beniregev.c_lambdasbasic;

interface MyFirstInterface {
    void show();
}

class MyFirstClassImpl implements MyFirstInterface {
    @Override
    public void show() {
        System.out.println("\tHello from class MyClassImpl");
    }
}

public class Java8LambdasDemo1 {
    /**
     * <p>
     * <div>We have:</div>
     * <div>1. Interface {@link MyFirstInterface} with a single method {@code show()}.</div>
     * <div>2. A class {@link MyFirstClassImpl} that implements {@link MyFirstInterface} and its single method {@code show()}.</div>
     * </p>
     * <p>
     * This method will declare the interface, create an instance of the implementation class and call the implementation
     * of the method {@code show()}.
     * </p>
     */
    public void beforeJava8CallingInterfaceMethodFromImplementation() {
        System.out.println("beforeJava8CallingInterfaceMethodFromImplementation(): ");
        MyFirstInterface obj = new MyFirstClassImpl();
        obj.show();
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * <p>Now we will take a step forward:</p>
     * <p>
     * We have an interface {@link MyFirstInterface} that has only one method and a
     * class {@link MyFirstClassImpl} whose whole purpose is to implement that one method
     * of the interface.
     * </p>
     * <p>
     * We can use Anonymous Inner Class instead of {@link MyFirstClassImpl} and save some code.
     * </p>
     */
    public void beforeJava8CallingInterfaceUsingAnonymousInnerClass() {
        System.out.println("beforeJava8CallingInterfaceUsingAnonymousInnerClass(): ");
        MyFirstInterface obj = new MyFirstInterface() {
            @Override
            public void show() {
                System.out.println("\tHello from Anonymous Inner Class 'new MyInterface()'");
            }
        };
        obj.show();
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * <p>Now we will take our code another step forward:</p>
     * <p>
     * We still have an Interface {@link MyFirstInterface} that has only one method and
     * we implement it using Anonymous Inner Class... and we have code that is repeating:
     * <code>MyInterface obj = new MyInterface() ...</code>
     * </p>
     * <p>
     * If we have {@code MyInterface obj} on the left side it's logical that we will have
     * {@code new MyInterface()} on the right side - this is called <i>Boilerplate Code</i>
     * we don't require this code (also the implementation of the one method) and this is
     * something we don't want to have.
     * </p>
     * <p>
     * There's a new feature in Java 8 that makes it possible to remove the
     * <i>Boilerplate Code</i> - <b><i>Lambda Expressions</i></b>
     * </p>
     */
    public void java8CallingInterfaceMethodUsingLambda() {
        System.out.println("java8CallingInterfaceMethodUsingLambda: ");
        //MyInterface obj = new MyInterface() {
        //    @Override
        //    public void show() {
        //        System.out.println("Hello from Anonymous Inner Class 'new MyInterface()'");
        //    }
        //};
        MyFirstInterface obj1 = () -> System.out.println("\tHello from single Lambda Expression #1\n");
        MyFirstInterface obj2 = () -> System.out.println("\tHello from single Lambda Expression #2\n");

        //  If there's only one argument then we don't need the round parenthesis
        MySecondInterface obj2nd = num -> {
            for (int i=0; i<num; i++) {
                System.out.println("\tHello from Multiple Lambda Expressions");
            }
        };

        obj1.show();
        obj2.show();
        obj2nd.show(5);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Java8LambdasDemo1 demo = new Java8LambdasDemo1();
        demo.beforeJava8CallingInterfaceMethodFromImplementation();
        demo.beforeJava8CallingInterfaceUsingAnonymousInnerClass();
        demo.java8CallingInterfaceMethodUsingLambda();
    }
}

interface MySecondInterface {
    void show(int i);
}
