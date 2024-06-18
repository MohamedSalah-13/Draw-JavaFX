package com.hamza.draw.draw;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Other_size {

    public void array_count_duplicates(int[][] array) {
        System.out.println("-----------array count duplicates-------------------");
        int[] flatArray = Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .toArray();

        //Count duplicates
        Object[] duplicates = Arrays.stream(flatArray).boxed()
                // Group all integers together
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                // Keep key/value pairs whose value > 1
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .toArray();

        System.out.println(duplicates.length);
    }

    public double get_space(int xy, double d1, double d2, int count) {
        return (xy - (d1 + d2)) / count;
    }

    public double get_sum_Array(int i ,double[][] array) {
        double sum = 0;
        for (double[] doubles : array) {
            sum += doubles[i];
        }
        return sum;
    }

    public double get_rest_size(double d1,double d2) {
        return d1 - d2;
    }

    public static List<Integer> getRepeatingNumbersJDK18Map(int[][] array) {
        List<Integer> duplicateNumbers = Arrays.stream(array)
                .flatMapToInt(IntStream::of)
                .boxed()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return duplicateNumbers;
    }
}
