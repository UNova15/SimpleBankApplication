package org.simplebank;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class UserCredentialsLoader {
    private static String FILE_PATH = "src/main/resources/Usersdata.txt";
    private static HashMap<String, User> users;

    private UserCredentialsLoader() {
    }

    static {
        loadUserCredentials();
    }

    static void addUsers(String login,User newUser) {
        users.put(login,newUser);
        saveUserCredentials();
    }

    static Map<String,User> getUsers(){
        return Collections.unmodifiableMap(users);
    }

    private static void loadUserCredentials() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(FILE_PATH)))) {

            File file = new File(FILE_PATH);
            if(!file.exists()){
                users = new HashMap<>();
                return;
            }

            users = (HashMap<String, User>) objectInputStream.readObject();


        } catch (IOException | ClassNotFoundException exception) {
            users = new HashMap<>();
            System.out.println("Ошибка чтения данных "+exception.getMessage());
        }
    }

     private static void saveUserCredentials() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(FILE_PATH)))) {
            objectOutputStream.writeObject(users);

        } catch (IOException exception) {
            System.out.println("Ошибка сохранения данных");
        }
    }

}
