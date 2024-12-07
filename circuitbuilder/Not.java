package circuitbuilder;

/**
 * The 'not' logicGate.
 *
 * @author elliot
 */
class Not extends LogicGate {
    Not(int x, int y) {
        super(x, y);
        setGateIcon("/images/NOT/NOT.png");
        inputs.add(new Input(this, 0, getHeight() / 2));
        outputs.add(new Output(this, getWidth(), getHeight() / 2));
    }

    /**
     * Implements NOT logic.
     */
    @Override
    void performFunction() {
        setVal(inputs.get(0).getVal() == 1 ? 0 : 1);
    }
}