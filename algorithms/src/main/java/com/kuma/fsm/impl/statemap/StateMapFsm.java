package com.kuma.fsm.impl.statemap;

import com.kuma.fsm.impl.Fsm;
import com.kuma.fsm.impl.Input;
import com.kuma.fsm.impl.InvalidInputException;
import com.kuma.fsm.impl.MachineState;
import lombok.var;

import java.util.Objects;

/**
 * State Map implement FSM
 *
 * @author kuma 2021-05-01
 */
public class StateMapFsm implements Fsm {

    private MachineState state;

    private final StateMap stateMap = StateMap.getInstance();

    public StateMapFsm(MachineState state) {
        this.state = state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    @Override
    public String execute(Input input) {
        var entry = stateMap.get(state, input);
        if (Objects.isNull(entry)) {
            throw new InvalidInputException();
        }
        var nextState = entry.getNextState();
        setState(nextState);
        var event = entry.getEvent();
        return event.action();
    }

    @Override
    public MachineState getState() {
        return this.state;
    }
}
