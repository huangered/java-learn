package com.yih.trans.schedule;

import com.yih.trans.entity.BankReceipt;
import com.yih.trans.repo.BankReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    KafkaTemplate template;

    @Autowired
    BankReceiptRepo bankRepo;

    @Scheduled(fixedRate = 5000)
    public void sendBankReceipt() {
        List<BankReceipt> all = bankRepo.findAll();
        for (BankReceipt record : all) {
            if (record.getStatus().equalsIgnoreCase("start")) {
                ListenableFuture<SendResult> d = template.send("mall", record.toJson());

                d.addCallback(result -> {
                    record.setStatus("finish");
                    bankRepo.save(record);
                }, ex -> {

                });

            }
        }
    }
}