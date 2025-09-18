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

    public List<Transaction> getAll(){
        return repository.findAll();
    }
    public Transaction addTransaction(Transaction t) {
        if (t.getType() == null) {
            throw new IllegalArgumentException("Transaction type cannot be null");
        }

        String normalizedType = t.getType().toLowerCase();

        if (!normalizedType.equals("income") && !normalizedType.equals("expense")) {
            throw new IllegalArgumentException("Invalid transaction type: " + t.getType() + ". Must be 'income' or 'expense'.");
        }

        t.setType(normalizedType); // always save lowercase
        return repository.save(t);
    }

    public Transaction updateTransaction(Long id, Transaction t) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setType(t.getType());
                    existing.setCategory(t.getCategory());
                    existing.setAmount(t.getAmount());
                    existing.setDate(t.getDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));
    }


    public void deleteTransaction(Long id){
        repository.deleteById(id);
    }

    public double getBalance() {
        return repository.findAll().stream()
                .mapToDouble(t ->
                        t.getType() != null && t.getType().equalsIgnoreCase("income")
                                ? t.getAmount()
                                : -t.getAmount()
                )
                .sum();
    }


}
