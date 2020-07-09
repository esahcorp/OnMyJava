package com.kuma.stack;

import java.util.*;

/**
 * 栈应用：括号检查器
 * 检查代码文本中的左右括号是否匹配
 *
 * @author kuma 2020-07-07
 */
public class BracesLinter {

    private static final Character LEFT_PARENTHESIS = '(';
    private static final Character LEFT_BRACKET = '[';
    private static final Character LEFT_BRACE = '{';
    private static final Character RIGHT_PARENTHESIS = ')';
    private static final Character RIGHT_BRACKET = ']';
    private static final Character RIGHT_BRACE = '}';

    private final Map<Character, Character> closeToOpen;

    public BracesLinter() {
        closeToOpen = new HashMap<>(3);
        closeToOpen.put(RIGHT_BRACE, LEFT_BRACE);
        closeToOpen.put(RIGHT_BRACKET, LEFT_BRACKET);
        closeToOpen.put(RIGHT_PARENTHESIS, LEFT_PARENTHESIS);
    }

    public String lint(String text) {
        Deque<Character> stack = new ArrayDeque<>();
        Collection<Character> opening = closeToOpen.values();
        Set<Character> closings = closeToOpen.keySet();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (opening.contains(c)) {
                stack.push(c);
            } else if (closings.contains(c)) {
                Character last = stack.peek();
                if (closeToOpen.get(c).equals(last)) {
                    stack.pop();
                } else {
                    return String.format("Incorrect closing brace: %1$c at index %2$d", c, i);
                }
            }
        }
        if (!stack.isEmpty()) {
            return String.format("%1$c dose not have a closing brace", stack.getLast());
        }
        return "All braces match";
    }

}
