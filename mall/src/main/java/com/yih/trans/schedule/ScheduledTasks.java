package com.yih.trans.schedule;

import com.yih.trans.entity.BankReceipt;
import com.yih.trans.entity.MallReceipt;
import com.yih.trans.repo.MallReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ScheduledTasks {

    @Autowired
    KafkaTemplate template;

    @Autowired
    MallReceiptRepo repo;

    @Scheduled(fixedRate = 5000)
    public void sendMallReceipt() {
        List<MallReceipt> all = repo.findAll();
        for (MallReceipt record : all) {
            if (record.getStatus().equalsIgnoreCase("start")) {
                template.send("bank", record.toJson());
            }
        }
    }

}