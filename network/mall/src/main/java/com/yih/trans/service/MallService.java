package com.yih.trans.service;

import com.yih.trans.entity.MallRecord;
import com.yih.trans.entity.BankReceipt;
import com.yih.trans.entity.MallReceipt;
import com.yih.trans.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class MallService {
    @Autowired
    MallRepo mallRepo;
    @Autowired
    MallReceiptRepo mallReceiptRepo;

    @Transactional
    public void add(Long userId, Long money) {
        MallRecord mallRecord = new MallRecord();
        mallRecord.setUserId(userId);
        mallRecord.setMoney(money);

        MallReceipt mallReceipt = new MallReceipt();
        mallReceipt.setUserId(userId);
        mallReceipt.setMoney(money);
        mallReceipt.setStatus("start");

        mallRepo.save(mallRecord);
        mallReceiptRepo.save(mallReceipt);
    }

    @Transactional
    public void deal(BankReceipt bankReceipt) {
        Long id = bankReceipt.getReceiptId();
        Optional<MallReceipt> receipt = mallReceiptRepo.findById(id);
        if (receipt.isPresent()) {
            log.info("remove {}", receipt.get().getId());
            receipt.get().setStatus("finish");
            mallReceiptRepo.save(receipt.get());
        }
    }

    @Transactional//(rollbackOn = Exception.class)
    public void test()  {

        Long userId = 1L;
        Long money = 100L;
        MallRecord mallRecord = new MallRecord();
        mallRecord.setUserId(userId);
        mallRecord.setMoney(money);

        MallReceipt mallReceipt = new MallReceipt();
        mallReceipt.setUserId(userId);
        mallReceipt.setMoney(money);
        mallReceipt.setStatus("start");

        mallRepo.save(mallRecord);

        throw new RuntimeException("htest");
    }
}
