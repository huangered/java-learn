package com.yih.trans.service;

import com.yih.trans.entity.BankRecord;
import com.yih.trans.repo.BankReceiptRepo;
import com.yih.trans.repo.BankRepo;
import com.yih.trans.entity.BankReceipt;
import com.yih.trans.entity.MallReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BankService {
    @Autowired
    BankRepo bankRepo;

    @Autowired
    BankReceiptRepo bankReceiptRepo;

    @Transactional
    public void add(Long userId, Long money) {

    }

    @Transactional
    public void deal(MallReceipt mallReceipt) {
        BankRecord bankRecord = new BankRecord();
        bankRecord.setUserId(mallReceipt.getUserId());
        bankRecord.setMoney(mallReceipt.getMoney());


        BankReceipt bankReceipt = new BankReceipt();
        bankReceipt.setMoney(mallReceipt.getMoney());
        bankReceipt.setUserId(mallReceipt.getUserId());
        bankReceipt.setStatus("start");
        bankReceipt.setReceiptId(mallReceipt.getId());
        bankRepo.save(bankRecord);
        bankReceiptRepo.save(bankReceipt);

    }
}
