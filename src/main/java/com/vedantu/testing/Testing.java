package com.vedantu.testing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testing {

    @RequestMapping("/testing")
    public String testing(){
        return "testing works!!!";
    }
}
