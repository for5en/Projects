package app;

import app.gui.Gui;
import app.service.DataService;

public class Toster 
{
    public static void main(String[] args) {
      DataService data = DataService.loadFromFile("data.ser");
      Gui gui = new Gui(data);
      gui.mainMenu();
   }
}
