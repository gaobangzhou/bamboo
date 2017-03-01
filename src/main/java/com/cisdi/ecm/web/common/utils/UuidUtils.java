package com.cisdi.ecm.web.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class UuidUtils
{
  private static SecureRandom random = new SecureRandom();

  public static String getUUID()
  {
    String uuid = UUID.randomUUID().toString().toUpperCase();
    return uuid;
  }

  public static String getUUIDShort()
  {
    return getUUID().replaceAll("-", "");
  }

  public static long randomLong()
  {
    return Math.abs(random.nextLong());
  }

  public static String randomBase64(int length)
  {
    byte[] randomBytes = new byte[length];
    random.nextBytes(randomBytes);
    return new String(BASE64.encode(randomBytes));
  }

  public static void main(String[] args) {
    System.out.println(randomBase64(2));
    System.out.println(randomLong());
  }
}