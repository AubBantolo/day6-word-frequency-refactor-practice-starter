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

                wordFrequencyInfoList = wordListMap.entrySet().stream()
                                .map(stringListEntry -> new WordFrequencyInfo(stringListEntry.getKey(), stringListEntry.getValue().size()))
                                .sorted((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount())
                                .collect(Collectors.toList());

                StringJoiner joiner = new StringJoiner(NEW_DELIMITER);
                for (WordFrequencyInfo w : wordFrequencyInfoList) {
                    String s = w.getValue() + SPACE_CHAR + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();


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
