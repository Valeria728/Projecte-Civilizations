package Civilization;

public class ResourceException extends Exception {
    // El número 1L es el estándar para versiones iniciales
    private static final long serialVersionUID = 1L;

    public ResourceException(String message) {
        super(message);
    }
}