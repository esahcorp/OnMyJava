package com.kuma.stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author kuma 2020-07-07
 */
public class BracesLinterTest {

    BracesLinter linter = new BracesLinter();

    @Test
    public void lint_all_match() {
        String code = "( var x={ y: [1, 2, 3] } )";
        assertEquals("All braces match", linter.lint(code));
    }

    @Test
    public void lint_not_closing() {
        String code = "( var x={ y: [1, 2, 3] } ";
        assertEquals("( dose not have a closing brace", linter.lint(code));
    }

    @Test
    public void lint_incorrect_match() {
        String code = "( var x={ y: [1, 2, 3] } ]";
        assertEquals("Incorrect closing brace: ] at index 25", linter.lint(code));
    }
}