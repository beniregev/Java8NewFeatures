package com.beniregev.b_interfacedefaultandstaticmethod;

/**
 * <p>
 * Class interface for {@link Java8InterfaceDefaultAndStaticMethodsExample} class
 * for demonstrating interface default method.
 * </p>
 * @author Binyamin Regev e-mail: beniregev@gmail.com
 * @since 1.8
 */
@FunctionalInterface
public interface Interface2 {
    void method2();

    default void log2(String str){
        System.out.println("I2 logging::"+str);
    }
}
