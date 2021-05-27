package com.hwpark.stringcalculator;

import com.hwpark.stringcalculator.calculator.StringCalculator;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    var sc = new Scanner(System.in);

    StringCalculator calc = new StringCalculator();

    int result = calc.add(sc.nextLine());

    System.out.println("result : " + result);
  }
}
