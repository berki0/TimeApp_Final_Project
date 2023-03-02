package com.example.TimeApp.Entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class DailyProtocol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "customer_id")
   @NotNull
    private Customer customer;
    @NotNull
    @Min(1)
    private int workTime;
    @NotBlank
    @Size(min = 20,max = 550)
    private String description;

    private LocalDate localDate;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DailyProtocol{" +
                " user=" + user.getId() +"Username = " +user.getUsername()+"Full Name = " + user.getFullName()+
                ", customer id =" + customer.getId() +"Company Name = "+customer.getName()+"Project Name = "+customer.getNameProject()+
                ", workTime=" + workTime +
                ", description='" + description + '\'' +
                '}';
    }
}
