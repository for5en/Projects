package app.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Chat implements Serializable
{
    private String name;
    private ArrayList <Message> messages = new ArrayList <Message>();
    private ArrayList <String> chat = new ArrayList <String>();

    public Chat(User userA, User userB)
    {
        this.name = userA + "'s and " + userB + "'s chat";
    }

    public String toString()
    {
        return name;
    }

    public String chatContent()
    {
        String chat = String.join("\n", this.chat);
        return name + "\n" + chat;
    }

    public void newMessage(Message message)
    {
        messages.add(message);
        chat.add(message.toString());
    }
}
