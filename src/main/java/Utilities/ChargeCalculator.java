package main.java.Utilities;

import main.java.Electro2D.Electro2D;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Scanner;
import javafx.application.Application;


public class ChargeCalculator {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("pH = ");
        double pH = in.nextDouble();
        System.out.print("sequence = ");
        String sequence = in.next();


        double lowpH = 0, highpH = 14;

        int pLength = sequence.length();

        double charge = 0;

        // n means that the amino acid is neutral
        char type = 'n';

        double pK = 0;




        for (int a = 0; a < pLength; a++) {
            switch (sequence.charAt(a)) {
                case 'R':
                    type = 'b';
                    pK = 12;
                    break;
                case 'D':
                    type = 'a';
                    pK = 4.05;
                    break;
                case 'C':
                    type = 'a';
                    pK = 9;
                    break;
                case 'E':
                    type = 'a';
                    pK = 4.75;
                    break;//pK = 4.45; break;
                case 'H':
                    type = 'b';
                    pK = 5.98;
                    break;
                case 'K':
                    type = 'b';
                    pK = 10;
                    break;
                case 'Y':
                    type = 'a';
                    pK = 10;
                    break;
                default:
                    type = 'n';
                    pK = 0;
                    break;
            }
            // calculates the charge for acids
            if (type == 'a') {
                charge += -1 /
                        (1 + Math.pow(10, pK - pH));
            }
            // calculates the charge for the bases
            if (type == 'b') {
                charge += 1 / (1 + Math.pow(10, pH - pK));
            }
        }
        // this calculates the charge at the C terminus and adds it to the total charge
        charge += -1 /
                (1 + Math.pow(10, 3.2 - pH));

        // this calculates the charge at the N terminus and adds it to the total charge
        charge += 1 / (1 + Math.pow(10, pH -/*9.53*/8.2));
        System.out.println("Final charge: " + charge);
    }
}