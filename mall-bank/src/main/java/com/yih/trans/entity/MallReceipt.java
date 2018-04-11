package com.yih.trans.entity;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;

@Data
@Entity
public class MallReceipt {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long money;
    @Column
    private String status;

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try
        {
            // 尝试从JSON中读取对象

            return mapper.writeValueAsString(this);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}