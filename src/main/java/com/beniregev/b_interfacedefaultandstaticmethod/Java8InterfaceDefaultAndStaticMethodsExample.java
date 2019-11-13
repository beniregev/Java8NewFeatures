package com.beniregev.b_interfacedefaultandstaticmethod;

/**
 * <p>
 * If you read <i>forEach</i> method details carefully, you will notice that it’s defined in
 * <i>Iterable</i> interface but we know that interfaces can’t have method body. From Java 8,
 * interfaces are enhanced to have method with implementation. We can use {@code default} and
 * {@code static} keyword to create interfaces with method implementation. <i>forEach</i> method
 * implementation in Iterable interface is:
 * <code>
 *     default void forEach(Consumer<? super T> action) {
 *     Objects.requireNonNull(action);
 *     for (T t : this) {
 *         action.accept(t);
 *     }
 * </code>
 * </p>
 * <p>
 * We know that Java doesn’t provide multiple inheritance in Classes because it leads
 * to Diamond Problem. So how it will be handled with interfaces now, since interfaces
 * are now similar to abstract classes. The solution is that compiler will throw
 * exception in this scenario and we will have to provide implementation logic in the
 * class implementing the interfaces.
 * </p>
 * <p>
 * Notice that both the interfaces have a common method log() with implementation logic.
 * </p>
 * <p>
 * As you can see that Interface1 has static method implementation that is used in
 * {@code MyClass.log()} method implementation. Java 8 uses default and static methods heavily
 * in Collection API and default methods are added so that our code remains backward compatible.
 * </p>
 * <p>
 * If any class in the hierarchy has a method with same signature, then default methods become
 * irrelevant. Since any class implementing an interface already has Object as superclass, if
 * we have equals(), hashCode() default methods in interface, it will become irrelevant. That's
 * why for better clarity, interfaces are not allowed to have {@code Object} class default
 * methods.
 * </p>
 * <p>
 * For complete details of interface changes in Java 8, please read <i>Java 8 interface changes</i>.
 * </p>
 * @author Binyamin Regev email: beniregev@gmail.com
 * @see com.beniregev.b_interfacedefaultandstaticmethod.Interface1
 * @see com.beniregev.b_interfacedefaultandstaticmethod.Interface2
 */
public class Java8InterfaceDefaultAndStaticMethodsExample implements Interface1, Interface2 {
    @Override
    public void method2() {
    }

    @Override
    public void method1(String str) {
    }

    public static void main(String[] args) {
        Java8InterfaceDefaultAndStaticMethodsExample example = new Java8InterfaceDefaultAndStaticMethodsExample();
        example.log1("Calling Interface1 default method log1(String)");
        example.log2("Calling Interface2 default method log1(String)");
        Interface1.print("Calling Interface1 static method print(String)");
    }
}
