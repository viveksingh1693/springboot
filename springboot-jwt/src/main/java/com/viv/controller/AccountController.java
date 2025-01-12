package com.viv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        log.info("Inside get Account");
        return "Here are the account details";
    }

}
