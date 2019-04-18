package Rental.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client
{

    private String name;
    private String surname;
    private int pesel;
    private int rentedCassettes;
    private List<Rent> rents;

    public Client(String dname, String dsurname, String dpesel)
    {
        name = dname;
        surname = dsurname;
        pesel = Integer.parseInt(dpesel);
        rentedCassettes = 0;
        rents = new ArrayList<>();
    }

    public String getFullName() { return name + " " + surname; }

    public int getPesel() { return pesel; }

    public String addRent(Cassette cassette)
    {
        if (rentedCassettes >= 3)
            return "Klient wypożyczył maksymalną liczbę kaset";

        Rent newRent = new Rent(cassette, this);
        Rent result = findRent(newRent);
        if (result == null)
        {
            rents.add(newRent);
            cassette.addRent(newRent);
            rentedCassettes++;
            return "Klient " + name + " " + surname + " wypożyczył film " + cassette.getFilmTitle();
        }
        return "Klient wypożyzczył już dany film";

    }

    public String returnRent(String title)
    {
        for (Rent rent : rents)
        {
            if (rent.getTitle().equals(title) && rent.isActive())
            {
                rentedCassettes--;
                return rent.end();
            }
        }
        return "Nie znaleziono takiego wypożyczenia";
    }

    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Client nowy = (Client) obj;
        if (pesel != nowy.pesel)
            return false;

        return true;
    }

    public Rent findRent(Rent rent)
    {
        int index;
        if ((index = rents.indexOf(rent)) != -1)
        {
            rent = rents.get(index);
            return rent;
        }
        return null;
    }
}