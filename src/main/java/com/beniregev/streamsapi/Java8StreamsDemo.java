package com.beniregev.streamsapi;

import com.beniregev.streamsapi.model.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * There are 3 distinct part of using a Stream:
 * 1) Create
 * 2) Process
 * 3) Consume
 */
public class Java8StreamsDemo {
    //  region Class properties
    private int[] numbers = {4, 90, 0, 1, 13, 4, 90, 16, 2, 0, 1};
    private List<Integer> listOfIntegers = Arrays.asList(new Integer[] { 2004, 90, 91, 0, 1, 2020, 95, 81, 2014, 13, 4, 77, 90, 2001, 16, 2, 2005, 0, 1 });
    private int steps = (listOfIntegers.size() * 10) + (listOfIntegers.size() / 2);
    //  endregion

    public void beforeJava8FindMinNumber() {
        int min = numbers[0];
        System.out.println("beforeJava8FindMinNumber(): ");
        for (int i = 1; i < numbers.length; i++) {
            if (min < numbers[i]) {
                min = numbers[i];
            }
        }
        System.out.println("\t Minimum number is " + min);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsFindMinNumber() {
        System.out.println("java8StreamsFindMinNumber(): ");
        //  Can throw exception if min cannot be found (e.g. if numbers array is empty)
        int minimum = IntStream.of(numbers).min().getAsInt();
        System.out.println("\t a) Using IntStream.of(an-array).min().getAsInt(): Minimum number is " + minimum);

        ////    The following 2 lines will produce the same output
        //IntStream.of(numbers).min().ifPresent(min -> System.out.println("Java8StreamsFindMinNumber()a -- " + min));
        //IntStream.of(numbers).min().ifPresent(System.out::println("Java8StreamsFindMinNumber()b -- " + min));
        OptionalInt optionalInt = IntStream.of(numbers).min();
        //  The following 2 lines will produce the same output
        optionalInt.ifPresent(min -> System.out.println("\t b) Using isPresent(min -> ..): " + min));
        System.out.print("\t c) Using isPresent(System.out::println): ");
        optionalInt.ifPresent(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsAggregatedFunctions() {
        // For the same array we need to keep calling IntStream.of(array) with a different function
        System.out.println("java8StreamsAggregatedFunctions(): ");
        System.out.println("\t a) average is " + IntStream.of(numbers).average() +
                ", count is " + IntStream.of(numbers).count() +
                ", max is " + IntStream.of(numbers).max() +
                ", min is " + IntStream.of(numbers).min() +
                ", sum is " + IntStream.of(numbers).sum() + "  ");
        System.out.print("\t\tdistinct is ");
        IntStream.of(numbers).distinct().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  So, we create stream once with IntStream.of(array)
        IntSummaryStatistics statistics = IntStream.of(numbers).summaryStatistics();
        System.out.println("\t b) average is " + statistics.getAverage() +
                ", count is " + statistics.getCount() +
                ", max is " + statistics.getMax() +
                ", min is " + statistics.getMin() +
                ", sum is " + statistics.getSum()
        );
        System.out.println("--------------------------------------------------------------------------------------------------");

    }

    public void beforeJava8Find3DistinctSmallestNumbers() {
        //  Clone numbers array to avoid mutating the original array
        int[] arrayCopy = Arrays.copyOf(numbers, numbers.length);

        //  Sort the cloned copy
        Arrays.sort(arrayCopy);

        System.out.println("beforeJava8Find3DistinctSmallestNumbers(): *** They Are Not Distinct *** ");
        //  Pick first 3 numbers, but for distinct we will need a few more lines of code
        for (int i = 0; i < 3; i++) {
            System.out.println(arrayCopy[i] + "  ");
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsFind3DistinctSmallestNumbers() {
        //  Method chaining for complicated logic and original array is not mutated!
        System.out.println("java8StreamsFind3DistinctSmallestNumbers(): *** They Are Distinct ***");
        /*  Create  */
        IntStream.of(numbers)
                /*  Process */.distinct()
                /*  Process */.sorted()
                /*  Process */.limit(3)
                /*  Consume */.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsApiMethods() {
        System.out.println("java8StreamsApiMethods(): ");

        //  Distinct
        System.out.print("\t a) IntStream.of(int[]).distinct(): ");
        IntStream.of(numbers).distinct().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  get first 3
        System.out.print("\t c) IntStream.of(int[]).limit(3): ");
        IntStream.of(numbers).limit(3).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  skip first 3
        System.out.print("\t d) IntStream.of(int[]).skip(3): ");
        IntStream.of(numbers).skip(3).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Filter: only even numbers
        System.out.print("\t e) IntStream.of(int[]).filter(num -> num%2 == 0): ");
        IntStream.of(numbers).filter(num -> num % 2 == 0).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Sort
        System.out.print("\t f) IntStream.of(int[]).sorted(): ");
        IntStream.of(numbers).sorted().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Map: double each num
        System.out.print("\t g) IntStream.of(int[]).map(num -> num * 2): ");
        IntStream.of(numbers).map(num -> num * 2).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Boxed: Convert each num to wrapper Integer
        System.out.print("\t h) IntStream.of(int[]).boxed(): ");
        IntStream.of(numbers).boxed().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Range(1, 10): print 1 to 9
        System.out.print("\t i) IntStream.range(1, 10): ");
        IntStream.range(1, 10).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  toArray(): Collect wrapper Integer(s) into List
        System.out.print("\t j) IntStream.range(1, 10).boxed().toArray(): ");
        int[] arrayNumbers1to9 = IntStream.range(1, 10).toArray();
        for (int i = 0; i < arrayNumbers1to9.length; i++) {
            System.out.print(arrayNumbers1to9[i] + "  ");
        }
        System.out.println(' ');

        //  Collect: Collect wrapper Integer(s) into List
        System.out.print("\t k) IntStream.range(1, 10).boxed().collect(Collectors.toList()): ");
        List<Integer> listNumbers1to9 = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        for (Integer integer : listNumbers1to9) {
            System.out.print(integer + "  ");
        }
        System.out.println(' ');

        //  RangeClosed(): Print numbers 1 to 10
        System.out.print("\t l) IntStream.rangeClosed(1, 10).forEach(num -> System.out.print(num + \", \")): ");
        IntStream.rangeClosed(1, 10).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  return boolean: TRUE if any num is odd?
        System.out.println("\t m) IntStream.of(int[]).anyMatch(num -> num%2 == 1): " + IntStream.of(numbers).anyMatch(num -> num % 2 == 1));

        //  return boolean: TRUE if all num are odd?
        System.out.println("\t n) IntStream.of(int[]).allMatch(num -> num%2 == 1): " + IntStream.of(numbers).allMatch(num -> num % 2 == 1));

        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void beforeJava8FindNamesOf3HighestEarningEmployees() {
        System.out.println("beforeJava8FindNamesOf3HighestEarningEmployees(): ");
        List<Employee> employeesList = getAllEmployees();
        //  New List
        List<Employee> copyList = new ArrayList<>(employeesList);
        //  Sort descending
        copyList.sort((o1, o2) -> o2.getSalary() - o1.getSalary());
        //  Get first 3
        for (int i = 0; i < 3; i++) {
            Employee employee = copyList.get(i);
            System.out.println("\t " + (i + 1) + ") " + employee.getFirstName() + ' ' + employee.getLastName());
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsFindNamesOf3HighestEarningEmployees() {
        System.out.println("java8StreamsFindNamesOf3HighestEarningEmployees(): ");
        List<Employee> employeesList = getAllEmployees();
        /*  Create  */
        employeesList.stream()
                /*  Process */.sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                /*  Process */.limit(3)
                /*  Process */.map(Employee::getFullName)
                /*  Consume */.forEach(emp -> System.out.println("\t " + emp));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * Just searching for the 3 highest paid ACTIVE employees makes the code less readable
     */
    public void beforeJava8FindNamesOf3HighestEarningActiveEmployees() {
        System.out.println("beforeJava8FindNamesOf3HighestEarningActiveEmployees(): ");
        List<Employee> employeesList = getAllEmployees();
        //  New List
        List<Employee> copyList = new ArrayList<>(employeesList);
        //  Sort descending
        copyList.sort((o1, o2) -> o2.getSalary() - o1.getSalary());
        //  Get first 3 Highest Earning ACTIVE employees
        List<String> highestPaidNames = new ArrayList<>();
        Iterator<Employee> iterator = copyList.iterator();
        int count = 0;
        while (count < 3 && iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.isActive()) {
                highestPaidNames.add(employee.getFullName());
                count++;
            }
        }

        //  Print the names of the highest paid ACTIVE employees
        highestPaidNames.forEach(name -> System.out.println("\t " + name));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * Searching for the first 3 highest paid ACTIVE employees using streams, the code is as readable as before
     * We just need to add .filter to keep the employees that are ACTIVE
     * This time we collect result into a List and print it.
     */
    public void java8StreamsFindNamesOf3HighestEarningActiveEmployees() {
        System.out.println("java8StreamsFindNamesOf3HighestEarningActiveEmployees(): ");
        List<Employee> employeesList = getAllEmployees();

        List<String> highestPaidNames = employeesList

        /*  Create  */  .stream()
        /*  Process */  .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
        /*  Process */  .filter(employee -> employee.isActive())
        /*  Process */  .limit(3)
        /*  Process */  .map(Employee::getFullName)
        /*  Consume */  .collect(Collectors.toList());

        highestPaidNames.forEach(name -> System.out.println("\t " + name));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsIntoCollectors() {
        System.out.println("java8StreamsIntoCollectors(): ");

        Stream<Employee> streamOfEmployees = getAllEmployees()
                /*  Create  */.stream()
                /*  Process */.sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                /*  Process */.filter(employee -> employee.isActive())
                /*  Process */.limit(3);

        //  Collect as different Collections: List<>, Set<>, Map<K,V>
        List<String> ListOfEmployeesNames = streamOfEmployees
                /*  Process */.map(Employee::getFullName)
                /*  Consume */.collect(Collectors.toList());

        Set<String> setOfEmployeesNames = streamOfEmployees
                /*  Process */.map(Employee::getFullName)
                /*  Consume */.collect(Collectors.toSet());

        Map<String, Employee> MapOfEmployees = streamOfEmployees
                /*  Consume */.collect(Collectors.toMap(e -> e.getFullName(), e -> e));

        ListOfEmployeesNames.forEach(name -> System.out.println("\t " + name));
        setOfEmployeesNames.forEach(name -> System.out.println("\t " + name));
        MapOfEmployees.forEach((k,v) -> System.out.println("\t " + "key: " + k + ", value: " + v));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /*
     * Exercise:
     * You are given a List of integers and number of steps, to rotate the list.
     * You need to write the the algorithm in 2 way: with and without Java8 new features
     * Note: number of steps can be bigger than the size of the list of integers.
     * Given List = {10, 20, 30, 40, 50}
     * Example #1: steps=0 ==> after rotating the list will be: {30, 40, 50, 10, 20}
     * Example #2: steps=2 ==> after rotating the list will be: {30, 40, 50, 10, 20}
     * Example #3: steps=8 ==> after rotating the list will be: {40, 50, 10, 20, 30}
     */
    public void rotateListOfIntegers() {
        System.out.println("rotateListOfIntegers(): ");
        List<Integer> listBeforeJava8 = this.beforeJava8RotateList(this.steps, this.listOfIntegers);
        List<Integer> ListUsingJava8 = this.java8StreamsRotateList(this.steps, this.listOfIntegers);

        System.out.print("\t Before Java8: ");
        listBeforeJava8.forEach(num -> System.out.print(num + "  "));
        System.out.println("\n");

        System.out.print("\t Java8 Stream: ");
        ListUsingJava8.forEach(num -> System.out.println("\t " + num + "  "));
        System.out.println("\n");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * For this method you will write an algorithm to rotate the {@link List} of
     * {@link Integer}s {@code steps} times without using <b>ANY</b> of the new
     * features of Java 8, e.g. No lambdas, No streams, No Optionals, etc.
     * NOTE: value of steps can be bigger than the list size.
     * @param steps Number of steps to rotate {@code intList}
     * @param intList the {@link List} of {@link Integer}s to rotate
     * @return List<Integer> new rotated {@link List} of {@link Integer}s.
     */
    public List<Integer> beforeJava8RotateList(int steps, List<Integer> intList) {
        int numberOfSteps = steps%intList.size();
        if (numberOfSteps == 0) {
            return intList;
        }
        List<Integer> resultList = new ArrayList<>();
        int startFrom = numberOfSteps - 1;

        //  From the item we start with to the end of the List
        for (int i = startFrom; i<intList.size(); i++) {
            resultList.add(intList.get(i));
        }

        //  from 0 to the item before the one we started from
        for (int i = 0; i < startFrom; i++) {
            resultList.add(intList.get(i));
        }
        return resultList;
    }

    /**
     * For this method you will write an algorithm to rotate the {@link List} of
     * {@link Integer}s {@code steps} times using <b>ANY</b> of the new
     * features of Java 8, e.g. lambdas, streams, Optionals, etc.
     * NOTE: value of steps can be bigger than the list size.
     * @param steps Number of steps to rotate {@code intList}
     * @param intList the {@link List} of {@link Integer}s to rotate
     * @return List<Integer> new rotated {@link List} of {@link Integer}s.
     */
    public List<Integer> java8StreamsRotateList(int steps, List<Integer> intList) {
        int numberOfSteps = steps%intList.size();
        if (numberOfSteps == 0) {
            return intList;
        }
        Stream<Integer> streamOfIntegers = intList.stream();
        List<Integer> resultList = streamOfIntegers
                .skip(numberOfSteps)
                .collect(Collectors.toList());
        resultList.addAll(streamOfIntegers
                .limit(numberOfSteps)
                .collect(Collectors.toList())
        );
        return resultList;
    }

    public static void main(String[] args) {
        Java8StreamsDemo demo = new Java8StreamsDemo();

        demo.beforeJava8FindMinNumber();
        demo.java8StreamsFindMinNumber();

        demo.beforeJava8Find3DistinctSmallestNumbers();
        demo.java8StreamsFind3DistinctSmallestNumbers();

        demo.beforeJava8FindNamesOf3HighestEarningEmployees();
        demo.java8StreamsFindNamesOf3HighestEarningEmployees();

        demo.beforeJava8FindNamesOf3HighestEarningActiveEmployees();
        demo.java8StreamsFindNamesOf3HighestEarningActiveEmployees();

        demo.java8StreamsApiMethods();
        demo.java8StreamsAggregatedFunctions();

        demo.rotateListOfIntegers();

    }

    private List<Employee> getAllEmployees() {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "john", "doe", 12345, true));
        empList.add(new Employee(2, "jane", "doe", 332211, false));
        empList.add(new Employee(3, "miri", "regev", 112233, true));
        empList.add(new Employee(4, "anya", "regev", 4321, true));
        empList.add(new Employee(5, "benny", "regev", 254321, true));
        empList.add(new Employee(6, "princess", "anna", 5432, true));
        empList.add(new Employee(7, "princess", "elza", 9876, true));
        empList.add(new Employee(8, "queen", "ester", 13579, true));
        empList.add(new Employee(9, "king", "solomon", 74680, false));
        empList.add(new Employee(10, "king", "david", 98765, true));
        return empList;
    }

}
