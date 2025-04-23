# Persian Calendar

This project provides a Java implementation of the Persian Calendar (also known as the Iranian or Jalali Calendar) based on the calendar framework of ICU4J (IBM's International Components for Unicode for Java).

Previous versions of the Persian Calendar that relied on an arithmetic algorithm were found to produce incorrect results in certain years. A notable instance was the failure to correctly recognize the year 1403 as a leap year. This highlighted the necessity for an upgrade to a more accurate astronomical implementation.

This version (3.0) is an astronomical implementation built upon the calendrical calculations of the CALENDRICA package. This package was distributed on the CD-ROM accompanying the book *Calendrical Calculations, the Millennium Edition* (2nd ed., 2001) by Edward M. Reingold and Nachum Dershowitz.

The Java code for calendrical calculations is a conversion of the Python code from Roozbeh Pournader's [persiancalendar project](https://github.com/roozbehp/persiancalendar), which itself is based on the Common Lisp code CALENDRICA 4.0 by E. M. Reingold and N. Dershowitz.

The authors of the CALENDRICA package kindly provided me with the jar file `Calendrica.jar` and gave me permission to use it in this open-source project. However, their computation of the Persian New Year is based on hard-coded coordinates of Tehran, which is not compatible with the official meridian as [reported](https://x.com/roozbehp/status/1902199646006689876) by Roozbeh Pournader. For this reason, a new Java conversion of their code was used.

Since ICU4J already includes a `PersianCalendar` class, there seems to be no real reason to continue this project separately, and indeed, it has been unmaintained for almost 20 years. However, some old projects still using this project require an update due to the failure of the arithmetic algorithm for 1403. Hence I decided to prepare a quick release. If you find any bugs in the code, please let me know.

It should be noted that both `PersianCalendar` classes in this project and in ICU4J use the type identifier `"persian"` which might create problems for resource loading or for other aspects of their operation. I haven't yet investigated this possibility, but there seems to be a problem with `DateFormat.FULL` date formatting (but not for other styles or when providing a pattern).

## Usage

This library is planned for release on Maven Central. Until then, you will need to build and install it into your local Maven repository (`~/.m2/repository`) to use it in other projects.

**1. Build and Install to Your Local Repository:**

Clone the repository and install it using Maven:

```bash
git clone https://github.com/ghasemkiani/persiancalendar.git
cd persiancalendar
mvn clean install
```

This command will build the library and place the `persiancalendar-3.0.jar` (and associated files) into your local Maven cache.

**2. Add Dependency to Your Project:**

Once the library is installed in your local repository, you can add the following dependency snippet to the `pom.xml` file of your own Maven project:

```xml
  <dependency>
    <groupId>com.ghasemkiani</groupId>
    <artifactId>persiancalendar</artifactId>
    <version>3.0</version>
  </dependency>
```

Your project will then be able to find and use the library from your local cache.

(**Note:** When version 3.0 is officially released to Maven Central, step 1 will no longer be necessary for most users, and they can simply add the dependency above.)

**3. Example Usage:**

Here's a basic example demonstrating how to use the `PersianCalendar` class to format the current date after adding the dependency:

```java
import java.util.Date;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.text.DateFormat;
import com.ghasemkiani.util.icu.PersianCalendar;

// ...

PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
DateFormat df = pc.getDateTimeFormat(DateFormat.LONG, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
String result = df.format(new Date());

// ...
```

For more information, see the [ICU4J documentation](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/).

## Acknowledgments

*   The astronomical calculations are based on CALENDRICA 4.0 by E. M. Reingold and N. Dershowitz.
*   Roozbeh Pournader's Persian Calendar project, based on Calendrica. I converted the Python code of this project into Java to be used for calendrical calculations in the current project.
*   IBM's International Components for Unicode for Java (ICU4J).


## References

* Reingold, Edward M., and Nachum Dershowitz. *Calendrical Calculations*, 4th Edition. Cambridge University Press, 2018 (ISBN [9781107057623](https://www.cambridge.org/nl/universitypress/subjects/computer-science/computing-general-interest/calendrical-calculations-ultimate-edition-4th-edition?format=HB&isbn=9781107057623)).
* Original Common Lisp Code (CALENDRICA 4.0): Written by Edward M. Reingold and Nachum Dershowitz. Available at [https://github.com/EdReingold/calendar-code2](https://github.com/EdReingold/calendar-code2).
* Python Port: Copyright 2024 Roozbeh Pournader. Available at [https://github.com/roozbehp/persiancalendar](https://github.com/roozbehp/persiancalendar).

## License

* The original Common Lisp code and the Python port are licensed under the Apache License, Version 2.0 (see the file `LICENSE-calendrica`).
* This project utilizes ICU4J (version 3.2), which is copyright International Business Machines Corporation (IBM). The license information for ICU4J can be found in the file `LICENSE-icu4j` included with this software package.
* This project itself is licensed under the GNU General Public License, version 3 (see the file `LICENSE`).
