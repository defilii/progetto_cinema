package it.euris.javaacademy.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.euris.javaacademy.cinema.entity.Film;
import it.euris.javaacademy.cinema.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@AutoConfigureMockMvc
public class FilmControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FilmService filmService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetOneFilm() throws Exception {

        Film film = Film.builder().build();
        List<Film> films = List.of(film);

        when(filmService.findAll()).thenReturn(films);

        mockMvc.perform(MockMvcRequestBuilders.get("/film/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].autore").value(film.getAutore()))
                .andExpect(jsonPath("$[0].titolo").value(film.getTitolo()));
    }

    @Test
    void shouldInsertAFilm() throws Exception {

        Film film = Film.builder().build();
        List<Film> films = List.of(film);

        when(filmService.insert(any())).thenReturn(film);

        mockMvc.perform(post("/film/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(film.toDto())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.autore").value(film.getAutore()))
                .andExpect(jsonPath("$.titolo").value(film.getTitolo()));
    }
}
