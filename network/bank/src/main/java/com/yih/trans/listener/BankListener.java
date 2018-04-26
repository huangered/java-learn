package com.yih.trans.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yih.trans.entity.MallReceipt;
import com.yih.trans.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BankListener {
    @Autowired
    BankService service;

    @KafkaListener(topics = "bank", groupId = "test")
    public void listen(String record) {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            // 尝试从JSON中读取对象

            MallReceipt mallReceipt = mapper.readValue(record, MallReceipt.class);
            System.out.println(mallReceipt);

            service.deal(mallReceipt);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}