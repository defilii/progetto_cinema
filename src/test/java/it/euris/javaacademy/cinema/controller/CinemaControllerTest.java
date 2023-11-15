package it.euris.javaacademy.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.euris.javaacademy.cinema.entity.Cinema;
import it.euris.javaacademy.cinema.entity.SalaCinematografica;
import it.euris.javaacademy.cinema.service.CinemaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class CinemaControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CinemaService cinemaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GIVEN only one cinema inside the db WHEN find all THEN should get it")
    void shouldGetOneCinema() throws Exception {
        //arrange
        Cinema cinema = Cinema.builder().build();
        List<Cinema> cinemaList = List.of(cinema);
        when(cinemaService.findAll()).thenReturn(cinemaList);
        //act+assert
        mockMvc.perform(MockMvcRequestBuilders.get("/cinema/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(cinema.getId()))
                .andExpect(jsonPath("$[0].nome").value(cinema.getNome()));
    }

    @Test
    @DisplayName("GIVEN a cinema not inside the db WHEN inserting THEN should insert")
    void shouldInsertACinema() throws Exception {
        //arrange
        Cinema cinema = Cinema.builder().build();
        when(cinemaService.insert(any())).thenReturn(cinema);
        //act+assert
        mockMvc.perform(post("/cinema/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinema.toDto())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(cinema.getId()))
                .andExpect(jsonPath("$.nome").value(cinema.getNome()));
    }

    @Test
    @DisplayName("GIVEN a cinema with a sale with an incasso WHEN getting incasso THEN should get sum")
    void shouldGetIncassi() throws Exception {
        //arrange
        Double incasso = 100.0;
        SalaCinematografica sala = SalaCinematografica.builder().incassi(incasso).build();

        Integer id = 1;
        List<SalaCinematografica> sale = List.of(sala);
        Cinema cinema = Cinema.builder().id(1).saleCinematografiche(sale).build();
        when(cinemaService.findById(any())).thenReturn(cinema);
        //act+assert
        mockMvc.perform(get("/cinema/getIncassi-{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").value(incasso));
    }
}