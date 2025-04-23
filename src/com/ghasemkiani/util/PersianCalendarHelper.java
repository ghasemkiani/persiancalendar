/*
	PersianCalendarHelper.java
	2005-02-20 21:23:17
	Copyright © Ghasem Kiani <ghasemkiani@yahoo.com>
	
	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with this program; if not, write to the Free Software
	Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.ghasemkiani.util;

import static com.ghasemkiani.util.PersianCalendarUtils.*;

/**
	This class contains algorithms for converting Julian days to the Persian 
	calendar system, and vice versa. The algorithms have been rewritten and 
	some bugs in the Java code have been corrected, but, originally, they have 
	been based on the "<a href="http://couprie.docspages.com/calmath/">Calendar 
	Math Add-In for Excel</a>" by <a href="mailto:kees@couprie.org">Kees 
	Couprie</a>, whose contribution is gratefully acknowledged.
	
	@author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
	@version 2.0
*/
public class PersianCalendarHelper
{
	/**
		Determines if the specified year is a leap year in the Persian calendar.
	*/
	public static boolean isLeapYear(long year)
	{
		long a = year - 474L;
		long b = mod(a, 2820L) + 474L;
		return mod((b + 38D) * 682D, 2816D) < 682L;
	}
	/**
		Returns the Julian day corresponding to the specified day in the Persian calendar.
	*/
	public static long pj(long year, int month, int day)
	{
		long a = year - 474L;
		long b = mod(a, 2820D) + 474L;
		return (EPOCH - 1L) + 1029983L * div(a, 2820D) + 365L * (b - 1L) + div(682L * b - 110L, 2816D) + (long)(month > 6? 30 * month + 6: 31 * month) + (long)day;
	}
	/**
		Returns the date in the Persian calendar corresponding to the specified Julian day. 
		The date fields (year, month, and day) are packed into a long value. See <code>{@link PersianCalendarUtils}</code>
		class for extraction of fields from the packed long value.
	*/
	public static long jp(long j)
	{
		long a = j - pj(475L, 0, 1);
		long b = div(a, 1029983D);
		long c = mod(a, 1029983D);
		long d = c != 1029982L? div(2816D * (double)c + 1031337D, 1028522D): 2820L;
		long year = 474L + 2820L * b + d;
		long f = (1L + j) - pj(year, 0, 1);
		int month = (int)(f > 186L? Math.ceil((double)(f - 6L) / 30D) - 1: Math.ceil((double)f / 31D) - 1);
		int day = (int)(j - (pj(year, month, 1) - 1L));
		return (year << 16) | (month << 8) | day;
	}
}
