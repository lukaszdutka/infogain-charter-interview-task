package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PointsResponseBody {
    String userId;
    int year;
    int month;
    int points;
}
