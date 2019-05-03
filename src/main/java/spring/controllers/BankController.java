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
	public String deleteBankAll(@RequestBody List<Bank> listBank) {
		//listBank.forEach(System.out::println);
		bankService.deleteSelected(listBank);
		return new Gson().toJson(listBank);
	}

	@RequestMapping(value="/loadBank", method=RequestMethod.GET, produces = {"application/json; charset=utf-8;"})
	@ResponseBody
	public String loadBank() {
		List<Bank> list = bankService.getAll();
//		list.forEach(System.out::println);
//		for(Bank bank: list){
//			System.out.println(bank.getId());
//			System.out.println(bank.getName());
//			System.out.println(bank.getWorkers());
//		}
		Gson gson = new GsonBuilder().registerTypeAdapter(Bank.class, new BankConverter()).create();
		return gson.toJson(list);
	}

//	@RequestMapping(value="/searchBank", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
//	@ResponseBody
//	public String searchBank(@RequestBody String name) {
//		System.out.println(name);
//		List<Bank> list = bankService.searchBank(name);
//		list.forEach(System.out::println);
//		Gson gson = new GsonBuilder().registerTypeAdapter(Bank.class, new BankConverter()).create();
//		return gson.toJson(list);
//	}

	 @RequestMapping(value = "/bank", method = RequestMethod.GET)
	 public String bank(Locale locale, Model model) {
	  return "bank";
	 }
	
}

