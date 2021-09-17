package utilities;

/**
 * Formatter --- Formatter class for simplifying formats.
 * 
 * @author Ian Effendi
 */
public class Formatter {

    /** Key/value pair template. */
    private final static String KEYVALUE_TEMPLATE = "%s=%s";

    /**
     * Label to assign to the particular component.
     * 
     * @param label Label of the value.
     * @param value Value's String representation.
     * @return formatted String.
     */
    public static String label(String label, Object value) {
        return Formatter.label(KEYVALUE_TEMPLATE, label, value);
    }
    
    /**
     * Label to assign to the particular component.
     * 
     * @param template Special template to use.
     * @param label Label of the value.
     * @param value Value's String representation.
     * @return formatted String.
     */
    public static String label(String template, String label, Object value) {
        return template.formatted(label, value.toString());
    }
}
