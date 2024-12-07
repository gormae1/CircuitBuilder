package circuitbuilder;

/**
 * The 'and' logic gate.
 *
 * @author elliot
 */
class And extends LogicGate {
    And(int x, int y) {
        super(x, y);
        setGateIcon("/images/AND/AND.png");


        //getWidth because output is on other side of gate (the width)
        //getHeight because output is in center of other side of gate (height / 2)
        //0 because the inputs are on the starting side of the gate
        outputs.add(new Output(this, getWidth(), getHeight() / 2));

        inputs.add(new Input(this, 0, (getHeight() / 3)));
        inputs.add(new Input(this, 0, (getHeight() / 3) + (Input.INPUT_HEIGHT - 1)));
    }

    /**
     * Implements AND logic
     */
    @Override
    void performFunction() {
        //temp val
        int t = 1;
        for (Input input : inputs) {
            //if there is a single output that isn't on
            if (input.getVal() != 1) {
                //off
                t = 0;
                break;
            }
        }
        //sets the gate value to the temp val
        setVal(t);
    }
}