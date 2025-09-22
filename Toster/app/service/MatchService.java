package app.service;

import app.model.*;
import java.util.ArrayList;

public class MatchService 
{
    private User user;
    private ArrayList<User> users;
    private int index = 0;

    public MatchService(User user, ArrayList<User> users)
    {
        this.user = user;
        this.users = users;
    }

    public User randomMatch()
    {
        while(index < users.size())
        {
            User temp = users.get(index);
            if(temp != user && !user.isDisliked(temp) && !temp.isDisliked(user) && !user.isLiked(temp) && user.isCompatible(temp) && temp.isCompatible(user)) return temp;
            index++;
        }
        return null;
    }
}
