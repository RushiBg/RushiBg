package com.Task3;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String pin;
    private double balance;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}

class UserDatabase {
    private Map<String, User> users;

    public UserDatabase() {
        users = new HashMap<>();
        users.put("user1", new User("user1", "1234", 5000));
        users.put("user2", new User("user2", "5678", 8000));
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}

class ATM {
    private List<Transaction> transactionHistory;
    private User currentUser;
    private UserDatabase userDatabase;

    public ATM() {
        this.transactionHistory = new ArrayList<>();
        this.userDatabase = new UserDatabase();
    }

    public boolean validateUser(String userId, String userPin) {
        User user = userDatabase.getUser(userId);
        if (user != null && user.getPin().equals(userPin)) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void withdraw(double amount) {
        if (currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            transactionHistory.add(new Transaction("Withdraw", amount));
            System.out.println("Withdraw successful. New balance: " + currentUser.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        currentUser.setBalance(currentUser.getBalance() + amount);
        transactionHistory.add(new Transaction("Deposit", amount));
        System.out.println("Deposit successful. New balance: " + currentUser.getBalance());
    }

    public void transfer(double amount, String recipientUserId) {
        User recipient = userDatabase.getUser(recipientUserId);
        if (recipient != null && currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            transactionHistory.add(new Transaction("Transfer to " + recipientUserId, amount));
            System.out.println("Transfer successful. New balance: " + currentUser.getBalance());
        } else {
            System.out.println("Transfer failed. Either recipient not found or insufficient balance.");
        }
    }
}
