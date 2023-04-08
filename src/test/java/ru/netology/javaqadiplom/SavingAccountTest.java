package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    int balance = 2_000;
    int minBalance = 1_000;
    int maxBalance = 10_000;
    int rate = 5;

    private SavingAccount createSavingAccount() {
        SavingAccount account = new SavingAccount(balance, minBalance, maxBalance, rate);
        return account;
    }


    @Test
    public void shouldCreateSavingAccount() {
        SavingAccount account = createSavingAccount();

        Assertions.assertEquals(balance, account.getBalance());
        Assertions.assertEquals(minBalance, account.getMinBalance());
        Assertions.assertEquals(maxBalance, account.getMaxBalance());
        Assertions.assertEquals(rate, account.getRate());
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean result = account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
        Assertions.assertEquals(true, result);
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = createSavingAccount();

        boolean result = account.add(9_000);

        Assertions.assertEquals(balance, account.getBalance());
        Assertions.assertEquals(false, result);
    }

    @Test
    public void amountAddShouldNotBeNegative() {
        SavingAccount account = createSavingAccount();
        int amount = -100;

        boolean result = account.add(amount);

        Assertions.assertEquals(balance, account.getBalance());
        Assertions.assertEquals(false, result);
    }

    @Test
    public void amountAddShouldNotBeZero() {
        SavingAccount account = createSavingAccount();
        int amount = 0;

        boolean result = account.add(amount);

        Assertions.assertEquals(balance, account.getBalance());
        Assertions.assertEquals(false, result);
    }


    @Test
    public void minBalanceShouldBeLessThanMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    11_000,
                    10_000,
                    5
            );
        });

    }

    @Test
    public void initialBalanceShouldNotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    -2_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void initialBalanceCouldBeZero() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void minBalanceShouldNotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    -1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void minBalanceCouldBeZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        Assertions.assertEquals(0, account.getMinBalance());
    }

    @Test
    public void maxBalanceShouldNotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    1_000,
                    -10_000,
                    5
            );
        });
    }

    @Test
    public void maxBalanceShouldNotBeZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    1_000,
                    0,
                    5
            );
        });
    }

    @Test
    public void rateCouldBeZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                0
        );

        Assertions.assertEquals(0, account.getRate());
    }

    @Test
    public void rateShouldNotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(balance, minBalance, maxBalance, -5);

        });
    }

    @Test
    public void initialBalanceShouldBeMoreThanMin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void initialBalanceShouldBeLessThanMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    50_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void shouldSuccessfullyPay() {
        SavingAccount account = createSavingAccount();
        int payment = 400;

        boolean result = account.pay(payment);

        Assertions.assertEquals(balance - payment, account.getBalance());
        Assertions.assertEquals(true, result);
    }

    @Test
    public void amountPayShouldNotBeNegative() {
        SavingAccount account = createSavingAccount();
        int payment = -100;

        boolean result = account.pay(payment);

        Assertions.assertEquals(balance, account.getBalance());
        Assertions.assertEquals(false, result);
    }

    @Test
    public void amountPayShouldNotBeZero() {
        SavingAccount account = createSavingAccount();
        int payment = 0;

        boolean result = account.pay(payment);

        Assertions.assertEquals(balance, account.getBalance());
        Assertions.assertEquals(false, result);
    }

    @Test
    public void afterPayBalanceShouldNotLessThanMin() {
        SavingAccount account = new SavingAccount(
                1_000,
                500,
                2_000,
                5
        );

        boolean result = account.pay(600);

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(false, result);
    }

    @Test
    public void shouldCountInterestOnBalance() {
        SavingAccount account = new SavingAccount(
                234,
                100,
                10_000,
                15
        );

        int result = account.yearChange();

        Assertions.assertEquals(35, result);
    }


}

