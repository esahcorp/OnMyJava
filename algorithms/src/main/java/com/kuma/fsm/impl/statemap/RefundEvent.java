package com.kuma.fsm.impl.statemap;

/**
 * Entrance Machine refund event
 *
 * @author kuma 2021-05-01
 */
public class RefundEvent implements Event {
    @Override
    public String action() {
        return "refund";
    }
}
