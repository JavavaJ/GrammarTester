package logic.testmaker.split_paragraph;

public class ParagraphOption {

    private int number;
    private String options;

    public ParagraphOption() { }

    public ParagraphOption(int number, String options) {
        this.number = number;
        this.options = options;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
