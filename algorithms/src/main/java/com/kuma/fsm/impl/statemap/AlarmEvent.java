package com.kuma.fsm.impl.statemap;

/**
 * Entrance machine alarm event
 *
 * @author kuma 2021-05-01
 */
public class AlarmEvent implements Event {
    @Override
    public String action() {
        return "alarm";
    }
}
