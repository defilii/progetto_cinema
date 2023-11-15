package it.euris.javaacademy.cinema.exceptions;

public class IdMustBeNullException extends RuntimeException {

    public IdMustBeNullException() {
        super("Id deve essere null, inserito valore non null");
    }
    public IdMustBeNullException(String message) {
        super(message);
    }
}
