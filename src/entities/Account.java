package entities;

public class Account {

    private String name;
    private String numberAccount;
    private String pin;
    private Double balance;

    public Account(String name, String pin) {
        this.name = name;
        this.numberAccount = "0000-1";
        this.pin = pin;
        this.balance = 0.0;
    }

    public String getPin() {
        return pin;
    }

        public String getNumberAccount() {
        return numberAccount;
    }

    public Double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void withdraw(Double amount) {
        balance -= amount;
    }

    public void deposit(Double amount) {
        balance += amount;
    }
}
