
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

    /**
     * @author Yesho Vir
     * 
     * @param password
     * @return
     * @throws LengthException
     * @throws NoUpperAlphaException
     * @throws NoLowerAlphaException
     * @throws NoDigitException
     * @throws NoSpecialCharacterException
     * @throws InvalidSequenceException
     */

    // Checks for password validity
    public static boolean isValidPassword(String password) throws LengthException, 
    NoUpperAlphaException, 
    NoLowerAlphaException, 
    NoDigitException, 
    NoSpecialCharacterException, 
    InvalidSequenceException
    
    {
        
        checkLength(password);
        checkUpperCase(password);
        checkLowerCase(password);
        checkDigit(password);
        checkSpecialCharacter(password);
        checkInvalidSequence(password);
        
        return true;
    }

    /**
     * 
     * @param password
     * @return
     * @throws WeakPasswordException
     */

    // Checks password weakness (6-9 characters long)
    public static boolean isWeakPassword(String password) throws WeakPasswordException {
        if (password.length() >= 6 && password.length() <= 9) {
            throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
        }
        return false;
    }

    /**
     * 
     * @param passwords
     * @return
     */

    // Returns a list of invalid passwords
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();

        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception e) {
                invalidPasswords.add(password + " " + e.getMessage());
            }
        }
        return invalidPasswords;
    }

    /**
     * 
     * @param password
     * @throws LengthException
     */

    // Public method to check if the length of the password is at least 6 characters
    public static void checkLength(String password) throws LengthException {
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long.");
        }
    }

    /**
     * 
     * @param password
     * @throws NoUpperAlphaException
     */

    // Public method to check if the password contains at least one uppercase letter
    public static void checkUpperCase(String password) throws NoUpperAlphaException {
        if (!password.matches(".*[A-Z].*")) {
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character.");
        }
    }

    /**
     * 
     * @param password
     * @throws NoLowerAlphaException
     */

    // Public method to check if the password contains at least one lowercase letter
    public static void checkLowerCase(String password) throws NoLowerAlphaException {
        if (!password.matches(".*[a-z].*")) {
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character.");
        }
    }

    /**
     * 
     * @param password
     * @throws NoDigitException
     */

    // Public method to check if the password contains at least one digit
    public static void checkDigit(String password) throws NoDigitException {
        if (!password.matches(".*\\d.*")) {
            throw new NoDigitException("The password must contain at least one digit.");
        }
    }

    /**
     * 
     * @param password
     * @throws NoSpecialCharacterException
     */

    // Public method to check if the password contains at least one special character
    public static void checkSpecialCharacter(String password) throws NoSpecialCharacterException {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            throw new NoSpecialCharacterException("The password must contain at least one special character.");
        }
    }

    /**
     * 
     * @param password
     * @throws InvalidSequenceException
     */

    // Public method to check if there are more than 2 of the same characters in sequence
    public static void checkInvalidSequence(String password) throws InvalidSequenceException {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i + 1) == password.charAt(i + 2)) {
                throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
            }
        }
    }

    /**
     * 
     * @param password
     * @param passwordConfirm
     * @throws UnmatchedException
     */

    // Public method to check if the two passwords match
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if (!comparePasswordsWithReturn(password, passwordConfirm)) {
            throw new UnmatchedException("Passwords do not match!");
        }
    }

    /**
     * 
     * @param password
     * @param passwordConfirm
     * @return
     */

    // Returns true if passwords match, false otherwise
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }
}
