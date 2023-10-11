package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Transaction;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Points;

import java.util.Comparator;
import java.util.List;

class PointsCalculator {
    private final List<Promotion> promotions;

    public PointsCalculator(List<Promotion> promotions) {
        this.promotions = promotions.stream()
                .sorted(fromHighestToLowestThreshold())
                .toList();
    }

    private static Comparator<Promotion> fromHighestToLowestThreshold() {
        return (p1, p2) -> p2.threshold().compareTo(p1.threshold());
    }

    public Points calculate(Transaction transaction) {
        int points = 0;
        int amountSpentInDollars = transaction.amountSpent().getAmountMajorInt(); //ignore cents

        for (Promotion p : promotions) {
            int promotionThreshold = p.threshold().getAmountMajorInt();
            int dollarsAboveThreshold = amountSpentInDollars - promotionThreshold;
            if (dollarsAboveThreshold <= 0) {
                continue;
            }
            amountSpentInDollars -= dollarsAboveThreshold;
            int pointsToAdd = p.pointsPerDollarAboveThreshold() * dollarsAboveThreshold;
            points += pointsToAdd;
        }
        return new Points(points);
    }
}
