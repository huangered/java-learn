package com.yih.trans.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class BankRecord {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long money;
}