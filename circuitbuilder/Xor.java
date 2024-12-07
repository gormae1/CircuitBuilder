package circuitbuilder;

/**
 * The 'xor' logic gate.
 *
 * @author elliot
 */
class Xor extends LogicGate {
    Xor(int x, int y) {
        super(x, y);

        setGateIcon("/images/XOR/XOR.png");

        outputs.add(new Output(this, getWidth(), getHeight() / 2));
        inputs.add(new Input(this, 0, (getHeight() / 3)));
        inputs.add(new Input(this, 0, (getHeight() / 3) + (Input.INPUT_HEIGHT)));
    }

    /**
     * Implements XOR logic.
     */
    @Override
    void performFunction() {
        //xor
        int t = inputs.get(0).getVal() ^ inputs.get(1).getVal();
        setVal(t);
    }

}