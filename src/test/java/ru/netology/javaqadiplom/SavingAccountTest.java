package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
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
    public void afterPayBalanceShouldNotLessThanMin () {
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


}

