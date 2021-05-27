package com.hwpark.stringcalculator.calculator;

import static com.hwpark.stringcalculator.utils.CommonUtils.isEmpty;

import com.hwpark.stringcalculator.exception.InvalidInputException;
import com.hwpark.stringcalculator.exception.NegativeNumberException;
import java.util.regex.Pattern;

public class StringCalculator {

  private static final String REGEX_SEPARATOR = "(\\/\\/(\\D+)\\\\n)?(.*)";

  private static final String[] DEFAULT_SEPARATORS = {",", ":"};

  private static final Pattern pattern = Pattern.compile(REGEX_SEPARATOR);

  public int add(String input) {

    var matcher = pattern.matcher(input);

    if (!matcher.matches()) {
      throw new InvalidInputException();
    }

    var splitString = getDefaultSplits();

    String customSeparator = matcher.group(2);
    String valueStr = matcher.group(3);

    if (isEmpty(valueStr)) {
      return 0;
    }

    if (!isEmpty(customSeparator)) {
      splitString = customSeparator;
    }

    return calculate(valueStr, escapeSeparator(splitString));
  }

  private int calculate(String value, String splitStr) {

    var sum = 0;

    String[] tokens = value.split(splitStr);

    for (String token : tokens) {
      sum += toInt(token);
    }

    return sum;
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

    if (separator.contains("*")) {
      result = separator.replaceAll("[*]", "\\\\*");
    } else if(separator.contains(".")){
      result = separator.replaceAll("[.]", "\\\\.");
    }

    return result;
  }
}
