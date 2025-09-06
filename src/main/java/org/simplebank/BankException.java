package org.simplebank;

class LoginNotUniqueException extends Exception {

    LoginNotUniqueException(String login) {
        super("Пользователь с логином" +"\t"+ login +"\t"+"уже существует ");
    }
}

class PasswordLengthException extends Exception {

    PasswordLengthException(String password) {
        super("Длина пароля должна быть не менее 8 символов.Текущая длина:" + password.length());
    }
}

class NegativeBalanceException extends Exception {

    NegativeBalanceException(int balance) {
        super("Баланс счета и переводы не могут быть отрицательными. Текущее значение:" + String.valueOf(balance));
    }
}

class AuthenticationException extends Exception{

    AuthenticationException(String login,String password){
        super("Ошибка входа в учетную запись. Проверьте следующие данные: "+"\t"+login+"\t"+password);
    }
}

class UserNotFoundException extends Exception{

    UserNotFoundException(){
        super("Данный пользователь не найден");
    }
}

class InvalidOperationException extends Exception{

    InvalidOperationException(){
        super("Ошибка выполнения операции");
    }
}

class AccountIsBlockedException extends Exception{

    AccountIsBlockedException(){
        super("Данный аккаунт заблокирован");
    }
}