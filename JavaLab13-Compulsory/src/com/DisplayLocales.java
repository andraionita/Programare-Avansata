package com;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;
/**
 * @author Ionita Andra grupa 2A7
 * Laborator 13 Compulsory
 * Clasa care afiseaza infromatii despre locatiile valabile
 */
public class DisplayLocales {
public String lan;

    public static void getLocales() {

        System.out.println("Available locales:");
        Locale available[] =
                Locale.getAvailableLocales();
        for(Locale locale : available) {
           System.out.println("Country:  " +locale.getDisplayCountry() + "  " + "----> Language:  "+
                    locale.getDisplayLanguage(locale));
        }
    }


    public static void getDefaultLocale(String lan){
        if(!lan.toLowerCase().equals("ro"))
          System.out.println(Locale.getDefault());
        else System.out.println("ro");
    }

}
