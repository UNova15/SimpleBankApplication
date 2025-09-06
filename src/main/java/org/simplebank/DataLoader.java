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
    private String USER_FILE_PATH = "src/main/resources/Usersdata.txt";
    private String LOGS_FILE_PATH = "src/main/resources/Logs.txt";
    private static DataLoader dataLoader = null;
    private final HashMap<String, User> users;
    private final HashMap<String, ArrayList<Transaction>> logs;

    private DataLoader() {
        users = (HashMap<String, User>) loadData(USER_FILE_PATH);
        logs = (HashMap<String, ArrayList<Transaction>>) loadData(LOGS_FILE_PATH);
    }

    static DataLoader getInstance(){
        if(dataLoader==null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    Map<String, ArrayList<Transaction>> getLogs() {
        return Collections.unmodifiableMap(logs);
    }

    ArrayList<Transaction> getLog(String login) {
        return logs.get(login);
    }

    void addTransaction(User recipient, User sender, int value) {

        Transaction transaction = new Transaction(sender.getId(), recipient.getId(), value);

        logs.computeIfAbsent(recipient.getLogin(),n->new ArrayList<>()).add(transaction);
        logs.computeIfAbsent(sender.getLogin(),n->new ArrayList<>()).add(transaction);

    }


    void addUsers(String login, User newUser) {
        users.put(login, newUser);
    }

    Map<String, User> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    User getUser(String login) throws UserNotFoundException {
        User user = users.get(login);

        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    private HashMap<?, ?> loadData(String filePath) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)))) {

            File file = new File(filePath);
            if (!file.exists()) {
                return new HashMap<>();
            }

            return (HashMap<?, ?>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Ошибка чтения данных " + exception.getMessage());
            return new HashMap<>();
        }
    }

    private void saveData(String filePath, HashMap<?, ?> data) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            objectOutputStream.writeObject(data);

        } catch (IOException exception) {
            System.out.println("Ошибка сохранения данных");
        }
    }

    void close() {
        saveData(USER_FILE_PATH, users);
        saveData(LOGS_FILE_PATH, logs);
    }
}
