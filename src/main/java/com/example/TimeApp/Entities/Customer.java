package com.example.TimeApp.Entities;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotBlank
    @Size(min = 2,max = 50)
    private String nameProject;
    @NotBlank
    @Size(min = 2,max = 50)
    private String name;
    @NotBlank
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String contract;

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", Name Project='" + nameProject + '\'' +
                ", Company Name='" + name + '\'' +
                ", Contract End Date='" + contract + '\'' +
                '}';
    }
}
