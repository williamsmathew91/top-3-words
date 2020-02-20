import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Top3Words {
    private static final String EMPTY_STRING = "";

    public static final String WORD_DISCRIMINATOR = "[ -]";
    public static final String VALID_WORD_CHARACTERS = "[^A-Za-z']";

    public String[] find(String input) {
        if (input == null || input.trim().length() == 0) return new String[]{};

        Map<String, Long> countsPerWord = countWords(input);

        return getTop3Words(countsPerWord);
    }

    private Map<String, Long> countWords(String input) {
        return Arrays.stream(input.split(WORD_DISCRIMINATOR))
                .map(word -> word.replaceAll(VALID_WORD_CHARACTERS, EMPTY_STRING))
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.toMap(word -> word, word -> 1L, Long::sum));
    }

    private String[] getTop3Words(Map<String, Long> countsPerWord) {
        return countsPerWord.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }
}