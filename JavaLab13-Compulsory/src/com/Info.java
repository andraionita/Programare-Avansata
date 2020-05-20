package com;

import java.text.DateFormatSymbols;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.*;

import static com.sun.tools.javac.util.Constants.format;
/**
 * @author Ionita Andra grupa 2A7
 * Laborator 13 Compulsory
 * Clasa care afiseaza informatii dupa u tag specific, informatii despre tara, limba, moneda, zilele saptamanii, lunile anuui si data actuala.
 */
public class Info {

    public static void displayInfo(Locale locale) {

        List<String> weekdays=new ArrayList<String>();

        Locale roLocale = locale;
        System.out.println(roLocale.getDisplayCountry());
        System.out.println(roLocale.getDisplayLanguage());

        Currency currency = Currency.getInstance(locale);
        System.out.println(currency.getDisplayName());


        java.util.Currency usd = java.util.Currency.getInstance(locale);
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(locale);
        format.setCurrency(usd);
        System.out.println(format.format(23));


        WeekFields wf = WeekFields.of(locale);
        DayOfWeek day = wf.getFirstDayOfWeek();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            weekdays.add(day.getDisplayName(TextStyle.FULL, locale));
            day = day.plus(1);
        }
        System.out.println(weekdays);


        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] months = dfs.getMonths();
        System.out.println(Arrays.toString(months));




        DateFormat full = DateFormat.getDateInstance(DateFormat.LONG, locale);
        System.out.println(full.format(new Date()));

        System.out.println("\n");


    }
}
