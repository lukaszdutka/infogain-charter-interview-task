package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints.infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    @Column(name = "amount_spent", precision = 19, scale = 4, nullable = false)
    private BigDecimal amountSpent;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;
}