package com.kuma.fsm.impl.enumfsm;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.FsmTest;

import static org.junit.Assert.*;

/**
 * @author kuma 2021-05-02
 */
public class EnumFsmTest extends FsmTest {

    @Override
    public Fsm initLockedFsm() {
        return new EnumFsm(StateEnum.LOCKED);
    }

    @Override
    public Fsm initUnlockedFsm() {
        return new EnumFsm(StateEnum.UNLOCKED);
    }
}