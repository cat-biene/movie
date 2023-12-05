package movie.dao;

import movie.model.Movie;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class MoviesCollectionImpl implements MoviesCollection {
    // поле для хранения объекта типа List
    private List<Movie> moviesList; // список фильмов

    // конструктор
    public MoviesCollectionImpl() {
        moviesList = new ArrayList<>(); // делаем этот List уже конкретным ArrayList
    }

    // заполняем ArrayList объектами из списка фильмов List<Movie> movies, который подаем на вход
    public MoviesCollectionImpl(List<Movie> movies) {
        this();
        for (Movie movie : movies) {
            addMovie(movie); // метод ArrayList, не отсекает дубликаты на этапе создания колекции
        }
    }

    @Override
    public boolean addMovie(Movie movie) {
        // null не добавляем
        if (movie == null) {
            return false;
        }
        // нельзя добавить фильм с таким же imdb
        if (findById(movie.getImbd()) != null) {
            return false;
        }
        // или еще можно так
        if (moviesList.contains(movie)) {
            return false;
        }
        // добавляем фильм
        moviesList.add(movie);
        return true;
    }

    @Override
    public Movie removeMovie(long imdb) {
        Movie victim = findById(imdb);
        if (victim != null) {
            moviesList.remove(victim);
            return victim;
        }
        return null;
    }

    @Override
    public Movie findById(long imdb) {
        // в moviesList надо найти элемент, у которого совпадает imdb с искомым
        for (Movie movie : moviesList) {
            if (movie.getImbd() == imdb) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public Iterable<Movie> findByGenre(String genre) {
        List<Movie> findByGenre = new ArrayList<>();
        for (Movie movie : moviesList) {
            if (movie.getGenre().equals(genre)) {
                findByGenre.add(movie);
            }
        }
        return findByGenre;
    }

    @Override
    public Iterable<Movie> findByDirector(String director) {

        List<Movie> findByDirector = new ArrayList<>();
        for (Movie movie : moviesList) {
            if (movie.getDirector().equals(director)) {
                findByDirector.add(movie);
            }
        }
        return findByDirector;
    }


    @Override
    public Iterable<Movie> findMoviesCreatedBetweenDates(LocalDate from, LocalDate to) {
        List<Movie> findMoviesCreated = new ArrayList<>();
        for (Movie movie : moviesList) {
            LocalDate createDate = movie.getDate();
            if(!createDate.isBefore(from) && !createDate.isAfter(to)) {
                findMoviesCreated.add(movie);
            }
        }
        return findMoviesCreated;
    }

    @Override
    public int totalQuantity() {
        return moviesList.size();
    }

    @Override
    public Iterator<Movie> iterator() {
        return moviesList.iterator();
    }

}
