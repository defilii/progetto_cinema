package it.euris.javaacademy.cinema.exceptions;

public class FilmVietatoAiMinori extends RuntimeException{
    public FilmVietatoAiMinori() {
        super("Film vietato ai minori");
    }
    public FilmVietatoAiMinori(String message) {
        super(message);
    }
}
