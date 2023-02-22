package com.example.TimeApp.Controller;

import com.example.TimeApp.Entities.Customer;
import com.example.TimeApp.Entities.User;
import com.example.TimeApp.Repository.CustomerRepository;
import com.example.TimeApp.Repository.ProtocolRepository;
import com.example.TimeApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/protocols")
public class DailyProtocol {
    @Autowired
    private ProtocolRepository protocolRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<com.example.TimeApp.Entities.DailyProtocol> allProtocols = protocolRepository.findAll();
        model.addAttribute("AllProtocols", allProtocols);
        return "Customers/DailyProtocol/allProtocols";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Customer> customers=customerRepository.findAll();
        List<User>users=userRepository.findAll();
        model.addAttribute("customers",customers);//????
        model.addAttribute("user",users);//autorization
        model.addAttribute("protocol", new com.example.TimeApp.Entities.DailyProtocol());
        return "Customers/DailyProtocol/add";
    }

    @PostMapping("/submit")
    public ModelAndView submit(com.example.TimeApp.Entities.DailyProtocol dailyProtocol, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("Customers/DailyProtocol/add");
        } else {
            protocolRepository.save(dailyProtocol);
        }
        return new ModelAndView("redirect:/protocols/list");
    }
}
