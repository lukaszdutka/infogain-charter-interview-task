package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos;

public record Points(int quantity) {

    public Points add(Points other) {
        return new Points(this.quantity + other.quantity);
    }
}
