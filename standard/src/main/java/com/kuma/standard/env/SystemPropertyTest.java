package com.kuma.standard.env;

import com.kuma.standard.encoding.UnicodeTest;

import java.io.File;

/**
 * 环境属性入口
 *
 * @author kuma 2021-02-14
 */
public class SystemPropertyTest {

    public static void main(String[] args) {
        System.out.println("java.class.path: " + System.getProperty("java.class.path"));
        System.out.println("java.home: " + System.getProperty("java.home"));
        System.out.println("java.version: " + System.getProperty("java.version"));
        // 处理不可见字符
        String lineSeparator = System.getProperty("line.separator");
        char c = lineSeparator.charAt(0);
        System.out.println("line.separator: " + UnicodeTest.char2Unicode(c));
        // 便捷方法
        System.out.println("line.separator: " + UnicodeTest.char2Unicode(System.lineSeparator().charAt(0)));
        System.out.println("os.name: " + System.getProperty("os.name"));
        System.out.println("os.version: " + System.getProperty("os.version"));
        // File.pathSeparator 用于在文件路径列表中分隔各个文件路径
        System.out.println("path.separator: " + System.getProperty("path.separator"));
        System.out.println(File.pathSeparator);
        // File.separator 是/或\用于拆分到特定文件的路径
        System.out.println("file.separator: " + System.getProperty("file.separator"));
        System.out.println(File.separator);
        System.out.println("security.destroy: " + System.getProperty("security.destroy"));
        System.out.println("user.dir: " + System.getProperty("user.dir"));
        System.out.println("user.home: " + System.getProperty("user.home"));
        System.out.println("user.name: " + System.getProperty("user.name"));
    }
}
