package dh.backend.clinicamvc.exception;

// Extiende de la clase padre Exception,
// se añade un constructor con mensaje personalizado. Exception(message.String)
public class ResourceNotFoundException extends Exception{
    //Constructor con mensaje
    public ResourceNotFoundException(String message) {

        super(message);
    }
}
