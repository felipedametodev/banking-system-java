package service;

import entities.Account;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidAmountException;
import exceptions.InvalidPinException;
import exceptions.WithdrawLimitException;

public class AccountService {

    public void deposit(Account account, double amount, String pin) {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount, please enter again.");
        }
        if (!account.getPin().equals(pin)) {
            throw new InvalidPinException("Invalid PIN, please enter again.");
        }
            account.deposit(amount);
    }

    public void withdraw(Account account, double amount, String pin) {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount, please enter again.");
        }
        if (amount > account.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance, please enter again.");
        }
        if (amount > 5000) {
            throw new WithdrawLimitException("Withdrawal limit exceeded, please enter again.");
        }
        if (!account.getPin().equals(pin)) {
            throw new InvalidPinException("Invalid PIN, please enter again.");
        }
        account.withdraw(amount);
    }


}

