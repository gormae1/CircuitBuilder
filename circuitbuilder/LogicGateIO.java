package circuitbuilder;

import java.awt.*;

/**
 * abstract 'template' for the connectingInput and connectingOutput classes.
 *
 * @author elliot
 */
abstract class LogicGateIO {
    /**
     * The shape of the circuitbuilder.Input / circuitbuilder.Output (acts as a hitbox).
     */
    Polygon io;

    /**
     * The color of the circuitbuilder.Input/circuitbuilder.Output.
     */
    Color ioColor;

    /**
     * Flag for drawing the highlights around the Inputs and outputs.
     */
    private static boolean highlight = false;

    /**
     * The logic gate that the I/O pertains to.
     */
    LogicGate owner;

    /**
     * The default height for the I/O hitbox.
     */
    static final int HEIGHT = 20;

    /**
     * The default width for the I/O hitbox.
     */
    static final int WIDTH = 20;


    private int connectX;
    private int connectY;

    /**
     * Instantiates a circuitbuilder.LogicGateIO object.
     *
     * @param gate the logic gate which this pertains to
     * @param x    the xCoordinate (used for placement and connection lines)
     * @param y    the yCoordinate (used for placement and connection lines)
     */
    LogicGateIO(LogicGate gate, int x, int y) {
        this.owner = gate;
        this.connectX = x;
        this.connectY = y;
    }

    /**
     * Finds whether the mouse is on the shape.
     *
     * @param mousePoint the Point which the mouse is on
     * @return {@code true} if the mouse is on this shape
     */
    boolean isMouseOn(Point mousePoint) {
        return io.contains(mousePoint);
    }

    /**
     * Used for positioning and drawing connections.
     *
     * @return the x position that the connection should be drawn on
     */
    public int getX() {
        return owner.getX() + connectX;
    }

    /**
     * Used for positioning and drawing connections.
     *
     * @return the y coordinate
     */
    public int getY() {
        return owner.getY() + connectY;
    }

    static void switchHighlight() {
        highlight = !highlight;
    }

    static boolean isHighlight() {
        return highlight;
    }

    /**
     * paints the I/O.
     *
     * @param graphics2D the graphics context
     */
    void paint(Graphics2D graphics2D) {
        //sets the color to the color variable (for differentiating between I / O)
        if (!highlight) {
            graphics2D.setColor(ioColor);

        } else {
            graphics2D.setColor(ioColor);
            //fills the shape
            graphics2D.fill(io);
        }
    }
}