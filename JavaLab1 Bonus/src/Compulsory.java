/**
 * @author Ionita Andra Paula grupa 2A7
 * Laboratorul 1 Bonus
 * In clasa optional s-a implementat partea obligatorie  a laboratorului 1 -> deja prezentata la laborator
 */

import java.util.*;
import java.util.Scanner;
public class Compulsory {
    public static void main (String[] args)
    {
        System.out.println("Hello world!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        System.out.println("n= " + n);
        n=(n*3+Integer.parseInt("10101",2)+Integer.parseInt("FF",16))*6;
        System.out.println("n= " + n);
        int sum=0;
        while(n>9) {
            sum=0;
            while (n != 0) {
                sum = sum + n % 10;
                n = n / 10;
            }
            n=sum;
        }
        System.out.println("Suma cifrelor este " + sum);
        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);
    }
}
