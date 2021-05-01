package com.kuma.fsm.impl.statemap;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.FsmTest;
import com.kuma.fsm.impl.MachineState;

/**
 * @author kuma 2021-05-01
 */
public class StateMapFsmTest extends FsmTest {

    @Override
    public Fsm initLockedFsm() {
        return new StateMapFsm(MachineState.LOCKED);
    }

    @Override
    public Fsm initUnlockedFsm() {
        return new StateMapFsm(MachineState.UNLOCKED);
    }
}