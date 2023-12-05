package movie.model;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {

    // fields
    private long imbd;
    private String title;
    private String director;
    private String genre;
    private LocalDate date;

    // constructor
    public Movie(long imbd, String title, String director, String genre, LocalDate date) {
        this.imbd = imbd;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.date = date;
    }

    // getter
    public long getImbd() {
        return imbd;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getDate() {
        return date;
    }

    // setter
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // method toString
    @Override
    public String toString() {
        return "Movie{" +
                "imbd=" + imbd +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", date=" + date +
                '}';
    }

    // methods equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        movie.model.Movie movie = (movie.model.Movie) o;
        return imbd == movie.imbd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imbd);
    }
}
