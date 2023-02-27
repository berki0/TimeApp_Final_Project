package com.example.TimeApp.Controller;

import com.example.TimeApp.Entities.Customer;
import com.example.TimeApp.Entities.User;
import com.example.TimeApp.Repository.CustomerRepository;
import com.example.TimeApp.Repository.ProtocolRepository;
import com.example.TimeApp.Repository.UserRepository;
import com.example.TimeApp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/protocols")
public class DailyProtocol {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ProtocolRepository protocolRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping()
    public String list(Model model) {
        List<com.example.TimeApp.Entities.DailyProtocol> allProtocols = protocolRepository.findAll();
        model.addAttribute("AllProtocols", allProtocols);
        return "Customers/DailyProtocol/allProtocols";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Customer> customer=customerRepository.findAll();
        List<User> user=userRepository.findAll();
        model.addAttribute("customers",customer);
        model.addAttribute("protocol", new com.example.TimeApp.Entities.DailyProtocol());
        return "Customers/DailyProtocol/add";
    }

    @PostMapping("/submit")
    public ModelAndView submit(com.example.TimeApp.Entities.DailyProtocol dailyProtocol, BindingResult bindingResult) {
        dailyProtocol.setUser(authenticationService.returnUserWitchLogin());
        dailyProtocol.setLocalDate(LocalDate.now());
        if (bindingResult.hasErrors()) {
            return new ModelAndView("Customers/DailyProtocol/add");
        } else {
            protocolRepository.save(dailyProtocol);
        }
        return new ModelAndView("redirect:/protocols");
    }
    @PostMapping("/delete/{protocolsId}")
    public ModelAndView deleteActors(@PathVariable(name= "protocolsId" )Long protocolId){
        protocolRepository.deleteById(protocolId);
        return new ModelAndView("redirect:/protocols");
    }
    @GetMapping("/edit/{protocolsId}")
    public String editCustomer(@PathVariable(name = "protocolsId")Long protocolsId,Model model){
        Optional<com.example.TimeApp.Entities.DailyProtocol> optionalProtocol=protocolRepository.findById(protocolsId);
        if (optionalProtocol.isPresent()){
            List<Customer> allCustomers=customerRepository.findAll();
            model.addAttribute("customers",allCustomers);
            model.addAttribute("protocol",optionalProtocol);
        }else {
            model.addAttribute("protocol", "Error");
        }
        return "Customers/DailyProtocol/edit";
    }
}
