package com.example.TimeApp.Service;

import com.example.TimeApp.Entities.DailyProtocol;
import com.example.TimeApp.Repository.ProtocolRepository;
import com.example.TimeApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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

    public List<DailyProtocol> returnTotalWorkTime(List<DailyProtocol> list, String userUserName) {
        List<DailyProtocol> userDailyProtocols = new ArrayList<>();
        int sumWorkTime = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().getUsername().equalsIgnoreCase(userUserName)) {
                userDailyProtocols.add(list.get(i));
                sumWorkTime = userDailyProtocols.get(i).getWorkTime() + sumWorkTime;
            }
        }
        DailyProtocol dailyProtocol = new DailyProtocol();
        dailyProtocol.setUser(userDailyProtocols.get(0).getUser());
        dailyProtocol.setLocalDate(LocalDate.now());
        dailyProtocol.setWorkTime(sumWorkTime);
        dailyProtocol.setDescription("This is total work time in minutes from all protocol = " + sumWorkTime + " minutes for Employee username= " + userDailyProtocols.get(0).getUser().getUsername() + ", Full Name= " + userDailyProtocols.get(0).getUser().getFullName());
        userDailyProtocols.add(dailyProtocol);
        return userDailyProtocols;
    }

    public List<DailyProtocol> returnListByDate(String[] dates) throws ParseException {
        List<DailyProtocol> allList = protocolRepository.findAll();
        List<DailyProtocol> listByDate = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(dates[0]);
        Date endDate = sdf.parse(dates[1]);
        Date requiredDate;
        if (startDate.before(endDate)) {
            for (int i = 0; i < allList.size(); i++) {
                String dateLocal = String.valueOf(allList.get(i).getLocalDate());
                requiredDate = sdf.parse(dateLocal);
                if (requiredDate.before(endDate) && requiredDate.after(startDate)) {
                    listByDate.add(allList.get(i));
                }
            }
        } else if (endDate.before(startDate)) {
            for (int i = allList.size()-1; i >= 0; i--) {
                String dateLocal = String.valueOf(allList.get(i).getLocalDate());
                requiredDate = sdf.parse(dateLocal);
                if (requiredDate.before(startDate) && requiredDate.after(endDate)) {
                    listByDate.add(allList.get(i));
                }
            }
        }
        return listByDate;
    }
}
