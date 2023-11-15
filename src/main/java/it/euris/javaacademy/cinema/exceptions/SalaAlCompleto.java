package it.euris.javaacademy.cinema.exceptions;

public class SalaAlCompleto extends RuntimeException{
    public SalaAlCompleto() {
        super("Sala al completo");
    }
    public SalaAlCompleto(String message) {
        super(message);
    }
}
