package com.example.solva.Models.DTO;

import com.example.solva.Models.Enums.Currency;
import com.example.solva.Models.Enums.SpendingsCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDTO {

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
}
