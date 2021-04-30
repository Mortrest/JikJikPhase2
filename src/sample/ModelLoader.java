package sample;

import sample.Config;
import sample.Models.*;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;

public class ModelLoader {
    private final File userDirectory;
    /**
     * DO NOT CHANGE ANYTHING IN CONSTRUCTOR.
     */
    public ModelLoader() {
        userDirectory = Config.getConfig("mainConfig").getProperty(File.class, "userDirectory");
        if (!userDirectory.exists()) userDirectory.mkdirs();
    }



    public void save(LinkedList<?> users,String name) {
        try {
            File file = getUserFile(name+".txt");
            assert file != null;
            PrintStream printStream = new PrintStream(file);
            Gson gson = new Gson();
            for (Object us : users) {
                String jsonInString = gson.toJson(us);
                printStream.print(jsonInString);
                printStream.println();
            }

            printStream.close();
            log("ModelLoader saved");
        } catch (FileNotFoundException e) {
            log("ModelLoader-ERROR Occurred while saving user");
        }
    }

    static LinkedList<String> logs = new LinkedList<>();
    public void log(String msg) {
        try {
            File file = getUserFile("Log"+".txt");
            assert file != null;
            PrintStream printStream = new PrintStream(file);
            logs.add(LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+":"+LocalTime.now().getSecond()+" - "+msg);
            for (String str : logs){
                printStream.println(str);
            }
            printStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }


     public  LinkedList<Tweet> loadTweets() {
        try {
            File userFile = getUserFile("Tweets.txt");
            if (userFile == null){
                System.out.println("not found");
            } else {
                Gson gson = new Gson();
                Scanner ss = new Scanner(userFile);
                LinkedList<Tweet> tweets = new LinkedList<>();
                while (ss.hasNextLine()) {
                    Tweet tweet = gson.fromJson(ss.nextLine(),Tweet.class);
                    tweets.add(tweet);
                }
                log("ModelLoader-Tweets Loaded");
                return tweets;
            }
        } catch (Exception e) {
            log("ModelLoader-ERROR Occurred when loading tweets");
            System.out.println(e);
        }
        return null;
    }

    public LinkedList<User> loadUsers() {
        try {
            File userFile = getUserFile("Users.txt");
            if (userFile == null){
                System.out.println("not found");
            } else {
                Gson gson = new Gson();
                Scanner ss = new Scanner(userFile);
                LinkedList<User> users = new LinkedList<>();
                while (ss.hasNextLine()) {
                    User user = gson.fromJson(ss.nextLine(), User.class);
                    users.add(user);
                }
                log("ModelLoader-Users Loaded");
                return users;
            }
        } catch (Exception e) {
            log("ModelLoader-ERROR Occurred when loading users");
            System.out.println(e);
        }
        return null;
    }

    public LinkedList<Chat> loadChats() {
        try {
            File userFile = getUserFile("Chats.txt");
            if (userFile == null){
                System.out.println("not found");
            } else {
                Gson gson = new Gson();
                Scanner ss = new Scanner(userFile);
                LinkedList<Chat> chats = new LinkedList<>();
                while (ss.hasNextLine()) {
                    Chat chat = gson.fromJson(ss.nextLine(), Chat.class);
                    chats.add(chat);
                }
                log("ModelLoader-Chats Loaded");
                return chats;
            }
        } catch (Exception e) {
            log("ModelLoader-ERROR Occurred when loading chats");
            System.out.println(e);
        }
        return null;
    }

    public LinkedList<Room> loadRooms() {
        try {
            File userFile = getUserFile("Rooms.txt");
            if (userFile == null){
                System.out.println("not found");
            } else {
                Gson gson = new Gson();
                Scanner ss = new Scanner(userFile);
                LinkedList<Room> rooms = new LinkedList<>();
                while (ss.hasNextLine()) {
                    Room room = gson.fromJson(ss.nextLine(), Room.class);
                    rooms.add(room);
                }
                log("ModelLoader-Rooms Loaded");
                return rooms;
            }
        } catch (Exception e) {
            System.out.println(e);
            log("ModelLoader-ERROR Occurred when loading rooms");
        }
        return null;
    }

    public LinkedList<Notif> loadNotifs(){
        try {
            File userFile = getUserFile("Notifs.txt");
            if (userFile == null){
                System.out.println("not found");
            } else {
                Gson gson = new Gson();
                Scanner ss = new Scanner(userFile);
                LinkedList<Notif> notifs = new LinkedList<>();
                while (ss.hasNextLine()) {
                    Notif notif = gson.fromJson(ss.nextLine(), Notif.class);
                    notifs.add(notif);
                }
                log("ModelLoader-Notifs Loaded");
                return notifs;
            }
        } catch (Exception e) {
            System.out.println(e);
            log("ModelLoader-ERROR Occurred when loading notifs");

        }
        return null;
    }

    private File getUserFile(String name) {
        File directory = new File(userDirectory.getAbsolutePath());
        File[] d = directory.listFiles();
        if (d != null) {
            for (File dd : d) {
                if (dd.getName().equals(name)) {
                    return dd;
                }
            }
            return null;
        } else {
            return null;

        }
    }

}
