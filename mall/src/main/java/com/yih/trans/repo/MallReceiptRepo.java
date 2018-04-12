package com.yih.trans.repo;

import com.yih.trans.entity.MallReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallReceiptRepo extends JpaRepository<MallReceipt, Long> {
}
