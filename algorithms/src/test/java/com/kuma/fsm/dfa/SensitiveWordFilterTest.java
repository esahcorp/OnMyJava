package com.kuma.fsm.dfa;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author kuma 2021-05-03
 */
public class SensitiveWordFilterTest {

    private SensitiveWordFilter filter;
    @Before
    public void initTrie() {
        filter = new SensitiveWordFilter();
        filter.initWordMap(Arrays.asList("玩游戏", "游戏", "游戏音乐"));
    }

    @Test
    public void should_replace_with_stars_given_a_sensitive_string() {
        String result = filter.searchSensitiveWordAndReplace("你玩游戏吗");
        assertEquals("你***吗", result);
    }

}