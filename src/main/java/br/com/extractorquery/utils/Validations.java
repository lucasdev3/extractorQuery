package br.com.extractorquery.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {


  public static Boolean isNumber(String number) {
    String pattern = "\\d";
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(number);
    return matcher.find();
  }

}
