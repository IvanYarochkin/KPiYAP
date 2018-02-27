package by.bsac.lab4.entity;

import java.time.LocalDate;
import java.util.List;

public class Film {
    private long id;
    private String name;
    private LocalDate releaseDate;
    private String country;
    private Staff producer;
    private List<Staff> actors;

    public Film(long id, String name, LocalDate releaseDate, String country) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.country = country;
    }

    public Film(long id, String name, LocalDate releaseDate, String country, Staff producer, List<Staff> actors) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.country = country;
        this.producer = producer;
        this.actors = actors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Staff getProducer() {
        return producer;
    }

    public void setProducer(Staff producer) {
        this.producer = producer;
    }

    public List<Staff> getActors() {
        return actors;
    }

    public void setActors(List<Staff> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Film) ) return false;

        Film film = (Film) o;

        if ( id != film.id ) return false;
        if ( name != null ? !name.equals(film.name) : film.name != null ) return false;
        if ( releaseDate != null ? !releaseDate.equals(film.releaseDate) : film.releaseDate != null ) return false;
        if ( country != null ? !country.equals(film.country) : film.country != null ) return false;
        if ( producer != null ? !producer.equals(film.producer) : film.producer != null ) return false;
        return actors != null ? actors.equals(film.actors) : film.actors == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", country='" + country + '\'' +
                ", producer=" + producer +
                ", actors=" + actors +
                '}';
    }
}
