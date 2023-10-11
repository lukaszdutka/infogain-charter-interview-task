package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain;

import org.joda.money.Money;

record Promotion(Money threshold, int pointsPerDollarAboveThreshold) {
}
