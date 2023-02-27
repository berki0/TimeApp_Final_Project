package com.example.TimeApp.Controller;

import com.example.TimeApp.Entities.Customer;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class User {
//    @Autowired
//    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String list(Model model) {
        List<com.example.TimeApp.Entities.User> allUsers = userRepository.findAll();
        model.addAttribute("users", allUsers);
        return "Customers/User/AllUsers";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new com.example.TimeApp.Entities.User());
        System.out.println("TEST");
        return "Customers/User/addUser";
    }

    @PostMapping("/submit")
    public ModelAndView submit(com.example.TimeApp.Entities.User user, BindingResult bindingResult) throws IOException {
        user.setPasswordString(user.getPassword());
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/add");
        } else
            user.setEnabled(false);
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
}
