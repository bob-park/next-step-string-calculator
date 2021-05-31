package com.hwpark.stringcalculator.calculator;

import static com.hwpark.stringcalculator.utils.CommonUtils.isEmpty;

import com.hwpark.stringcalculator.exception.InvalidInputException;
import com.hwpark.stringcalculator.exception.NegativeNumberException;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {

  private static final String REGEX_SEPARATOR = "//(.?)\\\\n(.*)";

  private static final String[] DEFAULT_SEPARATORS = {",", ";"};
  private static final String[] ESCAPE_CHAR_LIST = {"*", ".", "?"};

  private static final Pattern pattern = Pattern.compile(REGEX_SEPARATOR);

  public int add(String input) {

    var matcher = pattern.matcher(input);

    if (!matcher.matches()) {
      throw new InvalidInputException();
    }

    var splitString = getDefaultSplits();

    String customSeparator = matcher.group(1);
    String valueStr = matcher.group(2);

    if (isEmpty(valueStr)) {
      return 0;
    }

    if (!isEmpty(customSeparator)) {
      splitString = customSeparator;
    }

    int[] numbers = extractNumber(valueStr, escapeSeparator(splitString));

    return Arrays.stream(numbers).sum();
  }

  private int[] extractNumber(String value, String splitStr) {

    String[] tokens = value.split(splitStr);

    return Arrays.stream(tokens).mapToInt(this::toInt).toArray();
  }

  private String getDefaultSplits() {
    var builder = new StringBuilder();

    for (String separator : DEFAULT_SEPARATORS) {
      builder.append(separator);
      builder.append("|");
    }

    builder.deleteCharAt(builder.lastIndexOf("|"));

    return builder.toString();
  }

  private int toInt(String value) {

    var result = Integer.parseInt(value);

    if (result < 0) {
      throw new NegativeNumberException();
    }

    return result;
  }

  /**
   * regex 에서 사용하는 문자를 escape 해주는 메소드
   *
   * @param separator custom separator
   * @return {@code String}
   */
  private String escapeSeparator(String separator) {

    String result = separator;

    for (String escapeChar : ESCAPE_CHAR_LIST) {
      if (separator.contains(escapeChar)) {
        result =
            separator.replaceAll(
                String.format("[%s]", escapeChar), String.format("\\\\%s", escapeChar));
      }
    }

    return result;
  }
}
