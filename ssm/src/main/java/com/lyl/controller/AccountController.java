package com.lyl.controller;

import com.lyl.domain.Account;
import com.lyl.service.AccountService;
import com.lyl.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户controller
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource(name="accountService")
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Account> accounts = accountService.findAll();
        for (Account account:accounts)
            System.out.println(accounts);
        model.addAttribute("accounts",accounts);
        return "success";
    }

}
