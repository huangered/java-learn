package com.yih.trans.schedule;

import com.yih.trans.entity.BankReceipt;
import com.yih.trans.repo.BankReceiptRepo;
import com.yih.trans.entity.MallReceipt;
import com.yih.trans.repo.MallReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ScheduledTasks {

    @Autowired
    KafkaTemplate template;

    @Autowired
    MallReceiptRepo repo;

    @Autowired
    BankReceiptRepo bankRepo;

    @Scheduled(fixedRate = 5000)
    public void sendMallReceipt() {
        List<MallReceipt> all = repo.findAll();
        for (MallReceipt record : all) {
            if (record.getStatus().equalsIgnoreCase("start")) {
                template.send("bank", record.toJson());
            }
        }
    }

    @Scheduled(fixedRate = 5000)
    public void sendBankReceipt() {
        List<BankReceipt> all = bankRepo.findAll();
        for (BankReceipt record : all) {
            if (record.getStatus().equalsIgnoreCase("start")) {
                ListenableFuture<SendResult> d = template.send("mall", record.toJson());
                try {
                    d.get();
                    record.setStatus("finish");
                    bankRepo.save(record);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}