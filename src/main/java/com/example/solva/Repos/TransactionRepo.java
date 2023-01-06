package com.example.solva.Repos;

import com.example.solva.Models.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Transaction findTransactionById(long id);
}
