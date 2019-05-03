package spring.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.DAO.Model.Bank;
import spring.DAO.Model.Converters.BankConverter;
import spring.DAO.Service.BankService;

import java.util.List;

@RestController
@RequestMapping(value = "/restBank")
public class RestBankController {

    @Autowired
    public BankService bankService;

    @PostMapping(value="/searchBank",produces = {"application/json; charset=utf-8;"})
    public String searchBank(@RequestBody Bank bank) {
        System.out.println(bank);
        List<Bank> list = bankService.searchBank(bank.getName());
        list.forEach(System.out::println);
        Gson gson = new GsonBuilder().registerTypeAdapter(Bank.class, new BankConverter()).create();
        return gson.toJson(list);
    }
}
