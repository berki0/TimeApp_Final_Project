package com.example.TimeApp.Service;

import com.example.TimeApp.Entities.User;
import com.example.TimeApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
   @Autowired
   private UserRepository userRepository;
    public User returnUserWitchLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<User>allUsers=userRepository.findAll();
        for (int i = 0; i <allUsers.toArray().length ; i++) {
            if ( allUsers.get(i).getUsername().equalsIgnoreCase(authentication.getName())){
                return allUsers.get(i);
            }
        }
        return  new User() ;
    }
    public boolean uniqueUserName(String username){
        boolean ok;
        List<User>allUsers=userRepository.findAll();
        for (int i = 0; i < allUsers.toArray().length; i++) {
            if (allUsers.get(i).getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }
    public List<User> returnUsersRoleUser(){
       List<User>allUser= userRepository.findAll();
        List<User>userRoleUser=new ArrayList<>();
        for (int i = 0; i < allUser.toArray().length ; i++) {
            if (allUser.get(i).getRole().toString().equalsIgnoreCase("USER")){
                userRoleUser.add(allUser.get(i));
            }
        }
        return userRoleUser;
    }


}
