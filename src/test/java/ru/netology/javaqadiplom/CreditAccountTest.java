package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void balanceShouldDecreaseByAmount() {
        CreditAccount account = new CreditAccount(
                5_000,
                -5_000,
                15
        );

        account.pay(12_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void rateCalculationWithPositiveBalanceShouldBeZero() {
        CreditAccount account = new CreditAccount(
                200,
                -5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void balanceLessThanCreditLimit() {
        CreditAccount account = new CreditAccount(
                50,
                100,
                15
        );


        Assertions.assertThrows(IllegalArgumentException.class,
                () -> account.getBalance()
        );
    }

    @Test
    public void creditLimitShouldBePositive() {
        CreditAccount account = new CreditAccount(
                50,
                -100,
                15
        );


        Assertions.assertThrows(IllegalArgumentException.class,
                () -> account.getCreditLimit()
        );
    }
}
