/**
 * This code is a Java conversion of the Python code available at:
 * <a href="https://github.com/roozbehp/persiancalendar">https://github.com/roozbehp/persiancalendar</a>
 * which was written by Roozbeh Pournader and is licensed under
 * the Apache License, Version 2.0.
 *
 * The original Python code is based on the Common Lisp code:
 * CALENDRICA 4.0
 * by E. M. Reingold and N. Dershowitz
 * which is also licensed under the Apache License, Version 2.0.
 *
 * <pre>
 * Original Common Lisp Code Header:
 * ================================================================
 *
 * The Functions (code, comments, and definitions) contained in this
 * file (the "Program") were written by Edward M. Reingold and Nachum
 * Dershowitz (the "Authors"), who retain all rights to them except as
 * granted in the License and subject to the warranty and liability
 * limitations listed therein.  These Functions are explained in the Authors'
 * book, "Calendrical Calculations", 4th ed. (Cambridge University
 * Press, 2018), and are subject to an international copyright.
 *
 * Licensed under the Apache License, Version 2.0 &lt;LICENSE or
 * https://www.apache.org/licenses/LICENSE-2.0&gt;.
 *
 * Sample values for the functions (useful for debugging) are given in
 * Appendix C of the book.
 *
 * Python Port and Further Modifications:
 * Copyright 2024 Roozbeh Pournader
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ================================================================
 * Java Conversion:
 * Copyright 2025 Ghasem Kiani
 *
 * This Java code is released under the GNU General Public License version 3 (GPLv3).
 * You may obtain a copy of the GPLv3 at:
 *
 * https://www.gnu.org/licenses/gpl-3.0.html
 * </pre>
 */

package com.ghasemkiani.util.calendrica;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * This class provides utility functions for calendrical calculations, including conversions
 * between Gregorian, Julian, and Persian (Jalali) calendars. It is a Java port of
 * the Python code from Roozbeh Pournader's persiancalendar project
 * (<a href="https://github.com/roozbehp/persiancalendar">https://github.com/roozbehp/persiancalendar</a>),
 * which in turn is based on the Common Lisp code CALENDRICA 4.0 by E. M. Reingold and N. Dershowitz
 * (see <a href="https://github.com/EdReingold/calendar-code2">https://github.com/EdReingold/calendar-code2</a>).
 * The class also includes functions for astronomical calculations relevant to calendar systems.
 */
public class Calendrica {
  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private Calendrica() {
    // Utility class; should not be instantiated
    throw new IllegalStateException("Utility class");
  }
  /**
   * The fixed date corresponding to the Gregorian epoch (1 January 0001 CE in the proleptic Gregorian calendar).
   */
  public static long GREGORIAN_EPOCH = rd(1);
  /**
   * The fixed date corresponding to the Julian epoch (30 December 0000 BCE in the proleptic Gregorian calendar).
   */
  public static long JULIAN_EPOCH = fixedFromGregorian(new int[] {0, 12, 30});
  /**
   * The Julian date of noon on 1 January 2000 CE (J2000.0). Used as a reference point in astronomical calculations.
   */
  public static double J2000 = hr(12) + gregorianNewYear(2000);
  /**
   * The mean length of a tropical year in days.
   */
  public static double MEAN_TROPICAL_YEAR = 365.242189;
  /**
   * The fixed date corresponding to the Persian epoch (19 March 622 CE in the Julian calendar).
   */
  public static long PERSIAN_EPOCH = fixedFromJulian(622, 3, 19);
  /**
   * An array representing the location of Tehran (latitude, longitude, altitude, time zone offset).
   */
  public static double[] TEHRAN = {35.68, 51.42, 1100, +3.5};
  /**
   * An array representing the location of Iran (latitude, longitude, altitude, time zone offset).
   */
  public static double[] IRAN = {35.5, 52.5, 0, +3.5};
  /**
   * The default locale used for computing the Persian calendar (currently set to Iran).
   */
  public static double[] persianLocale = IRAN;
  /**
   * Constant representing the solar longitude of the spring equinox (in degrees).
   */
  public static int SPRING = 0;

  /**
   * Shifts the value of x into the range [a..b). Returns x if a=b.
   *
   * @param x The value to shift.
   * @param a The lower bound of the range (inclusive).
   * @param b The upper bound of the range (exclusive).
   * @return The value of x shifted into the range [a..b), or x if a=b.
   */
  public static double mod3(double x, double a, double b) {
    if (a == b) {
      return x;
    } else {
      double r = a + ((x - a) % (b - a));
      if (r < a) {
        r += (b - a);
      }
      return r;
    }
  }

  /**
   * Sums powers of x with coefficients (from order 0 up) in the array a.
   *
   * @param x The value to raise to powers.
   * @param a An array of coefficients, where a[0] is the coefficient of x^0, a[1] is for x^1, and so on.
   * @return The sum of the powers of x multiplied by their corresponding coefficients. Returns 0 if the coefficient array is null or empty.
   */
  public static double poly(double x, double[] a) {
    if (a == null || a.length == 0) {
      return 0;
    } else {
      return a[0] + x * poly(x, Arrays.copyOfRange(a, 1, a.length));
    }
  }

  /**
   * Identity function for fixed dates/moments. If internal
   * timekeeping is shifted, change epoch to be RD date of
   * origin of internal count. epoch should be an integer.
   *
   * @param tee The fixed date or moment.
   * @return The input fixed date or moment, adjusted by an epoch offset (currently 0).
   */
  public static long rd(long tee) {
    long epoch = 0;
    return tee - epoch;
  }

  /**
   * Returns the sign of y.
   *
   * @param y The value to determine the sign of.
   * @return -1 if y is negative, +1 if y is positive, and 0 if y is zero.
   */
  public static int sign(double y) {
    if (y < 0) {
      return -1;
    } else if (y > 0) {
      return +1;
    } else {
      return 0;
    }
  }

  /**
   * Checks if a given Gregorian year is a leap year.
   *
   * @param gYear The Gregorian year to check.
   * @return {@code true} if the year is a leap year, {@code false} otherwise.
   */
  public static boolean gregorianLeapYear(int gYear) {
    return gYear % 4 == 0 && (gYear % 400 != 100 && gYear % 400 != 200 && gYear % 400 != 300);
  }

  /**
   * Converts a Gregorian date (year, month, day) to a fixed date number.
   *
   * @param gDate An array of three integers representing the Gregorian date in the format {year, month, day}.
   * @return The fixed date number corresponding to the Gregorian date.
   */
  public static long fixedFromGregorian(int[] gDate) {
    double year = gDate[0];
    double month = gDate[1];
    double day = gDate[2];
    return (long)(
             GREGORIAN_EPOCH - 1  // Days before start of calendar
             + 365 * (year - 1)  // Ordinary days since epoch
             + (long)Math.floor((year - 1) / 4)    // Julian leap days since epoch...
             - (long)Math.floor((year - 1) / 100)  // ...minus century years since epoch...
             + (long)Math.floor((year - 1) / 400)  // plus years since epoch divisible by 400.
             // Days in prior months this year assuming 30-day Feb
             + (long)Math.floor((367 * month - 362) / 12)
             // Correct for 28- or 29-day Feb
             + (month <= 2 ? 0 : (gregorianLeapYear((int)year) ? -1 : -2))
             + day); // Days so far this month.
  }

  /**
   * Determines the Gregorian year corresponding to a given fixed date.
   *
   * @param date The fixed date number.
   * @return The Gregorian year corresponding to the fixed date.
   */
  public static int gregorianYearFromFixed(long date) {
    long d0 = date - GREGORIAN_EPOCH;  // Prior days.
    long n400 = Math.floorDiv(d0, 146097);  // Completed 400-year cycles.
    long d1 = Math.floorMod(d0, 146097);  // Prior days not in n400.
    long n100 = Math.floorDiv(d1, 36524);  // 100-year cycles not in n400.
    long d2 = Math.floorMod(d1, 36524);  // Prior days not in n400 or n100.
    long n4 = Math.floorDiv(d2, 1461);  // 4-year cycles not in n400 or n100.
    long d3 = Math.floorMod(d2, 1461);  // Prior days not in n400, n100, or n4.
    long n1 = Math.floorDiv(d3, 365);  // Years not in n400, n100, or n4.
    long year = (long) (400 * n400 + 100 * n100 + 4 * n4 + n1);
    if (n100 == 4 || n1 == 4) {
      return (int)year;  // Date is day 366 in a leap year.
    } else {
      return (int)year + 1;  // Date is ordinal day (d % 365 + 1) in (year + 1).
    }
  }

  /**
   * Calculates the fixed date of January 1st of a given Gregorian year.
   *
   * @param gYear The Gregorian year.
   * @return The fixed date number corresponding to January 1st of the given year.
   */
  public static long gregorianNewYear(int gYear) {
    return fixedFromGregorian(new int[] {gYear, 1, 1});
  }

  /**
   * Converts a fixed date number to a Gregorian date (year, month, day).
   *
   * @param date The fixed date number.
   * @return An array of three integers representing the Gregorian date in the format {year, month, day}.
   */
  public static int[] gregorianFromFixed(long date) {
    int year = gregorianYearFromFixed(date);

    long priorDays = date - gregorianNewYear(year);  // This year
    // To simulate a 30-day Feb
    int correction = 0;
    if (date < fixedFromGregorian(new int[] {year, 3, 1})) {
      correction = 0;
    } else if (gregorianLeapYear(year)) {
      correction = 1;
    } else {
      correction = 2;
    }
    int month = (int) ((12 * (priorDays + correction) + 373) / 367);  // Assuming a 30-day Feb
    // Calculate the day by subtraction.
    int day = (int) (date - fixedFromGregorian(new int[] {year, month, 1}) + 1);
    return new int[] {year, month, day};
  }

  /**
   * Calculates the number of days between two Gregorian dates.
   *
   * @param gDate1 An array of three integers representing the first Gregorian date in the format {year, month, day}.
   * @param gDate2 An array of three integers representing the second Gregorian date in the format {year, month, day}.
   * @return The number of days from gDate1 until gDate2 (positive if gDate2 is after gDate1).
   */
  public static long gregorianDateDifference(int[] gDate1, int[] gDate2) {
    return fixedFromGregorian(gDate2) - fixedFromGregorian(gDate1);
  }

  /**
   * Checks if a given Julian year is a leap year.
   *
   * @param jYear The Julian year to check.
   * @return {@code true} if the year is a leap year, {@code false} otherwise.
   */
  public static boolean julianLeapYear(int jYear) {
    return (Math.floorMod(jYear, 4)) == (jYear > 0 ? 0 : 3);
  }

  /**
   * Converts a Julian date (year, month, day) to a fixed date number.
   *
   * @param year  The Julian year.
   * @param month The Julian month.
   * @param day   The Julian day of the month.
   * @return The fixed date number corresponding to the Julian date.
   */
  public static long fixedFromJulian(int year, int month, int day) {
    double y = year < 0 ? year + 1 : year;  // No year zero
    return (
             JULIAN_EPOCH - 1  // Days before start of calendar
             + (long)(365 * (y - 1))  // Ordinary days since epoch.
             + (long)Math.floor((y - 1) / 4)    // Leap days since epoch...
             // Days in prior months this year...
             + ((367 * month - 362) / 12)  // ...assuming 30-day Feb
             // Correct for 28- or 29-day Feb
             + (month <= 2 ? 0 : (julianLeapYear(year) ? -1 : -2))
             + day);          // Days so far this month.
  }

  /**
   * Converts a number of hours to a fraction of a day.
   *
   * @param x The number of hours.
   * @return The equivalent fraction of a day.
   */
  public static double hr(double x) {
    return x / 24;
  }

  /**
   * Converts an angle given in degrees, arcminutes, and arcseconds to degrees.
   *
   * @param d Degrees.
   * @param m Arcminutes.
   * @param s Arcseconds.
   * @return The angle in degrees.
   */
  public static double angle(double d, double m, double s) {
    return d + (m + s / 60) / 60;
  }

  /**
   * Converts an angle from degrees to radians, ensuring it's within the range [0, 360).
   *
   * @param theta The angle in degrees.
   * @return The angle in radians.
   */
  public static double radiansFromDegrees(double theta) {
    return (mod3(theta, 0, 360)) * Math.PI / 180;
  }

  /**
   * Calculates the sine of an angle given in degrees.
   *
   * @param theta The angle in degrees.
   * @return The sine of the angle.
   */
  public static double sinDegrees(double theta) {
    return Math.sin(radiansFromDegrees(theta));
  }

  /**
   * Calculates the cosine of an angle given in degrees.
   *
   * @param theta The angle in degrees.
   * @return The cosine of the angle.
   */
  public static double cosDegrees(double theta) {
    return Math.cos(radiansFromDegrees(theta));
  }

  /**
   * Calculates the tangent of an angle given in degrees.
   *
   * @param theta The angle in degrees.
   * @return The tangent of the angle.
   */
  public static double tanDegrees(double theta) {
    return Math.tan(radiansFromDegrees(theta));
  }

  /**
   * Extracts the longitude from a location array.
   *
   * @param location An array representing a location, where the second element is the longitude.
   * @return The longitude of the location.
   */
  public static double longitude(double[] location) {
    return location[1];
  }

  /**
   * Calculates the difference between Universal Time (UT) and local mean time at a given longitude.
   *
   * @param phi The longitude in degrees.
   * @return The time zone offset as a fraction of a day.
   */
  public static double zoneFromLongitude(double phi) {
    return phi / 360;
  }

  /**
   * Converts a local time (`teeEll`) at a given location to Universal Time.
   *
   * @param teeEll   The local time value.
   * @param location An array representing the location, including the longitude.
   * @return The equivalent Universal Time.
   */
  public static double universalFromLocal(double teeEll, double[] location) {
    return teeEll - zoneFromLongitude(longitude(location));
  }

  /**
   * Calculates the Equation of Time (as a fraction of a day) for a given moment in time.
   * The Equation of Time is the difference between apparent solar time and mean solar time.
   *
   * <p>Adapted from "Astronomical Algorithms" by Jean Meeus,
   * Willmann-Bell, 2nd edn., 1998, p. 185.</p>
   *
   * @param tee The moment in time (Julian day number).
   * @return The Equation of Time as a fraction of a day.
   */
  public static double equationOfTime(double tee) {
    double c = julianCenturies(tee);
    double[] lambdaCoeffs = {280.46645, 36000.76983, 0.0003032};
    double lambda = poly(c, lambdaCoeffs);
    double[] anomalyCoeffs = {357.52910, 35999.05030, -0.0001559, -0.00000048};
    double anomaly = poly(c, anomalyCoeffs);
    double[] eccentricityCoeffs = {0.016708617, -0.000042037, -0.0000001236};
    double eccentricity = poly(c, eccentricityCoeffs);
    double varepsilon = obliquity(tee);
    double y = Math.pow(tanDegrees(varepsilon / 2), 2);
    double equation = ((1D / 2 / Math.PI) *
                       (y * sinDegrees(2 * lambda)
                        - 2 * eccentricity * sinDegrees(anomaly)
                        + 4 * eccentricity * y * sinDegrees(anomaly)
                        * cosDegrees(2 * lambda)
                        - 0.5 * y * y * sinDegrees(4 * lambda)
                        - 1.25 * eccentricity * eccentricity
                        * sinDegrees(2 * anomaly)));
    return sign(equation) * Math.min(Math.abs(equation), hr(12));
  }

  /**
   * Converts apparent solar time (sundial time) at a given location to local mean time.
   *
   * @param tee      The apparent solar time value.
   * @param location An array representing the location, including the longitude.
   * @return The equivalent local mean time.
   */
  public static double localFromApparent(double tee, double[] location) {
    return tee - equationOfTime(universalFromLocal(tee, location));
  }

  /**
   * Converts apparent solar time (sundial time) at a given location to Universal Time.
   *
   * @param tee      The apparent solar time value.
   * @param location An array representing the location, including the longitude.
   * @return The equivalent Universal Time.
   */
  public static double universalFromApparent(double tee, double[] location) {
    return universalFromLocal(localFromApparent(tee, location), location);
  }

  /**
   * Calculates the Universal Time at midday (true noon) on a given fixed date at a specific location.
   *
   * @param date     The fixed date number.
   * @param location An array representing the location, including the longitude.
   * @return The Universal Time at midday on the given date and location.
   */
  public static double midday(double date, double[] location) {
    return universalFromApparent(date + hr(12), location);
  }

  /**
   * Calculates the number of Julian centuries since J2000.0 for a given moment in time.
   *
   * @param tee The moment in time (Julian day number).
   * @return The number of Julian centuries since J2000.0.
   */
  public static double julianCenturies(double tee) {
    return (dynamicalFromUniversal(tee) - J2000) / 36525;
  }

  /**
   * Calculates the obliquity of the ecliptic at a given moment in time.
   * The obliquity of the ecliptic is the angle between the Earth's equatorial plane and the plane of the Earth's orbit around the Sun.
   *
   * @param tee The moment in time (Julian day number).
   * @return The obliquity of the ecliptic in degrees.
   */
  public static double obliquity(double tee) {
    double c = julianCenturies(tee);
    return angle(23, 26, 21.448) + poly(c, new double[] {0, angle(0, 0, -46.8150), angle(0, 0, -0.00059), angle(0, 0, 0.001813)});
  }

  /**
   * Converts a Universal Time moment to Dynamical Time.
   * Dynamical Time is a more uniform time scale used in astronomy.
   *
   * @param teeRomU The moment in Universal Time (Julian day number).
   * @return The equivalent moment in Dynamical Time.
   */
  public static double dynamicalFromUniversal(double teeRomU) {
    return teeRomU + ephemerisCorrection(teeRomU);
  }

  /**
   * Calculates the difference between Dynamical Time (TD) and Universal Time (UT) in days for a given moment.
   * This is also known as the Ephemeris Correction (ΔT).
   *
   * <p>Adapted from "Astronomical Algorithms"
   * by Jean Meeus, Willmann-Bell (1991) for years
   * 1600-1986 and from polynomials on the NASA
   * Eclipse web site for other years.</p>
   *
   * @param tee The moment in time (Julian day number).
   * @return The difference between Dynamical Time and Universal Time in days.
   */
  public static double ephemerisCorrection(double tee) {
    int year = gregorianYearFromFixed((long) Math.floor(tee));
    double c = gregorianDateDifference(new int[] {1900, 1, 1}, new int[] {year, 7, 1}) / 36525.0;
    double y2000 = year - 2000.0;
    double c2051 = (-20 + 32 * Math.pow((year - 1820.0) / 100.0, 2)
                    + 0.5628 * (2150 - year)) / 86400.0;
    double[] c2006Coeffs = {62.92, 0.32217, 0.005589};
    double c2006 = poly(y2000, c2006Coeffs) / 86400.0;
    double[] c1987Coeffs = {63.86, 0.3345, -0.060374, 0.0017275, 0.000651814, 0.00002373599};
    double c1987 = poly(y2000, c1987Coeffs) / 86400.0;
    double[] c1900Coeffs = {-0.00002, 0.000297, 0.025184, -0.181133, 0.553040, -0.861938, 0.677066, -0.212591};
    double c1900 = poly(c, c1900Coeffs);
    double[] c1800Coeffs = {-0.000009, 0.003844, 0.083563, 0.865736, 4.867575, 15.845535, 31.332267, 38.291999, 28.316289, 11.636204, 2.043794};
    double c1800 = poly(c, c1800Coeffs);
    double y1700 = year - 1700.0;
    double[] c1700Coeffs = {8.118780842, -0.005092142, 0.003336121, -0.0000266484};
    double c1700 = poly(y1700, c1700Coeffs) / 86400.0;
    double y1600 = year - 1600.0;
    double[] c1600Coeffs = {120, -0.9808, -0.01532, 0.000140272128};
    double c1600 = poly(y1600, c1600Coeffs) / 86400.0;
    double y1000 = (year - 1000.0) / 100.0;
    double[] c500Coeffs = {1574.2, -556.01, 71.23472, 0.319781, -0.8503463, -0.005050998, 0.0083572073};
    double c500 = poly(y1000, c500Coeffs) / 86400.0;
    double y0 = year / 100.0;
    double[] c0Coeffs = {10583.6, -1014.41, 33.78311, -5.952053, -0.1798452, 0.022174192, 0.0090316521};
    double c0 = poly(y0, c0Coeffs) / 86400.0;
    double y1820 = (year - 1820.0) / 100.0;
    double[] otherCoeffs = {-20, 0, 32};
    double other = poly(y1820, otherCoeffs) / 86400.0;

    if (year >= 2051 && year <= 2150) {
      return c2051;
    } else if (year >= 2006 && year <= 2050) {
      return c2006;
    } else if (year >= 1987 && year <= 2005) {
      return c1987;
    } else if (year >= 1900 && year <= 1986) {
      return c1900;
    } else if (year >= 1800 && year <= 1899) {
      return c1800;
    } else if (year >= 1700 && year <= 1799) {
      return c1700;
    } else if (year >= 1600 && year <= 1699) {
      return c1600;
    } else if (year >= 500 && year <= 1599) {
      return c500;
    } else if (year > -500 && year < 500) {
      return c0;
    } else {
      return other;
    }
  }

  /**
   * Calculates the longitude of the sun at a given moment in time.
   *
   * <p>Adapted from "Planetary Programs and Tables from -4000
   * to +2800" by Pierre Bretagnon and Jean-Louis Simon,
   * Willmann-Bell, 1986.</p>
   *
   * @param tee The moment in time (Julian day number).
   * @return The longitude of the sun in degrees.
   */
  public static double solarLongitude(double tee) {
    double c = julianCenturies(tee);  // moment in Julian centuries
    double[] coefficients = {403406, 195207, 119433, 112392, 3891, 2819, 1721,
                             660, 350, 334, 314, 268, 242, 234, 158, 132, 129, 114,
                             99, 93, 86, 78, 72, 68, 64, 46, 38, 37, 32, 29, 28, 27, 27,
                             25, 24, 21, 21, 20, 18, 17, 14, 13, 13, 13, 12, 10, 10, 10,
                             10
                            };
    double[] multipliers = {0.9287892, 35999.1376958, 35999.4089666,
                            35998.7287385, 71998.20261, 71998.4403,
                            36000.35726, 71997.4812, 32964.4678,
                            -19.4410, 445267.1117, 45036.8840, 3.1008,
                            22518.4434, -19.9739, 65928.9345,
                            9038.0293, 3034.7684, 33718.148, 3034.448,
                            -2280.773, 29929.992, 31556.493, 149.588,
                            9037.750, 107997.405, -4444.176, 151.771,
                            67555.316, 31556.080, -4561.540,
                            107996.706, 1221.655, 62894.167,
                            31437.369, 14578.298, -31931.757,
                            34777.243, 1221.999, 62894.511,
                            -4442.039, 107997.909, 119.066, 16859.071,
                            -4.578, 26895.292, -39.127, 12297.536,
                            90073.778
                           };
    double[] addends = {270.54861, 340.19128, 63.91854, 331.26220,
                        317.843, 86.631, 240.052, 310.26, 247.23,
                        260.87, 297.82, 343.14, 166.79, 81.53,
                        3.50, 132.75, 182.95, 162.03, 29.8,
                        266.4, 249.2, 157.6, 257.8, 185.1, 69.9,
                        8.0, 197.1, 250.4, 65.3, 162.7, 341.5,
                        291.6, 98.5, 146.7, 110.0, 5.2, 342.6,
                        230.9, 256.1, 45.3, 242.9, 115.2, 151.8,
                        285.3, 53.3, 126.6, 205.7, 85.9,
                        146.1
                       };
    double lambda = 282.7771834
                    + 36000.76953744 * c
                    + 0.000005729577951308232 *
                    IntStream.range(0, coefficients.length)
                    .mapToDouble(i -> coefficients[i] * sinDegrees(addends[i] + multipliers[i] * c))
                    .sum();

    return mod3((lambda + aberration(tee) + nutation(tee)), 0, 360);
  }

  /**
   * Calculates the longitudinal nutation at a given moment in time.
   * Nutation is the periodic variation in the Earth's rotation axis.
   *
   * @param tee The moment in time (Julian day number).
   * @return The longitudinal nutation in degrees.
   */
  public static double nutation(double tee) {
    double c = julianCenturies(tee);  // moment in Julian centuries
    double[] capACoeffs = {124.90, -1934.134, 0.002063};
    double capA = poly(c, capACoeffs);
    double[] capBCoeffs = {201.11, 72001.5377, 0.00057};
    double capB = poly(c, capBCoeffs);
    return -0.004778 * sinDegrees(capA) - 0.0003667 * sinDegrees(capB);
  }

  /**
   * Calculates the aberration at a given moment in time.
   * Aberration is the apparent displacement of celestial objects due to the Earth's motion.
   *
   * @param tee The moment in time (Julian day number).
   * @return The aberration in degrees.
   */
  public static double aberration(double tee) {
    double c = julianCenturies(tee);  // moment in Julian centuries
    return 0.0000974 * cosDegrees(177.63 + 35999.01848 * c) - 0.005575;
  }

  /**
   * Approximates the moment in time at or before a given moment (`tee`)
   * when the solar longitude just exceeded a specified value (`lambda`).
   *
   * @param lambda The target solar longitude in degrees.
   * @param tee    The moment in time (Julian day number) to search before.
   * @return An approximate moment in time when the solar longitude was just above `lambda`.
   */
  public static double estimatePriorSolarLongitude(double lambda, double tee) {
    double rate = MEAN_TROPICAL_YEAR / 360;  // Mean change of one degree.

    double tau = tee - rate * (mod3((double) (solarLongitude(tee) - lambda), 0, 360));
    double capDelta = mod3((double) (solarLongitude(tau) - lambda), -180, 180);
    return Math.min(tee, tau - rate * capDelta);
  }

  /**
   * Sets the locale used for computing the Persian calendar.
   *
   * @param locale An array representing the locale (e.g., latitude, longitude, etc.).
   */
  public static void setPersianLocale(double[] locale) {
    persianLocale = locale;
  }

  /**
   * Calculates the Universal Time of true noon on a given fixed date in the currently set Persian calendar locale.
   *
   * @param date The fixed date number.
   * @return The Universal Time of true noon in the Persian locale.
   */
  public static double middayInPersianLocale(double date) {
    return midday(date, persianLocale);
  }

  /**
   * Determines the fixed date of the Astronomical Persian New Year (Nowruz) on or before a given fixed date.
   *
   * @param date The fixed date number to search before or on.
   * @return The fixed date of the Persian New Year.
   */
  public static long persianNewYearOnOrBefore(long date) {
    // Approximate time of equinox.
    double approx = estimatePriorSolarLongitude(
                      SPRING, middayInPersianLocale(date));
    long day = (long) Math.floor(approx) - 1;
    while (solarLongitude(middayInPersianLocale(day)) > SPRING + 2) {
      day++;
    }
    return day;
  }

  /**
   * Converts an Astronomical Persian date (year, month, day) to a fixed date number.
   *
   * @param pDate An array of three integers representing the Persian date in the format {year, month, day}.
   * @return The fixed date number corresponding to the Persian date.
   */
  public static long fixedFromPersian(int[] pDate) {
    int year = pDate[0];
    int month = pDate[1];
    int day = pDate[2];
    long newYear = persianNewYearOnOrBefore(
                     PERSIAN_EPOCH + 180  // Fall after epoch.
                     + (long) Math.floor(MEAN_TROPICAL_YEAR *
                                         (year > 0 ? year - 1 : year)));  // No year zero.
    return (newYear - 1  // Days in prior years.
            // Days in prior months this year.
            + (month <= 7 ? 31 * (month - 1) : 30 * (month - 1) + 6)
            + day);  // Days so far this month.
  }

  /**
   * Converts a fixed date number to an Astronomical Persian date (year, month, day).
   *
   * @param date The fixed date number.
   * @return An array of three integers representing the Persian date in the format {year, month, day}.
   */
  public static int[] persianFromFixed(long date) {
    long newYear = persianNewYearOnOrBefore(date);
    double yDouble = (newYear - PERSIAN_EPOCH) / MEAN_TROPICAL_YEAR + 1;
    int y = (int) Math.round(yDouble);
    int year = y > 0 ? y : y - 1;  // No year zero
    long dayOfYear = date - fixedFromPersian(new int[] {year, 1, 1}) + 1;
    int month;
    if (dayOfYear <= 186) {
      month = (int) Math.ceil((double) dayOfYear / 31);
    } else {
      month = (int) Math.ceil((double) (dayOfYear - 6) / 30);
    }
    // Calculate the day by subtraction
    int day = (int) (date - fixedFromPersian(new int[] {year, month, 1}) + 1);
    return new int[] {year, month, day};
  }

  /**
   * Calculates the fixed date of the Persian New Year (Nowruz) in a given Gregorian year.
   *
   * @param gYear The Gregorian year.
   * @return The fixed date number of Nowruz in the given Gregorian year.
   */
  public static long nowruz(int gYear) {
    int persianYear = gYear - gregorianYearFromFixed(PERSIAN_EPOCH) + 1;
    int y = persianYear <= 0 ? persianYear - 1 : persianYear;  // No Persian year 0
    return fixedFromPersian(new int[] {y, 1, 1});
  }

  /**
   * Checks if a given Persian year is a leap year.
   *
   * @param pYear The Persian year to check.
   * @return {@code true} if the year is a leap year, {@code false} otherwise.
   */
  public static boolean persianLeapYear(int pYear) {
    long thisNowruz = fixedFromPersian(new int[] {pYear, 1, 1});
    long nextNowruz = fixedFromPersian(new int[] {pYear + 1, 1, 1});
    return nextNowruz - thisNowruz == 366;
  }
}
