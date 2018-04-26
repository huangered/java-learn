package com;

import lombok.val;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileChecksum;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestHadoop {
    public static void main(String[] argc) throws IOException, URISyntaxException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:8020");
        final FileSystem fs = FileSystem.get(conf);
        val d = fs.listFiles(new Path("/user/peter"), true);
        while (d.hasNext()) {
            LocatedFileStatus gg = d.next();
            System.out.println(gg);
        }
        for (int i = 0; i < 10; i++) {
            fs.copyFromLocalFile(new Path("/tmp/aaa"), new Path("/user/peter/test-" + i));
        }


        FileChecksum checksum = fs.getFileChecksum(new Path("/tmp/peter/test1"));
        System.out.println(checksum);
        fs.close();
    }
}