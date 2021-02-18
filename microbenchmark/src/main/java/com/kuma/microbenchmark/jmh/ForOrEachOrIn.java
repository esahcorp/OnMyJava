package com.kuma.microbenchmark.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 比较 Java 中不同迭代写法的性能区别
 *
 * <ul>
 *     <li>for i</li>
 *     <li>for in each</li>
 *     <li>forEach</li>
 * </ul>
 *
 * @author kuma 2021-02-11
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ForOrEachOrIn {

    @Param({"10", "100", "1000"})
    private int length;

    int x = 2;

    private List<Integer> generateSource(int length) {
        return IntStream.range(0, length).boxed().collect(Collectors.toList());
    }

    @Benchmark
    public int testFori() {
        final List<Integer> source = generateSource(length);
        final int[] sum = {0};
        for (int i = 0; i < source.size(); i++) {
            sum[0] = sum[0] + source.get(i) * 2;
        }
        return sum[0];
    }

    @Benchmark
    public int testForIn() {
        final List<Integer> source = generateSource(length);
        final int[] sum = {0};
        for (Integer integer : source) {
            sum[0] = sum[0] + integer * 2;
        }
        return sum[0];
    }

    @Benchmark
    public int testForEach() {
        final List<Integer> source = generateSource(length);
        final int[] sum = {0};
        source.forEach(integer -> sum[0] = sum[0] + integer * 2);
        return sum[0];
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ForOrEachOrIn.class.getSimpleName())
                .result("for-in-each.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
