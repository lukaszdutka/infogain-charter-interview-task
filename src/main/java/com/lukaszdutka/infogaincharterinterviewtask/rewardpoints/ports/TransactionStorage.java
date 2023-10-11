package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.ports;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionStorage {
    String add(Transaction transaction);
    List<Transaction> getFor(String userId, LocalDate from, LocalDate to);

    void delete(String transactionId);
}
