package civilizacion.logic;

//Esta clase sirve para que el juego nos avise si intentamos comprar sin dinero
public class ResourceException extends Exception {
 public ResourceException(String mensaje) {
     super(mensaje);
 }
}
