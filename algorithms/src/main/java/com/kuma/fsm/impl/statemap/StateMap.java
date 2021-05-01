package com.kuma.fsm.impl.statemap;

import com.kuma.fsm.impl.Input;
import com.kuma.fsm.impl.MachineState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * State Map
 *
 * @author kuma 2021-05-01
 */
public class StateMap {

    private static final StateMap INSTANCE = new StateMap();

    private final Map<String, WrapperEntry> map = new HashMap<>(4);

    private StateMap() {
        map.put(generateKey(MachineState.LOCKED, Input.INSERT_COIN),
                new WrapperEntry(MachineState.UNLOCKED, new OpenEvent()));
        map.put(generateKey(MachineState.LOCKED, Input.PASS),
                new WrapperEntry(MachineState.LOCKED, new AlarmEvent()));
        map.put(generateKey(MachineState.UNLOCKED, Input.INSERT_COIN),
                new WrapperEntry(MachineState.UNLOCKED, new RefundEvent()));
        map.put(generateKey(MachineState.UNLOCKED, Input.PASS),
                new WrapperEntry(MachineState.LOCKED, new CloseEvent()));
    }

    public static StateMap getInstance() {
        return INSTANCE;
    }

    public WrapperEntry get(MachineState state, Input input) {
        if (Objects.isNull(input)) {
            return null;
        }
        return map.get(generateKey(state, input));
    }

    private String generateKey(MachineState state, Input input) {
        return state.name() + input.name();
    }
}
