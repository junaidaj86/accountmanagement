package com.zinu.account_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1/driver")
public class DriverController {
 
    @PostMapping
    public void createDriver(){
        System.out.println("creating driver");
    }

    @GetMapping
    public void getDriver(){
        System.out.println("getting driver");
    }
}
