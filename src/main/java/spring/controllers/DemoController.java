package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.DAO.Model.Bank;

@Controller
public class DemoController {

	//@RequestMapping(path = "/demo", method = RequestMethod.GET)

//	@GetMapping(path = "/demo")
//	public ModelAndView showDemoPage(Model model, @RequestParam("user") String username) {
//		ModelAndView modelAndView = new ModelAndView();
//		//System.out.println(username);
//		Bank newBank = new Bank(username);
//		//model.addAttribute("message", "KEK1");
//		modelAndView.addObject("currentUserFirstName", newBank.getName());
//		//modelAndView.addObject("currentUserLastName", user.lastname);
//		//modelAndView.addObject("currentUser", user);
//		modelAndView.setViewName("demo");
//		return modelAndView;
//	}

	@RequestMapping(value = "/demoModal", method = RequestMethod.GET)
	public String demoModal(Model model) {
		return "demoModal";
	}

	
	@PostMapping(path = "/registration")
	public ModelAndView registration(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(firstname);
		System.out.println(lastname);
		modelAndView.addObject("firstname", firstname);
		modelAndView.addObject("lastname", lastname);
		modelAndView.setViewName("registration");
		return modelAndView;	
	}
	
	@GetMapping(path = "/registration")
	public String showPageRegistration() {
		return "registration";		
	}
	
	@GetMapping(path = "/pageJS")
	public String showPageJS() {
		return "pageJS";		
	}
	
	
	@GetMapping(path = "/demo1")
	public String showDemoPage(Model model) {
		//ModelAndView modelAndView = new ModelAndView();
		Bank bank = new Bank("Петрик");
		model.addAttribute("currentUserFirstName", bank);
		System.out.println("123");
		//model.addAttribute("currentUserLastName", user.lastname);
		//modelAndView.addObject("currentUserFirstName", user.firstname);
		//modelAndView.addObject("currentUserLastName", user.lastname);
		//modelAndView.setViewName("demo");
		return "demo1";
	}
	
}
