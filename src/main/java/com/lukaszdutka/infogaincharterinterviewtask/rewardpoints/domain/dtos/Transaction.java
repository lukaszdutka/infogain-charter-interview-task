package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure.CreateTransactionRequestBody;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure.TransactionEntity;
import org.joda.money.Money;

import java.math.RoundingMode;
import java.time.LocalDate;

import static org.joda.money.CurrencyUnit.USD;

public record Transaction(String userId, Money amountSpent, LocalDate date) {
    public static Transaction from(CreateTransactionRequestBody tr) {
        return new Transaction(
                tr.getUserId(),
                Money.of(USD, tr.getAmountSpent().setScale(2, RoundingMode.UNNECESSARY)),
                tr.getDate()
        );
    }

    public static Transaction from(TransactionEntity t) {
        return new Transaction(
                t.getCustomerId(),
                Money.of(USD, t.getAmountSpent().setScale(2, RoundingMode.UNNECESSARY)),
                t.getTransactionDate()
        );
    }
}
