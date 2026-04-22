package controller;

import entities.Account;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidAmountException;
import exceptions.InvalidPinException;
import exceptions.WithdrawLimitException;
import service.AccountService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class AccountController {

    AccountService service = new AccountService();

    public void menuBank(Account account, Scanner sc) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println();
        System.out.println("====================================");
        System.out.println("          🏦 BANK SYSTEM          ");
        System.out.println("====================================");
        System.out.println(now.format(fmt));
        System.out.println("Client: " + account.getName());
        System.out.println("Account: " + account.getNumberAccount());
        System.out.println("------------------------------------");
        System.out.println("1 - \uD83D\uDCB5 Deposit");
        System.out.println("2 - \uD83D\uDCB8 Withdraw");
        System.out.println("3 - \uD83D\uDCCA View Balance");
        System.out.println("0 - \uD83D\uDEAA Exit");
        System.out.println("====================================");
    }

    public void deposit(Account account, Scanner sc) {
        int attempts = 3;
        double amount;
            System.out.print("Enter the deposit amount USD$ ");
            if (!sc.hasNextDouble()) {
                System.out.println("Invalid amount! Enter a numeric value.");
                sc.next();
                return;
            }
                amount = sc.nextDouble();
                sc.nextLine();

            while(attempts > 0) {
                try {
                    System.out.print("PIN: ");
                    String pin = sc.nextLine();
                    service.deposit(account, amount, pin);
                    showDepositSuccess(account);
                    System.out.print("View deposit extract? (s/n): ");
                    char option = sc.next().toLowerCase().charAt(0);
                    sc.nextLine();
                    showExtractDeposit(account, option, amount);
                    return;
                }
                catch (InvalidPinException error) {
                    attempts --;
                    System.out.println(error.getMessage());

                    if (attempts > 0) {
                        System.out.println(attempts + " Remaining attempts");
                    }
                    else {
                        System.out.println("Operation canceled, attempts exceeded.");
                    }
                }
                catch (InvalidAmountException error) {
                    System.out.println(error.getMessage());
                    return;
                }
            }
        }

    public void withdraw(Account account, Scanner sc) {
        int attempts = 3;
        double amount;

        System.out.print("Enter the withdraw amount USD$ ");
        if (!sc.hasNextDouble()) {
            System.out.println("Invalid amount! Enter a numeric value.");
            sc.next();
            return;
        }
        amount = sc.nextDouble();
        sc.nextLine();

        while (attempts > 0) {
            try {
                System.out.print("PIN: ");
                String pin = sc.nextLine();
                service.withdraw(account, amount, pin);
                showWithdrawSuccess(account);
                System.out.print("View withdraw extract? (s/n): ");
                char option = sc.next().toLowerCase().charAt(0);
                sc.nextLine();
                showExtractWithdraw(account, option, amount);
                return;
            }
            catch (InvalidPinException error) {
                attempts--;
                System.out.println(error.getMessage());

                if (attempts > 0) {
                    System.out.println(attempts + " Remaining attempts");
                }
                else {
                    System.out.println("Operation canceled, attempts exceeded.");
                }
            }
            catch (InvalidAmountException error) {
                System.out.println(error.getMessage());
                return;
            }
            catch (InsufficientBalanceException error) {
                System.out.println(error.getMessage());
                return;
            }
            catch (WithdrawLimitException error) {
                System.out.println(error.getMessage());
                return;
            }


        }
    }

    public void showBalance(Account account) {
        System.out.println("====================================");
        System.out.printf("\uD83D\uDCB0 Balance: USD$%.2f%n", account.getBalance());
        System.out.println("====================================");
    }

    private void showDepositSuccess(Account account) {
        System.out.println("====================================");
        System.out.println("Deposit successfully completed.");
        System.out.println("===================================");
    }

    private void showWithdrawSuccess(Account account) {
        System.out.println("===================================");
        System.out.println("Withdraw successfully completed.");
        System.out.println("===================================");
    }

    public void showExtractDeposit(Account account, Character option, double amount) {
        if (option == 's') {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("===================================");
            System.out.println("Deposit made in " + now.format(fmt));
            System.out.println("Name: " + account.getName());
            System.out.println("Account: " + account.getNumberAccount());
            System.out.printf("Amount: USD$%.2f%n", amount);
            System.out.println("===================================");
        }
    }

    public void showExtractWithdraw(Account account, Character option, double amount) {
        if (option == 's') {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("===================================");
            System.out.println("Withdraw made in " + now.format(fmt));
            System.out.println("Name: " + account.getName());
            System.out.println("Account: " + account.getNumberAccount());
            System.out.printf("Amount: USD$%.2f%n", amount);
            System.out.println("===================================");
        }
    }



}

