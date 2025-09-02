package org.simplebank;

class LoginNotUniqueException extends Exception {

    LoginNotUniqueException(String login) {
        super("Пользователь с логином" + login + "уже существует ");
    }
}

class PasswordLengthException extends Exception {

    PasswordLengthException(String password) {
        super("Длина пароля должна быть не менее 8 символов.Текущая длина:" + password.length());
    }
}

class NegativeBalanceException extends Exception {

    NegativeBalanceException(int balance) {
        super("Баланс счета не может быть отрицательным. Текущий баланс:" + String.valueOf(balance));
    }
}

class AuthenticationException extends Exception{

    AuthenticationException(String login,String password){
        super("Ошибка входа в учетную запись. Проверьте следующие данные: "+"\t"+login+"\t"+password);
    }
}