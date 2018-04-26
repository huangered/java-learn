package com.yih.trans.repo;

import com.yih.trans.entity.BankReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankReceiptRepo extends JpaRepository<BankReceipt, Long> {
}
