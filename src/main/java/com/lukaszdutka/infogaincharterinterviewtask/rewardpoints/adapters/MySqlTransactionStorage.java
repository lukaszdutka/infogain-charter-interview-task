package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.adapters;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure.TransactionEntity;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure.RewardPointsRepository;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Transaction;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.ports.TransactionStorage;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.joda.money.CurrencyUnit.USD;

@Component
@RequiredArgsConstructor
public class MySqlTransactionStorage implements TransactionStorage {
    private final RewardPointsRepository rewardPointsRepository;

    @Override
    public String add(Transaction transaction) {
        TransactionEntity savedEntity = rewardPointsRepository.save(
                new TransactionEntity(null, transaction.userId(), transaction.amountSpent().getAmount(), transaction.date())
        );
        return savedEntity.getId();
    }

    @Override
    public List<Transaction> getFor(String userId, LocalDate from, LocalDate to) {
        return rewardPointsRepository.findAllByTransactionDateBetween(userId, from, to).stream()
                .map(Transaction::from)
                .toList();
    }

    @Override
    public void delete(String transactionId) {
        rewardPointsRepository.deleteById(transactionId);
    }
}
