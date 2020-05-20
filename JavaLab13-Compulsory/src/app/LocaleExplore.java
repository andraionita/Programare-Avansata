package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.util.Locale;
import java.util.Set;
import java.util.Scanner;


/**
 * @author Ionita Andra grupa 2A7
 * Laborator 13 Compulsory
 * Clasa LocaleExplore, zona unde citim comenzi de la tastatura si le executam.
 */
public class LocaleExplore {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        SetLocale.setLocale(Locale.ENGLISH);
        boolean ok = true;


        System.out.println("Choose command: prompt or exit");
        String command = keyboard.nextLine();
        System.out.println(command);
        if (command.equals("prompt")) {
            System.out.println("Language: EN or RO ?");
            String lang = keyboard.nextLine();

            if (lang.toUpperCase().equals("EN")) {
                while (ok == true) {
                    System.out.println("Choose between locales or locale.set or info");
                    String choose = keyboard.nextLine();

                    if (choose.equals("locales")) {
                        System.out.println("The available locales are:");
                        DisplayLocales.getLocales();
                    } else if (choose.equals("locale.set")) {
                        System.out.println("The current locale is:");
                        DisplayLocales.getDefaultLocale("en");
                    } else if (choose.equals("info")) {
                        System.out.println("Choose a country tag (example ro, fr etc)");
                        String tag = keyboard.nextLine();
                        SetLocale.setLocale(Locale.ENGLISH);
                        Locale roLocale = new Locale(tag.toLowerCase(), tag.toUpperCase());
                        System.out.println("Information:");
                        Info.displayInfo(roLocale);

                    } else ok = false;
                }
            } else if (lang.toUpperCase().equals("RO")) {
                while (ok == true) {
                    System.out.println("Alege una dintre comenzile locales sau locale.set sau info");
                    String choose = keyboard.nextLine();

                    if (choose.equals("locales")) {
                        System.out.println("Localizarile disponibile sunt:");
                        DisplayLocales.getLocales();
                    } else if (choose.equals("locale.set")) {
                        System.out.println("Localizarea curenta este:");

                        DisplayLocales.getDefaultLocale("ro");
                    } else if (choose.equals("info")) {
                        System.out.println("Choose a country tag (example ro, fr etc)");
                        String tag = keyboard.nextLine();

                        Locale roLocale = new Locale(tag.toLowerCase(), tag.toUpperCase());
                        SetLocale.setLocale(roLocale);
                        System.out.println("Informatii despre localizare sunt");
                        Info.displayInfo(roLocale);

                    } else ok = false;
                }

            } else {
                System.out.println("Wrong input. Try again");
            }
        } else {
            System.exit(0);
        }


    }
}
