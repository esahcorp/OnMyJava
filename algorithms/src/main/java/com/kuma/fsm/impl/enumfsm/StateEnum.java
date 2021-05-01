package com.kuma.fsm.impl.enumfsm;

/**
 * State enum with behavior
 *
 * @author kuma 2021-05-02
 */
public enum StateEnum {

    /**
     * Locked
     */
    LOCKED {
        @Override
        public String insertCoin(EnumFsm fsm) {
            fsm.setState(UNLOCKED);
            return "opened";
        }

        @Override
        public String pass(EnumFsm fsm) {
            return "alarm";
        }
    },
    UNLOCKED {
        @Override
        public String insertCoin(EnumFsm fsm) {
            return "refund";
        }

        @Override
        public String pass(EnumFsm fsm) {
            fsm.setState(LOCKED);
            return "closed";
        }
    };

    public abstract String insertCoin(EnumFsm enumFsm);
    public abstract String pass(EnumFsm fsm);
}
