package Messageboard;


import java.util.List;



public class Message{
    private int id;
    private int pid;
    private String username;
    private String text;
    private List<Message> childContent;

    public Message(){
    }

    public Message(String username, String text, int pid){
        this.username = username;
        this.text = text;
        this.pid = pid;
    }


}
