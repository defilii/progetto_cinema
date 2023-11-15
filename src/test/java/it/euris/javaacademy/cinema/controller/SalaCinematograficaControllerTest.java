package it.euris.javaacademy.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.euris.javaacademy.cinema.entity.Cinema;
import it.euris.javaacademy.cinema.entity.SalaCinematografica;
import it.euris.javaacademy.cinema.service.SalaCinematograficaService;
import it.euris.javaacademy.cinema.service.SpettatoreService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class SalaCinematograficaControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SalaCinematograficaService salaService;


    @MockBean
    SpettatoreService spettatoreService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GIVEN only one sala inside the db WHEN find all THEN should get it")
    void shouldGetOneSala() throws Exception {
        //arrange
        SalaCinematografica sala = SalaCinematografica.builder()
                .cinema(Cinema.builder().build())
                .build();
        List<SalaCinematografica> sale = List.of(sala);
        when(salaService.findAll()).thenReturn(sale);
        //act+assert
        mockMvc.perform(MockMvcRequestBuilders.get("/sale/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(sala.getId()))
                .andExpect(jsonPath("$[0].sedie").value(sala.getSedie()));
    }

    @Test
    @DisplayName("GIVEN a sala not inside the db WHEN inserting THEN should insert")
    void shouldInsertASala() throws Exception {
        //arrange
        SalaCinematografica sala = SalaCinematografica.builder()
                .cinema(Cinema.builder().build())
                .build();
        when(salaService.insert(any())).thenReturn(sala);
        //act+assert
        mockMvc.perform(post("/sale/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sala.toDto())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(sala.getId()))
                .andExpect(jsonPath("$.sedie").value(sala.getSedie()));
    }
}
