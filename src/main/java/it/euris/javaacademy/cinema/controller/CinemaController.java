package it.euris.javaacademy.cinema.controller;

import it.euris.javaacademy.cinema.dto.CinemaDto;
import it.euris.javaacademy.cinema.entity.Cinema;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cinema")
public class CinemaController {

    CinemaService cinemaService;
    @GetMapping("/getAll")
    public List<CinemaDto> getAllCinemas() {
        return cinemaService.findAll().stream().map(Cinema::toDto).toList();
    }

    @GetMapping("/getIncassi-{id}")
    public Double getIncassiTotali(@PathVariable("id") Integer idCinema) {
        return cinemaService.findById(idCinema).incassiTotali();
    }

    @PostMapping("/insert")
    public CinemaDto saveCinema(@RequestBody CinemaDto cinemaDto) {
        try {
            Cinema cinema = cinemaDto.toModel();
            return cinemaService.insert(cinema).toDto();
        } catch (IdMustBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public CinemaDto updateCinema(@RequestBody CinemaDto cinemaDto) {
        try {
            Cinema cinema = cinemaDto.toModel();
            return cinemaService.update(cinema).toDto();
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCinema(@PathVariable("id") Integer idCinema) {
        return cinemaService.deleteById(idCinema);
    }

    @GetMapping("/getById/{id}")
    public CinemaDto getCinemaById(@PathVariable("id") Integer idCinema) {
        return cinemaService.findById(idCinema).toDto();
    }
}
