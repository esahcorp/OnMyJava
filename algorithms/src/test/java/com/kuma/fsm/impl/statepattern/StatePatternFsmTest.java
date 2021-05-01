package com.kuma.fsm.impl.statepattern;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.FsmTest;
import com.kuma.fsm.impl.MachineState;
import lombok.var;

import static org.junit.Assert.*;

/**
 * @author kuma 2021-05-01
 */
public class StatePatternFsmTest extends FsmTest {

    @Override
    public Fsm initLockedFsm() {
        var fsm = new StatePatternFsm();
        fsm.setState(MachineState.LOCKED);
        return fsm;
    }

    @Override
    public Fsm initUnlockedFsm() {
        var fsm = new StatePatternFsm();
        fsm.setState(MachineState.UNLOCKED);
        return fsm;
    }
}