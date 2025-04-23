/*
 * In the name of God
 * DateFields.java
 * � Ghasem Kiani
 * 24/09/2003 09:31:46 PM
 * ghasemkiani@yahoo.com
 */

package com.ghasemkiani.util;

/**
	This class holds the fields of Persian date, namely the Persian year,
	month, and day. <code>SimplePersianCalendar</code> uses this class to
	set/get the Persian date.
	
	@author Ghasem Kiani
	@version 1.0
*/
public class DateFields
{
	// year
	/**
		This field denotes the Persian year.
	*/
	private int year;
	/**
		Accessor method to assign a new value to year.
		@param year The new value to be assigned to year.
	*/
	public void setYear(int year)
	{
		this.year = year;
	}
	/**
		Accessor method to fetch the value of year.
		@return The value of year.
	*/
	public int getYear()
	{
		 return year;
	}

	// month
	/**
		This field denotes the Persian month.
		<p>Note: month is zero based.
		See constants in <code>SimplePersianCalendar</code>.
	*/
	private int month;
	/**
		Accessor method to assign a new value to month.
		@param month The new value to be assigned to month.
	*/
	public void setMonth(int month)
	{
		this.month = month;
	}
	/**
		Accessor method to fetch the value of month.
		@return The value of month.
	*/
	public int getMonth()
	{
		 return month;
	}

	// day
	/**
		This field denotes the Persian day.
	*/
	private int day;
	/**
		Accessor method to assign a new value to day.
		@param day The new value to be assigned to day.
	*/
	public void setDay(int day)
	{
		this.day = day;
	}
	/**
		Accessor method to fetch the value of day.
		@return The value of day.
	*/
	public int getDay()
	{
		 return day;
	}

	public DateFields(int year, int month, int day)
	{
		super();
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	public DateFields()
	{
		this(0, 0, 0);
	}
	
	/**
		This method returns a usable string representation of this object.
		Month is incremented to show one-based Persian month index.
		
		@return A usable string representation of this object.
	*/
 
	public String toString()
	{
		return "" + year + "/" + (month + 1) + "/" + day;
	}
}
