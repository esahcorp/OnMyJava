package com.kuma.fsm.dfa;

import java.util.Collection;
import java.util.Objects;

/**
 * DFA implement Sensitive Words Filter
 *
 * @author kuma 2021-05-03
 */
public class SensitiveWordFilter {

    private final Trie sensitiveTrie;

    public SensitiveWordFilter() {
        sensitiveTrie = new Trie();
    }

    public void initWordMap(Collection<String> keys) {
        if (Objects.isNull(keys)) {
            return;
        }

        for (String key : keys) {
            sensitiveTrie.insert(key);
        }
    }

    /**
     * Find the sensitive word and replace with '*' or return old string if insensitive.
     */
    public String searchSensitiveWordAndReplace(String txt) {
        int index = 0;
        // Copy of old string
        StringBuilder sb = new StringBuilder(txt);

        while (index < txt.length()) {
            int len = sensitiveTrie.search(txt, index);
            if (len > 0) {
                for (int i = 0; i < len; i++) {
                    sb.setCharAt(index + i, '*');
                }
                index += len;
            } else {
                index ++;
            }
        }
        return sb.toString();
    }
}
