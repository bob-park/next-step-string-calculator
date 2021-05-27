package com.hwpark.stringcalculator.exception;

public class NegativeNumberException extends ServiceRuntimeException {

  public NegativeNumberException() {
    super("Entered negative number.");
  }
}
