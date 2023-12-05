package movie.test;

import movie.dao.MoviesCollection;
import movie.dao.MoviesCollectionImpl;
import movie.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoviesCollectionImplTest {

    MoviesCollection moviesCollection;
    static final LocalDate now = LocalDate.now();

    @BeforeEach
    void setUp() {
        moviesCollection = new MoviesCollectionImpl(List.of(
                new Movie(101, "T1", "D1", "G1", now.minusYears(1)),
                new Movie(102, "T2", "D2", "G2", now.minusYears(2)),
                new Movie(103, "T3", "D1", "G1", now.minusYears(3)),
                new Movie(104, "T4", "D4", "G2 ", now.minusYears(4))
        ));
    }

    @Test
    void testConstructor(){
        // вызываем конструктор с двумя одинаковыми объектами
        moviesCollection = new MoviesCollectionImpl(List.of(
                new Movie(1, "T1","D1", "G1", now.minusYears(1)),
                new Movie(1, "T1","D1", "G1", now.minusYears(1))
        ));
        // проверяем, что добавился только один объект
        assertEquals(1,moviesCollection.totalQuantity());
    }
    @Test
    void addMovieTest() {
        assertFalse(moviesCollection.addMovie(null));
        assertFalse(moviesCollection.addMovie(new Movie(102, "T2", "G2", "D2", now.minusYears(2))));
        assertEquals(4, moviesCollection.totalQuantity());
        assertTrue(moviesCollection.addMovie(new Movie(105, "T4", "G4", "D3", now.minusYears(5))));
        assertEquals(5, moviesCollection.totalQuantity());
    }

    @Test
    void removeMovieTest() {
        assertEquals(new Movie(101, "T1", "D1", "G1", now.minusYears(1)), moviesCollection.removeMovie(101));
        assertEquals(3, moviesCollection.totalQuantity());
        assertEquals(new Movie(102, "T2", "D2", "G2", now.minusYears(2)), moviesCollection.removeMovie(102));
        assertEquals(2, moviesCollection.totalQuantity());

    }

    @Test
    void findByIdTest() {
        Movie movie = moviesCollection.findById(101);
        assertEquals(101, movie.getImbd());
    }

    @Test
    void findByGenreTest() {
        Iterable<Movie> movies = moviesCollection.findByGenre("G1");
        String genre = "";
        int count = 0;

        for (Movie movie : movies) {
            assertEquals("G1", movie.getGenre());
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void findByDirector() {
        Iterable<Movie> movies = moviesCollection.findByDirector("D1");
        String direc = "";
        int count = 0;

        for (Movie movie : movies) {
            assertEquals("D1", movie.getDirector());
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void findMoviesCreatedBetweenDates() {
        //        вызвать метод и получить список
        LocalDate fromDate = LocalDate.now().minusYears(3);
        LocalDate toDate = LocalDate.now().minusYears(1);

        Iterable<Movie> actual = moviesCollection.findMoviesCreatedBetweenDates(fromDate, toDate);

        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie(101, "T1", "D1", "G1", now.minusYears(1)));
        expected.add(new Movie(102, "T2", "D2", "G2", now.minusYears(2)));
        expected.add(new Movie(103, "T3", "D1", "G1", now.minusYears(3)));

        assertIterableEquals(expected, actual);
    }


    @Test
    void totalQuantity() {
        assertEquals(4, moviesCollection.totalQuantity());
    }

    @Test
    void iterator() {
        // Получим итератор
        Iterator<Movie> iterator = moviesCollection.iterator();
        // Проверим, что итератор не равен null
        assertNotNull(iterator);
        // Проверим, что у нас есть следующий элемент в итераторе
        assertTrue(iterator.hasNext());
        // Получим первый элемент из итератора
        Movie firstMovie = iterator.next();
        // Проверим, что полученный элемент не равен null
        assertNotNull(firstMovie);
        // Проверим, что у полученного элемента правильный IMDb ID
        assertEquals(101, firstMovie.getImbd());
        // Теперь проверим второй элемент
        assertTrue(iterator.hasNext());
        Movie secondMovie = iterator.next();
        assertNotNull(secondMovie);
        assertEquals(102, secondMovie.getImbd());
        // Теперь проверим третий элемент
        assertTrue(iterator.hasNext());
        Movie thirdMovie = iterator.next();
        assertNotNull(thirdMovie);
        assertEquals(103, thirdMovie.getImbd());
        // Теперь проверим четвертый элемент
        assertTrue(iterator.hasNext());
        Movie fourthMovie = iterator.next();
        assertNotNull(fourthMovie);
        assertEquals(104, fourthMovie.getImbd());
        // После прохождения всех элементов, итератор не должен иметь следующего элемента
        assertFalse(iterator.hasNext());
    }
}