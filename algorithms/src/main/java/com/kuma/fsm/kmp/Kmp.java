package com.kuma.fsm.kmp;

/**
 * KMP string match
 *
 * @author kuma 2021-05-06
 */
public class Kmp {
    private int[][] dfa;
    private String pat;

    public Kmp(String pat) {
        this.pat = pat;
        // Use pattern string to init dfa array
        int length = pat.length();
        dfa = new int[length + 1][256];
        dfa[0][pat.charAt(0)] = 1;
        // Shadow state
        int shadow = 0;
        for (int i = 1; i < length; i++) {
            char ch = pat.charAt(i);
            // Next state when input is c and current state is i.
            for (int c = 0; c < 256; c++) {
                dfa[i][c] = dfa[shadow][c];
            }
            dfa[i][ch] = i + 1;
            shadow = dfa[shadow][ch];
        }
    }

    public int search(String txt) {
        int state = 0;
        int n = txt.length();
        int m = pat.length();

        // Use dfa array to match inout string.
        for (int i = 0; i < n; i++) {
            char ch = txt.charAt(i);
            state = dfa[state][ch];

            if (state == m) {
                return n - m + 1;
            }
        }
        return -1;
    }
}
