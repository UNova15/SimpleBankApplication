package org.simplebank;

import java.io.Serializable;
import java.util.UUID;

class User implements Serializable {
    private final String login;
    private final int password;
    private final String name;
    private final String surname;
    private boolean blockStatus;

    private final UUID id;
    private int balance;

    User(String login, String password, String name, String surname, int balance) throws
            LoginNotUniqueException, PasswordLengthException, NegativeBalanceException {

        if (!isValidLogin(login)) {
            throw new LoginNotUniqueException(login);
        }

        if (!isValidPassword(password)) {
            throw new PasswordLengthException(password);
        }

        if (!isValidBalance(balance)) {
            throw new NegativeBalanceException(balance);
        }

        this.login = login;
        this.password = password.hashCode();
        this.balance = balance;
        this.name = name;
        this.surname = surname;
        this.id = UUID.randomUUID();
        this.blockStatus = false;
    }

    @Override
    public String toString(){
        return id+"\t"+name+"\t"+balance;
    }

    private boolean isValidLogin(String login) {
        return !UserService.getUsers().containsKey(login);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    private boolean isValidBalance(int balance) {
        return balance >= 0;
    }

    int getPassword() {
        return password;
    }

    UUID getId() {
        return id;
    }

    boolean getBlockStatus(){
        return blockStatus;
    }

    void setBlockedStatus(boolean blockedStatus){
           this.blockStatus = blockedStatus;
    }

    int getBalance() {
        return balance;
    }

    void setBalance(int value) {
        balance = value;
    }

    String getLogin(){
        return login;
    }
}
