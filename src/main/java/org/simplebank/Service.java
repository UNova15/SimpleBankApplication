package org.simplebank;


enum Status{
    active,
    notActive
}

class Service {

    private static Status status = Status.notActive;

    static void registration(String login, String password, String name, String surname, int balance) {
        try {
            UserCredentialsLoader.addUsers(login,new User(login,password,name,surname,balance));

        } catch (LoginNotUniqueException | PasswordLengthException|NegativeBalanceException exception) {
            System.out.println(exception.getMessage());
        }
    }

    static void logInToTheSystem(String login,String password) throws AuthenticationException{

        User user = UserCredentialsLoader.getUsers().get(login);

        if (user==null||user.getPassword() != password.hashCode()){
            throw new AuthenticationException(login,password);
        }

        status=Status.active;
    }

}
