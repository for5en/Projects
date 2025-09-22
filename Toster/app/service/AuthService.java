package app.service;

import app.model.*;
import java.util.HashMap;

public class AuthService 
{
    private DataService data;
    private HashMap<String,User> usersmap;

    public AuthService(DataService data)
    {
        this.data = data;
        this.usersmap = data.getUsersmap();
    }

    public boolean doExists(String login)
    {
        if(usersmap.containsKey(login)) return true;
        else return false;
    }

    public User tryLogin(String login, String password)
    {
        if(usersmap.containsKey(login))
        {
            User temp = usersmap.get(login);
            if(temp.checkPassword(password)) return temp;
            else return null;
        }
        else return null;
    }

    public void register(String login, User user)
    {
        if(!usersmap.containsKey(login)) data.addUser(user, login);
    }
}
