package com.yih.trans.service;

import com.yih.trans.entity.MallRecord;
import com.yih.trans.entity.BankReceipt;
import com.yih.trans.entity.MallReceipt;
import com.yih.trans.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
            receipt.get().setStatus("finish");
            mallReceiptRepo.save(receipt.get());
        }
    }
}