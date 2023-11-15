package it.euris.javaacademy.cinema.exceptions;

public class IdMustNotBeNullException extends RuntimeException {

    public IdMustNotBeNullException() {
        super("Id non deve essere null, inserire id valido.");
    }
    public IdMustNotBeNullException(String message) {
        super(message);
    }
}