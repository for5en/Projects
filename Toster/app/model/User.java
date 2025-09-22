package app.model;

import java.util.ArrayList;
import java.io.Serializable;

public class User implements Serializable
{   
    // main
    private String password;
    private String name;
    private String surname;
    private int age;
    private char sex; // 'm'-male, 'f'-female
    private char orientation; // 'e'-heterosexual, 'o'-homosexual, 'b'-bisexual
    private String description;
    private String home;

    public User(String name, String surname, int age, char sex, char orientation, String description, String home, String password)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.orientation = orientation;
        this.description = description;
        this.home = home;
        this.password = password;
    }

    public String toString()
    {
        return (name + " " + surname);
    }

    public String info()
    {
        return (name + "\nage: " + age + "\nfrom: " + home + "\n" + description);
    }

    public void changeInfo(int age, char orientation, String description, String home)
    {
        this.age = age;
        this.orientation = orientation;
        this.description = description;
        this.home = home;
    }

    // sub
    private ArrayList <User> likes = new ArrayList <User>(); 
    private ArrayList <User> dislikes = new ArrayList <User>();
    private ArrayList <User> matches = new ArrayList <User>();
    private ArrayList <Chat> chats = new ArrayList <Chat>();

    public boolean isCompatible(User user)
    {
        if(sex == 'm' && orientation == 'e' && user.sex == 'f') return true;
        else if(sex == 'f' && orientation == 'e' && user.sex == 'm') return true;
        else if(sex == 'm' && orientation == 'o' && user.sex == 'm') return true;
        else if(sex == 'f' && orientation == 'o' && user.sex == 'f') return true;
        else if(orientation == 'b') return true;
        else return false;
    }

    public boolean isLiked(User user)
    {
        if(likes.contains(user)) return true;
        else return false;
    }

    public boolean isDisliked(User user)
    {
        if(dislikes.contains(user)) return true;
        else return false;
    }

    public boolean checkPassword(String password)
    {
        if(this.password.equals(password)) return true;
        else return false;
    }

    public int getAge()
    {
        return age;
    }

    public char getSex()
    {
        return this.sex;
    }

    public char getOrientation()
    {
        return this.orientation;
    }

    public String getHome()
    {
        return home;
    }

    public String getDescription()
    {
        return description;
    }

    public ArrayList <Chat> getChats()
    {
        return chats;
    }

    public void addLike(User user)
    {
        if(!likes.contains(user)) likes.add(user);
        if(user.isLiked(this))
        {
            Chat chat = new Chat(this, user);
            this.addMatch(user, chat);
            user.addMatch(this, chat);
        }
    }

    public void addDislike(User user) // userA.doesLike(UserB)
    {
        if(!dislikes.contains(user)) dislikes.add(user);
    }

    private void addMatch(User user, Chat chat)
    {
        matches.add(user);
        chats.add(chat);
    }
}
