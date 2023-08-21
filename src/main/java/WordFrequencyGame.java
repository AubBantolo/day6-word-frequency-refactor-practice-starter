import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {

        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(SPACE_DELIMITER);

                List<WordFrequencyInfo> wordFrequencyInfoList = Arrays.stream(words)
                        .map(s -> new WordFrequencyInfo(s,1))
                        .collect(Collectors.toList());

                //get the map for the next step of sizing the same word
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
    }


    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .collect(Collectors.groupingByConcurrent(WordFrequencyInfo::getValue));
    }
}
