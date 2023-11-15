package it.euris.javaacademy.cinema.exceptions;

public class SalaNonStaMostrandoFilm extends RuntimeException {
    public SalaNonStaMostrandoFilm() {
        super("Impossibile aggiungere spettatore a una sala che non sta mostrando film.");
    }
    public SalaNonStaMostrandoFilm(String message) {
        super(message);
    }
}
