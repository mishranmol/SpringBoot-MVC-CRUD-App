package com.anmol.week1.IntroductionToSpringBoot.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employees") 
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor

    
public class EmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long Id;

    private String name;


    private String email;


    private int age;


    private String role;


    private Double salary;


    private LocalDate dataOfJoining;


    //Indicates whether the employee is currently working or not
    private boolean isActive;



    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDataOfJoining() {
        return dataOfJoining;
    }

    public void setDataOfJoining(LocalDate dataOfJoining) {
        this.dataOfJoining = dataOfJoining;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    

}
