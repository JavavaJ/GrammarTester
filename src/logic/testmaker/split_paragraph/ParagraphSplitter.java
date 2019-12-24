package logic.testmaker.split_paragraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParagraphSplitter {

    private static final String testText = "My (7) sister and I are very different, __(26)__ we get on well together.She likes staying at " +
            "home in the evening __(27)__ watching televisionwith parents. __(28)__ I prefer going out with my " +
            "friends. We like togo to clubs or the cinema. Sometimes we just go to a café. I haveexams " +
            "soon, __(29)__ I’m not going out very much these days. Mysister is six years older than " +
            "me, __(30)__ she works in a bank. She’strying to save some money __(31)__ she’s going to get married " +
            "thisyear. Her fiancé’s name is Ferdinand. __(32)__, we all call him Freddy.People say I look " +
            "like my sister __(33)__ we both have brown eyes__(34)__ dark hair. __(35)__, we are very (777) different " +
            "in character. She’svery quiet, __(36)__ I’m a lot (6666) more sociable.\n";

    public static List<Integer> findNumsInParenths(String text) {
        String numbers = "0123456789";
        List<Integer> inds = IntStream.range(0, text.length())
                .filter(i -> {
                    String currCharStr = String.valueOf(text.charAt(i));
                    return numbers.contains(currCharStr);
                })
                .mapToObj(num -> num)
                .collect(Collectors.toList());
        System.out.println(inds);
        return inds;
    }

    public static void main(String[] args) {

        String str = "house";
        String repeatedStr = String.join("", Collections.nCopies(3, str));
        System.out.println(repeatedStr); // househousehouse

    }

}
