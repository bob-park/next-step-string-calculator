package com.hwpark.stringcalculator.utils;

public class CommonUtils {

  private CommonUtils() {}

  public static boolean isEmpty(Object obj) {

    if (obj == null) {
      return true;
    }

    if (obj instanceof String) {

      String str = (String) obj;

      return str.isBlank();
    }

    return false;
  }
}
