package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.Biglietto;
import it.euris.javaacademy.cinema.entity.Sconti;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.exceptions.NessunScontoDisponibile;
import it.euris.javaacademy.cinema.repository.BigliettoRepository;
import it.euris.javaacademy.cinema.repository.ScontiRepository;
import it.euris.javaacademy.cinema.repository.SpettatoreRepository;
import it.euris.javaacademy.cinema.service.BigliettoService;
import it.euris.javaacademy.cinema.service.ScontoService;
import it.euris.javaacademy.cinema.service.SpettatoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BigliettoServiceImpl implements BigliettoService {
    BigliettoRepository bigliettoRepository;
    ScontiRepository scontiRepository;
    SpettatoreRepository spettatoreRepository;
    SpettatoreService spettatoreService;
    @Override
    public List<Biglietto> findAll() {
        return bigliettoRepository.findAll();
    }

    @Override
    public Biglietto insert(Biglietto biglietto) {
        if (biglietto.getId() != null && biglietto.getId() > 0) {
            throw new IdMustBeNullException();
        }
        return bigliettoRepository.save(biglietto);
    }

    @Override
    public Biglietto update(Biglietto biglietto) {
        if (biglietto.getId() == null || biglietto.getId() == 0) {
            throw new IdMustNotBeNullException();
        }
        return bigliettoRepository.save(biglietto);
    }

    @Override
    public Boolean deleteById(Integer idBiglietto) {
        bigliettoRepository.deleteById(idBiglietto);
        return bigliettoRepository.findById(idBiglietto).isEmpty();
    }

    @Override
    public Biglietto findById(Integer idBiglietto) {
        return bigliettoRepository.findById(idBiglietto).orElse(Biglietto.builder().build());
    }

    @Override
    public String applySale(Integer idBiglietto) {
        Biglietto biglietto = bigliettoRepository.findById(idBiglietto).orElse(Biglietto.builder().build());
        Integer anni = spettatoreService.findById(biglietto.getSpettatore().getId()).getAnni();
        Optional<Sconti> scontiOptional = scontiRepository.findSaleByAge(anni).stream().findFirst();
        if(scontiOptional.isPresent()) {
            Double sconto = scontiOptional.get().getSconto();
            Double prezzoBiglietto = biglietto.getPrezzo();
            biglietto.setPrezzo(prezzoBiglietto * sconto);
            bigliettoRepository.save(biglietto);
            return String.format("applied sale of %.2f, price is now %.2f", sconto, biglietto.getPrezzo());
        }
       return "no available sale";

    }
}
