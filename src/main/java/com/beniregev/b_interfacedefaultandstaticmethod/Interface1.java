package com.beniregev.b_interfacedefaultandstaticmethod;

/**
 * <p>
 * Class interface for {@link Java8InterfaceDefaultAndStaticMethodsExample} class
 * for demonstrating interface default and static methods.
 * </p>
 * @author Binyamin Regev e-mail: beniregev@gmail.com
 * @since 1.8
 */
@FunctionalInterface
public interface Interface1 {
    void method1(String str);

    default void log1(String str){
        System.out.println("I1 logging::" + str);
    }

    static void print(String str){
        System.out.println("Printing " + str);
    }

    /*  trying to override Object method gives compile time error as
        "A default method cannot override a method from java.lang.Object" */
    //default String toString(){
    //  return "i1";
    //}
}
