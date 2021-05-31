package com.hwpark.stringcalculator.exception;

public class InvalidInputException extends ServiceRuntimeException {

  public InvalidInputException() {
    super("Invalid input.");
  }
}
