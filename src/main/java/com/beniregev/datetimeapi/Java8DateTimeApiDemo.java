package com.beniregev.datetimeapi;

import java.time.*;

public class Java8DateTimeApiDemo {
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    private ZonedDateTime zonedDateTime;

    public void getCurrentDate() {
        localDate = LocalDate.now();
        System.out.println("getCurrentDate() = " + localDate);
        System.out.println("\tIs Leap Year? " + localDate.isLeapYear());
        System.out.println("\tDay of Week Name = " + localDate.getDayOfWeek().name());
        System.out.println("\tThe Date 23 days ago was = " + localDate.minusDays(23));
        System.out.println("\tlocalDate is now = " + localDate);
        System.out.println("\tThe Date 23 days ago was = " + localDate.plusMonths(6));
    }

    public void getCurrentTime() {
        localTime = LocalTime.now();
        System.out.println("getCurrentTime() = " + localTime);
    }

    public void getCurrentDateTime() {
        localDateTime = LocalDateTime.now();
        System.out.println("getCurrentDateTime() = " + localDateTime);
    }

    public void getCurrentZonedDateTime() {
        zonedDateTime = ZonedDateTime.now();
        System.out.println("getCurrentZonedDateTime() = " + zonedDateTime);
        System.out.println("\tZonedDateTime(\"GMT\") = " + ZonedDateTime.now(ZoneId.of("GMT")));
        System.out.println("\tZonedDateTime(\"Asia/Jerusalem\") = " + ZonedDateTime.now(ZoneId.of("Asia/Jerusalem")));
        System.out.println("\tZonedDateTime(\"Africa/Johannesburg\") = " + ZonedDateTime.now(ZoneId.of("Africa/Johannesburg")));
        System.out.println("\tZonedDateTime(ZoneId.systemDefault()) = " + ZonedDateTime.now(ZoneId.systemDefault()));
        System.out.println("\tPrint Available ZoneId(s): ");
        ZoneId.getAvailableZoneIds().stream().sorted().forEach(z -> System.out.println("\t\t" + z));

    }

    public static void main(String[] args) {
        Java8DateTimeApiDemo demo = new Java8DateTimeApiDemo();
        demo.getCurrentDate();
        demo.getCurrentTime();
        demo.getCurrentDateTime();
        demo.getCurrentZonedDateTime();
    }
}
