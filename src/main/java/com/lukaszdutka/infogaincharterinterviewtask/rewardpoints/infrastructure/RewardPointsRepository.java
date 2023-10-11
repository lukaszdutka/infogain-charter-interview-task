package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RewardPointsRepository extends CrudRepository<TransactionEntity, String> {
    @Query("""
            SELECT t FROM TransactionEntity t
            WHERE t.customerId = :userId
            AND t.transactionDate BETWEEN :startDate AND :endDate
            """)
    List<TransactionEntity> findAllByTransactionDateBetween(
            @Param("userId") String userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
