package com.example.TimeApp.Service;

import com.example.TimeApp.Entities.DailyProtocol;
import com.example.TimeApp.Repository.ProtocolRepository;
import com.example.TimeApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProtocolsService {
    @Autowired
    private ProtocolRepository protocolRepository;
    @Autowired
    private UserRepository userRepository;

    public List<DailyProtocol> searchByUsername(String username) {
        List<DailyProtocol> allProtocols = protocolRepository.findAll();
        List<DailyProtocol> myDailyProtocols = new ArrayList<>();
        for (int i = 0; i < allProtocols.toArray().length; i++) {
            if (allProtocols.get(i).getUser().getUsername().equalsIgnoreCase(username)) {
                myDailyProtocols.add(allProtocols.get(i));
            }
        }
        return myDailyProtocols;
    }

    public List<DailyProtocol> searchById(Long userid) {
        List<DailyProtocol> allProtocols = protocolRepository.findAll();
        List<DailyProtocol> userDailyProtocols = new ArrayList<>();
        for (int i = 0; i < allProtocols.size(); i++) {
            if (allProtocols.get(i).getUser().getId().equals(userid)) {
                userDailyProtocols.add(allProtocols.get(i));
            }
        }
        return userDailyProtocols;
    }

}
