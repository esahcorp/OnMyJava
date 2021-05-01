package com.kuma.fsm.impl.statepattern;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.Input;
import com.kuma.fsm.impl.InvalidInputException;
import com.kuma.fsm.impl.MachineState;

/**
 * State pattern implement FSM
 *
 * @author kuma 2021-05-01
 */
public class StatePatternFsm implements Fsm {

    private final LockedState lockedState = new LockedState();
    private final UnlockedState unlockedState = new UnlockedState();

    private State state;

    @Override
    public String execute(Input input) {
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
        return state.state();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setState(MachineState state) {
        if (MachineState.LOCKED == state) {
            this.state = lockedState;
        } else {
            this.state = unlockedState;
        }
    }

    public void changeState() {
        if (state instanceof UnlockedState) {
            setState(lockedState);
        } else {
            setState(unlockedState);
        }
    }
}
