package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtil {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static int readInt(Scanner sc, String message) {
        try {
            System.out.print(message);
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return -1;
        }
    }

    public static int readIntInRange(Scanner sc, String message,
            int min, int max) {
        while (true) {
            int value = readInt(sc, message);

            if (value >= min && value <= max) {
                return value;
            }

            System.out.println("Value must be from " + min + " to " + max + ".");
        }
    }

    public static int readIntMin(Scanner sc, String message, int min) {
        while (true) {
            int value = readInt(sc, message);

            if (value >= min) {
                return value;
            }

            System.out.println("Value must be at least " + min + ".");
        }
    }

    private static double readDouble(Scanner sc, String message) {
        try {
            System.out.print(message);
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return -1;
        }
    }

    public static double readDoubleMin(Scanner sc, String message,
            double min) {
        while (true) {
            double value = readDouble(sc, message);

            if (value >= min) {
                return value;
            }

            System.out.println("Value must be at least " + min + ".");
        }
    }

    public static String readRequiredString(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine();

            if (!isBlank(value)) {
                return value;
            }

            System.out.println("Value must not be empty.");
        }
    }

    public static String readOption(Scanner sc, String message,
            String... options) {
        while (true) {
            String value = readRequiredString(sc, message);

            for (String option : options) {
                if (value.equalsIgnoreCase(option)) {
                    return option;
                }
            }

            System.out.print("Value must be");
            for (int i = 0; i < options.length; i++) {
                System.out.print(i == 0 ? " " : ", ");
                System.out.print(options[i]);
            }
            System.out.println(".");
        }
    }
 public static String readPosition(Scanner sc, String message) {
        while (true) {
            String value = readRequiredString(sc, message);
            String cleaned = value.trim().toUpperCase();

         
            if (cleaned.equals("GK") || cleaned.equals("DF") || 
                cleaned.equals("MF") || cleaned.equals("ST") || cleaned.equals("FW")) {
                return cleaned;
            }

            
            System.out.println("Invalid position! Please enter: GK, DF, MF, ST, or FW.");
        }
    }
    public static boolean readBoolean(Scanner sc, String message) {
        while (true) {
            String value = readRequiredString(sc, message);

            if (value.equalsIgnoreCase("true")) {
                return true;
            }

            if (value.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.println("Value must be true or false.");
        }
    }

    public static LocalDate readLocalDate(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            try {
                return LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }
}
