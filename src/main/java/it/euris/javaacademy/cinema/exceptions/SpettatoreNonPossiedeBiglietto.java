package it.euris.javaacademy.cinema.exceptions;

public class SpettatoreNonPossiedeBiglietto extends RuntimeException {
    public SpettatoreNonPossiedeBiglietto() {
        super("Impossibile aggiungere spettatore a una sala di cui non possiede il biglietto.");
    }
    public SpettatoreNonPossiedeBiglietto(String message) {
        super(message);
    }
}
