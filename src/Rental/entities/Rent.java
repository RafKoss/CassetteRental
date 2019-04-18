package Rental.entities;

import java.time.LocalDate;
import java.time.Period;

public class Rent
{
    private LocalDate startRentDate;
    private Film film;
    private Cassette cassette;
    private Client client;
    private boolean active;


    public Rent(Cassette cassette, Client aclient)
    {
        startRentDate = LocalDate.now();
        this.cassette = cassette;
        client = aclient;
        film = cassette.getFilm();
        active = true;
    }

    public String getTitle()
    {
        return film.getTitle();
    }

    public boolean isActive()
    {
        return active;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (active == false)
            return false;

        Rent nowy = (Rent) obj;
        if (film.getTitle() == null)
        {
            if (nowy.film.getTitle() != null)
                return false;
        }

        if (!film.getTitle().equals(nowy.film.getTitle()))
            return false;

        return true;
    }


    public String end()
    {
        active = false;
        System.out.println(checkDate());
        return "Zakończono wypożyczenie filmu " + film.getTitle() + " dla klienta " + client.getFullName();
    }

    public String checkDate()
    {
        LocalDate currentDate = LocalDate.now();
        Period rentPeriod = Period.between(startRentDate, currentDate);
        int rentDays = rentPeriod.getDays();
        if (rentDays > 7)
            return "Przekroczono termin oddania kasety, opłata wynosi " + (rentDays - 7) + " zł";

        return "Nie przekroczono terminu oddania";
    }
}
