package logic.testmaker.split_paragraph;

import logic.testmaker.split_paragraph.exception.NoSuchBraceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {

    public static String getFormatedParagraph(String text) {
        List<BracedOrdinal> bracedOrdinals = getAllParensedNumbers(text);
        String onlyParagraph = getOnlyParagraph(bracedOrdinals, text);
        List<Integer> qNums = bracedOrdinals.stream()
                .map(BracedOrdinal::getValue)
                .collect(Collectors.toList());
        List<ParagraphOption> listOfOptions = getListOfOptions(bracedOrdinals, text);

        StringBuilder sb = new StringBuilder("\n \n");

        for (Integer num : qNums) {
            String paragWithOneNum = "";
            try {
                paragWithOneNum = removeAllNumsExceptOne(num, onlyParagraph);
            } catch (NoSuchBraceException e) {
                e.printStackTrace();
            }
            paragWithOneNum = placeNumAtStart(num, paragWithOneNum);
            sb.append(paragWithOneNum);

            List<ParagraphOption> currOptionSingleList = listOfOptions.stream()
                    .filter(option -> option.getNumber() == num)
                    .collect(Collectors.toList());
            ParagraphOption currOption = null;
            if (currOptionSingleList.size() > 0) {
                currOption = currOptionSingleList.get(0);
            }
            sb.append(" \n");
            sb.append(currOption.getOptions());
            sb.append("\n \n");
        }
        return sb.toString();
    }

    public static String removeAllNumsExceptOne(int num, String text) throws NoSuchBraceException {

        List<BracedOrdinal> bracedOrdinals = getAllParensedNumbers(text);
        List<BracedOrdinal> rightBrace = bracedOrdinals.stream()
                .filter(brace -> brace.getValue() == num)
                .collect(Collectors.toList());
        if (rightBrace.size() == 0) {
            throw new NoSuchBraceException("Num is not one of the appropriate braces");
        }
        List<BracedOrdinal> bracesToRemove = bracedOrdinals.stream()
                .filter(brace -> brace.getValue() != num)
                .collect(Collectors.toList());

        StringBuilder textSb = new StringBuilder(text);

        for (BracedOrdinal brace : bracesToRemove) {
            StringBuilder newSB = new StringBuilder();

            int start = brace.getStart();
            int end = brace.getEnd();
            int len = end - start;

            newSB.append(textSb.substring(0, start))
                    .append(getNTimesReplacer(len))
                    .append(textSb.substring(end));
            textSb = new StringBuilder(newSB);
        }

        text = textSb.toString();
        text = text.replaceAll("±", "");
        return text;
    }

    public static String placeNumAtStart(int num, String text) {
        return num + ". " + text;
    }

    public static List<BracedOrdinal> getAllParensedNumbers(String text) {
        // the regex for pattern of digits in () parens
        String pattern = "\\((\\d)+\\)";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);

        List<BracedOrdinal> bracedOrdinals = new ArrayList<>();

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            Integer value = Integer.valueOf(text.substring(start + 1, end - 1));

            BracedOrdinal bracedOrdinal = new BracedOrdinal(start, end, value);
            bracedOrdinals.add(bracedOrdinal);
        }
        return bracedOrdinals;
    }

    // Return "^" symbol as many times as parameter specifies
    private static String getNTimesReplacer(int n) {
        String safeReplacer = "±";
        return String.join("", Collections.nCopies(n, safeReplacer));
    }

    public static List<ParagraphOption> getListOfOptions(List<BracedOrdinal> bracedOrdinals, String text) {

        List<ParagraphOption> options = new ArrayList<>();
        List<Integer> numbers = bracedOrdinals.stream()
                .map(BracedOrdinal::getValue)
                .collect(Collectors.toList());
        Integer max = Collections.max(numbers);

        String onlyOptionsText = getOnlyOptionsText(bracedOrdinals, text);
        for (Integer num : numbers) {
            String numMarker = String.valueOf(num) + ". ";
            int numMarkerLen = numMarker.length();
            int startIndex = onlyOptionsText.indexOf(numMarker);
            if (num != max) {
                String nextNumMarker = String.valueOf(num + 1) + ". ";
                int nextIndex = onlyOptionsText.indexOf(nextNumMarker);
                String optionText = onlyOptionsText.substring(startIndex + numMarkerLen, nextIndex);
                ParagraphOption option = new ParagraphOption(num, startIndex, optionText);
                options.add(option);
            } else {
                // if it's the last option - till the end of text
                String startLastIndex = String.valueOf(num) + ". ";
                int startLastIndexLen = startLastIndex.length();
                int lastIndex = onlyOptionsText.indexOf(startLastIndex);
                String optionText = onlyOptionsText.substring(lastIndex + startLastIndexLen);
                ParagraphOption option = new ParagraphOption(num, lastIndex, optionText);
                options.add(option);
            }
        }

        return options;
    }

    public static String getOnlyOptionsText(List<BracedOrdinal> bracedOrdinals, String text) {
        Integer min = bracedOrdinals.stream()
                .map(BracedOrdinal::getValue)
                .min(Integer::compare)
                .get();
        String minNumStrMarker = String.valueOf(min) + ". ";
        int minIndex = text.indexOf(minNumStrMarker);
        return text.substring(minIndex);
    }

    public static String getOnlyParagraph(List<BracedOrdinal> bracedOrdinals, String text) {
        Integer min = bracedOrdinals.stream()
                .map(BracedOrdinal::getValue)
                .min(Integer::compare)
                .get();
        String minNumStrMarker = String.valueOf(min) + ". ";
        int minIndex = text.indexOf(minNumStrMarker);
        return text.substring(0, minIndex);
    }

}
