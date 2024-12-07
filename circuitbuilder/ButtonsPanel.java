package circuitbuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The custom JPanel which houses all the gate buttons.
 */
public class ButtonsPanel extends JPanel implements ActionListener {

    /**
     * Instantiates a new custom JPanel.
     */
    ButtonsPanel() {
        setLayout(new GridLayout(0, 2));
        setBackground(Color.GRAY);

        initComponents();

        //adds all the buttons
        add(andButton);
        add(notButton);
        add(orButton);
        add(norButton);
        add(nandButton);
        add(xorButton);
        add(xnorButton);
        add(switchButton);
        add(haButton);
        add(faButton);
        add(ledButton);
        add(wireButton);
    }

    /**
     * The listener for each of the buttons.
     *
     * @param actionEvent the event
     */
    // @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //set the current device to the selected button.
        DrawingPanel.currentDevice =
                Integer.parseInt(gateButtonGroup.getSelection().getActionCommand());
    }

    /**
     * Initializes all the needed Swing components.
     */
    private void initComponents() {
        //initializes all components
        gateButtonGroup = new ButtonGroup();
        andButton = new JToggleButton();
        notButton = new JToggleButton();
        orButton = new JToggleButton();
        norButton = new JToggleButton();
        nandButton = new JToggleButton();
        xorButton = new JToggleButton();
        switchButton = new JToggleButton();
        xnorButton = new JToggleButton();
        haButton = new JToggleButton();
        faButton = new JToggleButton();
        ledButton = new JToggleButton();
        wireButton = new JToggleButton();

        //add all the buttons to the same group
        gateButtonGroup.add(andButton);
        gateButtonGroup.add(notButton);
        gateButtonGroup.add(orButton);
        gateButtonGroup.add(nandButton);
        gateButtonGroup.add(norButton);
        gateButtonGroup.add(xnorButton);
        gateButtonGroup.add(xorButton);
        gateButtonGroup.add(faButton);
        gateButtonGroup.add(haButton);
        gateButtonGroup.add(ledButton);
        gateButtonGroup.add(wireButton);
        gateButtonGroup.add(switchButton);
        gateButtonGroup.add(xorButton);

        //sets some parameters of all the buttons
        //(their listeners, their icons, their alignments, etc...)

        andButton.setIcon(new ImageIcon(getClass().getResource("/images/AND/AND.png"))); // NOI18N
        andButton.setText("AND");
        andButton.setHorizontalTextPosition(SwingConstants.CENTER);
        andButton.setVerticalAlignment(SwingConstants.TOP);
        andButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        andButton.setActionCommand("0");
        andButton.addActionListener(this);

        notButton.setIcon(new ImageIcon(getClass().getResource("/images/NOT/NOT.png"))); // NOI18N
        notButton.setText("NOT");
        notButton.setHorizontalTextPosition(SwingConstants.CENTER);
        notButton.setVerticalAlignment(SwingConstants.TOP);
        notButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        notButton.setActionCommand("1");
        notButton.addActionListener(this);

        orButton.setIcon(new ImageIcon(getClass().getResource("/images/OR/OR.png"))); // NOI18N
        orButton.setText("OR");
        orButton.setHorizontalTextPosition(SwingConstants.CENTER);
        orButton.setVerticalAlignment(SwingConstants.TOP);
        orButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        orButton.setActionCommand("2");
        orButton.addActionListener(this);

        norButton.setIcon(new ImageIcon(getClass().getResource("/images/NOR/NOR.png"))); // NOI18N
        norButton.setText("NOR");
        norButton.setHorizontalTextPosition(SwingConstants.CENTER);
        norButton.setVerticalAlignment(SwingConstants.TOP);
        norButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        norButton.setActionCommand("3");
        norButton.addActionListener(this);

        nandButton.setIcon(new ImageIcon(getClass().getResource("/images/NAND/NAND.png"))); // NOI18N
        nandButton.setText("NAND");
        nandButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nandButton.setVerticalAlignment(SwingConstants.TOP);
        nandButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        nandButton.setActionCommand("4");
        nandButton.addActionListener(this);

        xorButton.setIcon(new ImageIcon(getClass().getResource("/images/XOR/XOR.png"))); // NOI18N
        xorButton.setText("XOR");
        xorButton.setHorizontalTextPosition(SwingConstants.CENTER);
        xorButton.setVerticalAlignment(SwingConstants.TOP);
        xorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        xorButton.setActionCommand("5");
        xorButton.addActionListener(this);

        xnorButton.setIcon(new ImageIcon(getClass().getResource("/images/XNOR/XNOR.png"))); // NOI18N
        xnorButton.setText("XNOR");
        xnorButton.setHorizontalTextPosition(SwingConstants.CENTER);
        xnorButton.setVerticalAlignment(SwingConstants.TOP);
        xnorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        xnorButton.setActionCommand("6");
        xnorButton.addActionListener(this);

        switchButton.setIcon(new ImageIcon(getClass().getResource("/images/SWITCH/SWITCH_OFF.png"))); // NOI18N
        switchButton.setText("SWITCH");
        switchButton.setHorizontalTextPosition(SwingConstants.CENTER);
        switchButton.setVerticalAlignment(SwingConstants.TOP);
        switchButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        switchButton.setActionCommand("7");
        switchButton.addActionListener(this);

        haButton.setIcon(new ImageIcon(getClass().getResource("/images/HALFADDER/HALFADDER_SQ.png"))); // NOI18N
        haButton.setText("HALF ADDER");
        haButton.setHorizontalTextPosition(SwingConstants.CENTER);
        haButton.setVerticalAlignment(SwingConstants.TOP);
        haButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        haButton.setActionCommand("8");
        haButton.addActionListener(this);

        faButton.setIcon(new ImageIcon(getClass().getResource("/images/FULLADDER/FULLADDER_SQ.png"))); // NOI18N
        faButton.setHorizontalTextPosition(SwingConstants.CENTER);
        faButton.setText("FULL ADDER");
        faButton.setVerticalAlignment(SwingConstants.TOP);
        faButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        faButton.setActionCommand("9");
        faButton.addActionListener(this);

        ledButton.setIcon(new ImageIcon(getClass().getResource("/images/OUTPUT/OUTPUT_OFF.png"))); // NOI18N
        ledButton.setText("LED");
        ledButton.setHorizontalTextPosition(SwingConstants.CENTER);
        ledButton.setVerticalAlignment(SwingConstants.TOP);
        ledButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        ledButton.setActionCommand("10");
        ledButton.addActionListener(this);

        wireButton.setIcon(new ImageIcon(getClass().getResource("/images/WIRE/WIRE.png"))); // NOI18N
        wireButton.setText("WIRE");
        wireButton.setHorizontalTextPosition(SwingConstants.CENTER);
        wireButton.setVerticalAlignment(SwingConstants.TOP);
        wireButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        wireButton.setActionCommand("11");
        wireButton.addActionListener(this);
    }

    /**
     * The toggle button for the and gate.
     */
    private JToggleButton andButton;

    /**
     * The toggle button for the full adder.
     */
    private JToggleButton faButton;

    /**
     * The button group for all of the gate buttons.
     */
    private ButtonGroup gateButtonGroup;

    /**
     * The toggle button for the half adder.
     */
    private JToggleButton haButton;

    /**
     * The toggle button for the led.
     */
    private JToggleButton ledButton;

    /**
     * The toggle button for the nand gate.
     */
    private JToggleButton nandButton;

    /**
     * The toggle button for the nor gate.
     */
    private JToggleButton norButton;

    /**
     * The toggle button for the not gate.
     */
    private JToggleButton notButton;

    /**
     * The toggle button for the or gate.
     */
    private JToggleButton orButton;

    /**
     * The toggle button for the switch.
     */
    private JToggleButton switchButton;

    /**
     * The toggle button for the wire.
     */
    private JToggleButton wireButton;

    /**
     * The toggle button for the xnor gate.
     */
    private JToggleButton xnorButton;

    /**
     * The toggle button for the xor gate.
     */
    private JToggleButton xorButton;
}