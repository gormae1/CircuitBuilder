package circuitbuilder;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The 'output' class, for sending values over.
 *
 * @author elliot
 */
class Output extends LogicGateIO {
    /**
     * The connected Input.
     */
    private Input sendingTo;

    /**
     * Flag if the parent component has more than one output.
     */
    private boolean multi = false;

    /**
     * The output's value.
     */
    private int val = 0;

    /**
     * Instantiates a new output.
     *
     * @param owner the owning logic gate
     * @param x     the x coord
     * @param y     the y coord
     */
    Output(LogicGate owner, int x, int y) {
        //calls the super class
        super(owner, x, y);

        //change to io = new Polygon(); ??
        //creates the hitBox for the Output
        Polygon hitBox = new Polygon();

        hitBox.addPoint(x, y + HEIGHT / 2);
        hitBox.addPoint(x - WIDTH, y + HEIGHT / 2);
        hitBox.addPoint(x - WIDTH, y - HEIGHT / 2);
        hitBox.addPoint(x, y - HEIGHT / 2);

        io = hitBox;

        //sets the color to orange
        ioColor = new Color(200, 34, 54, 100);
    }

    /**
     * Getter for the devices value, first calls the logic function.
     *
     * @return the gates value
     */
    int getVal() {
        owner.performFunction();
        if (!multi) {
            val = owner.getVal();
        }
        return val;
    }


    /**
     * Manually set the value of the output.
     *
     * @param val the value to set
     */
    void setVal(int val) {
        this.val = val;
    }

    /**
     * Enables multi flag.
     */
    void setMultiple() {
        multi = true;
    }

    /**
     * Getter for the connecting input.
     *
     * @return the connecting input
     */
    Input getConnectedTo() {
        return sendingTo;
    }

    /**
     * Setter for the connecting input.
     *
     * @param sendingTo the connecting input
     */
    void setSendingTo(Input sendingTo) {
        this.sendingTo = sendingTo;
    }
}