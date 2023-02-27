package com.example.TimeApp.Controller;

import com.example.TimeApp.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class Customer {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping()
    public String listAllCustomers(Model model) {
        List<com.example.TimeApp.Entities.Customer> allCustomers = customerRepository.findAll();
        model.addAttribute("allCustomers", allCustomers);
        return "Customers/AllCustomers";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new com.example.TimeApp.Entities.Customer());
        return "Customers/addCustomer";
    }

    @PostMapping("/submit")
    public ModelAndView submitCustomer(com.example.TimeApp.Entities.Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/customers/add");
        } else {
            customerRepository.save(customer);
        }
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/edit/{customerId}")
    public String editCustomer(@PathVariable(name = "customerId")Long customerId,Model model){
       Optional<com.example.TimeApp.Entities.Customer> optionalCustomer=customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()){
            model.addAttribute("customer",optionalCustomer);
        }else {
            model.addAttribute("customer", "Error");
        }
            return "Customers/editCustomer";
    }
    @PostMapping("/delete/{customerId}")
    public ModelAndView deleteActors(@PathVariable(name= "customerId" )Long customerId){
        customerRepository.deleteById(customerId);
        return new ModelAndView("redirect:/customers");
    }

}
