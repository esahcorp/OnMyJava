package com.kuma.fsm.impl.statepattern;

import com.kuma.fsm.impl.MachineState;

/**
 * Base State of State Pattern
 *
 * @author kuma 2021-05-01
 */
public interface State {

    String insertCoin(StatePatternFsm fsm);

    String pass(StatePatternFsm fsm);

    /**
     * 为了复用类不得不加上的方法
     *
     * @return state enum
     */
    MachineState state();
}
