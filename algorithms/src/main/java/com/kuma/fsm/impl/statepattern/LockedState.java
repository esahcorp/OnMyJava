package com.kuma.fsm.impl.statepattern;

import com.kuma.fsm.impl.MachineState;

/**
 * Locked State
 *
 * @author kuma 2021-05-01
 */
public class LockedState implements State {

    @Override
    public String insertCoin(StatePatternFsm fsm) {
        fsm.changeState();
        return fsm.open();
    }

    @Override
    public String pass(StatePatternFsm fsm) {
        return fsm.alarm();
    }

    @Override
    public MachineState state() {
        return MachineState.LOCKED;
    }
}
