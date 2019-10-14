package main.java.MassSpec;
/**
 * Copyright (C) 2013 Rochester Institute of Technology
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 */

/**
 * This class extends ArrayList to function as a 'holder' for AminoAcids.
 * MassSpec.Ion knows the total mass and charge of itself, based on the AminoAcids
 * it contains, and calculates and sets its own mass/charge ratio. MassSpec.Ion also
 * knows the color it should be when displayed on a graph (black for initial
 * fragmentation, blue for b fragments and red for y fragments in sequencing.
 * The variable hits is set by MassSpec.Spectrometer as it counts how many of each type
 * of ion it has. Hits is used to determine the ion's intensity on the
 * MassSpec.OutputGraphGUI and MassSpec.TandemGraphGUI. MassSpec.Ion also knows its position on the
 * MassSpec.OutputGraphGUI and MassSpec.TandemGraphGUI so the user can click on individual peaks.
 * 
 */

/**
 *
 * @author Amanda Fisher
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.io.*;

import javax.swing.*;
import main.java.MainWindows.MarvinTab;
import main.java.MainWindows.JBioFrameworkMain;

public class Ion extends ArrayList<SpecAminoAcid> {

    private double totalMass = 0;
    private int totalCharge = 0;
    private double hits = 0;
    private int xCoordinate = 0;
    private String sequence;
    Color color = Color.BLACK;
    private String reverseSequence;

    /**
     * Adds the MassSpec.SpecAminoAcid to the end of the MassSpec.Ion. MassSpec.Ion adds its mass to
     * the appropriate instance variable. Charge of an MassSpec.Ion will always be one in
     * peptide sequencing.
     *
     * @param a MassSpec.SpecAminoAcid to be added to the end of the MassSpec.Ion.
     * @return true if successfully added AmmionAcid.
     */
    @Override
    public boolean add(SpecAminoAcid a) {
        super.add(a);
        // add the mass of the amino acid to the ion
        setMass(totalMass + a.getMass());
        // if this is the second or greater amino acid added to the chain;
        if(this.size() > 1) {
            // subtract the mass in Daltons of H2O as the amino acids dehydrate
            // together to form the chain
            setMass(totalMass - 18.01528);
        }
        return true;
    }
    /**
     *  This is used to get the pop-up window to display the
     *  amino acid sequence that is translated from the Amino Acid Translator 
     *  after it is sent from the ion setters and getters
     * 
     */
    
    public void displaySequence() {
    	// this is the code that gives the pop up box for the chemical structure to put in Marvin Sketch
    	// there is a text area inside of a panel and frame that allows the size to be changed.
	    
    	final JFrame frame = new JFrame("Name for MarvinSketch");
    	JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    	JTextArea proteinSet = new JTextArea("",150,300);
		proteinSet.append(
	       "Instructions: " +
	       "\n" +
	       "\n To display the structure properly, right click and select Ungroup.\n \n" +
	       "Note: The longer the sequence the more likely it is to display diagonally,"+
	       "\n Select everything. Click on Structure and select Clean 2D. \n"
	       
	    );
        panel.add(proteinSet, BorderLayout.CENTER);

        // Add a button that will load the protein in Marvin Sketch
        JButton marvinButton = new JButton("Show in MarvinSketch");
        marvinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MarvinTab.getSketchPane().setMol(sequence);
                JBioFrameworkMain.getTabs().setSelectedIndex(4);
                frame.dispose();
            }
        });
        panel.add(marvinButton, BorderLayout.SOUTH);

		frame.add(panel);
		frame.setSize(480,175);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
   
    	
    private void setSize(int i, int j) { }
	private Font newFont(String string, int i) { return null; }
	private Dimension newDimension(int i, int j) { return null;	}

	/**
     * Used by MassSpec.Ion in add method to keep a running total of the mass, and by
     * MassSpec.TandemGraphGUI to adjust the molecular weight of an ion after
     * sequencing fragmentation (single H gets taken away/put on depending on
     * if it is a b or y fragment).
     *
     * @param mass New totalMass.
     */
    public void setMass(double mass) {

        totalMass = mass;
    }
    
    // This will set the sequence given from the ion fragment in the bottom peaks of the graph
    public void setSequence(String sequence) {

        this.sequence = sequence;
	    
	this.reverseSequence = new StringBuilder(sequence).reverse().toString();
    }

    // Gets the sequence to be used in the Translator
    public String getSequence() {

        return sequence;
    }
    // Gets the reverseSequence to be used in the Translator
    public String getReverseSequence() {

        return reverseSequence;
    }

    /**
     * Used only by MassSpec.Spectrometer in RunAnalysis to set the charge of the ion to
     * one. Look for a better way to ensure that all ions only have a charge of one.
     *
     * @param charge New totalCharge.
     */
    public void setCharge(int charge) {

        totalCharge = charge;
    }

    /**
     * Used by MassSpec.Spectrometer to count how many ions share a specific mass charge
     * ratio.
     *
     * @param i New hits count.
     */
    public void setHits(double i) {

        hits = i;
    }

    /**
     * Used by MassSpec.OutputGraphGUI to let the MassSpec.Ion know where it is on the graph.
     *
     * @param x the x-coordinate
     */
    public void setXCoordinate(int x) {

        xCoordinate = x;
    }

    /**
     * Used to return the MassSpec.Ion's mass.
     * @return totalMass.
     */
    public double getMass() {

        return totalMass;
    }

    /**
     * Used to return the MassSpec.Ion's charge. Should always return one; think about
     * removing this function.
     *
     * @return totalCharge.
     */
    public int getCharge() {

        return totalCharge;
    }

    /**
     * Used to return the MassSpec.Ion's mass charge ratio. Should always just be mass
     * over one. Think about removing this method.
     *
     * @return massChargeRatio.
     */
    public double getMassChargeRatio() {

        return totalMass/(double)totalCharge;
    }

    /**
     * Used to return the MassSpec.Ion's hits count.
     *
     * @return hits.
     */
    public double getHits() {

        return hits;
    }

    /**
     * Used by MassSpec.Spectrometer to determine if a user clicked on this MassSpec.Ion or not.
     *
     * @return xCoordinate.
     */
    public int getXCoordinate() {

        return xCoordinate;
    }

    /**
     * Used by MassSpec.TandemGraphGUI to set b fragments to blue and y fragments to red.
     *
     * @param c desired color for the MassSpec.Ion's peak on a graph
     */
    public void setColor(Color c) {

        color = c;
    }

    /**
     * Used by MassSpec.TandemGraphGUI to draw the peaks on its graph with the
     * appropriate color coding for b and y fragments.
     *
     * @return color this MassSpec.Ion's peak should be on a graph
     */
    public Color getColor() {

        return color;
    }
}
