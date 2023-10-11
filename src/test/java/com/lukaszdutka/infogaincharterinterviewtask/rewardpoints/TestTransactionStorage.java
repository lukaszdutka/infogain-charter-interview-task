package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Transaction;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure.TransactionEntity;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.ports.TransactionStorage;
import org.joda.money.Money;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.joda.money.CurrencyUnit.USD;

public class TestTransactionStorage implements TransactionStorage {

    private List<TransactionEntity> transactions = new ArrayList<>();

    @Override
    public String add(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity(
                UUID.randomUUID().toString(),
                transaction.userId(),
                transaction.amountSpent().getAmount(),
                transaction.date()
        );
        transactions.add(transactionEntity);
        return transactionEntity.getId();
    }

    @Override
    public List<Transaction> getFor(String userId, LocalDate from, LocalDate to) {
        return transactions.stream()
                .map(Transaction::from)
                .filter(transaction -> transaction.userId().equals(userId))
                .filter(transaction -> !transaction.date().isBefore(from) && !transaction.date().isAfter(to))
                .toList();
    }

    @Override
    public void delete(String transactionId) {
        this.transactions = this.transactions.stream()
                .filter(t -> !t.getId().equals(transactionId))
                .toList();
    }
}
