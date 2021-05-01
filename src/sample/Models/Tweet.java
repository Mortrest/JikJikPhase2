package sample.Models;

import java.util.LinkedList;

public class Tweet {
    private final String text;
    private final String ID;
    private final String parent;
    private final String image;
    private final String date;
    String owner;
    LinkedList<String> users;
//    Date date;
    LinkedList<String> likes;
    boolean isRet;

    @Override
    public String toString() {
        return "{" +
                "ID='" + ID + '\'' +
                ", owner='" + owner + '\'' + ", text='" + text +
                '}';
    }

    public Tweet(String ID, String text,String parent, LinkedList<String > users, String date, LinkedList<String> likes, String owner,boolean isRet) {
        this.ID = ID;
        this.text = text;
        this.parent = parent;
        this.users = users;
        this.date = date;
        this.owner = owner;
        this.likes = likes;
        this.isRet = isRet;
        this.image = null;
    }

    public Tweet(String ID, String text,String parent, LinkedList<String > users, String date, LinkedList<String> likes, String owner,boolean isRet,String image) {
        this.ID = ID;
        this.text = text;
        this.parent = parent;
        this.users = users;
        this.date = date;
        this.owner = owner;
        this.likes = likes;
        this.isRet = isRet;
        this.image = image;
    }

    public String getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public String getID() {
        return ID;
    }

    public String getOwner() {
        return owner;
    }

    public String getDate() {
        return date;
    }

    public boolean isRet() {
        return isRet;
    }

    public LinkedList<String> getLikes() {
        return likes;
    }

    public LinkedList<String> getUsers() {
        return users;
    }

    public String getImage() {
        return image;
    }
}
