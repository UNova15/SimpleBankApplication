package org.simplebank;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class DataLoader {
    private static String USER_FILE_PATH = "src/main/resources/Usersdata.txt";
    private static String LOGS_FILE_PATH = "src/main/resources/Logs.txt";
    private static HashMap<String, User> users;
    private static HashMap<String, ArrayList<User>> logs;

    static {
        loadUserCredentials();
    }

    static void addUsers(String login, User newUser) {
        users.put(login, newUser);
        saveUserCredentials();
    }

    static Map<String, User> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    static User getUser(String login) throws UserNotFoundException {
        User user = users.get(login);

        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    private static void loadUserLogs() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(LOGS_FILE_PATH)))){

        isFileExists(LOGS_FILE_PATH,logs);

        }catch(IOException exception){

        }
    }


    private static void loadUserCredentials() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(USER_FILE_PATH)))) {

            isFileExists(USER_FILE_PATH,users);

            users = (HashMap<String, User>) objectInputStream.readObject();


        } catch (IOException | ClassNotFoundException exception) {
            users = new HashMap<>();
            System.out.println("Ошибка чтения данных " + exception.getMessage());
        }
    }

    private static void saveUserCredentials() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(USER_FILE_PATH)))) {
            objectOutputStream.writeObject(users);

        } catch (IOException exception) {
            System.out.println("Ошибка сохранения данных");
        }
    }

    private static <T> HashMap<String,T> readFile(String filePath,HashMap<String,T> map,ObjectInputStream objectInputStream) {

        File file = new File(filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try {
            map = (HashMap<String, T>) objectInputStream.readObject();
        }catch(ClassNotFoundException | IOException exception){
            System.out.println("Ошибка чтения данных");
        }
    }


}
