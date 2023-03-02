package com.example.TimeApp.Controller;

import com.example.TimeApp.Entities.Customer;
import com.example.TimeApp.Repository.CustomerRepository;
import com.example.TimeApp.Repository.ProtocolRepository;
import com.example.TimeApp.Repository.UserRepository;
import com.example.TimeApp.Service.ProtocolsService;
import com.example.TimeApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/protocols")
public class DailyProtocol {

    @Autowired
    private UserService userService;
    @Autowired
    private ProtocolRepository protocolRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProtocolsService protocolsService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String list(Model model) {
        List<com.example.TimeApp.Entities.DailyProtocol> allProtocols = protocolRepository.findAll();
        model.addAttribute("AllProtocols", allProtocols);
        return "Customers/DailyProtocol/allProtocols";
    }

    @GetMapping("/add")
    public String add(Model model, com.example.TimeApp.Entities.DailyProtocol dailyProtocol) {
        List<Customer> customer=customerRepository.findAll();
        model.addAttribute("customers",customer);
        return "Customers/DailyProtocol/add";
    }

    @PostMapping("/submit")
    public ModelAndView submit(@Valid com.example.TimeApp.Entities.DailyProtocol dailyProtocol, BindingResult bindingResult,Model model) {
        dailyProtocol.setUser(userService.returnUserWitchLogin());
        dailyProtocol.setLocalDate(LocalDate.now());
        if (bindingResult.hasErrors()) {
            List<Customer> customer=customerRepository.findAll();
            model.addAttribute("customers" ,customer);
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
    @GetMapping("/myList")
    public String listMyDailyProtocols(Model model) {
       model.addAttribute("AllProtocols",  protocolsService.searchByUsername(userService.returnUserWitchLogin().getUsername()));
        return "Customers/DailyProtocol/myList";
    }

    @GetMapping("/seeUserProtocols/{userId}")
    public String seeUserProtocols(@PathVariable(name = "userId")Long userId,Model model){
       model.addAttribute( "AllProtocols",protocolsService.searchById(userId));
        return "Customers/DailyProtocol/allProtocols";
    }

}
