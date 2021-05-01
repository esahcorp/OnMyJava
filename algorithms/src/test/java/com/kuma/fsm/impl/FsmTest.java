package com.kuma.fsm.impl;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test Cases
 *
 * @author kuma 2021-05-01
 */
public abstract class FsmTest {

    public abstract Fsm initLockedFsm();
    public abstract Fsm initUnlockedFsm();

    @Test
    public void should_be_unlocked_when_insert_coin_given_a_entrance_machine_with_locked_state() {
        Fsm fsm = initLockedFsm();

        String result = fsm.execute(Input.INSERT_COIN);

        assertEquals("opened", result);
        assertEquals(MachineState.UNLOCKED, fsm.getState());
    }

    @Test
    public void should_be_locked_and_alarm_when_pass_given_a_entrance_machine_with_locked_state() {
        Fsm fsm = initLockedFsm();

        String result = fsm.execute(Input.PASS);

        assertEquals("alarm", result);
        assertEquals(MachineState.LOCKED, fsm.getState());
    }

    @Test(expected = InvalidInputException.class)
    public void should_fail_when_execute_invalid_action_given_a_entrance_with_locked_state() {
        val locked = initLockedFsm();

        locked.execute(null);
    }

    @Test(expected = InvalidInputException.class)
    public void should_fail_when_execute_invalid_action_given_a_entrance_with_unlocked_state() {
        Fsm unlocked = initUnlockedFsm();
        unlocked.execute(null);
    }

    @Test
    public void should_locked_when_pass_given_a_entrance_machine_with_unlocked_state() {
        Fsm fsm = initUnlockedFsm();

        String result = fsm.execute(Input.PASS);

        assertEquals("closed", result);
        assertEquals(MachineState.LOCKED, fsm.getState());
    }

    @Test
    public void should_refund_and_unlocked_when_insert_coin_given_a_entrance_machine_with_unlocked_state() {
        Fsm fsm = initUnlockedFsm();

        String result = fsm.execute(Input.INSERT_COIN);

        assertEquals("refund", result);
        assertEquals(MachineState.UNLOCKED, fsm.getState());
    }
}