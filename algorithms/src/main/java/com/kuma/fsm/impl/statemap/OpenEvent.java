package com.kuma.fsm.impl.statemap;

/**
 * Entrance Machine open event
 *
 * @author kuma 2021-05-01
 */
public class OpenEvent implements Event {
    @Override
    public String action() {
        return "opened";
    }
}
