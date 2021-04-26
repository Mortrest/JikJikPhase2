package sample.Models;

public class Chat {
    private final String ID;
    private final String roomID;
    String text;
    private final String date;
    private final String owner;
    boolean edited;
    boolean forwarded;

    public Chat(String ID,String roomID, String text, String date, String owner,boolean edited,boolean forwarded) {
        this.ID = ID;
        this.roomID = roomID;
        this.text = text;
        this.date = date;
        this.owner = owner;
        this.edited = edited;
        this.forwarded = forwarded;
    }
    public String getID(){
        return ID;
    }

    public String getText() {
        return text;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getOwner() {
        return owner;
    }


    public String getDate() {
        return date;
    }
}
