package com.kuma.microbenchmark.jmh;

/**
 * @author kuma 2021-02-11
 */
public class ForOrStream {

//    @Benchmark
//    public int testStream() {
//        final List<Integer> source = generateSource(length);
//        final int[] sum = {0};
//        source.stream().map(integer -> integer * 2).forEach(integer -> sum[0] = sum[0] + integer);
//        return sum[0];
//    }
//
//    @Benchmark
//    public int testStreamSum() {
//        final List<Integer> source = generateSource(length);
//        return source.stream().mapToInt(Integer::intValue).sum();
//    }
//
//    @Benchmark
//    public int testParallelStream() {
//        final List<Integer> source = generateSource(length);
//        final int[] sum = {0};
//        source.parallelStream().map(integer -> integer * 2).forEach(integer -> sum[0] = sum[0] + integer);
//        return sum[0];
//    }
}
