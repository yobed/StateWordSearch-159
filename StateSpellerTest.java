import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Submission tests for the StateSpeller programming assignment
 * 
 * @author CS 159 Instructors
 * @version 4/11/2021
 *
 */
class StateSpellerTest {
    private String[] spellableFirstLettersNoRepeats;
    private String[] spellableFirstLettersOnlyByRepeatingStates;

    private String[] spellableMultiNoRepeats;
    private String[] nonSpellable;
    private String[] spellableMultiOnlyByRepeatingStates;
    private StateSpeller speller;

    @BeforeEach
    void setUp() throws Exception {

        spellableFirstLettersNoRepeats = new String[] {"atomism", "cantata",
                "malmags", "incant", "malmag", "nomism", "tamins", "amins",
                "canto", "comal", "fatal", "iiwis", "kiwis", "notal", "talma",
                "tamal", "tamin"};

        spellableFirstLettersOnlyByRepeatingStates = new String[] {"coala",
                "canon", "toco", "tatamis", "okimono", "matamata", "laminins",
                "cocoa", "alamo", "mocock", "agama", "sims", "tomato", "pop",
                "isms", "mini", "swims", "union"};

        spellableMultiNoRepeats = new String[] {"colorado", "colorant",
                "flatmate", "florigen", "ketamine", "komissar", "millines",
                "milliohm", "millions", "minimill", "minimism", "misagent",
                "misatone", "missions"};

        spellableMultiOnlyByRepeatingStates = new String[] {"pope", "coala",
                "conins", "conine", "ionone", "coniosis", "cocoa", "matelot",
                "nines", "corno", "isms", "swims", "minikin", "canine", "union",
                "momisms", "cancan", "nine", "tatar", "monomial"};

        nonSpellable = new String[] {"delectables", "highly", "hereticate",
                "presidium", "multiplied", "swimmingnesses", "kalsomines",
                "rapturously", "catacoustics", "ophiology", "folklorist",
                "procrastinator", "insultable", "camphors", "ventromedially",
                "unsuitability", "proaction", "anesthesias", "hodoscope",
                "popehoods"};

        speller = new StateSpeller("states.txt");

    }


    // ---------------------------------------------------
    // Tests for isSpellableFirstLetters (Does allow repeated states, only
    // returns a boolean)
    // ---------------------------------------------------

    @Test
    void testIsSpellableFirstLetters() {
        for (String word : spellableFirstLettersNoRepeats) {
            assertTrue(speller.isSpellableFirstLetters(word),
                    word + " should be spellable!");
        }

        for (String word : spellableFirstLettersOnlyByRepeatingStates) {
            assertTrue(speller.isSpellableFirstLetters(word),
                    word + " should be spellable!");
        }

        for (String word : nonSpellable) {
            assertFalse(speller.isSpellableFirstLetters(word),
                    word + " should not be spellable!");
        }

    }

    // ---------------------------------------------------
    // Tests for howToSpellFirstLetters (Does not allow repeated states, returns
    // states)
    // ---------------------------------------------------

    @Test
    void testHowToSpellFirstLetters() {
        for (String word : spellableFirstLettersNoRepeats) {
            assertNotNull(speller.howToSpellFirstLetters(word),
                    word + " should be spellable!");
        }

        for (String word : nonSpellable) {
            assertNull(speller.howToSpellFirstLetters(word),
                    word + " should not be spellable!");
        }
    }

    @Test
    void testHowToSpellFirstLettersDoesNotAllowRepeats() {
        for (String word : spellableFirstLettersNoRepeats) {
            assertNotNull(speller.howToSpellFirstLetters(word),
                    word + " should be spellable!");
        }

        for (String word : spellableFirstLettersOnlyByRepeatingStates) {
            assertNull(speller.howToSpellFirstLetters(word),
                    word + " should not be spellable!");
        }
    }

    @Test
    void testHowToSpellFirstLettersCorrectStates() {

        // These are all examples where there is only one way to spell the
        // indicated word, so we don't need to worry about multiple possible
        // answers.

        assertEquals(
                List.of("Idaho", "Nevada", "California", "Arizona",
                        "New Mexico", "Texas"),
                speller.howToSpellFirstLetters("incant"));

        assertEquals(List.of("California", "Arizona", "New Mexico", "Texas",
                "Oklahoma"), speller.howToSpellFirstLetters("canto"));

        assertEquals(List.of("Florida", "Alabama", "Tennessee", "Arkansas",
                "Louisiana"), speller.howToSpellFirstLetters("fatal"));

        assertEquals(List.of("Arkansas", "Missouri", "Oklahoma", "Kansas"),
                speller.howToSpellFirstLetters("amok"));

    }

    // ---------------------------------------------------
    // Tests for howToSpell (Doesn't allow repeated states)
    // ---------------------------------------------------
    @Test
    void testHowToSpell() {
        assertNotNull("colorado", "colorado should not be spellable!");
        for (String word : spellableMultiNoRepeats) {
            assertNotNull(speller.howToSpell(word),
                    word + " should be spellable!");
        }

        for (String word : nonSpellable) {
            assertNull(speller.howToSpell(word),
                    word + " should not be spellable!");
        }
    }

    @Test
    void testHowToSpellDoesNotAllowRepeats() {
        for (String word : spellableMultiNoRepeats) {
            assertNotNull(speller.howToSpell(word),
                    word + " should be spellable!");
        }

        for (String word : spellableMultiOnlyByRepeatingStates) {
            assertNull(speller.howToSpell(word),
                    word + " should not be spellable!");
        }
    }

    @Test
    void testHowToSpellCorrectStates() {

        // These are all examples where there is only one way to spell the
        // indicated word, so we don't need to worry about multiple possible
        // answers.

        assertEquals(List.of("Florida", "Georgia", "North Carolina",
                "South Carolina"), speller.howToSpell("florigens"));

        assertEquals(List.of("Missouri", "Illinois", "Iowa", "Nebraska",
                "South Dakota"), speller.howToSpell("millions"));

        assertEquals(List.of("Colorado", "New Mexico", "Texas"),
                speller.howToSpell("colorant"));

        assertEquals(
                List.of("Virginia", "Tennessee", "Arkansas", "Missouri", "Iowa",
                        "Nebraska", "South Dakota"),
                speller.howToSpell("vitamins"));

    }

}
