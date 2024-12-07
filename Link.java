package circuitbuilder;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The 'link' class, establishes a connection from input to output.
 *
 * @author elliot
 */
class Link {
    /**
     * The Output to connect with the input.
     */
    private Output connectingOutput;

    /**
     * The Input to connect to the output.
     */
    private Input connectingInput;

    /**
     * Instantiate a new Link.
     *  @param outTo the output to link
     * @param inTo  the input to link
     */
    Link(Output outTo, Input inTo) {
        this.connectingInput = inTo;
        this.connectingOutput = outTo;
    }


    /**
     * Getter for the connecting input.
     *
     * @return the connecting input of the connection
     */
    Input getConnectingInput() {
        return connectingInput;
    }

    /**
     * Getter for the output..
     *
     * @return the connecting output
     */
    Output getConnectingOutput() {
        return connectingOutput;
    }

    /**
     * Paints the connection from an Input to an Output.
     *
     * @param graphics2D the graphics context
     */
    void paintConnection(Graphics2D graphics2D) {
        //orange if on, black if off.
        graphics2D.setColor(connectingOutput.getVal() == 1 ? Color.ORANGE : Color.BLACK);
        //draws the line connecting the two
        graphics2D.drawLine(connectingOutput.getX() - 2, connectingOutput.getY(), connectingInput.getX() + 2, connectingInput.getY());
    }
}