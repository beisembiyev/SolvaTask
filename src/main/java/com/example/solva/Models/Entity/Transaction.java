package com.example.solva.Models.Entity;

import com.example.solva.Models.Enums.Currency;
import com.example.solva.Models.Enums.SpendingsCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int account_from;
    private int account_to;
    private Currency currency_shortname;
    private double sum;
    private SpendingsCategory expense_category;
    private boolean limit_exceeded;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime datetime;

    public Transaction(int account_from, int account_to, Currency currency_shortname, double sum, SpendingsCategory expense_category, boolean limit_exceeded, LocalDateTime datetime) {
        this.account_from = account_from;
        this.account_to = account_to;
        this.currency_shortname = currency_shortname;
        this.sum = sum;
        this.expense_category = expense_category;
        this.limit_exceeded = limit_exceeded;
        this.datetime = datetime;
    }
}
