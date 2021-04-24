package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MyStream<T> {

    public static void main(String[] args) {
        //Finite stream
        Stream<String> empty = Stream.empty(); // count = 0
        Stream<Integer> singleElement = Stream.of(1); // count = 1
        Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
        System.out.println(fromArray);

        //Collection to stream
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> fromList = list.stream();
        Stream<String> fromListParallel = list.parallelStream();

        //Infinite Stream
        Stream<Double> randoms = Stream.generate(Math::random);
//        randoms.forEach(System.out::println);

        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
//        oddNumbers.forEach(System.out::println);

        Stream<Integer> oddNumberUnder100 = Stream.iterate(
                1, // seed
                n -> n < 100, // Predicate to specify when done
                n -> n + 2); // UnaryOperator to get next value
    }
}