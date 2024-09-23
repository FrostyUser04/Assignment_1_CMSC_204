
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Yesho Vir
 *
 */
public class PasswordCheckerTest_STUDENT {

    ArrayList<String> passwordsArray;

    @Before
    public void setUp() throws Exception {
        // Set up a test list of passwords
        passwordsArray = new ArrayList<>();
        String[] passwords = {"12345", "Password", "password1!", "passWORD123!!", "abcABC123", "Password!!"};
        passwordsArray.addAll(Arrays.asList(passwords));
    }

    @After
    public void tearDown() throws Exception {
        // Clean up after each test
        passwordsArray = null;
    }

    /**
     * Test if the password is less than 6 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        try {
            PasswordCheckerUtility.isValidPassword("abc"); // Too short
            fail("Expected LengthException not thrown.");
        } catch (LengthException e) {
            assertEquals("The password must be at least 6 characters long.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one uppercase alpha character.
     * This test should throw a NoUpperAlphaException for second case.
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("abcdef1!"); // No uppercase, should throw exception
            fail("Expected NoUpperAlphaException not thrown.");
        } catch (NoUpperAlphaException e) {
            assertEquals("The password must contain at least one uppercase alphabetic character.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one lowercase alpha character.
     * This test should throw a NoLowerAlphaException for second case.
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("ABCDEF1!"); // No lowercase, should throw exception
            fail("Expected NoLowerAlphaException not thrown.");
        } catch (NoLowerAlphaException e) {
            assertEquals("The password must contain at least one lowercase alphabetic character.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one digit.
     * This test should throw a NoDigitException for the second case.
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        try {
            PasswordCheckerUtility.isValidPassword("Password!"); // No digit, should throw exception
            fail("Expected NoDigitException not thrown.");
        } catch (NoDigitException e) {
            assertEquals("The password must contain at least one digit.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password contains at least one special character.
     * This test should throw a NoSpecialCharacterException for the second case.
     */
    @Test
    public void testIsValidPasswordNoSpecialCharacter() {
        try {
            PasswordCheckerUtility.isValidPassword("Password1"); // No special character, should throw exception
            fail("Expected NoSpecialCharacterException not thrown.");
        } catch (NoSpecialCharacterException e) {
            assertEquals("The password must contain at least one special character.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password has more than 2 of the same character in sequence.
     * This test should throw an InvalidSequenceException for the second case.
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        try {
            PasswordCheckerUtility.isValidPassword("Headhunt12!"); // Has a valid sequence
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }

        try {
            PasswordCheckerUtility.isValidPassword("aaaBBB111!"); // Invalid sequence, should throw exception
            fail("Expected InvalidSequenceException not thrown.");
        } catch (InvalidSequenceException e) {
            assertEquals("The password cannot contain more than two of the same character in sequence.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test correct passwords.
     * This test should not throw an exception.
     */
    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Passw0rd!")); // Valid password
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test the invalidPasswords method.
     * Check the results of the ArrayList of Strings returned by the validPasswords method.
     */
    @Test
    public void testInvalidPasswords() {
        ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
        assertEquals(5, invalidPasswords.size()); // Should return 3 invalid passwords
        assertTrue(invalidPasswords.get(0).contains("The password must be at least 6 characters long."));
        assertTrue(invalidPasswords.get(1).contains("The password must contain at least one digit."));
        assertTrue(invalidPasswords.get(2).contains("The password must contain at least one uppercase alphabetic character."));
    }
}