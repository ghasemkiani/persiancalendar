/*
  PersianCalendarHelper.java
  2025-03-29 10:34:00
  Copyright © Ghasem Kiani <ghasemkiani@gmail.com>

  license: GPL
  See LICENSE file at project root.
*/

package com.ghasemkiani.util;

import com.ghasemkiani.util.calendrica.Calendrica;

/**
  This class contains algorithms for converting Julian days to the Persian calendar
  system, and vice versa. The astronomical calculations are performed by code from the
  <a href="http://emr.cs.iit.edu/home/reingold/calendar-book/Calendrica.html">Calendrica applet</a>
  contained in the compact disk that accompanies the book <em>Calendrical Calculations: The Millennium Edition</em>
  by Edward M. Reingold and Nachum Dershowitz (Cambridge University Press, second printing, 2002).

  @author <a href="mailto:ghasemkiani@gmail.com">Ghasem Kiani</a>
  @version 3.0
*/
public class PersianCalendarHelper {
  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private PersianCalendarHelper() {
    // Utility class; should not be instantiated
    throw new IllegalStateException("Utility class");
  }
  private static long DIFF = 1721425L;
  /**
    Determines if the specified year is a leap year in the Persian calendar.

    @param year the "Persian" year.
    @return <code>true</code> if <code>year</code> is a leap year, <code>false</code> otherwise.
  */
  public static boolean isLeapYear(long year) {
    return Calendrica.persianLeapYear((int)year);
  }
  /**
    Returns the Julian day corresponding to the specified date in the Persian calendar.

    @param y the Persian year.
    @param m the Persian month.
    @param d the Persian day.
    @return the Julian day corresponding to the specified date in the Persian calendar.
  */
  public static long pj(long y, int m, int d) {
    long j = Calendrica.fixedFromPersian(new int[] {(int)y,m+1,d}) + DIFF;
    return j;
  }
  /**
    Returns the date in the Persian calendar corresponding to the specified Julian day.
    The date fields (year, month, and day) are packed into a long value. See <code>{@link PersianCalendarUtils}</code>
    class for extraction of fields from the packed long value.

    @param j the Julian day.
    @return a packed long value containing the corresponding Persian year, month, and day.
  */
  public static long jp(long j) {
    int[] p = Calendrica.persianFromFixed(j-DIFF);
    long year = p[0];
    int month = p[1]-1;
    int day = p[2];
    return (year << 16) | (month << 8) | day;
  }
}
