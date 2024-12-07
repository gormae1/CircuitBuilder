package circuitbuilder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * The 'template' that all logic gates extend.
 *
 * @author elliot.
 */
abstract class LogicGate extends JLabel implements MouseListener, MouseMotionListener {
    /**
     * The inputs associated with the logic gate.
     */
    ArrayList<Input> inputs = new ArrayList<Input>();

    /**
     * The outputs associated with the logic gate.
     */
    ArrayList<Output> outputs = new ArrayList<Output>();

    /**
     * Flag for showing the borders around the gates.
     */
    private static boolean borders = false;

    /**
     * Flag for locking the movement in place.
     */
    private static boolean locked = false;

    /**
     * The value of the gate.
     */
    private int val = 0;

    /**
     * The currently selected output (only one for all classes).
     */
    static Output selectedOut;

    /**
     * The currently selected input (only one for all classes as well).
     */
    static Input selectedIn;

    /**
     * Default border surrounding the gates.
     */
    private static final LineBorder DEFAULT_BORDER = new LineBorder(Color.BLACK, 1);

    /**
     * The currently selected LogicGateIO (either input or output. One of the two above).
     */
    static LogicGateIO selectedIO;

    /**
     * The x coordinate of the dragging (for moving the label around).
     */
    private int dragX;

    /**
     * The y coordinate of the dragging (for moving the label around).
     */
    private int dragY;

    /**
     * Instantiates a new LogicGate object.
     * Extended by every logic Gate.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    LogicGate(int x, int y) {

        //Alignment and bounds
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        //gate images are 82w x 41h...
        this.setBounds(x, y, 82, 50);

        //adds the listeners
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * sets the icon of the JLabel using a given path.
     *
     * @param path the path of the image
     */
    void setGateIcon(String path) {
        try {
            setIcon(new ImageIcon(getClass().getResource(path)));
        } catch (NullPointerException e) {
            System.err.println("Error loading images.");
        }
    }

    /**
     * Turns the borders on/off.
     */
    static void switchBorders() {
        borders = !borders;
    }

    /**
     * Getter for the borders flag.
     *
     * @return the borders flag
     */
    static boolean isBorders() {
        return borders;
    }

    /**
     * Turns the lock on/off.
     */
    static void switchLocked() {
        locked = !locked;
    }

    /**
     * Getter for the locked flag.
     *
     * @return the locked flag
     */
    static boolean isLocked() {
        return locked;
    }

    /**
     * Painting method, paints the I/O.
     *
     * @param g the graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        //refresh
        super.paintComponent(g);
        //if borders are on, set the borders
        setBorder(borders ? DEFAULT_BORDER : null);

        Graphics2D graphics2D = (Graphics2D) g;
        //paints all inputs
        for (Input input : inputs) {
            //if inputs draw
            input.paint(graphics2D);
        }
        //paints all outputs
        for (Output output : outputs) {
            output.paint(graphics2D);
        }
    }

    /**
     * Getter for the gate value.
     *
     * @return val the gate value
     */
    public int getVal() {
        return val;
    }

    /**
     * Setter for the gate value.
     *
     * @param value the gate value
     */
    public void setVal(int value) {
        this.val = value;
    }

    /**
     * Registers clicks on the gates.
     *
     * @param mouseEvent the event
     */
    //   @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //temp input for checking the inputs
        Input in;

        //temp output for checking the outputs
        Output out = null;

        /*
         * if the user clicked on an input or an output
         */
        if ((in = onInput(mouseEvent.getPoint())) != null || (out = onOutput(mouseEvent.getPoint())) != null) {
            DrawingPanel.begin = true;

            //gets the selected input/output
            if (in != null) {
                selectedIn = in;
                selectedIO = in;
            } else {
                selectedOut = out;
                selectedIO = out;
            }

            //if the user right-clicked on the input/output
            if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                DrawingPanel.begin = false;

                //loop through connections and remove it
                removeConnection(selectedIO);

                //if on addConnection button AND...
                //if there was a previously selected output AND...
                //if the input and the output don't belong to the same component
            } else if (DrawingPanel.currentDevice == 11 && (selectedOut != null && selectedIn != null) && selectedOut.owner != selectedIn.owner) {
                DrawingPanel.begin = false;
                //if the input is already connected to something
                if (selectedIn.getConnectedTo() != null) {
                    //beep
                    Toolkit.getDefaultToolkit().beep();
                    //sets both of the selection back to null
                    selectedOut = null;
                    selectedIn = null;
                } else {
                    //new connection
                    DrawingPanel.connections.add(new Link(selectedOut,
                            selectedIn));

                    //sets the connection (input is connected to output)
                    selectedIn.senderToIn(selectedOut);
                    selectedOut.setSendingTo(selectedIn);

                    //update the LCDs
                    updateLCD();

                    //reset selected connectingOutput (connection has already been made)
                    selectedOut = null;
                    selectedIn = null;
                    //repaint the drawingPanel
                    getParent().repaint();
                }
            }
            //return, all that needs to be done
            return;
        }

        //if the gate was right-clicked (not the IO) (delete process)
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            selectedIn = null;
            selectedOut = null;

            //delete gate
            Component parent = getParent();
            //remove all the connection attached to this gate
            removeAllConnections();

            getParent().remove(this);
            removeAll();
            // parent.revalidate();
            parent.repaint();

            return;
        }

        //if this gate is a switch
        if (this instanceof Switch) {
            //if all other cases failed, then I/O wasn't clicked
            //performs switch logic (on/off) if it was clicked on
            //and all other cases failed

            //switch val
            val = val == 1 ? 0 : 1;
            //changes image and updates LCDs then repaints
            setGateIcon(val == 0 ? "/images/SWITCH/SWITCH_OFF.png" : "/images/SWITCH/SWITCH_ON.png");
            updateLCD();
            getParent().repaint();
        }
    }

    /**
     * Refreshes all LCDs involved in a connection.
     */
    private void updateLCD() {
        for (int j = 0; j < DrawingPanel.leds.size(); j++) {
            DrawingPanel.leds.get(j).performFunction();
        }
    }

    /**
     * Gets the output clicked on (if any).
     *
     * @param point the point the mouse clicked on
     * @return the output {@code null} if none found
     */
    private Output onOutput(Point point) {
        for (Output output : outputs) {
            if (output.isMouseOn(point)) {
                return output;
            }
        }
        return null;
    }

    /**
     * Gets the input clicked on (if any).
     *
     * @param point the point the mouse clicked on
     * @return the input {@code null} if none found
     */
    private Input onInput(Point point) {
        for (Input input : inputs) {
            if (input.isMouseOn(point)) {
                return input;
            }
        }
        return null;
    }

    /**
     * Removes a connection given either the input or the output involved.
     *
     * @param io the input or output
     */
    private void removeConnection(LogicGateIO io) {
        if (io instanceof Output) {
            if (((Output) io).getConnectedTo() == null) {
                return;
            }
        } else if (((Input) (io)).getConnectedTo() == null) {
            return;
        }

        for (int i = 0; i < DrawingPanel.connections.size(); i++) {
            //if connection involves the given parameter
            if (DrawingPanel.connections.get(i).getConnectingInput() == io
                    || DrawingPanel.connections.get(i).getConnectingOutput() == io) {
                //remove connecting output and input
                DrawingPanel.connections.get(i).getConnectingInput().senderToIn(null);
                DrawingPanel.connections.get(i).getConnectingOutput().setSendingTo(null);
                //remove the connection itself
                DrawingPanel.connections.remove(i);
                //ensures all of the connections ArrayList is processed
                i--;
            }
        }
        selectedIn = null;
        selectedOut = null;
        updateLCD();
        getParent().repaint();
    }

    /**
     * Removes all connections associated with this gate.
     */
    private void removeAllConnections() {
        //removes all input connections
        for (Input input : inputs) {
            removeConnection(input);
        }
        //removes all output connections
        //replaceable with foreach (but kept for comparability)
        for (Output output : outputs) {
            removeConnection(output);
        }
    }

    /**
     * Implements the logic.
     */
    abstract void performFunction();   //Essentially, the way it works is...
    //it calls the ancestor and tells it to refresh, which calls it's ancestor and tells it to refresh...
    //so on so forth... (implied instead of forced)

    /**
     * Mouse pressed event routine.
     *
     * @param mouseEvent the action event
     */
    //   @Override
    public void mousePressed(MouseEvent mouseEvent) {
        //sets the beginning of the drag to the mouse coordinates
        dragX = mouseEvent.getX();
        dragY = mouseEvent.getY();
        //repaint the canvas
        getParent().repaint();
    }

    /**
     * Handles when the mouse dragging.
     *
     * @param mouseEvent the event itself
     */
    // @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (locked) return;
        //set the new location of the gate to the dragging
        getParent().setCursor(new Cursor(Cursor.MOVE_CURSOR));

        setLocation(getX() + (mouseEvent.getX() - dragX),
                getY() + (mouseEvent.getY() - dragY));
        getParent().repaint();

    }

    /**
     * Listens for the mouse being released.
     *
     * @param mouseEvent the event
     */
    //   @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        //uninterested for now
    }

    /**
     * Listens for the mouse exiting the gate.
     *
     * @param mouseEvent the event.
     */
    //  @Override
    public void mouseExited(MouseEvent mouseEvent) {
        getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Listens for the mouse entering the gate (unused).
     *
     * @param mouseEvent the event.
     */
    //   @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        //uninterested for now
    }

    /**
     * Listens for mouse movements, changing cursors.
     *
     * @param mouseEvent the event
     */
    //  @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (onOutput(mouseEvent.getPoint()) != null || onInput(mouseEvent.getPoint()) != null) {
            getParent().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } else {
            getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        //dispatches event on the canvas to avoid the line being locked on the gate
        getParent().dispatchEvent(mouseEvent);
    }
}
