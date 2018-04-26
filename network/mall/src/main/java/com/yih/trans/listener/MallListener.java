package com.yih.trans.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yih.trans.entity.BankReceipt;
import com.yih.trans.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MallListener {
    @Autowired
    MallService service;

    @KafkaListener(topics = "mall", groupId = "test")
    public void listen(String record) {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            // 尝试从JSON中读取对象

            BankReceipt bankReceipt = mapper.readValue(record, BankReceipt.class);

            service.deal(bankReceipt);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}