package it.euris.javaacademy.cinema.exceptions;

public class NessunScontoDisponibile extends RuntimeException {
    public NessunScontoDisponibile() {
        super("Nessun sconto disponibile per user associato al biglietto.");
    }
    public NessunScontoDisponibile(String message) {
        super(message);
    }
}
