package com.example.TimeApp.Controller;

import com.example.TimeApp.Repository.UserRepository;
import com.example.TimeApp.Service.ProtocolsService;
import com.example.TimeApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class User {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProtocolsService protocolsService;

    @GetMapping()
    public String list(Model model) {
        List<com.example.TimeApp.Entities.User> allUsers = userRepository.findAll();
        model.addAttribute("users", allUsers);
        return "Customers/User/AllUsers";
    }

    @GetMapping("/add")
    public String add(com.example.TimeApp.Entities.User user) {
        return "Customers/User/addUser";
    }

    @PostMapping("/submit")
    public ModelAndView submit(@Valid com.example.TimeApp.Entities.User user, BindingResult bindingResult,Model model) throws IOException {
        System.out.println(user.toString());
        if (bindingResult.hasErrors()||userService.uniqueUserName(user.getUsername())) {
            model.addAttribute("user",user);
            return new ModelAndView("Customers/User/addUser");
        } else{
            BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        return new ModelAndView("redirect:/user");
    }
    @PostMapping("/editSubmit")
    public ModelAndView EditSubmit(@Valid com.example.TimeApp.Entities.User user, BindingResult bindingResult,Model model) throws IOException {
        if (bindingResult.hasErrors()||userService.uniqueUserName(user.getUsername())) {
            model.addAttribute("user",user);
            return new ModelAndView("Customers/User/editUser");
        } else
            userRepository.save(user);
        return new ModelAndView("redirect:/user");
    }

    @PostMapping("/delete/{userId}")
    public ModelAndView deleteActors(@PathVariable(name = "userId") Long userId) {
        userRepository.deleteById(userId);
        return new ModelAndView("redirect:/user");
    }

    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable(name = "userId") Long userId, Model model) {
        Optional<com.example.TimeApp.Entities.User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser);
        } else {
            model.addAttribute("user", "Error");
        }
        return "Customers/User/editUser";
    }

    @GetMapping("/protocolsByUserAdd")
    public String protocolsByUser( com.example.TimeApp.Entities.DailyProtocol dailyProtocol,Model model) {
        List<com.example.TimeApp.Entities.User>user=userService.returnUsersRoleUser();
        model.addAttribute("users",user);
        return "Customers/DailyProtocol/searchByUser";
    }
    @PostMapping("/protocolsByUserSubmit")
    public String protocols( com.example.TimeApp.Entities.DailyProtocol dailyProtocol, Model model){

        if (dailyProtocol.getUser()==null){
            List<com.example.TimeApp.Entities.User>user=userService.returnUsersRoleUser();
            model.addAttribute("users",user);
           return "Customers/DailyProtocol/searchByUser";
        }
        List<com.example.TimeApp.Entities.DailyProtocol>AllProtocols= protocolsService.searchById(dailyProtocol.getUser().getId());
        model.addAttribute("AllProtocols",AllProtocols);
       return "Customers/DailyProtocol/myList";
    }

}
