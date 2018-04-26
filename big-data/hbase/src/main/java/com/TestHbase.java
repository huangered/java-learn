package com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.logging.Logger;

public class TestHbase {
    private static Logger logger = Logger.getLogger("hbase");

    public static void main(String[] args) throws IOException {
        Configuration conf = null;
        HTablePool tablePool;

        conf = HBaseConfiguration.create();

//        conf.set("hbase.zookeeper.quorum", "libin2");
        tablePool = new HTablePool(conf, 10);
        TestHbase testHbase = new TestHbase();


        testHbase.createTable(conf, "test2", "a", "b", "c");

    }

    public void createTable(Configuration conf, String tableName, String... family) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor desc = new HTableDescriptor(tableName);
        for (int i = 0; i < family.length; i++) {
            desc.addFamily(new HColumnDescriptor(family[i]));
        }
        if (admin.tableExists(tableName)) {
            System.out.println("table Exists!");
            System.exit(0);
        } else {
            admin.createTable(desc);
            System.out.println("create table Success!");
        }


        admin.close();
    }
}