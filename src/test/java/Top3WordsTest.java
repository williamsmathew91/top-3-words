import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class Top3WordsTest {

    private Top3Words top3Words;

    @Before
    public void setUp() {
        top3Words = new Top3Words();
    }

    @Test
    public void shouldReturnEmptyArrayIfPassedNull() {
        assertArrayEquals(new String[]{}, top3Words.find(null));
    }

    @Test
    public void shouldReturnEmptyArrayIfPassedEmptyString() {
        assertArrayEquals(new String[]{}, top3Words.find(""));
    }

    @Test
    public void shouldReturnEmptyArrayIfPassedBlankString() {
        assertArrayEquals(new String[]{}, top3Words.find("  "));
    }

    @Test
    public void shouldJustReturnWordIfStringOnlyContainsWord() {
        assertArrayEquals(new String[]{"word"}, top3Words.find("word"));
    }

    @Test
    public void shouldReturnEmptyArrayIfStringOnlyContainsPunctuation() {
        assertArrayEquals(new String[]{}, top3Words.find(".,\n\r"));
    }

    @Test
    public void shouldTreatWordsSeparatedByHyphenAsTwoSeparateWords() {
        assertArrayEquals(new String[]{"some", "word"}, top3Words.find("some-word"));
    }

    @Test
    public void shouldTreatWordsWithApostrophesAsSeparateWords() {
        assertArrayEquals(new String[]{"don't", "dont"}, top3Words.find("don't dont"));
    }

    @Test
    public void shouldReturnBothWordsAsArrayIfOnlyTwoWordsAreContainedInTheString() {
        assertArrayEquals(new String[]{"otherword", "word"}, top3Words.find("word otherword"));
    }

    @Test
    public void shouldReturnWordsAsArrayIfSeparatedByArbitraryLengthWhitespace() {
        assertArrayEquals(new String[]{"nextword", "otherword", "word"}, top3Words.find("word     otherWord   nextWord"));
    }

    @Test
    public void shouldReturnSingleWordIfSameWordAppearsTwiceButWithDifferentCase() {
        assertArrayEquals(new String[]{"word"}, top3Words.find("word Word"));
    }

    @Test
    public void shouldIgnoreAllSpecialCharactersAndNumbersWhenParsingWords() {
        assertArrayEquals(new String[]{"someotherword", "word"}, top3Words.find("word\r\n,.!@£$%^&*()#<>?/;:\"\\|}]{[+=_±§1234567890 someOtherWord"));
    }

    @Test
    public void shouldReturnTop3WordsOrderedAlphabeticallyIfGreaterThan3UniqueWords() {
        assertArrayEquals(new String[]{"ant", "cat", "word"}, top3Words.find("zip ant word cat"));
    }

    @Test
    public void shouldReturnTop3WordsIfMultipleWordsAppearMultipleTimes() {
        assertArrayEquals(new String[]{"zip", "ant", "cat"}, top3Words.find("zip ant zip word zip cat ant zip ant cat"));
    }

}