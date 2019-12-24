package logic.testmaker.split_paragraph;

public class BracedOrdinal {

    private int start;
    private int end;
    private int value;

    public BracedOrdinal() {};

    public BracedOrdinal(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BracedOrdinal{" +
                "start=" + start +
                ", end=" + end +
                ", value=" + value +
                '}';
    }
}
