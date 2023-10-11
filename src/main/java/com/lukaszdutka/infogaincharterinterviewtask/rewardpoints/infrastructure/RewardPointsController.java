package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure;

import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.RewardPointsFacade;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Points;
import com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.domain.dtos.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rewards")
@RequiredArgsConstructor
class RewardPointsController {
    private final RewardPointsFacade facade;

    @PostMapping("/transactions")
    TransactionCreatedResponseBody createTransaction(@RequestBody CreateTransactionRequestBody createTransactionRequestBody) {
        Transaction transaction = Transaction.from(createTransactionRequestBody);
        String id = facade.addTransaction(transaction);
        return new TransactionCreatedResponseBody(id);
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteUserById(@PathVariable String id) {
        facade.deleteTransaction(id);
    }

    @GetMapping()
    public PointsResponseBody getPoints(@RequestParam String userId,
                                        @RequestParam int year,
                                        @RequestParam int month) {
        Points points = facade.calculatePoints(userId, year, month);
        return new PointsResponseBody(userId, year, month, points.quantity());
    }
}
