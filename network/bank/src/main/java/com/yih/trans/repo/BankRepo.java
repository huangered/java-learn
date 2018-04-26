package com.yih.trans.repo;

import com.yih.trans.entity.BankRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<BankRecord, Long> {
}
