public class WordFrequencyInfo {
    private final String word;
    private final Long frequency;

    public WordFrequencyInfo(String word, Long frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public Long getFrequency() {
        return frequency;
    }
}
