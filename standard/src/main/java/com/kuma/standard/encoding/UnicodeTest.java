package com.kuma.standard.encoding;

/**
 * @author kuma 2021-02-14
 */
public class UnicodeTest {

    public static String char2Unicode(char source) {
        return "\\u" + Integer.toHexString(source);
    }

    public static void main(String[] args) {
        System.out.println(UnicodeTest.char2Unicode('\n'));
    }
}
