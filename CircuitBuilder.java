package circuitbuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;

/**
 * The main class, builds and launches the GUI.
 *
 * @author elliot
 */
final class CircuitBuilder {
    /**
     * The main method, launches the GUI.
     *
     * @param args the input arguments (unused)
     */
    public static void main(String[] args) {
        //tries to set the LnF to Nimbus
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error Setting the LnF");
        } catch (InstantiationException e) {
            System.err.println("Error Setting the LnF");
        } catch (IllegalAccessException e) {
            System.err.println("Error Setting the LnF");
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error Setting the LnF");

        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CircuitBuilder().mainFrame.setVisible(true);
            }
        });
    }

    /**
     * Initializes components.
     */
    private CircuitBuilder() {
        initComponents();
        initListeners();
    }

    /**
     * Initializes all the anonymous inner class listeners.
     */
    private void initListeners() {
          /*
        Removes all components when button pressed.
         */
        button.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                canvas.removeAllComponents();
            }
        });

        /*
        Shows the instructions.
         */
        helpItem.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent actionEvent) {
                helpFrame.setVisible(true);
            }
        });

        /*
        Turns on the highlights.
         */
        ioHighlightItem.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LogicGateIO.switchHighlight();
                ioHighlightItem.setText(LogicGateIO.isHighlight() ? "Hide Input/Output Highlights" : "Show Input/Output Highlights");
                canvas.repaint();
            }
        });

        /*
         Shows the credits.
         */
        aboutItem.addActionListener(new ActionListener() {
            //  @Override
            public void actionPerformed(ActionEvent actionEvent) {
                aboutFrame.setVisible(true);
            }
        });

        /*
         Shows borders around the gate.
         */
        gateBorderItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogicGate.switchBorders();
                gateBorderItem.setText(LogicGate.isBorders() ? "Hide Gate Borders" : "Show Gate Borders");
                canvas.repaint();
            }
        });

        /*
        Locks the gates in their current positions.
         */
        lockGates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogicGate.switchLocked();
                lockGates.setText(LogicGate.isLocked() ? "Unlock Gate Positions" : "Lock Gate Positions");
            }
        });

    }

    /**
     * Initializes the needed Swing components.
     */
    private void initComponents() {
        canvas = new DrawingPanel();
        /*
         * The button panel, buttons for each of the gates.
         */
        ButtonsPanel buttons = new ButtonsPanel();

        /*
         * The pane which loads the html for the instructions
         */
        JEditorPane helpText = new JEditorPane();

        /*
         * The pane which loads the html for the
         */
        JEditorPane aboutText = new JEditorPane();

        /*
         * The main menu bar used in the program.
         */
        JMenuBar menuBar = new JMenuBar();
        helpItem = new JMenuItem("Instructions");
        ioHighlightItem = new JMenuItem("Show Input/Output Highlights");
        gateBorderItem = new JMenuItem("Show Gate Borders");
        lockGates = new JMenuItem("Lock Gate Positions");

        /*
         * The menu for the options.
         */
        JMenu optionsMenu = new JMenu("Options");

        /*
         * The menu for the help options.
         */
        JMenu helpMenu = new JMenu("Help  ");
        aboutItem = new JMenuItem("About");
        aboutFrame = new JFrame("Circuit Builder - About");
        helpFrame = new JFrame("Circuit Builder - Instructions");

        /*
         * Shows instruction to the user.
         */
        JLabel contents =
                new JLabel
                        ("   Select a gate:                         Click to place gate:");

        /*
         * The panel hosting the button panel and the canvas.
         */
        JPanel mainPanel = new JPanel();

        button = new JButton();

        /*
         * A JLabel which holds the program's logo.
         */
        JLabel creditsIcon = new JLabel();

        /*
         * The panel which hosts the credits and the logo.
         */
        JPanel creditsPanel = new JPanel();

        /*
         * The panel hosting the buttons.
         */
        JPanel buttonPanel = new JPanel();

        /*
         * The program's main logo/icon.
         */
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon2.png"));

        /*
         * The program's secondary logo/icon.
         */
        ImageIcon altIcon = new ImageIcon(getClass().getResource("/images/icon.png"));

        mainFrame = new JFrame("Circuit Builder - Elliot Gorman 2019");

        //sets some parameters of the main JFrame
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(900, 625));
        mainFrame.setIconImage(icon.getImage());

        creditsPanel.setLayout(new BorderLayout());

        //adds the icon to the JLabel
        creditsIcon.setIcon(altIcon);
        creditsIcon.setHorizontalAlignment(JLabel.CENTER);
        creditsIcon.setVerticalAlignment(JLabel.CENTER);

        //sets the dimensions of the two frames
        helpFrame.setSize(new Dimension(650, 575));
        aboutFrame.setSize(new Dimension(200, 230));
        aboutFrame.setIconImage(icon.getImage());

        //adds the menu items to their corresponding menus
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        optionsMenu.add(ioHighlightItem);
        optionsMenu.add(gateBorderItem);
        optionsMenu.add(lockGates);

        //tries to load the html files
        try {
            helpText.setContentType("text/html");
            //load the html file
            helpText.setPage(getClass().getResource("/images/howTo.html").toString());
            helpText.setEditable(false);
            helpFrame.add(helpText);
            helpFrame.setIconImage(icon.getImage());

            aboutText.setContentType("text/html");
            //load the html file
            aboutText.setPage(
                    getClass().getResource("/images/credits.html").toString());
            aboutFrame.setLayout(new BorderLayout());
            aboutText.setEditable(false);
            creditsPanel.add(creditsIcon, BorderLayout.CENTER);
            creditsPanel.add(aboutText, BorderLayout.SOUTH);
            aboutFrame.setSize(new Dimension(300, 500));
            aboutFrame.add(creditsPanel);
            //if the html fail to load, the help menu won't be added to the program
            menuBar.add(helpMenu);
        } catch (IOException e) {
            System.err.println("Error loading HTML files");
        }

        //add the options
        menuBar.add(optionsMenu);
        menuBar.setBackground(Color.DARK_GRAY);
        menuBar.setBorder(new BasicBorders.MenuBarBorder(Color.DARK_GRAY, Color.GRAY));

        //sets the font of the prompts
        contents.setFont(contents.getFont().deriveFont(16f));

        //sets some parameters of the remove all button
        button.setIcon(new ImageIcon(getClass().getResource("/images/REMOVEALL/REMOVEALL.png")));
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button.setText("REMOVE ALL");

        //sets some parameters of the main buttonPanel
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(buttons, BorderLayout.CENTER);
        buttonPanel.add(button, BorderLayout.SOUTH);

        //add all the main components to the main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(canvas, BorderLayout.CENTER);
        mainPanel.add(contents, BorderLayout.NORTH);

        //add the main panel and the menu bar to the main frame
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(menuBar, BorderLayout.NORTH);
    }

    /**
     * The canvas where the user can place their circuit.
     */
    private DrawingPanel canvas;

    /**
     * The menu item brings up the instructions.
     */
    private JMenuItem helpItem;

    /**
     * The menu item for the I/O highlight option.
     */
    private JMenuItem ioHighlightItem;

    /**
     * The menu item for showing the gate borders.
     */
    private JMenuItem gateBorderItem;

    /**
     * The menu item for locking the gate positions.
     */
    private JMenuItem lockGates;

    /**
     * The menu item brings up the about frame.
     */
    private JMenuItem aboutItem;

    /**
     * The window which displays the instructions.
     */
    private JFrame aboutFrame;

    /**
     * The Window for the instructions.
     */
    private JFrame helpFrame;

    /**
     * The button for clearing the canvas.
     */
    private JButton button;

    /**
     * The frame which the application is hosted on.
     */
    private JFrame mainFrame;
}
