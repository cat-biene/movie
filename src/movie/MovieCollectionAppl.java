package movie;

import movie.model.Movie;
import movie.dao.MoviesCollection;
import movie.dao.MoviesCollectionImpl;

import java.time.LocalDate;
import java.util.List;

public class MovieCollectionAppl {

    public static void main(String[] args) {
        MoviesCollection moviesCollection;

        moviesCollection = new MoviesCollectionImpl(List.of(
                new Movie(1, "Mad Max: Fury Road", "George Miller", "Action", LocalDate.of(2015,5,7)),
                new Movie(2, "The Three of life", "Terrence Malick", "Fantasy", LocalDate.of(2011,5,16)),
                new Movie(3, "Gisaengchung", "Bong Joon Ho", "Drama", LocalDate.of(2019,1,1)),
                new Movie(4, "Boyhood", "Richard Linklater", "Drama", LocalDate.of(2014,1,19)),
                new Movie(5, "Oppenheimer", "Christopher Nolan", "Biography", LocalDate.of(2023,1,1))
        ));
        System.out.println("===================Initial list===========================");
        for (Movie movie : moviesCollection){
            System.out.println(movie);
        }

        moviesCollection.addMovie(new Movie(6, "Like Someone in Love", "Abbas Kiarostami", "Drama", LocalDate.of(2012,9,15)));
        moviesCollection.addMovie(new Movie(7, "If Beale Street Could Talk", "Barry Jenkins", "Drama", LocalDate.of(2018,9,9)));
        moviesCollection.addMovie(new Movie(8, "Oppenheimer", "Christopher Nolan", "Biography", LocalDate.of(2023,1,1)));
        moviesCollection.addMovie(new Movie(9, "Arrival", "Denis Villeneuve", "Adventure", LocalDate.of(2016,9,1)));
        moviesCollection.addMovie(new Movie(10, "Spider-Man: Across the Spider-Verse", "Joaquim Dos Santos", "Animation", LocalDate.of(2023,1,1)));

        System.out.println("===================New List After Adding===========================");
        for (Movie movie : moviesCollection){
            System.out.println(movie);
        }

        LocalDate from = LocalDate.now().minusYears(6);
        LocalDate to = LocalDate.now().minusYears(0);


        Iterable<Movie> movieBevin = moviesCollection.findMoviesCreatedBetweenDates(from, to);

        System.out.println("===================List of films made over the past 5 years===========================");

        for (Movie movie : movieBevin){
            System.out.println(movie);
        }

        System.out.println("===================Find By Id===========================");
        Movie foundMovie = moviesCollection.findById(8);
        if (foundMovie != null) {
            System.out.println(foundMovie);
        } else {
            System.out.println("Movie not found");
        }

        Movie foundMovie1 = moviesCollection.findById(2);
        if (foundMovie1 != null) {
            System.out.println(foundMovie1);
        } else {
            System.out.println("Movie not found");
        }

        System.out.println("===================Find By Genre===========================");
        Iterable<Movie> findMovie = moviesCollection.findByGenre("Drama");

        for (Movie movie : findMovie) {
            System.out.println(movie);
        }

        System.out.println("===================Find By Director===========================");
        Iterable<Movie> findMovies = moviesCollection.findByDirector("George Miller");

        for (Movie movie : findMovies) {
            System.out.println(movie);
        }

        System.out.println("===================New List After Deletion===========================");
        moviesCollection.removeMovie(2);
        moviesCollection.removeMovie(4);
        moviesCollection.removeMovie(6);
        moviesCollection.removeMovie(8);

        for (Movie movie : moviesCollection) {
            System.out.println(movie);
        }

    }
}
