package circuitbuilder;

/**
 * The 'switch' class, the source of input.
 *
 * @author elliot
 */
class Switch extends LogicGate {
    Switch(int x, int y) {
        super(x, y);
        //starting value is always 1
        setVal(1);
        setGateIcon("/images/SWITCH/SWITCH_ON.png");
        outputs.add(new Output(this, getWidth(), getHeight() / 2));

    }

    /**
     * Switch functionality
     */
    @Override
    void performFunction() {
        //does nothing, due to functionality being implemented in mouseClicked
    }
}
