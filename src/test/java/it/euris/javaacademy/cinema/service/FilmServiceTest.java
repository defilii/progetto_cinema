package it.euris.javaacademy.cinema.service;

import it.euris.javaacademy.cinema.entity.Film;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.repository.FilmRepository;
import it.euris.javaacademy.cinema.service.impl.FilmServiceImpl;
import org.assertj.core.api.recursive.comparison.ComparingSnakeOrCamelCaseFields;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {
    @InjectMocks
    FilmServiceImpl filmService;

    @Mock
    FilmRepository filmRepository;

    @Test
    @DisplayName("GIVEN films inside a db WHEN using find all THEN should get all of them")
    void shouldReturnAFilm() {
        //arrange
        Film film = Film.builder().build();
        List<Film> films = List.of(film);
        when(filmRepository.findAll()).thenReturn(films);
        //act
        List<Film> returnedFilms = filmService.findAll();
        //assert
        assertThat(returnedFilms)
                .hasSize(1)
                .first()
                .usingRecursiveComparison()
                .withIntrospectionStrategy(new ComparingSnakeOrCamelCaseFields())
                .isEqualTo(film);
    }

    @Test
    @DisplayName("GIVEN a film not yet inserted WHEN inserting it THEN it should be inserted inside the db")
    void shouldInsertAFilm() {
        //arrange
        Film film = Film.builder().build();
        when(filmRepository.save(any())).thenReturn(film);
        //act+assert
        Film returnedFilm = filmService.insert(film);
        assertThat(returnedFilm.getTitolo())
                .isEqualTo(film.getTitolo());
    }

    @Test
    @DisplayName("GIVEN a film not yet inserted with set id WHEN inserting THEN shouldnt insert")
    void shouldNotInsertAnyFilm() {
        //arrange
        Film film = Film.builder().id(1).build();
        lenient().when(filmRepository.save(any())).thenReturn(film);
        assertThrows(IdMustBeNullException.class, () -> filmService.insert(film));

        //act+assert
        assertThatThrownBy(() -> filmService.insert(film))
                .isInstanceOf(IdMustBeNullException.class);

    }

    @Test
    @DisplayName("GIVEN a film inserted in the db WHEN updating it THEN should get update")
    void shouldUpdateAFilm() {
        //arrange
        Film film = Film.builder().id(1).build();
        //act
        when(filmRepository.save(any())).thenReturn(film);
        //assert
        Film returnedFilm = filmService.update(film);
        assertThat(returnedFilm.getTitolo())
                .isEqualTo(film.getTitolo());
    }

    @Test
    @DisplayName("GIVEN a film not inside the db WHEN updating THEN shouldnt update")
    void shouldNotUpdateAFilm() {
        //arrange
        Film film = Film.builder().build();
        lenient().when(filmRepository.save(any())).thenReturn(film);
        //act+assert
        assertThatThrownBy(() -> filmService.update(film))
                .isInstanceOf(IdMustNotBeNullException.class);
    }

    @Test
    @DisplayName("GIVEN a film inside the db WHEN deleting THEN should get deleted")
    void shouldDeleteFilm() {
        //arrange
        Integer id = 1;
        doNothing().when(filmRepository).deleteById(id);
        when(filmRepository.findById(id)).thenReturn(Optional.empty());

        //act+assert
        assertTrue(filmService.deleteById(id));
        Mockito.verify(filmRepository, times(1)).deleteById(id);
    }
}
