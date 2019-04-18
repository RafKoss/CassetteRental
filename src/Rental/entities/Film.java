package Rental.entities;

import java.util.ArrayList;
import java.util.List;

public class Film
{

    private String title;
    private String director;
    private String genre;
    private double cost;
    public int numberOfCassettes;
    public int indexNextCassete;
    public List<Cassette> cassettes;
    private Cassette casseteBuffer;

    public Film(String dtitle, String ddirector, String dgenre)
    {
        title = dtitle;
        director = ddirector;
        genre = dgenre;
        cost = 10;
        numberOfCassettes = 0;
        indexNextCassete = 1;
        cassettes = new ArrayList<>();
    }

    public String getDirector() { return director; }

    public String getGenre() { return genre; }

    public Cassette getCasseteBuffer() { return casseteBuffer; }

    public String getTitle()
    {
        return title;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Film newFilm = (Film) obj;
        if (title == null)
        {
            if (newFilm.title != null)
                return false;
        }

        if (!title.equals(newFilm.title))
            return false;

        return true;
    }


    public Cassette findCassette(Cassette cassette)
    {
        int index;
        if ((index = cassettes.indexOf(cassette)) != -1)
        {
            cassette = cassettes.get(index);
            return cassette;
        }
        return null;
    }


    public boolean findAvailableCassette()
    {
        for (Cassette cassette : cassettes)
            if (cassette.isAvailable())
            {
                casseteBuffer = cassette;
                return true;
            }
        return false;
    }


    public void addCassette()
    {
        Cassette newCassette = new Cassette(indexNextCassete, this);
        if (findCassette(newCassette) == null)
        {
            cassettes.add(newCassette);
        }
        indexNextCassete++;
        numberOfCassettes++;
    }
}