package com.kuma.fsm.impl.conditional;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.Input;
import com.kuma.fsm.impl.InvalidInputException;
import com.kuma.fsm.impl.MachineState;

import java.util.Objects;

/**
 * Switch 实现的状态机，基于逻辑语句
 *
 * @author kuma 2021-05-01
 */
public class SwitchFsm implements Fsm {

    private MachineState state;

    public SwitchFsm(MachineState state) {
        this.state = state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    @Override
    public String execute(Input input) {
        if (Objects.isNull(input)) {
            throw new InvalidInputException();
        }

        if (MachineState.LOCKED == state) {
            switch (input) {
                case INSERT_COIN:
                    setState(MachineState.UNLOCKED);
                    return open();
                case PASS:
                    return alarm();
                default:
                    throw new InvalidInputException();
            }
        }

        if (MachineState.UNLOCKED == state) {
            switch (input) {
                case INSERT_COIN:
                    return refund();
                case PASS:
                    setState(MachineState.LOCKED);
                    return close();
                default:
                    throw new InvalidInputException();
            }
        }
        return null;
    }

    @Override
    public MachineState getState() {
        return state;
    }
}
