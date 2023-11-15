package it.euris.javaacademy.cinema.controller;

import it.euris.javaacademy.cinema.dto.SalaCinematograficaDto;
import it.euris.javaacademy.cinema.entity.SalaCinematografica;
import it.euris.javaacademy.cinema.exceptions.*;
import it.euris.javaacademy.cinema.service.SalaCinematograficaService;
import it.euris.javaacademy.cinema.service.SpettatoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sale")
public class SalaCinematograficaController {

    SalaCinematograficaService saleService;
    SpettatoreService spettatoreService;

    @GetMapping("/getAll")
    public List<SalaCinematograficaDto> getAllSale() {
        return saleService.findAll().stream().map(SalaCinematografica::toDto).toList();
    }

    @PostMapping("/insert")
    public SalaCinematograficaDto saveSala(@RequestBody SalaCinematograficaDto salaDto) {
        try {
            SalaCinematografica sala = salaDto.toModel();
            return saleService.insert(sala).toDto();
        } catch (IdMustBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public SalaCinematograficaDto updateSalaCinematografica(@RequestBody SalaCinematograficaDto salaDto) {
        try {
            SalaCinematografica sala = salaDto.toModel();
            return saleService.update(sala).toDto();
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/add-spettatore-{idSpettatore}-to-{idSala}")
    public Boolean addSpettatore(@PathVariable("idSala") Integer idSala,
                                                @PathVariable("idSpettatore") Integer idSpettatore) {
        try {
            return saleService.aggiungiSpettatore(idSala, idSpettatore);
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (SalaNonStaMostrandoFilm ee) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ee.getMessage());
        } catch (FilmVietatoAiMinori eee) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, eee.getMessage());
        } catch (SalaAlCompleto eeee) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, eeee.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteSalaCinematografica(@PathVariable("id") Integer idSalaCinematografica) {
        return saleService.deleteById(idSalaCinematografica);
    }

    @GetMapping("/getById/{id}")
    public SalaCinematograficaDto getSalaCinematograficaById(@PathVariable("id") Integer idSalaCinematografica) {
        return saleService.findById(idSalaCinematografica).toDto();
    }

    @PutMapping("/svuota-sala/{id}")
    public Boolean svuotaSala(@PathVariable("id") Integer idSalaCinematografica) {
        try {
            return saleService.svuotaSala(idSalaCinematografica);
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}