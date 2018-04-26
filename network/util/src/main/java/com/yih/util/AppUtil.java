package com.yih.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtil {
    public String printDateTime(ZonedDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        return now.withZoneSameInstant(ZoneOffset.systemDefault()).format(formatter);
    }
}