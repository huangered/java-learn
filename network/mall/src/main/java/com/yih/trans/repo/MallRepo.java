package com.yih.trans.repo;

import com.yih.trans.entity.MallRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRepo extends JpaRepository<MallRecord, Long> {
}
