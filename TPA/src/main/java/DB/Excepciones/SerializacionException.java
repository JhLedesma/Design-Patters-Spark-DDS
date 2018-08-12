package DB.Excepciones;

import java.util.Arrays;

public class SerializacionException extends RuntimeException {

    public SerializacionException() {
    }

    public SerializacionException(String message) {
        super(message);
    }

    public SerializacionException(Throwable cause, String message, String... adicional) {
        super(String.format(message, Arrays.asList(adicional)), cause);
    }

    public SerializacionException(Throwable cause) {
        super(cause);
    }

    public SerializacionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
