package com.kuma.standard.nio;

import lombok.val;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @author kuma 2021-02-20
 */
public class FileNioCopy {

    public static void main(String[] args) {

        copyFile("result.json", "copy.result.json");
    }

    public static void copyFile(String source, String dest) {
        File sourceFile = new File(source);
        File destFile = new File(dest);
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();
//            baseNioCopyFile(sourceFile, destFile);
            fastNioCopyFile(sourceFile, destFile);
            System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void baseNioCopyFile(File sourceFile, File destFile) throws IOException {
        try (val fis = new FileInputStream(sourceFile);
             val fos = new FileOutputStream(destFile);
             val in = fis.getChannel();
             val out = fos.getChannel()) {
            val buffer = ByteBuffer.allocate(1024);
            for (int length; (length = in.read(buffer)) != -1; ) {
                System.out.println("读入字节数：" + length);
                buffer.flip();
                for (int outLength; (outLength = out.write(buffer)) > 0; ) {
                    System.out.println("写入字节数：" + outLength);
                }
                buffer.clear();
            }
            out.force(true);
        }
    }

    private static void fastNioCopyFile(File source, File dest) throws IOException {
        try (val fis = new FileInputStream(source);
             val fos = new FileOutputStream(dest);
             val in = fis.getChannel();
             val out = fos.getChannel()) {
            // 返回此通道文件的当前大小 in.size()
            out.transferFrom(in, 0, in.size());
            out.force(true);
        }
    }
}
