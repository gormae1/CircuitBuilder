package circuitbuilder;

/**
 * The 'or' logic gate.
 *
 * @author elliot
 */
class Or extends LogicGate {
    Or(int x, int y) {
        super(x, y);

        setGateIcon("/images/OR/OR.png");

        outputs.add(new Output(this, getWidth(), getHeight() / 2));
        inputs.add(new Input(this, 0, (getHeight() / 3)));
        inputs.add(new Input(this, 0, (getHeight() / 3) + (Input.INPUT_HEIGHT)));
    }

    /**
     * Implements OR logic.
     */
    @Override
    void performFunction() {
        //temp value
        int t = 0;
        for (Input input : inputs) {
            if (input.getVal() == 1) {
                t = 1;
                break;
            }
        }
        setVal(t);
    }
}