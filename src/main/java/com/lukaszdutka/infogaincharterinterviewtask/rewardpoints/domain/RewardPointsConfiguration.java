package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.adapters.MySqlTransactionStorage;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.PointsCalculator;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.Promotion;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.RewardPointsFacade;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure.RewardPointsRepository;
import org.joda.money.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static org.joda.money.CurrencyUnit.USD;

@Configuration
class RewardPointsConfiguration {

    @Bean
    RewardPointsFacade awardsFacade(RewardPointsRepository rewardPointsRepository) {
        List<Promotion> promotions = List.of(
                new Promotion(Money.of(USD, 100), 2),
                new Promotion(Money.of(USD, 50), 1)
        );

        return new RewardPointsFacade(
                new MySqlTransactionStorage(rewardPointsRepository),
                new PointsCalculator(promotions));
    }
}
