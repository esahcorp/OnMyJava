package com.kuma.fsm.kmp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author kuma 2021-05-06
 */
public class KmpTest {

    @Test
    public void should_return_correct_index_when_string_match() {
        Kmp kmp = new Kmp("ABABC");
        int index = kmp.search("ABABABABC");
        Assert.assertEquals(5, index);
    }

    @Test
    public void should_return_negative_one_when_not_match() {
        Kmp kmp = new Kmp("ABABC");
        int index = kmp.search("ABABABAB");
        Assert.assertEquals(-1, index);
    }
}