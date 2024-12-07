package circuitbuilder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * The DrawingPanel custom JPanel, acts as a canvas for the user to drop
 * logic gates and connections onto.
 */
class DrawingPanel extends JPanel {
    /**
     * All the connections made.
     */
    static ArrayList<Link> connections = new ArrayList<Link>();

    /**
     * All the LCDs used.
     */
    static ArrayList<Led> leds = new ArrayList<Led>();

    /**
     * Stores a number corresponding to the device to place onto the panel.
     */
    static int currentDevice = -1;

    /**
     * The logic gate to place on the panel.
     */
    private LogicGate gateToAdd;

    /**
     * Flag to keep track of when the user is dragging the mouse
     * and they want to make a new connection.
     */
    private boolean drawDragging = false;

    /**
     * Flag to start drawing connection line according to the mouse drag.
     */
    static boolean begin = false;

    /**
     * Tracks the users mouse (the x position).
     */
    private int dragX = 0;

    /**
     * Tracks the users mouse (the y position).
     */
    private int dragY = 0;

    /**
     * Initializes a new Drawing Panel Swing component.
     */
    DrawingPanel() {
        //sets the layout, border and background
        setLayout(null);
        setBorder(new LineBorder(Color.ORANGE, 2));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            /**
             * Listens for mouse CircuitBuilders.
             * @param e the mouseEvent
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                //if the click wasn't a left-click
                if (e.getButton() != MouseEvent.BUTTON1) {
                    return;
                }

                LogicGate.selectedIn = null;
                LogicGate.selectedOut = null;
                LogicGate.selectedIO = null;
                begin = false;

                //decides on the gate to add.
                switch (currentDevice) {
                    case 0:
                        gateToAdd = new And(e.getX(), e.getY());
                        break;
                    case 1:
                        gateToAdd = new Not(e.getX(), e.getY());
                        break;
                    case 2:
                        gateToAdd = new Or(e.getX(), e.getY());
                        break;
                    case 3:
                        gateToAdd = new Nor(e.getX(), e.getY());
                        break;
                    case 4:
                        gateToAdd = new Nand(e.getX(), e.getY());
                        break;
                    case 5:
                        gateToAdd = new Xor(e.getX(), e.getY());
                        break;
                    case 6:
                        gateToAdd = new Xnor(e.getX(), e.getY());
                        break;
                    case 7:
                        gateToAdd = new Switch(e.getX(), e.getY());
                        break;
                    case 8:
                        gateToAdd = new HalfAdder(e.getX(), e.getY());
                        break;
                    case 9:
                        gateToAdd = new FullAdder(e.getX(), e.getY());
                        break;
                    case 10:
                        gateToAdd = new Led(e.getX(), e.getY());
                        leds.add((Led) gateToAdd);
                        break;
                    case 11:
                        gateToAdd = null;
                        break;
                }
                if (gateToAdd != null) {
                    add(gateToAdd);
                    repaint();
                }
                //?
                repaint();
            }
        });

        /*
         * The mouse listener applied to this component.
         */
        addMouseMotionListener(new MouseMotionListener() {
            /**
             * Listens for the mouse being dragged (uneeded).
             * @param mouseEvent the event
             */
            //@Override
            public void mouseDragged(MouseEvent mouseEvent) {
                mouseMoved(mouseEvent);
                //not interested
            }

            /**
             * Listens for mouse movement.
             * @param mouseEvent the mouseEvent
             */
            // @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                //object to avoid null pointer exception
                Point t;
                if ((t = getMousePosition(true)) != null) {
                    dragX = (int) t.getX();
                }
                if ((t = getMousePosition(true)) != null) {
                    dragY = (int) t.getY();
                }

                if (currentDevice == 11) {
                    if (LogicGate.selectedIO != null && begin) {

                        drawDragging = true;
                        repaint();
                    } else {
                        drawDragging = false;
                        repaint();
                    }
                }
            }
        });
    }

    /**
     * Removes everything from the panel, aside from the kill button
     */
    void removeAllComponents() {
        //loops through all components present
        for (int i = 0; i < getComponents().length; i++) {
            remove(getComponent(i));
            i--;
        }
        connections.clear();
        repaint();
    }

    /**
     * Overrides the default painting in order to do
     * some custom painting.
     *
     * @param g the graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        //refreshes
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        //line width
        graphics2D.setStroke(new BasicStroke(5));

        //set antialiasing for the smoother lines
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (drawDragging && begin) {
            g.drawLine(LogicGate.selectedIO.getX(), LogicGate.selectedIO.getY(), dragX, dragY);
        }

        //iterate through each of the connections and draw them
        for (Link connection : connections) {
            //paint line
            connection.paintConnection(graphics2D);
        }
    }
}

