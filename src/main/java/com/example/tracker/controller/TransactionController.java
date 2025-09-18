package com.example.tracker.controller;


import com.example.tracker.model.Transaction;
import com.example.tracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    //  Get all transactions
    @GetMapping("/all")
    public List<Transaction> getAll() {
        return service.getAll();
    }

    // Get balance
    @GetMapping("/balance")
    public double getBalance() {
        return service.getBalance();
    }

    // Add a transaction with validation
    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }
    // Update transaction
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction t) {
        return service.updateTransaction(id, t);
    }


    // Delete transaction
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        service.deleteTransaction(id);
    }

    // Handle validation errors
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
