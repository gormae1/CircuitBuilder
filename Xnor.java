package circuitbuilder;

/**
 * The 'xnor' logic gate.
 *
 * @author elliot
 */
class Xnor extends LogicGate {
    Xnor(int x, int y) {
        super(x, y);

        setGateIcon("/images/XNOR/XNOR.png");

        inputs.add(new Input(this, 0, getHeight() / 3));
        inputs.add(new Input(this, 0, (getHeight() / 3) + Input.INPUT_HEIGHT));
        outputs.add(new Output(this, getWidth(), getHeight() / 2));
    }

    /**
     * Implements XOR logic
     */
    @Override
    void performFunction() {
        int t = (inputs.get(0).getVal() ^ inputs.get(1).getVal()) == 1 ? 0 : 1;
        setVal(t);
    }
}
