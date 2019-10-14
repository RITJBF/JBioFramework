package main.java.MassSpec;// This class translates the given sequence letter names of the protein into the full names so it can be used in Marvin Sketch

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;


public class AminoAcidTranslator {

    static public String translate(String inpt) {
        String outpt = "";
        /**
         * creates a switch case in which it looks at the output and than
         * will output the names of the amino acids depending on which one letter
         * code they fulfill. To account for marvin, if the acid is not the last in
         * the sequence, it will change to have a -yl at the end
         */
        for (int i = 0; i < inpt.length(); i++) {
            char c = inpt.charAt(i);
            switch (c) {

                case 'A':
                    if (i == inpt.length() - 1) {
                        outpt += "Alanine";
                    } else {
                        outpt += "Alanyl-";
                    }
                    break;
                case 'P':
                    if (i == inpt.length() - 1) {
                        outpt += "Proline";
                    } else {
                        outpt += "Prolyl-";
                    }
                    break;
                case 'G':
                    if (i == inpt.length() - 1) {
                        outpt += "Glycine";
                    } else {
                        outpt += "Glycyl-";
                    }
                    break;
                case 'V':
                    if (i == inpt.length() - 1) {
                        outpt += "Valanine";
                    } else {
                        outpt += "Valinyl-";
                    }
                    break;
                case 'L':
                    if (i == inpt.length() - 1) {
                        outpt += "Leucine";
                    } else {
                        outpt += "Leucinyl-";
                    }
                    break;
                case 'I':
                    if (i == inpt.length() - 1) {
                        outpt += "Isoleucine";
                    } else {
                        outpt += "Isoleucinyl-";
                    }
                    break;
                case 'M':
                    if (i == inpt.length() - 1) {
                        outpt += "Methionine";
                    } else {
                        outpt += "Methionyl-";
                    }
                    break;
                case 'F':
                    if (i == inpt.length() - 1) {
                        outpt += "Phenylalanine";
                    } else {
                        outpt += "Phenylalaninyl-";
                    }
                    break;
                case 'W':
                    if (i == inpt.length() - 1) {
                        outpt += "Tryptophan";
                    } else {
                        outpt += "Tryptophanyl-";
                    }
                    break;
                case 'S':
                    if (i == inpt.length() - 1) {
                        outpt += "Serine";
                    } else {
                        outpt += "Serinyl-";
                    }
                    break;
                case 'T':
                    if (i == inpt.length() - 1) {
                        outpt += "Threonine";
                    } else {
                        outpt += "Threonyl-";
                    }
                    break;
                case 'C':
                    if (i == inpt.length() - 1) {
                        outpt += "Cysteine";
                    } else {
                        outpt += "Cysteinyl-";
                    }
                    break;
                case 'Y':
                    if (i == inpt.length() - 1) {
                        outpt += "Tyrosine";
                    } else {
                        outpt += "Tyrosinyl-";
                    }
                    break;
                case 'N':
                    if (i == inpt.length() - 1) {
                        outpt += "Asparagine";
                    } else {
                        outpt += "Asparaginyl-";
                    }
                    break;
                case 'Q':
                    if (i == inpt.length() - 1) {
                        outpt += "Glutamine";
                    } else {
                        outpt += "Glutaminyl-";
                    }
                    break;
                case 'D':
                    if (i == inpt.length() - 1) {
                        outpt += "Aspartic Acid";
                    } else {
                        outpt += "Aspartyl-";
                    }
                    break;
                case 'E':
                    if (i == inpt.length() - 1) {
                        outpt += "Glutamic Acid";
                    } else {
                        outpt += "Glutamyl-";
                    }
                    break;
                case 'K':
                    if (i == inpt.length() - 1) {
                        outpt += "Lysine";
                    } else {
                        outpt += "Lysinyl-";
                    }
                    break;
                case 'R':
                    if (i == inpt.length() - 1) {
                        outpt += "Arginine";
                    } else {
                        outpt += "Arginyl-";
                    }
                    break;
                case 'H':
                    if (i == inpt.length() - 1) {
                        outpt += "Histidine";
                    } else {
                        outpt += "Histidinyl-";
                    }
                    break;
            }
        }
        return outpt;

    }

}

