package com.kuma.fsm.impl.statemap;

/**
 * Entrance machine close event
 *
 * @author kuma 2021-05-01
 */
public class CloseEvent implements Event {
    @Override
    public String action() {
        return "closed";
    }
}
