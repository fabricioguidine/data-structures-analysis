package com.bookdepository.benchmark;

import com.bookdepository.algorithms.sorting.QuickSort;
import com.bookdepository.algorithms.sorting.HeapSort;
import com.bookdepository.model.Record;
import com.bookdepository.test.TestUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JMH microbenchmarks for sorting algorithms.
 * Run this class to execute performance benchmarks.
 * 
 * Usage: mvn test -Dtest=SortingBenchmark
 * Or: java -jar target/benchmarks.jar
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class SortingBenchmark {

    private Record[] records100;
    private Record[] records1000;
    private Record[] records10000;
    private Record[] records50000;
    
    private QuickSort quickSort;
    private HeapSort heapSort;

    @Setup
    public void setup() {
        quickSort = new QuickSort();
        heapSort = new HeapSort();
        
        // Generate test data once
        records100 = TestUtils.generateRandomRecords(100);
        records1000 = TestUtils.generateRandomRecords(1000);
        records10000 = TestUtils.generateRandomRecords(10000);
        records50000 = TestUtils.generateRandomRecords(50000);
    }

    @Benchmark
    public void quickSort100() {
        Record[] copy = TestUtils.cloneRecords(records100);
        quickSort.sort(copy);
    }

    @Benchmark
    public void quickSort1000() {
        Record[] copy = TestUtils.cloneRecords(records1000);
        quickSort.sort(copy);
    }

    @Benchmark
    public void quickSort10000() {
        Record[] copy = TestUtils.cloneRecords(records10000);
        quickSort.sort(copy);
    }

    @Benchmark
    public void quickSort50000() {
        Record[] copy = TestUtils.cloneRecords(records50000);
        quickSort.sort(copy);
    }

    @Benchmark
    public void heapSort100() {
        Record[] copy = TestUtils.cloneRecords(records100);
        heapSort.sort(copy);
    }

    @Benchmark
    public void heapSort1000() {
        Record[] copy = TestUtils.cloneRecords(records1000);
        heapSort.sort(copy);
    }

    @Benchmark
    public void heapSort10000() {
        Record[] copy = TestUtils.cloneRecords(records10000);
        heapSort.sort(copy);
    }

    @Benchmark
    public void heapSort50000() {
        Record[] copy = TestUtils.cloneRecords(records50000);
        heapSort.sort(copy);
    }

    /**
     * Main method to run benchmarks.
     * Can be executed directly or via Maven.
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortingBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}

