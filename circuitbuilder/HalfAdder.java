package circuitbuilder;

/**
 * The 'halfadder' logic "gate".
 *
 * @author elliot
 */
class HalfAdder extends LogicGate {
    HalfAdder(int x, int y) {
        super(x, y);

        //gate is bigger than others, so size needs to be readjusted
        setBounds(x, y, 127, (88 + 9));
        setGateIcon("/images/HALFADDER/HALFADDER.png");

        outputs.add(new Output(this, getWidth(), getHeight() / 4 + 2));
        outputs.add(new Output(this, getWidth(), (getHeight() / 2) + Input.INPUT_HEIGHT + 2));

        inputs.add(new Input(this, 0, (getHeight() / 6)));
        inputs.add(new Input(this, 0, (getHeight() / 2) - 12));
    }

    /**
     * Implements half adder logic
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

        outputs.get(0).setVal(a ^ b);
        outputs.get(1).setVal(a == 1 && b == 1 ? 1 : 0);
    }
}
