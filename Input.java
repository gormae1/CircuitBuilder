package circuitbuilder;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The Input class extends the Logic Gate IO class
 * and serves as the input pin on the gates.
 */
class Input extends LogicGateIO {
    /**
     * the connected output (essentially the connections.
     */
    private Output sender;

    /**
     * Int for separating inputs with 1 space
     */
    static final int INPUT_HEIGHT = HEIGHT - 1;

    /**
     * Instantiates a newInput.
     *
     * @param owner the parent logic gate
     * @param x     the x coordinate
     * @param y     the y coordinate
     */
    Input(LogicGate owner, int x, int y) {
        //calls the super class (LogicGateIO)
        super(owner, 0, y);
        //creates the hitBox for the Output
        Polygon hitBox = new Polygon();

        //builds the hitbox
        hitBox.addPoint(x, y + HEIGHT / 2);
        hitBox.addPoint(x + WIDTH, y + HEIGHT / 2);
        hitBox.addPoint(x + WIDTH, y - HEIGHT / 2);
        hitBox.addPoint(x, y - HEIGHT / 2);

        //sets the I/O shape to the hitBox polygon
        io = hitBox;

        //sets the color to green
        ioColor = new Color(12, 34, 200, 100);
    }

    /**
     * Gets the inputs value, used for gates logic.
     *
     * @return the connected outputs value
     */
    public int getVal() {
        //if there is actually a connection, otherwise return 0 (off)
        return sender != null ? sender.getVal() : 0;
    }

    /**
     * Getter of the connected output.
     *
     * @return the sender
     */
    Output getConnectedTo() {
        return sender;
    }

    /**
     * Setter for the connected output.
     *
     * @param sender the output sending over the values
     */
    void senderToIn(Output sender) {
        this.sender = sender;
    }
}