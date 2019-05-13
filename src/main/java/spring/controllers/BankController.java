package spring.controllers;

import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;
import spring.DAO.Model.Bank;
import spring.DAO.Model.Converters.BankConverter;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import spring.DAO.Service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class BankController {

	@Autowired
	public BankService bankService;

	//@RequestParam - параметр который обязательно должно заполниться

	@RequestMapping(value="/addBank", method=RequestMethod.GET, produces = {"application/json; charset=utf-8;"})
	@ResponseBody
	public String addBank(@RequestParam String text) {
		Bank newBank = new Bank(text);
		bankService.addBank(newBank);
        return new Gson().toJson(newBank);
    }

	@RequestMapping(value="/editBank", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
	@ResponseBody
	public String editBank(@RequestBody Bank bank) {
		bankService.editBank(bank);
		return new Gson().toJson(bank);
	}

	@RequestMapping(value="/deleteBankAll", method=RequestMethod.POST,produces = {"application/json; charset=utf-8;"})//,consumes = MediaType.APPLICATION_JSON_VALUE)//produces = {"application/json; charset=utf-8;"})
	@ResponseBody
	public void deleteBankAll(@RequestBody List<String> list) {
		List<Long> longList = new ArrayList<>();
		for(String s : list) longList.add(Long.valueOf(s));
		//longList.forEach(System.out::println);
		bankService.deleteSelected(longList);
	}

	@RequestMapping(value="/loadBank", method=RequestMethod.GET, produces = {"application/json; charset=utf-8;"})
	@ResponseBody
	public String loadBank() {
		List<Bank> list = bankService.getAll();
//		list.forEach(System.out::println);
		Gson gson = new GsonBuilder().registerTypeAdapter(Bank.class, new BankConverter()).create();
		return gson.toJson(list);
	}

	@RequestMapping(value="/getBank", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
	@ResponseBody
	public String getBank(@RequestBody Long id) {
		Bank bank = bankService.getById(id);
		Gson gson = new GsonBuilder().registerTypeAdapter(Bank.class, new BankConverter()).create();
		return gson.toJson(bank);
	}

	 @RequestMapping(value = "/bank", method = RequestMethod.GET)
	 public String bank(Locale locale, Model model) {
	  return "bank";
	 }

//	@RequestMapping(value = "/bankModalAdd", method = RequestMethod.GET)
//	public String bankModalAdd(Locale locale, Model model) {
//	    System.out.println("Done");
//
//	    return "bankModalAdd";
//	}
	
}

