package utilities;

/**
 * Assertions --- Helper assertions.
 * 
 * @author Ian Effendi
 */
public class Assertions {
    
    // ---------------------------------------------------
    // STATIC METHODS
    // ---------------------------------------------------

    /**
     * Assertion that element is non-null.
     * 
     * @param element Element to check.
     */
    public static void assertNonNull(Object element) {
        assert element != null : "Element is null.";
    }

    /**
     * Assertion that Element is non-null and not empty.
     * 
     * @param element Element to check.
     */
    public static void assertNotEmpty(String element) {
        assert element != null && !element.isEmpty() && !element.isBlank() : "String is null, blank, or empty.";
    }

    /**
     * Assertion that collection is non-null and not empty.
     * 
     * @param collection Collection to check.
     */
    public static <E> void assertNotEmpty(java.util.Collection<E> collection) {
        assert collection != null && !collection.isEmpty() : "Collection is null or empty.";
    }

    /**
     * Assertion that array is non-null and not empty.
     * 
     * @param array Array to check.
     */
    public static void assertNotEmpty(Object[] array) {
        assert array != null : "Array is null.";
        assert array.length > 0 : "Array is empty.";
    }

    /**
     * Assertion that array index is within bounds.
     * 
     * @param array Array to check.
     * @param i Index to verify.
     * @return true if assertion is passed.
     */
    public static void assertInBounds(Object[] array, int i) {
        assert i >= 0 && i < array.length : "Index [%d] out of bounds for array of %d element(s).".formatted(i, array.length);
    }

    /**
     * Assertion that array value at index `i` is equal to specified `expected` value.
     * 
     * @param array Array to check.
     * @param i Index to verify.
     * @param expected Expected value.
     */
    public static void assertIndexValue(Object[] array, int i, Object value) {
        assert array[i].equals(value) : "Element at index [%d] did not match expected value (%s). Received %s instead.".formatted(i, array[i], value);
    }

    /**
     * Assertion that the two provided arrays are identical.
     * 
     * @param a Array to check against `b`.
     * @param b Array to check against `a`.
     */
    public static void assertIdentical(Object[] a, Object[] b) {
        // Pre-conditions:
        // - Arrays are of equal length.
        // - Arrays are non-null.
        assert a.length == b.length : "Arrays of different lengths.";
        assert a != null : "First array is null.";
        assert b != null : "Second array is null.";
        
        // Assert each value is equal to the corresponding value in the other.
        for(int i = 0; i < a.length; i++) {
            assert a[i].equals(b[i]) : "Elements differ at index [%d]. (a[%d] == %d, b[%d] == %d).".formatted(i, i, a[i], i, b[i]);
        }
    }

    /**
     * Assertion that array is sorted with provided in sort direction.
     * 
     * @param array Array to check.
     * @param descending Direction of sort order to check.
     */
    public static void assertIsSorted(Object[] array, boolean descending) {
        assert SortUtilities.sorted(array, descending) : "Array is not sorted";
    }

}
