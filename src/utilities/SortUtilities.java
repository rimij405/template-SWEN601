package utilities;

/**
 * SortUtilities.java
 * Ian Effendi <iae2784@rit.edu>
 * 
 * Sort helper functions.
 */
import java.util.StringJoiner;
import java.util.Random;
import java.util.Arrays;

/**
 * SortUtilities --- Sort helper functions.
* 
* @author Ian Effendi
*/
public class SortUtilities {

    // ---------------------------------------------------
    // FIELDS
    // ---------------------------------------------------

    /** Random RNG instance. */
    public static Random RNG = new Random();
    
    // ---------------------------------------------------
    // STATIC FACTORY METHODS
    // ---------------------------------------------------

    /**
     * Parse Object[] array into an Integer[] array.
     * 
     * @param array Array to copy.
     * @return Integer array.
     */
    public static Integer[] toIntegerArray(Object[] array) {
        return Arrays.copyOf(array, array.length, Integer[].class);
    }
    
    /**
     * Parse Object[] array into an String[] array.
     * 
     * @param array Array to copy.
     * @return String array.
     */
    public static String[] toStringArray(Object[] array) {
        return Arrays.copyOf(array, array.length, String[].class);
    }

    // ---------------------------------------------------
    // SERVICE METHODS
    // ---------------------------------------------------

    /**
     * Determine if input array is sorted (in specified order).
     * 
     * @param array Array to be checked.
     * @param descending If true, sort checks sort order in a descending direction.
     * @return true if `arr` is sorted.
     */
    public static boolean sorted(Object[] array, boolean descending) {
        // Pre-conditions:
        // NONE.

        // If empty, return true.
        if(array.length == 0) { return true; }

        // If not empty, check sort status.
        for(int i = 0; i < (array.length - 1); i++) {
            // Compare current element to next element.
            int comparison = SortUtilities.compare(array[i], array[i+1]);
            if(descending && comparison < 0) {
                // When sort(DESC), a[i] >= a[i+1].
                // So return false when a[i] < a[i+1].
                return false;
            }
            else if(!descending && comparison > 0) {
                // When sort(ASC), a[i] <= a[i+1].
                // So return false when a[i] > a[i+1].
                return false;
            }
        }

        // Post-conditions:
        // NONE.

        // If no exit case met, we have a sorted array.
        return true;
    }

    /**
     * Determine if input array is sorted (in ascending order).
     * 
     * @param array Array to be checked.
     * @return true if `arr` is sorted.
     */
    public static boolean sorted(Object[] array) {
        return SortUtilities.sorted(array, false);
    }

    /**
     * Compare two Comparable elements, of particular class types.
     * 
     * @param a First element.
     * @param b Second element.
     * @return return 0 if a == b. Return positive integer when a > b and negative integer when b > a.
     */
    public static int compare(Object a, Object b) {
        if(a instanceof Integer i1 && b instanceof Integer i2) {
            return i1.compareTo(i2);
        }
        else if(a instanceof String s1 && b instanceof String s2) {
            return s1.compareTo(s2);
        }
        else {
            throw new ClassCastException("Unhandled cast during comparison.");
        }
    }

    /**
     * Swap array value in place.
     * 
     * @param array Array to be checked.
     * @param a Index of value to be swapped with `array[b]`.
     * @param b Index of value to be swapped with `array[a]`.
     */
    public static void swap(Object[] array, int a, int b) {
        // Pre-conditions:
        // - Array is not empty.
        // - Array indices are within array bounds.
        Assertions.assertNotEmpty(array);
        Assertions.assertInBounds(array, a);
        Assertions.assertInBounds(array, b);

        // Attempt swap.
        Object aValue = array[a];
        array[a] = array[b];
        array[b] = aValue;

        // Post-conditions:
        // - Element at index `b` equals previous `a` value.
        Assertions.assertIndexValue(array, b, aValue);
    }

    /**
     * Reverse array and return result.
     * 
     * @param array Array to reverse.
     * @return reversed array.
     */
    public static Integer[] reverseArray(Integer[] array)
    {
       Integer[] target = new Integer[array.length];
       for(int i = 0; i < array.length; i++) {
           target[i] = array[array.length - (1 + i)];
       }
       System.out.println("Input array: %s ==> Reversed array: %s".formatted(
           SortUtilities.toString(array),
           SortUtilities.toString(target)
       ));
       return target;
    }

    /**
     * Make an array of the specified size filled with random values.
     * 
     * @param size Size of generated array.
     * @return array of specified size, filled with random values between 0 and size.
     */
    public static Integer[] generateArray(Integer size) {        
        // Create the array.
        Integer[] array = new Integer[size];

        // Fill the array.
        for(int i = 0; i < size; i++) {
            array[i] = SortUtilities.RNG.nextInt(size);
        }

        // Return the array.
        return array;
    }

    /**
     * Make an array of the specified size filled with blank Strings.
     * 
     * @param size Size of generated array.
     * @return array of specified size, filled with blank Strings.
     */
    public static String[] empty(Integer size) {
        // Create the array.
        String[] array = new String[size];

        // Fill the array.
        for(int i = 0; i < size; i++) {
            array[i] = "";
        }

        // Return the array.
        return array;
    }

    /**
     * Get formatted String for array.
     * 
     * @param array Array to format.
     * @return formatted String.
     */
    public static String toString(Object[] array) {
        if(array == null || array.length == 0) { return "<>"; }
        StringJoiner sj = new StringJoiner(",", "<", ">");
        for(Object val : array) {
            if(val instanceof Integer i) {
                sj.add(Integer.toString(i));
            } else if(val instanceof String s) {
                sj.add(s);
            } else {
                sj.add(val.toString());
            }
        }
        return sj.toString();
    }

    /**
     * Split source array into two sublists.
     * 
     * @param array Array of length n to split.
     * @return 2D array containing two sublists of n/2 length.
     */
    public static Object[][] cut(Object[] array) {
        // Pre-condition:
        // - Array is non-null and not empty.
        // - Array has more than 1 element.
        Assertions.assertNotEmpty(array);
        assert array.length > 1 : "Array of length 1 cannot be split.";
        
        // Copy and return subarrays.
        int sublength = array.length / 2;
        return new Object[][] {
            Arrays.copyOfRange(array, 0, sublength),
            Arrays.copyOfRange(array, sublength, array.length)
        };
    }

    // ---------------------------------------------------
    // MAIN METHOD
    // ---------------------------------------------------

    /**
     * Test the utilities.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        
        // Test swap(int[], int, int) function.
        Integer[] target = new Integer[]{ 2, 4, 1, 3, 5 };
        Integer[] input = new Integer[]{ 2, 4, 1, 3, 5 };
        Integer[] check = new Integer[]{ 1, 4, 2, 3, 5 };
        System.out.println("Before swap(%s, 0, 2): %s".formatted(SortUtilities.toString(input), SortUtilities.toString(target)));
        SortUtilities.swap(target, 0, 2); // 1, 4, 2, 3, 5
        System.out.println("After swap(%s, 0, 2): %s".formatted(SortUtilities.toString(input), SortUtilities.toString(target)));
        Assertions.assertIdentical(target, check);

        // Test sorted(int[]) function.
        Integer[] sorted = new Integer[]{ 1, 2, 3, 4, 5 };
        System.out.println("Is array %s sorted? %b".formatted(SortUtilities.toString(sorted), SortUtilities.sorted(sorted)));
        Assertions.assertIsSorted(sorted, false);

        // Test sorted(int[]) function.
        input = new Integer[]{ 5, 4, 3, 2, 1 };
        check = new Integer[]{ 1, 2, 3, 4, 5 };
        System.out.println("Array %s should be reverse of %s.".formatted(SortUtilities.toString(input), SortUtilities.toString(check)));
        Assertions.assertIdentical(SortUtilities.reverseArray(input), check);

        // Test generateArray(int) function.
        Integer size = 5;
        System.out.println("Testing generateArray(%d), %d time(s).".formatted(size, size));
        for(int i = 0; i < size; i++) {
            System.out.println("[%d] generateArray(%d): %s".formatted(i + 1, size, SortUtilities.toString(generateArray(size))));
        }

        // Test empty(int) function.
        size = 10;
        System.out.println("empty(%d): %s".formatted(size, SortUtilities.toString(empty(size))));

    }

}
