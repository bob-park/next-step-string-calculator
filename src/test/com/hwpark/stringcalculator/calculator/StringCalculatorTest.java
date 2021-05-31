package com.hwpark.stringcalculator.calculator;

import com.hwpark.stringcalculator.exception.InvalidInputException;
import com.hwpark.stringcalculator.exception.NegativeNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {

  private StringCalculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new StringCalculator();
  }

  @DisplayName("test case 1")
  @ParameterizedTest
  @MethodSource("provideParams")
  void case1(String input, int result) {
    assertThat(calculator.add(input)).isEqualTo(result);
  }

  @DisplayName("test case 2")
  @ParameterizedTest
  @ValueSource(strings = {"//\\n-1", "//.\\n1.-2.3"})
  void case2(String input) {
    assertThatThrownBy(() -> calculator.add(input)).isInstanceOf(NegativeNumberException.class);
  }

  @DisplayName("test case 3")
  @ParameterizedTest
  @ValueSource(strings = {"13,2,23"})
  void case3(String input) {
    assertThatThrownBy(() -> calculator.add(input)).isInstanceOf(InvalidInputException.class);
  }

  private static Stream<Arguments> provideParams() {
    return Stream.of(
        Arguments.of("//\\n1,2;3", 6),
        Arguments.of("//*\\n1*2*3", 6),
        Arguments.of("//&\\n123&1&3", 127),
        Arguments.of("//?\\n10?20?30", 60));
  }
}
