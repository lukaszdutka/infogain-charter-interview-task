package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.TestTransactionStorage;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Points;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Transaction;
import org.jetbrains.annotations.NotNull;
import org.joda.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.USD;

class RewardPointsFacadeTest {
    private static final List<Promotion> PROMOTIONS = List.of(
            new Promotion(Money.of(USD, 100), 2),
            new Promotion(Money.of(USD, 50), 1)
    );
    private RewardPointsFacade facade;

    @BeforeEach
    void setUp() {
        this.facade = new RewardPointsFacade(new TestTransactionStorage(), new PointsCalculator(PROMOTIONS));
    }

    @Test
    void whenNonExistingUserThenReturnsZero() {
        Points points = facade.calculatePoints("non-existing-user-id", 2023, 1);

        assertThat(points.quantity()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource(value = "transactionsCornerCases")
    void whenTransactionsRecordedThenReturnsNumberOfPoints(String amountSpent, int points) {
        facade.addTransaction(transaction(amountSpent));

        Points result = facade.calculatePoints("existing-user", 2023, 1);

        assertThat(result.quantity()).isEqualTo(points);
    }

    public static List<Arguments> transactionsCornerCases() {
        return List.of(
                Arguments.of("49.99", 0),
                Arguments.of("50.00", 0),
                Arguments.of("50.01", 0),
                Arguments.of("50.99", 0),
                Arguments.of("51.00", 1),
                Arguments.of("51.01", 1),
                Arguments.of("100.99", 50),
                Arguments.of("101.00", 52),
                Arguments.of("101.01", 52),
                Arguments.of("102.00", 54)
        );
    }

    @NotNull
    private static Transaction transaction(String amountSpent) {
        return new Transaction("existing-user",
                Money.of(USD, new BigDecimal(amountSpent)),
                LocalDate.of(2023, 1, randomDay())
        );
    }

    private static int randomDay() {
        return (int) (Math.random() * 25 + 1);
    }
}