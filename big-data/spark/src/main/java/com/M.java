package com;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class M {
    public static void main(String[] argc) {
        String date = "20120101111111";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
//        ZonedDateTime zdt = ZonedDateTime.parse(date, formatter);
//        System.out.println(zdt);
        System.out.println(localDateTime);
        System.out.println(ZonedDateTime.of(localDateTime, ZoneOffset.UTC));
    }

}
