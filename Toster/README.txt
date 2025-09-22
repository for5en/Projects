Toster to projekt aplikacji randkowej z GUI konsolowym pozwalajacy na laczenie uzytkownikow i umozliwianie im prowadzenia wspolnego chatu. Obsluguje mechanizm rejestracji i logowania, a takze zapisuje i przechowuje wszystkie dane uzytkownikow.

Srodowisko deweloperskie:
- Jezyk: Java
- IDE: VS Code
- Biblioteki:
    - java.util
    - java.io
    - java.time


Uruchomienie:
cd Toster
java app.Toster

Kompilowanie:
cd Toster
javac app/Toster.java app/gui/*.java app/model/*.java app/service/*.java


Opisy klas:

- Message
Podczas tworzenia zapisuje aktualna date i godzine, zawiera tresc wiadomosci i uzytkownika ktory ja napisal.

- Chat
Jest zbiorem wiadomosci i przechowuje uzytkownikow ktorzy prowadza ten chat.

- User
Przechowuje wszystkie dane uzytkownika wraz z jego chatami i uzytkownikami ktorych lubi/nie lubi.

- DataService
Sluzy do zapisywania i odczytywania danych z pliku (mozna to interpretowac jako dane calego serwera uzytkownikow).

- MatchService
Sluzy do losowania uzytkownika w wyszukiwaniu.

- AuthService
Na podstawie podanych danych (DataService) obsluguje funkcjonalnosc logowania i rejestracji nowych uzytkownikow.

- Gui
Na podstawie podanych danych (DataService) obsluguje cale gui konsolowe.


Podstawowe komendy:

# odczytuje dane z pliku "nazwa.ser", lub tworzy ten plik jesli nie istnieje
DataService data = new DataService.loadFromFile("nazwa.ser");

# tworzy nowe gui oparte na podanych danych
Gui gui = new Gui(data);

# otwiera glowne menu konsolowe podanego gui
gui.mainMenu();

# menu implementowane przez gui
gui.loginMenu();
gui.registerMenu();
gui.userMenu(user);
gui.changeInfoMenu(user);
gui.chatsMenu(user);
gui.chattingMenu(user, chat);
gui.matchFinderMenu(user, match: MatchService);

# zwraca liste uzytkownikow kompatybilnych z podanym user
data.getCompatibles(user)

# na podstawie podanych user i listy uzytkownikow tworzy match sluzacy do zwracania losowych kompatybilnych z user uzytkownikow
match = new MatchService(user, users);

# zwraca losowego uzytkownika
match.randomMatch();
# mozliwosc rozwoju o mechanizm dobierania wedlug preferencji


W programie juz sa przykladowe dane ("data.ser"), w "przyklad.txt" sa dane wprowadzonych uzytkownikow.


