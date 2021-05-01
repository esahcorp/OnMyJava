package com.kuma.fsm.impl.enumfsm;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.Input;
import com.kuma.fsm.impl.InvalidInputException;
import com.kuma.fsm.impl.MachineState;

/**
 * Enum FSM
 *
 * @author kuma 2021-05-02
 */
public class EnumFsm implements Fsm {

    public void setState(StateEnum state) {
        this.state = state;
    }

    private StateEnum state;

    public EnumFsm(StateEnum state) {
        this.state = state;
    }

    @Override
    public String execute(Input input) {
        // Input also is an enum, could also use abstract method.
        if (input == Input.INSERT_COIN) {
            return state.insertCoin(this);
        } else if (input == Input.PASS) {
            return state.pass(this);
        } else {
            throw new InvalidInputException();
        }
    }

    @Override
    public MachineState getState() {
        // to reuse test class, use some bad code
        return MachineState.valueOf(state.name());
    }
}
