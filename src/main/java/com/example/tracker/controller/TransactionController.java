package com.example.tracker.controller;


import com.example.tracker.model.Transaction;
import com.example.tracker.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction t) {
        return service.updateTransaction(id, t);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        service.deleteTransaction(id);
    }

    // balance endpoint
    @GetMapping("/balance")
    public Map<String, Double> getBalance() {
        double balance = service.getBalance();
        return Collections.singletonMap("balance", balance); // returns { "balance": 12345.0 }
    }


}
