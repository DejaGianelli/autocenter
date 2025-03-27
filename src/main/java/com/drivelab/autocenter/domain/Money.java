package com.drivelab.autocenter.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class Money {

    public static final Money ZERO = Money.create(0.00);

    private final int amount;

    private Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = Math.abs(amount);
    }

    private Money(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = toCents(amount);
    }

    public static Money create(double amount) {
        return new Money(amount);
    }

    public static Money create(int amountInCents) {
        return new Money(amountInCents);
    }

    private int toCents(double amount) {
        amount = amount * 100;
        amount = Math.floor(amount);
        return (int) amount;
    }

    public int cents() {
        return amount;
    }

    public double decimal() {
        return (((double) this.amount) / 100);
    }

    @Override
    public String toString() {
        var nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        return nf.format(decimal());
    }
}
