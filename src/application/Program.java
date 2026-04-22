package application;

import controller.AccountController;
import entities.Account;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String name = null;
        String pinAccount = null;

        int attemptsName = 3;
        while (attemptsName > 0) {
            System.out.print("Enter your first name: ");
            name = sc.nextLine().trim();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            }
            attemptsName--;
            if (attemptsName > 0) {
                System.out.println("Only letters allowed!");
                System.out.println(attemptsName + " Remaining attempts");
            }
            if (attemptsName == 0) {
                System.out.println("Attempts exceeded. Operation canceled.");
                return;
            }
        }

        int attemptsPin = 3;
        while (attemptsPin > 0) {
            System.out.print("Create your password (four digits): ");
            pinAccount = sc.nextLine();
            if (pinAccount.matches("\\d{4}")) {
                break;
            }
            attemptsPin--;
            if (attemptsPin > 0) {
                System.out.println("The password must consist of only digits!");
                System.out.println(attemptsPin + " Remaining attempts");
            }
            if (attemptsPin == 0) {
                System.out.println("Attempts exceeded. Operation canceled.");
                return;
            }
        }

        System.out.println();
        System.out.println("User successfully registered.");
        System.out.println("Releasing access...");

        Account account = new Account(name, pinAccount);
        AccountController controller = new AccountController();

        boolean finishMenu = false;
        do {
            controller.menuBank(account, sc);
            System.out.print("Choose an option : ");
            if (sc.hasNextInt()) {
                int option = sc.nextInt();
                sc.nextLine();
                if (option < 0 || option > 3) {
                    System.out.println("Invalid option!");
                    continue;
                }

                switch (option) {
                    case 1 -> controller.deposit(account, sc);
                    case 2 -> controller.withdraw(account, sc);
                    case 3 -> controller.showBalance(account);
                    case 0 -> {
                        System.out.println("Leaving...");
                        finishMenu = true;
                    }
                }
            }
            else {
                System.out.println("Only numbers allowed!");
                sc.next();
            }
        } while(!finishMenu);
    sc.close();
    }
}
