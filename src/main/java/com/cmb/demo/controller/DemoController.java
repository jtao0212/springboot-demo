package com.cmb.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value = "/zeroException")
    public int zeroException() {
        return 100 / 0;
    }
}
