package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/home");
        List<Customer> customerList = customerService.findAll();
        modelAndView.addObject("customers", customerList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCustomer() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customers", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/update/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("customers", customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes attributes) {
       Customer customer = customerService.findById(id);
        customerService.delete(customer.getId());
        attributes.addFlashAttribute("message", "Removed customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id , Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }
}
