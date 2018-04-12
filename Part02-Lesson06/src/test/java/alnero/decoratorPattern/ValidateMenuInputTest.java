package alnero.decoratorPattern;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import alnero.StubInput;
import alnero.TrackerInput;
import alnero.innerClasses.MenuTracker;

/**
 * Testing method of ValidateMenuInput class of Tracker app with automatic stub input and
 * redirection of default system output to mock stream.
 * ValidateMenuInput is implemented via decorator pattern.
 */
public class ValidateMenuInputTest {
    /** Common dictionary object for stub input strings. */
    private HashMap<String, String[]> stubInputDictionary;
    /** Common object for automatic stub inputs. */
    private TrackerInput stubInput;
    /** Common object for menu values allowable for input. */
    private String[] possibleMenuValues;

    /** Object to save original default system output. */
    private PrintStream originalSystemOut;
    /** Mock output stream object for redirection of default system output. */
    private ByteArrayOutputStream outputContent;

    /** Create common task tracker, stub input dictionary and allowed possible menu values for tests. */
    @Before
    public void createCommonTaskTrackerAndStubInputDictionaryObjectsForTesting() {
        this.stubInputDictionary = new HashMap<>();
        this.possibleMenuValues = new String[MenuTracker.MAX_NUMBER_OF_TASKS];
        for (int i = 0; i < possibleMenuValues.length; i++) {
            possibleMenuValues[i] = String.valueOf(i);
        }
    }

    /** Save original default system output and redirect to mock output stream. */
    @Before
    public void redirectSystemOutputToSeparateStream() {
        this.originalSystemOut = System.out;
        this.outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.outputContent));
    }

    /** Reinstate original default system output. */
    @After
    public void reinstateSystemOutputToDefault() {
        System.setOut(this.originalSystemOut);
    }

    /**
     * If we choose valid menu item then validation method returns this menu item.
     */
    @Test
    public void whenValidMenuItemChosenThenValidatorReturnsValidString() {
        // take random menu item from possible menu items
        int randomMenuItemIndex = ThreadLocalRandom.current().nextInt(this.possibleMenuValues.length);
        String randomPossibleMenuItem = this.possibleMenuValues[randomMenuItemIndex];

        this.stubInputDictionary.put("stubInputLine", new String[]{randomPossibleMenuItem});
        this.stubInput = new StubInput(this.stubInputDictionary);
        ValidateMenuInput validateInput = new ValidateMenuInput(this.stubInput);
        Assert.assertThat(validateInput.readInputLine(this.possibleMenuValues), is(randomPossibleMenuItem));
    }

    /**
     * If we input negative number for menu item then validation method asks to select correct menu item.
     */
    @Test
    public void whenNegativeNumberEnteredAsMenuItemThenValidatorAsksToChooseMenuItemAgain() {
        // take random string value of negative number
        int randomNegativeNumber = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, 0);
        String incorrectMenuItem = String.valueOf(randomNegativeNumber);

        // take random menu item from possible menu items
        int randomMenuItemIndex = ThreadLocalRandom.current().nextInt(this.possibleMenuValues.length);
        String randomPossibleMenuItem = this.possibleMenuValues[randomMenuItemIndex];

        this.stubInputDictionary.put("stubInputLine", new String[]{incorrectMenuItem, randomPossibleMenuItem});
        this.stubInput = new StubInput(this.stubInputDictionary);
        ValidateMenuInput validateInput = new ValidateMenuInput(this.stubInput);
        validateInput.readInputLine(this.possibleMenuValues);
        Assert.assertThat(outputContent.toString(), containsString("Please select correct key from Menu."));
    }

    /**
     * If we input string letter for menu item then validation method asks to select correct menu item.
     */
    @Test
    public void whenAlphabetLetterEnteredAsMenuItemThenValidatorAsksToChooseMenuItemAgain() {
        // take random string letter
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int randomAlphabetIndex = ThreadLocalRandom.current().nextInt(alphabet.length);
        String incorrectMenuItem = String.valueOf(alphabet[randomAlphabetIndex]);

        // take random menu item from possible menu items
        int randomMenuItemIndex = ThreadLocalRandom.current().nextInt(this.possibleMenuValues.length);
        String randomPossibleMenuItem = this.possibleMenuValues[randomMenuItemIndex];

        this.stubInputDictionary.put("stubInputLine", new String[]{incorrectMenuItem, randomPossibleMenuItem});
        this.stubInput = new StubInput(this.stubInputDictionary);
        ValidateMenuInput validateInput = new ValidateMenuInput(this.stubInput);
        validateInput.readInputLine(this.possibleMenuValues);
        Assert.assertThat(outputContent.toString(), containsString("Please select correct key from Menu."));
    }
}
