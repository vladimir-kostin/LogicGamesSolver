package com.vkostin.util;

public class StringUtils {

  public static boolean isNotBlank(String str) {
    if (null == str)
      return false;

    return str.chars()
            .anyMatch(ch -> !Character.isWhitespace(ch));
  }

}
