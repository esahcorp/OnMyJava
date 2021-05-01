package com.kuma.fsm.impl.statemap;

import com.kuma.fsm.impl.MachineState;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * wrapper class of event and nextState
 *
 * @author kuma 2021-05-01
 */
@Data
@AllArgsConstructor
public class WrapperEntry {

   private MachineState nextState;

   private Event event;
}
