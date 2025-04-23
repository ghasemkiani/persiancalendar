/*
  PersianCalendarUtils.java
  2005-02-20 05:56:36
  Copyright © Ghasem Kiani <ghasemkiani@gmail.com>

  license: GPL
  See LICENSE file at project root.
*/

package com.ghasemkiani.util;

/**
  This class contains some utility functions and constants used by other
  Persian Calendar classes.

  @author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
  @version 2.1
*/
public class PersianCalendarUtils {
  /**
   * Private constructor to prevent instantiation of this utility class.
   * @throws IllegalStateException Always thrown, as this class should not be instantiated.
   */
  private PersianCalendarUtils() {
    throw new IllegalStateException("Utility class");
  }
  /**
    Julian day corresponding to 1 Farvardin 1 A.H., corresponding to
    March 19, 622 A.D. by the Julian version of the Gregorian calendar.
  */
  public static final long EPOCH = 1948321;
  /**
    A modulo function suitable for our purpose.

    @param a the dividend.
    @param b the divisor.
    @return the remainder of integer division.
  */
  public static long mod(double a, double b) {
    return (long)(a - b * Math.floor(a / b));
  }
  /**
    An integer division function suitable for our purpose.

    @param a the dividend.
    @param b the divisor.
    @return the quotient of integer division.
  */
  public static long div(double a, double b) {
    return (long)Math.floor(a / b);
  }
  /**
    Extracts the year from a packed long value.

    @param r the packed long value.
    @return the year part of date.
  */
  public static long y(long r) {
    return r >> 16;
  }
  /**
    Extracts the month from a packed long value.

    @param r the packed long value
  .
    @return the month part of date.
  */
  public static int m(long r) {
    return (int)(r & 0xff00) >> 8;
  }
  /**
    Extracts the day from a packed long value.

    @param r the packed long value.
    @return the day part of date.
  */
  public static int d(long r) {
    return (int)(r & 0xff);
  }
}
