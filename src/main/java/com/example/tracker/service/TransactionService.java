package com.example.tracker.service;

import com.example.tracker.model.Transaction;
import com.example.tracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }


    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction addTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setType(updated.getType());
                    existing.setCategory(updated.getCategory());
                    existing.setAmount(updated.getAmount());
                    existing.setDate(updated.getDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }

    public double getBalance() {
        List<Transaction> transactions = repository.findAll();
        double income = transactions.stream()
                .filter(t -> "income".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
        double expense = transactions.stream()
                .filter(t -> "expense".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
        return income - expense;
    }


}
