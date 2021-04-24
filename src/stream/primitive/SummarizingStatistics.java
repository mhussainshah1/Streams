package stream.primitive;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class SummarizingStatistics {

    public static void main(String[] args) {
        IntStream ints = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(max(ints));
        
        ints = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(range(ints));
        
         ints = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(average(ints));
        
        ints = IntStream.empty();
        System.out.println(count(ints));
        
        ints = IntStream.empty();
        System.out.println(sum(ints));
        
    }

    private static int max(IntStream ints) {
        OptionalInt optional = ints.max();
        return optional.orElseThrow(RuntimeException::new);
    }

    private static int range(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) {
            throw new RuntimeException();
        }
        return stats.getMax() - stats.getMin();
    }

    private static long count(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        return stats.getCount();
    }

    private static long sum(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        return stats.getSum();
    }    

    private static double average(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        return stats.getAverage();
    }
}