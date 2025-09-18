package com.example.tracker;

import com.example.tracker.model.Transaction;
import com.example.tracker.repository.TransactionRepository;
import com.example.tracker.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TransactionServiceTest {

    @Test
    void testBalanceCalculation() {
        TransactionRepository repo = Mockito.mock(TransactionRepository.class);
        Mockito.when(repo.findAll()).thenReturn(List.of(
                new Transaction(null, "income", "salary", 1000, null),
                new Transaction(null, "expense", "food", 200, null)
        ));

        TransactionService service = new TransactionService(repo);


        assertEquals(800, service.getBalance());
    }
}
