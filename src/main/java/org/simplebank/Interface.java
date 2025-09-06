package org.simplebank;

import java.util.Scanner;

class Interface {

    static Scanner scanner;

    static void start() {

        boolean isContinue = true;
        scanner = new Scanner(System.in);

        while (isContinue) {
            printStartMenu();
            isContinue = processStartMenu();
        }
    }

    private static void printStartMenu() {
        System.out.println("Добро пожаловать в SimpleBank!" +
                "\nВыберите действие:\n" +
                "1.Вход\n2.Регистрация\n3.Вход как администратор\n4.Выход");
    }

    private static boolean processStartMenu() {
        switch (scanner.nextLine()) {

            case "1":
                try {

                    System.out.println("Введите логин и пароль");
                    UserService.logInToTheSystem(scanner.nextLine(), scanner.nextLine());
                    System.out.println("Выполнен вход.");
                    processUsersMenu();

                } catch (AuthenticationException|AccountIsBlockedException exception) {
                    System.out.println(exception.getMessage());
                }
                break;

            case "2":
                try {

                    System.out.println("Введите:\nЛогин\nПароль\nИмя\nФамилию\nБаланс");
                    UserService.registration(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(),
                            scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
                    System.out.println("Выполнен вход.");
                    processUsersMenu();

                } catch (LoginNotUniqueException | PasswordLengthException | NegativeBalanceException exception) {
                    System.out.println(exception.getMessage());
                }
                break;

            case "3":
                try {
                    System.out.println("Введите логин и пароль");
                    AdminService.logInToTheSystem(scanner.nextLine(), scanner.nextLine());
                    System.out.println("Выполнен вход.");
                    processAdminMenu();

                }catch(UserNotFoundException exception){
                    System.out.println(exception.getMessage());
                }
                break;

            case "4":
                UserService.close();
                return false;
            default:
                System.out.println("Выберете один из предложенных вариантов");
        }
        return true;
    }

    private static void printUsersMenu() {
        System.out.println("Выберите действие:" +
                "\n1.Просмотр баланса\n2.Перевод средств другому клиенту\n3.Просмотр истории транзакций\n4.Выход из учетной записи");
    }

    private static void processUsersMenu() {

        while (true) {
            printUsersMenu();
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println(UserService.getBalance());
                    break;

                case "2":
                    System.out.println("Введите логин получателя и сумму перевода");

                    try {
                        UserService.fundsTransfer(scanner.nextLine(), Integer.parseInt(scanner.nextLine()));

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case "3":
                    System.out.println(UserService.getLog());
                    break;

                case "4":
                    return;
            }
        }
    }

    private static void printAdminMenu(){
        System.out.println("\"Выберите действие:\n1.Просмотр списка всех клиентов\n" +
                "2.Блокировка клиента\n3.Разблокировка клиента\n4.Выход из режима администратора");
    }

    private static void processAdminMenu(){

        while(true){
            printAdminMenu();
            switch(scanner.nextLine()){
                case "1":
                    System.out.println(AdminService.getUsers());
                    break;

                case "2":
                    try {
                        System.out.println("Введите логин аккаунта для блокировки");
                        AdminService.setBlockStatus(scanner.nextLine(),true);
                        System.out.println("Аккаунт заблокирован");
                    }catch(UserNotFoundException exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case "3":
                    try {
                        System.out.println("Введите логин аккаунта для разблокировки");
                        AdminService.setBlockStatus(scanner.nextLine(),false);
                        System.out.println("Аккаунт разблокирован");
                    }catch(UserNotFoundException exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case "4":
                    return;
            }
        }
    }
}
