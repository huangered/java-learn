package com.yih.trans.web;

import com.yih.trans.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCtl {
    @Autowired
    MallService service;

    @GetMapping("/test")
    public void test() {
        service.add(1L, 100L);
    }
}
