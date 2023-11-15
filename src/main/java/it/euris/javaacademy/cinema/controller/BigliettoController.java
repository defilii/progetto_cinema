package it.euris.javaacademy.cinema.controller;

import it.euris.javaacademy.cinema.dto.BigliettoDto;
import it.euris.javaacademy.cinema.entity.Biglietto;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.service.BigliettoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/biglietti")
public class BigliettoController {

    BigliettoService bigliettoService;
    @GetMapping("/getAll")
    public List<BigliettoDto> getAllBigliettos() {
        return bigliettoService.findAll().stream().map(Biglietto::toDto).toList();
    }

    @PostMapping("/insert")
    public BigliettoDto saveBiglietto(@RequestBody BigliettoDto bigliettoDTO) {
        try {
            Biglietto Biglietto = bigliettoDTO.toModel();
            return bigliettoService.insert(Biglietto).toDto();
        } catch (IdMustBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public BigliettoDto updateBiglietto(@RequestBody BigliettoDto bigliettoDTO) {
        try {
            Biglietto Biglietto = bigliettoDTO.toModel();
            return bigliettoService.update(Biglietto).toDto();
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBiglietto(@PathVariable("id") Integer idBiglietto) {
        return bigliettoService.deleteById(idBiglietto);
    }

    @GetMapping("/getById/{id}")
    public BigliettoDto getBigliettoById(@PathVariable("id") Integer idBiglietto) {
        return bigliettoService.findById(idBiglietto).toDto();
    }
}
