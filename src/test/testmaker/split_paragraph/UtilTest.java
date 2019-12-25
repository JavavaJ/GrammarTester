package test.testmaker.split_paragraph;

import logic.testmaker.split_paragraph.BracedOrdinal;
import logic.testmaker.split_paragraph.ParagraphOption;
import logic.testmaker.split_paragraph.Util;
import logic.testmaker.split_paragraph.exception.NoSuchBraceException;

import java.util.List;
import java.util.stream.Collectors;

public class UtilTest {

    private static final String testText = "My(7) sister and I are very different, __(26)__ we get on well together. She likes staying at " +
            "home in the evening __(27)__ watching television with parents. __(28)__ I prefer going out with my " +
            "friends. We like togo to clubs or the cinema. Sometimes we just go to a café. I have exams " +
            "soon, __(29)__ I’m not going out very much these days. My sister is six years older than " +
            "me, __(30)__ she works in a bank. She’s trying to save some money __(31)__ she’s going to get married " +
            "this year. Her fiancé’s name is Ferdinand. __(32)__, we all call him Freddy. People say I look " +
            "like my sister __(33)__ we both have brown eyes__(34)__ dark hair. __(35)__, we are very (777) different " +
            "in character. She’s very quiet, __(36)__ I’m a lot (6666) more sociable.\n";

    private static final String orderedText = "My sister and I are very different, __(26)__ we get on well together. She likes staying at " +
            "home in the evening __(27)__ watching television with parents. __(28)__ I prefer going out with my " +
            "friends. We like togo to clubs or the cinema. Sometimes we just go to a café. I have exams " +
            "soon, __(29)__ I’m not going out very much these days. My sister is six years older than " +
            "me, __(30)__ she works in a bank. She’s trying to save some money __(31)__ she’s going to get married " +
            "this year. Her fiancé’s name is Ferdinand. __(32)__, we all call him Freddy. People say I look " +
            "like my sister __(33)__ we both have brown eyes__(34)__ dark hair. __(35)__, we are very different " +
            "in character. She’s very quiet, __(36)__ I’m a lot more sociable.  \n" +
            "26. A) and B) but C) so D) because\n" +
            "27. A) however B) so C) and D) because\n" +
            "28. A) Because B) And C) So D) But\n" +
            "29. A) so B) however C) but D) and\n" +
            "30. A) however B) so C) because D) and\n" +
            "31. A) and B) so C) because D) but\n" +
            "32. A) However B) So C) But D) And\n" +
            "33. A) so B) because C) and D) however\n" +
            "34. A) but B) so C) however D) and\n" +
            "35. A) But B) So C) However D) And\n" +
            "36. A) however B) but C) and D) so\n";

    public static void testRemoveAllNumsExceptOne() {
        String outputText = "";
        try {
            outputText = Util.removeAllNumsExceptOne(7, testText);
        } catch (NoSuchBraceException e) {
            e.printStackTrace();
        }
        System.out.println(outputText);
    }

    public static void testPlaceNumAtStart() {
        String text = "";
        try {
            text = Util.removeAllNumsExceptOne(26, testText);
        } catch (NoSuchBraceException e) {
            e.printStackTrace();
        }
        text = Util.placeNumAtStart(26, text);
        System.out.println(text);
    }

    private static void testGetListOfOptions() {
        List<BracedOrdinal> bracedOrdinals = Util.getAllParensedNumbers(orderedText);
        List<ParagraphOption> options = Util.getListOfOptions(bracedOrdinals, orderedText);
        System.out.println(options);
    }

    private static void testGetOnlyParagraph() {
        List<BracedOrdinal> bracedOrdinals = Util.getAllParensedNumbers(orderedText);
        String onlyParagraph = Util.getOnlyParagraph(bracedOrdinals, orderedText);
        System.out.println(onlyParagraph);
    }

    private static void testGetFormatedParagraph() {
        String formatedParagraph = Util.getFormatedParagraph(orderedText);
        System.out.println(formatedParagraph);
    }

    private static void testJustThatFunction() {
        List<BracedOrdinal> bracedOrdinals = Util.getAllParensedNumbers(orderedText);
        String onlyParagraph = Util.getOnlyParagraph(bracedOrdinals, orderedText);
        List<Integer> qNums = bracedOrdinals.stream()
                .map(BracedOrdinal::getValue)
                .collect(Collectors.toList());
        List<ParagraphOption> listOfOptions = Util.getListOfOptions(bracedOrdinals, orderedText);

        List<ParagraphOption> currOptionSingleList = listOfOptions.stream()
                .filter(option -> option.getNumber() == 20)
                .collect(Collectors.toList());

        ParagraphOption neededOption = null;
        for (ParagraphOption option : listOfOptions) {
            if (option.getNumber() == 27) {
                neededOption = option;
                break;
            }
        }

        System.out.println(neededOption);
    }

    public static void main(String[] args) {
//        testRemoveAllNumsExceptOne();
//        testPlaceNumAtStart();
//        testGetListOfOptions();
//        testGetOnlyParagraph();
//        testGetFormatedParagraph();
//        testJustThatFunction();
        List<BracedOrdinal> bracedOrdinals = Util.getAllParensedNumbers(orderedText);
        String onlyOptionsText = Util.getOnlyOptionsText(bracedOrdinals, orderedText);
        System.out.println(onlyOptionsText);
    }

}
