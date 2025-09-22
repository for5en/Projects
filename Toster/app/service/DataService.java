package app.service;

import app.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class DataService implements Serializable
{
    private HashMap<String,User> usersmap;
    private ArrayList<User> users;
    private ArrayList<User> m_het_bis;
    private ArrayList<User> m_hom_bis;
    private ArrayList<User> f_het_bis;
    private ArrayList<User> f_hom_bis;
    private ArrayList<User> m_hom_bis_f_het_bis;
    private ArrayList<User> m_het_bis_f_hom_bis;
    

    public DataService()
    {
        usersmap = new HashMap<String,User>();
        users = new ArrayList<User>();
        m_het_bis = new ArrayList<User>();
        m_hom_bis = new ArrayList<User>();
        f_het_bis = new ArrayList<User>();
        f_hom_bis = new ArrayList<User>();
        m_hom_bis_f_het_bis = new ArrayList<User>();
        m_het_bis_f_hom_bis = new ArrayList<User>();
    }

    public void addUser(User user, String login)
    {
        users.add(user);
        usersmap.put(login, user);
        
        if(user.getSex() == 'm' && user.getOrientation() == 'e') 
        {
            m_het_bis.add(user);
            m_het_bis_f_hom_bis.add(user);
        }
        else if(user.getSex() == 'm' && user.getOrientation() == 'o') 
        {
            m_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'm' && user.getOrientation() == 'b') 
        {
            m_het_bis.add(user);
            m_hom_bis.add(user);
            m_het_bis_f_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'f' && user.getOrientation() == 'e') 
        {
            f_het_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'f' && user.getOrientation() == 'o') 
        {
            f_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'f' && user.getOrientation() == 'b')
        {
            f_het_bis.add(user);
            f_hom_bis.add(user);
            m_het_bis_f_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
    }

    public void updateUser(User user, char orientation)
    {
        if(user.getSex() == 'm' && user.getOrientation() == 'e') 
        {
            m_het_bis.remove(user);
            m_het_bis_f_hom_bis.remove(user);
        }
        else if(user.getSex() == 'm' && user.getOrientation() == 'o') 
        {
            m_hom_bis.remove(user);
            m_hom_bis_f_het_bis.remove(user);
        }
        else if(user.getSex() == 'm' && user.getOrientation() == 'b') 
        {
            m_het_bis.remove(user);
            m_hom_bis.remove(user);
            m_het_bis_f_hom_bis.remove(user);
            m_hom_bis_f_het_bis.remove(user);
        }
        else if(user.getSex() == 'f' && user.getOrientation() == 'e') 
        {
            f_het_bis.remove(user);
            m_hom_bis_f_het_bis.remove(user);
        }
        else if(user.getSex() == 'f' && user.getOrientation() == 'o') 
        {
            f_hom_bis.remove(user);
            m_hom_bis_f_het_bis.remove(user);
        }
        else if(user.getSex() == 'f' && user.getOrientation() == 'b')
        {
            f_het_bis.remove(user);
            f_hom_bis.remove(user);
            m_het_bis_f_hom_bis.remove(user);
            m_hom_bis_f_het_bis.remove(user);
        }

        if(user.getSex() == 'm' && orientation == 'e') 
        {
            m_het_bis.add(user);
            m_het_bis_f_hom_bis.add(user);
        }
        else if(user.getSex() == 'm' && orientation == 'o') 
        {
            m_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'm' && orientation == 'b') 
        {
            m_het_bis.add(user);
            m_hom_bis.add(user);
            m_het_bis_f_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'f' && orientation == 'e') 
        {
            f_het_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'f' && orientation == 'o') 
        {
            f_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
        else if(user.getSex() == 'f' && orientation == 'b')
        {
            f_het_bis.add(user);
            f_hom_bis.add(user);
            m_het_bis_f_hom_bis.add(user);
            m_hom_bis_f_het_bis.add(user);
        }
    }

    public ArrayList<User> getCompatibles(User user)
    {
        if(user.getSex() == 'm' && user.getOrientation() == 'e') return f_het_bis;
        else if(user.getSex() == 'm' && user.getOrientation() == 'o') return m_hom_bis;
        else if(user.getSex() == 'm' && user.getOrientation() == 'b') return m_hom_bis_f_het_bis; 
        else if(user.getSex() == 'f' && user.getOrientation() == 'e') return m_het_bis; 
        else if(user.getSex() == 'f' && user.getOrientation() == 'o') return f_hom_bis;
        else return m_het_bis_f_hom_bis;
    }

    public HashMap<String,User> getUsersmap()
    {
        return usersmap;
    }

    public static DataService loadFromFile(String file) 
    {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) 
        {
            return (DataService) in.readObject();
        } 
        catch(IOException | ClassNotFoundException e) 
        {
            return new DataService();
        }
    }

    public void saveToFile(String file) 
    {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) 
        {
            out.writeObject(this);
        } 
        catch(IOException e) 
        {
            e.printStackTrace();
        }
    }
}
