/*
  DateFields.java
  2003-09-24 21:31:46
  Copyright © Ghasem Kiani <ghasemkiani@gmail.com>

  license: GPL
  See LICENSE file at project root.
*/

package com.ghasemkiani.util;

/**
  This class holds the fields of Persian date, i.e., the Persian year,
  month, and day. <code>{@link SimplePersianCalendar}</code> uses this class to
  set/get the Persian date.

  @author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
  @version 2.1
*/
@Deprecated
public class DateFields {
  /**
    This field denotes the Persian year.
  */
  private int year;
  /**
    Accessor method to assign a new value to year.
    @param year The new value to be assigned to year.
  */
  public void setYear(int year) {
    this.year = year;
  }
  /**
    Accessor method to get the value of year.
    @return The value of year.
  */
  public int getYear() {
    return year;
  }

  /**
    This field denotes the Persian month.
    <strong>Note:</strong> month is zero-based.
    See constants in <code>{@link PersianCalendarConstants}</code>.
  */
  private int month;
  /**
    Accessor method to assign a new value to month.
    @param month The new value to be assigned to month.
  */
  public void setMonth(int month) {
    this.month = month;
  }
  /**
    Accessor method to get the value of month.
    @return The value of month.
  */
  public int getMonth() {
    return month;
  }

  /**
    This field denotes the Persian day.
  */
  private int day;
  /**
    Accessor method to assign a new value to day.
    @param day The new value to be assigned to day.
  */
  public void setDay(int day) {
    this.day = day;
  }
  /**
    Accessor method to get the value of day.
    @return The value of day.
  */
  public int getDay() {
    return day;
  }
  /**
    Constructs a <code>DateFields</code> object with the date fields initialized to 0.
  */
  public DateFields() {
    this(0, 0, 0);
  }
  /**
    Constructs a <code>DateFields</code> object with the given date fields.

    @param year the Persian year.
    @param month the Persian month (zero-based).
    @param day the Persian day of month.
  */
  public DateFields(int year, int month, int day) {
    super();
    setYear(year);
    setMonth(month);
    setDay(day);
  }
  /**
    This method returns a usable string representation of this object.
    Month is incremented to show one-based Persian month index.

    @return a usable string representation of this object.
  */
  public String toString() {
    return "" + year + "/" + (month + 1) + "/" + day;
  }
}
