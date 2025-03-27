package com.drivelab.autocenter.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {
    @ParameterizedTest
    @CsvSource({
            "10.00,1000",
            "10.05,1005",
            "00.01,1",
            "00.0513456,5",
            "001.0513456,105",
    })
    void get_cents_from_money(double amount, int cents) {
        Money money = Money.create(amount);

        assertEquals(cents, money.cents());
    }

    @ParameterizedTest
    @CsvSource({
            "1000,10.00",
            "1005,10.05,",
            "1,0.01",
            "5,0.05",
            "105,1.05",
    })
    void get_decimal_from_money(int cents, double decimal) {
        Money money = Money.create(cents);

        assertEquals(decimal, money.decimal());
    }
}