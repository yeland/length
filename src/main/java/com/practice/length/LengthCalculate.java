package com.practice.length;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;


public class LengthCalculate {
    public static String calculate(String formula) {
        if (formula == null) {
            throw new IllegalArgumentException("Formula is null");
        }
        formatCheck(formula);
        divisorCheck(formula);
        return formulaCalculate(formula);
    }

    private static void formatCheck(String formula) {
        String newString = formula.replaceAll("\\s[+|\\-|*|/]\\s", "");
        if (newString.split("[+|\\-|*|/]").length > 1) {
            throw new IllegalArgumentException("Format is not correct0");
        }
        unitFormatCheck(formula);
    }

    private static void unitFormatCheck(String formula) {
        String[] wordsByAddSub = formula.split("[+|\\-]");
        boolean match = Arrays.stream(wordsByAddSub).allMatch(word -> word.contains("m"));
        if (!match) {
            throw new IllegalArgumentException("Format is not correct1");
        }
        for (String word :wordsByAddSub) {
            String[] wordsByMulDiv = word.split("[*|/]");
            int unitNumber = Arrays.stream(wordsByMulDiv).filter(ele -> ele.contains("m")).toArray().length;
            if (wordsByMulDiv.length > 1 && unitNumber != 1) {
                throw new IllegalArgumentException("Format is not correct2");
            }
        }
    }

    private static void divisorCheck(String formula) {
        String[] wordsByDiv = formula.split("[/]");
        boolean isZero = Arrays.stream(wordsByDiv).skip(1).anyMatch(word -> word.trim().startsWith("0"));
        if (isZero) {
            throw new IllegalArgumentException("Divisor is 0");
        }
    }

    private static String formulaCalculate(String formula) {
        String convertedString = formulaConvert(formula);
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        double mmResult;
        try {
            mmResult = (double) scriptEngine.eval(convertedString);
        } catch (ScriptException e) {
            mmResult = 0;
        }
        Length length = new Length(Math.round(mmResult), "mm");
        String result = length.toString() + " = " + length.convertToCm().toString() + " = " + length.convertToM().toString();
        return result;
    }

    private static String formulaConvert(String formulaString) {
        String[] numbers = formulaString.split(" ");
        String[] converted = Arrays.stream(numbers).map(number -> {
            if (number.contains("m")) {
                return String.valueOf(getLength(number).convertToMm().getNumber());
            }
            return number;
        }).toArray(String[]::new);
        return String.join(" ", converted);
    }

    private static Length getLength(String string) {
        double number;
        String unit;
        if (string.contains("cm")) {
            unit = "cm";
        } else if (string.contains("mm")) {
            unit = "mm";
        } else {
            unit = "m";
        }
        number = Double.parseDouble(string.replace(unit, "").trim());
        return new Length(number, unit);
    }
}