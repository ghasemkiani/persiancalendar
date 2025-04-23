/*
  PersianCalendar.java
  2005-01-11 17:49:03
  Copyright © Ghasem Kiani <ghasemkiani@gmail.com>

  license: GPL
  See LICENSE file at project root.
*/

/*
  HISTORY:
  Version 3.0 2025-03-29:
    Implemented the astronomical calendar using the calculations from Calendrica.
  Version 3.0-pre 2010-12-16 (never released):
    Implemented the astronomical calendar using Calendrica.
  Version 2.1 2005-03-18:
    Improved documentation. Some i18n enhancements.
  Version 2.0 2005-02-21:
    First release.
*/
package com.ghasemkiani.util.icu;

import java.util.Date;
import java.util.Locale;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import static com.ghasemkiani.util.PersianCalendarUtils.*;
import com.ghasemkiani.util.PersianCalendarHelper;
import com.ghasemkiani.util.PersianCalendarConstants;

/**
  <p>This is an <em>astronomical</em> implementation of the Persian Calendar
  (also known as the Iranian or Jalali Calendar) based on the calendar
  framework of <strong>ICU4J</strong> (IBM's International Components for Unicode for
  Java).</p>

  <p>ICU4J is copyright International Business Machines Corporation (IBM).
  Please see the file <code>icu4j_3_2_license.html</code> included with
  this software package for information about ICU4J license.</p>

  <p><em>Previous versions of the Persian Calendar used an arithmetic algorithm which produced
  incorrect results in some years. This became practically important when it failed to recognize
  the year 1403 as a leap year, hence the necessity for this upgrade to an astronomical
  implementation.</em></p>
  
  <p>The current astronomical implementation uses a Java port of
  the Python code from Roozbeh Pournader's persiancalendar project
  (<a href="https://github.com/roozbehp/persiancalendar">https://github.com/roozbehp/persiancalendar</a>),
  which in turn is based on the Common Lisp code CALENDRICA 4.0 by E. M. Reingold and N. Dershowitz
  (see <a href="https://github.com/EdReingold/calendar-code2">https://github.com/EdReingold/calendar-code2</a>).</p>

  <p><strong>Usage Example</strong></p>
  <p>The following code snippet shows how to use the <code>{@link PersianCalendar}</code> class:
  <pre>
  import java.util.Date;
  import com.ibm.icu.util.TimeZone;
  import com.ibm.icu.util.ULocale;
  import com.ibm.icu.text.DateFormat;
  import com.ghasemkiani.util.icu.PersianCalendar;
  // ...

  PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
  DateFormat df = pc.getDateTimeFormat(DateFormat.FULL, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
  String result = df.format(new Date());
  // ...
  </pre>
  <p>Some possible results are shown below:</p>
  <table border="1">
    <caption>Examples of Persian Date Formatting</caption>
    <tr>
      <th>Locale</th>
      <th>Formatted String</th>
    </tr>
    <tr>
      <td style="direction:rtl;">فارسی</td>
      <td style="direction:rtl;">جمعه، ۷ اسفند ۱۳۸۳ ۱۲:۰۰:۳۹</td>
    </tr>
    <tr>
      <td style="direction:rtl;">فارسی (افغانستان)</td>
      <td style="direction:rtl;">جمعه، ۷ حوت ۱۳۸۳ ۱۲:۰۰:۳۹</td>
    </tr>
    <tr>
      <td style="direction:rtl;">العربية</td>
      <td style="direction:rtl;">الجمعة, ٧ إسفند, ١٣٨٣ ١٢:٠٠:٣٩ م</td>
    </tr>
    <tr>
      <td>English</td>
      <td>Friday, Esfand 7, 1383 12:00:39 PM</td>
    </tr>
    <tr>
      <td>Français</td>
      <td>vendredi 7 Esfand 1383 12:00:39</td>
    </tr>
    <tr>
      <td>Deutsch</td>
      <td>Freitag, 7. Esfand 1383 12:00:39</td>
    </tr>
    <tr>
      <td>Русский</td>
      <td>7 Эсфанд 1383 г. 12:00:39</td>
    </tr>
    <tr>
      <td>Türkçe</td>
      <td>07 Isfend 1383 Cuma 12:00:39</td>
    </tr>
    <tr>
      <td>Esperanto</td>
      <td>vendredo, 7-a de esfando 1383 12:00:39</td>
    </tr>
  </table>
  @author <a href="mailto:ghasemkiani@gmail.com">Ghasem Kiani</a>
  @version 3.0
*/
public class PersianCalendar extends Calendar implements PersianCalendarConstants {
  private static String copyright = "Copyright \u00a9 2005-2010, Ghasem Kiani <ghasemkiani@gmail.com>. All Rights Reserved.";
  /**
    Before Hijra Era.
  */
  public static final int BH = 0;
  /**
    After Hijra Era.
  */
  public static final int AH = 1;

  /**
    Constructs a Persian calendar with the default time zone and locale.
  */
  public PersianCalendar() {
    this(TimeZone.getDefault(), ULocale.getDefault());
  }
  /**
    Constructs a Persian calendar with the specified time zone and the default locale.

    @param zone the desired timezone.
  */
  public PersianCalendar(TimeZone zone) {
    this(zone, ULocale.getDefault());
  }
  /**
    Constructs a Persian calendar with the default time zone and the specified locale.

    @param aLocale the desired locale.
  */
  public PersianCalendar(Locale aLocale) {
    this(ULocale.forLocale(aLocale));
  }
  /**
    Constructs a Persian calendar with the default time zone and the specified locale.

    @param locale the desired locale.
  */
  public PersianCalendar(ULocale locale) {
    this(TimeZone.getDefault(), locale);
  }
  /**
    Constructs a Persian calendar with the specified time zone and locale.

    @param zone the desired timezone.
    @param aLocale the desired locale.
  */
  public PersianCalendar(TimeZone zone, Locale aLocale) {
    this(zone, ULocale.forLocale(aLocale));
  }
  /**
    Constructs a Persian calendar with the specified time zone and locale.

    @param zone the desired timezone.
    @param locale the desired locale.
  */
  public PersianCalendar(TimeZone zone, ULocale locale) {
    super(zone, locale);
    setTimeInMillis(System.currentTimeMillis());
  }
  /**
    Constructs a Persian calendar with the default time zone and locale
    and sets its time to the specified date-time.

    @param date the date of this calendar object.
  */
  public PersianCalendar(Date date) {
    super(TimeZone.getDefault(), ULocale.getDefault());
    setTime(date);
  }
  /**
    Constructs a Persian calendar with the default time zone and locale
    and sets its time to the specified date.

    @param year the Persian year.
    @param month the Persian month (zero-based).
    @param date the Persian day of month.
  */
  public PersianCalendar(int year, int month, int date) {
    super(TimeZone.getDefault(), ULocale.getDefault());
    set(ERA, AH);
    set(YEAR, year);
    set(MONTH, month);
    set(DATE, date);
  }
  /**
    Constructs a Persian calendar with the default time zone and locale
    and sets its time to the specified time.

    @param year the Persian year.
    @param month the Persian month (zero-based).
    @param date the Persian day of month.
    @param hour the hours part of time.
    @param minute the minutes part of time.
    @param second the seconds part of time.
  */
  public PersianCalendar(int year, int month, int date, int hour, int minute, int second) {
    super(TimeZone.getDefault(), ULocale.getDefault());
    set(ERA, AH);
    set(YEAR, year);
    set(MONTH, month);
    set(DATE, date);
    set(HOUR_OF_DAY, hour);
    set(MINUTE, minute);
    set(SECOND, second);
  }
  private static final int LIMITS[][] = {
    // Minimum, GreatestMinimum, LeastMaximum, Maximum
    { 0, 0, 1, 1 },                           // ERA
    { 1, 1, 5000000, 5000000 },               // YEAR
    { 0, 0, 11, 11 },                         // MONTH
    { 1, 1, 51, 52 },                         // WEEK_OF_YEAR
    { 0, 0, 5, 6 },                           // WEEK_OF_MONTH
    { 1, 1, 29, 31 },                         // DAY_OF_MONTH
    { 1, 1, 365, 366 },                       // DAY_OF_YEAR
    { /* */ },                                // DAY_OF_WEEK
    { -1, -1, 4, 5 },                         // DAY_OF_WEEK_IN_MONTH
    { /* */ },                                // AM_PM
    { /* */ },                                // HOUR
    { /* */ },                                // HOUR_OF_DAY
    { /* */ },                                // MINUTE
    { /* */ },                                // SECOND
    { /* */ },                                // MILLISECOND
    { /* */ },                                // ZONE_OFFSET
    { /* */ },                                // DST_OFFSET
    { -5000001, -5000001, 5000001, 5000001 }, // YEAR_WOY
    { /* */ },                                // DOW_LOCAL
    { -5000000, -5000000, 5000000, 5000000 }, // EXTENDED_YEAR
    { /* */ },                                // JULIAN_DAY
    { /* */ },                                // MILLISECONDS_IN_DAY
  };
  protected int handleGetLimit(int field, int limitType) {
    return LIMITS[field][limitType];
  }
  protected int handleGetMonthLength(int extendedYear, int month) {
    if(month < 6) return 31;
    if(month < 11) return 30;
    boolean leap = PersianCalendarHelper.isLeapYear(extendedYear);
    return leap ? 30 : 29;
  }
  protected int handleGetYearLength(int extendedYear) {
    boolean leap = PersianCalendarHelper.isLeapYear(extendedYear);
    return leap ? 366 : 365;
  }
  protected int handleComputeMonthStart(int extendedYear, int month, boolean useMonth) {
    return (int)PersianCalendarHelper.pj(extendedYear, month, 0);
  }
  protected int handleGetExtendedYear() {
    int year;
    if (newerField(EXTENDED_YEAR, YEAR) == EXTENDED_YEAR) {
      year = internalGet(EXTENDED_YEAR, 1);
    } else {
      int era = internalGet(ERA, AH);
      if (era == BH) {
        year = 1 - internalGet(YEAR, 1); // Convert to extended year
      } else {
        year = internalGet(YEAR, 1);
      }
    }
    return year;
  }
  protected void handleComputeFields(int julianDay) {
    long r = PersianCalendarHelper.jp(julianDay);
    int year = (int)y(r);
    int month = m(r);
    int day = d(r);
    internalSet(ERA, year > 0? AH: BH);
    internalSet(YEAR, year > 0? year: 1 - year);
    internalSet(EXTENDED_YEAR, year);
    internalSet(MONTH, month);
    internalSet(DAY_OF_MONTH, day);
    internalSet(DAY_OF_YEAR, day + month * 30 + Math.min(6, month));
  }
  /**
    Adds the specified amount to the specified field of this calendar.
    This is overriden to correct the behavior at the end of the leap years.

    @param field the field index.
    @param amount the amount to add.
  */
  public void add(int field, int amount) {
    switch (field) {
    case MONTH: {
      int month = get(MONTH);
      int extendedYear = get(EXTENDED_YEAR);
      if (amount > 0) {
        extendedYear += amount / 12;
        month += amount % 12;
        if(month > 11) {
          month -= 12;
          extendedYear++;
        }
      } else {
        amount = -amount;
        extendedYear -= amount / 12;
        month -= amount % 12;
        if(month < 0) {
          month += 12;
          extendedYear--;
        }
      }
      set(EXTENDED_YEAR, extendedYear);
      set(MONTH, month);
      pinField(DAY_OF_MONTH);
      break;
    }
    default:
      super.add(field, amount);
      break;
    }
  }
  /**
    <p>Type of this calendar.</p>
    <p>Type is used for loading resources.</p>

    @return type of this calendar ("persian").
  */
  public String getType() {
    return "persian";
  }
}
