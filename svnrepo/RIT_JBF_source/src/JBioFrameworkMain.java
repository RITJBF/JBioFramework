/*
 * The main frame for the JBioFramework program
 * Adds Electro2D, Electrophoresis, and Ionex applications to itself
 * with a JTabbedPane.
 */

/**
 *
 * @author Amanda Fisher
 */
import javax.swing.*;
import java.awt.*;

public class JBioFrameworkMain extends JFrame {

    public static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private Electro2D electro2D;
    private MainPanelGUI spectrometer;
    private Welcome welcome;
    /*private [NameOfClass] [user-created reference]*/

    public static void main(String[] args) {
        JBioFrameworkMain jbfMain = new JBioFrameworkMain();
    }

    public JBioFrameworkMain() {
        super("JBioFramework");

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        electro2D = new Electro2D();
        spectrometer = new MainPanelGUI();
        welcome = new Welcome();
        /*[user-created reference] = new [name of class]()*/

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Welcome", welcome);
        tabbedPane.addTab("Electro2D", electro2D);
        tabbedPane.addTab("Mass Spectrometer", spectrometer);
        /*tabbedPane.addTab(["user-created name (to be displayed)], [user-created reference]);*/

        add(tabbedPane);

        /**
         * Use a toolit to find the screen size of the user's monitor and set
         * the window size to it.
         */

        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        this.pack();
    }
}