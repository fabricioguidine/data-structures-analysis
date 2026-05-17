package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.io.FileReader;
import com.bookdepository.io.OutputFileWriter;
import com.bookdepository.io.PerformanceResult;
import com.bookdepository.algorithms.sorting.QuickSort;
import com.bookdepository.algorithms.sorting.HeapSort;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Part I: Sorting Algorithms Experiment
 *
 * This program reads book records from the dataset and analyzes
 * the performance of QuickSort and HeapSort algorithms.
 *
 * Output: Results are written to output/output.txt with one block per
 * algorithm in the format documented in {@link OutputFileWriter}.
 */
public class SortingExperiment {

    public static void main(String[] args) {
        try {
            // Clear output file
            OutputFileWriter.clearOutput();

            // Read input sizes
            List<Integer> sizes = FileReader.readInputSizes();

            // Read all records once
            List<Record> allRecords = FileReader.readAllRecords();
            System.out.println("Total records loaded: " + allRecords.size());

            List<PerformanceResult> quickSortResults = new ArrayList<>();
            List<PerformanceResult> heapSortResults = new ArrayList<>();

            // Test each size, collecting results per algorithm
            for (Integer size : sizes) {
                System.out.println("Processing size: " + size);

                int actualSize = Math.min(size, allRecords.size());
                List<Record> records = allRecords.subList(0, actualSize);

                Record[] quickSortArray = records.toArray(new Record[0]);
                QuickSort quickSort = new QuickSort();
                quickSortResults.add(quickSort.sort(quickSortArray));

                Record[] heapSortArray = records.toArray(new Record[0]);
                HeapSort heapSort = new HeapSort();
                heapSortResults.add(heapSort.sort(heapSortArray));
            }

            // Write all results grouped by algorithm
            OutputFileWriter.writeSortingResults(quickSortResults, "QuickSort");
            OutputFileWriter.writeSortingResults(heapSortResults, "HeapSort");

            System.out.println("Experiment completed. Results written to output/output.txt");

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
