package com.lukaszdutka.infogaincharterinterviewtask.rewardpoints;

import com.lukaszdutka.infogaincharterinterviewtask.BaseIT;
import org.joda.money.Money;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.joda.money.CurrencyUnit.USD;

public class RewardPointsIT extends BaseIT {
    public static final String DEFAULT_USER_ID = "userId";
    public static final int DEFAULT_YEAR = 2023;
    public static final int DEFAULT_MONTH = 10;
    private static final Money DEFAULT_AMOUNT_SPENT = Money.of(USD, 51);
    private static final LocalDate DEFAULT_DATE = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 10);

    @Test
    void givenNoTransactionsReturnZero() {
        given()
                .params(defaultParams())
                .when()
                .get("/api/rewards")
                .then()
                .statusCode(200)
                .body("points", equalTo(0));
    }

    @Test
    void givenOneTransactionReturnPoints() {
        postTransaction(Money.of(USD, 51));

        given()
                .params(defaultParams())
                .when()
                .get("/api/rewards")
                .then()
                .statusCode(200)
                .body("points", equalTo(1));
    }

    @Test
    void givenManyTransactionsReturnSumOfPoints() {
        postTransaction(Money.of(USD, 51));
        postTransaction(Money.of(USD, 52));

        given()
                .params(defaultParams())
                .when()
                .get("/api/rewards")
                .then()
                .statusCode(200)
                .body("points", equalTo(3));
    }

    @Test
    void givenTransactionsForDifferentUsersReturnCorrectPoints() {
        postTransaction("other-user-id", Money.of(USD, 9999));
        postTransaction(DEFAULT_USER_ID, Money.of(USD, 52));

        given()
                .params(defaultParams())
                .when()
                .get("/api/rewards")
                .then()
                .statusCode(200)
                .body("points", equalTo(2));
    }

    @Test
    void givenTransactionsFromDifferentDateReturnCorrectPoints() {
        postTransaction(body(DEFAULT_USER_ID, DEFAULT_AMOUNT_SPENT, DEFAULT_DATE));
        postTransaction(body(DEFAULT_USER_ID, DEFAULT_AMOUNT_SPENT, DEFAULT_DATE.plusMonths(1)));

        given()
                .params(defaultParams())
                .when()
                .get("/api/rewards")
                .then()
                .statusCode(200)
                .body("points", equalTo(1));
    }

    private void postTransaction(String userId, Money amountSpent) {
        given()
                .contentType("application/json")
                .body(body(userId, amountSpent, DEFAULT_DATE))
                .when()
                .post("/api/rewards/transactions")
                .then()
                .statusCode(200);
    }

    private void postTransaction(String body) {
        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/rewards/transactions")
                .then()
                .statusCode(200);
    }

    private static void postTransaction(Money amountSpent) {
        given()
                .contentType("application/json")
                .body(defaultBody(amountSpent))
                .when()
                .post("/api/rewards/transactions")
                .then()
                .statusCode(200);
    }

    private static String defaultBody(Money amountSpent) {
        return body(DEFAULT_USER_ID, amountSpent, DEFAULT_DATE);
    }

    private static String body(String userId, Money amountSpent, LocalDate date) {
        JSONObject body = new JSONObject();
        body.put("userId", userId);
        body.put("amountSpent", amountSpent.getAmount());
        body.put("date", date.toString());
        return body.toJSONString();
    }

    private static Map<String, Object> defaultParams() {
        return Map.of(
                "userId", DEFAULT_USER_ID,
                "year", DEFAULT_YEAR,
                "month", DEFAULT_MONTH
        );
    }
}
