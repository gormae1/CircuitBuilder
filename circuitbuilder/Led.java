package circuitbuilder;

/**
 * The 'led' gate, allows the user to see the output.
 *
 * @author elliot
 */
class Led extends LogicGate {
    Led(int x, int y) {
        super(x, y);

        setGateIcon("/images/OUTPUT/OUTPUT_OFF.png");

        inputs.add(new Input(this, 0, getHeight() / 2));
    }

    /**
     * Changes image according to input.
     */
    @Override
    void performFunction() {
        setGateIcon(inputs.get(0).getVal() == 1 ? "/images/OUTPUT/OUTPUT_ON.png" : "/images/OUTPUT/OUTPUT_OFF.png");
    }
}