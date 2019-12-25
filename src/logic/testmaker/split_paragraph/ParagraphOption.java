package logic.testmaker.split_paragraph;

public class ParagraphOption {

    private int number;
    private int startIndex;
    private String options;

    public ParagraphOption() { }

    public ParagraphOption(int number, int startIndex, String options) {
        this.number = number;
        this.startIndex = startIndex;
        this.options = options;
//        options.replaceAll(String.valueOf(number), "");
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ParagraphOption{" +
                "number=" + number +
                ", options='" + options + '\'' +
                '}';
    }
}
