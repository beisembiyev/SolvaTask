package com.example.solva.Models.DTO;

import com.example.solva.Models.Entity.Limit;
import com.example.solva.Models.Entity.Transaction;
import com.example.solva.Models.Enums.SpendingsCategory;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private long id;
    private int accountNum;
    private String firstName;
    private String lastName;
    private String email;
    private double balance;
    private Map<SpendingsCategory,Limit> limits;
    private List<Transaction> transactions;
}
