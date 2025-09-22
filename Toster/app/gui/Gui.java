package app.gui;

import app.model.*;
import app.service.*;
import app.gui.Gui;
import java.util.ArrayList;
import java.util.Scanner;

public class Gui 
{
    private Scanner scanner = new Scanner(System.in);
    private DataService dataService;
    private AuthService authService;

    public Gui(DataService data)
    {
        this.dataService = data;
        this.authService = new AuthService(data);
    }

    public void mainMenu()
    {
        System.out.print("\nHELLO IN TOSTER!\n");
        System.out.print("Choose 1 to login, 2 to create new Toster account, 0 to exit: ");
        String line = scanner.nextLine();
        char option = ' ';
        if(line.length() > 0) option = line.charAt(0);
        switch(option)
        {
            case '1':
                loginMenu();
            return;
            case '2':
                registerMenu();
            return;
            case '0':
                dataService.saveToFile("data.ser");
            return;
            default:
                System.out.print("Wrong option.\n");
                mainMenu();
        }
    }

    private void loginMenu()
    {
        System.out.print("\nLOGGING\n");
        System.out.print("Type in your login: ");
        String login = scanner.nextLine();
        
        if(!authService.doExists(login))
        {
            System.out.print("Wrong login, going back to menu...\n");
            mainMenu();
            return;
        }

        System.out.print("Type in your password: ");
        String password = scanner.nextLine();
    
        User user = authService.tryLogin(login, password);
        if(user == null)
        {
            System.out.print("Wrong password, going back to menu...\n");
            mainMenu();
            return;
        }

        System.out.print("Logged successfully as " + user + ".\n");
        userMenu(user);
    }

    private void registerMenu()
    {
        System.out.print("\nREGISTRATION\n");
        System.out.print("Type in your login: ");
        String login = scanner.nextLine();
        if(authService.doExists(login))
        {
            System.out.print("Login is taken, going back to menu...\n");
            mainMenu();
            return;
        }

        System.out.print("Type in your password: ");
        String password = scanner.nextLine();

        while(password.length() < 8)
        {
            System.out.print("Your password is too weak.\n");
            System.out.print("Type in your password: ");
            password = scanner.nextLine();
        }

        System.out.print("Type in your name: ");
        String name = scanner.nextLine();

        System.out.print("Type in your surname: ");
        String surname = scanner.nextLine();

        int age;
        String ageString;
        while(true)
        {
            System.out.print("Type in your age: ");
            ageString = scanner.nextLine();
            try 
            {
                age = Integer.parseInt(ageString);
                break;
            } 
            catch (NumberFormatException ee) 
            {
                System.out.print("Wrong age format.\n");
            }
        }
        

        System.out.print("Choose your sex, m - male, f - female: ");
        String sexString = scanner.nextLine();
        char sex = ' ';
        if(sexString.length() > 0) sex = sexString.charAt(0);
        while(sex != 'm' && sex != 'f')
        {
            System.out.print("Wrong option.\n");
            System.out.print("Choose your sex, m - male, f - female: ");
            sexString = scanner.nextLine();
            if(sexString.length() > 0) sex = sexString.charAt(0);
        }

        System.out.print("Choose your orientation, e - heterosexual, o - homosexual, b - bisexual: ");
        String orientationString = scanner.nextLine();
        char orientation = ' ';
        if(orientationString.length() > 0) orientation = orientationString.charAt(0);
        while(orientation != 'e' && orientation != 'o' && orientation != 'b')
        {
            System.out.print("Wrong option.\n");
            System.out.print("Choose your orientation, e - heterosexual, o - homosexual, b - bisexual: ");
            orientationString = scanner.nextLine();
            if(orientationString.length() > 0) orientation = orientationString.charAt(0);
        }

        System.out.print("Type in your city: ");
        String home = scanner.nextLine();

        System.out.print("Tell about yourself: ");
        String description = scanner.nextLine();

        User user = new User(name, surname, age, sex, orientation, description, home, password);
        dataService.addUser(user, login);

        System.out.print("Registered successfully!\n");
        mainMenu();
    }

    private void userMenu(User user)
    {
        System.out.print("\nMAIN MENU\n");
        System.out.print("Choose 1 to find new people, 2 to chat with your matches, 3 to change your information, 0 to logout: ");
        String line = scanner.nextLine();
        char option = ' ';
        if(line.length() > 0) option = line.charAt(0);
        switch(option)
        {
            case '1':
                MatchService match = new MatchService(user, dataService.getCompatibles(user));
                System.out.print("\nMATCH FINDER\n");
                matchFinderMenu(user, match);
            return;
            case '2':
                chatsMenu(user);
            return;
            case '3':
                changeInfoMenu(user);
            return;
            case '0':
                System.out.print("Logging out...\n");
                mainMenu();
            return;
            default:
                System.out.print("Wrong option.\n");
                userMenu(user);
            return;
        }
    }

    private void changeInfoMenu(User user)
    {
        System.out.print("\nCHANGING INFO\n");

        int age = user.getAge();
        char orientation = user.getOrientation();
        String home = user.getHome();
        String description = user.getDescription();

        System.out.print("Choose 1 to change age, 2 to change orientation 3 to change city, 4 to change description, 0 to go back: ");
        String line = scanner.nextLine();
        char option = ' ';
        if(line.length() > 0) option = line.charAt(0);
        switch(option)
        {
            case '1':
                String ageString;
                while(true)
                {
                    System.out.print("Type in your age: ");
                    ageString = scanner.nextLine();
                    try 
                    {
                        age = Integer.parseInt(ageString);
                        break;
                    } 
                    catch (NumberFormatException ee) 
                    {
                        System.out.print("Wrong age format.\n");
                    }
                }
            break;
            case '2':
                System.out.print("Choose your orientation, e - heterosexual, o - homosexual, b - bisexual: ");
                String orientationString = scanner.nextLine();
                orientation = ' ';
                if(orientationString.length() > 0) orientation = orientationString.charAt(0);
                while(orientation != 'e' && orientation != 'o' && orientation != 'b')
                {
                    System.out.print("Wrong option.\n");
                    System.out.print("Choose your orientation, e - heterosexual, o - homosexual, b - bisexual: ");
                    orientationString = scanner.nextLine();
                    if(orientationString.length() > 0) orientation = orientationString.charAt(0);
                }
            break;
            case '3':
                System.out.print("Type in your city: ");
                home = scanner.nextLine();
            break;
            case '4':
                System.out.print("Tell about yourself: ");
                description = scanner.nextLine();
            break;
            case '0':
                System.out.print("Going back...\n");
                userMenu(user);
            return;
            default:
                System.out.print("Wrong option, going back...\n");
                userMenu(user);
            return;
        }

        dataService.updateUser(user, orientation);
        user.changeInfo(age, orientation, description, home);

        System.out.print("Change successful!\n");
        changeInfoMenu(user);
    }

    private void matchFinderMenu(User user, MatchService match)
    {   
        User temp = match.randomMatch();
        if(temp == null)
        {
            System.out.print("Could not found any matches, going back to menu...\n");
            userMenu(user);
            return;
        }

        System.out.print(temp.info() + "\n");

        System.out.print("Choose 1 for yes, 2 for no: ");
        String line = scanner.nextLine();
        char option = ' ';
        if(line.length() > 0) option = line.charAt(0);
        while(option != '1' && option != '2')
        {
            System.out.print("Wrong option.\nChoose 1 for yes, 2 for no: ");
            line = scanner.nextLine();
            if(line.length() > 0) option = line.charAt(0);
        }

        if(option == '1') 
        {
            user.addLike(temp);
            if(temp.isLiked(user)) System.out.print("You found a match!\n");
        }
        else user.addDislike(temp);
        
        System.out.print("Choose 1 to continue, 0 to go back: ");
        line = scanner.nextLine();
        if(line.length() > 0) option = line.charAt(0);

        while(option != '1' && option != '0')
        {
            System.out.print("Wrong option.\nChoose 1 to continue, 0 to go back: ");
            line = scanner.nextLine();
            if(line.length() > 0) option = line.charAt(0);
        }

        if(option == '1') matchFinderMenu(user, match);
        else userMenu(user);
    }

    private void chatsMenu(User user)
    {
        System.out.print("\nCHATS MENU\n");
        ArrayList<Chat> chats = user.getChats();
        int i = 0;
        
        if(chats.size() < 1)
        {
            System.out.print("Could not find any chats, going back to menu...\n");
            userMenu(user);
            return;
        }

        for(Chat chat : chats)
        {
            System.out.print((i+1) + ". " + chat + "\n");
            i++;
        }

        int option;
        String line;
        while(true)
        {
            System.out.print("Choose chat number or 0 to go back: ");
            line = scanner.nextLine();
            try 
            {
                option = Integer.parseInt(line);
                break;
            } 
            catch (NumberFormatException ee) 
            {
                System.out.print("Wrong number.\n");
            }
        }

        if(option == 0)
        {
            userMenu(user);
            return;
        }
        else if(option <= chats.size())
        {
            chattingMenu(user, chats.get(option-1));
        }

    }

    private void chattingMenu(User user, Chat chat)
    {
        System.out.print("\n" + chat.chatContent() + "\n");
        System.out.print("Choose 1 to write a message, 0 to go back: ");
        String line = scanner.nextLine();
        char option = ' ';
        if(line.length() > 0) option = line.charAt(0);

        if(option == '0')
        {
            chatsMenu(user);
            return;
        }
        else if(option != '1')
        {
            System.out.print("Wrong option, going back...\n");
            chatsMenu(user);
            return;
        }

        System.out.print("New message: ");
        line = scanner.nextLine();

        Message message = new Message(user, line);
        chat.newMessage(message);

        chattingMenu(user, chat);
    }
}
