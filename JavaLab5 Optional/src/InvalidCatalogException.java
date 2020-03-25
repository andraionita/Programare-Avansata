/**
 * @author Andra Ionita Grupa 2A7
 * Laborator 5 Optional
 */
public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(Exception ex) {
        super("Invalid catalog file.", ex);
    }
}
