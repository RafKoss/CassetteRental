package Rental.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cassette
{

    private int number;
    private Film film;
    private List<Rent> rents;

    public Cassette(int dnumber, Film dfilm)
    {
        film = dfilm;
        number = dnumber;
        rents = new ArrayList<>();
    }

    public int getNumber()
    {
        return number;
    }

    public Film getFilm()
    {
        return film;
    }

    public String getFilmTitle() { return film.getTitle(); }

    public boolean equals(Object obj)
    {
        return number == ((Cassette) obj).getNumber();
    }

    public void addRent(Rent rent)
    {
        rents.add(rent);
    }

    public boolean isAvailable()
    {
        for (Rent rent : rents)
            if (rent.isActive())
                return false;

        return true;
    }
}