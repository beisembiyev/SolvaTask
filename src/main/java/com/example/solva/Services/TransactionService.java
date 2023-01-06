package com.example.solva.Services;

import com.example.solva.Models.DTO.LimitDTO;
import com.example.solva.Models.DTO.TransactionDTO;
import com.example.solva.Models.Entity.Limit;
import com.example.solva.Models.Entity.Transaction;
import com.example.solva.Repos.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;

    public TransactionDTO getTransaction(long id){
        Transaction transaction=findTransactionById(id);
        return TransactionDTO.builder()
                .id(transaction.getId())
                .account_from(transaction.getAccount_from())
                .account_to(transaction.getAccount_to())
                .currency_shortname(transaction.getCurrency_shortname())
                .sum(transaction.getSum())
                .expense_category(transaction.getExpense_category())
                .limit_exceeded(transaction.isLimit_exceeded())
                .datetime(transaction.getDatetime())
                .build();
    }

    public TransactionDTO updateTransaction(TransactionDTO transactionDTO){
        Transaction transaction=findTransactionById(transactionDTO.getId());
        transaction.setAccount_from(transactionDTO.getAccount_from());
        transaction.setAccount_to(transactionDTO.getAccount_to());
        transaction.setCurrency_shortname(transactionDTO.getCurrency_shortname());
        transaction.setSum(transactionDTO.getSum());
        transaction.setExpense_category(transactionDTO.getExpense_category());
        transaction.setLimit_exceeded(transactionDTO.isLimit_exceeded());
        transaction.setDatetime(transactionDTO.getDatetime());
        //limit.setDate(limitDTO.getDate());//need to change to LocalDateTime.now() in controller
        transactionRepo.save(transaction);
        return transactionDTO;
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
        Transaction transaction=Transaction.builder()
                //.id(transactionDTO.getId())
                .account_from(transactionDTO.getAccount_from())
                .account_to(transactionDTO.getAccount_to())
                .currency_shortname(transactionDTO.getCurrency_shortname())
                .sum(transactionDTO.getSum())
                .expense_category(transactionDTO.getExpense_category())
                .limit_exceeded(transactionDTO.isLimit_exceeded())
                .datetime(transactionDTO.getDatetime()).build();

        transactionRepo.save(transaction);
        return transactionDTO;
    }

    public void deleteTransaction(long id){
        Transaction transaction=findTransactionById(id);
        transactionRepo.delete(transaction);
    }

    public List<TransactionDTO> getAllTransactions(){
        List<Transaction> transactions=transactionRepo.findAll();
        List<TransactionDTO> transactionDTOS=new ArrayList<>();
        transactions.forEach(transaction -> transactionDTOS.add(TransactionDTO.builder()
                .id(transaction.getId())
                .account_from(transaction.getAccount_from())
                .account_to(transaction.getAccount_to())
                        .currency_shortname(transaction.getCurrency_shortname())
                        .sum(transaction.getSum())
                        .expense_category(transaction.getExpense_category())
                        .limit_exceeded(transaction.isLimit_exceeded())
                        .datetime(transaction.getDatetime()).build()
        ));
        return transactionDTOS;
    }

    public Transaction findTransactionById(long id){
        Transaction transaction=transactionRepo.findTransactionById(id);
        if (Objects.isNull(transaction)){
            throw new RuntimeException("limit not found!");
        }
        return transaction;
    }
}
