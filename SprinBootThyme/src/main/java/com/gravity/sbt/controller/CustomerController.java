package com.gravity.sbt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gravity.sbt.entity.Customer;
import com.gravity.sbt.service.CustomerService;



@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/")
	public String showCustomerList(Model model)
	{
		List<Customer> customers=customerService.showCustomerList();
		model.addAttribute("customers",customers);
		return "customer-list";
	}
	
	
	@RequestMapping("/register")
	public String registerCustomer(Model model)
	{
		Customer c1=new Customer();
		model.addAttribute("customer",c1);
		return "customer-registration-form";
		
	}
	
	@PostMapping("/addcustomer")
	public String addCustomer(@ModelAttribute Customer customer)
	{
		customerService.addCustomer(customer);
		return "redirect:/";
		
	}
	
	@RequestMapping("/deletecustomer/{id}")
	public String deleteCustomer(@PathVariable("id") int cno)
	{
		customerService.deleteCustomer(cno);
		return "redirect:/";
		
	}
	
	@RequestMapping("/updatecustomerform/{id}")
	public String updateCustomerform(@PathVariable("id") int cno, Model model)
	{
		Customer customer=customerService.getCustomer(cno);
		model.addAttribute("customer",customer);
		return "update-customer-form";
	}
	
	@PostMapping("/updatecustomer/{id}")
	public String updateCustomer(@PathVariable("id") int cno, @ModelAttribute Customer customer)
	{
		customerService.updateCustomer(cno,customer);
		return "redirect:/";
	}
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			    "you do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
