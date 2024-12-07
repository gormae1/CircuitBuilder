package circuitbuilder;

/**
 * The 'nand' logic gate.
 */
class Nand extends LogicGate {
    Nand(int x, int y) {
        super(x, y);
        setGateIcon("/images/NAND/NAND.png");
        outputs.add(new Output(this, getWidth(), getHeight() / 2));
        inputs.add(new Input(this, 0, (getHeight() / 3)));
        inputs.add(new Input(this, 0, (getHeight() / 3) + (Input.INPUT_HEIGHT)));
    }

    /**
     * Implements NAND logic.
     */
    @Override
    void performFunction() {
        int t = 0;
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).getVal() != 1) {
                //off
                t = 1;
                break;
            }
        }
        setVal(t);
    }
}