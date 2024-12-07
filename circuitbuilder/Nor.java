package circuitbuilder;

/**
 * The not-or ('nor') logic gate.
 *
 * @author elliot
 */
class Nor extends LogicGate {
    Nor(int x, int y) {
        super(x, y);
        setGateIcon("/images/NOR/NOR.png");

        inputs.add(new Input(this, 0, getHeight() / 3));
        inputs.add(new Input(this, 0, (getHeight() / 3) + Input.INPUT_HEIGHT));
        outputs.add(new Output(this, getWidth(), getHeight() / 2));
    }

    /**
     * Implements NOR logic.
     */
    @Override
    void performFunction() {
        //temp value
        int t = 1;
        for (Input input : inputs) {
            if (input.getVal() == 1) {
                t = 0;
                break;
            }
        }
        setVal(t);
    }
}
