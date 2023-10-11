package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Points;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Transaction;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.ports.TransactionStorage;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@AllArgsConstructor
public class RewardPointsFacade {
    private final TransactionStorage transactionStorage;
    private final PointsCalculator pointsCalculator;

    public String addTransaction(Transaction transaction) {
        return transactionStorage.add(transaction);
    }

    public void deleteTransaction(String transactionId) {
        transactionStorage.delete(transactionId);
    }

    public Points calculatePoints(String userId, int year, int month) {
        LocalDate initial = LocalDate.of(year, month, 1);
        LocalDate start = initial.with(firstDayOfMonth());
        LocalDate end = initial.with(lastDayOfMonth());

        List<Transaction> transactions = transactionStorage.getFor(userId, start, end);
        return transactions.stream()
                .map(pointsCalculator::calculate)
                .reduce(new Points(0), Points::add);
    }

}
