package org.simplebank;

import java.io.Serializable;
import java.util.UUID;

class User implements Serializable {
    private String login;
    private int password;
    private String name;
    private String surname;

    private UUID id;
    private int balance;

    User(String login, String password, String name, String surname, int balance) throws
            LoginNotUniqueException,PasswordLengthException,NegativeBalanceException {

        if (!isValidLogin(login)) {
            throw new LoginNotUniqueException(login);
        }

        if(!isValidPassword(password)){
            throw new PasswordLengthException(password);
        }

        if(!isValidBalance(balance)){
            throw new NegativeBalanceException(balance);
        }

        this.login = login;
        this.password = password.hashCode();
        this.balance = balance;
        this.name = name;
        this.surname = surname;
        this.id = UUID.randomUUID();
    }

     private boolean isValidLogin(String login) {
         return !UserCredentialsLoader.getUsers().containsKey(login);
     }

    private boolean isValidPassword(String password){
        return password!=null&&password.length() >= 6;
    }

    private boolean isValidBalance(int balance){
        return balance >= 0;
    }

    int getPassword(){
        return password;
    }
}
