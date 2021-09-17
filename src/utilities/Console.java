package utilities;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Console --- Helper functions for handling and retrieving user input.
 * 
 * @author Ian Effendi
 */
public class Console {
    
    // ---------------------------------------------------
    // pause()
    // ---------------------------------------------------

    /**
     * Wait for user to input something and discard the input.
     * 
     * @param s Scanner.
     * @param prompt Prompt.
     */
    public static void pause(Scanner s, String prompt) {
        try {
            s = new Scanner(System.in);
            System.out.print(prompt);
            s.nextLine();
        } catch(NoSuchElementException e) {
            // Continue without worry.
            Console.pause(s, prompt);
        }
    }

    // ---------------------------------------------------
    // getString()
    // ---------------------------------------------------
    
    /**
     * Prompt user until valid String is provided.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param reminder User reminder when input is invalid.
     * @return non-null, non-blank String.
     */
    public static String getString(Scanner s, String prompt, String reminder) {
        String response = "";
        while(response.isEmpty() || response.isBlank()) {
            try {
                s = new Scanner(System.in);
                System.out.print(prompt);
                response = s.hasNextLine() ? s.nextLine() : "";
                if(response == null || response.isEmpty() || response.isBlank()) {
                    System.out.println(reminder);
                    continue;
                }
            } catch(InputMismatchException e) {
                e.printStackTrace();
                response = "";
            }
        }
        return response;        
    }

    /**
     * Prompt user until valid String is provided using default reminder on failure.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @return non-null, non-blank String.
     */
    public static String getString(Scanner s, String prompt) {
        return Console.getString(s, prompt, "Please enter a valid String input.");
    }

    // ---------------------------------------------------
    // getOption()
    // ---------------------------------------------------

    /**
     * Prompt user until valid option is provided.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param optionsSet TreeSet<String> or LinkedHashSet<String> of options.
     * @return valid character option as String.
     */
    public static String getOption(Scanner s, String prompt, Set<String> optionsSet) {
        // Pre-conditions:
        // - Options set is non-null.
        // - Options set is not empty.
        Assertions.assertNonNull(optionsSet);
        Assertions.<String>assertNotEmpty(optionsSet);

        // Prepare option String and reminder.
        String optionsString = "(" + String.join("/", optionsSet.toArray(String[]::new)) + ")";
        String reminder = "Please enter a valid option %s.".formatted(optionsString);

        // Get valid option.
        String response = "";
        while(response == "") {
            response = Console.getString(s, "%s %s: ".formatted(prompt, optionsString), reminder);
            if(!optionsSet.contains(response)) {
                System.out.println("\"%s\" is not a valid option.".formatted(response));
                System.out.println(reminder);
                response = "";
            }
        }
        return response;
    }

    /**
     * Prompt user until valid option is provided.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param options Array containing individual String options.
     * @param sort If false, uses a LinkedHashSet to preserve insertion order. Otherwise, if true, uses a TreeSet to sort options.
     * @return valid character option as String.
     */
    public static String getOption(Scanner s, String prompt, String[] options, boolean sort) {
        // Pre-conditions:
        // - Options set is non-null.
        // - Options set is not empty.
        Assertions.assertNonNull(options);
        Assertions.assertNotEmpty(options);

        // Prepare set (with insertion or alphabetical sort):
        Set<String> optionsSet = (sort) 
        ? new TreeSet<String>(Arrays.asList(options)) 
        : new LinkedHashSet<String>(Arrays.asList(options));

        // Prepare option String and reminder.
        return Console.getOption(s, prompt, optionsSet);
    }

    /**
     * Prompt user until valid option is provided.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param optionsString String containing individual character options deliminated by a ','.
     * @param sort If false, uses a LinkedHashSet to preserve insertion order. Otherwise, if true, uses a TreeSet to sort options.
     * @return valid character option as String.
     */
    public static String getOption(Scanner s, String prompt, String optionsString, boolean sort) {
        // Pre-conditions:
        // - Options set is non-null.
        // - Options set is not empty.
        Assertions.assertNonNull(optionsString);
        Assertions.assertNotEmpty(optionsString);

        // Prepare options array.
        String[] options = optionsString.split(",");
        return Console.getOption(s, prompt, options, sort);
    }
    
    /**
     * Prompt user for boolean option.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param optionsString String containing individual character options deliminated by a ','.
     * @param sort If false, uses a LinkedHashSet to preserve insertion order. Otherwise, if true, uses a TreeSet to sort options.
     * @return valid character option as String.
     */
    public static boolean getBoolean(Scanner s, String prompt, String trueOption, String falseOption) {
        // Pre-conditions:
        // - Options set is non-null.
        // - Options set is not empty.
        Assertions.assertNonNull(trueOption);
        Assertions.assertNotEmpty(trueOption);
        Assertions.assertNonNull(falseOption);
        Assertions.assertNotEmpty(falseOption);
        
        String optionsString = "%s,%s".formatted(trueOption, falseOption);
        String choice = Console.getOption(s, prompt, optionsString, false);
        return choice.equalsIgnoreCase(trueOption);
    }


    // ---------------------------------------------------
    // getInt()
    // ---------------------------------------------------

    /**
     * Prompt user until valid Integer is provided.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param reminder User reminder when input is invalid.
     * @return non-null Integer.
     */
    public static Integer getInt(Scanner s, String prompt, String reminder) {
        Integer response = null;
        while(null == response) {
            try {
                s = new Scanner(System.in);
                System.out.print(prompt);
                response = s.hasNextInt() ? s.nextInt() : null;
                if(response == null) {
                    System.out.println(reminder);
                    continue;
                }
            } catch(InputMismatchException e) {
                e.printStackTrace();
                response = null;
            }
        }
        return response;
    }

    /**
     * Prompt user until valid Integer is provided using default reminder on failure.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @return non-null Integer.
     */
    public static Integer getInt(Scanner s, String prompt) {
        return Console.getInt(s, prompt, "Please enter a valid Integer.");
    }

    // ---------------------------------------------------
    // getNonnegativeInt()
    // ---------------------------------------------------

    /**
     * Prompt user for non-negative Integer.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param reminder User reminder when input is invalid.
     * @param includeZero Should zero be included when requesting Integer?
     * @return non-null Integer.
     */
    public static Integer getNonnegativeInt(Scanner s, String prompt, String reminder, boolean includeZero) {
        Integer response = null;
        int lowerBound = (includeZero) ? 0 : 1;
        while(null == response) {
            try {
                response = Console.getInt(s, prompt, reminder);
                if(response < lowerBound) {
                    System.out.println(reminder);
                    response = null;
                }
            } catch(InputMismatchException e) {
                e.printStackTrace();
                response = null;
            }
        }
        return response;
    }

    /**
     * Prompt user for non-negative or zero Integer.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param reminder User reminder when input is invalid.
     * @return non-null Integer.
     */
    public static Integer getNonnegativeInt(Scanner s, String prompt, String reminder) {
        return Console.getNonnegativeInt(s, prompt, reminder, true);
    }

    /**
     * Prompt user for non-negative or zero Integer using the appropriate reminder on failure.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param includeZero Should zero be included when requesting Integer?
     * @return non-null Integer.
     */
    public static Integer getNonnegativeInt(Scanner s, String prompt, boolean includeZero) {
        String reminder = "Please enter a non-negative" + ((includeZero) ? " or zero " : " and non-zero ") + "Integer.";
        return Console.getNonnegativeInt(s, prompt, reminder, includeZero);
    }

    /**
     * Prompt user for non-negative or zero Integer using the default reminder on failure.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @return non-null Integer.
     */
    public static Integer getNonnegativeInt(Scanner s, String prompt) {
        return Console.getNonnegativeInt(s, prompt, "Please enter a non-negative or zero Integer.");
    }

    // ---------------------------------------------------
    // getDouble()
    // ---------------------------------------------------

    /**
     * Prompt user until valid Double is provided.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param reminder User reminder when input is invalid.
     * @return non-null Double.
     */
    public static Double getDouble(Scanner s, String prompt, String reminder) {
        Double response = null;
        while(null == response) {
            try {
                s = new Scanner(System.in);
                System.out.print(prompt);
                response = s.hasNextDouble() ? s.nextDouble() : null;
                if(response == null) {
                    System.out.println(reminder);
                    continue;
                }
            } catch(InputMismatchException e) {
                e.printStackTrace();
                response = null;
            }
        }
        return response;
    }

    /**
     * Prompt user until valid Double is provided using default reminder on failure.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @return non-null Double.
     */
    public static Double getDouble(Scanner s, String prompt) {
        return Console.getDouble(s, prompt, "Please enter a valid Double.");
    }

    // ---------------------------------------------------
    // getNonnegativeDouble()
    // ---------------------------------------------------

    /**
     * Prompt user for non-negative Double.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param reminder User reminder when input is invalid.
     * @param includeZero Should zero be included when requesting Double?
     * @return non-null Double.
     */
    public static Double getNonnegativeDouble(Scanner s, String prompt, String reminder, boolean includeZero) {
        Double response = null;
        int lowerBound = (includeZero) ? 0 : 1;
        while(null == response) {
            try {
                response = Console.getDouble(s, prompt, reminder);
                if(response < lowerBound) {
                    System.out.println(reminder);
                    response = null;
                }
            } catch(InputMismatchException e) {
                e.printStackTrace();
                response = null;
            }
        }
        return response;
    }

    /**
     * Prompt user for non-negative or zero Double.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param reminder User reminder when input is invalid.
     * @return non-null Double.
     */
    public static Double getNonnegativeDouble(Scanner s, String prompt, String reminder) {
        return Console.getNonnegativeDouble(s, prompt, reminder, true);
    }

    /**
     * Prompt user for non-negative or zero Double using the appropriate reminder on failure.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @param includeZero Should zero be included when requesting Integer?
     * @return non-null Double.
     */
    public static Double getNonnegativeDouble(Scanner s, String prompt, boolean includeZero) {
        String reminder = "Please enter a non-negative" + ((includeZero) ? " or zero " : " and non-zero ") + "Double.";
        return Console.getNonnegativeDouble(s, prompt, reminder, includeZero);
    }

    /**
     * Prompt user for non-negative or zero Double using the default reminder on failure.
     * 
     * @param s Scanner instance.
     * @param prompt User prompt.
     * @return non-null Double.
     */
    public static Double getNonnegativeDouble(Scanner s, String prompt) {
        return Console.getNonnegativeDouble(s, prompt, "Please enter a non-negative or zero Double.");
    }

    // ---------------------------------------------------
    // TEST METHODS
    // ---------------------------------------------------

    /**
     * Test the getString() method.
     * 
     * @param s Scanner instance.
     */
    public static void test_getString(Scanner s) {
        String response = Console.getString(s, "Test getString(): ");
        System.out.println(response);
        assert response != null && !response.isBlank() && !response.isEmpty() : "Expected non-null and non-blank String.";
        System.out.println("Test successful.");
    }

    /**
     * Test the getInt() method.
     * 
     * @param s Scanner instance.
     */
    public static void test_getInt(Scanner s) {
        Integer response = Console.getInt(s, "Test getInt(): ");
        System.out.println(response);
        assert response != null;
        System.out.println("Test successful.");
    }

    /**
     * Test the getNonnegativeInt() method.
     * 
     * @param s Scanner instance.
     */
    public static void test_getNonnegativeInt(Scanner s) {
        Integer response = Console.getNonnegativeInt(s, "Test getNonnegativeInt(includeZero=false): ", false);
        System.out.println(response);
        assert response != null;
        System.out.println("Test successful.");
    }

    /**
     * Test the getDouble() method.
     * 
     * @param s Scanner instance.
     */
    public static void test_getDouble(Scanner s) {
        Double response = Console.getDouble(s, "Test getDouble(): ");
        System.out.println(response);
        assert response != null;
        System.out.println("Test successful.");
    }

    /**
     * Test the getNonnegativeDouble() method.
     * 
     * @param s Scanner instance.
     */
    public static void test_getNonnegativeDouble(Scanner s) {
        Double response = Console.getNonnegativeDouble(s, "Test getNonnegativeDouble(includeZero=false): ", false);
        System.out.println(response);
        assert response != null;
        System.out.println("Test successful.");
    }

    /**
     * Test the getOption() method.
     * 
     * @param s Scanner instance.
     */
    public static void test_getOption(Scanner s) {
        String choice = Console.getOption(s, "Test getOption()", "y,n", false).toLowerCase();
        switch(choice) {
            case "y":
                System.out.println("YES");
                break;
            case "n":
                System.out.println("NO");
                break;
            default:
                assert false : "Invalid option chosen.";
        }
        System.out.println("Test successful.");
    }

    // ---------------------------------------------------
    // MAIN METHOD
    // ---------------------------------------------------

    /**
     * Test the Console methods.
     * 
     * @param args CLI arguments.
     */
    public static void main(String[] args) {

        // Prepare Scanner instance.
        Scanner s = new Scanner(System.in);

        // Test the getString() method.
        // Console.test_getString(s);

        // Test the getInt() method.
        // Console.test_getInt(s);

        // Test the getNonnegativeInt() method.
        // Console.test_getNonnegativeInt(s);

        // Test the getInt() method.
        Console.test_getDouble(s);

        // Test the getNonnegativeInt() method.
        Console.test_getNonnegativeDouble(s);

        // Test the getOption() method.
        // Console.test_getOption(s);

    }

}
