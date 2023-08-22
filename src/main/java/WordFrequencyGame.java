import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry;

public class WordFrequencyGame {
    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    //TODO: extract methods from calculateFrequency e.g. method for building the word-frequency map and another for building the string output
    public String calculateFrequency(String sentence) {
        try {
            String[] words = sentence.split(SPACE_DELIMITER);

            Map<String, Long> wordCountMap = Arrays.stream(words)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            Comparator<Entry<String, Long>> byWordFrequency = Entry.comparingByValue();

            return wordCountMap.entrySet().stream()
                    .sorted(byWordFrequency.reversed())
                    .map(this::buildWordFrequencyResult)
                    .collect(Collectors.joining(NEW_DELIMITER));
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private String buildWordFrequencyResult(Entry<String, Long> wordFrequencyEntry) {
        return String.format("%s %d", wordFrequencyEntry.getKey(), wordFrequencyEntry.getValue());
    }
}
