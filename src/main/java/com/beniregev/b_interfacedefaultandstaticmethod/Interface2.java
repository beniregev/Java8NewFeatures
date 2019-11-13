package com.beniregev.b_interfacedefaultandstaticmethod;

@FunctionalInterface
public interface Interface2 {
    void method2();

    default void log2(String str){
        System.out.println("I2 logging::"+str);
    }
}
