package spring.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.DAO.Model.BankAccount;
import spring.DAO.Model.Converters.BankAccountConverter;
import spring.DAO.Service.BankAccountService;

import java.util.List;
import java.util.Locale;

@Controller
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping(value = "/bankAccount", method = RequestMethod.GET)
    public String client(Locale locale, Model model) {
        return "bankAccount";
    }

    @RequestMapping(value="/addBankAccount", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String addBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addBankAccount(bankAccount);
        Gson gson = new GsonBuilder().registerTypeAdapter(BankAccount.class, new BankAccountConverter()).create();
        return gson.toJson(bankAccount);
    }

    @RequestMapping(value="/deleteBankAccountAll", method=RequestMethod.POST,produces = {"application/json; charset=utf-8;"})//,consumes = MediaType.APPLICATION_JSON_VALUE)//produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String deleteBankAccountAll(@RequestBody List<BankAccount> listBankAccount) {
        //listBank.forEach(System.out::println);
        bankAccountService.deleteSelected(listBankAccount);
        return new Gson().toJson(listBankAccount);
    }

    @RequestMapping(value="/editBankAccount", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String editBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.editBankAccount(bankAccount);
        Gson gson = new GsonBuilder().registerTypeAdapter(BankAccount.class, new BankAccountConverter()).create();
        return gson.toJson(bankAccount);
    }

    @RequestMapping(value="/loadBankAccount", method=RequestMethod.GET, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String loadBankAccount() {
        List<BankAccount> list = bankAccountService.getAll();
        Gson gson = new GsonBuilder().registerTypeAdapter(BankAccount.class, new BankAccountConverter()).create();
        return gson.toJson(list);
    }

}
