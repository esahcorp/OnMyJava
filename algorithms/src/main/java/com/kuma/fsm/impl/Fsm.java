package com.kuma.fsm.impl;

/**
 * Base class
 *
 * @author kuma 2021-05-01
 */
public interface Fsm {

    String execute(Input input);

    MachineState getState();

    default String refund() {
        return "refund";
    }

    default String close() {
        return "closed";
    }

    default String alarm() {
        return "alarm";
    }

    default String open() {
        return "opened";
    }
}
