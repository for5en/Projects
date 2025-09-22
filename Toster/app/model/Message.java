package app.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Message implements Serializable
{
    private LocalDate date;
    private LocalTime time;
    private User user;
    private String content;

    public Message(User user, String content)
    {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.user = user;
        this.content = content;
    }

    public String toString()
    {
        String message;
        message = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " 
                + time.format(DateTimeFormatter.ofPattern("HH.mm")) + " "
                + user + "\n" + content;
        return message;
    }
}
