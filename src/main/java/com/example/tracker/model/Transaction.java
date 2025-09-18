package com.example.tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;       // income or expens
    private String category;   // food, transport, bills
    private double amount;
    private LocalDate date;

    //constructors
    public Transaction(){}

    public Transaction(Long id, String type, String category, double amount, LocalDate date) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }


    //Getters
    public Long getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public String getCategory(){
        return category;
    }

    public double getAmount(){
       return amount;
    }

    public LocalDate getDate(){
        return date;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setType(String type){
        this.type = type;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

}
