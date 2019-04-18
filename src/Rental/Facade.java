package Rental;

import Rental.entities.Client;
import Rental.entities.Film;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Facade
{

    private static Scanner scanner = new Scanner(System.in);
    ArrayList<Client> clients;
    ArrayList<Film> films;

    public Facade()
    {
        films = new ArrayList<>();
        clients = new ArrayList<>();
    }


    public String addCassette(String[] data)
    {
        Film newFilm = new Film(data[0], null, null);
        Film result = findFilm(newFilm);
        if (result != null)
        {
            result.addCassette();
            return "Dodano kasete filmu: " + data[0];
        }
        return "Nie ma takiego filmu";
    }


    public String addFilm(String[] data)
    {
        Film newFilm = new Film(data[0], data[1], data[2]);
        Film result = findFilm(newFilm);
        if (result == null)
        {
            films.add(newFilm);
            return "Dodano film: " + data[0] + ", Reżyser: " + data[1] + ", Gatunek: " + data[2];
        }
        return "Film jest już w wypożczalni";
    }


    public Film findFilm(Film film)
    {
        int index;
        if ((index = films.indexOf(film)) != -1)
        {
            film = films.get(index);
            return film;
        }
        return null;
    }


    public String addClient(String[] data)
    {
        Client newClient = new Client(data[0], data[1], data[2]);
        Client result = findClient(data[2]);
        if (result == null)
        {
            clients.add(newClient);
            return "Dodano klienta: " + data[0] + " " + data[1] + ", PESEL: " + data[2];
        }
        return "Klient istnieje już w bazie";

    }

    public Client findClient(String pesel)
    {
        int index;
        Client client = new Client(null, null, pesel);
        if ((index = clients.indexOf(client)) != -1)
        {
            client = clients.get(index);
            return client;
        }
        return null;
    }


    public String addRent(String data[])
    {
        Client newClient = findClient(data[0]); //szukanie klienta po peselu
        if (newClient == null)
            return "Nie znaleziono takiego klienta";

        Film searchPattern = new Film(data[1], null, null);
        Film newFilm = findFilm(searchPattern); //szukanie filmu po tytule
        if (newFilm == null)
            return "Nie znaleziono takiego filmu";

        boolean result = newFilm.findAvailableCassette();
        if (!result)
            return "Nie znaleziono dostępnej kasety";

        return newClient.addRent(newFilm.getCasseteBuffer());
    }

    public String returnRent(String data[])
    {
        Client newClient = findClient(data[0]); //szukanie klienta po peselu
        if (newClient == null)
            return "Nie znaleziono takiego klienta";

        return newClient.returnRent(data[1]);
    }

    public void showClients()
    {
        System.out.println("Lista klientów:");
        for (Client client : clients)
        {
            System.out.println("Imię i nazwizko: " + client.getFullName() + "    PESEL: " + client.getPesel());
        }
    }

    public void showFilms()
    {
        System.out.println("Lista filmów:");
        for (Film film : films)
        {
            System.out.println("Tytuł: " + film.getTitle() + "    Reżyseria: " + film.getDirector() + "    Gatunek: " + film.getGenre());
        }
    }

    public static void main(String[] args)
    {
        Facade fas = new Facade();
        scanner = new Scanner(System.in);
        int choice = 0;
        String[] data = new String[3];
        while (choice != 8)
        {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Dodaj klienta");
            System.out.println("2. Dodaj film");
            System.out.println("3. Dodaj kasete do filmu");
            System.out.println("4. Wyświetl klientów");
            System.out.println("5. Wyświetl filmy");
            System.out.println("6. Wypożyczenie kasety");
            System.out.println("7. Zwrot kasety");
            System.out.println("8. Wyjdź");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("Podaj imię klienta:");
                    data[0] = scanner.nextLine();
                    System.out.println("Podaj nazwisko klienta:");
                    data[1] = scanner.nextLine();
                    System.out.println("Podaj PESEL klienta:");
                    data[2] = scanner.nextLine();
                    System.out.println(fas.addClient(data));
                    break;

                case 2:
                    System.out.println("Podaj tytuł filmu:");
                    data[0] = scanner.nextLine();
                    System.out.println("Podaj imię i nazwisko reżysera:");
                    data[1] = scanner.nextLine();
                    System.out.println("Podaj gatunek:");
                    data[2] = scanner.nextLine();
                    System.out.println(fas.addFilm(data));
                    break;

                case 3:
                    System.out.println("Podaj tytuł filmu:");
                    data[0] = scanner.nextLine();
                    System.out.println(fas.addCassette(data));
                    break;

                case 4:
                    fas.showClients();
                    break;

                case 5:
                    fas.showFilms();
                    break;

                case 6:
                    System.out.println("Podaj PESEL klienta:");
                    data[0] = scanner.nextLine();
                    System.out.println("Podaj tytuł filmu:");
                    data[1] = scanner.nextLine();
                    System.out.println(fas.addRent(data));
                    break;

                case 7:
                    System.out.println("Podaj PESEL klienta:");
                    data[0] = scanner.nextLine();
                    System.out.println("Podaj tytuł filmu:");
                    data[1] = scanner.nextLine();
                    System.out.println(fas.returnRent(data));
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Zły wybór");
                    break;
            }
            Arrays.fill(data,null);
        }
    }
}
