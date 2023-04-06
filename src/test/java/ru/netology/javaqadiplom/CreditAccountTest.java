package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    @Test
    public void balanceMayBeNegativeButShouldBeMoreCreditLimit() {
        CreditAccount account = new CreditAccount(
                -2_000,
                5_000,
                15
        );

        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void balanceLessThanCreditLimit() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(-150, 100, 15)
        );
    }

    @Test
    public void constructorCreditAccountTestCreditLimitShouldBePositive() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(50, -1, 15)
        );
    }

    @Test
    public void constructorCreditAccountTestRateShouldBePositive() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(50, 100, -15)
        );
    }


    @Test
    public void balanceShouldChangeForAmount() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }


    @Test
    public void balanceDoNotShouldChangeIfTotalLessThanCreditLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(12_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void amountMoreThanInitialBalanceButLessCreditLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(6_000);

        Assertions.assertEquals(-1_000, account.getBalance());
    }
    @Test
    public void amountNegativeInPayMethods() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(-1_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

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
    public void addShouldIncreaseBalanceIfInitialBalanceNotZero() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void addAmountNegative() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> account.add(-3_000)
        );
    }


    @Test
    public void rateCalculationWithPositiveBalanceShouldBeZero() {
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void rateCalculationWithNegativeBalanceShouldCalculate() {
        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15
        );

        Assertions.assertEquals(-30, account.yearChange());
    }
}
