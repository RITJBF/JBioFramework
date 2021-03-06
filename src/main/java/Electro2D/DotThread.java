package main.java.Electro2D;

/**
 * Electro2D.DotThread is the thread controlling the second half of the 2DGE animation.
 *
 * @author Jill Zapoticznyj
 * @author Adam Bazinet
 */

public class DotThread extends Thread {
    private static boolean go = false;
    private GelCanvas gel;
    private Electro2D electro2D;

    /**
     * Constructor
     *
     * @param e reference to the Electro2D
     */

    public DotThread(Electro2D e) {
        gel = e.getGel();
        electro2D = e;
    }

    /**
     * This method called when the play button is pressed to pause
     * the animation or when the stop button is pressed.  Sets the
     * boolean variable in charge of the animation loop to false,
     * stopping the animation.
     */

    public void stopDots() {
        go = false;
    }

    /**
     * This method returns the value of go - the variable
     * which determines whether or not to edit the location of the protein
     * dots.
     */

    public static boolean getDotState() {
        return go;
    }

    /**
     * This method is called just before the animation is started or
     * when the animation is restarted by pushing the play button.
     */

    public void startDots() {
        go = true;
    }


    /**
     * The run method used by the animation thread.  Makes repeated
     * calls to Electro2D.GelCanvas' genDots method while go is true.
     */

    public void run() {
        GelCanvas.stopBlink();
        //send the percent acrylamide value to Electro2D.ProteinDot
        ProteinDot.setPercent(electro2D.getLowPercent(),
                electro2D.getHighPercent());

        gel.resetReLine();
        // while the IEFProteins are still visible...
        while (IEFProtein.returnHeight() > 0) {
            //...shrink them in size...
            IEFProtein.shrinkProtein();
            // ...and redraw them to the Electro2D.GelCanvas
            gel.shrinkIEF();

            // then wait for 100 milliseconds
            try {
                sleep((long) 100);
            } catch (Exception exc) {
                System.err.println("Exception was: " + exc.getMessage());
            }
        }

        // Make the ProteinDots visible
        if (ProteinDot.getShow() == false) {
            ProteinDot.setShow();
        }

        int i = 0;
        long tm = System.currentTimeMillis();
        long newTm = 0;
        // While the stop button or pause button has not been pressed...
        while (go == true) {
            // ...change the location of the dots and redraw them to
            // the Electro2D.GelCanvas
            gel.genDots();
            newTm = System.currentTimeMillis();
            if (newTm - tm >= 10000) {
                stopDots();
            }
            // Then wait for 100 milliseconds
            try {
                sleep((long) 100);
            } catch (Exception e) {
                System.err.println("Exception was: " + e);
            }
            i = 1 + i;
        }
        try {
            sleep((long) 100);
        } catch (Exception e) {
            System.err.println("Exception was: " + e);
        }
        gel.setreLine();
        gel.setMWLines(i);
        i = 0;
        electro2D.resetPlay();
        gel.paint(gel.getGraphic());
        gel.repaint();
    }
}