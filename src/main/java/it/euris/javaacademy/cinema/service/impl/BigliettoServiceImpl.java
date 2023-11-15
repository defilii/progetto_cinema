package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.Biglietto;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.repository.BigliettoRepository;
import it.euris.javaacademy.cinema.service.BigliettoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BigliettoServiceImpl implements BigliettoService {
    BigliettoRepository bigliettoRepository;
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
}
