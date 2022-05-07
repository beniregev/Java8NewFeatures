package com.beniregev.e_streamsapi;

import com.beniregev.e_streamsapi.model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>
 * <div>There are 3 distinct part of using a Stream: </div>
 * <div>1) Create.</div>
 * <div>2) Process.</div>
 * <div>3) Consume.</div>
 * </p>
 * <p>
 * This class contains examples and demos for various usages of the new Stream API introduced in Java 8.
 * </p>
 * <p>
 * There are examples and demos for implementatio before Java 8 vs. the implementation in Java 8 using Stream API.
 * </p>
 * @author Binyamin Regev e-mail: beniregev@gmail.com
 * @since 1.8
 */
public class Java8StreamsDemo {
    //  region Class Finals
    private static final String PATH_RESOURCES = "D:/JavaProjects/IdeaProjects/Java8NewFeatures/src/main/resources/";

    private static final String FILE_BANDS_TXT = "bands.txt";
    private static final String FILE_DATA_CSV = "data.csv";
    //  endregion

    //  region Class properties
    private int[] numbers = {4, 90, 0, 1, 13, 4, 90, 16, 2, 0, 1};
    private List<Integer> listOfIntegers = Arrays.asList(2004, 90, 25, 91, 84, 0, 69, 1, 2020, 95, 81, 2014, 13, 83, 4, 77, 90, 2001, 16, 81, 2, 34, 2005, 0, 1);
    private int steps = (listOfIntegers.size() * 10) + (listOfIntegers.size() / 2);
    private List<Employee> listOfEmployees = new ArrayList<>();
    private long start, finish;
    //  endregion


    public Java8StreamsDemo() {
        listOfEmployees = getAllEmployees();
    }

    public void beforeJava8FindMinNumber() {
        int min = numbers[0];
        System.out.println("beforeJava8FindMinNumber(): ");
        for (int i = 1; i < numbers.length; i++) {
            if (min < numbers[i]) {
                min = numbers[i];
            }
        }
        System.out.println("\tMinimum number is " + min);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsFindMinNumber() {
        System.out.println("java8StreamsFindMinNumber(): ");
        //  Can throw exception if min cannot be found (e.g. if numbers array is empty)
        int minimum = IntStream.of(numbers).min().getAsInt();
        System.out.println("\ta) Using IntStream.of(an-array).min().getAsInt(): Minimum number is " + minimum);

        ////    The following 2 lines will produce the same output
        //IntStream.of(numbers).min().ifPresent(min -> System.out.println("Java8StreamsFindMinNumber()a -- " + min));
        //IntStream.of(numbers).min().ifPresent(System.out::println("Java8StreamsFindMinNumber()b -- " + min));
        OptionalInt optionalInt = IntStream.of(numbers).min();
        //  The following 2 lines will produce the same output
        optionalInt.ifPresent(min -> System.out.println("\tb) Using isPresent(min -> ..): " + min));
        System.out.print("\tc) Using isPresent(System.out::println): ");
        optionalInt.ifPresent(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsAggregatedFunctions() {
        // For the same array we need to keep calling IntStream.of(array) with a different function
        System.out.println("java8StreamsAggregatedFunctions(): ");
        System.out.println("\ta) average is " + IntStream.of(numbers).average() +
                ", count is " + IntStream.of(numbers).count() +
                ", max is " + IntStream.of(numbers).max() +
                ", min is " + IntStream.of(numbers).min() +
                ", sum is " + IntStream.of(numbers).sum() + "  ");
        System.out.print("\t\tdistinct is ");
        IntStream.of(numbers).distinct().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  So, we create stream once with IntStream.of(array)
        IntSummaryStatistics statistics = IntStream.of(numbers).summaryStatistics();
        System.out.println("\tb) average is " + statistics.getAverage() +
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

    public void java8StreamsAPIMethods() {
        System.out.println("java8StreamsAPIMethods(): ");

        //  Distinct
        System.out.print("\ta) IntStream.of(int[]).distinct(): ");
        IntStream.of(numbers).distinct().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  get first 3
        System.out.print("\tc) IntStream.of(int[]).limit(3): ");
        IntStream.of(numbers).limit(3).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  skip first 3
        System.out.print("\td) IntStream.of(int[]).skip(3): ");
        IntStream.of(numbers).skip(3).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Filter: only even numbers
        System.out.print("\te) IntStream.of(int[]).filter(num -> num%2 == 0): ");
        IntStream.of(numbers).filter(num -> num % 2 == 0).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Sort
        System.out.print("\tf) IntStream.of(int[]).sorted(): ");
        IntStream.of(numbers).sorted().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Map: double each num
        System.out.print("\tg) IntStream.of(int[]).map(num -> num * 2): ");
        IntStream.of(numbers).map(num -> num * 2).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Boxed: Convert each num to wrapper Integer
        System.out.print("\th) IntStream.of(int[]).boxed(): ");
        IntStream.of(numbers).boxed().forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  Range(1, 10): print 1 to 9
        System.out.print("\ti) IntStream.range(1, 10): ");
        IntStream.range(1, 10).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  toArray(): Collect wrapper Integer(s) into List
        System.out.print("\tj) IntStream.range(1, 10).boxed().toArray(): ");
        int[] arrayNumbers1to9 = IntStream.range(1, 10).toArray();
        for (int i = 0; i < arrayNumbers1to9.length; i++) {
            System.out.print(arrayNumbers1to9[i] + "  ");
        }
        System.out.println(' ');

        //  Collect: Collect wrapper Integer(s) into List
        System.out.print("\tk) IntStream.range(1, 10).boxed().collect(Collectors.toList()): ");
        List<Integer> listNumbers1to9 = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        for (Integer integer : listNumbers1to9) {
            System.out.print(integer + "  ");
        }
        System.out.println(' ');

        //  RangeClosed(): Print numbers 1 to 10
        System.out.print("\tl) IntStream.rangeClosed(1, 10).forEach(num -> System.out.print(num + \", \")): ");
        IntStream.rangeClosed(1, 10).forEach(num -> System.out.print(num + "  "));
        System.out.println(' ');

        //  return boolean: TRUE if any num is odd?
        System.out.println("\tm) IntStream.of(int[]).anyMatch(num -> num%2 == 1): " + IntStream.of(numbers).anyMatch(num -> num % 2 == 1));

        //  return boolean: TRUE if all num are odd?
        System.out.println("\tn) IntStream.of(int[]).allMatch(num -> num%2 == 1): " + IntStream.of(numbers).allMatch(num -> num % 2 == 1));

        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsSomeExamples() throws IOException {
        System.out.println("java8StreamsExamples() ");
        //  1. Integer Stream
        System.out.println("\t1. Integer Stream:");
        IntStream.range(1, 10).forEach(System.out::print);
        IntStream.range(1, 10).forEach(num -> System.out.print(num + " "));

        System.out.println("\n\t2. Integer Stream with skip:");
        IntStream.range(1, 10).skip(5).forEach(num -> System.out.print(num + " "));

        System.out.print("\n\t3. Integer Stream with sum: ");
        System.out.println(IntStream.range(1, 10).sum());

        System.out.print("\n\t4. Stream.of, sorted and findFirst: ");
        Stream.of("Ava", "Nagila", "Nice", "Company", "Java", "jQuery", "Spring5", "Springboot")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        //  5. Create a stream from an Array. Then sort, filter and print it
        System.out.println("\n\t5. Stream from Array, sort, filter and print: ");
        String[] names = { "JavaScript", "Nagila", "Nice", "Company", "Java", "jQuery", "Spring5", "Springboot" };
        Arrays.stream(names)
                .filter(x -> x.startsWith("J"))
                .sorted()
                .forEach(name -> System.out.println("\t\t" + name));

        //  6. Average of squares of an int array
        System.out.print("\n\t6. Average of squares of an int array: ");
        Arrays.stream(new int[] {2,4,6,8,10,12,14,16,18,20})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        //  7. Stream from List, filter and print
        System.out.print("\n\t7. Stream from List, filter and print: ");
        List<String> languages = Arrays.asList("JavaScript", "CSS3", "Angular8", "Java", "jQuery", "Spring5", "Springboot4", "HTML5", "Cucumber", "Jijot");
        languages.stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("j"))
                .forEach(lang -> System.out.print('"' + lang + "\"  "));

        //  8. Stream rows from text file, sort, filter and print
        System.out.println("\n\t8. Stream rows from text file, sort, filter and print: ");
//        Stream<String> bands1 = Files.lines(Paths.get(PATH_RESOURCES + FILE_BANDS_TXT));
//
//        bands1.sorted()
//                .filter(x -> x.length() > 13)
//                .forEach(band -> System.out.println("\t\t" + band));
//        bands1.close();

        //  9. Stream rows from text file as save to List
        System.out.println("\n\t9. Stream rows from text file as save to List: ");
        List<String> listOfBands = Files.lines(Paths.get(PATH_RESOURCES + FILE_BANDS_TXT))
                .filter(x -> x.contains("jit"))
                .collect(Collectors.toList());
        listOfBands.forEach(str -> System.out.println("\t\t" + str));

        //  10. Stream rows from CSV file and count the good rows that have all values
        System.out.print("\n\t10. Stream rows from CSV file and count: ");
        Stream<String> rows1 = Files.lines(Paths.get(PATH_RESOURCES + FILE_DATA_CSV));
        //  Splitting each row into an array, and array with less than 3 items will be filtered out
        int rowCount = (int) rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();

        //  11. Stream rows from CSV, parse data from rows
        System.out.println("\n\t11. Stream rows from CSV, parse data from rows: ");
        Stream<String> rows2 = Files.lines(Paths.get(PATH_RESOURCES + FILE_DATA_CSV));
        //  Splitting each row into an array, and array with less than 3 items will be filtered out
        rows2.map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println("\t\t" + x[0] + "  " + x[1] + "  " + x[2]));
        rows2.close();

        //  12. Stream rows from CSV, store fields in HashMap
        System.out.println("\n\t12. Stream rows from CSV, store fields in HashMap: ");
        Stream<String> rows3 = Files.lines(Paths.get(PATH_RESOURCES + FILE_DATA_CSV));
        Map<String, Integer> map = new HashMap<>();
        map = rows3.map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        rows3.close();
        //map.keySet().stream().forEach(key -> System.out.print(key + "  "));
        //map.values().stream().forEach(val -> System.out.print(val + "  "));
        map.forEach((key,val) -> System.out.println("\t\tkey=" + key + ", value=" + val));

        //  13. Reduction - sum
        System.out.print("\n\t13. Reduction - sum: ");
        double total = Stream.of(8.2, 5.6, 8.1, 7.3, 9.6, 6.7, 4.8, 7.7)
                .reduce(0.0, (Double a, Double b) -> a + b);
        System.out.println(total);

        //  14. Reduction - Summary Statistics -- NOTE: only works with integers
        System.out.print("\n\t14. Reduction - Summary Statistics (only work w/ integers): ");
        IntSummaryStatistics stats = IntStream.of(82, 56, 81, 42, 73, 67, 48, 77)
                .summaryStatistics();
        System.out.println(stats);

        //  15. Create a phone-number from a given int[] with numbers between 0 and 9
        System.out.println("\t\t15. Create a phone-number from a given int[] with numbers between 0 and 9");
        int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(String.format("(%d%d%d) %d%d%d-%d%d%d%d", java.util.stream.IntStream.of(numbers).boxed().toArray()));

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
            System.out.println("\t" + (i + 1) + ") " + employee.getFirstName() + ' ' + employee.getLastName());
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
                /*  Consume */.forEach(emp -> System.out.println("\t" + emp));
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
        highestPaidNames.forEach(name -> System.out.println("\t" + name));
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

                /*  Create  */.stream()
                /*  Process */.sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                /*  Process */.filter(employee -> employee.isActive())
                /*  Process */.limit(3)
                /*  Process */.map(Employee::getFullName)
                /*  Consume */.collect(Collectors.toList());

        highestPaidNames.forEach(name -> System.out.println("\t" + name));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsIntoCollectors() {
        System.out.println("java8StreamsIntoCollectors(): ");

        Stream<Employee> streamOfEmployees1 = getAllEmployees()
                /*  Create  */.stream()
                /*  Process */.sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                /*  Process */.filter(employee -> employee.isActive())
                /*  Process */.limit(3);

        Stream<Employee> streamOfEmployees2 = getAllEmployees()
                /*  Create  */.stream()
                /*  Process */.sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                /*  Process */.filter(employee -> employee.isActive())
                /*  Process */.limit(3);

        Stream<Employee> streamOfEmployees3 = getAllEmployees()
                /*  Create  */.stream()
                /*  Process */.sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                /*  Process */.filter(employee -> employee.isActive())
                /*  Process */.limit(3);

        //  Collect as different Collections: List<>, Set<>, Map<K,V>
        List<String> ListOfEmployeesNames = streamOfEmployees1
                /*  Process */.map(Employee::getFullName)
                /*  Consume */.collect(Collectors.toList());

        Set<String> setOfEmployeesNames = streamOfEmployees2
                /*  Process */.map(Employee::getFullName)
                /*  Consume */.collect(Collectors.toSet());

        Map<String, Employee> MapOfEmployees = streamOfEmployees3
                /*  Consume */.collect(Collectors.toMap(e -> e.getFullName(), e -> e));

        ListOfEmployeesNames.forEach(name -> System.out.println("\t" + name));
        setOfEmployeesNames.forEach(name -> System.out.println("\t" + name));
        MapOfEmployees.forEach((k, v) -> System.out.println("\t" + "key: " + k + ", value: " + v));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsCollectors() {
        System.out.println("java8StreamsCollectors(): ");

        //  Collect into a string with comma separator, e.g. "value, value, value, ..."
        String names = listOfEmployees.stream()
                .limit(3)
                .map(Employee::getFullName)
                .collect(Collectors.joining(", "));

        //  Collect into a Map<String, List<Employee>>, group by dept
        Map<String, List<Employee>> empByDept = listOfEmployees
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDept()));

        //  Collect into a Map<String, Long>, counting employees in each dept
        Map<String, Long> deptCounts = listOfEmployees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));

        System.out.println("\tMap<String, List<Employee>> empByDept:");
        empByDept.forEach((k, v) -> System.out.println("\t\t" + "key: " + k + ", value: \n\t\t" + v));
        System.out.println("\t---------------------------------------------------------------------------------------------");
        System.out.println("\tMap<String, Long> deptCounts:");
        deptCounts.forEach((k, v) -> System.out.println("\t\t" + "key: " + k + ", value: " + v));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * Because of the time it takes and the memory it consumes, consider Using ParallelStreams only when you have
     * more than 10000 (10K) elements, and even then: measure first!!!
     */
    public void java8StreamsParallelStreams() {
        System.out.println("java8StreamsParallelStreams(): ");
        long parStart = System.currentTimeMillis();
        Map<String, List<Employee>> empByDept = getAllEmployees()
                .stream()
                .parallel()
                .collect(Collectors.groupingBy(e -> e.getDept()));
        long parEnd = System.currentTimeMillis();
        System.out.println("\tParallel Streams: Collect as Map<String, List<Employee>>, group by dept -- time taken: " + (parEnd - parStart) + "\n");

        List<Employee> listOfEmployeesLocal = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            listOfEmployeesLocal.addAll(getAllEmployees());
        }
        System.out.println("\tlistOfEmployees.size() = " + listOfEmployeesLocal.size() + "\n");

        //  Here We will use a 'Sequential Stream' & Displaying The Result
        long seqStart = System.currentTimeMillis();
        System.out.println("\tSequential Steam Count = " + listOfEmployeesLocal.stream().filter(e -> e.getSalary() > 10000).count());
        long seqEnd = System.currentTimeMillis();
        System.out.println("\tSequential Steam time taken = " + (seqEnd - seqStart));

        //  Here We will use a 'Parallel Stream' & Displaying The Result
        parStart = System.currentTimeMillis();
        System.out.println("\tParallel Steam Count = " + listOfEmployeesLocal.parallelStream().filter(e -> e.getSalary() > 10000).count());
        parEnd = System.currentTimeMillis();
        System.out.println("\tParallel Steam time taken = " + (parEnd - parStart) + "\n");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * Simple demonstration of Sequential vs. Parallel streams:
     * print the numbers from 0 to 9
     */
    public void java8StreamsSequentialVsParallel() {
        System.out.println("java8StreamsSequentialVsParallel(): ");
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.print("\tSequential: ");
        list.stream().forEach(System.out::print);
        System.out.println();
        System.out.print("\tParallel: ");
        list.parallelStream().forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsParallelVsSequentialSorting() {
        System.out.println("java8StreamsParallelVsSequentialStreams(): ");
        start = System.currentTimeMillis();
        List<Employee> sortedItemsSequential = listOfEmployees.stream()
                .sorted(Comparator.comparing(Employee::getFullName))
                .collect(Collectors.toList());
        finish = System.currentTimeMillis();
        System.out.println("\tSequential Stream Sort took: " + (finish - start) + " milliseconds");
        sortedItemsSequential.forEach(employee -> System.out.println("\t\t" + employee.getFullName()));

        start = System.currentTimeMillis();
        List<Employee> sortedItemsParallel = listOfEmployees
                .parallelStream()
                .sorted(Comparator.comparing(Employee::getFullName))
                .collect(Collectors.toList());
        finish = System.currentTimeMillis();
        System.out.println("\tParallel Stream Sort took: " + (finish - start) + " milliseconds");
        sortedItemsParallel.forEach(employee -> System.out.println("\t\t" + employee.getFullName()));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsParallelVsSequentialProcessing() {
        System.out.println("java8StreamsParallelVsSequentialStreams(): ");
        //  Before Java 8: Create & populate a list with numbers 0 to 100
        List<Integer> myList = new ArrayList<>();
        for (int i = 0; i < 100; i++) myList.add(i);

        //  Sequential Stream: using .filter() and .forEach() and a Lambda Expression
        System.out.print("\tHigh Nums sequential = [ ");
        myList.stream()
                .filter(p -> p > 80)
                .forEach(p -> System.out.print(p + " "));
        System.out.println("]");

        //  Parallel Stream: Doing it All-in-One -- Using Java 8 Stream API to
        //  create List of Integers 0 to 100, then filter the integers greater
        //  than 80 and printing them using forEach() method with Lambda Expression
        System.out.print("\tHigh Nums parallel   = [ ");
        IntStream
                .range(1, 100)
                .boxed()
                .collect(Collectors.toList())
                .parallelStream()
                .filter(p -> p > 80)
                .forEach(p -> System.out.print(p + " "));
        System.out.println("]");

        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsParallelVsSequentialTotalSalary() {
        System.out.println("java8StreamsParallelVsSequentialTotalSalary(): ");
        start = System.currentTimeMillis();
        int totalSalarySequential = listOfEmployees.stream()
                .map(e -> e.getSalary())
                .reduce(0, (a1, a2) -> a1+a2);
        finish = System.currentTimeMillis();
        System.out.println("\tTotal Salary Sequential took: " + (finish - start) + " milliseconds");
        System.out.println("\tTotal Salary is: " + totalSalarySequential);
        System.out.println();
        start = System.currentTimeMillis();
        int totalSalaryParallel = listOfEmployees
                .parallelStream()
                .map(e -> e.getSalary())
                .reduce(0, (a1, a2) -> a1+a2);
        finish = System.currentTimeMillis();
        System.out.println("\tTotal Salary Parallel took: " + (finish - start) + " milliseconds");
        System.out.println("\tTotal Salary is: " + totalSalarySequential);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void java8StreamsParallelVsSequentialFindMaxUsingOptional() {
        System.out.println("java8StreamsParallelVsSequentialFindMaxUsingOptional(): ");
        start = System.currentTimeMillis();
        Optional<Employee> maxSalarySequential = listOfEmployees.stream()
                .reduce((Employee e1, Employee e2) -> e1.getSalary() < e2.getSalary() ? e2 : e1);
        finish = System.currentTimeMillis();
        System.out.println("\tFind Max Salary Sequential took: " + (finish - start) + " milliseconds");
        if (maxSalarySequential.isPresent())
            System.out.println("\tMax Salary Employee is: " + maxSalarySequential.get().getFullName() + " who has salary of " + maxSalarySequential.get().getSalary());
        else
            System.out.println("\tTotal Salary employee not present!");

        System.out.println();
        start = System.currentTimeMillis();
        Optional<Employee> maxSalaryParallel = listOfEmployees
                .parallelStream()
                .reduce((Employee e1, Employee e2) -> e1.getSalary() < e2.getSalary() ? e2 : e1);
        finish = System.currentTimeMillis();
        System.out.println("\tFind Max Salary Parallel took: " + (finish - start) + " milliseconds");
        maxSalaryParallel.ifPresent(e -> System.out.println("\tMax Salary Employee is: " + e.getFullName() + " who has salary of " + e.getSalary()));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /*
     * Exercise:
     * You are given a List of integers and number of steps, to rotate the list.
     * You need to write the the algorithm in 2 way: with and without Java8 new features
     * Note: number of steps can be bigger than the size of the list of integers.
     * Given List = {10, 20, 30, 40, 50}
     * Example #1: steps=0 ==> after rotating the list will be: {10, 20, 30, 40, 50}
     * Example #2: steps=2 ==> after rotating the list will be: {30, 40, 50, 10, 20}
     * Example #3: steps=8 ==> after rotating the list will be: {40, 50, 10, 20, 30}
     */
    public void rotateListOfIntegers() {
        System.out.println("rotateListOfIntegers(): steps=" + this.steps);
        System.out.print("\tOriginal List: ");
        listOfIntegers.forEach(num -> System.out.print(num + "  "));
        System.out.println("| count: " + listOfIntegers.size() + "\n");

        List<Integer> listBeforeJava8 = this.beforeJava8RotateList(this.steps, this.listOfIntegers);
        List<Integer> ListUsingJava8 = this.java8StreamsRotateList(this.steps, this.listOfIntegers);

        System.out.print("\tBefore Java8: ");
        listBeforeJava8.forEach(num -> System.out.print(num + "  "));
        System.out.println("\n");

        System.out.print("\tJava8 Stream: ");
        ListUsingJava8.forEach(num -> System.out.print(num + "  "));
        System.out.println("\n");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * Exercise: Given an array with duplicate values, we want an array with all distinct values.
     */
    public void java8StreamsArrayOfDistinctValues() {
        System.out.println("java8StreamsArrayOfDistinctValues(): ");
        int[] arrayWithDuplicates = { 5,4,6,7,8,4,5,6,7,3,1,1,2,3,4,5,6,7,8,8};
        System.out.println("Array with duplicate values: " + Arrays.toString(arrayWithDuplicates));

        //  region Exercise Solution
        int[] arrayOfDistinctValues = IntStream.of(arrayWithDuplicates).distinct().toArray();
        System.out.println("Array of Distinct values: " + Arrays.toString(arrayOfDistinctValues));
        //  endregion
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    /**
     * For this method you will write an algorithm to rotate the {@link List} of
     * {@link Integer}s {@code steps} times without using <b>ANY</b> of the new
     * features of Java 8, e.g. No lambdas, No streams, No Optionals, etc.
     * NOTE: value of steps can be bigger than the list size.
     *
     * @param steps   Number of steps to rotate {@code intList}
     * @param intList the {@link List} of {@link Integer}s to rotate
     * @return List<Integer> new rotated {@link List} of {@link Integer}s.
     */
    public List<Integer> beforeJava8RotateList(int steps, List<Integer> intList) {
        int numberOfSteps = steps % intList.size();
        if (numberOfSteps == 0) {
            return intList;
        }
        List<Integer> resultList = new ArrayList<>();
        int startFrom = numberOfSteps - 1;
        //  From the item we start with to the end of the List
        for (int i = numberOfSteps; i < intList.size(); i++) {
            resultList.add(intList.get(i));
        }
        //  from 0 to the item before the one we started from
        for (int i = 0; i < numberOfSteps; i++) {
            resultList.add(intList.get(i));
        }
        return resultList;
    }

    /**
     * For this method you will write an algorithm to rotate the {@link List} of
     * {@link Integer}s {@code steps} times using <b>ANY</b> of the new
     * features of Java 8, e.g. lambdas, streams, Optionals, etc.
     * NOTE: value of steps can be bigger than the list size.
     *
     * @param steps   Number of steps to rotate {@code intList}
     * @param intList the {@link List} of {@link Integer}s to rotate
     * @return List<Integer> new rotated {@link List} of {@link Integer}s.
     */
    public List<Integer> java8StreamsRotateList(int steps, List<Integer> intList) {
        int numberOfSteps = steps % intList.size();
        if (numberOfSteps == 0) {
            return intList;
        }
        //  From the item we start with to the end of the List
        List<Integer> resultList = intList.stream()
                .skip(numberOfSteps)
                .collect(Collectors.toList());
        //  Add the numbers from 0 to the item before the one we started from to the end of the result list
        resultList.addAll(intList.stream()
                .limit(numberOfSteps)
                .collect(Collectors.toList()));
        return resultList;
    }

    /**
     * Exercise: Given an {@link HashSet} collection containing {@code Strings}, we want
     * to remove from it all the Strings that contain at least one character that isn't
     * a capital (UPPER case) letter.
     */
    public void java8StreamsRemoveInvalidStrings() {
        System.out.println("java8StreamsRemoveInvalidStrings(): ");
        Set<String> strings = new HashSet<>();
        strings.add("NICE");
        strings.add("Java 8");
        strings.add("SPRING5");
        strings.add("Springboot 2.2.1");
        strings.add("HTML 5");
        strings.add("CSS");
        strings.add("jQuery");
        strings.add("JavaScript");
        strings.add("HTML");
        strings.add("bootstrap4");

        System.out.println("Original HashSet of strings: ");
        strings.forEach(i -> System.out.print(i + " "));
        System.out.println();

        //  region Exercise Solution
        strings.removeIf(str -> !str.matches("[A-Z]+"));
        System.out.println("After removing strings: ");
        strings.forEach(i -> System.out.print(i + " "));
        System.out.println();
        //  endregion Exercise Solution
        System.out.println("--------------------------------------------------------------------------------------------------");
    }


    public static void main(String[] args) throws IOException {
        Java8StreamsDemo demo = new Java8StreamsDemo();

        demo.beforeJava8FindMinNumber();
        demo.java8StreamsFindMinNumber();

        demo.beforeJava8Find3DistinctSmallestNumbers();
        demo.java8StreamsFind3DistinctSmallestNumbers();

        demo.beforeJava8FindNamesOf3HighestEarningEmployees();
        demo.java8StreamsFindNamesOf3HighestEarningEmployees();

        demo.beforeJava8FindNamesOf3HighestEarningActiveEmployees();
        demo.java8StreamsFindNamesOf3HighestEarningActiveEmployees();


        demo.java8StreamsIntoCollectors();
        demo.java8StreamsAPIMethods();
        demo.java8StreamsSomeExamples();
        demo.java8StreamsAggregatedFunctions();

        demo.java8StreamsCollectors();
        demo.java8StreamsParallelStreams();
        demo.java8StreamsSequentialVsParallel();
        demo.java8StreamsParallelVsSequentialSorting();
        demo.java8StreamsParallelVsSequentialProcessing();
        demo.java8StreamsParallelVsSequentialTotalSalary();
        demo.java8StreamsParallelVsSequentialFindMaxUsingOptional();

        demo.java8StreamsArrayOfDistinctValues();
        demo.rotateListOfIntegers();
        demo.java8StreamsRemoveInvalidStrings();

    }

    private List<Employee> getAllEmployees() {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "john", "doe", "GRE", 12345, true));
        empList.add(new Employee(2, "jane", "doe", "GRE", 332211, false));
        empList.add(new Employee(3, "miri", "regev", "Health", 112233, true));
        empList.add(new Employee(4, "anya", "regev", "UI/UX", 4321, true));
        empList.add(new Employee(5, "benny", "regev", "R&D", 254321, true));
        empList.add(new Employee(6, "princess", "anna", "UI/UX", 5432, true));
        empList.add(new Employee(7, "princess", "elza", "R&D", 9876, true));
        empList.add(new Employee(8, "queen", "ester", "HR", 13579, true));
        empList.add(new Employee(9, "king", "solomon", "Management", 74680, false));
        empList.add(new Employee(10, "king", "david", "HQ", 98765, true));
        empList.add(new Employee(11, "queen", "padmeh", "UI/UX", 5432, true));
        empList.add(new Employee(12, "princess", "lea", "R&D", 9876, true));
        empList.add(new Employee(13, "yoav", "ben-tsruya", "Security", 3579, true));
        empList.add(new Employee(14, "joshua", "bin-nun", "Security", 4680, false));
        empList.add(new Employee(15, "king", "arthur", "Management", 8765, true));
        empList.add(new Employee(16, "king", "simba", "Security", 109876, false));
        empList.add(new Employee(17, "robin", "hood", "Welfare", 8765, true));
        empList.add(new Employee(18, "lady", "mary-ann", "Welfare", 10987, true));
        empList.add(new Employee(19, "aaron", "hacohen", "Management", 98765, false));
        empList.add(new Employee(20, "moses", "rabenu", "HQ", 98765, true));
        empList.add(new Employee(21, "hanavie", "jeremaya", "Inovation", 54321, true));
        empList.add(new Employee(22, "samuel", "hanavie", "GRE", 9876, false));
        empList.add(new Employee(23, "ehud", "ben-gera", "Security", 35791, true));
        empList.add(new Employee(24, "nakdimon", "ben-guryon", "Consulting", 54321, true));
        empList.add(new Employee(25, "yakir", "sheli", "HR", 6543, false));
        return empList;
    }

}
