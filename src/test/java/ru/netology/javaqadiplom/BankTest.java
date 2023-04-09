package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {
    Account from = new SavingAccount(25, 10, 30, 15);
    Account to = new CreditAccount(10, 50, 15);
    Bank bank = new Bank();

    @Test
    public void transferSuccess() {
        Assertions.assertTrue(bank.transfer(from, to, 3));
        Assertions.assertEquals(13, to.getBalance());
        Assertions.assertEquals(22, from.getBalance());
    }

    @Test
    public void transferNegativeAmount() {
        Assertions.assertFalse(bank.transfer(from, to, -3));
        Assertions.assertEquals(10, to.getBalance());
        Assertions.assertEquals(25, from.getBalance());
    }

    @Test
    public void transferZeroAmount() {
        Assertions.assertFalse(bank.transfer(from, to, 0));
        Assertions.assertEquals(10, to.getBalance());
        Assertions.assertEquals(25, from.getBalance());
    }

    @Test
    public void transferMoreThanAllowed() {
        Assertions.assertFalse(bank.transfer(from, to, 20));
        Assertions.assertEquals(10, to.getBalance());
        Assertions.assertEquals(25, from.getBalance());
    }

    @Test
    public void transferMoreThanMaxBalance() {
        Assertions.assertFalse(bank.transfer(to, from, 20));
        Assertions.assertEquals(10, to.getBalance());
        Assertions.assertEquals(25, from.getBalance());
    }
}

