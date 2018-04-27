package com.yih.bt;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class Test {
    public static void main(String[] argc) throws IOException {
        String path = "/Users/peter.huang/Desktop/java-example/bt/bt-core/src/main/resources/a.torrent";

        String data = Files.toString(new File(path), StandardCharsets.US_ASCII);
        //   System.out.println(data);
        System.out.println(data.length());
        BenCoding coding = new BenCoding();
        Object obj = coding.decode(data);
        Map map = (Map) obj;
        Map info = (Map) map.get("info");
        String pieces = (String) info.get("pieces");

        byte[] array = pieces.getBytes(StandardCharsets.US_ASCII);
        System.out.println(array.length);
        System.out.println(array.length / 20);
        for (int i = 0; i < array.length / 20 - 1; i++) {
            byte[] dst = Arrays.copyOfRange(array, i * 20, (i + 1) * 20);
            System.out.println(Arrays.toString(dst));
//            String a = new String(dst);
//            System.out.println(a);
        }
    }
}