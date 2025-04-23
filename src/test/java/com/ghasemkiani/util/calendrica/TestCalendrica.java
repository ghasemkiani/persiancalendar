package com.ghasemkiani.util.calendrica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class TestCalendrica {

  @Test
  void testMod3Double() {
    assertEquals(-100.5, Calendrica.mod3(-460.5, -180, 180), 0.000001);
    assertEquals(-100.5, Calendrica.mod3(-460.5, -180, 180));
    assertEquals(259.5, Calendrica.mod3(-460.5, 0, 360));
    assertEquals(-60.5, Calendrica.mod3(-60.5, -180, 180));
    assertEquals(299.5, Calendrica.mod3(-60.5, 0, 360));
    assertEquals(-180, Calendrica.mod3(-180, -180, 180));
    assertEquals(-180, Calendrica.mod3(180, -180, 180));
  }

  @Test
  void testPoly() {
    assertEquals(-2.816, Calendrica.poly(1.3, new double[] {5.01, -6.02}));
    assertEquals(-2.1569000000000003, Calendrica.poly(1.3, new double[] {5.01, -6.02, 0, 0.3}), 1e-9);
  }

  @Test
  void testRd() {
    assertEquals(111L, Calendrica.rd(111));
    assertEquals(-111L, Calendrica.rd(-111));
  }

  @Test
  void testSign() {
    assertEquals(-1, Calendrica.sign(-134.5));
    assertEquals(1, Calendrica.sign(134.5));
    assertEquals(0, Calendrica.sign(0));
  }

  @Test
  void testGregorianLeapYear() {
    assertFalse(Calendrica.gregorianLeapYear(-5));
    assertTrue(Calendrica.gregorianLeapYear(-4));
    assertFalse(Calendrica.gregorianLeapYear(-3));
    assertFalse(Calendrica.gregorianLeapYear(-2));
    assertFalse(Calendrica.gregorianLeapYear(-1));
    assertTrue(Calendrica.gregorianLeapYear(0));
    assertFalse(Calendrica.gregorianLeapYear(1));
    assertFalse(Calendrica.gregorianLeapYear(2));
    assertFalse(Calendrica.gregorianLeapYear(3));
    assertTrue(Calendrica.gregorianLeapYear(4));
    assertFalse(Calendrica.gregorianLeapYear(5));
    assertFalse(Calendrica.gregorianLeapYear(1970));
    assertTrue(Calendrica.gregorianLeapYear(2000));
    assertFalse(Calendrica.gregorianLeapYear(2001));
    assertFalse(Calendrica.gregorianLeapYear(2002));
    assertFalse(Calendrica.gregorianLeapYear(2003));
    assertTrue(Calendrica.gregorianLeapYear(2004));
    assertFalse(Calendrica.gregorianLeapYear(2005));
    assertFalse(Calendrica.gregorianLeapYear(2006));
    assertFalse(Calendrica.gregorianLeapYear(2007));
    assertTrue(Calendrica.gregorianLeapYear(2008));
    assertFalse(Calendrica.gregorianLeapYear(2009));
  }

  @Test
  void testFixedFromGregorian() {
    assertEquals(719421L, Calendrica.fixedFromGregorian(new int[] {1970, 9, 16}));
    assertEquals(-106L, Calendrica.fixedFromGregorian(new int[] {0, 9, 16}));
    assertEquals(-1L, Calendrica.fixedFromGregorian(new int[] {0, 12, 30}));
    assertEquals(0L, Calendrica.fixedFromGregorian(new int[] {0, 12, 31}));
    assertEquals(1L, Calendrica.fixedFromGregorian(new int[] {1, 1, 1}));
  }

  @Test
  void testGregorianYearFromFixed() {
    assertEquals(-2737, Calendrica.gregorianYearFromFixed(-1000000L));
    assertEquals(-273, Calendrica.gregorianYearFromFixed(-100000L));
    assertEquals(-27, Calendrica.gregorianYearFromFixed(-10000L));
    assertEquals(-1, Calendrica.gregorianYearFromFixed(-366L));
    assertEquals(0, Calendrica.gregorianYearFromFixed(-365L));
    assertEquals(0, Calendrica.gregorianYearFromFixed(-1L));
    assertEquals(0, Calendrica.gregorianYearFromFixed(0L));
    assertEquals(1, Calendrica.gregorianYearFromFixed(1L));
    assertEquals(1998, Calendrica.gregorianYearFromFixed(729740L));
    assertEquals(1600, Calendrica.gregorianYearFromFixed(584388L));
    assertEquals(3347, Calendrica.gregorianYearFromFixed(1222333L));
  }

  @Test
  void testGregorianNewYear() {
    assertEquals(-730L, Calendrica.gregorianNewYear(-1));
    assertEquals(-365L, Calendrica.gregorianNewYear(0));
    assertEquals(1L, Calendrica.gregorianNewYear(1));
    assertEquals(722450L, Calendrica.gregorianNewYear(1979));
  }

  @Test
  void testGregorianFromFixed() {
    assertArrayEquals(new int[] {-2, 4, 6}, Calendrica.gregorianFromFixed(-1000L));
    assertArrayEquals(new int[] {0, 12, 30}, Calendrica.gregorianFromFixed(-1L));
    assertArrayEquals(new int[] {0, 12, 31}, Calendrica.gregorianFromFixed(0L));
    assertArrayEquals(new int[] {1, 1, 1}, Calendrica.gregorianFromFixed(1L));
    assertArrayEquals(new int[] {1600, 12, 31}, Calendrica.gregorianFromFixed(584388L));
    assertArrayEquals(new int[] {1970, 9, 16}, Calendrica.gregorianFromFixed(719421L));
    assertArrayEquals(new int[] {1998, 12, 17}, Calendrica.gregorianFromFixed(729740L));
  }

  @Test
  void testGregorianDateDifference() {
    assertEquals(-255488L, Calendrica.gregorianDateDifference(new int[] {1900, 1, 1}, new int[] {1200, 7, 1}));
    assertEquals(1472292L, Calendrica.gregorianDateDifference(new int[] {-2000, 1, 1}, new int[] {2030, 12, 31}));
  }

  @Test
  void testJulianEpoch() {
    assertEquals(-1L, Calendrica.JULIAN_EPOCH);
  }

  @Test
  void testGregorianEpoch() {
    assertEquals(1L, Calendrica.GREGORIAN_EPOCH);
  }

  @Test
  void testJulianLeapYear() {
    assertTrue(Calendrica.julianLeapYear(-1));
    assertFalse(Calendrica.julianLeapYear(0));
    assertFalse(Calendrica.julianLeapYear(1));
    assertFalse(Calendrica.julianLeapYear(2));
    assertFalse(Calendrica.julianLeapYear(3));
    assertTrue(Calendrica.julianLeapYear(4));
    assertFalse(Calendrica.julianLeapYear(5));
    assertFalse(Calendrica.julianLeapYear(6));
    assertFalse(Calendrica.julianLeapYear(7));
    assertTrue(Calendrica.julianLeapYear(8));
  }

  @Test
  void testFixedFromJulian() {
    assertEquals(-367L, Calendrica.fixedFromJulian(-1, 1, 1));
    assertEquals(-2L, Calendrica.fixedFromJulian(-1, 12, 31));
    assertEquals(-367L, Calendrica.fixedFromJulian(0, 1, 1));
    assertEquals(-1L, Calendrica.fixedFromJulian(1, 1, 1));
    assertEquals(363L, Calendrica.fixedFromJulian(1, 12, 31));
    assertEquals(719447L, Calendrica.fixedFromJulian(1970, 9, 29));
  }

  @Test
  void testHr() {
    assertEquals(1.0/24.0, Calendrica.hr(1), 0.000001);
    assertEquals(0.5, Calendrica.hr(12), 0.000001);
  }

  @Test
  void testAngle() {
    assertEquals(23.4392911111111, Calendrica.angle(23, 26, 21.448), 0.000000001);
  }

  @Test
  void testRadiansFromDegrees() {
    assertEquals(11 * Math.PI / 6, Calendrica.radiansFromDegrees(-30), 0.000001);
    assertEquals(0.0, Calendrica.radiansFromDegrees(0), 0.000001);
    assertEquals(Math.PI / 6, Calendrica.radiansFromDegrees(30), 0.000001);
    assertEquals(Math.PI / 2, Calendrica.radiansFromDegrees(90), 0.000001);
    assertEquals(Math.PI, Calendrica.radiansFromDegrees(180), 0.000001);
    assertEquals(3 * Math.PI / 2, Calendrica.radiansFromDegrees(270), 0.000001);
  }

  @Test
  void testSinDegrees() {
    assertEquals(-0.5, Calendrica.sinDegrees(-30), 0.000001);
    assertEquals(0.0, Calendrica.sinDegrees(0), 0.000001);
    assertEquals(0.5, Calendrica.sinDegrees(30), 0.000001);
    assertEquals(1.0, Calendrica.sinDegrees(90), 0.000001);
    assertEquals(0.0, Calendrica.sinDegrees(180), 0.000001);
    assertEquals(-1.0, Calendrica.sinDegrees(270), 0.000001);
  }

  @Test
  void testCosDegrees() {
    assertEquals(Math.sqrt(3) / 2, Calendrica.cosDegrees(-30), 0.000001);
    assertEquals(1.0, Calendrica.cosDegrees(0), 0.000001);
    assertEquals(Math.sqrt(3) / 2, Calendrica.cosDegrees(30), 0.000001);
    assertEquals(0.0, Calendrica.cosDegrees(90), 0.000001);
    assertEquals(-1.0, Calendrica.cosDegrees(180), 0.000001);
    assertEquals(0.0, Calendrica.cosDegrees(270), 0.000001);
  }

  @Test
  void testTanDegrees() {
    assertEquals(-Math.sqrt(3) / 3, Calendrica.tanDegrees(-30), 0.000001);
    assertEquals(0.0, Calendrica.tanDegrees(0), 0.000001);
    assertEquals(Math.sqrt(3) / 3, Calendrica.tanDegrees(30), 0.000001);
    assertEquals(Math.sqrt(3), Calendrica.tanDegrees(60), 0.000001);
    assertEquals(-Math.sqrt(3), Calendrica.tanDegrees(120), 0.000001);
  }

  @Test
  void testLongitude() {
    assertEquals(51.42, Calendrica.longitude(Calendrica.TEHRAN), 0.000001);
  }

  @Test
  void testZoneFromLongitude() {
    assertEquals(0.14283333333333334, Calendrica.zoneFromLongitude(Calendrica.TEHRAN[1]), 0.000001);
  }

  @Test
  void testUniversalFromLocal() {
    assertEquals(11.857166666666666, Calendrica.universalFromLocal(12, Calendrica.TEHRAN), 0.000001);
  }

  @Test
  void testEquationOfTime() {
    assertEquals(0.0030944328956880427, Calendrica.equationOfTime(-1075000.75), 0.000001);
    assertEquals(-0.005469845222877644, Calendrica.equationOfTime(0), 0.000001);
    assertEquals(-0.008939409489765494, Calendrica.equationOfTime(12), 0.000001);
    assertEquals(-0.002160674787077407, Calendrica.equationOfTime(1075000.75), 0.000001);
  }

  @Test
  void testEphemerisCorrection() {
    assertEquals(0.5167202222222221, Calendrica.ephemerisCorrection(-700000.5), 0.000001);
    assertEquals(0.12249537037037038, Calendrica.ephemerisCorrection(0), 0.000001);
    assertEquals(0.11923706103520947, Calendrica.ephemerisCorrection(10001.5), 0.000001);
    assertEquals(0.09001744444444443, Calendrica.ephemerisCorrection(1234567.89), 0.000001);
  }

  @Test
  void testConstants() {
    assertEquals(365.242189, Calendrica.MEAN_TROPICAL_YEAR, 0.000001);
    assertEquals(730120.5, Calendrica.J2000, 0.000001);
    assertEquals(0, Calendrica.SPRING, 0.000001);
    assertEquals(226896, Calendrica.PERSIAN_EPOCH, 0.000001);
  }

  @Test
  void testJulianCenturies() {
    assertEquals(13.412175508597356, Calendrica.julianCenturies(1220000.125), 0.000001);
  }

  @Test
  void testObliquity() {
    assertEquals(23.266062510790697, Calendrica.obliquity(1220000.125), 0.000001);
  }

  @Test
  void testDynamicalFromUniversal() {
    assertEquals(12.122378000837466, Calendrica.dynamicalFromUniversal(12), 0.000001);
  }

  @Test
  void testLocalFromApparent() {
    assertEquals(12.008904079634519, Calendrica.localFromApparent(12, Calendrica.TEHRAN), 0.000001);
  }

  @Test
  void testUniversalFromApparent() {
    assertEquals(11.866070746301185, Calendrica.universalFromApparent(12, Calendrica.TEHRAN), 0.000001);
  }

  @Test
  void testMidday() {
    assertEquals(1234568.2549291698, Calendrica.midday(1234567.89, Calendrica.TEHRAN), 0.000001);
  }

  @Test
  void testSolarLongitude() {
    assertEquals(328.5206944748643, Calendrica.solarLongitude(1234567.89), 0.000001);
  }

  @Test
  void testNutation() {
    assertEquals(-0.0034824203728600176, Calendrica.nutation(1234567.89), 0.000001);
  }

  @Test
  void testAberration() {
    assertEquals(-0.005666070665654096, Calendrica.aberration(1234567.89), 0.000001);
  }

  @Test
  void testEstimatePriorSolarLongitude() {
    assertEquals(1234233.0621276246, Calendrica.estimatePriorSolarLongitude(-0.715, 1234567.89), 0.000001);
  }

  @Test
  void testMiddayInPersianLocale() {
    assertEquals(-999.6448755018984, Calendrica.middayInPersianLocale(-1000), 0.000001);
    assertEquals(-0.6405737999741052, Calendrica.middayInPersianLocale(-1), 0.000001);
    assertEquals(0.35975105010920616, Calendrica.middayInPersianLocale(0), 0.000001);
    assertEquals(1234568.2519292708, Calendrica.middayInPersianLocale(1234567.89), 0.000001);
  }

  @Test
  void testPersianNewYearOnOrBefore() {
    assertEquals(-1016, Calendrica.persianNewYearOnOrBefore(-1000), 0.000001);
    assertEquals(-285, Calendrica.persianNewYearOnOrBefore(-1), 0.000001);
    assertEquals(-285, Calendrica.persianNewYearOnOrBefore(0), 0.000001);
    assertEquals(1234234, Calendrica.persianNewYearOnOrBefore(1234567), 0.000001);
  }

  @Test
  void testFixedFromPersian() {
    assertEquals(-1000, Calendrica.fixedFromPersian(new int[] {-624, 1, 17}));
    assertEquals(-1, Calendrica.fixedFromPersian(new int[] {-622, 10, 9}));
    assertEquals(0, Calendrica.fixedFromPersian(new int[] {-622, 10, 10}));
    assertEquals(725432, Calendrica.fixedFromPersian(new int[] {1365, 12, 11}));
  }

  @Test
  void testPersianFromFixed() {
    assertArrayEquals(new int[] {-624, 1, 17}, Calendrica.persianFromFixed(-1000));
    assertArrayEquals(new int[] {-622, 10, 9}, Calendrica.persianFromFixed(-1));
    assertArrayEquals(new int[] {-622, 10, 10}, Calendrica.persianFromFixed(0));
    assertArrayEquals(new int[] {1365, 12, 11}, Calendrica.persianFromFixed(725432));
  }

  @Test
  void testNowruz() {
    assertEquals(-365527, Calendrica.nowruz(-1000));
    assertEquals(-650, Calendrica.nowruz(-1));
    assertEquals(-285, Calendrica.nowruz(0));
    assertEquals(80, Calendrica.nowruz(1));
    assertEquals(502288, Calendrica.nowruz(1376));
    assertEquals(512515, Calendrica.nowruz(1404));
    assertEquals(536621, Calendrica.nowruz(1470));
  }

  @Test
  void testPersianLeapYear() {
    assertTrue(Calendrica.persianLeapYear(-5));
    assertFalse(Calendrica.persianLeapYear(-4));
    assertFalse(Calendrica.persianLeapYear(-3));
    assertFalse(Calendrica.persianLeapYear(-2));
    assertTrue(Calendrica.persianLeapYear(-1));
    assertFalse(Calendrica.persianLeapYear(0));
    assertFalse(Calendrica.persianLeapYear(1));
    assertTrue(Calendrica.persianLeapYear(5));
    assertFalse(Calendrica.persianLeapYear(1374));
    assertTrue(Calendrica.persianLeapYear(1375));
    assertTrue(Calendrica.persianLeapYear(1403));
    assertFalse(Calendrica.persianLeapYear(1404));
    assertTrue(Calendrica.persianLeapYear(1469));
    assertFalse(Calendrica.persianLeapYear(1470));
  }
}
