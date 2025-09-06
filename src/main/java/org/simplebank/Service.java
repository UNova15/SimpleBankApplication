package org.simplebank;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

enum Status {
    active,
    notActive
}

class UserService {

    private static final DataLoader dataLoader = DataLoader.getInstance();
    private static Status status = Status.notActive;
    private static User user;

    static void registration(String login, String password, String name, String surname, int balance) throws
            LoginNotUniqueException, PasswordLengthException, NegativeBalanceException {

        User user = new User(login, password, name, surname, balance);
        dataLoader.addUsers(login, user);

        UserService.user = user;
        status = Status.active;
    }

    static void logInToTheSystem(String login, String password) throws
            AuthenticationException,AccountIsBlockedException {

        User user = dataLoader.getUsers().get(login);

        if (user == null || user.getPassword() != password.hashCode()) {
            throw new AuthenticationException(login, password);
        }
        if (user.getBlockStatus()){
            throw new AccountIsBlockedException();
        }
        UserService.user = user;
        status = Status.active;
    }

    static int getBalance() {
        return user.getBalance();
    }

    static Status getStatus() {
        return status;
    }

    static ArrayList<Transaction> getLog() {
        return dataLoader.getLog(user.getLogin());
    }

    static void fundsTransfer(String recipientLogin, int value) throws
            NegativeBalanceException, UserNotFoundException, InvalidOperationException {

        User recipient = dataLoader.getUser(recipientLogin);
        int userBalance = user.getBalance();

        if (value < 0) {
            throw new NegativeBalanceException(value);
        }

        if (userBalance < value) {
            throw new NegativeBalanceException(user.getBalance() - value);
        }

        if (user == recipient) {
            throw new InvalidOperationException();
        }

        user.setBalance(userBalance - value);
        recipient.setBalance(recipient.getBalance() + value);
        dataLoader.addTransaction(recipient, user, value);
    }

    static void close(){
        dataLoader.close();
    }

    static Map<String,User> getUsers(){
        return dataLoader.getUsers();
    }

}

class AdminService{
    private static final DataLoader dataLoader = DataLoader.getInstance();
    private static Status status = Status.notActive;

    private static String password = "Admin";
    private static String login = "Admin";

    static void logInToTheSystem(String login,String password) throws UserNotFoundException{
        if(login.equals(AdminService.login)&&password.equals(AdminService.password)){
           status=Status.active;
           return;
        }

        throw new UserNotFoundException();
    }

    static Map<String,User> getUsers(){
        return dataLoader.getUsers();
    }

    static void setBlockStatus(String login,boolean isBlocked) throws UserNotFoundException{
        dataLoader.getUser(login).setBlockedStatus(isBlocked);

    }

}