package com.example.solva.Controllers;


import com.example.solva.Models.DTO.TransactionDTO;
import com.example.solva.Services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/transactions")
@Tag(name = "Transactions", description = "Transaction API")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/all")
    @Operation(summary = "Method to get all transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Method to get transaction by id")
    public ResponseEntity<TransactionDTO> getTransaction(
            @Parameter(description = "Transaction id") @PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @PostMapping()
    @Operation(summary = "Method to create transaction")
    public ResponseEntity<TransactionDTO> createTransaction(
            @Parameter(description = "Request body of transaction") @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionDTO));
    }
    @PutMapping()
    @Operation(summary = "Method to update transaction by id")
    public ResponseEntity<TransactionDTO> updateTransaction(
            @Parameter(description = "Request body of transaction") @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.updateTransaction(transactionDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Method to delete transaction by id")
    public ResponseEntity<?> deleteTransaction(
            @Parameter(description = "Transaction id") @PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
