package circuitbuilder;

/**
 * The 'fulladder' logic gate.
 */
class FullAdder extends LogicGate {
    FullAdder(int x, int y) {
        super(x, y);

        setBounds(x, y, 294, 152 + 9);
        setGateIcon("/images/FULLADDER/FULLADDER.png");

        outputs.add(new Output(this, getWidth(), getHeight() / 4 + 4));
        outputs.add(new Output(this, getWidth(), (getHeight() / 2) + Input.INPUT_HEIGHT + 10));

        inputs.add(new Input(this, 0, (getHeight() / 9)));
        inputs.add(new Input(this, 0, (getHeight() / 8) + (Input.INPUT_HEIGHT - 2)));
        inputs.add(new Input(this, 0, (getHeight() / 4) + (Input.INPUT_HEIGHT - 3)));
    }

    /**
     * Implements the full adder logic.
     */
    @Override
    void performFunction() {
        //1, 2, 3 = CIn
        //sum ^, carry &&
        //a = 0
        //b = 1
        //c = 2
        //last sum = 0;
        //last c = 1;

        outputs.get(0).setMultiple();
        outputs.get(1).setMultiple();

        int a = inputs.get(0).getVal();
        int b = inputs.get(1).getVal();
        int c = inputs.get(2).getVal();

        int firstSum = a ^ b;
        int firstCarry = ((a == 1) && (b == 1)) ? 1 : 0;

        int lastSum = c ^ firstSum;
        int lastCarry = ((firstSum == 1) && (c == 1)) ? 1 : 0;

        outputs.get(1).setVal((firstCarry == 1) ? 1 : ((lastCarry == 1) ? 1 : 0));
        outputs.get(0).setVal(lastSum);
    }
}
