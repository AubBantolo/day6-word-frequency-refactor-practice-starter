import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String calculateFrequency(String sentence) {
        try {
            String[] words = sentence.split(SPACE_DELIMITER);

            List<WordFrequencyInfo> wordFrequencyInfoList = Arrays.stream(words)
                    .map(s -> new WordFrequencyInfo(s, 1))
                    .collect(Collectors.toList());

            Map<String, List<WordFrequencyInfo>> wordListMap = getListMap(wordFrequencyInfoList);

            return wordListMap.entrySet().stream()
                    .map(stringListEntry -> new WordFrequencyInfo(stringListEntry.getKey(), stringListEntry.getValue().size()))
                    .sorted((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount())
                    .map(wordFrequencyInfo -> wordFrequencyInfo.getValue() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                    .collect(Collectors.joining(NEW_DELIMITER));
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .collect(Collectors.groupingByConcurrent(WordFrequencyInfo::getValue));
    }
}
