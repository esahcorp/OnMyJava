package com.kuma.fsm.impl.statepattern;

import com.kuma.fsm.impl.MachineState;

/**
 * Unlocked State behavior
 *
 * @author kuma 2021-05-01
 */
public class UnlockedState implements State {

    @Override
    public String insertCoin(StatePatternFsm fsm) {
        return fsm.refund();
    }

    @Override
    public String pass(StatePatternFsm fsm) {
        fsm.changeState();
        return fsm.close();
    }

    @Override
    public MachineState state() {
        return MachineState.UNLOCKED;
    }
}
