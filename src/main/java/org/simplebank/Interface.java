package org.simplebank;

import java.util.Scanner;

class Interface {

    static void start() {

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                printStartMenu();

                switch (scanner.nextLine()) {

                    case "1":

                        System.out.println("Введите логин и пароль");
                        try {
                            Service.logInToTheSystem(scanner.nextLine(), scanner.nextLine());
                        }catch(AuthenticationException exception){
                            System.out.println(exception.getMessage());
                        }
                        System.out.println("Вы вошли в систему");
                        break;

                    case "2":

                        System.out.println("Введите:\nЛогин\nПароль\nИмя\nФамилию\nБаланс");
                        Service.registration(scanner.nextLine(),scanner.nextLine(),scanner.nextLine(),
                                scanner.nextLine(),scanner.nextInt());
                        System.out.println("Вы успешно зарегистрировались");
                        break;

                    case "3":
                        break;
                    case "4":
                        break;
                    default:
                        System.out.println("Выберете один из предложенных вариантов");
                }


            }
        }
    }
    private static void printStartMenu(){
        System.out.println("Добро пожаловать в SimpleBank!" +
                "\nВыберите действие:\n" +
                "1.Вход\n2.Регистрация\n3.Вход как администратор\n4.Выход");
    }
}
