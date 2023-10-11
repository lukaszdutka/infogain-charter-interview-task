package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequestBody {
    private String userId;
    private BigDecimal amountSpent;
    private LocalDate date;
}
