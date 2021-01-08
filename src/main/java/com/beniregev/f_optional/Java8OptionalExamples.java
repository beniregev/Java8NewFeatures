package com.beniregev.f_optional;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Using {@link Optional}<T> and {@link OptionalInt} classes,
 * demonstrating the usage of {@code .isPresent()}, {@code .orElse()}
 * and {@code .orElseThrow()} methods.
 * @author Binyamin Regev e-mail: beniregev@gmail.com
 * @since 1.8
 */
public class Java8OptionalExamples {
    private int[] numbers = {4, 90, 0, 1, 13, 4, 90, 16, 2, 0, 1};
    List<Integer> listOfIntegers = Arrays.asList(-9, 77, -18, 81, 0, 25, 4, 69);

    public void java8OptionalString() {
        System.out.println("java8OptionalString(): ");
        Optional<String> Company = Optional.of("Nice1");
        Optional<String> emptyCompany = Optional.empty();
        System.out.println("Company (nice1): " + Company.orElse("Missing company name"));
        System.out.println("Company (empty): " + emptyCompany.orElse("Missing company name"));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8FindMinAndMaxInArrayOfIntegers() {
        System.out.println("java8FindMinInListOfIntegers(): ");
        //Integer max = listOfIntegers.stream()
        //        .mapToInt(val -> val)
        //        .max()
        //        .orElseThrow(NoSuchElementException::new);
        OptionalInt max = IntStream.of(numbers).max();
        OptionalInt min = IntStream.of(numbers).min();
        OptionalInt optionalIntEmpty = OptionalInt.empty();

        System.out.println("\toptionalInt (min): " + min.orElse(Integer.MAX_VALUE));
        System.out.println("\toptionalInt (max): " + max.orElse(Integer.MIN_VALUE));
        System.out.println("\toptionalInt (empty): " + optionalIntEmpty.orElse(Integer.MIN_VALUE));
        System.out.println("\toptionalInt (empty): " + optionalIntEmpty.orElse(Integer.MAX_VALUE));
        System.out.println(optionalIntEmpty.orElseThrow(NoSuchElementException::new));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * Using stream.min() to get minimum element according
     * to provided Integer Comparator.
     * Here I'm using {@link Optional} methods .isPresent(), .orElse() and .orElseThrow()
     * to control and handle when what I am looking for is not found.
     */
    public void java8MinimumFromListOfIntegers() {
        Integer intMin = listOfIntegers.stream().min(Integer::compare).get();
        //Integer intMinEmpty = (new ArrayList<Integer>()).stream().min(Integer::compare).get();
        Optional<Integer> optionalIntegerMin = (new ArrayList<Integer>()).stream().min(Integer::compare);
        Optional<Integer> optionalIntMin = listOfIntegers.stream().min(Integer::compare);
        if (optionalIntegerMin.isPresent()) {
            System.out.println("optionalIntegerMin = " + optionalIntegerMin.orElseThrow(NoSuchElementException::new));
        } else {
            System.out.println("optionalIntegerMin = No Such Element Found");
        }
        System.out.println("intMin = " + intMin.intValue());

        //  region To see the difference -- Comment / Uncomment the lines of code in the region
        /*  Throwing an exception in the correct way to handle when an element is not found */
//        System.out.println("optionalIntegerMin = " + optionalIntegerMin.orElseThrow(NoSuchElementException::new));
//        System.out.println("OptionalIntMin = " + optionalIntMin.orElseThrow(NoSuchElementException::new));
//        System.out.println("OptionalIntMin = " + OptionalInt.empty().orElseThrow(NoSuchElementException::new));
        //  endregion

    }

    public static void main(String[] args) {
        Java8OptionalExamples example = new Java8OptionalExamples();

        example.java8OptionalString();
        example.java8MinimumFromListOfIntegers();
        example.java8FindMinAndMaxInArrayOfIntegers();
    }
}
