package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    int initialBalance = 5_000;
    int creditLimit = 5_000;
    int rate = 15;

    private CreditAccount createCreditAccount() {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);
        return account;
    }

    @Test
    public void shouldCreateSavingAccount() {
        CreditAccount account = createCreditAccount();

        Assertions.assertEquals(initialBalance, account.getBalance());
        Assertions.assertEquals(creditLimit, account.getCreditLimit());
        Assertions.assertEquals(rate, account.getRate());
    }


    @Test
    public void initialBalanceCanNotBeLessThanCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(-1, 100, 15)
        );
    }

    @Test
    public void creditLimitShouldBePositive() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(50, -1, 15)
        );
    }

    @Test
    public void rateShouldBePositive() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(50, 100, -15)
        );
    }

    @Test
    public void balanceMayBeNegativeButNotLessThanCreditLimit() {
        CreditAccount account = new CreditAccount(
                1,
                2,
                15
        );
        Assertions.assertTrue(account.pay(2));
        Assertions.assertEquals(-1, account.getBalance());
    }

    @Test
    public void balanceShouldChangeForAmount() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertTrue(account.pay(3_000));

        Assertions.assertEquals(2_000, account.getBalance());
    }


    @Test
    public void balanceDoNotChangeIfNotEnoughMoney() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertFalse(account.pay(12_000));

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void amountMoreThanInitialBalanceButLessCreditLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertTrue(account.pay(6_000));

        Assertions.assertEquals(-1_000, account.getBalance());
    }

    @Test
    public void balanceStayZeroAfterPay() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(5_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void amountCouldNotBeZeroPayMethod() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertFalse(account.pay(0));
        Assertions.assertEquals(5_000, account.getBalance());

    }

    @Test
    public void amountNegativeInPayMethod() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertFalse(account.pay(-1_000));
        Assertions.assertEquals(5_000, account.getBalance());

    }

    @Test
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertTrue(account.add(3_000));

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void addShouldIncreaseBalanceIfInitialBalanceNotZero() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        Assertions.assertTrue(account.add(3_000));

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void amountNegativeInAddMethod() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        Assertions.assertFalse(account.add(-3_000));

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void amountZeroInAddMethod() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        Assertions.assertFalse(account.add(0));

        Assertions.assertEquals(2_000, account.getBalance());
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
    public void rateCalculationWithZeroBalanceShouldBeZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void rateCalculationRoundsAheadOfTime() {
        CreditAccount account = new CreditAccount(
                200,
                100,
                15
        );
        account.pay(242);
        Assertions.assertEquals(6, account.yearChange());
    }
}
