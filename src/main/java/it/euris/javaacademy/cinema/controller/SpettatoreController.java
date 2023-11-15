package it.euris.javaacademy.cinema.controller;

import it.euris.javaacademy.cinema.dto.SpettatoreDto;
import it.euris.javaacademy.cinema.entity.Spettatore;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.service.SpettatoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/spettatore")
public class SpettatoreController {

    SpettatoreService spettatoreService;
    @GetMapping("/getAll")
    public List<SpettatoreDto> getAllSpettatori() {
        return spettatoreService.findAll().stream().map(Spettatore::toDto).toList();
    }

    @PostMapping("/insert")
    public SpettatoreDto saveSpettatore(@RequestBody SpettatoreDto spettatoreDto) {
        try {
            Spettatore spettatore = spettatoreDto.toModel();
            return spettatoreService.insert(spettatore).toDto();
        } catch (IdMustBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public SpettatoreDto updateSpettatore(@RequestBody SpettatoreDto spettatoreDto) {
        try {
            Spettatore spettatore = spettatoreDto.toModel();
            return spettatoreService.update(spettatore).toDto();
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteSpettatore(@PathVariable("id") Integer idSpettatore) {
        return spettatoreService.deleteById(idSpettatore);
    }

    @GetMapping("/getById/{id}")
    public SpettatoreDto getSpettatoreById(@PathVariable("id") Integer idSpettatore) {
        return spettatoreService.findById(idSpettatore).toDto();
    }
}
