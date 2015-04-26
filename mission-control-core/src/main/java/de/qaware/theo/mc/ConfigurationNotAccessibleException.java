package de.qaware.theo.mc;

/**
 * @author s.wittke
 */
public class ConfigurationNotAccessibleException extends Exception {

    public ConfigurationNotAccessibleException() {
    }

    public ConfigurationNotAccessibleException(String message) {
        super(message);
    }

    public ConfigurationNotAccessibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
