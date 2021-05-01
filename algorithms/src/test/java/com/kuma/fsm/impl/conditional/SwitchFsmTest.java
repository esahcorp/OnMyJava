package com.kuma.fsm.impl.conditional;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.FsmTest;
import com.kuma.fsm.impl.MachineState;

/**
 * @author kuma 2021-05-01
 */
public class SwitchFsmTest extends FsmTest {

    @Override
    public Fsm initLockedFsm() {
        return new SwitchFsm(MachineState.LOCKED);
    }

    @Override
    public Fsm initUnlockedFsm() {
        return new SwitchFsm(MachineState.UNLOCKED);
    }
}
